package com.juwoong.opiniontrade.survey.api.request;

import com.juwoong.opiniontrade.survey.domain.Creator;

public record SurveyRequest(
	Creator creator,
	String title,
	String description
) {
}
