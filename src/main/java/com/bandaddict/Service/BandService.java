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
}
