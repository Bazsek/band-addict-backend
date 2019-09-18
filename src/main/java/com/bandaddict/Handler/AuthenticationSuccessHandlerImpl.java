package com.bandaddict.Handler;

import com.bandaddict.Entity.User;
import com.bandaddict.DTO.UserDTO;
import com.bandaddict.Response.LoginResponse;
import com.bandaddict.Service.JwtTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * the authenticationSuccessHandler implementation
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private JwtTokenService jwtTokenService;
    private ConversionService conversionService;

    public AuthenticationSuccessHandlerImpl(final JwtTokenService jwtTokenService, final ConversionService conversionService) {
        this.jwtTokenService = jwtTokenService;
        this.conversionService = conversionService;
    }

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {

        final Object responseObject;

        final User user = (User)authentication.getPrincipal();
        responseObject = new LoginResponse(jwtTokenService.generateToken(user.getId()), conversionService.convert(user, UserDTO.class));

        new ObjectMapper().writeValue(response.getWriter(), responseObject);
    }
}
