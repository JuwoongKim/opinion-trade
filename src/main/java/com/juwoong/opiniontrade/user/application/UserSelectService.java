package com.juwoong.opiniontrade.user.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juwoong.opiniontrade.user.application.response.UserResponse;
import com.juwoong.opiniontrade.user.domain.Email;
import com.juwoong.opiniontrade.user.domain.Password;
import com.juwoong.opiniontrade.user.domain.User;
import com.juwoong.opiniontrade.user.domain.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserSelectService {
	private UserRepository userRepository;

	public UserSelectService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserResponse> getUsers() {
		List<User> users = List.of(
			new User(new Email("email"), new Password("password")),
			new User(new Email("email2"), new Password("password2")),
			new User(new Email("email3"), new Password("password3"))
		);

		List<UserResponse> userResponses = users.stream().map(user -> new UserResponse(user)).toList();

		return userResponses;
	}
}
