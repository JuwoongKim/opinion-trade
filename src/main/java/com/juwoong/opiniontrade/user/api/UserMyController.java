package com.juwoong.opiniontrade.user.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juwoong.opiniontrade.user.application.UserMyService;
import com.juwoong.opiniontrade.user.application.response.FriendResponse;

@RestController
@RequestMapping("/users/my")
public class UserMyController {
	private final UserMyService userMyService;

	public UserMyController(UserMyService userMyService) {
		this.userMyService = userMyService;
	}

	@GetMapping("/{userId}/friends")
	public ResponseEntity<List<FriendResponse>> getFriends(
		@PathVariable Long userId
	) {
		List<FriendResponse> friendResponses = userMyService.getFriends(userId);
		return ResponseEntity.ok(friendResponses);
	}
}
