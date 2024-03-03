package com.juwoong.opiniontrade.survey.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juwoong.opiniontrade.survey.api.request.QuestionRequest;
import com.juwoong.opiniontrade.survey.application.SurveyQuestionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/surveys")
public class SurveyQuestionController {
	private final SurveyQuestionService surveyQuestionService;

	public SurveyQuestionController(SurveyQuestionService surveyQuestionService) {
		this.surveyQuestionService = surveyQuestionService;
	}

	@PostMapping("/{surveyId}/questions")
	@ResponseStatus(HttpStatus.CREATED)
	public void createSurveyQuestion(
		@PathVariable Long surveyId,
		@Valid @RequestBody QuestionRequest.Create request
	) {
		surveyQuestionService.createQuestion(
			surveyId,
			request.type(),
			request.title(),
			request.description(),
			request.options()
		);
	}

	@DeleteMapping("/{surveyId}/questions")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSurveyQuestion(
		@PathVariable Long surveyId,
		@Valid @RequestBody QuestionRequest.Delete request
	) {
		Integer questionOrder = request.questionOrder();
		surveyQuestionService.removeQuestion(surveyId, questionOrder);
	}

	@PutMapping("/{surveyId}/questions")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSurveyQuestion(
		@PathVariable Long surveyId,
		@Valid @RequestBody QuestionRequest.Update request
	) {
		surveyQuestionService.updateQuestion(
			surveyId,
			request.questionOrder(),
			request.type(),
			request.title(),
			request.description(),
			request.options()
		);
	}


	//
	// @GetMapping("/{surveyId}/questions")
	// @ResponseStatus(HttpStatus.OK)
	// public QuestionsResponse getQuestions(@PathVariable Long surveyId) {
	// 	QuestionsResponse questionsResponse = surveyQuestionService.getQuestions(surveyId);
	//
	// 	return questionsResponse;
	// }
	//
	//
	// @PutMapping("/{surveyId}/questions/change-order")
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	// public void changeQuestionOrder(
	// 	@PathVariable Long surveyId,
	// 	@RequestParam Integer oneOrder,
	// 	@RequestParam Integer anotherOrder
	// ) {
	// 	surveyQuestionService.changeOrder(surveyId, oneOrder, anotherOrder);
	// }
}
