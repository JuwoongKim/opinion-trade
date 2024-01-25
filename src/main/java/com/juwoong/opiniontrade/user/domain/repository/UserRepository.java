package com.juwoong.opiniontrade.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juwoong.opiniontrade.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
