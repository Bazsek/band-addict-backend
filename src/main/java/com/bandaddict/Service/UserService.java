package com.bandaddict.Service;

import com.bandaddict.Entity.User;
import com.bandaddict.DTO.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * the User service extends UserDetails service
 */
public interface UserService extends UserDetailsService {

    /**
     * Registration
     *
     * @param userDTO userDTO
     */
    User signUp(final UserDTO userDTO);

    /**
     * Find one user by id
     *
     * @param id id
     * @return user
     */
    User findOneById(final Long id);

    /**
     * Search user by email
     *
     * @param email email
     * @return UserDetails
     * @throws UsernameNotFoundException username not found exception
     */
    UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException;

    /**
     * Update user
     *
     * @param currentUser currentUser
     * @param userDTO updatedUser
     */
    void updateUser(final User currentUser, final UserDTO userDTO);

    /**
     * Activate user by id
     * @param userId id
     */
    void activateUser(final Long userId);
}
