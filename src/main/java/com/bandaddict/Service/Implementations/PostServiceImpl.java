package com.bandaddict.Service.Implementations;

import com.bandaddict.DTO.PostDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.Post;
import com.bandaddict.Entity.User;
import com.bandaddict.Exception.ConversionException;
import com.bandaddict.Repository.PostRepository;
import com.bandaddict.Service.PostService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Post service implementation
 */
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ConversionService conversionService;

    /**
     * Constructor
     *
     * @param postRepository the postRepository bean
     */
    public PostServiceImpl(final PostRepository postRepository, final ConversionService conversionService) {
        this.postRepository = postRepository;
        this.conversionService = conversionService;
    }

    @Override
    public void createPost(final PostDTO postDTO, final User user) {
        final Post post = Optional.ofNullable(conversionService.convert(postDTO, Post.class)).
                orElseThrow(() -> new ConversionException("Conversion failed from postDTO to Post!"));

        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public List<PostDTO> getAllPost() {

        return postRepository.findAllByOrderByCreatedAtDesc().stream().
                map(post -> conversionService.convert(post, PostDTO.class)).collect(Collectors.toList());
    }
}
