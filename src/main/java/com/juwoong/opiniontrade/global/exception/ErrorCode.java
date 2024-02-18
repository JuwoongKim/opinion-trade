package com.juwoong.opiniontrade.global.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	// 404
	NOT_FOUND_SURVEY(NOT_FOUND, "설문지가 존재하지 않습니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
