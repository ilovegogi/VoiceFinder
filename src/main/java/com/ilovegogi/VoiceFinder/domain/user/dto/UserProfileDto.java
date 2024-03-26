package com.ilovegogi.VoiceFinder.domain.user.dto;


import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.domain.user.entity.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserProfileDto {
    String username;
    String email;
    LocalDate birthDate;
    String gender;

    public UserProfileDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.birthDate = user.getBirthDate();
        this.gender = user.getGender();
    }

}