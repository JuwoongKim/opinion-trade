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
import com.juwoong.opiniontrade.survey.domain.Creator;
import com.juwoong.opiniontrade.survey.domain.Survey;

@WebMvcTest(SurveyController.class)
class SurveyControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SurveyService surveyService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void createSurvey() throws Exception {
		// given
		Creator creator = new Creator(1L, "juwoongKim");
		String title = "surveyTitle";
		String description = "surveyDescription";

		String request = objectMapper.writeValueAsString(new SurveyRequest(creator, title, description));
		SurveyResponse surveyResponse = new SurveyResponse(new Survey(creator, title, description));

		when(surveyService.createSurvey(any(Creator.class), anyString(), anyString())).thenReturn(surveyResponse);

		// when then
		mockMvc.perform(post("/surveys")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("survey.creator.creatorId").value(1))
			.andExpect(jsonPath("survey.creator.nickname").value("juwoongKim"))
			.andExpect(jsonPath("survey.title").value("surveyTitle"))
			.andExpect(jsonPath("survey.description").value("surveyDescription"));

		SurveyResponse survey = verify(surveyService, times(1)).createSurvey(any(Creator.class), anyString(),
			anyString());
	}
}
