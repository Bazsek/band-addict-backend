package com.bandaddict.Controller;

import com.bandaddict.Annotation.CurrentUser;
import com.bandaddict.DTO.BandDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.User;
import com.bandaddict.Repository.BandRepository;
import com.bandaddict.Service.BandService;
import com.bandaddict.Service.JwtTokenService;
import com.bandaddict.Service.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Home controller
 */
@RestController
@RequestMapping("")
public class HomeController {

    private UserService userService;
    private JwtTokenService jwtTokenService;
    private ConversionService conversionService;
    private BandService bandService;

    /**
     * Constructor
     *
     * @param userService the userService bean
     * @param jwtTokenService the jwtTokenService bean
     * @param conversionService the conversionService bean
     */
    public HomeController(final UserService userService, final JwtTokenService jwtTokenService, final ConversionService conversionService,
                          final BandService bandService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.conversionService = conversionService;
        this.bandService = bandService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody final UserDTO userDTO) {
        final User user = userService.signUp(userDTO);

        jwtTokenService.generateToken(user.getId());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-user")
    public UserDTO getCurrentUser(@CurrentUser final User user) {
        return conversionService.convert(user, UserDTO.class);
    }

    @GetMapping("/get-band")
    public BandDTO getCurrentBand(@CurrentUser final User user) {
        return conversionService.convert(user.getBand(), BandDTO.class);
    }

    @PatchMapping("/activate")
    public ResponseEntity activate(final User user) {
        userService.activateUser(user.getId());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-user-by-id/{id}")
    public UserDTO getUserById(@CurrentUser final User user, @PathVariable final Long id) {
        return conversionService.convert(userService.findOneById(id), UserDTO.class);
    }

    @GetMapping("/get-band-by-id/{id}")
    public BandDTO getBandById(@CurrentUser final User user, @PathVariable final Long id) {
        return bandService.getBandById(id);
    }
}
