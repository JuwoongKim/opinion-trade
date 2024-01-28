package com.juwoong.opiniontrade.survey.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juwoong.opiniontrade.survey.api.request.SurveyRequest;
import com.juwoong.opiniontrade.survey.application.SurveyService;
import com.juwoong.opiniontrade.survey.application.response.SurveyResponse;
import com.juwoong.opiniontrade.survey.domain.Creator;

@RestController
@RequestMapping(value = "/surveys")
public class SurveyController {
	private final SurveyService surveyService;

	public SurveyController(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SurveyResponse createSurvey(@RequestBody SurveyRequest surveyRequest) {
		Creator creator = surveyRequest.creator();
		String title = surveyRequest.title();
		String description = surveyRequest.description();

		SurveyResponse surveyResponse = surveyService.createSurvey(creator, title, description);

		return surveyResponse;
	}
}
