package com.bandaddict.Converter;

import com.bandaddict.DTO.SongDTO;
import com.bandaddict.Entity.Song;
import org.springframework.core.convert.converter.Converter;

public class SongTOSongDTOConverter implements Converter<Song, SongDTO> {

    @Override
    public SongDTO convert(final Song song) {
        final SongDTO songDTO = new SongDTO();

        songDTO.setTitle(song.getName());
        songDTO.setAlbum(song.getAlbum());
        songDTO.setType(song.getType().toString());
        songDTO.setDescription(song.getDescription());
        songDTO.setPublishingDate(song.getPublishingDate());
        songDTO.setYoutube(song.getYoutube());

        return songDTO;
    }
}
