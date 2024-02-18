package com.juwoong.opiniontrade.survey.application;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.juwoong.opiniontrade.survey.domain.Creator;
import com.juwoong.opiniontrade.survey.domain.Option;
import com.juwoong.opiniontrade.survey.domain.Question;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.domain.repository.SurveyRepository;

@ExtendWith(MockitoExtension.class)
class SurveyQuestionServiceTest {

	@InjectMocks
	private SurveyQuestionService surveyQuestionService;

	@Mock
	private SurveyRepository surveyRepository;

	@Test
	void createQuestion() {
		// given
		Long surveyId = 1L;
		Integer questionOrder = 1;
		String title = "title";
		String description = "description";
		Question.Type type = Question.Type.MULTIPLE_CHOICE;
		List<Option> options = List.of(new Option("Option1"), new Option("Option2"));

		// when
		Survey survey = new Survey(new Creator(1L, "juwoongKim"), "surveytitle", "surveyDescriptions");
		when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(survey));

		// when then
		assertThatNoException().isThrownBy(() -> surveyQuestionService.createQuestion(surveyId, questionOrder, title, description, type, options));
		verify(surveyRepository, times(1)).findById(surveyId);
	}
}
