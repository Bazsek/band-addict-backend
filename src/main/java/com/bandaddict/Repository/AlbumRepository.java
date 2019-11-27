package com.bandaddict.Repository;

import com.bandaddict.Entity.Album;
import org.springframework.data.repository.CrudRepository;

/**
 * Album repository
 */
public interface AlbumRepository extends CrudRepository<Album, Long> {
}
