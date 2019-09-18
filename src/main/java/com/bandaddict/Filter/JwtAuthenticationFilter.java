package com.bandaddict.Filter;

import com.bandaddict.Entity.User;
import com.bandaddict.Enum.JwtPart;
import com.bandaddict.Service.JwtTokenService;
import com.bandaddict.Service.UserService;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * the jwt token authentication filter class
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final static int TOKEN_EXPIRED_STATUS = 409;

    private JwtTokenService jwtTokenService;
    private UserService userService;

    /**
     * Constructor
     *
     * @param authenticationManager the authenticationManager bean
     * @param jwtTokenService the jwtTokenService bean
     * @param userService the userService bean
     */
    public JwtAuthenticationFilter(final AuthenticationManager authenticationManager, final JwtTokenService jwtTokenService,
                                   final UserService userService) {
        super(authenticationManager);
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected final void doFilterInternal(final HttpServletRequest req, final HttpServletResponse resp,
                                          final FilterChain chain) throws IOException, ServletException {
        final String header = req.getHeader(JwtPart.HEADER.getValue());

        if (StringUtils.startsWithIgnoreCase(header, JwtPart.PREFIX.getValue())) {
            try {
                final String token = StringUtils.replace(header, JwtPart.PREFIX.getValue(), "");
                final Long userId = jwtTokenService.parseToken(token);

                if (userId != null) {
                    final User user = userService.findOneById(userId);
                    SecurityContextHolder.getContext().
                            setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
            } catch (final JwtException | IllegalArgumentException | NoSuchElementException e) {
                resp.setStatus(TOKEN_EXPIRED_STATUS);
                return;
            }
        }

        chain.doFilter(req,resp);
    }
}
