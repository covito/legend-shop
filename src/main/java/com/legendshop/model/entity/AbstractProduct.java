package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

public abstract class AbstractProduct extends UploadFile implements BaseEntity {
	private static final long serialVersionUID = 639772483681855956L;
	protected Long prodId;
	protected Long sortId;
	protected Long nsortId;
	protected Long globalSort;
	protected Long globalNsort;
	protected Long globalSubSort;
	protected Long subNsortId;
	protected String modelId;
	protected String name;
	protected Double price;
	protected Double cash;
	protected Double proxyPrice;
	protected Double carriage;
	protected String brief;
	protected String content;
	protected Integer views;
	protected Integer buys;
	protected Integer comments;
	protected Date recDate;
	protected String smallPic;
	protected String useSmallPic;
	protected String pic;
	protected String commend;
	protected String hot;
	protected Integer status;
	protected Date modifyDate;
	protected String userId;
	protected String userName;
	protected Date startDate;
	protected Date endDate;
	protected Integer stocks;
	protected String prodType;
	protected String keyWord;
	protected String attribute;
	protected String parameter;
	protected Long brandId;
	protected Long globalBrand;
	protected Integer actualStocks;
	protected MultipartFile smallPicFile;
	protected Integer provinceid;
	protected Integer cityid;
	protected Integer areaid;
	protected Integer commentNum;

	public Long getProdId() {
		return this.prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public Long getSortId() {
		return this.sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public Long getNsortId() {
		return this.nsortId;
	}

	public void setNsortId(Long nsortId) {
		this.nsortId = nsortId;
	}

	public Long getSubNsortId() {
		return this.subNsortId;
	}

	public void setSubNsortId(Long subNsortId) {
		this.subNsortId = subNsortId;
	}

	public String getModelId() {
		return this.modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCash() {
		return this.cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public Double getProxyPrice() {
		return this.proxyPrice;
	}

	public void setProxyPrice(Double proxyPrice) {
		this.proxyPrice = proxyPrice;
	}

	public Double getCarriage() {
		return this.carriage;
	}

	public void setCarriage(Double carriage) {
		this.carriage = carriage;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getViews() {
		return this.views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getBuys() {
		return this.buys;
	}

	public void setBuys(Integer buys) {
		this.buys = buys;
	}

	public Date getRecDate() {
		return this.recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getCommend() {
		return this.commend;
	}

	public void setCommend(String commend) {
		this.commend = commend;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
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

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStocks() {
		return this.stocks;
	}

	public void setStocks(Integer stocks) {
		this.stocks = stocks;
	}

	public String getProdType() {
		return this.prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getParameter() {
		return this.parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Long getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Integer getActualStocks() {
		return this.actualStocks;
	}

	public void setActualStocks(Integer actualStocks) {
		this.actualStocks = actualStocks;
	}

	public Serializable getId() {
		return this.prodId;
	}

	public String getSmallPic() {
		return this.smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public String getHot() {
		return this.hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}

	public MultipartFile getSmallPicFile() {
		return this.smallPicFile;
	}

	public void setSmallPicFile(MultipartFile smallPicFile) {
		this.smallPicFile = smallPicFile;
	}

	public String getUseSmallPic() {
		return this.useSmallPic;
	}

	public void setUseSmallPic(String useSmallPic) {
		this.useSmallPic = useSmallPic;
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

	public Integer getCommentNum() {
		return this.commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getComments() {
		return this.comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public Long getGlobalSort() {
		return this.globalSort;
	}

	public void setGlobalSort(Long globalSort) {
		this.globalSort = globalSort;
	}

	public Long getGlobalNsort() {
		return this.globalNsort;
	}

	public void setGlobalNsort(Long globalNsort) {
		this.globalNsort = globalNsort;
	}

	public Long getGlobalSubSort() {
		return this.globalSubSort;
	}

	public void setGlobalSubSort(Long globalSubSort) {
		this.globalSubSort = globalSubSort;
	}

	public Long getGlobalBrand() {
		return this.globalBrand;
	}

	public void setGlobalBrand(Long globalBrand) {
		this.globalBrand = globalBrand;
	}
}