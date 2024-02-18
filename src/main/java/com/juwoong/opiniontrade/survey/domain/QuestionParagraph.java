package com.juwoong.opiniontrade.survey.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

@Entity
@DiscriminatorValue(value = "paragraph")
@AllArgsConstructor
public class QuestionParagraph extends Question {
	public QuestionParagraph(String title, String description) {
		super(title, description);
		this.type = Type.PARAGRAPH;
	}
}
