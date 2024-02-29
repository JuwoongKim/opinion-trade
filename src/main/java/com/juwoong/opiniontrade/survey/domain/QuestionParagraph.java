package com.juwoong.opiniontrade.survey.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value = "paragraph")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionParagraph extends Question {
	public QuestionParagraph(Question.Type type, QuestionInfo questionInfo) {
		super(type, questionInfo);
	}

	public static QuestionParagraph init(Question.Type type, QuestionInfo questionInfo) {
		return new QuestionParagraph(type, questionInfo);
	}
}
