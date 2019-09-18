package com.bandaddict.Service.Implementations;

import com.bandaddict.Exception.ParseException;
import com.bandaddict.Service.JwtTokenService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * the jwtToken service implementation
 */
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    @Value("${jwt.expiration.time}")
    private Long jwtExpirationTime;

    @Override
    public Long parseToken(final String token) throws JwtException, IllegalArgumentException {
        Long userId = null;

        if(!StringUtils.isEmpty(token)){

            final String subject = Jwts.parser().
                    setSigningKey(jwtSecretKey).
                    parseClaimsJws(token).
                    getBody().
                    getSubject();

            if(!StringUtils.isEmpty(subject)){
                try{
                    userId = Long.parseLong(subject);
                } catch (final NumberFormatException e){
                    throw new ParseException("Failed parse token!", e);
                }
            }
        }

        return userId;
    }

    @Override
    public String generateToken(final Long userId) {
        return Jwts.builder().
                setSubject(MessageFormat.format("{0,number,#}", userId)).
                setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(jwtExpirationTime))).
                signWith(SignatureAlgorithm.HS512, jwtSecretKey).
                compact();
    }
}
