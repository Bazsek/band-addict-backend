package com.bandaddict.Service.Implementations;

import com.bandaddict.Entity.Band;
import com.bandaddict.Entity.User;
import com.bandaddict.Repository.BandRepository;
import com.bandaddict.Repository.UserRepository;
import com.bandaddict.Service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    private static final String PROFILE_PICTURE = "profile-picture";
    private static final String BASE_PATH = "/assets";
    private static final String USER = "/users/";
    private static final String BAND = "/bands/";

    final UserRepository userRepository;
    final BandRepository bandRepository;

    public FileServiceImpl(final UserRepository userRepository, final BandRepository bandRepository) {
        this.userRepository = userRepository;
        this.bandRepository = bandRepository;
    }

    @Override
    public String getPath(final User currentUser, final String imgName, final String type) {
        final User user = userRepository.findOneById(currentUser.getId());
        //final Band band = bandRepository.findOneById(currentUser.getBand().getId());

        if (PROFILE_PICTURE.equals(type)) {
            final String path = BASE_PATH + USER + currentUser.getId() + '_' +  imgName;

            user.setProfilePicture(path);
            userRepository.save(user);
        } else {
            final String path = BASE_PATH + BAND + currentUser.getBand().getId() + '_' +  imgName;

//            band.setBandLogo(path);
//            bandRepository.save(band);

        }

        return PROFILE_PICTURE.equals(type) ? user.getProfilePicture() : user.getProfilePicture();
    }
}
