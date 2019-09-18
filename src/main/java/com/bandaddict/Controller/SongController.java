package com.bandaddict.Controller;

import com.bandaddict.DTO.SheetDTO;
import com.bandaddict.Service.SongService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {
    private SongService songService;

    public SongController(final SongService songService){
        this.songService = songService;
    }

    @GetMapping("/get-sheets")
    public List<SheetDTO> getLatestSheets() {
        return songService.getLatestSheets();
    }

    @GetMapping("/search-sheets/{text}")
    public List<SheetDTO> searchSheets(@PathVariable final String text) {
        return songService.searchSheet(text);
    }
}
