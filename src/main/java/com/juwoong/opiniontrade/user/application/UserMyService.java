package com.juwoong.opiniontrade.user.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juwoong.opiniontrade.user.application.response.FriendResponse;
import com.juwoong.opiniontrade.user.domain.Friend;
import com.juwoong.opiniontrade.user.domain.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserMyService {
	private final UserRepository userRepository;

	public UserMyService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<FriendResponse> getFriends(Long userId) {
		List<Friend> friends = List.of(
			new Friend(1L, "friend1"),
			new Friend(2L, "friend2"),
			new Friend(3L, "friend3")
		);

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
	public void removeFriend(Long friendId) {
		// find(userId);
		// user.removeFriend(friendId);
	}
}
