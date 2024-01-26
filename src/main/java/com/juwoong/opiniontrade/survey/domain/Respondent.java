package com.juwoong.opiniontrade.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Respondent {
	@Column(name = "respondentId")
	private Long respondentId;

	@Column(name = "respondent_name")
	private String name;

	protected Respondent() {
	}

	public Respondent(Long respondentId, String name) {
		this.respondentId = respondentId;
		this.name = name;
	}
}
