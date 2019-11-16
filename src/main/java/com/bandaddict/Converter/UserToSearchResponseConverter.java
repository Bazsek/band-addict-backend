package com.bandaddict.Converter;

import com.bandaddict.Entity.User;
import com.bandaddict.Response.SearchResponse;
import org.springframework.core.convert.converter.Converter;

/**
 * Convert user --> searchResponse
 */
public class UserToSearchResponseConverter implements Converter<User, SearchResponse> {

    @Override
    public SearchResponse convert(final User source) {
        final SearchResponse searchResponse = new SearchResponse();

        searchResponse.setId(source.getId());
        searchResponse.setType("User");
        searchResponse.setText(source.getName());
        searchResponse.setImg(source.getProfilePicture());

        return searchResponse;
    }
}
