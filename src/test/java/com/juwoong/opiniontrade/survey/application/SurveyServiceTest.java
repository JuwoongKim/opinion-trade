// package com.juwoong.opiniontrade.survey.application;
//
// import static org.assertj.core.api.Assertions.*;
// import static org.mockito.Mockito.*;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
//
// import com.juwoong.opiniontrade.survey.application.response.SurveyResponse;
// import com.juwoong.opiniontrade.survey.domain.Creator;
// import com.juwoong.opiniontrade.survey.domain.Survey;
// import com.juwoong.opiniontrade.survey.domain.repository.SurveyRepository;
//
// @ExtendWith(MockitoExtension.class)
// class SurveyServiceTest {
// 	@InjectMocks
// 	private SurveyService surveyService;
//
// 	@Mock
// 	private SurveyRepository surveyRepository;
//
// 	@Test
// 	@DisplayName("유저가 설문지 생성에 성공한다.")
// 	void createSurvey() {
// 		// given
// 		Creator creator = new Creator(1L, "juwoongKim");
// 		String title = "surveyTitle";
// 		String description = "surveyDescription";
// 		Survey survey = new Survey(creator, title, description);
//
// 		when(surveyRepository.save(any(Survey.class))).thenReturn(survey);
//
// 		// when
// 		SurveyResponse surveyResponse = surveyService.createSurvey(creator, title, description);
//
// 		// then
// 		verify(surveyRepository, times(1)).save(any(Survey.class));
// 		assertThat(surveyResponse.survey()).extracting("creator.nickname", "title", "description")
// 			.containsExactly(creator.getNickname(), title, description);
// 	}
// }
