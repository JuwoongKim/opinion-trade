package com.juwoong.opiniontrade.user.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juwoong.opiniontrade.user.application.response.FriendResponse;
import com.juwoong.opiniontrade.user.application.response.ScrapProductResponse;
import com.juwoong.opiniontrade.user.application.response.ScrapSurveyResponse;
import com.juwoong.opiniontrade.user.domain.Friend;
import com.juwoong.opiniontrade.user.domain.ScrapProduct;
import com.juwoong.opiniontrade.user.domain.ScrapSurvey;
import com.juwoong.opiniontrade.user.domain.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserMyService {
	private final UserRepository userRepository;

	public UserMyService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<FriendResponse> getFriends(Long userId) {
		List<Friend> friends = List.of(new Friend(1L, "friend1"), new Friend(2L, "friend2"), new Friend(3L, "friend3"));

		List<FriendResponse> friendResponses = friends.stream().map(friend -> new FriendResponse(friend)).toList();
		return friendResponses;
	}

	@Transactional
	public FriendResponse addFriend(Long userId, Friend friend) {
		// find(userId);
		// user.addFriend(friend);

		return new FriendResponse(friend);
	}

	@Transactional
	public void removeFriend(Long userId, Long friendId) {
		// find(userId);
		// user.removeFriend(friendId);
	}

	public List<ScrapSurveyResponse> getScrapSurveys(Long userId) {
		List<ScrapSurvey> scrapSurveys = List.of(
			new ScrapSurvey(1L, "title1", "description1"),
			new ScrapSurvey(2L, "title2", "description2"),
			new ScrapSurvey(3L, "title3", "description3")
		);

		List<ScrapSurveyResponse> scrapSurveyResponses = scrapSurveys.stream()
			.map(scrapSurvey -> new ScrapSurveyResponse(scrapSurvey))
			.toList();

		return scrapSurveyResponses;
	}

	@Transactional
	public ScrapSurveyResponse addScrapSurvey(Long userId, ScrapSurvey scrapSurvey) {
		// find(userId);
		// user.addScrapSurvey(scrapSurvey);

		return new ScrapSurveyResponse(scrapSurvey);
	}

	@Transactional
	public void removeScrapSurvey(Long userId, Long scrapSurveyId) {
		// find(userId);
		// user.removeScrapSurvey(scrapSurveyId);
	}

	public List<ScrapProductResponse> getScrapProducts(Long userId) {
		List<ScrapProduct> scrapProducts = List.of(
			new ScrapProduct(1L, "title1", 100L),
			new ScrapProduct(2L, "title2", 100L),
			new ScrapProduct(3L, "title3", 100L)
		);

		List<ScrapProductResponse> scrapProductResponses = scrapProducts.stream()
			.map(scrapProduct -> new ScrapProductResponse(scrapProduct))
			.toList();

		return scrapProductResponses;
	}

	@Transactional
	public ScrapProductResponse addScrapProduct(Long userId, ScrapProduct scrapProduct) {
		//
		return new ScrapProductResponse(scrapProduct);
	}

	@Transactional
	public void removeScrapProduct(Long userId, Long scrapProductId) {
	}
}
