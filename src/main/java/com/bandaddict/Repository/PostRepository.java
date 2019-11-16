package com.bandaddict.Repository;

import com.bandaddict.Entity.Band;
import com.bandaddict.Entity.Post;
import org.springframework.data.jpa.repository.Query;
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

    /**
     * Find post by title matching
     * @param param param
     * @return list of post
     */
    @Query("SELECT P FROM Post P WHERE LOWER(P.title) LIKE LOWER(concat(?1, '%'))")
    List<Post> findByTitle(String param);

    /**
     * Find post by description matching
     * @param param param
     * @return list of post entities
     */
    @Query("SELECT P FROM Post P WHERE LOWER(P.description) LIKE LOWER(concat(?1, '%'))")
    List<Post> findByDescription(String param);

    /**
     * Find post by id
     * @param id id
     * @return post
     */
    Post findOneById(Long id);
}
