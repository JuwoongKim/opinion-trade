package com.juwoong.opiniontrade.survey.api.request;

import java.util.List;

import com.juwoong.opiniontrade.survey.domain.Option;
import com.juwoong.opiniontrade.survey.domain.Question;

public record QuestionRequest(
	Integer questionOrder,
	String title,
	String description,
	Question.Type type,
	List<Option> options
) {
}
