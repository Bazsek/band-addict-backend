package com.bandaddict.Converter;

import com.bandaddict.Entity.Band;
import com.bandaddict.Response.SearchResponse;
import org.springframework.core.convert.converter.Converter;

public class BandToSearchResponseConverter implements Converter<Band, SearchResponse> {
    @Override
    public SearchResponse convert(Band source) {
        final SearchResponse searchResponse = new SearchResponse();

        searchResponse.setId(source.getId());
        searchResponse.setType("Band");
        searchResponse.setText(source.getName());
        searchResponse.setImg(source.getBandLogo());

        return searchResponse;
    }
}
