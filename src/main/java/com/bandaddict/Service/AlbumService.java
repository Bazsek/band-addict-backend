package com.bandaddict.Service;

import com.bandaddict.DTO.AlbumDTO;
import com.bandaddict.Entity.Band;
import com.bandaddict.Entity.User;

import java.util.List;

/**
 * Album service interface
 */
public interface AlbumService {

    /**
     * Get all albums by band
     * @param band band
     * @return list of albums
     */
    List<AlbumDTO> getAlbums(final Band band);

    /**
     * Create album
     * @param albumDTO albumDTO
     * @param user current user
     */
    void createAlbum(final AlbumDTO albumDTO, final User user);

    /**
     * Add song to album
     * @param songId song id
     * @param albumId album id
     */
    void addSongToAlbum(final Long songId, final Long albumId);

    /**
     * Remove song from album
     * @param songId songId
     */
    void removeSongFromAlbum(final Long songId);

    /**
     * Remove album
     * @param albumId album id
     */
    void removeAlbum(final Long albumId);
}
