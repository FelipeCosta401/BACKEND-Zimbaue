package com.felipeantunes.Zimbaue.service;

import com.felipeantunes.Zimbaue.interfaces.IEventDTO;
import com.felipeantunes.Zimbaue.model.dto.EventDTO;
import com.felipeantunes.Zimbaue.model.entity.Event;
import com.felipeantunes.Zimbaue.repository.EventRepository;
import com.felipeantunes.Zimbaue.repository.UserRepository;
import com.felipeantunes.Zimbaue.service.exceptions.BadRequestException;
import com.felipeantunes.Zimbaue.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public EventDTO save(EventDTO eventDTO) {
        //Verifies if the user exists
        boolean userExists = userRepository.existsById(eventDTO.getUserDTO().getId());

        //validations
        Event event = EventDTO.toEventEntity(eventDTO);
        if(event.getTitle() == null){
            throw new BadRequestException("Campo título obrigatório");
        } else if(event.getDate() == null){
            throw new BadRequestException("Data do evento é obrigatório");
        } else if(!userExists){
            throw new NotFoundException("Usuário criador do evento não encontrado");
        }

        //Save the new event
        Event createdEvent = this.eventRepository.save(event);
        IEventDTO iEventDTO = this.eventRepository.getEventById(createdEvent.getId());
        return iEventDTO.toEventDTO();
    }

    public List<EventDTO> getEventList() {
        List<IEventDTO> iEventDTOList = this.eventRepository.getEventList();
        ArrayList<EventDTO> eventDTOList = new ArrayList<>();
        for(IEventDTO iEventDTO : iEventDTOList){
            eventDTOList.add(iEventDTO.toEventDTO());
        }
        return eventDTOList;
    }
}
