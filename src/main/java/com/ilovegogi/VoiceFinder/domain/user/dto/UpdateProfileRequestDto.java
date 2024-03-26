package com.ilovegogi.VoiceFinder.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateProfileRequestDto {

    @NotBlank
    private String password;
    @NotBlank
    private String newPassword;
    private String username;
    private String gender;

}
