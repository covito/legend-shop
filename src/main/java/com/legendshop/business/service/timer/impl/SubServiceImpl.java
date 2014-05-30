package com.legendshop.business.service.timer.impl;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.dao.BasketDao;
import com.legendshop.business.dao.SubDao;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.business.service.impl.BaseServiceImpl;
import com.legendshop.core.constant.PageProviderEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.QueryMap;
import com.legendshop.core.dao.support.SimpleSqlQuery;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.event.EventHome;
import com.legendshop.model.SubForm;
import com.legendshop.model.SubListForm;
import com.legendshop.model.SubQueryForm;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.PayType;
import com.legendshop.model.entity.Sub;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.constants.OrderStatusEnum;
import com.legendshop.spi.constants.PayTypeEnum;
import com.legendshop.spi.constants.SubStatusEnum;
import com.legendshop.spi.event.OrderSaveEvent;
import com.legendshop.spi.form.MemberForm;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.PayTypeService;
import com.legendshop.spi.service.StockService;
import com.legendshop.spi.service.timer.SubService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class SubServiceImpl extends BaseServiceImpl implements SubService {
	private static Logger log = LoggerFactory.getLogger(SubServiceImpl.class);
	private SubDao subDao;
	private BasketDao basketDao;
	private PayTypeService payTypeService;
	private UserDetailDao userDetailDao;
	private Integer commitInteval = Integer.valueOf(100);
	private Integer expireDate = Integer.valueOf(-30);
	private StockService stockService;

	public void finishUnAcklodge() {
		Date date = getDate(this.expireDate.intValue());
		log.debug("finishUnAcklodge,date = {}", date);
		boolean haveValue = true;
		while (haveValue) {
			List list = this.subDao.getUnAcklodgeSub(
					this.commitInteval.intValue(), date);
			log.debug("finishUnAcklodge,list = {}", list);
			if (AppUtils.isBlank(list)) {
				haveValue = false;
			} else {
				for (Iterator localIterator = list.iterator(); localIterator
						.hasNext();) {
					Sub sub = (Sub) localIterator.next();
					this.subDao.saveSubHistory(sub,
							SubStatusEnum.ORDER_OVER_TIME.value());
					sub.setStatus(OrderStatusEnum.SUCCESS.value());
					sub.setSubCheck("Y");
					sub.setUpdateDate(new Date());
					this.subDao.updateSub(sub);
				}
				this.subDao.flush();
			}
		}
	}

	public void finishUnPay() {
		Date date = getDate(this.expireDate.intValue());
		log.debug("finishUnPay,date = {}", date);
		boolean haveValue = true;
		while (haveValue) {
			List list = this.subDao.getFinishUnPay(
					this.commitInteval.intValue(), date);
			log.debug("finishUnPay,list = {}", list);
			if (AppUtils.isBlank(list)) {
				haveValue = false;
			} else {
				for (Iterator localIterator = list.iterator(); localIterator
						.hasNext();) {
					Sub sub = (Sub) localIterator.next();
					this.subDao.saveSubHistory(sub,
							SubStatusEnum.ORDER_OVER_TIME.value());
					sub.setStatus(OrderStatusEnum.CLOSE.value());
					sub.setSubCheck("Y");
					sub.setUpdateDate(new Date());
					this.subDao.updateSub(sub);
				}
				this.subDao.flush();
			}
		}
	}

	public void removeOverTimeBasket() {
		Date date = getDate(this.expireDate.intValue());
		this.subDao.deleteOverTimeBasket(date);
	}

	private Date getDate(int days) {
		Date myDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(5, days);
		return cal.getTime();
	}

	@Required
	public void setSubDao(SubDao subDao) {
		this.subDao = subDao;
	}

	public void setCommitInteval(Integer commitInteval) {
		this.commitInteval = commitInteval;
	}

	public void setExpireDate(Integer expireDate) {
		this.expireDate = expireDate;
	}

	public List<Sub> saveSub(SubForm form) {
		List subList = new ArrayList();

		Map basketMap = this.basketDao.getBasketGroupById(form.getBasketId());
		if (AppUtils.isNotBlank(basketMap)) {
			for (Iterator localIterator1 = basketMap.values().iterator(); localIterator1
					.hasNext();) {
				List baskets = (List) localIterator1.next();
				String subNember = CommonServiceUtil.getSubNember(form
						.getUserName());
				String prodName = "";

				List validateBasketList = new ArrayList();
				for (Iterator localIterator2 = baskets.iterator(); localIterator2
						.hasNext();) {
					Basket backet = (Basket) localIterator2.next();
					prodName = prodName + backet.getProdName() + ",";
					Basket basket = this.basketDao.getBasketById(backet
							.getBasketId());
					if ((basket == null)
							|| (!(this.stockService.addHold(basket.getProdId(),
									basket.getBasketCount())))){
						
					}
					validateBasketList.add(backet);

					basket.setSubNumber(subNember);
					basket.setBasketCheck("Y");
					basket.setLastUpdateDate(new Date());
					label224: this.basketDao.updateBasket(basket);
				}

				if (validateBasketList.size() <= 0){
					
					
				}
				Sub bo = makeSub(form);
				bo.setSubNumber(subNember);
				bo.setTotal(CommonServiceUtil
						.calculateTotalCash(validateBasketList));
				bo.setActualTotal(bo.getTotal());
				bo.setShopName(((Basket) validateBasketList.get(0))
						.getShopName());
				bo.setStatus(OrderStatusEnum.UNPAY.value());
				List payTypeList = this.payTypeService
						.getPayTypeList(((Basket) baskets.get(0)).getShopName());
				if ((payTypeList != null) && (payTypeList.size() == 1)) {
					PayType payType = (PayType) payTypeList.get(0);
					if (payType.getPayTypeId().equals(
							PayTypeEnum.PAY_AT_GOODS_ARRIVED.value())) {
						bo.setPayId(payType.getPayId());
						bo.setPayDate(new Date());
						bo.setPayTypeId(payType.getPayTypeId());
						bo.setPayTypeName(payType.getPayTypeName());
					}
				}

				bo.setProdName(parseProdName(prodName));
				this.subDao.saveSub(bo);
				bo.setBasket(baskets);
				bo.setPayType(payTypeList);
				subList.add(bo);

				label478: EventHome.publishEvent(new OrderSaveEvent(bo));
			}

		}

		return subList;
	}

	private String parseProdName(String prodName) {
		String result = null;
		if (AppUtils.isNotBlank(prodName)) {
			if (prodName.length() < 1000)
				result = prodName;
			else
				result = prodName.substring(0, 996) + "...";

		}

		return result;
	}

	public List<Basket> getBasketBySubNumber(String subNumber) {
		return this.subDao.getBasketBySubNumber(subNumber);
	}

	private Sub makeSub(SubForm form) {
		Sub sub = new Sub();
		sub.setUserName(form.getUserName());
		sub.setSubDate(new Date());
		sub.setSubTel(form.getUserTel());
		sub.setSubPost(form.getUserPostcode());
		sub.setSubMail(form.getUserMail());
		sub.setSubAdds(form.getUserAdds());
		sub.setPayId(form.getPayType());
		sub.setOther(form.getOther());
		sub.setSubCheck("N");
		sub.setOrderName(form.getOrderName());
		return sub;
	}

	@Required
	public void setBasketDao(BasketDao basketDaoImpl) {
		this.basketDao = basketDaoImpl;
	}

	@Required
	public void setPayTypeService(PayTypeService payTypeService) {
		this.payTypeService = payTypeService;
	}

	public Sub getSubBySubNumber(String subNumber) {
		return this.subDao.getSubBySubNumber(subNumber);
	}

	public String getOrderDetail(HttpServletRequest request,
			HttpServletResponse response, Sub sub, String userName,
			String subNumber) {
		List subList = new ArrayList();

		MemberForm form = new MemberForm();
		form.setUserAdds(sub.getSubAdds());
		form.setUserPostcode(sub.getSubPost());
		form.setOrderName(sub.getUserName());
		form.setOther(sub.getOther());
		form.setUserTel(sub.getSubTel());
		form.setPayTypeName(sub.getPayTypeName());
		request.setAttribute("member", form);

		List baskets = this.subDao.getBasketBySubNumber(subNumber);
		if (!(AppUtils.isBlank(baskets))) {
			sub.setBasket(baskets);
			sub.setPayType(this.payTypeService.getPayTypeList(sub.getShopName()));
			subList.add(sub);

			request.setAttribute("subList", subList);
		}
		if (OrderStatusEnum.UNPAY.value().equals(sub.getStatus())) {
			UserDetail userdetail = this.userDetailDao.getUserDetail(userName);
			if (userdetail != null) {
				if (userdetail.getScore() == null)
					userdetail.setScore(Long.valueOf(-4648549147203338240L));

				request.setAttribute("availableScore", userdetail.getScore());
			}
		} else {
			request.setAttribute("availableScore",
					Long.valueOf(-4648550349794181120L));
		}
		return PathResolver.getPath(request, response, TilesPage.PAGE_SUB);
	}

	public String findOrder(HttpServletRequest request,
			HttpServletResponse response, String curPageNO, Sub entity,
			String userName, SubQueryForm subQueryForm) {
		getAndSetOneAdvertisement(request, response,
				ThreadLocalContext.getCurrentShopName(request, response),
				"USER_REG_ADV_950");
		String subNumber = entity.getSubNumber();
		if (AppUtils.isNotBlank(subNumber))
			subNumber = subNumber.trim();

		log.debug("find order userName {}, subNumber {}", userName, subNumber);

		QueryMap map = new QueryMap();
		map.put("subCheck", entity.getSubCheck());
		map.put("status", entity.getStatus());
		map.like("subNumber", entity.getSubNumber());
		map.put("userName", userName);

		String queryAllSQL = ConfigCode.getInstance().getCode(
				"biz.queryCountOrderList", map);
		String querySQL = ConfigCode.getInstance().getCode(
				"biz.queryOrderList", map);
		SimpleSqlQuery query = new SimpleSqlQuery(SubListForm.class, querySQL,
				queryAllSQL, map.toArray());

		query.setCurPage(curPageNO);
		query.setPageProvider(PageProviderEnum.SIMPLE_PAGE_PROVIDER);
		PageSupport ps = this.subDao.getOrder(query);
		request.setAttribute("offset", Integer.valueOf(ps.getOffset() + 1));
		request.setAttribute("curPageNO", curPageNO);
		request.setAttribute("toolBar", ps.getToolBar());
		List subListFormList = convertSubBasketToSub(ps.getResultList());
		Map subListFormMap = convertBasketMap(subListFormList);
		request.setAttribute("map", subListFormMap);
		request.setAttribute("subForm", entity);

		return PathResolver.getPath(request, response, TilesPage.ORDER);
	}

	protected List<SubListForm> convertSubBasketToSub(List<SubListForm> list) {
		if (AppUtils.isBlank(list))
			return null;

		Map map = new HashMap();
		for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
			SubListForm subListForm = (SubListForm) localIterator.next();
			SubListForm sub = (SubListForm) map.get(subListForm.getSubNumber());
			if (sub == null)
				sub = subListForm.clone();

			sub.addBasket(new Basket(subListForm.getBasketId(), subListForm
					.getCash(), subListForm.getCarriage(), subListForm
					.getProdId(), subListForm.getPic(), subListForm
					.getBasketCount(), subListForm.getProdName(), subListForm
					.getAttribute()));
			map.put(sub.getSubNumber(), sub);
		}
		return new ArrayList(map.values());
	}

	protected Map<String, List<SubListForm>> convertBasketMap(
			List<SubListForm> list) {
		if (AppUtils.isBlank(list))
			return null;

		Map map = new HashMap();
		for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
			SubListForm sub = (SubListForm) localIterator.next();
			List subList = (List) map.get(sub.getShopName());
			if (subList == null)
				subList = new ArrayList();

			subList.add(sub);
			map.put(sub.getShopName(), subList);
		}
		return map;
	}

	public void updateSub(Sub sub) {
		this.subDao.updateSub(sub);
	}

	public PageSupport getOrderList(CriteriaQuery cq) {
		return this.subDao.getOrder(cq);
	}

	public void setUserDetailDao(UserDetailDao userDetailDao) {
		this.userDetailDao = userDetailDao;
	}

	public Long getTotalProcessingOrder(String userName) {
		return this.subDao.getTotalProcessingOrder(userName);
	}

	public Sub getSubById(Long subId) {
		return this.subDao.getSubById(subId);
	}

	public boolean updateSubPrice(Sub sub, String userName, Double totalPrice) {
		return this.subDao.updateSubPrice(sub, userName, totalPrice);
	}

	public boolean updateSub(Sub sub, Integer status, String userName,
			String payTypeId) {
		return this.subDao.updateSub(sub, status, userName, payTypeId);
	}

	public boolean deleteSub(Sub sub) {
		return this.subDao.deleteSub(sub);
	}

	public void saveSubHistory(Sub sub, String subAction) {
		this.subDao.saveSubHistory(sub, subAction);
	}

	private CriteriaQuery constructCriteriaQuery(HttpServletRequest request,
			String curPageNO, Sub entity, String userName, String subNumber,
			SubQueryForm subQueryForm) {
		if (log.isDebugEnabled()) {
			log.debug(
					"orderType:{}, orderActiveStatus:{}, kwType:{}, kwText:{}, subNumber:{}",
					new Object[] { subQueryForm.getOrderType(),
							subQueryForm.getOrderActiveStatus(),
							subQueryForm.getKwType(), subQueryForm.getKwText(),
							subNumber });
		}

		CriteriaQuery cq = new CriteriaQuery(Sub.class, curPageNO,
				PageProviderEnum.SIMPLE_PAGE_PROVIDER);
		cq.setPageSize(((Integer) PropertiesUtil.getObject(
				SysParameterEnum.FRONT_PAGE_SIZE, Integer.class)).intValue());
		cq.eq("userName", userName);

		if (AppUtils.isNotBlank(subNumber)) {
			cq.like("subNumber", subNumber + "%");
		}

		cq.eq("status", entity.getStatus());
		cq.eq("subCheck", entity.getSubCheck());
		cq.addOrder("desc", "subDate");
		return cq;
	}

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}
}