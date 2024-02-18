package com.juwoong.opiniontrade.global.exception;

import lombok.Getter;

@Getter
public class OpinionTradeException extends RuntimeException {
	private final ErrorCode errorCode;

	public OpinionTradeException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public OpinionTradeException(ErrorCode errorCode, Throwable throwable) {
		super(errorCode.getMessage(), throwable);
		this.errorCode = errorCode;
	}
}
