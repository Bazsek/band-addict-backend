package com.bandaddict.Converter;

import com.bandaddict.DTO.SongDTO;
import com.bandaddict.Entity.Song;
import com.bandaddict.Enum.MusicType;
import org.springframework.core.convert.converter.Converter;

public class SongDTOTOSongConverter implements Converter<SongDTO, Song> {

    @Override
    public Song convert(final SongDTO songDTO) {
        final Song song = new Song();

        song.setName(songDTO.getTitle());
        song.setAlbum(songDTO.getAlbum());
        song.setDescription(songDTO.getDescription());
        song.setType(MusicType.getEnum(songDTO.getType()));
        song.setPublishingDate(songDTO.getPublishingDate());
        song.setYoutube(songDTO.getYoutube());

        return song;
    }
}
