package com.legendshop.model.entity;

public class ShopDetail extends AbstractShopDetail {
	private static final long serialVersionUID = 3924728437878080050L;

	public ShopDetail() {
	}

	public ShopDetail(String logoPic, String briefDesc, String userId,
			String userName) {
		this.logoPic = logoPic;
		this.userId = userId;
		this.userName = userName;
		this.briefDesc = briefDesc;
	}
}