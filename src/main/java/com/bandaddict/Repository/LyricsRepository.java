package com.bandaddict.Repository;

import com.bandaddict.Entity.Lyrics;
import com.bandaddict.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Crud repository for Lyrics entity
 */
public interface LyricsRepository extends CrudRepository<Lyrics, Long> {

    /**
     * Find all by user
     * @param createdBy user
     * @return list of lyrics
     */
    List<Lyrics> findAllByCreatedBy(User createdBy);

    /**
     * Find all lyrics where is song null and name containing value
     * @param value value
     * @param user user
     * @return list of lyrics
     */
    List<Lyrics> findAllBySongIsNullAndCreatedByNotAndTitleContaining(User user, String value);

    /**
     * Find all lyrics where is song null and name containing value
     * @param value value
     * @param users band members
     * @return list of lyrics
     */
    List<Lyrics> findAllBySongIsNullAndCreatedByNotInAndTitleContaining(List<User> users, String value);

    /**
     * Find all lyrics from other bands
     * @param users users
     * @return list of lyrics
     */
    List<Lyrics> findAllByCreatedByNotIn(List<User> users);

    /**
     * Find all lyrics from other users
     * @param user user
     * @return list of lyrics
     */
    List<Lyrics> findAllByCreatedByNot(User user);
}
