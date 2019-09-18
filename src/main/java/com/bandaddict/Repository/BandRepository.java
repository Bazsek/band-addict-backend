package com.bandaddict.Repository;

import com.bandaddict.Entity.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
}
