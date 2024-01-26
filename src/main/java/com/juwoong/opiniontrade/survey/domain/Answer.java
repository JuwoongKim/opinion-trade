package com.juwoong.opiniontrade.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Answer {
	@Column(name = "question_id")
	private Long questionId;

	@Column(name = "content")
	private String content;

	protected Answer() {
	}

	public Answer(Long questionId, String content) {
		this.questionId = questionId;
		this.content = content;
	}
}
