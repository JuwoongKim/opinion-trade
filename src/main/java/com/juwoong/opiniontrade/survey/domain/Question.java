package com.juwoong.opiniontrade.survey.domain;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private Long id;

	@Column(name = "question_title")
	private String title;

	@Column(name = "question_description")
	private String description;

	@ElementCollection
	@CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
	private List<Option> options;

	protected Question() {
	}

	public Question(String title, String description, List<Option> options) {
		this.title = title;
		this.description = description;
		this.options = options;
	}
}
