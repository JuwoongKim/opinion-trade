package com.juwoong.opiniontrade.survey.application;

import static com.juwoong.opiniontrade.global.exception.ErrorCode.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.juwoong.opiniontrade.global.exception.OpinionTradeException;
import com.juwoong.opiniontrade.survey.application.response.SurveyResponse;
import com.juwoong.opiniontrade.survey.domain.Creator;
import com.juwoong.opiniontrade.survey.domain.Question;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.domain.SurveyInfo;
import com.juwoong.opiniontrade.survey.domain.repository.SurveyRepository;
import com.juwoong.opiniontrade.survey.fixture.QuestionFixture;
import com.juwoong.opiniontrade.survey.fixture.SurveyFixture;

@ExtendWith(MockitoExtension.class)
class SurveyServiceTest {
	@InjectMocks
	private SurveyService surveyService;

	@Mock
	private SurveyRepository surveyRepository;

	private Survey surveyFixture;

	@BeforeEach
	void setUp() {
		Long creatorId = 1L;
		String title = "surveyTitle";
		String description = "surveyDescription";
		Creator creator = Creator.init(creatorId);
		SurveyInfo surveyInfo = SurveyInfo.init(title, description);

		surveyFixture = Survey.init(creator, surveyInfo);
	}

	@Test
	@DisplayName("유저가 설문지 생성에 성공한다.")
	void createSurvey() {
		// given
		Long creatorId = 1L;
		String title = "surveyTitle";
		String description = "surveyDescription";
		when(surveyRepository.save(any())).thenReturn(surveyFixture);

		// when
		SurveyResponse surveyResponse = surveyService.createSurvey(creatorId, title, description);

		// then
		verify(surveyRepository, times(1)).save(any(Survey.class));
	}

	@Test
	@DisplayName("설문지 정보 수정에 성공 한다.")
	void updateSurvey_Succes_With_NoExceptions() {
		// given
		Long surveyId = 1L;
		String title = "surveyTitle";
		String description = "surveyDescription";
		when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(surveyFixture));

		// when
		surveyService.updateSurvey(surveyId, title, description);

		// then
		verify(surveyRepository, times(1)).findById(anyLong());
	}

	@Test
	@DisplayName("설문지가 존재하지 않으면 예외가 발생한다.")
	void updateSurvey_Fail_When_Not_Found_Survey() {
		// given
		Long surveyId = 1L;
		String title = "surveyTitle";
		String description = "surveyDescription";
		when(surveyRepository.findById(anyLong())).thenThrow(new OpinionTradeException(NOT_FOUND_SURVEY));

		// when then
		assertThatExceptionOfType(OpinionTradeException.class)
			.isThrownBy(() -> surveyService.updateSurvey(surveyId, title, description))
			.withMessage(NOT_FOUND_SURVEY.getMessage());
	}

	@Test
	@DisplayName("설문지 상세 조회 포함된 질문을 함께 조회 한다.")
	void getSurvey_Detail_With_NoExceptions() {
		// given
		Survey survey = SurveyFixture.SURVEY.getInstance();
		Question question1 = QuestionFixture.PARAGRAPH.getInstance();
		Question question2 = QuestionFixture.MULTIPLE_CHOICE.getInstance();
		survey.createQuestion(question1);
		survey.createQuestion(question2);

		when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));

		// when
		SurveyResponse.GetDetail response = surveyService.getSurvey(1L);

		// then
		assertThat(response.questions().size()).isEqualTo(2);
	}
}
