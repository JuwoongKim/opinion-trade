package com.juwoong.opiniontrade.survey.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.juwoong.opiniontrade.survey.application.response.SurveyResponse;
import com.juwoong.opiniontrade.survey.domain.Creator;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.domain.SurveyInfo;
import com.juwoong.opiniontrade.survey.domain.repository.SurveyRepository;

@ExtendWith(MockitoExtension.class)
class SurveyServiceTest {
	@InjectMocks
	private SurveyService surveyService;

	@Mock
	private SurveyRepository surveyRepository;

	@Test
	@DisplayName("유저가 설문지 생성에 성공한다.")
	void createSurvey() {
		// given
		Long creatorId = 1L;
		String title = "surveyTitle";
		String description = "surveyDescription";
		Creator creator = Creator.init(creatorId);
		SurveyInfo surveyInfo = SurveyInfo.init(title, description);

		Survey survey = Survey.init(creator, surveyInfo);
		when(surveyRepository.save(any())).thenReturn(survey);

		// when
		SurveyResponse surveyResponse = surveyService.createSurvey(creatorId, title, description);

		// then
		verify(surveyRepository, times(1)).save(any(Survey.class));
	}
}
