package com.legendshop.model;

import com.legendshop.model.entity.ShopDetailView;
import java.io.Serializable;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = -2459516151507147213L;
	private String currentVersion;
	private String newestVersion;
	private Long userTotalNum;
	private Long shopTotalNum;
	private Long processingOrderNum;
	private Long unReadMessageNum;
	private Long articleNum;
	private ShopDetailView shopDetail;

	public String getCurrentVersion() {
		return this.currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getNewestVersion() {
		return this.newestVersion;
	}

	public void setNewestVersion(String newestVersion) {
		this.newestVersion = newestVersion;
	}

	public Long getUserTotalNum() {
		return this.userTotalNum;
	}

	public void setUserTotalNum(Long userTotalNum) {
		this.userTotalNum = userTotalNum;
	}

	public Long getShopTotalNum() {
		return this.shopTotalNum;
	}

	public void setShopTotalNum(Long shopTotalNum) {
		this.shopTotalNum = shopTotalNum;
	}

	public Long getProcessingOrderNum() {
		return this.processingOrderNum;
	}

	public void setProcessingOrderNum(Long processingOrderNum) {
		this.processingOrderNum = processingOrderNum;
	}

	public Long getUnReadMessageNum() {
		return this.unReadMessageNum;
	}

	public void setUnReadMessageNum(Long unReadMessageNum) {
		this.unReadMessageNum = unReadMessageNum;
	}

	public Long getArticleNum() {
		return this.articleNum;
	}

	public void setArticleNum(Long articleNum) {
		this.articleNum = articleNum;
	}

	public ShopDetailView getShopDetail() {
		return this.shopDetail;
	}

	public void setShopDetail(ShopDetailView shopDetail) {
		this.shopDetail = shopDetail;
	}
}