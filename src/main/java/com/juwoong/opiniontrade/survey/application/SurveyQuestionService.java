package com.juwoong.opiniontrade.survey.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juwoong.opiniontrade.survey.application.response.QuestionResponse;
import com.juwoong.opiniontrade.survey.domain.Option;
import com.juwoong.opiniontrade.survey.domain.Question;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.domain.repository.SurveyRepository;

@Service
@Transactional(readOnly = true)
public class SurveyQuestionService {
	private final SurveyRepository surveyRepository;

	public SurveyQuestionService(SurveyRepository surveyRepository) {
		this.surveyRepository = surveyRepository;
	}

	@Transactional
	public QuestionResponse createQuestion(
		Long surveyId,
		Integer questionOrder,
		String title,
		String description,
		List<Option> options
	) {
		Question question = new Question(title, description, options);
		// Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException());
		// survey.createQuestion(questionOrder, question);

		return new QuestionResponse(question);
	}

	@Transactional
	public void removeSurvey(Long surveyId, Integer questionOrder) {
		// Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException());
		// survey.removeQuestion(questionOrder);
	}
}
