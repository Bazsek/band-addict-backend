package com.bandaddict.Repository;

import com.bandaddict.Entity.Band;
import com.bandaddict.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findOneById(Long id);

    User findOneByEmail(String email);

    List<User> findAllByBand(Band band);

    @Query("SELECT U FROM User U WHERE LOWER(U.name) LIKE LOWER(concat(?1, '%'))")
    List<User> findByName(String param);

    @Query("SELECT U FROM User U WHERE LOWER(U.email) LIKE LOWER(concat(?1, '%'))")
    List<User> findByEmail(String param);

    @Query("SELECT U FROM User U WHERE LOWER(U.nickName) LIKE LOWER(concat(?1, '%'))")
    List<User> findByNickName(String param);
}
