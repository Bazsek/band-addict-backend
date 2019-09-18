package com.bandaddict.Controller;

import com.bandaddict.Annotation.CurrentUser;
import com.bandaddict.DTO.PostDTO;
import com.bandaddict.DTO.SheetDTO;
import com.bandaddict.DTO.SongDTO;
import com.bandaddict.Entity.User;
import com.bandaddict.Service.PostService;
import com.bandaddict.Service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private PostService postService;
    private SongService songService;

    public DashboardController(final PostService postService, final SongService songService) {
        this.postService = postService;
        this.songService = songService;
    }

    @PostMapping("/create-post")
    public ResponseEntity createPost(@RequestBody final PostDTO postDTO, @CurrentUser final User user) {
        postService.createPost(postDTO, user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all-post")
    public List<PostDTO> getAllPost() {

        return postService.getAllPost();
    }

    @PostMapping("/create-song")
    public ResponseEntity createSong(@RequestBody final SongDTO songDTO, @CurrentUser final User user) {
        songService.createSong(songDTO, user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all-song")
    public List<SongDTO> getAllSong(@CurrentUser final User user) {

        return songService.getAllSong(user);
    }

    @PostMapping("/upload-sheet")
    public ResponseEntity uploadSheet(@RequestBody final SheetDTO sheetDTO, @CurrentUser final User user) {
        songService.uploadSheet(sheetDTO, user);

        return ResponseEntity.ok().build();
    }
}
