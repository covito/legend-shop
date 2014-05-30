package com.legendshop.business.dao.impl;

import com.legendshop.model.entity.ProductDetail;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ProductJdbcDaoImpl extends ProductDaoImpl {
	private static Logger log = LoggerFactory
			.getLogger(ProductJdbcDaoImpl.class);
	private JdbcTemplate jdbcTemplate;

	@Cacheable(value = { "ProductDetail" }, key = "#prodId")
	public ProductDetail getProdDetail(Long prodId) {
		List list = null;
		String sql = ConfigCode.getInstance().getCode("prod.getProdDetail");
		log.debug("getProdDetail run sql {}, prodId = {}", sql, prodId);
		list = this.jdbcTemplate.query(sql, new Object[] { prodId },
				new ProductDetailRowMapper());
		if (AppUtils.isBlank(list))
			return null;

		return ((ProductDetail) list.get(0));
	}

	public List<ProductDetail> getProdDetail(List<Object> prodIds) {
		List products = new ArrayList();
		for (Iterator localIterator = prodIds.iterator(); localIterator
				.hasNext();) {
			Object prodId = localIterator.next();
			products.add(getProdDetail(Long.valueOf(Long.parseLong(prodId
					.toString()))));
		}
		return products;
	}

	public List<ProductDetail> getProdDetail(Long[] prodId) {
		List postIdList = new ArrayList();
		StringBuffer sql = new StringBuffer(ConfigCode.getInstance().getCode(
				"prod.getProdDetailList"));
		for (int i = 0; i < prodId.length; ++i)
			if (prodId[i] != null) {
				sql.append("?,");
				postIdList.add(prodId[i]);
			}

		if (postIdList.isEmpty()) {
			return new ArrayList();
		}

		sql.setLength(sql.length() - 1);
		sql.append(")");
		if (log.isDebugEnabled()) {
			log.debug("getProdDetail run sql {}, param {}", sql.toString(),
					postIdList.toArray());
		}

		return this.jdbcTemplate.query(sql.toString(), postIdList.toArray(),
				new ProductDetailRowMapper());
	}

	public boolean hasOrder(Long prodId) {
		int result = this.jdbcTemplate
				.queryForInt(
						"select count(*) from ls_sub s, ls_basket b where s.sub_number = b. sub_number and b.prod_id = ?",
						new Object[] { prodId });
		return (result > 0);
	}

	@Required
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	class ProductDetailRowMapper implements RowMapper<ProductDetail> {
		public ProductDetail mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			ProductDetail product = new ProductDetail();
			product.setProdId(Long.valueOf(rs.getLong("prod_id")));
			product.setSortId(Long.valueOf(rs.getLong("sort_id")));
			product.setNsortId(Long.valueOf(rs.getLong("nsort_id")));
			product.setSubNsortId(Long.valueOf(rs.getLong("sub_nsort_id")));
			product.setName(rs.getString("name"));
			product.setPrice(Double.valueOf(rs.getDouble("price")));
			product.setCash(Double.valueOf(rs.getDouble("cash")));
			product.setProxyPrice(Double.valueOf(rs.getDouble("proxy_price")));
			product.setCarriage(Double.valueOf(rs.getDouble("carriage")));
			product.setBrief(rs.getString("brief"));
			product.setContent(rs.getString("content"));
			product.setViews(Integer.valueOf(rs.getInt("views")));
			product.setBuys(Integer.valueOf(rs.getInt("buys")));
			product.setRecDate(rs.getDate("rec_date"));
			product.setPic(rs.getString("pic"));
			product.setCommend(rs.getString("commend"));
			product.setStatus(Integer.valueOf(rs.getInt("status")));
			product.setModifyDate(rs.getDate("modify_date"));
			product.setUserId(rs.getString("user_id"));
			product.setUserName(rs.getString("user_name"));
			product.setStartDate(rs.getDate("start_date"));
			product.setEndDate(rs.getDate("end_date"));
			product.setStocks(Integer.valueOf(rs.getInt("stocks")));
			product.setProdType(rs.getString("prod_type"));
			product.setKeyWord(rs.getString("key_word"));
			product.setAttribute(rs.getString("attribute"));
			product.setParameter(rs.getString("parameter"));
			product.setBrandId(Long.valueOf(rs.getLong("brand_id")));
			product.setSortName(rs.getString("sort_name"));
			product.setNsortName(rs.getString("nsort_name"));
			product.setSubNsortName(rs.getString("sub_nsort_name"));
			product.setBrandName(rs.getString("brand_name"));
			product.setProdType(rs.getString("prod_type"));
			product.setModelId(rs.getString("model_id"));
			return product;
		}
	}
}