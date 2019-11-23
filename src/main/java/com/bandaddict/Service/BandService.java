package com.bandaddict.Service;

import com.bandaddict.DTO.BandDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.User;

import java.util.List;

public interface BandService {

    /**Create band
     *
     * @param bandDTO bandDTO
     * @param currentUser currentUser
     */
    void createBand(final BandDTO bandDTO, final User currentUser);

    /**
     * Update band
     *
     * @param bandDTO bandDTO
     * @param currentUser currentUser
     */
    void updateBand(final BandDTO bandDTO, final User currentUser);

    /**
     * Get members of the current band
     *
     * @param user current user
     * @return list of members
     */
    List<UserDTO> getMembers(final User user);

    /**
     * Get band by id
     * @param id id
     * @return BandDTO
     */
    BandDTO getBandById(final Long id);

    /**
     * Remove user from band
     * @param id user id
     */
    void removeFromBand(final Long id);

    /**
     * Add user to band
     * @param user current user
     * @param id id
     */
    void addToBand(final User user, final Long id);

    /**
     * Change member role
     * @param id member id
     * @param role role
     */
    void changeRole(final Long id, final String role);
}
