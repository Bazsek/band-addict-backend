package com.bandaddict.Controller;

import com.bandaddict.Annotation.CurrentUser;
import com.bandaddict.DTO.BandDTO;
import com.bandaddict.DTO.MusicStyleDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.User;
import com.bandaddict.Response.UploadResponse;
import com.bandaddict.Service.BandService;
import com.bandaddict.Service.FileService;
import com.bandaddict.Service.MusicStyleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-band")
public class MyBandController {

    private BandService bandService;
    private MusicStyleService musicStyleService;
    private FileService fileService;

    public MyBandController(final BandService bandService, final MusicStyleService musicStyleService, final FileService fileService) {
        this.bandService = bandService;
        this.musicStyleService = musicStyleService;
        this.fileService = fileService;
    }

    @PostMapping("/create")
    public ResponseEntity createBand(@RequestBody final BandDTO bandDTO, @CurrentUser final User user) {
        bandService.createBand(bandDTO, user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity updateBand(@RequestBody final BandDTO bandDTO, @CurrentUser final User user) {
        bandService.updateBand(bandDTO, user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/styles")
    public List<MusicStyleDTO> getAllMusicStyle() {
        return musicStyleService.findAll();
    }

    @GetMapping("/get-members")
    public List<UserDTO> getMembers(@CurrentUser final User user) {
        return bandService.getMembers(user);
    }

    @PostMapping("/logo/{type}")
    public UploadResponse uploadPhoto(@CurrentUser final User currentUser, @RequestBody final UploadResponse uploadResponse, @PathVariable final String type) {
        return fileService.setPath(currentUser, uploadResponse, type);
    }

}
