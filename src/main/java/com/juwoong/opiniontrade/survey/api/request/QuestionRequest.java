package com.juwoong.opiniontrade.survey.api.request;

import java.util.List;

import com.juwoong.opiniontrade.survey.domain.Option;
import com.juwoong.opiniontrade.survey.domain.Question;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public sealed interface QuestionRequest {
	record Create(
		@Size(min = 0, max = 50) String title,
		@Size(min = 0, max = 200) String description,
		@NotNull Question.Type type,
		@NotNull List<Option> options
	) implements QuestionRequest {
	}

	record Update(
		@Min(value = 1) Integer questionOrder,
		@Size(min = 0, max = 50) String title,
		@Size(min = 0, max = 200) String description,
		@NotNull Question.Type type,
		@NotNull List<Option> options
	) implements QuestionRequest {
	}

	record Delete(
		@Min(value = 1) Integer questionOrder
	) implements QuestionRequest {
	}
}
