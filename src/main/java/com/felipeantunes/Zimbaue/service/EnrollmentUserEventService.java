package com.felipeantunes.Zimbaue.service;

import com.felipeantunes.Zimbaue.interfaces.IEnrollmentDTO;
import com.felipeantunes.Zimbaue.model.dto.EnrollmentUserEventDTO;
import com.felipeantunes.Zimbaue.model.dto.EventDTO;
import com.felipeantunes.Zimbaue.model.entity.EnrollmentUserEvent;
import com.felipeantunes.Zimbaue.model.entity.Event;
import com.felipeantunes.Zimbaue.repository.EnrollmentUserEventRepository;
import com.felipeantunes.Zimbaue.repository.EventRepository;
import com.felipeantunes.Zimbaue.repository.UserRepository;
import com.felipeantunes.Zimbaue.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentUserEventService {

    @Autowired
    private EnrollmentUserEventRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public EnrollmentUserEventDTO save(EnrollmentUserEventDTO enrollmentDTO) {
        //Verifies if the user exists
        boolean userExists = this.userRepository.existsById(enrollmentDTO.getUserDTO().getId());
        if(!userExists){
            throw new NotFoundException("Usuario nao encontrado");
        }

        //Verifies if the event exists
        boolean eventExists = this.eventRepository.existsById(enrollmentDTO.getEventDTO().getId());
        if(!eventExists){
            throw new NotFoundException("Evento nao existe!");
        }

        EnrollmentUserEvent enrollmentToBeSaved = EnrollmentUserEventDTO.toEnrollmentEntity(enrollmentDTO);
        EnrollmentUserEvent savedEnrollment = this.enrollmentRepository.save(enrollmentToBeSaved);
        IEnrollmentDTO iEnrollmentDTO = this.enrollmentRepository.getEnrollmentById(savedEnrollment.getId());
        return iEnrollmentDTO.toEnrollmentDTO();
    }

    public List<EnrollmentUserEventDTO> getEnrollmentList() {
        List<IEnrollmentDTO> iEnrollmentDTOList = this.enrollmentRepository.getEnrollmentList();
        ArrayList<EnrollmentUserEventDTO> enrollmentUserEventDTOList = new ArrayList<>();
        for(IEnrollmentDTO iEnrollmentDTO : iEnrollmentDTOList){
            enrollmentUserEventDTOList.add(iEnrollmentDTO.toEnrollmentDTO());
        }
        return enrollmentUserEventDTOList;
    }

}
