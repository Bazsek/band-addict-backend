package com.bandaddict.Converter;

import com.bandaddict.DTO.BandDTO;
import com.bandaddict.Entity.Band;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

public class BandDTOToBandConverter implements Converter<BandDTO, Band> {

    private final ConversionService conversionService;

    public BandDTOToBandConverter(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Band convert(final BandDTO bandDTO) {
        final Band band = new Band();

        band.setName(bandDTO.getName());
        band.setShortDescription(bandDTO.getShortDescription());
        band.setCountry(bandDTO.getCountry());
        band.setFormedDate(bandDTO.getFormedDate());
        band.setBandLogo(bandDTO.getBandLogo());

        return band;
    }
}
