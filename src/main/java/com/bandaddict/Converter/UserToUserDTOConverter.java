package com.bandaddict.Converter;

import com.bandaddict.DTO.InstrumentDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.User;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

/**
 * Converter user -> userDTO
 */
public class UserToUserDTOConverter implements Converter<User, UserDTO> {

    private ConversionService conversionService;

    public UserToUserDTOConverter(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public UserDTO convert(final User user) {
        final UserDTO userDTO = new UserDTO();

        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setBand(user.getBand());
        userDTO.setBand(user.getBand());
        userDTO.setDescription(user.getDescription());
        userDTO.setRole(user.getRole());
        userDTO.setProfilePicture(user.getProfilePicture());
        userDTO.setNickName(user.getNickName());
        userDTO.setPhoneNumber(user.getPhoneNumber());

        if (user.getInstruments() != null) {
            userDTO.setInstruments(user.getInstruments().stream().
                    map(inst -> conversionService.convert(inst, InstrumentDTO.class)).collect(Collectors.toList()));
        }

        return userDTO;
    }
}
