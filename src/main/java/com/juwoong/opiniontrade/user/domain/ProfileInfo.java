package com.juwoong.opiniontrade.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileInfo {
	@Column(name = "nick_name")
	private String nickName;

	@Column(name = "introduction")
	private String introduction;

	@Column(name = "age")
	private Long age;

	public static ProfileInfo init(String nickName, String introduction, Long age) {
		return new ProfileInfo(nickName, introduction, age);
	}
}
