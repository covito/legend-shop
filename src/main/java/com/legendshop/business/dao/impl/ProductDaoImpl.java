package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.ImgFileDao;
import com.legendshop.business.dao.ProductCommentDao;
import com.legendshop.core.constant.PageProviderEnum;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.QueryMap;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.event.EventHome;
import com.legendshop.model.ProdSearchArgs;
import com.legendshop.model.dynamic.Item;
import com.legendshop.model.dynamic.Model;
import com.legendshop.model.entity.DynamicTemp;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.RelProduct;
import com.legendshop.spi.cache.ProductUpdate;
import com.legendshop.spi.dao.ProductDao;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.spi.event.ProductDeleteEvent;
import com.legendshop.spi.event.ProductUpdateEvent;
import com.legendshop.util.AppUtils;
import com.legendshop.util.JSONUtil;
import com.legendshop.util.sql.ConfigCode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

public abstract class ProductDaoImpl extends BaseDaoImpl
  implements ProductDao
{
  private static Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);
  private ImgFileDao imgFileDao;
  private ProductCommentDao productCommentDao;
  private ShopDetailDao shopDetailDao;
  private Map<String, List<Integer>> scaleList;

  @Cacheable({"ProductDetailList"})
  public List<Product> getCommendProd(String shopName, int total)
  {
    log.debug("getCommendProd, shopName = {}", shopName);
    Date date = new Date();
    return findByHQLLimit(ConfigCode.getInstance().getCode("prod.getCommend"), 0, total, new Object[] { shopName, date, date });
  }

  @Cacheable({"ProductDetailList"})
  public List<Product> getGlobalCommendProd(int total) {
    Date date = new Date();
    return findByHQLLimit(ConfigCode.getInstance().getCode("prod.getGlobalCommend"), 0, total, new Object[] { date, date });
  }

  @Cacheable({"ProductDetailList"})
  public List<Product> getCommendProdBySort(String shopName, Long sortId, int total)
  {
    log.debug("getCommendProd, shopName = {}, sortId = {}", shopName, sortId);
    Date date = new Date();
    return findByHQLLimit(ConfigCode.getInstance().getCode("prod.getCommendBySort"), 0, total, new Object[] { sortId, shopName, date, date });
  }

  @Cacheable({"ProductDetailList"})
  public List<Product> getReleationProd(String shopName, Long prodId, int total)
  {
    Date date = new Date();
    return findByHQLLimit(ConfigCode.getInstance().getCode("prod.getRelationProd"), 0, total, new Object[] { shopName, date, date, prodId });
  }

  @Cacheable({"ProductDetailList"})
  public List<Product> getNewestProd(String shopName, int total)
  {
    Date date = new Date();
    return findByHQLLimit(ConfigCode.getInstance().getCode("prod.getNewestProd"), 0, total, new Object[] { shopName, date, date });
  }

  @Cacheable({"ProductDetailList"})
  public List<Product> getGlobalNewestProd(int total) {
    Date date = new Date();
    return findByHQLLimit(ConfigCode.getInstance().getCode("prod.getGlobalNewestProd"), 0, total, new Object[] { date, date });
  }

  @Cacheable({"ProductDetailList"})
  public List<Product> gethotsale(String shopName)
  {
    if (shopName == null)
      return null;

    Date date = new Date();
    return findByHQLLimit(ConfigCode.getInstance().getCode("prod.gethotsale"), 0, 6, new Object[] { shopName, date, date });
  }

  @Cacheable(value={"ProductDetailList"}, condition="T(Integer).parseInt(#curPageNO) < 3")
  public PageSupport getProdDetail(String curPageNO, Long sortId, Long nsortId, Long subNsortId, Boolean isGlobal)
  {
    CriteriaQuery cq = new CriteriaQuery(Product.class, curPageNO, PageProviderEnum.SIMPLE_PAGE_PROVIDER);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.FRONT_PAGE_SIZE, Integer.class)).intValue());
    cq.addOrder("desc", "buys");
    cq.addOrder("desc", "views");
    cq.addOrder("desc", "modifyDate");
    if (isGlobal.booleanValue()) {
      cq.eq("globalSort", sortId);
      cq.eq("globalNsort", nsortId);
      cq.eq("globalSubSort", subNsortId);
    } else {
      cq.eq("sortId", sortId);
      cq.eq("nsortId", nsortId);
      cq.eq("subNsortId", subNsortId);
    }
    PageSupport ps = find(cq);
    return ps;
  }

  public PageSupport getProdDetail(String curPageNO, Long sortId)
  {
    HqlQuery hql = new HqlQuery(((Integer)PropertiesUtil.getObject(SysParameterEnum.FRONT_PAGE_SIZE, Integer.class)).intValue(), curPageNO, 
      PageProviderEnum.SIMPLE_PAGE_PROVIDER);
    String QueryNsortCount = ConfigCode.getInstance().getCode("prod.getSortProdCount");
    String QueryNsort = ConfigCode.getInstance().getCode("prod.getSortProd");
    hql.setAllCountString(QueryNsortCount);
    hql.setQueryString(QueryNsort);
    Date date = new Date();
    hql.setParam(new Object[] { sortId, date, date });
    PageSupport ps = find(hql);
    return ps;
  }

  public PageSupport getGlobalProdDetail(String curPageNO, Long sortId)
  {
    HqlQuery hql = new HqlQuery(((Integer)PropertiesUtil.getObject(SysParameterEnum.FRONT_PAGE_SIZE, Integer.class)).intValue(), curPageNO, 
      PageProviderEnum.SIMPLE_PAGE_PROVIDER);
    String QueryNsortCount = ConfigCode.getInstance().getCode("prod.getGlobalSortProdCount");
    String QueryNsort = ConfigCode.getInstance().getCode("prod.getGlobalSortProd");
    hql.setAllCountString(QueryNsortCount);
    hql.setQueryString(QueryNsort);
    Date date = new Date();
    hql.setParam(new Object[] { sortId, date, date });
    PageSupport ps = find(hql);
    return ps;
  }

  public PageSupport getProdDetail(String curPageNO, Long sortId, Long nsortId, Long subNsortId, ProdSearchArgs args)
  {
    HqlQuery hql = new HqlQuery(((Integer)PropertiesUtil.getObject(SysParameterEnum.FRONT_PAGE_SIZE, Integer.class)).intValue(), curPageNO, 
      PageProviderEnum.SIMPLE_PAGE_PROVIDER);
    QueryMap map = new QueryMap();
    map.put("sortId", sortId);
    map.put("nsortId", nsortId);
    map.put("subNsortId", subNsortId);
    if (args.isHasProd())
      map.put("hasProd", Integer.valueOf(0));

    if (AppUtils.isNotBlank(args.getOrderBy())) {
      if ("asc".equals(args.getOrderDir())) {
        map.put("orderByAndDir", "order by " + args.getOrderBy() + " asc");
      }
      else
        map.put("orderByAndDir", "order by " + args.getOrderBy() + " desc");

    }
    else {
      map.put("orderByAndDir", "order by prod.buys desc ");
    }

    String QueryNsortCount = ConfigCode.getInstance().getCode("prod.getOrderSortProdCount", map);
    String QueryNsort = ConfigCode.getInstance().getCode("prod.getOrderSortProd", map);
    hql.setAllCountString(QueryNsortCount);
    hql.setQueryString(QueryNsort);
    Date date = new Date();
    map.put("startDate", date);
    map.put("endDate", date);
    map.remove("orderByAndDir");
    hql.setParam(map.toArray());
    PageSupport ps = find(hql);
    return ps;
  }

  @Cacheable({"ProductDetailList"})
  public List<Product> getHotOn(String shopName, Long sortId)
  {
    if (AppUtils.isBlank(sortId))
      return Collections.EMPTY_LIST;

    Date date = new Date();
    return findByHQLLimit(ConfigCode.getInstance().getCode("prod.getHotOnProd"), 0, 21, new Object[] { shopName, sortId, date, date });
  }

  @Cacheable({"ProductDetailList"})
  public List<Product> getHotViewProd(String shopName, Integer number)
  {
    Date date = new Date();
    return findByHQLLimit(ConfigCode.getInstance().getCode("prod.getHotViewProd"), 0, number.intValue(), new Object[] { date, date });
  }

  @Cacheable({"ProductDetailList"})
  public List<Product> getHotComment(String shopName, Long sortId, int maxNum)
  {
    Date date = new Date();
    return findByHQLLimit(ConfigCode.getInstance().getCode("prod.getHotComment"), 0, maxNum, new Object[] { shopName, sortId, date, date });
  }

  public Product getProduct(Long id)
  {
    return ((Product)get(Product.class, id));
  }

  public void updateProdViews(Long prodId)
  {
    exeByHQL("update Product set views = views+1 where prodId = ?", new Object[] { prodId });
  }

  public void updateProduct(Product product)
  {
    EventHome.publishEvent(new ProductUpdateEvent(product));
    updateOnly(product);
  }

  @ProductUpdate
  public void updateOnly(Product product)
  {
    update(product);
  }

  public Long saveProduct(Product product)
  {
    return ((Long)save(product));
  }

  public PageSupport getProdDetail(CriteriaQuery cq)
  {
    return find(cq);
  }

  public String deleteProd(String loginedUser, Long prodId)
  {
    if (prodId == null) {
      return "商品ID不能为空";
    }

    Product product = getProduct(prodId);
    if (product == null) {
      log.warn("Product with Id {} does not exists ", prodId);
      return "找不到该商品";
    }

    if (!(product.getUserName().equals(loginedUser))) {
      log.warn("can not delete Id {} does not belongs to you", prodId);
      return "您不是该商品的主人，不能删除该商品";
    }

    if (product != null)
    {
      exeByHQL("delete from Myfavorite where prodId = ?", new Object[] { prodId });

      exeByHQL("delete from ProductConsult where prodId = ?", new Object[] { prodId });

      List list = getReleationProd(product.getProdId(), product.getUserName());
      if (AppUtils.isNotBlank(list)) {
        deleteAll(list);
      }

      List imgFileList = this.imgFileDao.getAllProductPics(product.getProdId());
      if (AppUtils.isNotBlank(imgFileList)) {
        for (Iterator localIterator = imgFileList.iterator(); localIterator.hasNext(); ) { ImgFile imgFile = (ImgFile)localIterator.next();
          this.imgFileDao.deleteImgFile(imgFile);
          String imgFileUrl = RealPathUtil.getBigPicRealPath() + "/" + imgFile.getFilePath();

          log.debug("delete Big imgFileUrl file {}", imgFileUrl);
          FileProcessor.deleteFile(imgFileUrl);
        }

      }

      this.productCommentDao.deleteProductComment(product.getProdId(), product.getUserName());

      this.shopDetailDao.updateShopDetail(product.getUserName());

      deleteProd(product);

      deleteProdImgFile(product);
    }

    return "OK";
  }

  private void deleteProdImgFile(Product product)
  {
    FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + product.getPic());

    for (Iterator localIterator = this.scaleList.keySet().iterator(); localIterator.hasNext(); ) { String sacle = (String)localIterator.next();
      StringBuilder sb = new StringBuilder(RealPathUtil.getSmallPicRealPath());
      sb.append("/").append(sacle).append("/").append(product.getPic());
      FileProcessor.deleteFile(sb.toString(), false);
    }

    if (AppUtils.isNotBlank(product.getSmallPic()))
      FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + product.getSmallPic(), false);
  }

  @ProductUpdate
  public void deleteProd(Product product)
  {
    delete(product);
    EventHome.publishEvent(new ProductDeleteEvent(product));
  }

  public List<Model> loadDynamicAttribute(Long prodId, String userName)
  {
    List list = new ArrayList();
    Product product = getProd(prodId, userName);
    if ((AppUtils.isNotBlank(product)) && (AppUtils.isNotBlank(product.getAttribute())))
      list = JSONUtil.getArray(product.getAttribute(), Model.class);

    return list;
  }

  public Product getProd(Long prodId, String userName)
  {
    return ((Product)findUniqueBy("from Product prod where prod.prodId = ? and prod.userName = ?", Product.class, new Object[] { prodId, userName }));
  }

  public DynamicTemp getDynamicTemp(Long tempId)
  {
    return ((DynamicTemp)get(DynamicTemp.class, tempId));
  }

  public PageSupport getDynamicTemp(CriteriaQuery cq)
  {
    return find(cq);
  }

  public Long saveDynamicTemp(DynamicTemp dynamicTemp)
  {
    if ((AppUtils.isBlank(dynamicTemp.getName())) || (AppUtils.isBlank(dynamicTemp.getUserName()))) {
      return Long.valueOf(-1L);
    }

    List temps = findByHQL("from DynamicTemp where type = ? and name = ? and userName = ?", new Object[] { dynamicTemp.getType(), 
      dynamicTemp.getName(), dynamicTemp.getUserName() });
    if (AppUtils.isNotBlank(temps))
      return Long.valueOf(-1L);

    return ((Long)save(dynamicTemp));
  }

  public boolean updateDynamicTemp(DynamicTemp dynamicTemp)
  {
    if ((AppUtils.isBlank(dynamicTemp.getId())) || (AppUtils.isBlank(dynamicTemp.getUserName())))
      return false;

    update(dynamicTemp);
    return true;
  }

  public boolean deleteDynamicTemp(DynamicTemp temp)
  {
    if (temp != null) {
      delete(temp);
      return true;
    }
    return false;
  }

  public String saveProdItem(List<String> idList, List<String> nameList, Long prodId, String userName)
  {
    List list = findByHQL("from RelProduct n where n.prodId = ? and userName = ?", new Object[] { prodId, userName });

    deleteAll(list);
    if (AppUtils.isNotBlank(idList))
      for (int i = 0; i < idList.size(); ++i) {
        RelProduct rprod = new RelProduct();
        rprod.setRecDate(new Date());
        rprod.setProdId(prodId);
        rprod.setRelProdId(Long.valueOf((String)idList.get(i)));
        rprod.setRelProdName((String)nameList.get(i));
        rprod.setUserName(userName);
        save(rprod);
      }


    return null;
  }

  public String getProdParameter(Long prodId)
  {
    return ((String)findUniqueBy("select prod.parameter from Product prod where prod.prodId = ?", String.class, new Object[] { prodId }));
  }

  public List<RelProduct> getReleationProd(Long prodId, String userName)
  {
    return findByHQL("from RelProduct n where n.prodId = ? and userName = ?", new Object[] { prodId, userName });
  }

  public List<Product> getHotRecommend(String shopName, Long sortId, int maxNum)
  {
    if (AppUtils.isBlank(sortId))
      return Collections.EMPTY_LIST;

    Date date = new Date();
    return findByHQLLimit(ConfigCode.getInstance().getCode("prod.getHotRecommend"), 0, maxNum, new Object[] { shopName, sortId, date, date });
  }

  public List<DynamicTemp> getDynamicTemp(Integer type, String userName)
  {
    return findByHQL("from DynamicTemp t where  t.status = 1 and t.type =? and t.userName = ?  ", new Object[] { type, userName });
  }

  public List<DynamicTemp> getDynamicTemp(Integer type, Long sortId, String userName)
  {
    return findByHQL("from DynamicTemp t where  t.status = 1 and t.type =?  and t.sortId =? and t.userName = ? ", new Object[] { type, sortId, 
      userName });
  }

  public PageSupport getDynamicTemp(SimpleHqlQuery hql)
  {
    hql.initSQL("biz.QueryDynamicTemp", "biz.QueryDynamicTempCount");
    return find(hql);
  }

  public void setImgFileDao(ImgFileDao imgFileDao)
  {
    this.imgFileDao = imgFileDao;
  }

  public void setProductCommentDao(ProductCommentDao productCommentDao)
  {
    this.productCommentDao = productCommentDao;
  }

  public void setShopDetailDao(ShopDetailDao shopDetailDao)
  {
    this.shopDetailDao = shopDetailDao;
  }

  public List<Item> getUsableProductItemByName(Long prodId, String userName, String prodName)
  {
    return findByHQLLimit(
      "select new com.legendshop.model.dynamic.Item(b.prodId , b.name) from Product b where name like ? and b.prodId <> ? and b.userName = ? and not exists ( select n.userName from RelProduct n where b.prodId = n.relProdId and  n.prodId = ?)", 
      0, 30, new Object[] { prodName, prodId, userName, prodId });
  }

  public List<Item> getUsableProductItem(Long prodId, String userName)
  {
    return findByHQLLimit(
      "select new com.legendshop.model.dynamic.Item(b.prodId , b.name) from Product b where  b.prodId <> ? and not exists ( select n.userName from RelProduct n where b.prodId = n.relProdId and  n.prodId = ?  ) and b.userName = ?", 
      0, 30, new Object[] { prodId, prodId, userName });
  }

  public List<Item> getUsedProd(Long prodId, String userName)
  {
    return findByHQLLimit(
      "select new com.legendshop.model.dynamic.Item(b.prodId , b.name) from Product b where b.userName = ? and exists ( select n.userName from RelProduct n where b.prodId = n.relProdId and  n.prodId = ? )", 
      0, 30, new Object[] { userName, prodId });
  }

  public void setScaleList(Map<String, List<Integer>> scaleList) {
    this.scaleList = scaleList;
  }
}