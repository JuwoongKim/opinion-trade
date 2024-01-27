package com.juwoong.opiniontrade.survey.api.request;

import java.util.List;

import com.juwoong.opiniontrade.survey.domain.Option;

public record QuestionRequest(
	Integer questionOrder,
	String title,
	String description,
	List<Option> options
) {
}
