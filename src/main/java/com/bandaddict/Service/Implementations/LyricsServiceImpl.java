package com.bandaddict.Service.Implementations;

import com.bandaddict.DTO.LyricsDTO;
import com.bandaddict.Entity.Lyrics;
import com.bandaddict.Entity.User;
import com.bandaddict.Repository.LyricsRepository;
import com.bandaddict.Service.LyricsService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of lyrics service interface
 */
@Service
public class LyricsServiceImpl implements LyricsService {
    private ConversionService conversionService;
    private LyricsRepository lyricsRepository;

    public LyricsServiceImpl(final ConversionService conversionService, final LyricsRepository lyricsRepository){
        this.conversionService = conversionService;
        this.lyricsRepository = lyricsRepository;
    }

    @Override
    public List<LyricsDTO> getLyrics(final User user) {
        final List<LyricsDTO> lyricsDTOS = new ArrayList<>();

        if (user.getBand() != null) {
            user.getBand().getBandMembers().forEach(member ->
                    lyricsRepository.findAllByCreatedBy(member).forEach(lyrics -> lyricsDTOS.add(conversionService.convert(lyrics, LyricsDTO.class))));
        } else {
            lyricsRepository.findAll().forEach(lyrics -> lyricsDTOS.add(conversionService.convert(lyrics, LyricsDTO.class)));
        }

        return lyricsDTOS;
    }

    @Override
    public List<LyricsDTO> getAllLyrics() {
        final List<LyricsDTO> list = new ArrayList<>();

        lyricsRepository.findAll().forEach(lyrics -> list.add(conversionService.convert(lyrics, LyricsDTO.class)));

        return list;
    }

    @Override
    public List<LyricsDTO> browseLyrics(final User user) {
        final List<LyricsDTO> lyricsDTOS = new ArrayList<>();

        if (user.getBand() != null) {
            lyricsRepository.findAllByCreatedByNotIn(user.getBand().getBandMembers()).forEach(lyrics -> lyricsDTOS.add(conversionService.convert(lyrics, LyricsDTO.class)));
        } else {
            lyricsRepository.findAllByCreatedByNot(user).forEach(lyrics -> lyricsDTOS.add(conversionService.convert(lyrics, LyricsDTO.class)));
        }

        return lyricsDTOS;
    }

    @Override
    public List<LyricsDTO> searchLyrics(final String value, final User user) {
        final List<LyricsDTO> resultList = new ArrayList<>();

        if (user.getBand() != null) {
            lyricsRepository.findAllBySongIsNullAndCreatedByNotInAndTitleContaining(user.getBand().getBandMembers(), value).forEach(lyrics -> resultList.add(conversionService.convert(lyrics, LyricsDTO.class)));
        } else {
            lyricsRepository.findAllBySongIsNullAndCreatedByNotAndTitleContaining(user, value).forEach(lyrics -> resultList.add(conversionService.convert(lyrics, LyricsDTO.class)));
        }

        return resultList;
    }

    @Override
    public void createLyrics(final User user, final LyricsDTO lyricsDTO) {
        final Lyrics lyrics = new Lyrics();

        lyrics.setTitle(lyricsDTO.getTitle());
        lyrics.setText(lyricsDTO.getText());
        lyrics.setCreatedAt(lyricsDTO.getCreatedAt());
        lyrics.setCreatedBy(user);

        lyricsRepository.save(lyrics);
    }
}
