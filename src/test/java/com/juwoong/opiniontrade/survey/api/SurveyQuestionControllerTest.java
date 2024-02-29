package com.juwoong.opiniontrade.survey.api;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juwoong.opiniontrade.survey.api.request.QuestionRequest;
import com.juwoong.opiniontrade.survey.application.SurveyQuestionService;
import com.juwoong.opiniontrade.survey.domain.Option;
import com.juwoong.opiniontrade.survey.domain.Question;

@WebMvcTest(SurveyQuestionController.class)
class SurveyQuestionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SurveyQuestionService surveyQuestionService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void createQuestion() throws Exception {
		// given
		Long surveyId = 1L;
		Integer questionOrder = 1;
		String title = "questionTitle";
		String description = "questionDescription";
		Question.Type type = Question.Type.MULTIPLE_CHOICE;
		List<Option> options = List.of(Option.init("Option1"), Option.init("Option2"));

		String request = objectMapper.writeValueAsString(
			new QuestionRequest.Create(
				questionOrder,
				title,
				description,
				type,
				options
			)
		);

		doNothing().when(surveyQuestionService).createQuestion(
			anyLong(),
			anyInt(),
			any(),
			anyString(),
			anyString(),
			anyList()
		);

		// when then
		mockMvc.perform(post("/surveys/1/questions")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());

		verify(surveyQuestionService, times(1)).createQuestion(
			anyLong(),
			anyInt(),
			any(),
			anyString(),
			anyString(),
			anyList()
		);
	}
}
