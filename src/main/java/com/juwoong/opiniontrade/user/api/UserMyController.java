package com.juwoong.opiniontrade.user.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juwoong.opiniontrade.user.api.request.FriendRequest;
import com.juwoong.opiniontrade.user.api.request.ScrapProductRequest;
import com.juwoong.opiniontrade.user.api.request.ScrapSurveyRequest;
import com.juwoong.opiniontrade.user.application.UserMyService;
import com.juwoong.opiniontrade.user.application.response.FriendResponse;
import com.juwoong.opiniontrade.user.application.response.ScrapProductResponse;
import com.juwoong.opiniontrade.user.application.response.ScrapSurveyResponse;
import com.juwoong.opiniontrade.user.domain.Friend;
import com.juwoong.opiniontrade.user.domain.ScrapProduct;
import com.juwoong.opiniontrade.user.domain.ScrapSurvey;

@RestController
@RequestMapping("/users/my")
public class UserMyController {
	private final UserMyService userMyService;

	public UserMyController(UserMyService userMyService) {
		this.userMyService = userMyService;
	}

	@GetMapping("/{userId}/friends")
	public ResponseEntity<List<FriendResponse>> getFriends(@PathVariable Long userId) {
		List<FriendResponse> friendResponses = userMyService.getFriends(userId);
		return ResponseEntity.ok(friendResponses);
	}

	@PostMapping("/{userId}/friends")
	public ResponseEntity<FriendResponse> addFriend(
		@PathVariable Long userId,
		@RequestBody FriendRequest friendRequest
	) {
		Friend friend = friendRequest.friend();
		FriendResponse friendResponse = userMyService.addFriend(userId, friend);

		return ResponseEntity.ok(friendResponse);
	}

	@DeleteMapping("/{userId}/friends/{friendId}")
	public ResponseEntity<Void> removeFriend(
		@PathVariable Long userId,
		@PathVariable Long friendId
	) {
		userMyService.removeFriend(userId, friendId);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{userId}/scrap-surveys")
	public ResponseEntity<List<ScrapSurveyResponse>> getScrapSurveys(@PathVariable Long userId) {
		List<ScrapSurveyResponse> scrapSurveyResponses = userMyService.getScrapSurveys(userId);
		return ResponseEntity.ok(scrapSurveyResponses);
	}

	@PostMapping("/{userId}/scrap-surveys")
	public ResponseEntity<ScrapSurveyResponse> addScrapSurvey(
		@PathVariable Long userId,
		@RequestBody ScrapSurveyRequest scrapSurveyRequest
	) {
		ScrapSurvey scrapSurvey = scrapSurveyRequest.scrapSurvey();
		ScrapSurveyResponse scrapSurveyResponse = userMyService.addScrapSurvey(userId, scrapSurvey);

		return ResponseEntity.ok(scrapSurveyResponse);
	}

	@DeleteMapping("/{userId}/scrap-surveys/{scrapSurveyId}")
	public ResponseEntity<Void> removeScrapSurvey(
		@PathVariable Long userId,
		@PathVariable Long scrapSurveyId
	) {
		userMyService.removeScrapSurvey(userId, scrapSurveyId);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{userId}/scrap-products")
	public ResponseEntity<List<ScrapProductResponse>> getScrapProducts(@PathVariable Long userId) {
		List<ScrapProductResponse> scrapProductResponses = userMyService.getScrapProducts(userId);
		return ResponseEntity.ok(scrapProductResponses);
	}

	@PostMapping("/{userId}/scrap-products")
	public ResponseEntity<ScrapProductResponse> addScrapProduct(
		@PathVariable Long userId,
		@RequestBody ScrapProductRequest scrapProductRequest
	) {
		ScrapProduct scrapProduct = scrapProductRequest.scrapProduct();
		ScrapProductResponse scrapProductResponse = userMyService.addScrapProduct(userId, scrapProduct);

		return ResponseEntity.ok(scrapProductResponse);
	}

	@DeleteMapping("/{userId}/scrap-products/{scrapProductId}")
	public ResponseEntity<Void> removeScrapProduct(
		@PathVariable Long userId,
		@PathVariable Long scrapProductId
	) {
		userMyService.removeScrapProduct(userId, scrapProductId);

		return ResponseEntity.noContent().build();
	}
}
