package com.juwoong.opiniontrade.survey.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juwoong.opiniontrade.survey.application.response.SurveyResultResponse;
import com.juwoong.opiniontrade.survey.application.response.SurveyResultsResponse;
import com.juwoong.opiniontrade.survey.domain.Answer;
import com.juwoong.opiniontrade.survey.domain.Respondent;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.domain.SurveyResult;
import com.juwoong.opiniontrade.survey.domain.repository.SurveyRepository;

@Service
@Transactional(readOnly = true)
public class SurveyResultService {
	private final SurveyRepository surveyRepository;

	public SurveyResultService(SurveyRepository surveyRepository) {
		this.surveyRepository = surveyRepository;
	}

	@Transactional
	public SurveyResultResponse receiveSurveyResult(Long surveyId, Respondent respondent, List<Answer> answers) {
		SurveyResult surveyResult = new SurveyResult(respondent, answers);

		Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException());
		survey.receiveSurveyResult(surveyResult);

		return new SurveyResultResponse(surveyResult);
	}

	public SurveyResultsResponse getSurveyResults(Long surveyId) {
		Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException());
		List<SurveyResult> surveyResults = survey.findSurveyResult();

		return new SurveyResultsResponse(surveyResults);
	}

	public SurveyResultResponse getSurveyResult(Long surveyId, Long respondentId) {
		// 쿼리 조회

		Respondent tempRespondent = new Respondent(1L, "name");
		List<Answer> tempAnswers =List.of(new Answer(1L, "contnet"));

		SurveyResult surveyResult = new SurveyResult(tempRespondent, tempAnswers);

		return new SurveyResultResponse(surveyResult);
	}

	@Transactional
	public void removeSurveyResult(Long surveyId, Long respondentId) {
	}
}
