package com.juwoong.opiniontrade.user.api.request;

import com.juwoong.opiniontrade.user.domain.ProfileInfo;

public record ProfileInfoRequest(
	Long userId,
	ProfileInfo profileInfo
) {
}
