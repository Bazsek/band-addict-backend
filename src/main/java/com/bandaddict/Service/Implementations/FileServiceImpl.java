package com.bandaddict.Service.Implementations;

import com.bandaddict.Entity.Band;
import com.bandaddict.Entity.User;
import com.bandaddict.Repository.BandRepository;
import com.bandaddict.Repository.UserRepository;
import com.bandaddict.Response.UploadResponse;
import com.bandaddict.Service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    private static final String PHOTO = "photo";
    private static final String LOGO = "logo";
    private static final String FILE = "file";
    private static final String OTHER = "other";

    private UserRepository userRepository;
    private BandRepository bandRepository;

    public FileServiceImpl(final UserRepository userRepository, final BandRepository bandRepository) {
        this.userRepository = userRepository;
        this.bandRepository = bandRepository;
    }

    @Override
    public UploadResponse setPath(final User currentUser, final UploadResponse uploadResponse, final String type) {
        final User user = userRepository.findOneById(currentUser.getId());
        final Band band = bandRepository.findOneById(currentUser.getBand().getId());

        switch (type) {
            case PHOTO:
                user.setProfilePicture(uploadResponse.getPath());
                userRepository.save(user);
                break;
            case LOGO:
                band.setBandLogo(uploadResponse.getPath());
                bandRepository.save(band);
                break;
            case FILE:
                break;
            default:
        }

        return uploadResponse;
    }

}
