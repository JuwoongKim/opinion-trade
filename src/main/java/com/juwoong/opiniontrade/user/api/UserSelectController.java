package com.juwoong.opiniontrade.user.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juwoong.opiniontrade.user.application.UserSelectService;
import com.juwoong.opiniontrade.user.application.response.UserResponse;

@RestController
@RequestMapping("/users")
public class UserSelectController {
	private final UserSelectService userSelectService;

	public UserSelectController(UserSelectService userSelectService) {
		this.userSelectService = userSelectService;
	}

	@GetMapping
	public ResponseEntity<List<UserResponse>> getUsers() {
		List<UserResponse> userResponses = userSelectService.getUsers();

		return ResponseEntity.ok(userResponses);
	}
}
