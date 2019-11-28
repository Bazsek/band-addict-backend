package com.bandaddict.Converter;

import com.bandaddict.DTO.LyricsDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.Lyrics;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

/**
 * Lyrics to lyricsDTO converter
 */
@RequiredArgsConstructor
public class LyricsToLyricsDTOConverter implements Converter<Lyrics, LyricsDTO> {
    private final ConversionService conversionService;

    @Override
    public final LyricsDTO convert(final Lyrics source) {
        final LyricsDTO lyricsDTO = new LyricsDTO();

        lyricsDTO.setId(source.getId());
        lyricsDTO.setCreatedAt(source.getCreatedAt());
        lyricsDTO.setCreatedBy(conversionService.convert(source.getCreatedBy(), UserDTO.class));
        lyricsDTO.setText(source.getText());
        lyricsDTO.setTitle(source.getTitle());

        return lyricsDTO;
    }
}
