package com.juwoong.opiniontrade.survey.domain.repository;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.juwoong.opiniontrade.global.config.JpaConfiguration;
import com.juwoong.opiniontrade.survey.domain.Question;
import com.juwoong.opiniontrade.survey.domain.Survey;
import com.juwoong.opiniontrade.survey.fixture.QuestionFixture;
import com.juwoong.opiniontrade.survey.fixture.SurveyFixture;

import jakarta.persistence.EntityManager;

@DataJpaTest
@Import(JpaConfiguration.class)
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

	@Test
	@DisplayName("날짜 내림차순 커서 기반 페이징 구현 테스트")
	void test_For_Cursor_Paging_Based_On_UpdateDate() {
		// 데이터 저장
		Survey survey = SurveyFixture.SURVEY.getInstance();
		surveyRepository.save(survey);

		Survey survey2 = SurveyFixture.SURVEY.getInstance();
		surveyRepository.save(survey2);

		Survey survey3 = SurveyFixture.SURVEY.getInstance();
		surveyRepository.save(survey3);

		Survey survey4 = SurveyFixture.SURVEY.getInstance();
		surveyRepository.save(survey4);

		// 첫번째 페이징 조회 + 디폴트 값
		Long creatorId = 1L;
		Long defaultCursorId = 0L;
		LocalDateTime defaultCursorDate = LocalDateTime.now();
		Pageable pageable = PageRequest.of(0, 2);

		Slice<Survey> surveys = surveyRepository.getSurveysByCursor(
			creatorId,
			defaultCursorId,
			defaultCursorDate,
			pageable
		);

		// 두번째 페이징 조회 + 이전 페이징 결과의 마지막 설문지
		Survey pagingLastSurvey = surveys.getContent().get(1);
		Slice<Survey> surveys2 = surveyRepository.getSurveysByCursor(
			creatorId,
			pagingLastSurvey.getId(),
			pagingLastSurvey.getUpdatedAt(),
			pageable
		);

		assertThat(surveys.getContent().get(0).getId()).isEqualTo(4L);
		assertThat(surveys.getContent().get(1).getId()).isEqualTo(3L);
		assertThat(surveys2.getContent().get(0).getId()).isEqualTo(2L);
		assertThat(surveys2.getContent().get(1).getId()).isEqualTo(1L);
		assertThat(surveys2.hasNext()).isFalse();
	}
}
