package com.juwoong.opiniontrade.global.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

public record ErrorResponse(
	HttpStatus code,
	String message,
	List<ArgumentError> errors
) {
	@Getter
	@AllArgsConstructor
	public static class ArgumentError {
		String field;
		String inputValue;
		String message;
	}

	public ErrorResponse(HttpStatus code, String message) {
		this(code, message, null);
	}

	public ErrorResponse(HttpStatus code, String message, List<ArgumentError> errors) {
		this.code = code;
		this.message = message;
		this.errors = errors;
	}
}
