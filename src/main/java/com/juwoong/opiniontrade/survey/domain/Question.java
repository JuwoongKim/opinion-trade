package com.juwoong.opiniontrade.survey.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "questions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Question {
	public enum Type {
		PARAGRAPH, MULTIPLE_CHOICE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private Long id;

	@Embedded
	private QuestionInfo questionInfo;

	@Enumerated(EnumType.STRING)
	private Type type;

	protected Question(Type type, QuestionInfo questionInfo) {
		this(null, questionInfo, type);
	}

	public static Question init(Type type, QuestionInfo questionInfo, List<Option> options) {
		return switch (type) {
			case PARAGRAPH -> QuestionParagraph.init(type, questionInfo);
			case MULTIPLE_CHOICE -> QuestionMultipleChoice.init(type, questionInfo, options);
		};
	}
}
