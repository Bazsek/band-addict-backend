package com.bandaddict.Service.Implementations;

import com.bandaddict.DTO.BandDTO;
import com.bandaddict.DTO.MusicStyleDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.Band;
import com.bandaddict.Entity.MusicStyle;
import com.bandaddict.Entity.User;
import com.bandaddict.Repository.BandRepository;
import com.bandaddict.Repository.MusicStyleRepository;
import com.bandaddict.Repository.UserRepository;
import com.bandaddict.Service.BandService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BandServiceImpl implements BandService {

    private ConversionService conversionService;
    private BandRepository bandRepository;
    private UserRepository userRepository;
    private MusicStyleRepository musicStyleRepository;

    public BandServiceImpl(final ConversionService conversionService, final BandRepository bandRepository,
                           final UserRepository userRepository, final MusicStyleRepository musicStyleRepository) {
        this.conversionService = conversionService;
        this.bandRepository = bandRepository;
        this.userRepository = userRepository;
        this.musicStyleRepository = musicStyleRepository;
    }

    @Override
    public void createBand(final BandDTO bandDTO, final User currentUser) {
        final Band band = conversionService.convert(bandDTO, Band.class);
        final User user = userRepository.findOneById(currentUser.getId());
        final List<MusicStyle> styleList = new ArrayList<>();

        if (bandDTO.getStyles() != null) {
            for (MusicStyleDTO musicStyleDTO : bandDTO.getStyles()) {
                styleList.add(musicStyleRepository.findOneById(musicStyleDTO.getId()));
            }
        }

        band.setStyles(styleList);
        user.setBand(bandRepository.save(band));
        userRepository.save(user);
    }

    @Override
    public void updateBand(final BandDTO bandDTO, final User currentUser) {
        final Band band = bandRepository.findOneById(currentUser.getBand().getId());
        final List<MusicStyle> styleList = new ArrayList<>();

        band.setName(bandDTO.getName());
        band.setShortDescription(bandDTO.getShortDescription());
        band.setCountry(bandDTO.getCountry());
        band.setFormedDate(bandDTO.getFormedDate());
        band.setBandLogo(bandDTO.getBandLogo());

        if (bandDTO.getStyles() != null) {
            for (MusicStyleDTO musicStyleDTO : bandDTO.getStyles()) {
                styleList.add(musicStyleRepository.findOneById(musicStyleDTO.getId()));
            }
        }

        band.setStyles(styleList);
        bandRepository.save(band);
    }

    @Override
    public List<UserDTO> getMembers(final User user){
        final Band band = user.getBand();
        final List<User> users = userRepository.findAllByBand(band);
        final List<UserDTO> members = new ArrayList<>();

        users.forEach(member -> members.add(conversionService.convert(member, UserDTO.class)));

        return members;
    }

    @Override
    public BandDTO getBandById(final Long id) {
        return conversionService.convert(bandRepository.findOneById(id), BandDTO.class);
    }
}
