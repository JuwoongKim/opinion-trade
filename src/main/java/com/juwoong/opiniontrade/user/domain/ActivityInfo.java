package com.juwoong.opiniontrade.user.domain;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ActivityInfo {
	@Column(name = "request_count")
	@ColumnDefault(value = "0")
	private Long requestCount;

	@Column(name = "response_count")
	@ColumnDefault(value = "0")
	private Long responseCount;

	@Column(name = "adopted_response_count")
	@ColumnDefault(value = "0")
	private Long adoptedResponseCount;
}
