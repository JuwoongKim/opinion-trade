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
import com.juwoong.opiniontrade.user.application.UserMyService;
import com.juwoong.opiniontrade.user.application.response.FriendResponse;
import com.juwoong.opiniontrade.user.domain.Friend;

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
		userMyService.removeFriend(friendId);

		return ResponseEntity.noContent().build();
	}
}
