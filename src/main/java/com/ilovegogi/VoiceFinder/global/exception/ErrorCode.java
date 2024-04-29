package com.ilovegogi.VoiceFinder.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* USER */
    // 00: not found, 99 : 관리자 관련
    NOT_FOUND_BY_ID(HttpStatus.NOT_FOUND, "U10000", "해당 id를 가지는 유저를 찾을 수 없습니다."),
    NOT_FOUND_USER_FOR_LOGIN(HttpStatus.NOT_FOUND, "U10001", "존재하지 않는 회원입니다."),
    REQUIRED_ADMIN_USER_AUTHORITY(HttpStatus.UNAUTHORIZED, "U19900", "관리자 권한이 필요합니다."),
    INVALID_ADMIN_TOKEN(HttpStatus.BAD_REQUEST, "U19901", "유효하지 않은 관리자 토큰입니다."),
    EXISTED_USER_EMAIL(HttpStatus.CONFLICT, "U10100", "사용 중인 이메일 입니다."),
    ALREADY_USED_PASSWORD(HttpStatus.BAD_REQUEST, "U10200", "기존과 다른 비밀 번호를 입력해주세요."),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "U10201", "비밀번호가 틀렸습니다."),
    EXISTED_USER_NICKNAME(HttpStatus.CONFLICT, "U10400", "사용 중인 닉네임 입니다."),
    INVALID_NICKNAME_LENGTH(HttpStatus.BAD_REQUEST, "U10401", "최소 2자, 최대 10자로 입력해주세요."),
    INVALID_NICKNAME_PATTERN(HttpStatus.BAD_REQUEST, "U10402", "한글로만 입력해주세요."),
    EXISTED_USER_PHONE_NUMBER(HttpStatus.CONFLICT, "U10600", "사용 중인 전화 번호 입니다."),
    INVALID_PHONE_NUMBER_PATTERN(HttpStatus.BAD_REQUEST, "U10601", "전화번호 형식으로, 숫자로만 입력해주세요."),
    INVALID_VERIFICATION_NUMBER(HttpStatus.BAD_REQUEST, "U10700", "잘못된 인증 번호 입니다."),
    EXCEED_VERIFICATION_TIME(HttpStatus.BAD_REQUEST, "U10701", "인증 번호 입력 시간 초과 입니다."),

    /* MARKET */
    EXISTED_MARKET(HttpStatus.CONFLICT, "M10100", "이미 등록된 매장입니다."),
    NOT_FOUND_MARKET(HttpStatus.NOT_FOUND, "M10000", "해당하는 매장을 찾지 못했습니다."),


    /* CAMPAIGNS */
    ALREADY_ENDED_CAMPAIGN(HttpStatus.CONFLICT, "C10000", "이미 종료된 캠페인입니다."),
    NOT_FOUND_CAMPAIGN(HttpStatus.NOT_FOUND, "C10100", "해당하는 캠페인 정보를 찾지 못했습니다."),
    EXISTED_CAMPAIGN(HttpStatus.CONFLICT, "C10101", "이미 등록된 캠페인입니다."),

    /* AGE */
    EXISTED_AGE(HttpStatus.CONFLICT, "A0100", "이미 등록된 나이대 입니다."),

    /* GLOBAL */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "", ""),
    INTERNAL_BAD_REQUEST(HttpStatus.BAD_REQUEST, "", ""),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "G10000", "해당 요청에 대한 권한이 없습니다."),

    /* TOKEN */
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "T10000", "유효하지 않는 JWT 토큰입니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "T10001", "만료된 JWT 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "T10002", "지원하지 않는 JWT 토큰입니다."),
    NON_ILLEGAL_ARGUMENT_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "T10003", "잘못된 JWT 토큰입니다.");

    private HttpStatus httpStatus;
    private String code;
    private String message;
}