package com.bandaddict.Service.Implementations;

import com.bandaddict.DTO.EventDTO;
import com.bandaddict.Entity.Band;
import com.bandaddict.Entity.Event;
import com.bandaddict.Enum.EvenType;
import com.bandaddict.Exception.NotFoundException;
import com.bandaddict.Repository.BandRepository;
import com.bandaddict.Repository.EventRepository;
import com.bandaddict.Service.EventService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of EventService
 */
@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private ConversionService conversionService;
    private BandRepository bandRepository;

    /**
     * Constructor
     */
    public EventServiceImpl(final EventRepository eventRepository, final ConversionService conversionService,
                            final BandRepository bandRepository){
        this.eventRepository = eventRepository;
        this.conversionService = conversionService;
        this.bandRepository = bandRepository;
    }

    @Override
    public void addEvent(final EventDTO eventDTO, final Band band) {
        final Event event = conversionService.convert(eventDTO, Event.class);
        event.setCreatedBy(band);
        event.setType(event.getEnd() != null ? EvenType.LONG :EvenType.ONE_DAY );

        eventRepository.save(event);
    }

    @Override
    public List<EventDTO> getEvents(final Long bandId) {
        final Band band = bandRepository.findOneById(bandId);

        return eventRepository.findAll().stream().filter(event -> event.getCreatedBy().equals(band)).map(event ->
                conversionService.convert(event, EventDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteEvent(final Long id) {
        final Event event = eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event not found!"));

        eventRepository.delete(event);
    }

    @Override
    public void editEvent(final EventDTO eventDTO) {
        final Event event = eventRepository.findById(eventDTO.getId()).orElseThrow(()-> new NotFoundException("Event not found"));

        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setStart(eventDTO.getStart());
        event.setEnd(eventDTO.getEnd());
        event.setType(eventDTO.getEnd() == null ? EvenType.ONE_DAY : EvenType.LONG);

        eventRepository.save(event);
    }
}
