package com.bandaddict.Controller;

import com.bandaddict.Annotation.CurrentUser;
import com.bandaddict.DTO.AlbumDTO;
import com.bandaddict.DTO.SheetDTO;
import com.bandaddict.DTO.SongDTO;
import com.bandaddict.Entity.User;
import com.bandaddict.Service.AlbumService;
import com.bandaddict.Service.SearchService;
import com.bandaddict.Service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {
    private SongService songService;
    private AlbumService albumService;
    private SearchService searchService;

    public SongController(final SongService songService, final AlbumService albumService, final SearchService searchService){
        this.songService = songService;
        this.albumService = albumService;
        this.searchService = searchService;
    }

    @GetMapping("/get-sheets")
    public List<SheetDTO> getLatestSheets() {
        return songService.getLatestSheets();
    }

    @GetMapping("/search-sheets/{text}")
    public List<SheetDTO> searchSheets(@PathVariable final String text) {
        return songService.searchSheet(text);
    }

    @GetMapping("/get-albums")
    public List<AlbumDTO> getAlbums(@CurrentUser final User user) {
        return albumService.getAlbums(user.getBand());
    }

    @PostMapping("/create-album")
    public ResponseEntity createAlbum(@CurrentUser final User user, @RequestBody final AlbumDTO albumDTO) {
        albumService.createAlbum(albumDTO, user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/{value}")
    public List<SongDTO> getUserById(@CurrentUser final User user, @PathVariable final String value) {
        return searchService.searchSongs(user.getBand(), value);
    }

    @PostMapping("/add/{songId}")
    public ResponseEntity addSongToAlbum(@PathVariable final Long songId, @RequestBody final Long albumId) {
        albumService.addSongToAlbum(songId, albumId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove")
    public ResponseEntity removeSongFromAlbum(@RequestBody final Long songId) {
        albumService.removeSongFromAlbum(songId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove-album")
    public ResponseEntity removeAlbumm(@RequestBody final Long albumId) {
        albumService.removeAlbum(albumId);

        return ResponseEntity.ok().build();
    }
}
