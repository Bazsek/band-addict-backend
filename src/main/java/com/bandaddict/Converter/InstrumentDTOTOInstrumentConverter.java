package com.bandaddict.Converter;

import com.bandaddict.DTO.InstrumentDTO;
import com.bandaddict.Entity.Instrument;
import org.springframework.core.convert.converter.Converter;

public class InstrumentDTOTOInstrumentConverter implements Converter<InstrumentDTO, Instrument> {

    @Override
    public Instrument convert(final InstrumentDTO instrumentDTO) {
        final Instrument instrument = new Instrument();

        instrument.setName(instrumentDTO.getName());
        instrument.setDescription(instrumentDTO.getDescription());
        instrument.setType(instrumentDTO.getType());

        return instrument;
    }
}
