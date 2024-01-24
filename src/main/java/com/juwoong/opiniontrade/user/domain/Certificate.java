package com.juwoong.opiniontrade.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Certificate {
	@Column(name = "certificate_name")
	private String certificateName;

	protected Certificate() {
	}

	public Certificate(String certificateName) {
		this.certificateName = certificateName;
	}
}
