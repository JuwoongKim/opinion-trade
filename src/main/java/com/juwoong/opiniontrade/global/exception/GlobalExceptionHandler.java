package com.juwoong.opiniontrade.global.exception;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// custom exception
	@ExceptionHandler(OpinionTradeException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(OpinionTradeException e) {
		ErrorCode errorCode = e.getErrorCode();
		ErrorResponse errorResponse = new ErrorResponse(errorCode.getHttpStatus(), errorCode.getMessage());

		return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
	}

	// client request exception 400 Error
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(BAD_REQUEST)
	public ErrorResponse handleArgumentNotValidException(MethodArgumentNotValidException e) {
		List<ErrorResponse.ArgumentError> argumentErrors = e.getBindingResult().getFieldErrors().stream()
			.map(fieldError -> new ErrorResponse.ArgumentError(fieldError.getField(),
				fieldError.getRejectedValue().toString(),
				fieldError.getDefaultMessage())).toList();

		return new ErrorResponse(BAD_REQUEST, e.getMessage(), argumentErrors);
	}

	// etc
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	public ErrorResponse handleUnexpectedException(RuntimeException e) {
		return new ErrorResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.getReasonPhrase());
	}
}
