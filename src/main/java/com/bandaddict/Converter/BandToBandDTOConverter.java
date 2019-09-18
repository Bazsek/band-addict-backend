package com.bandaddict.Converter;

import com.bandaddict.DTO.BandDTO;
import com.bandaddict.DTO.MusicStyleDTO;
import com.bandaddict.DTO.SongDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.Band;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BandToBandDTOConverter implements Converter<Band, BandDTO> {

    private final ConversionService conversionService;

    public BandToBandDTOConverter(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public BandDTO convert(final Band band) {
        final BandDTO bandDTO = new BandDTO();
        final List<UserDTO> bandMemberList = new ArrayList<>();

        bandDTO.setId(band.getId());
        bandDTO.setName(band.getName());
        bandDTO.setShortDescription(band.getShortDescription());
        bandDTO.setCountry(band.getCountry());
        bandDTO.setFormedDate(band.getFormedDate());
        bandDTO.setBandLogo(band.getBandLogo());

        if (band.getBandMembers() != null) {
            bandDTO.setBandMembers(band.getBandMembers().stream().
                    map(user -> conversionService.convert(user, UserDTO.class)).collect(Collectors.toList()));
        }

        if (band.getStyles() != null) {
            bandDTO.setStyles(band.getStyles().stream().
                    map(style -> conversionService.convert(style, MusicStyleDTO.class)).collect(Collectors.toList()));
        }

        if (band.getSongs() != null) {
            bandDTO.setSongs(band.getSongs().stream().
                    map(song -> conversionService.convert(song, SongDTO.class)).collect(Collectors.toList()));
        }

        return bandDTO;
    }
}
