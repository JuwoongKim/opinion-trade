package com.juwoong.opiniontrade.survey.api;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juwoong.opiniontrade.survey.api.request.ResultRequest;
import com.juwoong.opiniontrade.survey.application.SurveyResultService;
import com.juwoong.opiniontrade.survey.application.response.SurveyResultResponse;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.domain.SurveyResult;
import com.juwoong.opiniontrade.survey.fixture.SurveyFixture;
import com.juwoong.opiniontrade.survey.fixture.SurveyResultFixture;

@WebMvcTest(SurveyResultController.class)
class SurveyResultControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SurveyResultService surveyResultService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("설문결과 생성 성공시 201 상태 코드와 반환값 없음을 응답 한다")
	void createSurveyResult_Success() throws Exception {
		Long surveyId = 1L;
		Long respondentId = 1L;
		List<ResultRequest.Answer> answer = List.of(new ResultRequest.Answer(1L, "content"));
		ResultRequest.Create request = new ResultRequest.Create(respondentId, answer);

		doNothing().when(surveyResultService).createSurveyResult(anyLong(), anyLong(), anyList());

		ResultActions result = mockMvc.perform(
			post("/surveys/{surveyId}/surveyResult", surveyId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)));

		result.andExpect(status().isCreated());

		verify(surveyResultService, times(1)).createSurveyResult(anyLong(), anyLong(), anyList());
	}

	@Test
	@DisplayName("설문결과 생성 성공시 204 상태 코드와 반환값 없음을 응답 한다")
	void updateSurveyResult_Success() throws Exception {
		Long surveyId = 1L;
		Long respondentId = 1L;
		List<ResultRequest.Answer> answer = List.of(new ResultRequest.Answer(1L, "content"));
		ResultRequest.Update request = new ResultRequest.Update(respondentId, answer);

		doNothing().when(surveyResultService).updateSurveyResult(anyLong(), anyLong(), anyList());

		ResultActions result = mockMvc.perform(
			put("/surveys/{surveyId}/surveyResult", surveyId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)));

		result.andExpect(status().isNoContent());

		verify(surveyResultService, times(1)).updateSurveyResult(anyLong(), anyLong(), anyList());
	}

	@Test
	@DisplayName("응답자 기준 설문결과 조회시 200 상태 코드와 반환값을 응답 한다")
	void getSurveyResultByRespondent_Success() throws Exception {
		// given
		Long surveyId = 1L;
		SurveyResult surveyResult = SurveyResultFixture.SURVEY_RESULT.getInstance();
		Integer totalRespondentSize = 10;
		Integer currentIndex = 1;

		SurveyResultResponse.GetByRespondent response = new SurveyResultResponse.GetByRespondent(totalRespondentSize,
			currentIndex, surveyResult);

		when(surveyResultService.getSurveyResultByRespondent(anyLong(), anyInt())).thenReturn(response);

		// when
		ResultActions result = mockMvc.perform(
			get("/surveys/{surveyId}/surveyResult/byRespondent", surveyId)
				.param("nextRespondent", currentIndex.toString())
				.accept(MediaType.APPLICATION_JSON)
		);

		// then
		result.andExpect(status().isOk())
			.andExpect(jsonPath("$.totalRespondents").value(totalRespondentSize))
			.andExpect(jsonPath("$.currentRespondent").value(currentIndex))
			.andExpect(jsonPath("$.respondentId").value(surveyResult.getRespondent().getRespondentId()))
			.andExpect(jsonPath("$.answers[0].questionId").value(surveyResult.getAnswers().get(0).getQuestionId()))
			.andExpect(jsonPath("$.answers[0].content").value(surveyResult.getAnswers().get(0).getContent()));
	}
}
