package com.felipeantunes.Zimbaue.controller;

import com.felipeantunes.Zimbaue.model.dto.EnrollmentUserEventDTO;
import com.felipeantunes.Zimbaue.model.dto.EventDTO;
import com.felipeantunes.Zimbaue.model.dto.UserDTO;
import com.felipeantunes.Zimbaue.service.EnrollmentUserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricao")
public class EnrollmentUserEventController {
    @Autowired
    private EnrollmentUserEventService enrollmentService;

    @GetMapping
    public ResponseEntity<List<EnrollmentUserEventDTO>> getEnrollmentList(){
        List<EnrollmentUserEventDTO> enrollmentDTOList = this.enrollmentService.getEnrollmentList();
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentDTOList);
    }

    @PostMapping
    public ResponseEntity<EnrollmentUserEventDTO>
    registerEnrollment(@RequestBody EnrollmentUserEventDTO enrollmentDTO){
        EnrollmentUserEventDTO createdEnrollment = this.enrollmentService.save(enrollmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEnrollment);
    }
}
