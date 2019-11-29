package com.bandaddict.Controller;

import com.bandaddict.Annotation.CurrentUser;
import com.bandaddict.DTO.BandDTO;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Entity.User;
import com.bandaddict.Repository.BandRepository;
import com.bandaddict.Service.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

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
    private SearchService searchService;
    private EmailService emailService;

    /**
     * Constructor
     *
     * @param userService the userService bean
     * @param jwtTokenService the jwtTokenService bean
     * @param conversionService the conversionService bean
     * @param searchService searchService
     * @param emailService emailService
     */
    public HomeController(final UserService userService, final JwtTokenService jwtTokenService, final ConversionService conversionService,
                          final BandService bandService, final SearchService searchService, final EmailService emailService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.conversionService = conversionService;
        this.bandService = bandService;
        this.searchService = searchService;
        this.emailService = emailService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody final UserDTO userDTO) {
        final User user = userService.signUp(userDTO);

        emailService.sendActivationMail(user, jwtTokenService.generateToken(user.getId()), Locale.ENGLISH);

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
    public UserDTO activate(@CurrentUser final User user) {
        userService.activateUser(user.getId());

        return conversionService.convert(user, UserDTO.class);
    }

    @GetMapping("/get-user-by-id/{id}")
    public UserDTO getUserById(@CurrentUser final User user, @PathVariable final Long id) {
        return conversionService.convert(userService.findOneById(id), UserDTO.class);
    }

    @GetMapping("/get-band-by-id/{id}")
    public BandDTO getBandById(@CurrentUser final User user, @PathVariable final Long id) {
        return bandService.getBandById(id);
    }

    @GetMapping("/search/{value}")
    public List<UserDTO> getUserById(@PathVariable final String value) {
        return searchService.searchUsers(value);
    }
}
