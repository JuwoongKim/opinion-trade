package com.juwoong.opiniontrade.survey.domain;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value = "multiple_choice")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionMultipleChoice extends Question {
	@Getter
	@ElementCollection
	@CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
	private List<Option> options;

	private QuestionMultipleChoice(Question.Type type, QuestionInfo questionInfo, List<Option> options) {
		super(type, questionInfo);
		this.options = options;
	}

	public static QuestionMultipleChoice init(Question.Type type, QuestionInfo questionInfo, List<Option> options) {
		return new QuestionMultipleChoice(type, questionInfo, options);
	}
}
