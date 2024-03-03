package com.juwoong.opiniontrade.survey.api;

import static com.juwoong.opiniontrade.global.exception.ErrorCode.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juwoong.opiniontrade.global.exception.OpinionTradeException;
import com.juwoong.opiniontrade.survey.api.request.SurveyRequest;
import com.juwoong.opiniontrade.survey.application.SurveyService;
import com.juwoong.opiniontrade.survey.application.response.SurveyResponse;

@WebMvcTest(SurveyController.class)
class SurveyControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SurveyService surveyService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("설문지 생성에 성공 시 201 상태코드, 설문지 식별자 응답값을 반환한다.")
	void createSurvey_Success() throws Exception {
		// given
		Long creatorId = 1L;
		String title = "surveyTitle";
		String description = "surveyDescription";
		SurveyRequest.Create request = new SurveyRequest.Create(creatorId, title, description);

		Long createdSurveyId = 1L;
		SurveyResponse.Create response = (new SurveyResponse.Create(creatorId));

		when(surveyService.createSurvey(anyLong(), anyString(), anyString())).thenReturn(response);

		// when then
		mockMvc.perform(post("/surveys")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("id").value(createdSurveyId));

		verify(surveyService, times(1)).createSurvey(anyLong(), anyString(),
			anyString());
	}

	@Test
	@DisplayName("사용자 입력값 오류로 인해 설문지 생성 실패 시 400 상태코드, BAD_REQUEST 메세지를 반환한다.")
	void createSurvey_Fail_WithOutOfLength() throws Exception {
		// given
		Long creatorId = 1L;
		String title = "0000000000/0000000000/0000000000/0000000000/0000000000/";
		String description = "surveyDescription";
		SurveyRequest.Create request = new SurveyRequest.Create(creatorId, title, description);

		// when then
		var resultActions = mockMvc.perform(post("/surveys")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
			.accept(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isBadRequest())
			.andExpect(jsonPath("code").value("BAD_REQUEST"));
	}

	@Test
	@DisplayName("설문지 수정 성공 시 204 상태코드와 응답값 없음을 반환한다.")
	void updateSurvey_Success() throws Exception {
		// given
		Long surveyId = 1L;
		String title = "surveyTitle";
		String description = "surveyDescription";
		SurveyRequest.Update request = new SurveyRequest.Update(title, description);

		doNothing().when(surveyService).updateSurvey(surveyId, title, description);

		// when then
		ResultActions result = mockMvc.perform(put("/surveys/{id}", surveyId)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
		);

		result.andExpect(status().isNoContent());
	}

	@Test
	@DisplayName("수정할 설문지가 존재하지 않으면 404 에러가 발생한다.")
	void updateSurvey_Fail_When_No_Survey() throws Exception {
		Long surveyId = 1L;
		String title = "surveyTitle";
		String description = "surveyDescription";
		SurveyRequest.Update request = new SurveyRequest.Update(title, description);

		doThrow(new OpinionTradeException(NOT_FOUND_SURVEY))
			.when(surveyService).updateSurvey(eq(surveyId), eq(title), eq(description));

		// when then
		ResultActions result = mockMvc.perform(put("/surveys/{id}", surveyId)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
		);

		result.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.code").value(NOT_FOUND_SURVEY.getHttpStatus().name()))
			.andExpect(jsonPath("$.message").value(NOT_FOUND_SURVEY.getMessage()));
	}
}
