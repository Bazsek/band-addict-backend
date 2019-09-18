package com.bandaddict.Response;

import com.bandaddict.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Login response
 */
@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private UserDTO userDTO;
}
