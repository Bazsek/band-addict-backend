package com.bandaddict.Converter;

import com.bandaddict.DTO.LyricsDTO;
import com.bandaddict.Entity.Lyrics;
import org.springframework.core.convert.converter.Converter;

/**
 * LyricsDTO to lyrics converter
 */
public class LyricsDTOToLyricsConverter implements Converter<LyricsDTO, Lyrics> {

    @Override
    public Lyrics convert(final LyricsDTO source) {
        final Lyrics lyrics = new Lyrics();

        lyrics.setId(source.getId());
        lyrics.setCreatedAt(source.getCreatedAt());
        lyrics.setTitle(source.getTitle());
        lyrics.setText(source.getText());
        lyrics.setSong(source.getSong());

        return lyrics;
    }
}
