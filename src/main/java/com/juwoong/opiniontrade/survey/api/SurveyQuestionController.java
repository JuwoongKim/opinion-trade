package com.juwoong.opiniontrade.survey.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juwoong.opiniontrade.survey.api.request.QuestionRequest;
import com.juwoong.opiniontrade.survey.application.SurveyQuestionService;

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
		@RequestBody QuestionRequest.Create request
	) {
		surveyQuestionService.createQuestion(
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
	// @DeleteMapping("/{surveyId}/questions/{questionOrder}")
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	// public void deleteSurveyQuestion(
	// 	@PathVariable Long surveyId,
	// 	@PathVariable Integer questionOrder
	// ) {
	// 	surveyQuestionService.removeSurvey(surveyId, questionOrder);
	// }
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
