package com.juwoong.opiniontrade.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionInfo {
	@Column(name = "question_title")
	private String title;

	@Column(name = "question_description")
	private String description;

	public static QuestionInfo init(String title, String description) {
		return new QuestionInfo(title, description);
	}
}
