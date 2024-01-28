package com.juwoong.opiniontrade.survey.api.request;

import java.util.List;

import com.juwoong.opiniontrade.survey.domain.Answer;
import com.juwoong.opiniontrade.survey.domain.Respondent;

public record SurveyResultRequest(
	Respondent respondent,
	List<Answer> answers
) {
}
