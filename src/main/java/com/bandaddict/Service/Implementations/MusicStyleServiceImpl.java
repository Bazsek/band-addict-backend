package com.bandaddict.Service.Implementations;

import com.bandaddict.DTO.MusicStyleDTO;
import com.bandaddict.Repository.MusicStyleRepository;
import com.bandaddict.Service.MusicStyleService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicStyleServiceImpl implements MusicStyleService {

    private MusicStyleRepository musicStyleRepository;
    private ConversionService conversionService;

    public MusicStyleServiceImpl(final MusicStyleRepository musicStyleRepository, final ConversionService conversionService) {
        this.musicStyleRepository = musicStyleRepository;
        this.conversionService = conversionService;
    }

    @Override
    public List<MusicStyleDTO> findAll() {
        return musicStyleRepository.findAll().stream().
                map(style -> conversionService.convert(style, MusicStyleDTO.class)).collect(Collectors.toList());
    }
}
