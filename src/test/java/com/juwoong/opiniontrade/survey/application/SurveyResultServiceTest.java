package com.juwoong.opiniontrade.survey.application;

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

import com.juwoong.opiniontrade.survey.api.request.ResultRequest;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.domain.repository.SurveyRepository;
import com.juwoong.opiniontrade.survey.fixture.SurveyFixture;

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
		List<ResultRequest.Answer> answer = List.of(
			new ResultRequest.Answer(1L, "content")
		);

		surveyResultService.createSurveyResult(surveyId, respondentId, answer);

		verify(surveyRepository, times(1)).findById(anyLong());
	}
}
