package com.juwoong.opiniontrade.survey.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juwoong.opiniontrade.survey.api.request.SurveyResultRequest;
import com.juwoong.opiniontrade.survey.application.SurveyResultService;
import com.juwoong.opiniontrade.survey.application.response.SurveyResultResponse;
import com.juwoong.opiniontrade.survey.domain.Answer;
import com.juwoong.opiniontrade.survey.domain.Respondent;
import com.juwoong.opiniontrade.survey.domain.SurveyResult;

@RestController(value = "/surveys")
public class SurveyResultController {

	private final SurveyResultService surveyResultService;

	public SurveyResultController(SurveyResultService surveyResultService) {
		this.surveyResultService = surveyResultService;
	}

	@PostMapping("/{surveyId}/survey-results")
	@ResponseStatus(HttpStatus.CREATED)
	public SurveyResultResponse receiveSurveyResult(
		@PathVariable Long surveyId,
		@RequestBody SurveyResultRequest surveyResultRequest
	) {
		Respondent respondent = surveyResultRequest.respondent();
		List<Answer> answers = surveyResultRequest.answers();

		SurveyResultResponse surveyResultResponse = surveyResultService.receiveSurveyResult(
			surveyId,
			respondent,
			answers
		);

		return surveyResultResponse;
	}

}
