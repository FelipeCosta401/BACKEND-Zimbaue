package com.felipeantunes.Zimbaue.controller;

import com.felipeantunes.Zimbaue.model.dto.EventDTO;
import com.felipeantunes.Zimbaue.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDTO>> getEventList(){
        return ResponseEntity.status(HttpStatus.OK).body(this.eventService.getEventList());
    }

    @PostMapping
    public ResponseEntity<EventDTO> saveEvent(@RequestBody EventDTO eventDTO){
        EventDTO createdEvent = this.eventService.save(eventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }
}
