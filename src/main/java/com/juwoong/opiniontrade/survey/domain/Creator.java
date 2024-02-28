package com.juwoong.opiniontrade.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Creator {
	@Column(name = "creator_id", nullable = false)
	private  Long creatorId;

	@Column(name = "creator_nickname")
	private String nickname;

	public static Creator init(Long creatorId, String nickname) {
		return new Creator(creatorId, nickname);
	}
}
