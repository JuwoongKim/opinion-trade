package com.juwoong.opiniontrade.survey.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juwoong.opiniontrade.survey.api.request.RequestInfoRequest;
import com.juwoong.opiniontrade.survey.api.request.SurveyRequest;
import com.juwoong.opiniontrade.survey.application.SurveyService;
import com.juwoong.opiniontrade.survey.application.response.SurveyResponse;
import com.juwoong.opiniontrade.survey.domain.Creator;
import com.juwoong.opiniontrade.survey.domain.RequestInfo;
import com.juwoong.opiniontrade.survey.domain.Respondent;

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
	public SurveyResponse createSurvey(@Valid @RequestBody SurveyRequest surveyRequest) {
		Creator creator = surveyRequest.creator();
		String title = surveyRequest.title();
		String description = surveyRequest.description();

		SurveyResponse surveyResponse = surveyService.createSurvey(creator, title, description);

		return surveyResponse;
	}

	@PutMapping("/{surveyId}/request")
	@ResponseStatus(HttpStatus.OK)
	public void requestSurvey(
		@PathVariable Long surveyId,
		@RequestBody RequestInfoRequest requestInfoRequest
	) {

		RequestInfo requestInfo = requestInfoRequest.requestInfo();
		List<Respondent> respondents = requestInfoRequest.assignedRespondent();
		//surveyService.requestSurvey(surveyId, requestInfo, respondents);

	}

}
