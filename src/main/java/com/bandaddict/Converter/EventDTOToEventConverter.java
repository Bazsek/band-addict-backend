package com.bandaddict.Converter;

import com.bandaddict.DTO.EventDTO;
import com.bandaddict.Entity.Event;
import com.bandaddict.Enum.EvenType;
import org.springframework.core.convert.converter.Converter;

public class EventDTOToEventConverter implements Converter<EventDTO, Event> {
    @Override
    public final Event convert(final EventDTO source) {
        final Event event = new Event();

        event.setId(source.getId());
        event.setTitle(source.getTitle());
        event.setDescription(source.getDescription());
        event.setStart(source.getStart());
        event.setEnd(source.getEnd());
        event.setType(EvenType.getEnum(source.getType()));

        return event;
    }
}
