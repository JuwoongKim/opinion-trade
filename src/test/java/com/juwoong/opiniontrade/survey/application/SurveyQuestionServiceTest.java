package com.juwoong.opiniontrade.survey.application;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.juwoong.opiniontrade.survey.domain.Creator;
import com.juwoong.opiniontrade.survey.domain.Option;
import com.juwoong.opiniontrade.survey.domain.Question;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.domain.SurveyInfo;
import com.juwoong.opiniontrade.survey.domain.repository.SurveyRepository;
import com.juwoong.opiniontrade.survey.fixture.SurveyFixture;

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
		List<Option> options = List.of(Option.init("Option1"), Option.init("Option2"));

		// when
		Creator creator = Creator.init(1L);
		SurveyInfo surveyInfo = SurveyInfo.init("title", "descriptions");
		Survey survey = Survey.init(creator, surveyInfo);

		when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));

		// when then
		assertThatNoException().isThrownBy(
			() -> surveyQuestionService.createQuestion(surveyId, Question.Type.MULTIPLE_CHOICE,
				title, description, options));

		verify(surveyRepository, times(1)).findById(anyLong());
	}

	@Test
	@DisplayName("질문 삭제에 성공한다.")
	void deleteQuestions_Success_With_NoExceptions(){
		Survey survey = SurveyFixture.SURVEY.getInstance();
		Integer questionOrder = 1;
		Long surveyId = 1L;
		when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));

		// when then
		assertThatNoException().isThrownBy(
			() -> surveyQuestionService.removeQuestion(surveyId, questionOrder));

		verify(surveyRepository, times(1)).findById(anyLong());
	}
}
