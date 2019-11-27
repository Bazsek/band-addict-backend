package com.bandaddict.Converter;

import com.bandaddict.DTO.AlbumDTO;
import com.bandaddict.DTO.SongDTO;
import com.bandaddict.Entity.Album;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

/**
 * Album to albumDTO converter
 */
@RequiredArgsConstructor
public class AlbumToAlbumDTOConverter implements Converter<Album, AlbumDTO> {
    private final ConversionService conversionService;

    @Override
    public final AlbumDTO convert(final Album source) {
        final AlbumDTO albumDTO = new AlbumDTO();

        albumDTO.setId(source.getId());
        albumDTO.setCoverPhoto(source.getCoverPhoto());
        albumDTO.setDescription(source.getDescription());
        albumDTO.setName(source.getName());
        albumDTO.setCreatedAt(source.getCreatedAt());
        albumDTO.setSongs(source.getSongs().stream().map(song -> conversionService.convert(song, SongDTO.class)).collect(Collectors.toList()));

        return albumDTO;
    }
}
