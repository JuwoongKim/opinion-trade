package com.juwoong.opiniontrade.survey.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juwoong.opiniontrade.survey.application.response.SurveyResponse;
import com.juwoong.opiniontrade.survey.domain.Creator;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.domain.repository.SurveyRepository;

@Service
@Transactional(readOnly = true)
public class SurveyService {
	private final SurveyRepository surveyRepository;

	public SurveyService(SurveyRepository surveyRepository) {
		this.surveyRepository = surveyRepository;
	}

	@Transactional
	public SurveyResponse createSurvey(Creator creator, String title, String description) {
		Survey survey = new Survey(creator, title, description);

		return new SurveyResponse(survey);
	}
}
