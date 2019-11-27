package com.bandaddict.Repository;

import com.bandaddict.Entity.Album;
import com.bandaddict.Entity.Band;
import com.bandaddict.Entity.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Crud repository for Song entity
 */
@Repository
public interface SongRepository extends CrudRepository<Song, Long> {

    /**
     * Get all song by band
     * @return list of songs
     */
    List<Song> findAllByBand(Band band);

    /**
     * Get all by band and album is null
     * @param band band
     * @param album album
     * @param param search param
     * @return list of song
     */
    List<Song> findAllByBandAndAlbumAndNameContaining(Band band, Album album, String param);

    /**
     * Get all song by album
     * @param album album
     * @return list of songs
     */
    List<Song> findAllByAlbum(Album album);

}
