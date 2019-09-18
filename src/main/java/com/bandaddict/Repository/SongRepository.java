package com.bandaddict.Repository;

import com.bandaddict.Entity.Band;
import com.bandaddict.Entity.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {

    /**
     * Get all song by band
     * @return list of songs
     */
    List<Song> getAllByBand(Band band);
}
