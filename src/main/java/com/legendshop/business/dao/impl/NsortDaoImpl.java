package com.legendshop.business.dao.impl;

import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.jdbc.BaseJdbcDao;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.entity.Nsort;
import com.legendshop.spi.dao.NsortDao;
import com.legendshop.util.AppUtils;
import com.legendshop.util.constant.ProductTypeEnum;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;

public class NsortDaoImpl extends BaseDaoImpl implements NsortDao {
	private static Logger log = LoggerFactory.getLogger(NsortDaoImpl.class);
	private BaseJdbcDao baseJdbcDao;

	@Cacheable(value = { "Nsort" }, key = "#nsortId")
	public Nsort getNsort(Long nsortId) {
		log.debug("queryNsort,nsortId = {} ", nsortId);
		Nsort nsort = (Nsort) get(Nsort.class, nsortId);
		if (nsort != null)
			nsort.setSubSort(new HashSet(findByHQL(
					"from Nsort where parent_nsort_id = ? and status = 1",
					new Object[] { nsort.getNsortId() })));

		return nsort;
	}

	@Cacheable({ "NsortList" })
	public List<Nsort> getOtherNsortList(Long sortId, Long nsortId) {
		return findByHQL(
				"from Nsort where sortId = ? and nsortId <> ? and status = 1",
				new Object[] { sortId, nsortId });
	}

	public List<Nsort> getOthorNsort(List<Nsort> list) {
		List result = new ArrayList();
		for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
			Nsort nsort = (Nsort) localIterator.next();
			if (nsort.getParentNsortId() != null)
				result.add(nsort);
		}

		return result;
	}

	@Cacheable({ "NsortList" })
	public List<Nsort> getNsortList(Long sortId) {
		return findByHQL("from Nsort where sortId = ? and status = 1",
				new Object[] { sortId });
	}

	public List<Nsort> getOthorSubNsort(Long nsortId, List<Nsort> list) {
		List result = new ArrayList();
		for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
			Nsort nsort = (Nsort) localIterator.next();
			if ((nsort.getParentNsortId() == null)
					|| (!(nsort.getParentNsortId().equals(nsortId))))
				result.add(nsort);
		}

		return result;
	}

	@Cacheable({ "NsortList" })
	public List<Nsort> getNsortBySortId(Long sortId) {
		return findByHQL(
				"from Nsort where sortId = ? and status = 1 and parent_nsort_id is null",
				new Object[] { sortId });
	}

	@Cacheable(value = { "NsortList" }, key = "#nsortId")
	public List<Nsort> getSubNsortBySortId(Long nsortId) {
		return findByHQL("from Nsort where status = 1 and parent_nsort_id = ?",
				new Object[] { nsortId });
	}

	@Cacheable(value = { "NsortList" }, key = "#userName")
	public List<Nsort> getNavigationNsort(String userName) {
		return findByHQL(
				"select n from Nsort n,Sort s where n.sortId=s.id and n.status =1 and s.status =1 and s.userName=? and s.sortType=? and s.navigationMenu=?",
				new Object[] { userName, ProductTypeEnum.PRODUCT.value(),
						Integer.valueOf(1) });
	}

	@CacheEvict(value = { "Nsort" }, key = "#nsort.nsortId")
	public void updateNsort(Nsort nsort) {
		update(nsort);
	}

	@CacheEvict(value = { "Nsort" }, key = "#id")
	public void deleteNsortById(Long id) {
		exeByHQL("delete from NsortBrand where id.brandId = ?",
				new Object[] { id });
		delete(Nsort.class, id);
	}

	public List<KeyValueEntity> loadNSorts(Long sortId) {
		List list = getNsortBySortId(sortId);
		return convertToEntity(list);
	}

	public List<KeyValueEntity> loadSubNSorts(Long nsortId) {
		List list = getSubNsortBySortId(nsortId);
		return convertToEntity(list);
	}

	private List<KeyValueEntity> convertToEntity(List<Nsort> list) {
		if (AppUtils.isBlank(list))
			return null;

		List result = new ArrayList(list.size());
		for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
			Nsort nsort = (Nsort) localIterator.next();
			KeyValueEntity entity = new KeyValueEntity();
			entity.setKey(String.valueOf(nsort.getNsortId()));
			entity.setValue(nsort.getNsortName());
			result.add(entity);
		}
		return result;
	}

	public List<Nsort> getSubNsortBySortId(Long nsortId, String nsortName) {
		if (AppUtils.isBlank(nsortName))
			return getSubNsortBySortId(nsortId);

		return findByHQL(
				"from Nsort where status = 1 and parent_nsort_id = ? and nsortName like ?",
				new Object[] { nsortId, "%" + nsortName + "%" });
	}

	public String getUserNameByNsortId(Long subNsortId) {
		String sql = "select s.user_name as userName from ls_sort s inner join ls_nsort ns on s.sort_id = ns.sort_id where nsort_id  = ?";
		String result = (String) this.baseJdbcDao.getJdbcTemplate()
				.queryForObject(sql, String.class, new Object[] { subNsortId });
		return result;
	}

	public void setBaseJdbcDao(BaseJdbcDao baseJdbcDao) {
		this.baseJdbcDao = baseJdbcDao;
	}

	public boolean hasChildProduct(String userName, Long id, Long parentNsortId) {
		Long result = Long.valueOf(-4648542120636841984L);
		if (PropertiesUtil.isDefaultShopName(userName)) {
			if (AppUtils.isBlank(parentNsortId))
				result = (Long) findUniqueBy(
						"select count(*) from Product where globalNsort = ?",
						Long.class, new Object[] { id });
			else
				result = (Long) findUniqueBy(
						"select count(*) from Product where globalSubSort = ?",
						Long.class, new Object[] { id });

		} else if (AppUtils.isBlank(parentNsortId))
			result = (Long) findUniqueBy(
					"select count(*) from Product where nsortId = ?",
					Long.class, new Object[] { id });
		else {
			result = (Long) findUniqueBy(
					"select count(*) from Product where subNsortId = ?",
					Long.class, new Object[] { id });
		}

		return (result.longValue() > -4648542240895926272L);
	}
}