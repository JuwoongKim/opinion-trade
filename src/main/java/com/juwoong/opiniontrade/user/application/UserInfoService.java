// package com.juwoong.opiniontrade.user.application;
//
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.juwoong.opiniontrade.user.application.response.ProfileInfoResponse;
// import com.juwoong.opiniontrade.user.domain.ProfileInfo;
// import com.juwoong.opiniontrade.user.domain.repository.UserRepository;
//
// @Service
// @Transactional(readOnly = true)
// public class UserInfoService {
// 	private final UserRepository userRepository;
//
// 	public UserInfoService(UserRepository userRepository) {
// 		this.userRepository = userRepository;
// 	}
//
// 	@Transactional
// 	public ProfileInfoResponse updateProfileInfo(Long userId, ProfileInfo profileInfo) {
// 		// find(userId);
// 		// user.updateProfileInfo(profileInfo);
//
// 		return new ProfileInfoResponse(profileInfo);
// 	}
//
// 	// public ProfileInfoResponse getProfileInfo(Long userId) {
// 	// 	// find(userId);
// 	// 	// ProfileInfo profileInfo = user.getProfileInfo();
// 	//
// 	// 	ProfileInfo profileInfo = new ProfileInfo();
// 	// 	return new ProfileInfoResponse(profileInfo);
// 	// }
//
// }
