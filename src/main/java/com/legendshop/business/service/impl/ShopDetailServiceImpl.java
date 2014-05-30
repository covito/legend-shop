package com.legendshop.business.service.impl;

import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.ShopStatusChecker;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.spi.service.ShopDetailService;
import org.springframework.cache.annotation.Cacheable;

public class ShopDetailServiceImpl extends BaseServiceImpl implements
		ShopDetailService {
	private ShopDetailDao shopDetailDao;
	private UserDetailDao userDetailDao;
	private ShopStatusChecker shopStatusChecker;

	public void setShopDetailDao(ShopDetailDao shopDetailDao) {
		this.shopDetailDao = shopDetailDao;
	}

	public ShopDetail getShopDetailById(Long id) {
		return ((ShopDetail) this.shopDetailDao.get(ShopDetail.class, id));
	}

	public UserDetail getShopDetailByName(String userName) {
		return this.userDetailDao.getUserDetailByName(userName);
	}

	public ShopDetail getShopDetailByShopId(Long shopId) {
		return this.shopDetailDao.getShopDetailByShopId(shopId);
	}

	public void delete(ShopDetail shopDetail) {
		this.shopDetailDao.deleteShopDetail(shopDetail);
	}

	public void save(ShopDetail shopDetail) {
		this.shopDetailDao.saveShopDetail(shopDetail);
	}

	@Cacheable(value = { "ShopDetailView" }, key = "#currentShopName")
	public ShopDetailView getShopDetailView(String currentShopName) {
		ShopDetailView shopDetail = this.shopDetailDao
				.getShopDetailView(currentShopName);
		if ((shopDetail != null)
				&& (!(this.shopStatusChecker.check(shopDetail,
						ThreadLocalContext.getRequest())))){
			return shopDetail;
		}else{
			return null;
		}
	}

	@Cacheable(value = { "ShopDetailView" }, key = "'DM_' + #domainName")
	public String getShopNameByDomain(String domainName) {
		return this.shopDetailDao.getShopNameByDomain(domainName);
	}

	public void update(ShopDetail shopDetail) {
		this.shopDetailDao.updateShopDetail(shopDetail);
	}

	public PageSupport getShopDetail(CriteriaQuery cq) {
		return this.shopDetailDao.find(cq);
	}

	public void setUserDetailDao(UserDetailDao userDetailDao) {
		this.userDetailDao = userDetailDao;
	}

	public ShopDetail getShopDetailByUserId(String userId) {
		return this.shopDetailDao.getShopDetailByUserId(userId);
	}

	public void updateShopDetail(Product product) {
		ShopDetail shopdetail = this.shopDetailDao
				.getShopDetailForUpdate(product.getUserName());
		if (shopdetail == null)
			throw new NotFoundException("ShopDetail is null, UserName = "
					+ product.getUserName());

		shopdetail.setProductNum(this.shopDetailDao.getProductNum(product
				.getUserName()));
		shopdetail.setOffProductNum(this.shopDetailDao.getOffProductNum(product
				.getUserName()));
		this.shopDetailDao.updateShopDetail(shopdetail);
	}

	public boolean updateShop(String userId, ShopDetail shopDetail,
			Integer status) {
		return this.shopDetailDao.updateShop(userId, shopDetail, status);
	}

	public void setShopStatusChecker(ShopStatusChecker shopStatusChecker) {
		this.shopStatusChecker = shopStatusChecker;
	}

	public Boolean isShopExists(String userName) {
		return this.shopDetailDao.isShopExists(userName);
	}

	public Long getShopIdByName(String userName) {
		return this.shopDetailDao.getShopIdByName(userName);
	}
}