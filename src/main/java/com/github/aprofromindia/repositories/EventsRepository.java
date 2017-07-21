package com.github.aprofromindia.repositories;

import com.github.aprofromindia.entities.Event;
import org.springframework.data.repository.Repository;

/**
 * Created by Apro on 19-07-2017.
 */
public interface EventsRepository extends Repository<Event, Long> {
    void save(Event event);
}
