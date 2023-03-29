package com.example.LabWorkMarch_2.dto;

import com.example.LabWorkMarch_2.entity.User;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDto {
    public static UserDto from(User user){
        return builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .enabled(user.getEnabled())
                .build();
    }
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private Boolean enabled = Boolean.TRUE;
}
