package com.juwoong.opiniontrade.user.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juwoong.opiniontrade.user.application.response.SignUpResponse;
import com.juwoong.opiniontrade.user.domain.Email;
import com.juwoong.opiniontrade.user.domain.Password;
import com.juwoong.opiniontrade.user.domain.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public SignUpResponse signUp(Email email, Password password) {
		// 검증
		// new User(email, password)
		// save

		Long tempUserId = 1L;
		return new SignUpResponse(tempUserId);
	}
}
