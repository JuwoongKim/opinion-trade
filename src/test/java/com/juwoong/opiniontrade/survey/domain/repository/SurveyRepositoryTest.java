package com.juwoong.opiniontrade.survey.domain.repository;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.juwoong.opiniontrade.survey.domain.Question;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.fixture.QuestionFixture;
import com.juwoong.opiniontrade.survey.fixture.SurveyFixture;

import jakarta.persistence.EntityManager;

@DataJpaTest
	// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SurveyRepositoryTest {

	@Autowired
	SurveyRepository surveyRepository;

	@Autowired
	EntityManager entityManager;

	@Test
	@DisplayName("설문지 도메인 저장을 성공 한다.")
	void saveSurvey_Success() {
		// given
		Survey instance = SurveyFixture.SURVEY.getInstance();

		// when
		Survey save = surveyRepository.save(instance);

		// then
		assertThat(save.getId()).isNotNull();
	}

	@Test
	@DisplayName("설문지에 질문을 추가하면 질문 정보가 함께 저장되고 함께 조회된다.")
	void save_and_select_Question_With_Survey() {
		// given
		Survey survey = SurveyFixture.SURVEY.getInstance();
		Question question = QuestionFixture.PARAGRAPH.getInstance();
		survey.createQuestion(question);

		// when
		Survey saved = surveyRepository.save(survey);

		// then
		assertThat(saved.getQuestions().size()).isEqualTo(1);
	}
}
