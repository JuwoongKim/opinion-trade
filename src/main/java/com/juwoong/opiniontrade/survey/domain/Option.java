package com.juwoong.opiniontrade.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Option {
	@Column(name = "question_option")
	private String description;

	protected Option() {
	}

	public Option(String description) {
		this.description = description;
	}
}
