package com.juwoong.opiniontrade.survey.api;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	void createSurvey_success() throws Exception {
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
	void createSurvey_fail_withOutOfLength() throws Exception {
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
}
