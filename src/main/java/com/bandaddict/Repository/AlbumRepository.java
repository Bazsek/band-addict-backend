package com.bandaddict.Repository;

import com.bandaddict.Entity.Album;
import com.bandaddict.Entity.Band;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Album repository
 */
public interface AlbumRepository extends CrudRepository<Album, Long> {

    /**
     * Find all album by band
     * @param band band
     * @return list of albums
     */
    List<Album> findAllByBand(Band band);
}
