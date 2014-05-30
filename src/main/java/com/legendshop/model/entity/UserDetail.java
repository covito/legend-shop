package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDetail extends AbstractEntity implements BaseEntity {
	private static final long serialVersionUID = -3648830158220060652L;
	private String userId;
	private Integer gradeId;
	private String userName;
	private String nickName;
	private String realName;
	private String userMail;
	private String userAdds;
	private String userTel;
	private String userPostcode;
	private String msn;
	private String qq;
	private List qqList;
	private String fax;
	private Date modifyTime;
	private Date userRegtime;
	private String userRegip;
	private Date userLasttime;
	private String userLastip;
	private String userMemo;
	private String password;
	private String sex;
	private String birthDate;
	private String userMobile;
	private Long shopId;
	private ShopDetail shopDetail;
	private String registerCode;
	private Long score;
	private Double totalCash;
	private Double totalConsume;
	private Set<UserAddress> userAddresses;
	private Long defaultAddrId;
	private String enabled;
	protected Integer provinceid;
	protected Integer cityid;
	protected Integer areaid;
	private String idCard;

	public UserDetail(String userId, Integer gradeId, String userName,
			String nickName, String userMail, String userAdds, String userTel,
			String userPostcode, String msn, String qq, String fax,
			Date modifyTime, Date userRegtime, String userRegip,
			Date userLasttime, String userLastip, String userMemo,
			String password, String sex, String birthDate, String userMobile,
			Long shopId) {
		this.userId = userId;
		this.gradeId = gradeId;
		this.userName = userName;
		this.nickName = nickName;
		this.userMail = userMail;
		this.userAdds = userAdds;
		this.userTel = userTel;
		this.userPostcode = userPostcode;
		this.msn = msn;
		this.qq = qq;
		this.fax = fax;
		this.modifyTime = modifyTime;
		this.userRegtime = userRegtime;
		this.userRegip = userRegip;
		this.userLasttime = userLasttime;
		this.userLastip = userLastip;
		this.userMemo = userMemo;
		this.password = password;
		this.sex = sex;
		this.birthDate = birthDate;
		this.userMobile = userMobile;
		this.shopId = shopId;
	}

	public UserDetail() {
	}

	public UserDetail(Map userDetailMap) {
		if (userDetailMap != null) {
			this.userId = ((String) userDetailMap.get("USER_ID"));
			Number grade = (Number) userDetailMap.get("GRADE_ID");
			if (grade != null) {
				this.gradeId = Integer.valueOf(grade.intValue());
			}

			this.userName = ((String) userDetailMap.get("USER_NAME"));

			this.nickName = ((String) userDetailMap.get("NICK_NAME"));
			this.userMail = ((String) userDetailMap.get("USER_MAIL"));

			this.userRegip = ((String) userDetailMap.get("USER_REGIP"));
			this.userLastip = ((String) userDetailMap.get("USER_LASTIP"));

			Number shop = (Number) userDetailMap.get("SHOP_ID");
			if (shop != null)
				this.shopId = Long.valueOf(shop.longValue());

			this.modifyTime = ((Date) userDetailMap.get("MODIFY_TIME"));
			this.userRegtime = ((Date) userDetailMap.get("USER_REGTIME"));
		}
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return this.userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserAdds() {
		return this.userAdds;
	}

	public void setUserAdds(String userAdds) {
		this.userAdds = userAdds;
	}

	public String getUserTel() {
		return this.userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserPostcode() {
		return this.userPostcode;
	}

	public void setUserPostcode(String userPostcode) {
		this.userPostcode = userPostcode;
	}

	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
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

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getUserRegtime() {
		return this.userRegtime;
	}

	public void setUserRegtime(Date userRegtime) {
		this.userRegtime = userRegtime;
	}

	public String getUserRegip() {
		return this.userRegip;
	}

	public void setUserRegip(String userRegip) {
		this.userRegip = userRegip;
	}

	public Date getUserLasttime() {
		return this.userLasttime;
	}

	public void setUserLasttime(Date userLasttime) {
		this.userLasttime = userLasttime;
	}

	public String getUserLastip() {
		return this.userLastip;
	}

	public void setUserLastip(String userLastip) {
		this.userLastip = userLastip;
	}

	public String getUserMemo() {
		return this.userMemo;
	}

	public void setUserMemo(String userMemo) {
		this.userMemo = userMemo;
	}

	public ShopDetail getShopDetail() {
		return this.shopDetail;
	}

	public void setShopDetail(ShopDetail shopDetail) {
		this.shopDetail = shopDetail;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public Long getShopId() {
		return this.shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getRegisterCode() {
		return this.registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	public List getQqList() {
		return this.qqList;
	}

	public void setQqList(List qqList) {
		this.qqList = qqList;
	}

	public Long getScore() {
		return this.score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Double getTotalCash() {
		return this.totalCash;
	}

	public void setTotalCash(Double totalCash) {
		this.totalCash = totalCash;
	}

	public Set<UserAddress> getUserAddresses() {
		return this.userAddresses;
	}

	public void setUserAddresses(Set<UserAddress> userAddresses) {
		this.userAddresses = userAddresses;
	}

	public Long getDefaultAddrId() {
		return this.defaultAddrId;
	}

	public void setDefaultAddrId(Long defaultAddrId) {
		this.defaultAddrId = defaultAddrId;
	}

	public Double getTotalConsume() {
		return this.totalConsume;
	}

	public void setTotalConsume(Double totalConsume) {
		this.totalConsume = totalConsume;
	}

	public Serializable getId() {
		return this.userId;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Integer getProvinceid() {
		return this.provinceid;
	}

	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}

	public Integer getCityid() {
		return this.cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public Integer getAreaid() {
		return this.areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}