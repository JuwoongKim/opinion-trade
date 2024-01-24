package com.juwoong.opiniontrade.user.domain;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Account {
	@Column(name = "account", nullable = false)
	@ColumnDefault("")
	private String account;

	protected Account() {
	}

	public Account(String account) {
		this.account = account;
	}
}
