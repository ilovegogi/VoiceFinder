package com.ilovegogi.VoiceFinder.domain.user.service;

import com.ilovegogi.VoiceFinder.domain.user.dto.SignupRequestDto;
import com.ilovegogi.VoiceFinder.domain.user.dto.UpdateProfileRequestDto;
import com.ilovegogi.VoiceFinder.domain.user.dto.UserProfileDto;
import com.ilovegogi.VoiceFinder.domain.user.entity.Role;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.domain.user.repository.UserRepository;
import com.ilovegogi.VoiceFinder.global.exception.CustomException;
import com.ilovegogi.VoiceFinder.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final String ADMIN_TOKEN = "wirafob32obvaobf3aaw1lk3ne";

    public UserProfileDto signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new CustomException(ErrorCode.EXISTED_USER_EMAIL);
        }

        // 사용자 ROLE 확인
        Role role = Role.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new CustomException(ErrorCode.INVALID_ADMIN_TOKEN);
            }
            role = Role.ADMIN;
        }

        // 사용자 등록
        User user = new User(email, password, requestDto.getUsername(), requestDto.getBirthDate(), requestDto.getGender(), role);
        userRepository.save(user);

        return new UserProfileDto(user);
    }

    @Override
    public UserProfileDto updateUserProfile(User user, UpdateProfileRequestDto requestDto) {
        // 기존 비밀번호가 일치하는지 검증
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.NOT_MATCH_PASSWORD);
        }

        if (requestDto.getNewPassword() != null && !requestDto.getNewPassword().trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        }
        if (requestDto.getUsername() != null && !requestDto.getUsername().trim().isEmpty()) {
            user.setUsername(requestDto.getUsername());
        }
        if (requestDto.getGender() != null && !requestDto.getGender().trim().isEmpty()) {
            user.setGender(requestDto.getGender());
        }

        User updatedUser = userRepository.save(user);
        return new UserProfileDto(updatedUser);
    }

}
