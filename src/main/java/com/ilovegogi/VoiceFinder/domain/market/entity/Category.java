package com.ilovegogi.VoiceFinder.domain.market.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    CAFE("CAFE", "카페"),
    KOREAN_FOOD("KOREAN_FOO", "한식");

    private final String key;
    private final String title;
}
