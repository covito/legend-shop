package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.BrandDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.dynamic.Item;
import com.legendshop.model.entity.Brand;
import com.legendshop.model.entity.NsortBrand;
import com.legendshop.model.entity.NsortBrandId;
import com.legendshop.util.AppUtils;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrandDaoImpl extends BaseDaoImpl
  implements BrandDao
{
  private static Logger log = LoggerFactory.getLogger(BrandDaoImpl.class);

  public List<Item> getUsableBrand(Long nsortId, String userName)
  {
    log.debug("getUsableBrand nsortId = {},userName = {} ", nsortId, userName);
    return findByHQLLimit(
      "select new com.legendshop.model.dynamic.Item(b.brandId , b.brandName) from Brand b where not exists ( select n.userName from NsortBrand n where b.brandId = n.id.brandId and  n.id.nsortId = ? ) and b.userName = ? ", 
      0, 30, new Object[] { nsortId, userName });
  }

  public List<Item> getUsableBrandByName(Long nsortId, String userName, String brandName)
  {
    return findByHQLLimit(
      "select new com.legendshop.model.dynamic.Item(b.brandId , b.brandName) from Brand b where brandName like ? and not exists ( select n.userName from NsortBrand n where b.brandId = n.id.brandId and  n.id.nsortId = ? ) and b.userName = ? ", 
      0, 30, new Object[] { brandName, nsortId, userName });
  }

  public List<Item> getUsedBrand(Long nsortId, String userName)
  {
    return findByHQLLimit(
      "select new com.legendshop.model.dynamic.Item(b.brandId , b.brandName) from Brand b where exists ( select n.userName from NsortBrand n where b.brandId = n.id.brandId and  n.id.nsortId = ? )  and b.userName = ?", 
      0, 30, new Object[] { nsortId, userName });
  }

  public String saveBrandItem(List<String> idList, Long nsortId, String userName)
  {
    List list = findByHQL("from NsortBrand n where n.id.nsortId = ? and userName = ?", new Object[] { nsortId, userName });
    deleteAll(list);
    if (AppUtils.isNotBlank(idList))
      for (Iterator localIterator = idList.iterator(); localIterator.hasNext(); ) { String brandId = (String)localIterator.next();
        NsortBrand nb = new NsortBrand();
        NsortBrandId id = new NsortBrandId();
        id.setBrandId(Long.valueOf(brandId));
        id.setNsortId(nsortId);
        nb.setId(id);
        nb.setUserName(userName);
        save(nb);
      }

    return null;
  }

  public void deleteBrandById(Long id)
  {
    deleteById(Brand.class, id);
  }

  public void updateBrand(Brand brand)
  {
    update(brand);
  }

  public List<Brand> loadBrandBySubSortId(Long subNsortId, String userName)
  {
    List list = findByHQL("select new Brand(b.brandId,b.brandName) from NsortBrand nb, Brand b where nb.id.brandId = b.brandId and nb.id.nsortId = ?  and b.status = 1 and b.userName = ?", new Object[] { subNsortId, userName });
    return list;
  }

  public List<Brand> loadBrandByName(Long subNsortId, String shopName, String brandName)
  {
    if (AppUtils.isBlank(brandName))
      return loadBrandBySubSortId(subNsortId, shopName);

    List list = findByHQL("select new Brand(b.brandId,b.brandName) from NsortBrand nb, Brand b where nb.id.brandId = b.brandId and nb.id.nsortId = ?  and b.status = 1 and b.userName = ? and b.brandName like ?", new Object[] { subNsortId, shopName, "%" + brandName + "%" });
    return list;
  }

  public boolean hasChildProduct(String userName, Long brandId)
  {
    Long result = Long.valueOf(-4648541983197888512L);
    if (PropertiesUtil.isDefaultShopName(userName))
      result = (Long)findUniqueBy("select count(*) from Product where globalBrand = ?", Long.class, new Object[] { brandId });
    else
      result = (Long)findUniqueBy("select count(*) from Product where brandId = ?", Long.class, new Object[] { brandId });

    return (result.longValue() > -4648543202968600576L);
  }
}