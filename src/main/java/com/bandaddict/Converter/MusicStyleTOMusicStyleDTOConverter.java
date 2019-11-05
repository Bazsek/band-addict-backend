package com.bandaddict.Converter;

import com.bandaddict.DTO.MusicStyleDTO;
import com.bandaddict.Entity.MusicStyle;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter music style --> music style DTO
 */
public class MusicStyleTOMusicStyleDTOConverter implements Converter<MusicStyle, MusicStyleDTO> {

    @Override
    public MusicStyleDTO convert(final MusicStyle musicStyle) {
        final MusicStyleDTO musicStyleDTO = new MusicStyleDTO();

        musicStyleDTO.setId(musicStyle.getId());
        musicStyleDTO.setName(musicStyle.getName());
        musicStyleDTO.setDescription(musicStyle.getDescription());
        musicStyleDTO.setType(musicStyle.getType());

        return musicStyleDTO;
    }
}
