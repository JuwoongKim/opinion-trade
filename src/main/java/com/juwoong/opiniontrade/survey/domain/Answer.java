package com.juwoong.opiniontrade.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
	@Column(name = "question_id")
	private Long questionId;

	@Column(name = "content")
	private String content;

	public static Answer init(Long questionId, String content) {
		return new Answer(questionId, content);
	}
}
