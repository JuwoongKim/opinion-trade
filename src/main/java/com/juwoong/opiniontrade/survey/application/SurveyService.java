package com.juwoong.opiniontrade.survey.application;

import static com.juwoong.opiniontrade.global.exception.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juwoong.opiniontrade.global.exception.OpinionTradeException;
import com.juwoong.opiniontrade.survey.application.response.SurveyResponse;
import com.juwoong.opiniontrade.survey.domain.Creator;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.domain.SurveyInfo;
import com.juwoong.opiniontrade.survey.domain.repository.SurveyRepository;

@Service
@Transactional(readOnly = true)
public class SurveyService {
	private final SurveyRepository surveyRepository;

	public SurveyService(SurveyRepository surveyRepository) {
		this.surveyRepository = surveyRepository;
	}

	@Transactional
	public SurveyResponse.Create createSurvey(Long creatorId, String title, String description) {
		Creator creator = Creator.init(creatorId);
		SurveyInfo surveyInfo = SurveyInfo.init(title, description);
		Survey survey = Survey.init(creator, surveyInfo);

		Survey createdSurvey = surveyRepository.save(survey);

		return new SurveyResponse.Create(createdSurvey.getId());
	}

	@Transactional
	public void updateSurvey(Long surveyId, String title, String description) {
		Survey survey = surveyRepository.findById(surveyId)
			.orElseThrow(() -> new OpinionTradeException(NOT_FOUND_SURVEY));

		SurveyInfo surveyInfo = SurveyInfo.init(title, description);
		survey.update(surveyInfo);
	}

	public SurveyResponse.GetDetail getSurvey(Long surveyId) {
		Survey survey = surveyRepository.findById(surveyId)
			.orElseThrow(() -> new OpinionTradeException(NOT_FOUND_SURVEY));

		return new SurveyResponse.GetDetail(survey);
	}
}
