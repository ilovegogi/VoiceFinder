package com.ilovegogi.VoiceFinder.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SignupRequestDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String username;
    @NotNull
    private LocalDate birthDate;
    @NotBlank
    private String gender;
    private boolean admin = false;
    private String adminToken = "";

    @Builder
    public SignupRequestDto(String email, String password, String username, LocalDate birthDate, String gender, boolean admin, String adminToken) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.birthDate = birthDate;
        this.gender = gender;
        this.admin = admin;
        this.adminToken = adminToken;
    }

    public static class UpdateProfileRequestDto {
    }
}
