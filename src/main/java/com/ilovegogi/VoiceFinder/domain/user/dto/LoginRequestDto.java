package com.ilovegogi.VoiceFinder.domain.user.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    private String email;
    private String password;
}