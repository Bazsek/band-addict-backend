package com.bandaddict.Service.Implementations;

import com.bandaddict.DTO.InstrumentDTO;
import com.bandaddict.Entity.Instrument;
import com.bandaddict.Entity.User;
import com.bandaddict.Enum.Status;
import com.bandaddict.Exception.UserAlreadyExistException;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Repository.InstrumentRepository;
import com.bandaddict.Repository.UserRepository;
import com.bandaddict.Service.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * the User service implementation
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ConversionService conversionService;
    private InstrumentRepository instrumentRepository;

    /**
     * Constructor
     *
     * @param userRepository the userRepository bean
     * @param conversionService the conversionService bean
     */
    public UserServiceImpl(final UserRepository userRepository, final ConversionService conversionService,
                           final InstrumentRepository instrumentRepository) {
        this.conversionService = conversionService;
        this.userRepository = userRepository;
        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public User signUp(final UserDTO userDTO) {
        if (Optional.ofNullable(userRepository.findOneByEmail(userDTO.getEmail())).isPresent()) {
            throw new UserAlreadyExistException("User already exists!");
        }

        final User user = conversionService.convert(userDTO, User.class);

        return userRepository.save(user);
    }

    @Override
    public User findOneById(final Long id) {
        return userRepository.findOneById(id);

    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return Optional.ofNullable(userRepository.findOneByEmail(email)).orElseThrow(()
                -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public void updateUser(final User currentUser, final UserDTO userDTO) {
        final User user = userRepository.findOneById(currentUser.getId());
        final List<Instrument> instrumentList = new ArrayList<>();

        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setNickName(userDTO.getNickName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setDescription(userDTO.getDescription());

        if (userDTO.getInstruments() != null) {
            for (InstrumentDTO instrumentDTO : userDTO.getInstruments()) {
                instrumentList.add(instrumentRepository.findOneById(instrumentDTO.getId()));
            }
        }

        user.setInstruments(instrumentList);

        userRepository.save(user);
    }


    @Override
    public void activateUser(final Long userId) {
        final User user = userRepository.findOneById(userId);

        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
    }
}
