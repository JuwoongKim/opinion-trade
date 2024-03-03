package com.juwoong.opiniontrade.survey.domain;

import static com.juwoong.opiniontrade.survey.fixture.QuestionFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.juwoong.opiniontrade.survey.fixture.QuestionFixture;
import com.juwoong.opiniontrade.survey.fixture.SurveyFixture;

class SurveyTest {
	@Test
	@DisplayName("특정 번호를 통해 질문을 삭제하면 뒤의 질문 순서가 순서대로 채워진다.")
	void subsequentQuestion_Filled_Sequentially_When_Specific_Question_Is_Deleted() {
		// given
		Survey survey = SurveyFixture.SURVEY.getInstance();

		List<Question> questions = List.of(
			QuestionFixture.PARAGRAPH.getInstance(),
			QuestionFixture.PARAGRAPH.getInstance(),
			MULTIPLE_CHOICE.getInstance(),
			QuestionFixture.PARAGRAPH.getInstance());
		questions.forEach(question -> survey.createQuestion(question));

		// when
		survey.removeQuestion(2);

		// then
		assertThat(survey.getQuestions().get(2).getType().name()).isEqualTo(MULTIPLE_CHOICE.name());
	}
}
