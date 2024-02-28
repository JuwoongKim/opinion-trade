package com.juwoong.opiniontrade.survey.domain;

import java.util.List;

import com.juwoong.opiniontrade.global.entity.TimeBaseEntity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "survey-results")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyResult extends TimeBaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "survey_result_id")
	private Long id;

	@Embedded
	private Respondent respondent;

	@ElementCollection
	@CollectionTable(name = "survey_result_answers", joinColumns = @JoinColumn(name = "survey_result_id"))
	private List<Answer> answers;

	public static SurveyResult init(Respondent respondent, List<Answer> answers) {
		return new SurveyResult(null, respondent, answers);
	}

	public void createAnswer(Answer answer) {
		answers.add(answer);
	}
}
