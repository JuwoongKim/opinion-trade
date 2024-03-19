package com.juwoong.opiniontrade.survey.application.response;

import java.util.List;

import com.juwoong.opiniontrade.survey.domain.Question;
import com.juwoong.opiniontrade.survey.domain.Survey;

public sealed interface SurveyResponse {
	record Create(
		Long id
	) implements SurveyResponse {
	}

	record GetDetail(
		Long surveyId,
		String title,
		String description,
		Survey.Status surveyStatus,
		List<Question> questions
	) implements SurveyResponse {
		public GetDetail(Survey survey) {
			this(
				survey.getId(),
				survey.getSurveyInfo().getTitle(),
				survey.getSurveyInfo().getDescription(),
				survey.getSurveyStatus(),
				survey.getQuestions()
			);
		}
	}
}
