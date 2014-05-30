package com.legendshop.business.service.impl;

import com.legendshop.business.dao.HotsearchDao;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.ConflictException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.ProdSearchArgs;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.constants.PageADV;
import com.legendshop.spi.dao.NsortDao;
import com.legendshop.spi.dao.ProductDao;
import com.legendshop.spi.dao.SortDao;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.SortService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.constant.ProductTypeEnum;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class SortServiceImpl extends BaseServiceImpl
  implements SortService
{
  private final Logger log = LoggerFactory.getLogger(SortServiceImpl.class);
  protected SortDao sortDao;
  protected NsortDao nsortDao;
  protected ProductDao productDao;
  protected HotsearchDao hotsearchDao;

  public Sort getSortById(Long id)
  {
    return this.sortDao.getSort(id);
  }

  public void deleteSort(Long sortId)
  {
    List list = this.sortDao.getProductBySortId(sortId);
    if (!(AppUtils.isBlank(list))) {
      throw new ConflictException("请删除该类型对应的商品");
    }

    List nsortList = this.sortDao.getNsortBySortId(sortId);
    if (!(AppUtils.isBlank(nsortList)))
      throw new ConflictException("请删除该类型对应的二级商品类型");

    this.sortDao.deleteSortById(sortId);
  }

  public Long save(Sort sort)
  {
    if (!(AppUtils.isBlank(sort.getSortId()))) {
      updateSort(sort);
      return sort.getSortId();
    }
    return this.sortDao.saveSort(sort);
  }

  public void updateSort(Sort sort)
  {
    this.sortDao.updateSort(sort);
  }

  public PageSupport getSortList(CriteriaQuery cq)
  {
    return this.sortDao.find(cq);
  }

  public List<Sort> getSort(String shopName, Boolean loadAll)
  {
    return this.sortDao.getSort(shopName, ProductTypeEnum.PRODUCT.value(), null, null, loadAll);
  }

  public void delete(Sort sort)
  {
    if (this.sortDao.hasChildNsort(sort.getSortId())) {
      throw new ConflictException("发现子商品分类，不能删除该商品分类！");
    }

    if (this.sortDao.hasChildProduct(sort.getUserName(), sort.getSortId())) {
      throw new ConflictException("商品分类下有产品, 不能删除该商品分类！");
    }

    this.sortDao.deleteSort(sort);
  }

  @Required
  public void setSortDao(SortDao sortDao)
  {
    this.sortDao = sortDao;
  }

  public List<Sort> getSort(String name, String sortType, Boolean loadAll)
  {
    return this.sortDao.getSort(name, sortType, null, null, loadAll);
  }

  public List<Sort> getSort(String userName, String sortType, String sortName)
  {
    return this.sortDao.getSort(userName, sortType, sortName);
  }

  public List<Sort> getSort(String name, String sortType, Integer headerMenu, Integer navigationMenu, Boolean loadAll)
  {
    return this.sortDao.getSort(name, sortType, headerMenu, navigationMenu, loadAll);
  }

  public String getSecSort(HttpServletRequest request, HttpServletResponse response, Sort sort, Long nsortId, Long subNsortId)
  {
    String curPageNO = request.getParameter("curPageNO");
    if (curPageNO == null) {
      curPageNO = "1";
    }

    getAndSetAdvertisement(request, response, sort.getUserName(), PageADV.SORT.name());
    request.setAttribute("sort", sort);

    Nsort nsort = this.nsortDao.getNsort(nsortId);
    if ((nsort != null) && (!(AppUtils.isBlank(nsort.getSubSort())))) {
      request.setAttribute("hasSubSort", Boolean.valueOf(true));
    }

    if (subNsortId != null) {
      Nsort subNsort = this.nsortDao.getNsort(subNsortId);
      request.setAttribute("subNsort", subNsort);
      if (subNsort != null) {
        request.setAttribute("CurrentSubNsortId", subNsort.getNsortId());
      }

    }

    List nsortList = this.nsortDao.getNsortList(sort.getSortId());
    request.setAttribute("nsort", nsort);

    request.setAttribute("nsortList", this.nsortDao.getOthorNsort(nsortList));
    request.setAttribute("subNsortList", this.nsortDao.getOthorSubNsort(nsortId, nsortList));
    if (nsort != null)
      request.setAttribute("CurrentNsortId", nsort.getNsortId());

    PageSupport ps = this.productDao.getProdDetail(curPageNO, sort.getSortId(), nsortId, subNsortId, Boolean.valueOf(PropertiesUtil.getDefaultShopName().equals(sort.getUserName())));
    ps.savePage(request);

    return PathResolver.getPath(request, response, TilesPage.NSORT);
  }

  public void setNsortDao(NsortDao nsortDao)
  {
    this.nsortDao = nsortDao;
  }

  public void setProductDao(ProductDao productDao)
  {
    this.productDao = productDao;
  }

  public String parseSort(HttpServletRequest request, HttpServletResponse response, String curPageNO, Sort sort)
  {
    if (curPageNO == null)
      curPageNO = "1";

    getAndSetAdvertisement(request, response, sort.getUserName(), PageADV.SORT.name());

    List searchList = this.hotsearchDao.getHotsearch(sort.getSortId());
    request.setAttribute("hotProdList", searchList);

    List nsortList = this.nsortDao.getNsortBySortId(sort.getSortId());
    request.setAttribute("sort", sort);
    request.setAttribute("nsortList", nsortList);
    String userName = UserManager.getUserName(request.getSession());

    if (this.log.isInfoEnabled())
      this.log.info("[{}],{},{},sort", new Object[] { request.getRemoteAddr(), (userName == null) ? "" : userName, 
        ThreadLocalContext.getCurrentShopName(request, response) });

    PageSupport ps = null;
    if (PropertiesUtil.getDefaultShopName().equals(sort.getUserName()))
      ps = this.productDao.getGlobalProdDetail(curPageNO, sort.getSortId());
    else {
      ps = this.productDao.getProdDetail(curPageNO, sort.getSortId());
    }

    ps.savePage(request);
    return PathResolver.getPath(request, response, TilesPage.PRODUCT_SORT);
  }

  public void setHotsearchDao(HotsearchDao hotsearchDao)
  {
    this.hotsearchDao = hotsearchDao;
  }

  public Sort getSortAndBrand(Long sortId)
  {
    Sort sort = this.sortDao.getSort(sortId);
    List brandList = this.sortDao.getBrandList(sortId);
    if (AppUtils.isNotBlank(brandList))
      sort.setBrandList(brandList);

    return sort;
  }

  public String sortList(HttpServletRequest request, HttpServletResponse response, Sort sort)
  {
    return null;
  }

  public String parseSort(HttpServletRequest request, HttpServletResponse response, String curPageNO, Long sortId, ProdSearchArgs args)
  {
    return null;
  }

  public String parseSecSort(HttpServletRequest request, HttpServletResponse response, String curPageNO, Long sortId, Long nsortId, Long subNsortId, ProdSearchArgs args)
  {
    return null;
  }

  public List<KeyValueEntity> loadSorts(String shopName, String sortType)
  {
    List sortList = this.sortDao.getSort(shopName, sortType, null, null, Boolean.valueOf(true));
    if (AppUtils.isBlank(sortList))
      return null;

    List result = new ArrayList(sortList.size());
    for (Iterator localIterator = sortList.iterator(); localIterator.hasNext(); ) { Sort sort = (Sort)localIterator.next();
      KeyValueEntity entity = new KeyValueEntity();
      entity.setKey(String.valueOf(sort.getSortId()));
      entity.setValue(sort.getSortName());
      result.add(entity);
    }
    return result;
  }

  public List<KeyValueEntity> loadNSorts(Long sortId)
  {
    return this.nsortDao.loadNSorts(sortId);
  }

  public List<KeyValueEntity> loadSubNSorts(Long nsortId)
  {
    return this.nsortDao.loadSubNSorts(nsortId);
  }

  public List<Nsort> getNsortBySortId(Long sortId)
  {
    return this.sortDao.getNsortBySortId(sortId);
  }

  public List<Nsort> getNsortBySortId(Long sortId, String nsortName)
  {
    return this.sortDao.getNsortBySortId(sortId, nsortName);
  }

  public List<Nsort> getSubNsortBySortId(Long nsortId, String nsortName)
  {
    return this.nsortDao.getSubNsortBySortId(nsortId, nsortName);
  }
}