package com.bandaddict.Controller;

import com.bandaddict.Annotation.CurrentUser;
import com.bandaddict.DTO.InstrumentDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.User;
import com.bandaddict.Response.UploadResponse;
import com.bandaddict.Service.FileService;
import com.bandaddict.Service.InstrumentService;
import com.bandaddict.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-data")
public class MyDataController {

    private InstrumentService instrumentService;
    private UserService userService;
    private FileService fileService;

    public MyDataController(final InstrumentService instrumentService, final UserService userService,
                            final FileService fileService) {
        this.instrumentService = instrumentService;
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("/instruments")
    public List<InstrumentDTO> getAllInstrument() {
        return instrumentService.findAll();
    }

    @PostMapping("/update")
    public ResponseEntity updateUser(@CurrentUser final User currentUser, @RequestBody final UserDTO userDTO) {
        userService.updateUser(currentUser, userDTO);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload/{type}")
    public UploadResponse getPath(@CurrentUser final User currentUser, @RequestBody final String imgName, @PathVariable final String type) {
        final UploadResponse path = new UploadResponse();
        path.setPath(fileService.getPath(currentUser, imgName, type));
        return path;
    }
}
