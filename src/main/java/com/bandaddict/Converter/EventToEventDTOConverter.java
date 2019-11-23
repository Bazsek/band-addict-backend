package com.bandaddict.Converter;

import com.bandaddict.DTO.EventDTO;
import com.bandaddict.Entity.Event;
import org.springframework.core.convert.converter.Converter;

/**
 * Event to EventDTO converter
 */
public class EventToEventDTOConverter implements Converter<Event, EventDTO> {

    @Override
    public final EventDTO convert(final Event source) {
        final EventDTO eventDTO = new EventDTO();

        eventDTO.setId(source.getId());
        eventDTO.setTitle(source.getTitle());
        eventDTO.setDescription(source.getDescription());
        eventDTO.setStart(source.getStart());
        eventDTO.setEnd(source.getEnd());
        eventDTO.setType(source.getType().getValue());

        return eventDTO;
    }
}
