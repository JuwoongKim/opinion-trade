package com.juwoong.opiniontrade.survey.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juwoong.opiniontrade.survey.api.request.ResultRequest;
import com.juwoong.opiniontrade.survey.application.SurveyResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/surveys")
@RequiredArgsConstructor
public class SurveyResultController {
	private final SurveyResultService surveyResultService;

	@PostMapping("/{surveyId}/surveyResult")
	@ResponseStatus(HttpStatus.CREATED)
	public void createSurveyResult(
		@PathVariable Long surveyId,
		@RequestBody ResultRequest.Create request
	) {
		Long respondentId = request.respondentId();
		List<ResultRequest.Answer> answers = request.answers();

		surveyResultService.createSurveyResult(surveyId, respondentId, answers);
	}
}