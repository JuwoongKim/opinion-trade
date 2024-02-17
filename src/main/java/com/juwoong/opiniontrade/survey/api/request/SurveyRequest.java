package com.juwoong.opiniontrade.survey.api.request;

import com.juwoong.opiniontrade.survey.domain.Creator;

import jakarta.validation.constraints.Size;

public record SurveyRequest(
	Creator creator,
	@Size(min =0, max = 50)
	String title,
	@Size(min =0, max = 200)
	String description
) {
}
