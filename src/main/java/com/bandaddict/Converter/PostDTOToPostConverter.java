package com.bandaddict.Converter;

import com.bandaddict.DTO.PostDTO;
import com.bandaddict.Entity.Post;
import com.bandaddict.Entity.User;
import com.bandaddict.Enum.PostType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter PostDTO --> Post
 */
public class PostDTOToPostConverter implements Converter<PostDTO, Post> {

    private ConversionService conversionService;

    public PostDTOToPostConverter(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Post convert(final PostDTO postDTO) {
        final Post post = new Post();

        post.setCreatedBy(conversionService.convert(postDTO.getCreatedBy(), User.class));
        post.setCreatedAt(postDTO.getCreatedAt());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setType(PostType.getEnum(postDTO.getPostType()));
        post.setPicture(postDTO.getPicture());

        return post;
    }
}
