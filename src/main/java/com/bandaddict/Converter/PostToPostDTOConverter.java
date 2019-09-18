package com.bandaddict.Converter;


import com.bandaddict.DTO.PostDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.Post;
import com.bandaddict.Enum.PostType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

public class PostToPostDTOConverter implements Converter<Post, PostDTO> {

    private ConversionService conversionService;

    public PostToPostDTOConverter(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public PostDTO convert(final Post post) {
        final PostDTO postDTO = new PostDTO();

        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setPostType(PostType.ADVERTISEMENT.getValue());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setCreatedBy(conversionService.convert(post.getCreatedBy(), UserDTO.class));
        postDTO.setPicture(post.getPicture());

        return postDTO;
    }
}
