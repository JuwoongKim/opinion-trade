package com.juwoong.opiniontrade.survey.application.response;

import java.util.List;

import com.juwoong.opiniontrade.survey.domain.SurveyResult;

public record SurveyResultsResponse(
	List<SurveyResult> surveyResults
) {
}
