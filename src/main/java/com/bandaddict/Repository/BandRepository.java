package com.bandaddict.Repository;

import com.bandaddict.Entity.Band;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * the Band repository extends crud repository
 */
@Repository
public interface BandRepository extends CrudRepository<Band, Long> {

    /**
     * Find one band by id
     *
     * @param id id
     * @return band
     */
    Band findOneById(final Long id);

    /**
     * Find band by name matching
     * @param param param
     * @return list of band entites
     */
    @Query("SELECT B FROM Band B WHERE LOWER(B.name) LIKE LOWER(concat(?1, '%'))")
    List<Band> findByName(String param);

    /**
     * Find all band
     * @return list of band
     */
    List<Band> findAll();
}
