package com.github.aprofromindia.repositories;

import com.github.aprofromindia.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by Apro on 18-07-2017.
 */

public interface PersonRepository extends Repository<Person, Long> {

    @Query("select count(p) from Person p join p.device d join d.events e join e.content c" +
            " where c.id = :contentId and d.id = :deviceId and p.appears between :startTime and :endTime")
    long personCountByDeviceAndContent(@Param("deviceId") long deviceId, @Param("contentId") long contentId,
                                       @Param("startTime") LocalDateTime start, @Param("endTime") LocalDateTime end);

    @Query("select avg(p.age) from Person p join p.device d join d.events e join e.content c" +
            " where c.id = :contentId and d.id = :deviceId and p.appears between :startTime and :endTime")
    float avgAgeByDevicesAndContent(@Param("deviceId") long deviceId, @Param("contentId") long contentId,
                                    @Param("startTime") LocalDateTime start, @Param("endTime") LocalDateTime end);

    @Query("select map(sum(case p.gender when 'MALE' then 1 end) as male, sum(case p.gender when 'FEMALE' then 1 end) as female) from Person p join p.device d join d.events e" +
            " join e.content c where c.id = :contentId and d.id = :deviceId and p.appears between :startTime and :endTime")
    Map<String, Long> genderDistByDevicesAndContent(@Param("deviceId") long deviceId,
                                                    @Param("contentId") long contentId,
                                                    @Param("startTime") LocalDateTime start,
                                                    @Param("endTime") LocalDateTime end);
}
