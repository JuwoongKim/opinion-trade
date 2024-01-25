package com.juwoong.opiniontrade.user.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juwoong.opiniontrade.user.api.request.ProfileInfoRequest;
import com.juwoong.opiniontrade.user.application.UserInfoService;
import com.juwoong.opiniontrade.user.application.response.ProfileInfoResponse;
import com.juwoong.opiniontrade.user.domain.ProfileInfo;

@RestController
@RequestMapping("/users/info")
public class UserInfoController {
	private final UserInfoService userInfoService;

	public UserInfoController(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	@PostMapping("/profile")
	public ResponseEntity<ProfileInfoResponse> updateProfileInfo(@RequestBody ProfileInfoRequest profileInfoRequest) {
		Long userId = profileInfoRequest.userId();
		ProfileInfo profileInfo = profileInfoRequest.profileInfo();
		ProfileInfoResponse profileInfoResponse = userInfoService.updateProfileInfo(userId, profileInfo);

		return new ResponseEntity<>(profileInfoResponse, HttpStatus.CREATED);
	}

}
