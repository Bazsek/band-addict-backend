package com.bandaddict.Controller;

import com.bandaddict.Annotation.CurrentUser;
import com.bandaddict.DTO.BandDTO;
import com.bandaddict.DTO.MusicStyleDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.User;
import com.bandaddict.Service.BandService;
import com.bandaddict.Service.MusicStyleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-band")
public class MyBandController {

    private BandService bandService;
    private MusicStyleService musicStyleService;

    public MyBandController(final BandService bandService, final MusicStyleService musicStyleService) {
        this.bandService = bandService;
        this.musicStyleService = musicStyleService;
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
}
