package com.bandaddict.Service.Implementations;

import com.bandaddict.Entity.Instrument;
import com.bandaddict.Entity.MusicStyle;
import com.bandaddict.Exception.PrefillDataException;
import com.bandaddict.Repository.InstrumentRepository;
import com.bandaddict.Repository.MusicStyleRepository;
import com.bandaddict.Service.JsonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonServiceImpl implements JsonService {

    private MusicStyleRepository musicStyleRepository;
    private InstrumentRepository instrumentRepository;

    /**
     * Constructor
     *
     * @param musicStyleRepository the musicStyleRepository bean
     * @param instrumentRepository the instrumentRepository bean
     */
    public JsonServiceImpl(final MusicStyleRepository musicStyleRepository, final InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
        this.musicStyleRepository = musicStyleRepository;
    }

    @PostConstruct
    protected final void init() {
        if (instrumentRepository.findAll().isEmpty()) {
            instrumentRepository.saveAll(getInstruments());
        }

        if (musicStyleRepository.findAll().isEmpty()) {
            musicStyleRepository.saveAll(getMusicStyles());
        }
    }

    private List<Instrument> getInstruments() {
        final ObjectMapper mapper = new ObjectMapper();
        List<Instrument> instruments = new ArrayList<>();
        final ClassPathResource resource = new ClassPathResource("/json/instruments.json");

        try {
            final byte[] dataArr = FileCopyUtils.copyToByteArray(resource.getInputStream());
            instruments = mapper.readValue(new String(dataArr, StandardCharsets.UTF_8), mapper.getTypeFactory().constructCollectionType(List.class, Instrument.class));
        } catch (final IOException e) {
            throw new PrefillDataException("Failed to load instruments from json!", e);
        }

        return instruments;
    }

    private List<MusicStyle> getMusicStyles() {
        final ObjectMapper mapper = new ObjectMapper();
        List<MusicStyle> musicStyles = new ArrayList<>();
        final ClassPathResource resource = new ClassPathResource("/json/musicStyles.json");

        try {
            final byte[] dataArr = FileCopyUtils.copyToByteArray(resource.getInputStream());
            musicStyles = mapper.readValue(new String(dataArr, StandardCharsets.UTF_8), mapper.getTypeFactory().constructCollectionType(List.class, MusicStyle.class));
        } catch (final IOException e) {
            throw new PrefillDataException("Failed to load music styles from json!", e);
        }

        return musicStyles;
    }

}
