package com.legendshop.model.entity;

import com.legendshop.model.ModelUtil;
import com.legendshop.util.constant.ProductTypeEnum;
import java.util.Date;

public class Product extends AbstractProduct {
	private GroupProduct groupProduct;
	private static final long serialVersionUID = -7571396124663475715L;

	public Product() {
	}

	public Product(Long prodId, Long sortId, Long nsortId, Long subNsortId,
			String prodName, String pic) {
		this.prodId = prodId;
		this.sortId = sortId;
		this.nsortId = nsortId;
		this.subNsortId = subNsortId;
		this.name = prodName;
		this.pic = pic;
	}

	public Product(Long prodId, String name, Double cash, String userId,
			Integer cityid, Integer provinceid, Integer areaid, Date recDate,
			String content, Integer buys, Integer commentNum) {
		this.prodId = prodId;
		this.name = name;
		this.cash = cash;
		this.userId = userId;
		this.cityid = cityid;
		this.provinceid = provinceid;
		this.areaid = areaid;
		this.recDate = recDate;
		this.content = content;
		this.buys = buys;
		this.commentNum = commentNum;
	}

	public Product(Long prodId, Long sortId, Long nsortId, Long subNsortId,
			String prodName) {
		this.prodId = prodId;
		this.sortId = sortId;
		this.nsortId = nsortId;
		this.subNsortId = subNsortId;
		this.name = prodName;
	}

	public Product(Long prodId, Long sortId, Long nsortId, Long subNsortId,
			String prodName, String pic, Double price, Double cash,
			Double proxyPrice, Integer views, Integer buys, Integer comments) {
		this.prodId = prodId;
		this.sortId = sortId;
		this.nsortId = nsortId;
		this.subNsortId = subNsortId;
		this.name = prodName;
		this.pic = pic;
		this.price = price;
		this.cash = cash;
		this.proxyPrice = proxyPrice;
		this.views = views;
		this.buys = buys;
		this.comments = comments;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" [prodId=").append(this.prodId).append(", sortId=")
				.append(this.sortId).append(", nsortId=").append(this.nsortId)
				.append(", name=").append(this.name).append(", userName=")
				.append(this.userName).append("] ");
		return sb.toString();
	}

	public GroupProduct getGroupProduct() {
		return this.groupProduct;
	}

	public void setGroupProduct(GroupProduct groupProduct) {
		this.groupProduct = groupProduct;
	}

	public void validate() {
		ModelUtil util = new ModelUtil();
		util.isNotNull(getSortId(), "sortId");
		if (ProductTypeEnum.PRODUCT.value().equals(getProdType())) {
			util.isNotNull(getNsortId(), "nsortId");
			util.isNotNull(getSubNsortId(), "subNsort");
		}
		util.isNotNull(getPic(), "Pic");
		util.isNotNull(getPic(), "Pic");

		util.isNotNull(getContent(), "Content");
	}
}