package com.legendshop.business.service.impl;

import com.legendshop.business.dao.BrandDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.dynamic.Item;
import com.legendshop.model.entity.Brand;
import com.legendshop.spi.service.BrandService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.JSONUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cache.annotation.CacheEvict;

public class BrandServiceImpl
  implements BrandService
{
  private BrandDao brandDao;

  @Required
  public void setBrandDao(BrandDao brandDao)
  {
    this.brandDao = brandDao;
  }

  public List<Brand> getBrand(String userName)
  {
    return this.brandDao.findByHQL("from Brand where userName = ?", new Object[] { userName });
  }

  public Brand getBrand(Long id)
  {
    return ((Brand)this.brandDao.get(Brand.class, id));
  }

  @CacheEvict(value={"Brand"}, key="#id")
  public void delete(Long id)
  {
    this.brandDao.exeByHQL("delete from NsortBrand where id.brandId = ?", new Object[] { id });
    this.brandDao.deleteBrandById(id);
  }

  public Long save(Brand brand)
  {
    if (!(AppUtils.isBlank(brand.getBrandId()))) {
      update(brand);
      return brand.getBrandId();
    }
    return ((Long)this.brandDao.save(brand));
  }

  @CacheEvict(value={"Brand"}, key="#brand.brandId")
  public void update(Brand brand)
  {
    this.brandDao.updateBrand(brand);
  }

  public List<KeyValueEntity> loadBrandEntityBySubSortId(Long subNsortId, String userName)
  {
    return convertToEntity(this.brandDao.loadBrandBySubSortId(subNsortId, userName));
  }

  public List<Brand> loadBrandBySubSortId(Long subNsortId, String userName)
  {
    return this.brandDao.loadBrandBySubSortId(subNsortId, userName);
  }

  public PageSupport getDataByCriteriaQuery(CriteriaQuery cq)
  {
    return this.brandDao.find(cq);
  }

  public String saveBrandItem(List<String> idList, Long nsortId, String userName)
  {
    return this.brandDao.saveBrandItem(idList, nsortId, userName);
  }

  public String saveBrandItem(String idJson, String nameJson, Long nsortId, String userName)
  {
    List idList = JSONUtil.getArray(idJson, String.class);
    return this.brandDao.saveBrandItem(idList, nsortId, userName);
  }

  public List<Item> getUsableBrandByName(Long nsortId, String userName, String brandName)
  {
    return this.brandDao.getUsableBrandByName(nsortId, userName, brandName);
  }

  public List<Item> getUsableBrand(Long nsortId, String userName)
  {
    List list = new ArrayList();
    try {
      list = this.brandDao.getUsableBrand(nsortId, userName);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public List<Item> getUsedBrand(Long nsortId, String userName)
  {
    return this.brandDao.getUsedBrand(nsortId, userName);
  }

  private List<KeyValueEntity> convertToEntity(List<Brand> list)
  {
    if (AppUtils.isBlank(list))
      return null;

    List result = new ArrayList(list.size());
    for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) { Brand brand = (Brand)localIterator.next();
      KeyValueEntity entity = new KeyValueEntity();
      entity.setKey(String.valueOf(brand.getBrandId()));
      entity.setValue(brand.getBrandName());
      result.add(entity);
    }
    return result;
  }

  public List<Brand> loadBrandByName(Long subNsortId, String shopName, String brandName)
  {
    return this.brandDao.loadBrandByName(subNsortId, shopName, brandName);
  }

  public boolean hasChildProduct(String userName, Long brandId)
  {
    return this.brandDao.hasChildProduct(userName, brandId);
  }
}