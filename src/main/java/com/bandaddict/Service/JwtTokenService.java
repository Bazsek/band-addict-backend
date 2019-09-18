package com.bandaddict.Service;

/**
 * the jwtToken service
 */
public interface JwtTokenService {

    /**
     * Parse jwt token
     *
     * @param token jwt token
     * @return user id
     */
    Long parseToken(final String token);

    /**
     * Generate jwt token
     *
     * @param userId user id
     * @return jwt token
     */
    String generateToken(final Long userId);
}
