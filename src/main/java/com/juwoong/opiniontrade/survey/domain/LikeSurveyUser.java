package com.juwoong.opiniontrade.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LikeSurveyUser {
	@Column(name = "like_survey_user_id")
	private Long likeSurveyUserId;

	@Column(name = "like_survey_user_name")
	private Long name;
}
