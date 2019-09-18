package com.bandaddict.Repository;

import com.bandaddict.Entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    /**
     * Find all post and order by desc
     *
     * @return list of posts
     */
    List<Post> findAllByOrderByCreatedAtDesc();
}
