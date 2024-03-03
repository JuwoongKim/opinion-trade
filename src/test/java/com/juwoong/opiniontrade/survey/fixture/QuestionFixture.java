package com.juwoong.opiniontrade.survey.fixture;

import java.util.List;

import com.juwoong.opiniontrade.survey.domain.Option;
import com.juwoong.opiniontrade.survey.domain.Question;
import com.juwoong.opiniontrade.survey.domain.QuestionInfo;

public enum QuestionFixture {
	MULTIPLE_CHOICE(Question.Type.MULTIPLE_CHOICE, "title", "MULTIPLE_CHOICE", List.of("Red", "Blue", "greem")),
	PARAGRAPH(Question.Type.PARAGRAPH, "title", "PARAGRAPH", List.of());

	private Question.Type type;
	private String title;
	private String description;
	private List<String> optionNames;

	QuestionFixture(Question.Type type, String title, String description, List<String> optionNames) {
		this.type = type;
		this.title = title;
		this.description = description;
		this.optionNames = optionNames;
	}

	public Question getInstance() {
		QuestionInfo questionInfo = QuestionInfo.init(title, description);
		List<Option> options = optionNames.stream().map(optionName -> Option.init(optionName)).toList();
		return Question.init(type, questionInfo, options);
	}
}
