package com.juwoong.opiniontrade.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Password {
	@Column(name = "password", nullable = false)
	private String password;

	protected Password() {
	}

	public Password(String password) {
		validatePassword();
		this.password = password;
	}

	private void validatePassword() {
	}
}
