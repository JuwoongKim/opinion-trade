package com.juwoong.opiniontrade.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Friend {

	@Column(name = "firend_id", nullable = false)
	private Long friendId;

	@Column(name = "friend_name", nullable = false)
	private String friendName;

	protected Friend() {
	}

	public Friend(Long friendId, String friendName) {
		this.friendId = friendId;
		this.friendName = friendName;
	}
}
