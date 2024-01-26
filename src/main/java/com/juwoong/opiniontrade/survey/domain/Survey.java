package com.juwoong.opiniontrade.survey.domain;

import java.util.HashMap;
import java.util.Map;

import com.juwoong.opiniontrade.common.entity.TimeBaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Survey extends TimeBaseEntity {
	enum SurveyStatus {
		CREATE, REQUEST, END
	}

	@Id
	@Column(name = "survey_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name = "survey_id")
	private Map<Integer, Question> questions = new HashMap<>();

	protected Survey() {
	}

	public Survey(Creator creator, String title, String description) {
		this.creator = creator;
		this.title = title;
		this.description = description;
		this.surveyStatus = SurveyStatus.CREATE;
	}

	public Long getSurveyId() {
		return id;
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

	public void createQuestion(Integer questionOrder, Question question) {
		questions.put(questionOrder, question);
	}

	public void removeQuestion(Integer removeQuestionOrder) {
	}

	public void changeQuestionOrder(Integer oneQuestionOrder, Integer anotherQuestionOrder) {
	}
}
