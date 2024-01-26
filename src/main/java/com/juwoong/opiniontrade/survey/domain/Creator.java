package com.juwoong.opiniontrade.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Creator {
	@Column(name = "creator_id", nullable = false)
	Long creatorId;

	@Column(name = "creator_nickname")
	String nickName;

	protected Creator() {
	}

	public Creator(Long creatorId, String nickName) {
		this.creatorId = creatorId;
		this.nickName = nickName;
	}
}
