package com.ilovegogi.VoiceFinder.domain.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BusinessSignupRequestDto {

    private Long userId;
    private String bossName;
    private String bizName;
    private Long bizNum;
    private String bizFileUrl;
    private Boolean bizClause1;
    private Boolean bizClause2;

}
