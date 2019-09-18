package com.bandaddict.Controller;

import com.bandaddict.Annotation.CurrentUser;
import com.bandaddict.DTO.BandDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.User;
import com.bandaddict.Repository.BandRepository;
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
    private BandRepository bandRepository;

    /**
     * Constructor
     *
     * @param userService the userService bean
     * @param jwtTokenService the jwtTokenService bean
     * @param conversionService the conversionService bean
     */
    public HomeController(final UserService userService, final JwtTokenService jwtTokenService, final ConversionService conversionService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.conversionService = conversionService;
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
}
