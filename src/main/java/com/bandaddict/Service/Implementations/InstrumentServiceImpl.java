package com.bandaddict.Service.Implementations;

import com.bandaddict.DTO.InstrumentDTO;
import com.bandaddict.Repository.InstrumentRepository;
import com.bandaddict.Service.InstrumentService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    private InstrumentRepository instrumentRepository;
    private ConversionService conversionService;

    public InstrumentServiceImpl(final InstrumentRepository instrumentRepository, final ConversionService conversionService) {
        this.instrumentRepository = instrumentRepository;
        this.conversionService = conversionService;
    }

    @Override
    public List<InstrumentDTO> findAll() {
        return instrumentRepository.findAll().stream().
                map(instrument -> conversionService.convert(instrument, InstrumentDTO.class)).collect(Collectors.toList());
    }
}
