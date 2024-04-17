package com.ilovegogi.VoiceFinder.domain.user.dto;


import com.ilovegogi.VoiceFinder.domain.user.entity.Role;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserProfileDto {
    Long userId;
    String username;
    String email;
    String imageUrl;
    LocalDate birthDate;
    String gender;
    Role role;

    public UserProfileDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.imageUrl = user.getImageUrl();
        this.birthDate = user.getBirthDate();
        this.gender = user.getGender();
        this.role = user.getRole();
    }

}