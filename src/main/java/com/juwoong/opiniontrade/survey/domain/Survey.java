package com.juwoong.opiniontrade.survey.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juwoong.opiniontrade.global.entity.TimeBaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Entity
public class Survey extends TimeBaseEntity {
	enum SurveyStatus {
		CREATE, REQUEST, END
	}

	@Id
	@Column(name = "survey_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Column(name = "survey_title")
	private String title;

	@Getter
	@Column(name = "survey_description")
	private String description;

	@Column(name = "category_id")
	private Long categoryId;

	@Getter
	@Column(name = "survey_status")
	@Enumerated(value = EnumType.STRING)
	private SurveyStatus surveyStatus;

	@Getter
	@Embedded
	private Creator creator;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name = "survey_id")
	private Map<Integer, Question> questions = new HashMap<>();

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name = "survey_id")
	private List<SurveyResult> surveyResults = new ArrayList<>();

	@Embedded
	private RequestInfo requestInfo;

	@Getter
	@Column(name = "view_count")
	private Long viewCount;

	@ElementCollection
	@CollectionTable(name = "like_survey_users", joinColumns = @JoinColumn(name = "survey_id"))
	private List<LikeSurveyUser> likeSurveyUsers = new ArrayList<>();

	protected Survey() {
	}

	public Survey(Creator creator, String title, String description) {
		this.creator = creator;
		this.title = title;
		this.description = description;
		this.surveyStatus = SurveyStatus.CREATE;
		this.viewCount = 0L;
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

	public Map<Integer, Question> findQuestionWithOrder() {
		return questions;
	}

	public void receiveSurveyResult(SurveyResult surveyResult) {
		surveyResults.add(surveyResult);
	}

	public void updateSurveyResult(Respondent respondent, SurveyResult surveyResult) {
	}

	public List<SurveyResult> findSurveyResult() {
		return surveyResults;
	}

	public void updateRequestInfo(RequestInfo requestInfo) {
	}

	public void checkEndSatisfiedCondition(Long answerCount, LocalDateTime currentTime) {
	}

	public void addLikeSurveyUser(LikeSurveyUser likeSurveyUser) {
	}

	public void subtractLikeSurveyUser(LikeSurveyUser likeSurveyUser) {
	}

	public void getLikeCount() {
	}

	public List<LikeSurveyUser> getLikeSurveyUser() {
		return List.of();
	}
}
