package com.github.aprofromindia.repositories;

import com.github.aprofromindia.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Pair;

import java.time.LocalDateTime;

/**
 * Created by Apro on 18-07-2017.
 */

public interface PersonRepository extends Repository<Person, Long> {

    @Query("select count(p) from Person p join p.device d join d.events e join e.content c" +
            " where c.id = :contentId and d.id = :deviceId and p.appears between :startTime and :endTime")
    long personCountByDeviceAndContent(@Param("deviceId") long deviceId, @Param("contentId") long contentId,
                                       @Param("startTime") LocalDateTime start, @Param("endTime") LocalDateTime end);

//    @Query("select count(p) from Person p where p.device.events.content.id = :contentId" +
//            " and p.device.id = :deviceId and p.appears between :startTime and :endTime")
//    long personCountByDeviceAndContent(@Param("deviceId") long deviceId, @Param("contentId") long contentId,
//                                       @Param("startTime") LocalDateTime start, @Param("endTime") LocalDateTime end);

//    @Query("select count(p) from Person p join p.device d where d.id = :deviceId and p.appears between :startTime and :endTime")
//    long personCountByDeviceAndContent(@Param("deviceId") long deviceId,
//                                       @Param("startTime") LocalDateTime start, @Param("endTime") LocalDateTime end);

    @Query("select avg(p.age) from Person p join p.device d join d.events e join e.content c" +
            " where c.id = :contentId and d.id = :deviceId and p.appears between :startTime and :endTime")
    long avgAgeByDevicesAndContent(@Param("deviceId") long deviceId, @Param("contentId") long contentId,
                                   @Param("startTime") LocalDateTime start, @Param("endTime") LocalDateTime end);

    @Query("select sum(case when p.gender = 'MALE' then 1 end), sum(case when p.gender = 'FEMALE' then 1 end)" +
            " from Person p join p.device d join d.events e join e.content c where c.id = :contentId" +
            " and d.id = :deviceId and p.appears between :startTime and :endTime")
    Pair<Integer, Integer> genderDistByDevicesAndContent(@Param("deviceId") long deviceId,
                                                         @Param("contentId") long contentId,
                                                         @Param("startTime") LocalDateTime start,
                                                         @Param("endTime") LocalDateTime end);
}
