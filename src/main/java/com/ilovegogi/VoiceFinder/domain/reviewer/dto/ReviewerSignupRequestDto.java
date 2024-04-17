package com.ilovegogi.VoiceFinder.domain.reviewer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewerSignupRequestDto {

    private Long userId;
    private String blogUrl;
    private String selfDescription;
    private String job;
    private List<String> type;
    private List<String> activeAddress;

}
