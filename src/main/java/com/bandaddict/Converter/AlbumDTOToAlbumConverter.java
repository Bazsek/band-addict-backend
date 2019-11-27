package com.bandaddict.Converter;

import com.bandaddict.DTO.AlbumDTO;
import com.bandaddict.Entity.Album;
import com.bandaddict.Entity.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

/**
 * AlbumDTO to Album converter
 */
@RequiredArgsConstructor
public class AlbumDTOToAlbumConverter implements Converter<AlbumDTO, Album> {
    private final ConversionService conversionService;

    @Override
    public final Album convert(final AlbumDTO source) {
        final Album album = new Album();

        album.setId(source.getId());
        album.setCoverPhoto(source.getCoverPhoto());
        album.setDescription(source.getDescription());
        album.setName(source.getName());
        album.setCreatedAt(source.getCreatedAt());
        album.setSongs(source.getSongs().stream().map(songDTO -> conversionService.convert(songDTO, Song.class)).collect(Collectors.toList()));

        return album;
    }
}
