package com.bandaddict.Converter;

import com.bandaddict.DTO.InstrumentDTO;
import com.bandaddict.Entity.Instrument;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter instrument --> instrument DTO
 */
public class InstrumentToInstrumentDTOConverter implements Converter<Instrument, InstrumentDTO> {

    @Override
    public InstrumentDTO convert(final Instrument instrument) {
        final InstrumentDTO instrumentDTO = new InstrumentDTO();

        instrumentDTO.setId(instrument.getId());
        instrumentDTO.setName(instrument.getName());
        instrumentDTO.setDescription(instrument.getDescription());
        instrumentDTO.setType(instrument.getType());

        return instrumentDTO;
    }
}
