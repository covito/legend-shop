package com.legendshop.business.dao.impl;

import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Brand;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.dao.SortDao;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SortDaoImpl extends BaseDaoImpl implements SortDao {
	private static Logger log = LoggerFactory.getLogger(SortDaoImpl.class);
	private JdbcTemplate jdbcTemplate;

	@Cacheable(value = { "Sort" }, key = "#sortId")
	public Sort getSort(Long sortId) {
		return ((Sort) get(Sort.class, sortId));
	}

	@CacheEvict(value = { "Sort" }, key = "#sortId")
	public void deleteSortById(Long sortId) {
		deleteById(Sort.class, sortId);
	}

	@CacheEvict(value = { "Sort" }, key = "#sort.sortId")
	public void updateSort(Sort sort) {
		update(sort);
	}

	public Long saveSort(Sort sort) {
		return ((Long) save(sort));
	}

	public List<Product> getProductBySortId(Long sortId) {
		return findByHQL("from Product where sortId = ?",
				new Object[] { sortId });
	}

	public List<Nsort> getNsortBySortId(Long sortId) {
		return findByHQL("from Nsort where sortId = ?", new Object[] { sortId });
	}

	@CacheEvict(value = { "Sort" }, key = "#sort.sortId")
	public void deleteSort(Sort sort) {
		delete(sort);
	}

	@Cacheable(value = { "SortList" }, key = "#shopName + #sortType + #headerMenu + #navigationMenu  + #loadAll")
	public List<Sort> getSort(String shopName, String sortType,
			Integer headerMenu, Integer navigationMenu, Boolean loadAll) {
		log.debug("getSort, shopName = {}, loadAll = {}", shopName, loadAll);
		List paramList = new ArrayList();
		String hql = "from Sort where userName = ? ";
		paramList.add(shopName);

		if (sortType != null) {
			hql = hql + " and sortType = ? ";
			paramList.add(sortType);
		}
		if (headerMenu != null) {
			hql = hql + " and headerMenu = ? ";
			paramList.add(headerMenu);
		}
		if (navigationMenu != null) {
			hql = hql + " and navigationMenu = ? ";
			paramList.add(navigationMenu);
		}
		hql = hql + " order by seq ";
		List list = findByHQLLimit(hql, 0, 1000, paramList.toArray());
		if (loadAll.booleanValue()) {
			loadAllNsort(shopName, sortType, list);
		}
		return list;
	}

	private void loadAllNsort(String shopName, String sortType, List<Sort> list) {
		Nsort n2;
		if (list == null) {
			return;
		}

		List nsortList = null;
		if (AppUtils.isNotBlank(sortType))
			nsortList = findByHQL(
					"select n from Nsort n, Sort s where n.sortId = s.sortId  and n.status = 1 and s.status = 1 and  s.userName = ? and s.sortType = ?",
					new Object[] { shopName, sortType });
		else {
			nsortList = findByHQL(
					"select n from Nsort n, Sort s where n.sortId = s.sortId  and n.status = 1 and s.status = 1 and  s.userName = ? ",
					new Object[] { shopName });
		}

		for (int i = 0; i < nsortList.size(); ++i) {
			Nsort n1 = (Nsort) nsortList.get(i);
			for (int j = i; j < nsortList.size(); ++j) {
				n2 = (Nsort) nsortList.get(j);
				n1.addSubSort(n2);
				n2.addSubSort(n1);
			}
		}

		Iterator n1 = list.iterator();
		while (n1.hasNext()) {
			Sort sort = (Sort) n1.next();
			for (Iterator i2 = nsortList.iterator(); i2.hasNext();) {
				Nsort nsort = (Nsort) i2.next();
				sort.addSubSort(nsort);
			}
		}
	}

	public List<Brand> getBrandList(Long sortId) {
		String sql = ConfigCode.getInstance().getCode("biz.QueryBrandInSort");
		return this.jdbcTemplate.query(sql, new Object[] { sortId },
				new BrandRowMapper());
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Sort> getSort(String userName, String sortType, String sortName) {
		List list = null;
		if (AppUtils.isBlank(sortName))
			list = findByHQLLimit(
					"from Sort s where  s.status = 1 and  s.userName = ? and s.sortType = ?",
					0, 1000, new Object[] { userName, sortType });
		else {
			list = findByHQLLimit(
					"from Sort s where  s.status = 1 and  s.userName = ? and s.sortType = ? and s.sortName like ?",
					0, 1000, new Object[] { userName, sortType,
							"%" + sortName + "%" });
		}

		loadAllNsort(userName, sortType, list);
		return list;
	}

	public List<Nsort> getNsortBySortId(Long sortId, String nsortName) {
		if (AppUtils.isBlank(nsortName))
			return findByHQL(
					"from Nsort where status = 1 and parentNsortId is null and sortId = ?",
					new Object[] { sortId });

		return findByHQL(
				"from Nsort where  status = 1 and sortId = ? and  parentNsortId is null and nsortName like ?",
				new Object[] { sortId, "%" + nsortName + "%" });
	}

	public boolean hasChildNsort(Long sortId) {
		Long result = (Long) findUniqueBy(
				"select count(*) from Nsort where sortId = ?", Long.class,
				new Object[] { sortId });
		return (result.longValue() > -4648543202968600576L);
	}

	public boolean hasChildProduct(String userName, Long sortId) {
		Long result = Long.valueOf(-4648541983197888512L);
		if (PropertiesUtil.isDefaultShopName(userName))
			result = (Long) findUniqueBy(
					"select count(*) from Product where globalSort = ?",
					Long.class, new Object[] { sortId });
		else
			result = (Long) findUniqueBy(
					"select count(*) from Product where sortId = ?",
					Long.class, new Object[] { sortId });

		return (result.longValue() > -4648543202968600576L);
	}

	class BrandRowMapper implements RowMapper<Brand> {
		public Brand mapRow(ResultSet rs, int rowNum) throws SQLException {
			Brand brand = new Brand();
			brand.setBrandId(Long.valueOf(rs.getLong("brandId")));
			brand.setSortId(Long.valueOf(rs.getLong("sortId")));
			brand.setBrandName(rs.getString("brandName"));
			return brand;
		}
	}
}