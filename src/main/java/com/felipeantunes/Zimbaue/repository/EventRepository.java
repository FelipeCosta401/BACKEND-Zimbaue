package com.felipeantunes.Zimbaue.repository;

import com.felipeantunes.Zimbaue.interfaces.IEventDTO;
import com.felipeantunes.Zimbaue.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query(value = "SELECT \n" +
            "\te.id `eventID`,\n" +
            "    e.title `eventTitle`,\n" +
            "    e.`description` `eventDescription`,\n" +
            "    e.`date` `eventDate`,\n" +
            "    e.created_at `eventCreatedAt`,\n" +
            "    u.id `userId`,\n" +
            "    u.`name` `userName`,\n" +
            "    u.email `userEmail`,\n" +
            "    u.created_at `userCreatedAt`\n" +
            "FROM `event` `e` \n" +
            "INNER JOIN `user` `u` ON e.user_id = u.id\n" +
            "WHERE e.id = :id;", nativeQuery = true)
    IEventDTO getEventById(@Param("id") Integer id);

    @Query(value = "SELECT \n" +
            "\te.id `eventID`,\n" +
            "    e.title `eventTitle`,\n" +
            "    e.`description` `eventDescription`,\n" +
            "    e.`date` `eventDate`,\n" +
            "    e.created_at `eventCreatedAt`,\n" +
            "    u.id `userId`,\n" +
            "    u.`name` `userName`,\n" +
            "    u.email `userEmail`,\n" +
            "    u.created_at `userCreatedAt`\n" +
            "FROM `event` `e` \n" +
            "INNER JOIN `user` `u` ON e.user_id = u.id;", nativeQuery = true)
    List<IEventDTO> getEventList();
}
