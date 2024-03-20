package com.juwoong.opiniontrade.survey.fixture;

import java.util.List;

import com.juwoong.opiniontrade.survey.domain.Answer;
import com.juwoong.opiniontrade.survey.domain.Respondent;
import com.juwoong.opiniontrade.survey.domain.SurveyResult;

public enum SurveyResultFixture {
	SURVEY_RESULT(1l, 1l, "content"),
	SURVEY_RESULT_WITH_ANSWER_FOR_QUESTION_ID_ONE(1l, 1l, "content"),
	SURVEY_RESULT_WITH_ANSWER_FOR_QUESTION_ID_TWO(1l, 2l, "content");
	private Long respondentId;
	private Long questionId;
	private String answerContent;

	public SurveyResult getInstance() {
		Respondent respondent = Respondent.init(respondentId);
		Answer answer = Answer.init(questionId, answerContent);
		return SurveyResult.init(respondent, List.of(answer));
	}

	SurveyResultFixture(Long respondentId, Long questionId, String answerContent) {
		this.respondentId = respondentId;
		this.questionId = questionId;
		this.answerContent = answerContent;
	}
}
