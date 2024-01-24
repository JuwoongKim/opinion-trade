package com.juwoong.opiniontrade.user.domain;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;

@Embeddable
public class ProfileInfo {
	@Column(name = "nick_name")
	private String nickName;

	@Column(name = "introduction")
	private String introduction;

	@Column(name = "age")
	private String age;

	@Column(name = "job")
	private String job;

	@Column(name = "residence")
	private String residence;

	@ElementCollection
	@CollectionTable(name = "user_certifications", joinColumns = @JoinColumn(name = "user_id"))
	private List<Certificate> certifications;
}
