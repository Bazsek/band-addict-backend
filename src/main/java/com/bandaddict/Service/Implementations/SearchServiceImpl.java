package com.bandaddict.Service.Implementations;

import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.Band;
import com.bandaddict.Entity.MusicStyle;
import com.bandaddict.Entity.Post;
import com.bandaddict.Entity.User;
import com.bandaddict.Repository.BandRepository;
import com.bandaddict.Repository.MusicStyleRepository;
import com.bandaddict.Repository.PostRepository;
import com.bandaddict.Repository.UserRepository;
import com.bandaddict.Response.SearchResponse;
import com.bandaddict.Service.SearchService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Search service interface implementation
 */
@Service
public class SearchServiceImpl implements SearchService {
    private UserRepository userRepository;
    private ConversionService conversionService;
    private BandRepository bandRepository;
    private MusicStyleRepository musicStyleRepository;
    private PostRepository postRepository;

    public SearchServiceImpl(final UserRepository userRepository, final ConversionService conversionService, final BandRepository bandRepository,
                             final MusicStyleRepository musicStyleRepository, final PostRepository postRepository) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
        this.bandRepository = bandRepository;
        this.musicStyleRepository = musicStyleRepository;
        this.postRepository = postRepository;
    }


    @Override
    public List<SearchResponse> search(final String param) {
        final List<SearchResponse> searchResults = new ArrayList<>();
        searchResults.addAll(searchUser(param));
        searchResults.addAll(searchBand(param));
        searchResults.addAll(searchPost(param));

        return searchResults;
    }

    @Override
    public List<UserDTO> searchUsers(final String value) {
        final List<User> userListByName = userRepository.findByName(value);
        final List<User> userListByEmail = userRepository.findByEmail(value);
        final List<User> userListByNickName = userRepository.findByNickName(value);

        userListByEmail.forEach(user -> {
            if (!userListByName.contains(user)) userListByName.add(user);
        });

        userListByNickName.forEach(user -> {
            if (!userListByName.contains(user)) userListByName.add(user);
        });

        return userListByName.stream().map(searchUser ->
                conversionService.convert(searchUser, UserDTO.class)).collect(Collectors.toList());
    }

    private List<SearchResponse> searchUser(final String param) {
        final List<User> userListByName = userRepository.findByName(param);
        final List<User> userListByEmail = userRepository.findByEmail(param);
        final List<User> userListByNickName = userRepository.findByNickName(param);

        userListByEmail.forEach(user -> {
            if (!userListByName.contains(user)) userListByName.add(user);
        });

        userListByNickName.forEach(user -> {
            if (!userListByName.contains(user)) userListByName.add(user);
        });

        final List<SearchResponse> response = userListByName.stream().map(searchUser ->
                conversionService.convert(searchUser, SearchResponse.class)).collect(Collectors.toList());

        return response;
    }

    private List<SearchResponse> searchBand(final String param) {
        final List<Band> bandListByName = bandRepository.findByName(param);
        final List<Band> bandListByStyle = new ArrayList<>();
        final List<MusicStyle> styles = musicStyleRepository.findByName(param);
        final List<Band> allBand = bandRepository.findAll();

        styles.forEach(style -> {
            allBand.forEach(band -> {
                if (band.getStyles().contains(style)) {
                    bandListByStyle.add(band);
                }
            });
        });

        bandListByStyle.forEach(user -> {
            if (!bandListByName.contains(user)) bandListByName.add(user);
        });


        final List<SearchResponse> response = bandListByName.stream().map(searchUser ->
                conversionService.convert(searchUser, SearchResponse.class)).collect(Collectors.toList());

        return response;
    }

    private List<SearchResponse> searchPost(final String param) {
        final List<Post> postListByTitle = postRepository.findByTitle(param);
        final List<Post> postListByDescription = postRepository.findByDescription(param);

        postListByTitle.forEach(post -> {
            if (!postListByDescription.contains(post)) postListByDescription.add(post);
        });

        final List<SearchResponse> response = postListByTitle.stream().map(post ->
                conversionService.convert(post, SearchResponse.class)).collect(Collectors.toList());

        return response;
    }
}
