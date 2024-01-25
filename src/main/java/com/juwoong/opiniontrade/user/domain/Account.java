package com.juwoong.opiniontrade.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Account {
	@Column(name = "account")
	private String account;

	protected Account() {
	}

	public Account(String account) {
		this.account = account;
	}
}
