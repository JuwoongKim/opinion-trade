package com.juwoong.opiniontrade.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Email {
	@Column(name = "email", nullable = false)
	private String email;

	protected Email() {
	}

	public Email(String email) {
		validateEmail();
		this.email = email;
	}

	private void validateEmail() {
	}
}
