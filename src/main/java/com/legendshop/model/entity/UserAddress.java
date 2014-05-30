package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class UserAddress implements BaseEntity {
	private static final long serialVersionUID = -6057981433703294161L;
	private Long addrId;
	private String userId;
	private String userName;
	private String receiver;
	private String subAdds;
	private String subPost;
	private Integer provinceId;
	private Integer cityId;
	private Integer areaId;
	private String mobile;
	private String telphone;
	private String email;
	private String status;
	private String commonAddr;
	private Date createTime;

	public Long getAddrId() {
		return this.addrId;
	}

	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubAdds() {
		return this.subAdds;
	}

	public void setSubAdds(String subAdds) {
		this.subAdds = subAdds;
	}

	public String getSubPost() {
		return this.subPost;
	}

	public void setSubPost(String subPost) {
		this.subPost = subPost;
	}

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCommonAddr() {
		return this.commonAddr;
	}

	public void setCommonAddr(String commonAddr) {
		this.commonAddr = commonAddr;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Serializable getId() {
		return this.addrId;
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
}