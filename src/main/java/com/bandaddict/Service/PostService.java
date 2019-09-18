package com.bandaddict.Service;

import com.bandaddict.DTO.PostDTO;
import com.bandaddict.Entity.User;

import java.util.List;

/**
 * Post service interface
 */
public interface PostService {

    /**
     * Create new post
     *
     * @param postDTO postDTO
     */
    void createPost(final PostDTO postDTO, final User user);

    /**
     * Get all post
     *
     * @return list of posts
     */
    List<PostDTO> getAllPost();
}
