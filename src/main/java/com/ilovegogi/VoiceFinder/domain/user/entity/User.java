package com.ilovegogi.VoiceFinder.domain.user.entity;

import com.ilovegogi.VoiceFinder.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Column(nullable = false)
    private String username;

    private LocalDate birthDate;

    private String gender;

    private String imageUrl;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String provider;

    public User(String email, String password, String username, LocalDate birthDate, String gender, Role role) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.birthDate = birthDate;
        this.gender = gender;
        this.role = role;
    }
}
