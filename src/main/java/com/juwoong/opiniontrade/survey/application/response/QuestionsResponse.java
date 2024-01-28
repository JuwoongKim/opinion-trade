package com.juwoong.opiniontrade.survey.application.response;

import java.util.Map;

import com.juwoong.opiniontrade.survey.domain.Question;

public record QuestionsResponse(
	Map<Integer, Question> questions
) {
}
