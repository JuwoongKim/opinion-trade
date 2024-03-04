package com.juwoong.opiniontrade.survey.api.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public sealed interface ResultRequest {
	record Create(
		@NotNull Long respondentId,
		List<Answer> answers
	) implements ResultRequest {
	}

	record Update(
		@NotNull Long respondentId,
		List<Answer> answers
	) implements ResultRequest {
	}

	record Answer(
		@NotNull Long questionId,
		@NotNull String content
	) implements ResultRequest {
	}
}
