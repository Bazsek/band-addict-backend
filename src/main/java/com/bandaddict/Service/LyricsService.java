package com.bandaddict.Service;

import com.bandaddict.DTO.LyricsDTO;
import com.bandaddict.Entity.User;

import java.util.List;

/**
 * Lyrics service interface
 */
public interface LyricsService {

    /**
     * Get lyrics by user
     * @param user user
     * @return list of lyrics
     */
    List<LyricsDTO> getLyrics(final User user);

    /**
     * Get all lyrics
     * @return list of lyrics
     */
    List<LyricsDTO> getAllLyrics();

    /**
     * Get all lyrics with other bands
     * @param user user
     * @return list of lyrics
     */
    List<LyricsDTO> browseLyrics(final User user);

    /**
     * Search for lyrics
     * @param value value
     * @param user user
     * @return list of lyrics
     */
    List<LyricsDTO> searchLyrics(final String value, final User user);

    /**
     * Create lyrics
     * @param user user
     * @param lyricsDTO lyricsDTO
     */
    void createLyrics(final User user, final LyricsDTO lyricsDTO);
}
