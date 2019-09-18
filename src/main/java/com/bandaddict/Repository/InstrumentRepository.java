package com.bandaddict.Repository;

import com.bandaddict.Entity.Instrument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentRepository extends CrudRepository<Instrument, Long> {

    /**
     * Find all instruments
     * @return list of instruments
     */
    List<Instrument> findAll();

    /**
     * Find one instrument by id
     * @param id instrument id
     * @return instrument
     */
    Instrument findOneById(Long id);
}
