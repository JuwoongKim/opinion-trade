package com.juwoong.opiniontrade.survey.domain;

import java.util.List;

import com.juwoong.opiniontrade.common.entity.TimeBaseEntity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
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

	protected SurveyResult() {
	}

	public SurveyResult(Respondent respondent, List<Answer> answers) {
		this.respondent = respondent;
		this.answers = answers;
	}
}
