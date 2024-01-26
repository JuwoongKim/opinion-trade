package com.juwoong.opiniontrade.survey.domain;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class RequestInfo {
	enum RequestType {
		POST_BOARD, EXPORT_LINK, ASSIGN_RESPONDENT
	}

	@Column(name = "request_type")
	private RequestType requestType;

	@Column(name = "desired_response_count")
	private Long desiredResponseCount;

	@Column(name = "deadline_at")
	@JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime deadline;

	protected RequestInfo() {
	}

	public RequestInfo(RequestType requestType, Long desiredResponseCount, LocalDateTime deadline) {
		this.requestType = requestType;
		this.desiredResponseCount = desiredResponseCount;
		this.deadline = deadline;
	}

	protected void checkEndCondition() {
	}
}
