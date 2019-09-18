package com.bandaddict.Repository;

import com.bandaddict.Entity.Band;
import com.bandaddict.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findOneById(Long id);

    User findOneByEmail(String email);

    List<User> findAllByBand(Band band);
}
