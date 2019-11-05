package com.bandaddict.DTO;

import com.bandaddict.Entity.Band;
import com.bandaddict.Enum.Role;
import lombok.Data;

import java.util.List;

/**
 * the User DTO
 */
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Band band;
    private Role role;
    private List<InstrumentDTO> instruments;
    private List<PostDTO> posts;
    private String description;
    private String profilePicture;
    private String nickName;
    private String phoneNumber;
}
