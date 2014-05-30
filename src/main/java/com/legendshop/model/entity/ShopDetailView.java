package com.legendshop.model.entity;

public class ShopDetailView extends AbstractShopDetail {
	private static final long serialVersionUID = -8763595849099410286L;
	private String province;
	private String city;
	private String area;
	private String nickName;
	private String userMobile;
	private String msnNumber;
	private String qq;
	private String fax;

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getMsnNumber() {
		return this.msnNumber;
	}

	public void setMsnNumber(String msnNumber) {
		this.msnNumber = msnNumber;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
}