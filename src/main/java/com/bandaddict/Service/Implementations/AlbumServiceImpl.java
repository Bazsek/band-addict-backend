package com.bandaddict.Service.Implementations;

import com.bandaddict.DTO.AlbumDTO;
import com.bandaddict.Entity.Album;
import com.bandaddict.Entity.Band;
import com.bandaddict.Entity.Song;
import com.bandaddict.Entity.User;
import com.bandaddict.Exception.NotFoundException;
import com.bandaddict.Repository.AlbumRepository;
import com.bandaddict.Repository.SongRepository;
import com.bandaddict.Service.AlbumService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of album service interface
 */
@Service
public class AlbumServiceImpl implements AlbumService {
    private AlbumRepository albumRepository;
    private ConversionService conversionService;
    private SongRepository songRepository;

    /**
     * Constructor
     * @param albumRepository albumRepository
     * @param conversionService conversionService
     * @param songRepository songRepository
     */
    public AlbumServiceImpl(final AlbumRepository albumRepository, final ConversionService conversionService, final SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.conversionService = conversionService;
        this.songRepository = songRepository;
    }

    @Override
    public List<AlbumDTO> getAlbums(final Band band) {
        final List<AlbumDTO> albumList = new ArrayList<>();

        albumRepository.findAll().forEach(album -> albumList.add(conversionService.convert(album, AlbumDTO.class)));

        return albumList;
    }

    @Override
    public void createAlbum(final AlbumDTO albumDTO, final User user) {
        final Album album = new Album();

        album.setName(albumDTO.getName());
        album.setCreatedAt(albumDTO.getCreatedAt());
        album.setDescription(albumDTO.getDescription());
        album.setCoverPhoto(albumDTO.getCoverPhoto());
        album.setBand(user.getBand());

        albumRepository.save(album);
    }

    @Override
    public void addSongToAlbum(final Long songId, final Long albumId) {
        final Album album = albumRepository.findById(albumId).orElseThrow(() -> new NotFoundException("Album not found!"));
        final Song song = songRepository.findById(songId).orElseThrow(() -> new NotFoundException("Song not found!"));

        song.setAlbum(album);
        songRepository.save(song);
    }

    @Override
    public void removeSongFromAlbum(final Long songId) {
        final Song song = songRepository.findById(songId).orElseThrow(() -> new NotFoundException("Song not found!"));

        song.setAlbum(null);
        songRepository.save(song);
    }

    @Override
    public void removeAlbum(final Long albumId) {
        final Album album = albumRepository.findById(albumId).orElseThrow(() -> new NotFoundException("Album not found!"));
        final List<Song> songList = songRepository.findAllByAlbum(album);

        songList.forEach(song -> {
            song.setAlbum(null);
            songRepository.save(song);
        });

        albumRepository.delete(album);
    }
}
