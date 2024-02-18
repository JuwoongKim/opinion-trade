package com.juwoong.opiniontrade.user.domain;

import java.util.ArrayList;
import java.util.List;

import com.juwoong.opiniontrade.global.entity.TimeBaseEntity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class User extends TimeBaseEntity {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private Email email;

	@Embedded
	private Password password;

	@Embedded
	private Account account;

	@Embedded
	private ProfileInfo profileInfo;

	@Embedded
	private ActivityInfo activityInfo;

	@Embedded
	private CommerceInfo commerceInfo;

	@ElementCollection
	@CollectionTable(name = "user_friends", joinColumns = @JoinColumn(name = "user_id"))
	private List<Friend> friends = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "user_scrap_surveys", joinColumns = @JoinColumn(name = "user_id"))
	private List<ScrapSurvey> scrapSurveys = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "user_scrap_product", joinColumns = @JoinColumn(name = "user_id"))
	private List<ScrapProduct> scrapProduct = new ArrayList<>();

	protected User() {
	}

	public User(Email email, Password password) {
		this.email = email;
		this.password = password;
	}

	public void registerAccount(Account account) {
	}

	public void updateProfile(ProfileInfo profileInfo) {
	}

	public void updateActivityInfo(ActivityInfo activityInfo) {
	}

	public void updateCommerceInfo(CommerceInfo commerceInfo) {
	}

	public void addFriend(Friend friend) {
	}

	public void removeFriend(Friend friend) {
	}

	public void addScrapSurvey(ScrapSurvey scrapSurvey) {
	}

	public void removeScrapSurvey(ScrapSurvey scrapSurvey) {
	}

	public void addScrapProduct(ScrapProduct scrapProduct) {
	}

	public void removeScrapProduct(ScrapProduct scrapProduct) {
	}
}
