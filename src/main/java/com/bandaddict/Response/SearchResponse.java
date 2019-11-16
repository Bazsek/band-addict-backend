package com.bandaddict.Response;

import lombok.Data;

@Data
public class SearchResponse {

    private Long id;
    private String text;
    private String type;
    private String img;
    private String matchingParam;
}
