package com.bandaddict.Converter;

import com.bandaddict.Entity.Post;
import com.bandaddict.Response.SearchResponse;
import org.springframework.core.convert.converter.Converter;

public class PostToSearchResponseConverter implements Converter<Post, SearchResponse> {
    @Override
    public SearchResponse convert(final Post source) {
        final SearchResponse searchResponse = new SearchResponse();

        searchResponse.setId(source.getId());
        searchResponse.setText(source.getTitle());
        searchResponse.setImg(source.getPicture());
        searchResponse.setType("Post");
        searchResponse.setMatchingParam("");

        return searchResponse;
    }
}
