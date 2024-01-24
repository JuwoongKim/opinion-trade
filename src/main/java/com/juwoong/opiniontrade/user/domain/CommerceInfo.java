package com.juwoong.opiniontrade.user.domain;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CommerceInfo {

	@Column(name = "sales_count")
	@ColumnDefault(value = "0")
	Long salesCount;

	@Column(name = "total_sales_count")
	@ColumnDefault(value = "0")
	Long totalSalesAmount;

	@Column(name = "purchase_count")
	@ColumnDefault(value = "0")
	Long purchaseCount;

	@Column(name = "total_purchase_count")
	@ColumnDefault(value = "0")
	Long totalPurchaseAmount;

	protected CommerceInfo() {
	}

	public CommerceInfo(Long salesCount, Long totalSalesAmount, Long purchaseCount, Long totalPurchaseAmount) {
		this.salesCount = salesCount;
		this.totalSalesAmount = totalSalesAmount;
		this.purchaseCount = purchaseCount;
		this.totalPurchaseAmount = totalPurchaseAmount;
	}
}
