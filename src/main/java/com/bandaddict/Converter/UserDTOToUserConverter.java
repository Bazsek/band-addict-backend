package com.bandaddict.Converter;

import com.bandaddict.Entity.User;
import com.bandaddict.Enum.Role;
import com.bandaddict.Enum.Status;
import com.bandaddict.DTO.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Converter userDTO -> user
 */
public class UserDTOToUserConverter implements Converter<UserDTO, User> {

    @Override
    public User convert(final UserDTO userDTO){
        final User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setBand(userDTO.getBand());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setStatus(Status.INACTIVE);
        user.setRole(Role.USER);
        user.setProfilePicture(userDTO.getProfilePicture());
        user.setNickName(userDTO.getNickName());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        return user;
    }
}
