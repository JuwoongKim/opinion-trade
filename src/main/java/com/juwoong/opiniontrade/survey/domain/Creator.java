package com.juwoong.opiniontrade.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Creator {
	@Column(name = "creator_id", nullable = false)
	Long creatorId;

	@Column(name = "creator_nickname")
	String nickname;

	protected Creator() {
	}

	public Creator(Long creatorId, String nickname) {
		this.creatorId = creatorId;
		this.nickname = nickname;
	}
}
