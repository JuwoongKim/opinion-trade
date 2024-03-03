package com.juwoong.opiniontrade.survey.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juwoong.opiniontrade.survey.api.request.SurveyRequest;
import com.juwoong.opiniontrade.survey.application.SurveyService;
import com.juwoong.opiniontrade.survey.application.response.SurveyResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/surveys")
public class SurveyController {
	private final SurveyService surveyService;

	public SurveyController(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SurveyResponse.Create createSurvey(@Valid @RequestBody SurveyRequest.Create request) {
		Long creatorId = request.creatorId();
		String title = request.title();
		String description = request.description();

		return surveyService.createSurvey(creatorId, title, description);
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSurvey(
		@PathVariable(name = "id") Long surveyId,
		@Valid @RequestBody SurveyRequest.Update request
	) {
		String title = request.title();
		String description = request.description();

		surveyService.updateSurvey(surveyId, title, description);
	}

}
