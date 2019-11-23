package com.bandaddict.Repository;

import com.bandaddict.Entity.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Event repository
 */
public interface EventRepository extends CrudRepository<Event, Long> {

    /**
     * Find all
     * @return list of events
     */
    List<Event> findAll();
}
