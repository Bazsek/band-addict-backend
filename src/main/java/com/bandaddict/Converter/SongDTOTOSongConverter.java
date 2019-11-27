package com.bandaddict.Converter;

import com.bandaddict.DTO.SongDTO;
import com.bandaddict.Entity.Album;
import com.bandaddict.Entity.Song;
import com.bandaddict.Enum.MusicType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

/**
 * SongDTO to song converter
 */
@RequiredArgsConstructor
public class SongDTOTOSongConverter implements Converter<SongDTO, Song> {
    private final ConversionService conversionService;

    @Override
    public Song convert(final SongDTO songDTO) {
        final Song song = new Song();

        song.setName(songDTO.getTitle());
        song.setAlbum(conversionService.convert(song.getAlbum(), Album.class));
        song.setDescription(songDTO.getDescription());
        song.setType(MusicType.getEnum(songDTO.getType()));
        song.setPublishingDate(songDTO.getPublishingDate());
        song.setYoutube(songDTO.getYoutube());

        return song;
    }
}
