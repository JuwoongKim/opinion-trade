package com.juwoong.opiniontrade.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ScrapProduct {
	@Column(name = "scrap_product_id", nullable = false)
	private Long scrapProductId;

	@Column(name = "scrip_product_title", nullable = false)
	private String scrapSurveyTitle;

	@Column(name = "scrip_product_price", nullable = false)
	private Long scrapSurveyPrice;

	protected ScrapProduct() {
	}

	public ScrapProduct(Long scrapProductId, String scrapSurveyTitle, Long scrapSurveyPrice) {
		this.scrapProductId = scrapProductId;
		this.scrapSurveyTitle = scrapSurveyTitle;
		this.scrapSurveyPrice = scrapSurveyPrice;
	}
}
