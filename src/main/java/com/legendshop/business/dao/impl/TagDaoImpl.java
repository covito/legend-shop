package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.TagDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.model.entity.Sort;
import com.legendshop.model.entity.Tag;
import com.legendshop.spi.dao.AdvertisementDao;
import com.legendshop.spi.dao.ProductDao;
import com.legendshop.spi.dao.SortDao;
import com.legendshop.util.AppUtils;
import com.legendshop.util.constant.ProductTypeEnum;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class TagDaoImpl extends BaseDaoImpl implements TagDao {
	private SortDao sortDao;
	private AdvertisementDao advertisementDao;
	private ProductDao productDao;

	@Cacheable({ "TagList" })
	public List<Tag> getPageTag(String userName, String page) {
		List tagList = findByHQL("from Tag where userName = ?",
				new Object[] { userName });

		List sortList = this.sortDao.getSort(userName,
				ProductTypeEnum.PRODUCT.value(), null, null,
				Boolean.valueOf(true));
		Map advList = this.advertisementDao.getAdvertisement(userName, page);
		if (AppUtils.isNotBlank(tagList))
			for (int i = 0; i < tagList.size(); ++i) {
				Tag tag = (Tag) tagList.get(i);

				for (Iterator localIterator = sortList.iterator(); localIterator
						.hasNext();) {
					Sort sort = (Sort) localIterator.next();
					if (!(tag.getSortId().equals(sort.getSortId())))
						tag.setSort(sort);
				}

				List pageAdv = (List) advList.get(page + "_ADV_" + (i + 1));
				if (pageAdv != null) {
					tag.setAdvertisementList(pageAdv);
				}

				List commendProdList = this.productDao.getCommendProdBySort(
						userName, tag.getSortId(), 8);
				tag.setCommendProdList(commendProdList);
			}

		return tagList;
	}

	public Tag getTag(Long id) {
		return ((Tag) get(Tag.class, id));
	}

	@CacheEvict(value = { "Tag" }, key = "#tag.tagId")
	public void deleteTag(Tag tag) {
		delete(tag);
	}

	public Long saveTag(Tag tag) {
		return ((Long) save(tag));
	}

	@CacheEvict(value = { "Tag" }, key = "#tag.tagId")
	public void updateTag(Tag tag) {
		update(tag);
	}

	public PageSupport getTag(CriteriaQuery cq) {
		return find(cq);
	}

	public PageSupport getTag(SimpleHqlQuery hql) {
		hql.initSQL("biz.QueryTag", "biz.QueryTagCount");
		return find(hql);
	}

	public Tag getTag(String tagName, String userName) {
		List result = findByHQL("from Tag where name = ? and userName = ?",
				new Object[] { tagName, userName });
		if (AppUtils.isNotBlank(result))
			return ((Tag) result.get(0));

		return null;
	}

	public void setSortDao(SortDao sortDao) {
		this.sortDao = sortDao;
	}

	public void setAdvertisementDao(AdvertisementDao advertisementDao) {
		this.advertisementDao = advertisementDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
}