package com.juwoong.opiniontrade.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Respondent {
	@Column(name = "respondentId")
	private Long respondentId;

	@Column(name = "respondent_name")
	private String name;

	public static Respondent init(Long respondentId, String name) {
		return new Respondent(respondentId, name);
	}
}
