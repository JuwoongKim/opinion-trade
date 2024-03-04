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

@WebMvcTest(SurveyResultController.class)
class SurveyResultControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SurveyResultService surveyResultService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("설문결과 성공시 204 상태 코드와 반환값 없음을 응답 한다")
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
}
