package com.juwoong.opiniontrade.survey.domain.repository;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.juwoong.opiniontrade.survey.domain.Creator;
import com.juwoong.opiniontrade.survey.domain.Option;
import com.juwoong.opiniontrade.survey.domain.Question;
import com.juwoong.opiniontrade.survey.domain.Survey;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SurveyRepositoryTest {

	@Autowired
	SurveyRepository surveyRepository;

	@Test
	@DisplayName("설문지 생성 테스트")
	void saveSurvey() {
		// given
		Creator creator = new Creator(1L, "juwoong");
		Survey survey = new Survey(creator, "title", "description");

		// when
		Survey createdSurvey = surveyRepository.save(survey);

		// then
		assertThat(createdSurvey.getSurveyId()).isNotNull();
	}

	@Test
	@DisplayName("설문지 질문 동일 생명 주기 확인을 위한 질문 생성 테스트")
	@Rollback(value = false)
	void createSurveyQuestion() {
		// given
		Creator creator = new Creator(1L, "juwoong");
		Survey survey = new Survey(creator, "title", "description");

		List<Option> options = List.of(
			new Option("Question_Option1"),
			new Option("Question_Option1")
		);
		Question question = new Question("title", "description", options);
		Integer questionOrder = 1;

		survey.createQuestion(questionOrder, question);

		// when
		Survey createdSurvey = surveyRepository.save(survey);

		// then
		assertThat(createdSurvey.getSurveyId()).isNotNull();
	}

	@Test
	@DisplayName("설문지 질문 동일 생명 주기 확인을 위한 질문 삭제 테스트")
	@Rollback(value = false)
	void removeSurveyQuestion() {
		// given
		Creator creator = new Creator(1L, "juwoong");
		Survey survey = new Survey(creator, "title", "description");

		List<Option> options = List.of(
			new Option("Question_Option1"),
			new Option("Question_Option1")
		);
		Question question = new Question("title", "description", options);
		Integer questionOrder = 1;

		survey.createQuestion(questionOrder, question);
		Survey createdSurvey = surveyRepository.save(survey);

		// when then
		Survey selectedSurvey = surveyRepository.findById(createdSurvey.getSurveyId()).orElseThrow();
		surveyRepository.delete(selectedSurvey);

		Assertions.assertThatCode(() -> surveyRepository.delete(selectedSurvey))
			.doesNotThrowAnyException();
	}
}
