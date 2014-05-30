package com.legendshop.business.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.StatusKeyValueEntity;
import com.legendshop.model.entity.Basket;
import com.legendshop.util.AppUtils;
import com.legendshop.util.Arith;
import com.legendshop.util.TimerUtil;
import com.legendshop.util.des.DES2;

public class CommonServiceUtil extends FoundationUtil {
	private static Logger log = LoggerFactory
			.getLogger(CommonServiceUtil.class);

	public static synchronized String getSubNember(String userName) {
		String subNumber = "";
		String now = TimerUtil.dateToStr(new Date());
		subNumber = now;
		subNumber = subNumber.replace("-", "");
		subNumber = subNumber.replace(" ", "");
		subNumber = subNumber.replace(":", "");
		Random r = new Random();
		subNumber = subNumber + randomNumeric(r, 8);
		return subNumber;
	}

	private static String randomNumeric(Random random, int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; ++i) {
			int val = random.nextInt(10);
			sb.append(String.valueOf(val));
		}
		return sb.toString();
	}

	public static int random(int count) {
		Random random = new Random();
		return random.nextInt(count);
	}

	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 10000; ++i) {
			int re = random(2);
			System.out.println(re);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("t2 - t1 = " + (t2 - t1));
	}

	public static Double calculateTotalCash(List<Basket> baskets) {
		if (AppUtils.isNotBlank(baskets)) {
			Double totalcash = Double.valueOf(0D);
			for (Iterator localIterator = baskets.iterator(); localIterator
					.hasNext();) {
				Basket bo = (Basket) localIterator.next();
				try {
					double total = Arith.mul(bo.getBasketCount().intValue(), bo
							.getCash().doubleValue());
					bo.setTotal(Double.valueOf(total));
					if (bo.getCarriage() != null) {
						totalcash = Double
								.valueOf(Arith.add(totalcash.doubleValue(), bo
										.getCarriage().doubleValue()));
						totalcash = Double.valueOf(Arith.add(totalcash
								.doubleValue(), bo.getTotal().doubleValue()));
					}
					totalcash = Double.valueOf(Arith.add(totalcash
							.doubleValue(), bo.getTotal().doubleValue()));
				} catch (Exception e) {
					log.error("calculateTotalCash ", e);
				}
			}
			return totalcash;
		}
		return Double.valueOf(0D);
	}

	public static Double calculateTotalCash(Map<String, Basket> basketMap) {
		Double totalcash = Double.valueOf(0D);
		for (Iterator localIterator = basketMap.values().iterator(); localIterator
				.hasNext();) {
			Basket basket = (Basket) localIterator.next();
			try {
				double total = Arith.mul(basket.getBasketCount().intValue(),
						basket.getCash().doubleValue());
				if (basket.getCarriage() != null)
					total = Arith
							.add(total, basket.getCarriage().doubleValue());

				basket.setTotal(Double.valueOf(total));
				totalcash = Double.valueOf(Arith.add(totalcash.doubleValue(),
						basket.getTotal().doubleValue()));
			} catch (Exception e) {
				log.error("convert count", e);
			}
		}
		return totalcash;
	}

	public static Integer generateRandom() {
		Random r = new Random();
		return new Integer(r.nextInt(100000));
	}

	public static DES2 getDES() {
		return new DES2("!23done!");
	}

	public static List<StatusKeyValueEntity> convertKeyValueEntity(
			List<StatusKeyValueEntity> list, String selected) {
		List result = new ArrayList();
		if (AppUtils.isNotBlank(list))
			for (Iterator localIterator = list.iterator(); localIterator
					.hasNext();) {
				StatusKeyValueEntity key = (StatusKeyValueEntity) localIterator
						.next();
				StatusKeyValueEntity entity = new StatusKeyValueEntity();
				entity.setKey(key.getKey());
				String value = key.getValue();
				value = ResourceBundleHelper.getString(key.getValue(),
						key.getValue());
				entity.setValue(value);
				if (key.getKey().equals(selected))
					entity.setStatus("selected");

				result.add(entity);
			}

		return result;
	}

	public static List<StatusKeyValueEntity> convertKeyValueEntity(
			List<StatusKeyValueEntity> list) {
		return convertKeyValueEntity(list, null);
	}

	public static void setBasketTotalCount(HttpSession session, int count) {
		session.setAttribute("BASKET_TOTAL_COUNT", Integer.valueOf(count));
	}

	public static void calBasketTotalCount(HttpSession session, int count) {
		Integer totalCount = (Integer) session
				.getAttribute("BASKET_TOTAL_COUNT");
		Integer result = Integer.valueOf(totalCount.intValue() + count);
		if (result.intValue() >= 0)
			session.setAttribute("BASKET_TOTAL_COUNT", result);
	}

	public static String getBasketKey(String shopName, Long prodId,
			String attribute) {
		StringBuffer sb = new StringBuffer();
		sb.append(shopName).append(prodId).append(AppUtils.getCRC32(attribute));
		return sb.toString();
	}
}