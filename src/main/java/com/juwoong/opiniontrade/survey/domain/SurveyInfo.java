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
public class SurveyInfo {
	@Column(name = "survey_title")
	private String title;

	@Column(name = "survey_description")
	private String description;

	public static SurveyInfo init(String title, String description) {
		return new SurveyInfo(title, description);
	}
}
