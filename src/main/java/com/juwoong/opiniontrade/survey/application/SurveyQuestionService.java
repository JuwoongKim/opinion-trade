package com.juwoong.opiniontrade.survey.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juwoong.opiniontrade.survey.domain.Option;
import com.juwoong.opiniontrade.survey.domain.Question;
import com.juwoong.opiniontrade.survey.domain.QuestionInfo;
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
	public void createQuestion(
		Long surveyId,
		Integer questionOrder,
		Question.Type type,
		String title,
		String description,
		List<Option> options
	) {
		QuestionInfo questionInfo = QuestionInfo.init(title, description);
		Question question = Question.init(type, questionInfo, options);

		Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException());

		survey.createQuestion(questionOrder, question);
	}

	// @Transactional
	// public void removeSurvey(Long surveyId, Integer questionOrder) {
	// 	// Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException());
	// 	// survey.removeQuestion(questionOrder);
	// }
	//
	// @Transactional
	// public void changeOrder(Long surveyId, Integer oneOrder, Integer anotherOrder) {
	// 	// Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException());
	// 	// survey.changeQuestionOrder(oneOrder, anotherOrder);
	// }
	//
	// public QuestionsResponse getQuestions(Long surveyId) {
	// 	Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException());
	// 	Map<Integer, Question> questions = survey.findQuestionWithOrder();
	//
	// 	return new QuestionsResponse(questions);
	// }
}
