package com.ilovegogi.VoiceFinder.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private String code;
    private String message;
    private T data;

    public static <K> ApiResponse<K> of(String code, String message, K data) {
        return new ApiResponse<>(code, message, data);
    }

}