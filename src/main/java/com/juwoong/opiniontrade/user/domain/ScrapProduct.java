package com.juwoong.opiniontrade.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ScrapProduct {
	@Column(name = "scrap_product_id", nullable = false)
	private String scrapProductId;

	@Column(name = "scrip_product_title", nullable = false)
	private String scrapSurveyTitle;

	@Column(name = "scrip_product_price", nullable = false)
	private Long scrapSurveyPrice;
}
