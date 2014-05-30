package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.BasketDao;
import com.legendshop.business.dao.PayTypeDao;
import com.legendshop.business.dao.ScoreDao;
import com.legendshop.business.dao.SubDao;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.dao.jdbc.BaseJdbcDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleSqlQuery;
import com.legendshop.core.event.FireEvent;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.PayType;
import com.legendshop.model.entity.Sub;
import com.legendshop.spi.constants.OrderStatusEnum;
import com.legendshop.spi.constants.SubStatusEnum;
import com.legendshop.spi.event.OrderUpdateEvent;
import com.legendshop.spi.service.StockService;
import com.legendshop.util.AppUtils;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class SubDaoImpl extends SubCommonDaoImpl implements SubDao {
	private static Logger log = LoggerFactory.getLogger(SubDaoImpl.class);
	private ScoreDao scoreDao;
	private BasketDao basketDao;
	private BaseJdbcDao baseJdbcDao;
	private PayTypeDao payTypeDao;
	private StockService stockService;

	public void saveSub(Sub sub) {
		saveOrUpdate(sub);
	}

	public boolean deleteSub(Sub sub) {
		if (sub != null) {
			EventHome
					.publishEvent(new FireEvent(sub, OperationTypeEnum.DELETE));
			saveSubHistory(sub, SubStatusEnum.ORDER_DEL.value());
			delete(sub);
			this.basketDao.deleteBasketBySubNumber(sub.getSubNumber());
			return true;
		}
		return false;
	}

	public boolean updateSubPrice(Sub sub, String userName, Double totalPrice) {
		if (sub != null) {
			saveSubHistory(sub, SubStatusEnum.PRICE_CHANGE.value());
			sub.setActualTotal(totalPrice);
			EventHome.publishEvent(new FireEvent(sub,
					OperationTypeEnum.UPDATE_PRICE));
			updateSub(sub);
			return true;
		}
		return false;
	}

	public Sub getSubById(Long subId) {
		return ((Sub) get(Sub.class, subId));
	}

	public Sub getSubBySubNumber(String subNumber) {
		if (AppUtils.isBlank(subNumber))
			return null;

		return ((Sub) findUniqueBy("from Sub where subNumber = ?", Sub.class,
				new Object[] { subNumber }));
	}

	public boolean updateSub(Sub sub, Integer status, String userName,
			String payTypeId) {
		if (sub != null) {
			saveSubHistory(sub, SubStatusEnum.CHANGE_STATUS.value());
			if ((status.equals(OrderStatusEnum.SUCCESS.value()))
					|| (status.equals(OrderStatusEnum.CLOSE.value())))
				sub.setSubCheck("Y");

			if (status.equals(OrderStatusEnum.SUCCESS.value())) {
				this.scoreDao.saveScore(sub);
			}

			List baskets = getBasketBySubNumber(sub.getSubNumber());
			if (AppUtils.isNotBlank(baskets))
				for (Iterator localIterator = baskets.iterator(); localIterator
						.hasNext();) {
					Basket basket = (Basket) localIterator.next();
					Integer basketCount = basket.getBasketCount();
					if (status.equals(OrderStatusEnum.SUCCESS.value())) {
						this.stockService.addBuys(basket.getProdId(),
								basketCount);
					}
					if (status.equals(OrderStatusEnum.CLOSE.value())) {
						this.stockService.addStock(basket.getProdId(),
								basketCount);
					}
					if (!(status.equals(OrderStatusEnum.CONSIGNMENT.value())))

						this.stockService.reduceStock(basket.getProdId(),
								basketCount);
				}

			if (payTypeId != null) {
				PayType payType = this.payTypeDao.getPayTypeByIdAndName(
						sub.getShopName(), payTypeId);
				if (payType != null) {
					sub.setPayTypeName(payType.getPayTypeName());
					sub.setPayTypeId(payType.getPayTypeId());
					sub.setPayId(payType.getPayId());
				}
			}
			sub.setStatus(status);
			sub.setUpdateDate(new Date());
			log.info("userName {} update sub with status {}", userName, status);
			updateSub(sub);
			return true;
		}
		return false;
	}

	public List<Basket> getBasketBySubNumber(String subNumber) {
		return findByHQL(
				"from Basket where subNumber=? and basket_check=? order by basketDate desc",
				new Object[] { subNumber, "Y" });
	}

	public List<Sub> getFinishUnPay(int maxNum, Date expireDate) {
		return findByHQLLimit("from Sub where subDate < ? and status = ?", 0,
				maxNum,
				new Object[] { expireDate, OrderStatusEnum.UNPAY.value() });
	}

	public List<Sub> getUnAcklodgeSub(int maxNum, Date expireDate) {
		return findByHQLLimit("from Sub where updateDate < ? and status = ?",
				0, maxNum, new Object[] { expireDate,
						OrderStatusEnum.CONSIGNMENT.value() });
	}

	public void deleteOverTimeBasket(Date date) {
		Integer result = exeByHQL(
				"delete from Basket where basketCheck != ? and basketDate < ?",
				new Object[] { "Y", date });
		log.debug("removeOverTimeBasket, date = {}, result = {}", date, result);
	}

	@Required
	public void setScoreDao(ScoreDao scoreDaoImpl) {
		this.scoreDao = scoreDaoImpl;
	}

	@Required
	public void setBasketDao(BasketDao basketDaoImpl) {
		this.basketDao = basketDaoImpl;
	}

	public void updateSub(Sub sub) {
		EventHome.publishEvent(new OrderUpdateEvent(sub));
		update(sub);
	}

	public PageSupport getOrder(CriteriaQuery cq) {
		return find(cq);
	}

	public Long getTotalProcessingOrder(String userName) {
		return ((Long) findUniqueBy(
				"select count(*) from Sub where subCheck = ? and userName = ?",
				Long.class, new Object[] { "N", userName }));
	}

	public boolean isUserOrderProduct(Long prodId, String userName) {
		String sql = "select count(*) from Basket b, Sub s where s.subNumber = b.subNumber and s.subCheck = ? and b.prodId = ? and b.userName = ?";
		Long result = (Long) findUniqueBy(sql, Long.class, new Object[] { "Y",
				prodId, userName });
		return (result.longValue() > -4648542240895926272L);
	}

	public void deleteSub(String userName) {
		this.baseJdbcDao.deleteUserItem("ls_sub", userName);
	}

	public void setBaseJdbcDao(BaseJdbcDao jdbcDao) {
		this.baseJdbcDao = jdbcDao;
	}

	public PageSupport getOrder(SimpleSqlQuery query) {
		return this.baseJdbcDao.find(query);
	}

	public void setPayTypeDao(PayTypeDao payTypeDao) {
		this.payTypeDao = payTypeDao;
	}

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}
}