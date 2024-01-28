package com.juwoong.opiniontrade.survey.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juwoong.opiniontrade.survey.api.request.SurveyResultRequest;
import com.juwoong.opiniontrade.survey.application.SurveyResultService;
import com.juwoong.opiniontrade.survey.application.response.SurveyResultResponse;
import com.juwoong.opiniontrade.survey.application.response.SurveyResultsResponse;
import com.juwoong.opiniontrade.survey.domain.Answer;
import com.juwoong.opiniontrade.survey.domain.Question;
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

	@GetMapping("/{surveyId}/survey-results")
	@ResponseStatus(HttpStatus.OK)
	public SurveyResultsResponse getSurveyResults(
		@PathVariable Long surveyId
	) {
		SurveyResultsResponse surveyResultsResponse = surveyResultService.getSurveyResults(surveyId);

		return surveyResultsResponse;
	}

	@GetMapping("/{surveyId}/survey-results/{respondentId}")
	@ResponseStatus(HttpStatus.OK)
	public SurveyResultResponse getSurveyResult(
		@PathVariable Long surveyId,
		@PathVariable Long respondentId
	) {
		SurveyResultResponse surveyResultResponse = surveyResultService.getSurveyResult(surveyId, respondentId);

		return surveyResultResponse;
	}

	@DeleteMapping("/{surveyId}/survey-results/{respondentId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSurveyQuestion(
		@PathVariable Long surveyId,
		@PathVariable Long respondentId
	) {
		surveyResultService.removeSurveyResult(surveyId, respondentId);
	}
}
