package com.bandaddict.Service;

import com.bandaddict.DTO.EventDTO;
import com.bandaddict.Entity.Band;

import java.util.List;

/**
 * Event service interface
 */
public interface EventService {

    /**
     * Create event
     * @param eventDTO eventDTO
     * @param band Band
     */
    void addEvent(EventDTO eventDTO, Band band);

    /**
     * Get all events of the band
     * @param bandId id of the band
     * @return list of events
     */
    List<EventDTO> getEvents(Long bandId);

    /**
     * Delete event by id
     * @param id id
     */
    void deleteEvent(Long id);

    /**
     * Edit event
     * @param eventDTO eventDTO
     */
    void editEvent(EventDTO eventDTO);
}
