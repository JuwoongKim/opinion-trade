package com.juwoong.opiniontrade.global.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	// 400
	INDEX_OUT_OF_BOUNDS(BAD_REQUEST, "범위에 없는 값을 요청했습니다."),
	// 404
	NOT_FOUND_SURVEY(NOT_FOUND, "설문지가 존재하지 않습니다."),
	NOT_FOUND_SURVEY_RESULT(NOT_FOUND, "설문 결과가 존재하지 않습니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
