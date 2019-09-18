package com.bandaddict.Service;

import com.bandaddict.DTO.MusicStyleDTO;

import java.util.List;

public interface MusicStyleService {

    /**
     * Find all music style
     *
     * @return list of musicStyleDTO
     */
    List<MusicStyleDTO> findAll();
}
