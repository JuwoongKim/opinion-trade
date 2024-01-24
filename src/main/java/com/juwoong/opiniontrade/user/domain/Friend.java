package com.juwoong.opiniontrade.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public class Friend {

	@Column(name = "firend_id", nullable = false)
	private String friendId;

	@Column(name = "friend_name", nullable = false)
	private String friendName;

	protected Friend() {
	}

	public Friend(String friendId, String friendName) {
		this.friendId = friendId;
		this.friendName = friendName;
	}
}
