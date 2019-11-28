package com.bandaddict.Controller;

import com.bandaddict.Annotation.CurrentUser;
import com.bandaddict.DTO.LyricsDTO;
import com.bandaddict.Entity.User;
import com.bandaddict.Service.LyricsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lyrics")
public class LyricsController {
    private LyricsService lyricsService;

    /**
     * Constructor for lyrics controller
     * @param lyricsService lyricsService
     */
    public LyricsController(final LyricsService lyricsService) {
        this.lyricsService = lyricsService;
    }

    @GetMapping("/get-lyrics")
    public List<LyricsDTO> getLyrics(@CurrentUser final User user) {
        return lyricsService.getLyrics(user);
    }

    @PostMapping("/save-lyrics")
    public ResponseEntity createLyrics(@CurrentUser final User user, @RequestBody final LyricsDTO lyricsDTO) {
        lyricsService.createLyrics(user, lyricsDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/browse")
    public List<LyricsDTO> browseLyrics(@CurrentUser final User user) {
        return lyricsService.browseLyrics(user);
    }

    @GetMapping("/search/{value}")
    public List<LyricsDTO> searchLyrics(@CurrentUser final User user, @PathVariable final String value) {
        return lyricsService.searchLyrics(value, user);
    }
}
