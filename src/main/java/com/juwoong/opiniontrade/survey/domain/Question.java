package com.juwoong.opiniontrade.survey.domain;

import java.util.List;

import org.apache.commons.lang3.function.TriFunction;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Question {
	public enum Type {
		SHORT_ANSWER((title, descriptions, options) -> new QuestionShortAnswer(title, descriptions)),
		PARAGRAPH((title, descriptions, options) -> new QuestionParagraph(title, descriptions)),
		MULTIPLE_CHOICE((title, descriptions, options) -> new QuestionMultipleChoice(title, descriptions, options)),
		CHECKBOX((title, descriptions, options) -> new QuestionCheckbox(title, descriptions, options)),
		DROPDOWN((title, descriptions, options) -> new QuestionDropdown(title, descriptions, options));

		public final TriFunction<String, String, List<Option>, Question> creator;

		Type(TriFunction<String, String, List<Option>, Question> creator) {
			this.creator = creator;
		}

		public Question create(String title, String description, List<Option> options) {
			return creator.apply(title, description, options);
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private Long id;

	@Getter
	@Column(name = "question_title")
	private String title;

	@Getter
	@Column(name = "question_description")
	private String description;

	@Getter
	@Enumerated(EnumType.STRING)
	protected Type type;

	protected Question(String title, String description) {
		this.title = title;
		this.description = description;
	}
}
