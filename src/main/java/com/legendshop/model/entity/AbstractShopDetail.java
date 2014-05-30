package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public abstract class AbstractShopDetail extends UploadFile implements
		BaseEntity {
	private static final long serialVersionUID = -9153182932362623738L;
	protected Long shopId;
	protected String userId;
	protected String userName;
	protected String siteName;
	protected String shopAddr;
	protected String bankCard;
	protected String payee;
	protected String code;
	protected String postAddr;
	protected String recipient;
	protected Integer status;
	protected Integer visitTimes;
	protected Integer visitTimesToday;
	protected Integer productNum;
	protected Integer commNum;
	protected Integer offProductNum;
	protected Date modifyDate;
	protected Date recDate;
	protected String briefDesc;
	protected String detailDesc;
	protected String shopPic;
	protected String logoPic;
	protected String userMail;
	protected String userAdds;
	protected String userTel;
	protected String userPostcode;
	protected Integer gradeId;
	protected Integer type;
	protected String idCardPic;
	protected String trafficPic;
	protected MultipartFile idCardPicFile;
	protected MultipartFile trafficPicFile;
	protected String idCardNum;
	protected String realPath;
	protected String createAreaCode;
	protected String createCountryCode;
	protected String ip;
	protected Integer provinceid;
	protected Integer cityid;
	protected Integer areaid;
	protected String frontEndTemplet;
	protected String backEndTemplet;
	protected String frontEndLanguage;
	protected String backEndLanguage;
	protected String frontEndStyle;
	protected String backEndStyle;
	protected String domainName;
	protected String icpInfo;
	protected List<String> qqList;
	protected Double capital;
	protected Integer credit;

	public Long getShopId() {
		return this.shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
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

	public String getSiteName() {
		return this.siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getShopAddr() {
		return this.shopAddr;
	}

	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}

	public String getBankCard() {
		return this.bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getPayee() {
		return this.payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPostAddr() {
		return this.postAddr;
	}

	public void setPostAddr(String postAddr) {
		this.postAddr = postAddr;
	}

	public String getRecipient() {
		return this.recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getVisitTimes() {
		return this.visitTimes;
	}

	public void setVisitTimes(Integer visitTimes) {
		this.visitTimes = visitTimes;
	}

	public Integer getVisitTimesToday() {
		return this.visitTimesToday;
	}

	public void setVisitTimesToday(Integer visitTimesToday) {
		this.visitTimesToday = visitTimesToday;
	}

	public Integer getProductNum() {
		return this.productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public Integer getCommNum() {
		return this.commNum;
	}

	public void setCommNum(Integer commNum) {
		this.commNum = commNum;
	}

	public Integer getOffProductNum() {
		return this.offProductNum;
	}

	public void setOffProductNum(Integer offProductNum) {
		this.offProductNum = offProductNum;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyTime) {
		this.modifyDate = modifyTime;
	}

	public Date getRecDate() {
		return this.recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	public String getBriefDesc() {
		return this.briefDesc;
	}

	public void setBriefDesc(String briefDesc) {
		this.briefDesc = briefDesc;
	}

	public String getDetailDesc() {
		return this.detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	public String getShopPic() {
		return this.shopPic;
	}

	public void setShopPic(String shopPic) {
		this.shopPic = shopPic;
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

	public Integer getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIdCardPic() {
		return this.idCardPic;
	}

	public void setIdCardPic(String idCardPic) {
		this.idCardPic = idCardPic;
	}

	public String getTrafficPic() {
		return this.trafficPic;
	}

	public void setTrafficPic(String trafficPic) {
		this.trafficPic = trafficPic;
	}

	public MultipartFile getIdCardPicFile() {
		return this.idCardPicFile;
	}

	public void setIdCardPicFile(MultipartFile idCardPicFile) {
		this.idCardPicFile = idCardPicFile;
	}

	public MultipartFile getTrafficPicFile() {
		return this.trafficPicFile;
	}

	public void setTrafficPicFile(MultipartFile trafficPicFile) {
		this.trafficPicFile = trafficPicFile;
	}

	public String getIdCardNum() {
		return this.idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getRealPath() {
		return this.realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getCreateAreaCode() {
		return this.createAreaCode;
	}

	public void setCreateAreaCode(String createAreaCode) {
		this.createAreaCode = createAreaCode;
	}

	public String getCreateCountryCode() {
		return this.createCountryCode;
	}

	public void setCreateCountryCode(String createCountryCode) {
		this.createCountryCode = createCountryCode;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public String getFrontEndStyle() {
		return this.frontEndStyle;
	}

	public void setFrontEndStyle(String frontEndStyle) {
		this.frontEndStyle = frontEndStyle;
	}

	public String getBackEndStyle() {
		return this.backEndStyle;
	}

	public void setBackEndStyle(String backEndStyle) {
		this.backEndStyle = backEndStyle;
	}

	public List<String> getQqList() {
		return this.qqList;
	}

	public void setQqList(List<String> qqList) {
		this.qqList = qqList;
	}

	public Serializable getId() {
		return this.shopId;
	}

	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(String domainName) {
		if (domainName != null) {
			String domain = domainName.trim();
			if (domain.toLowerCase().startsWith("http://")) {
				domain = domain.substring(7);
			}

			if (domain.startsWith("www.")) {
				this.domainName = domain.substring(4).trim();
				return;
			}

			this.domainName = domain.trim();
		}
	}

	public String getIcpInfo() {
		return this.icpInfo;
	}

	public void setIcpInfo(String icpInfo) {
		this.icpInfo = icpInfo;
	}

	public String getLogoPic() {
		return this.logoPic;
	}

	public void setLogoPic(String logoPic) {
		this.logoPic = logoPic;
	}

	public Double getCapital() {
		return this.capital;
	}

	public void setCapital(Double capital) {
		this.capital = capital;
	}

	public Integer getCredit() {
		return this.credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getFrontEndTemplet() {
		return this.frontEndTemplet;
	}

	public void setFrontEndTemplet(String frontEndTemplet) {
		this.frontEndTemplet = frontEndTemplet;
	}

	public String getBackEndTemplet() {
		return this.backEndTemplet;
	}

	public void setBackEndTemplet(String backEndTemplet) {
		this.backEndTemplet = backEndTemplet;
	}

	public String getFrontEndLanguage() {
		return this.frontEndLanguage;
	}

	public void setFrontEndLanguage(String frontEndLanguage) {
		this.frontEndLanguage = frontEndLanguage;
	}

	public String getBackEndLanguage() {
		return this.backEndLanguage;
	}

	public void setBackEndLanguage(String backEndLanguage) {
		this.backEndLanguage = backEndLanguage;
	}
}