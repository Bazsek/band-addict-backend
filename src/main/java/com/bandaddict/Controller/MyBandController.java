package com.bandaddict.Controller;

import com.bandaddict.Annotation.CurrentUser;
import com.bandaddict.DTO.BandDTO;
import com.bandaddict.DTO.EventDTO;
import com.bandaddict.DTO.MusicStyleDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.User;
import com.bandaddict.Response.UploadResponse;
import com.bandaddict.Service.BandService;
import com.bandaddict.Service.EventService;
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
    private EventService eventService;

    public MyBandController(final BandService bandService, final MusicStyleService musicStyleService, final FileService fileService,
                            final EventService eventService) {
        this.bandService = bandService;
        this.musicStyleService = musicStyleService;
        this.fileService = fileService;
        this.eventService = eventService;
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

    @PostMapping("/remove")
    public ResponseEntity removeMember(@RequestBody final Long id) {
        bandService.removeFromBand(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity addMember(@CurrentUser User user, @RequestBody final Long id) {
        bandService.addToBand(user, id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/role/{id}")
    public ResponseEntity changeRole(@RequestBody final String role, @PathVariable final Long id) {
        bandService.changeRole(id, role);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/event/add")
    public ResponseEntity addEvent(@CurrentUser final User user, @RequestBody final EventDTO eventDTO) {
        eventService.addEvent(eventDTO, user.getBand());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/event/get")
    public List<EventDTO> getEvents(@CurrentUser final User user) {
        return eventService.getEvents(user.getBand().getId());
    }

    @DeleteMapping("/event/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable final Long id) {
        eventService.deleteEvent(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/event/edit")
    public ResponseEntity editEvent(@RequestBody final EventDTO eventDTO) {
        eventService.editEvent(eventDTO);

        return ResponseEntity.ok().build();
    }

}
