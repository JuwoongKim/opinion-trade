package com.juwoong.opiniontrade.survey.application;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.juwoong.opiniontrade.global.exception.OpinionTradeException;
import com.juwoong.opiniontrade.survey.api.request.ResultRequest;
import com.juwoong.opiniontrade.survey.application.response.SurveyResultResponse;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.domain.SurveyResult;
import com.juwoong.opiniontrade.survey.domain.repository.SurveyRepository;
import com.juwoong.opiniontrade.survey.fixture.SurveyFixture;
import com.juwoong.opiniontrade.survey.fixture.SurveyResultFixture;

@ExtendWith(MockitoExtension.class)
class SurveyResultServiceTest {
	@InjectMocks
	private SurveyResultService surveyResultService;

	@Mock
	private SurveyRepository surveyRepository;

	@Test
	@DisplayName("설문 결과 생성에 성공 한다.")
	void createSurveyResult_Success() {
		Survey survey = SurveyFixture.SURVEY.getInstance();
		when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));

		Long surveyId = 1L;
		Long respondentId = 1L;
		List<ResultRequest.Answer> answer = List.of(new ResultRequest.Answer(1L, "content"));

		surveyResultService.createSurveyResult(surveyId, respondentId, answer);

		verify(surveyRepository, times(1)).findById(anyLong());
	}

	@Test
	@DisplayName("설문 결과 수정에 성공 한다.")
	void updateSurveyResult_Success() {
		Survey survey = SurveyFixture.SURVEY.getInstance();
		SurveyResult surveyResult = SurveyResultFixture.SURVEY_RESULT.getInstance();
		survey.receiveSurveyResult(surveyResult);
		when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));

		Long surveyId = 1L;
		Long respondentId = 1L;
		List<ResultRequest.Answer> answer = List.of(new ResultRequest.Answer(1L, "updateContent"));

		surveyResultService.updateSurveyResult(surveyId, respondentId, answer);

		verify(surveyRepository, times(1)).findById(anyLong());
	}

	@Test
	@DisplayName("응답자별 설문 결과 조회에 성공한다.")
	void getSurveyResultByRespondent_Success() {
		// given
		Survey survey = SurveyFixture.SURVEY.getInstance();
		SurveyResult result = SurveyResultFixture.SURVEY_RESULT.getInstance();
		survey.receiveSurveyResult(result);

		when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));

		// when
		SurveyResultResponse.GetByRespondent response = surveyResultService.getSurveyResultByRespondent(1L, 0);

		// then
		assertAll(() -> assertThat(response.respondentId()).isEqualTo(result.getRespondent().getRespondentId()),
			() -> assertThat(response.answers().size()).isEqualTo(result.getAnswers().size()));
	}

	@Test
	@DisplayName("설문 결과 갯수 초과 요청 시 예외를 발생한다. ")
	void getSurveyResultByRespondent_Fail_With_OutOfIndex() {
		// given
		Survey survey = SurveyFixture.SURVEY.getInstance();
		SurveyResult result = SurveyResultFixture.SURVEY_RESULT.getInstance();
		survey.receiveSurveyResult(result);

		when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));

		// when then
		assertThatThrownBy(() -> surveyResultService.getSurveyResultByRespondent(1L, 3))
			.isInstanceOf(OpinionTradeException.class)
			.hasMessageContaining("범위에 없는 값을 요청했습니다.");
	}
}
