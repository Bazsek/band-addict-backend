package com.bandaddict.Service;

import com.bandaddict.DTO.InstrumentDTO;

import java.util.List;

public interface InstrumentService {

    /**
     * Find all instrument
     *
     * @return list of instruments
     */
    List<InstrumentDTO> findAll();
}
