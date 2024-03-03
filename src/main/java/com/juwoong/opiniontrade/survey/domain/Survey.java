package com.juwoong.opiniontrade.survey.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.juwoong.opiniontrade.global.entity.TimeBaseEntity;

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
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "surveys")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Survey extends TimeBaseEntity {
	enum SurveyStatus {CREATE, REQUEST, SALE}

	@Id
	@Column(name = "survey_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private Creator creator;

	@Embedded
	private SurveyInfo surveyInfo;

	@Column(name = "survey_status")
	@Enumerated(value = EnumType.STRING)
	private SurveyStatus surveyStatus;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name = "survey_id")
	private final List<Question> questions = new LinkedList<>();

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name = "survey_id")
	private final List<SurveyResult> surveyResults = new ArrayList<>();

	private Survey(Creator creator, SurveyInfo surveyInfo, SurveyStatus surveyStatus) {
		this(null, creator, surveyInfo, surveyStatus);
	}

	public static Survey init(Creator creator, SurveyInfo surveyInfo) {
		return new Survey(creator, surveyInfo, SurveyStatus.CREATE);
	}

	public void update(SurveyInfo surveyInfo) {
		this.surveyInfo = surveyInfo;
	}

	public void createQuestion(Question question) {
		questions.add(question);
	}

	public void updateQuestion(Integer order, Question question) {
		Integer index = order - 1;
		questions.set(index, question);
	}

	public void changeQuestionOrder(Integer oneOrder, Integer anotherOrder) {
	}

	public void removeQuestion(Integer order) {
		Integer index = order - 1;
		questions.remove(index);
	}

	public void receiveSurveyResult(SurveyResult surveyResult) {
		surveyResults.add(surveyResult);
	}

	public SurveyResult getSurveyResultByRespondent(Respondent respondent) {
		return null;
	}

	void updateSurveyResultAnswer(Respondent respondent, Answer answer) {
	}
}
