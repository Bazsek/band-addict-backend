package com.bandaddict.Service;

import com.bandaddict.DTO.SongDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.Band;
import com.bandaddict.Response.SearchResponse;

import java.util.List;

/**
 * Search service interface
 */
public interface SearchService {

    /**
     * Searching in database by param
     * @param param string
     * @return list of search response objects
     */
    List<SearchResponse> search(final String param);

    /**
     * Search users
     * @param value value
     * @return list of users
     */
    List<UserDTO> searchUsers(final String value);

    /**
     * Search songs by name
     * @param value string
     * @param band band
     * @return list of songs
     */
    List<SongDTO> searchSongs(final Band band, final String value);

}
