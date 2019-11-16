package com.bandaddict.Service;

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
}
