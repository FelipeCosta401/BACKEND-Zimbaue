package com.felipeantunes.Zimbaue.repository;

import com.felipeantunes.Zimbaue.interfaces.IEnrollmentDTO;
import com.felipeantunes.Zimbaue.model.entity.EnrollmentUserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentUserEventRepository
        extends JpaRepository<EnrollmentUserEvent, Integer> {

    @Query(value = "SELECT \n" +
            "\ten.id `enrollmentId`,\n" +
            "    en.`status` `enrollmentStatus`,\n" +
            "    en.created_at `enrollmentDate`,\n" +
            "\tu.id `userId`,\n" +
            "    u.`name` `userName`,\n" +
            "    u.email `userEmail`,\n" +
            "    e.id `eventId`,\n" +
            "    e.title `eventTitle`,\n" +
            "    e.`description` `eventDescription`,\n" +
            "    e.`date` `eventDate`\n" +
            "FROM enrollment_user_event `en`\n" +
            "INNER JOIN `user` `u` ON en.user_id = u.id\n" +
            "INNER JOIN `event` `e` ON en.event_id = e.id;", nativeQuery = true)
    List<IEnrollmentDTO> getEnrollmentList();

    @Query(value = "SELECT \n" +
            "\ten.id `enrollmentId`,\n" +
            "    en.`status` `enrollmentStatus`,\n" +
            "    en.created_at `enrollmentDate`,\n" +
            "\tu.id `userId`,\n" +
            "    u.`name` `userName`,\n" +
            "    u.email `userEmail`,\n" +
            "    e.id `eventId`,\n" +
            "    e.title `eventTitle`,\n" +
            "    e.`description` `eventDescription`,\n" +
            "    e.`date` `eventDate`\n" +
            "FROM enrollment_user_event `en`\n" +
            "INNER JOIN `user` `u` ON en.user_id = u.id\n" +
            "INNER JOIN `event` `e` ON en.event_id = e.id\n" +
            "WHERE en.id = :id;", nativeQuery = true)
    IEnrollmentDTO getEnrollmentById(@Param("id") Integer id);
}
