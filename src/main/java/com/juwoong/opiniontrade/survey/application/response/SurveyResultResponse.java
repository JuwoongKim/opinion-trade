package com.juwoong.opiniontrade.survey.application.response;

import java.util.List;

import com.juwoong.opiniontrade.survey.domain.Answer;
import com.juwoong.opiniontrade.survey.domain.SurveyResult;

public sealed interface SurveyResultResponse {
	record GetByRespondent(
		Integer totalRespondents,
		Integer currentRespondent,
		Long respondentId,
		List<Answer> answers
	) implements SurveyResultResponse {
		public GetByRespondent(Integer totalRespondents, Integer currentRespondent, SurveyResult result) {
			this(
				totalRespondents,
				currentRespondent,
				result.getRespondent().getRespondentId(),
				result.getAnswers()
			);
		}
	}

	record GetByQuestion(
		Long questionId,
		List<Answer> answers
	) implements SurveyResultResponse {
	}

}
