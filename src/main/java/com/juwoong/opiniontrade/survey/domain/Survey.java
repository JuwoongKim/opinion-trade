package com.juwoong.opiniontrade.survey.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Map<Integer, Question> questions = new HashMap<>();

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name = "survey_id")
	private List<SurveyResult> surveyResults = new ArrayList<>();

	private Survey(Creator creator, SurveyInfo surveyInfo, SurveyStatus surveyStatus) {
		this(null, creator, surveyInfo, surveyStatus, null, null);
	}

	public static Survey init(Creator creator, SurveyInfo surveyInfo) {
		return new Survey(creator, surveyInfo, SurveyStatus.CREATE);
	}

	public void createQuestion(Integer order, Question question) {
		questions.put(order, question);
	}

	public void changeQuestionOrder(Integer oneOrder, Integer anotherOrder) {
	}

	public void removeQuestion(Integer removeQuestionOrder) {
	}

	public void deleteQuestion(Integer order) {
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
