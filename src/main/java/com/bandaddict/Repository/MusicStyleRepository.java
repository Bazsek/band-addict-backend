package com.bandaddict.Repository;

import com.bandaddict.Entity.MusicStyle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicStyleRepository extends CrudRepository<MusicStyle, Long> {

    /**
     * Find all music style
     *
     * @return list of music styles
     */
    List<MusicStyle> findAll();

    /**
     * Find one music style by id
     * @param id id
     * @return musicStyle
     */
    MusicStyle findOneById(Long id);

    /**
     * Find music style by name matching
     * @param param param
     * @return list of musicStyle entities
     */
    @Query("SELECT M FROM MusicStyle M WHERE LOWER(M.name) LIKE LOWER(concat(?1, '%'))")
    List<MusicStyle> findByName(String param);
}
