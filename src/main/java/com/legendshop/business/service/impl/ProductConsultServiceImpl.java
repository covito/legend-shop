package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ProductConsultDao;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.PageProviderEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.QueryMap;
import com.legendshop.core.dao.support.SimpleSqlQuery;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductConsult;
import com.legendshop.spi.dao.ProductDao;
import com.legendshop.spi.service.ProductConsultService;
import com.legendshop.util.SafeHtml;
import com.legendshop.util.sql.ConfigCode;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ProductConsultServiceImpl extends BaseServiceImpl
  implements ProductConsultService
{
  private ProductConsultDao productConsultDao;
  private ProductDao productDao;

  public List<ProductConsult> getProductConsultList(Long prodId)
  {
    return this.productConsultDao.getProductConsultList(prodId);
  }

  public ProductConsult getProductConsult(Long id)
  {
    return this.productConsultDao.getProductConsult(id);
  }

  public String deleteProductConsult(HttpServletRequest request, Long consId)
  {
    String userName = UserManager.getUserName(request.getSession());
    ProductConsult consult = this.productConsultDao.getProductConsult(consId);
    Product product = this.productDao.getProduct(consult.getProdId());
    String result = checkPrivilege(request, userName, product.getUserName());
    if (result == null)
      this.productConsultDao.deleteProductConsult(consult);

    return result;
  }

  public Long saveProductConsult(ProductConsult productConsult)
  {
    return this.productConsultDao.saveProductConsult(productConsult);
  }

  public String updateProductConsult(HttpServletRequest request, ProductConsult productConsult)
  {
    ProductConsult consult = this.productConsultDao.getProductConsult(productConsult.getConsId());
    if (consult == null)
      throw new NotFoundException("ProductConsult Not Found");

    String userName = UserManager.getUserName(request.getSession());
    Product product = this.productDao.getProduct(consult.getProdId());
    String result = checkPrivilege(request, userName, product.getUserName());
    if (result == null) {
      SafeHtml safe = new SafeHtml();
      consult.setAnswer(safe.makeSafe(productConsult.getAnswer()));
      consult.setAnsUserName(userName);
      consult.setAnswertime(new Date());
      this.productConsultDao.updateProductConsult(consult);
    }
    return result;
  }

  public PageSupport getProductConsult(String curPageNO, ProductConsult productConsult)
  {
    return this.productConsultDao.getProductConsult(resolveQuery(curPageNO, productConsult));
  }

  public PageSupport getProductConsultForList(String curPageNO, Integer pointType, Long prodId)
  {
    QueryMap map = new QueryMap();
    if ((pointType != null) && (pointType.intValue() != 0))
      map.put("pointType", pointType);

    map.put("prodId", prodId);
    String queryAllSQL = ConfigCode.getInstance().getCode("prod.getProductConsultListCount", map);
    String querySQL = ConfigCode.getInstance().getCode("prod.getProductConsultList", map);
    SimpleSqlQuery query = new SimpleSqlQuery(ProductConsult.class, querySQL, queryAllSQL, map.toArray());
    query.setMyaction("javascript:consultPager");
    query.setCurPage(curPageNO);
    query.setPageProvider(PageProviderEnum.SIMPLE_PAGE_PROVIDER);
    return this.productConsultDao.getProductConsult(query);
  }

  private SimpleSqlQuery resolveQuery(String curPageNO, ProductConsult productConsult) {
    QueryMap map = new QueryMap();
    map.put("pointType", productConsult.getPointType());

    if (productConsult.getReplyed() != null)
      if (productConsult.getReplyed().intValue() == 0)
        map.put("replyed", "and pc.answer is null");
      else
        map.put("replyed", "and pc.answer is not null");

    else {
      map.put("replyed", "and pc.answer is null");
    }

    map.hasAllDataFunction("userName", productConsult.getUserName());
    map.like("prodName", productConsult.getProdName());
    map.put("startTime", productConsult.getStartTime());
    map.put("endTime", productConsult.getEndTime());

    String queryAllSQL = ConfigCode.getInstance().getCode("prod.getProductConsultCount", map);
    String querySQL = ConfigCode.getInstance().getCode("prod.getProductConsult", map);
    map.remove("replyed");
    Object[] params = map.toArray();
    SimpleSqlQuery query = new SimpleSqlQuery(ProductConsult.class, querySQL, queryAllSQL, params);

    query.parseExportPageSize();
    query.setCurPage(curPageNO);
    query.setPageProvider(PageProviderEnum.PAGE_PROVIDER);
    return query;
  }

  public void setProductConsultDao(ProductConsultDao productConsultDao)
  {
    this.productConsultDao = productConsultDao;
  }

  public PageSupport getProductConsult(CriteriaQuery query)
  {
    return this.productConsultDao.getProductConsult(query);
  }

  public void setProductDao(ProductDao productDao)
  {
    this.productDao = productDao;
  }

  public long checkFrequency(ProductConsult consult)
  {
    return this.productConsultDao.checkFrequency(consult);
  }
}