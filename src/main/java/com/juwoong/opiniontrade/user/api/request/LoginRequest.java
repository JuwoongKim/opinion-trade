package com.juwoong.opiniontrade.user.api.request;

import com.juwoong.opiniontrade.user.domain.Email;
import com.juwoong.opiniontrade.user.domain.Password;

public record LoginRequest(
	Email email,
	Password password
) {
}
