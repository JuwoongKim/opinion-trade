package com.juwoong.opiniontrade.survey.application.response;

public sealed interface SurveyResponse {
	record Create(Long id) implements SurveyResponse {
	}
}
