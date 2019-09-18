package com.bandaddict.Service;

import com.bandaddict.DTO.SheetDTO;
import com.bandaddict.DTO.SongDTO;
import com.bandaddict.Entity.User;

import java.util.List;

public interface SongService {

    /**
     * create song
     *
     * @param songDTO songDTO
     * @param user current user
     */
    void createSong(final SongDTO songDTO, final User user);

    /**
     * Get all song by current user's band
     * @param user current user
     * @return list of songs
     */
    List<SongDTO> getAllSong(final User user);

    /**
     * Get latest 10 sheets
     * @return list of sheets
     */
    List<SheetDTO> getLatestSheets();

    /**
     * Search for sheets
     * @param text text
     * @return list of sheets
     */
    List<SheetDTO> searchSheet(final String text);

    /**
     * Upload sheet
     * @param sheetDTO sheetDTO
     * @param user current user
     */
    void uploadSheet(final SheetDTO sheetDTO, final User user);
}
