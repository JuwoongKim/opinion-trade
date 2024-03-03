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
		String title = "questionTitle";
		String description = "questionDescription";
		Question.Type type = Question.Type.MULTIPLE_CHOICE;
		List<Option> options = List.of(Option.init("Option1"), Option.init("Option2"));

		String request = objectMapper.writeValueAsString(
			new QuestionRequest.Create(
				title,
				description,
				type,
				options
			)
		);

		doNothing().when(surveyQuestionService).createQuestion(
			anyLong(),
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
			any(),
			anyString(),
			anyString(),
			anyList()
		);
	}

	@Test
	@DisplayName("질문 삭제 성공시 204 상태 코드와 반환값 없음을 응답한다.")
	void deleteQuestion_Success_With_NoExceptions() throws Exception {
		Long surveyId = 1L;
		Integer questionOrder = 1;
		QuestionRequest.Delete request = new QuestionRequest.Delete(questionOrder);

		ResultActions result = mockMvc.perform(delete("/surveys/{surveyId}/questions", surveyId)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request)));

		result.andExpect(status().isNoContent());
	}

	@Test
	@DisplayName("질문 순서 요청 값이 1 미만일 때 400 상태 코드와 오류 반환 값을 응답 한다.")
	void deleteQuestion_Fail_When_QuestionNumber_Request_Is_Smaller_Than_One()throws Exception{
		Long surveyId = 1L;
		Integer questionOrder = 0;
		QuestionRequest.Delete request = new QuestionRequest.Delete(questionOrder);

		ResultActions result = mockMvc.perform(delete("/surveys/{surveyId}/questions", surveyId)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request)));

		result.andExpect(status().isBadRequest());
	}
}
