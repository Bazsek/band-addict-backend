package com.bandaddict.Converter;

import com.bandaddict.DTO.MusicStyleDTO;
import com.bandaddict.Entity.MusicStyle;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter music style DTO --> music style
 */
public class MusicStyleDTOtoMusicStyleConverter implements Converter<MusicStyleDTO, MusicStyle> {

    @Override
    public MusicStyle convert(final MusicStyleDTO musicStyleDTO) {
        final MusicStyle musicStyle = new MusicStyle();

        musicStyle.setId(musicStyleDTO.getId());
        musicStyle.setName(musicStyleDTO.getName());
        musicStyle.setDescription(musicStyleDTO.getDescription());
        musicStyle.setType(musicStyleDTO.getType());

        return musicStyle;
    }
}
