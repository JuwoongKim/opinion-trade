package com.juwoong.opiniontrade.survey.domain;

import com.juwoong.opiniontrade.common.entity.TimeBaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Survey extends TimeBaseEntity {
	enum SurveyStatus {
		CREATE, REQUEST, END;
	}

	@Id
	@Column(name = "survey_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long surveyId;

	@Column(name = "survey_title")
	private String title;

	@Column(name = "survey_description")
	private String description;

	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "survey_status")
	@Enumerated(value = EnumType.STRING)
	private SurveyStatus surveyStatus;

	@Embedded
	private Creator creator;

	@Column(name = "view_count")
	private Long viewCount;

	protected Survey(){
	}

	public Survey(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public void updateTitle() {
	}

	public void updateDescription() {
	}

	public void requestSurvey() {
	}

	public void cancelSurvey() {
	}

	public void finishSurvey() {
	}

	public void addViewCount() {
	}

	public void subtractViewCount() {
	}
}
