package com.juwoong.opiniontrade.survey.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public sealed interface SurveyRequest {
	record Create(
		@NotNull Long creatorId,
		@Size(min = 0, max = 50) String title,
		@Size(min = 0, max = 200) String description
	)
		implements SurveyRequest {
	}
}
