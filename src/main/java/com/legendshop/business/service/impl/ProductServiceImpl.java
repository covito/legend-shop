package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ImgFileDao;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.helper.VisitHistoryHelper;
import com.legendshop.core.model.UserMessages;
import com.legendshop.model.dynamic.Item;
import com.legendshop.model.entity.DynamicTemp;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.RelProduct;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.dao.ProductDao;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.service.ProductService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.JSONUtil;
import com.legendshop.util.constant.ProductStatusEnum;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cache.annotation.Cacheable;

public class ProductServiceImpl extends BaseServiceImpl
  implements ProductService
{
  private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
  private ProductDao productDao;
  private ImgFileDao imgFileDao;

  @Required
  public void setShopDetailDao(ShopDetailDao shopDetailDao)
  {
    this.shopDetailDao = shopDetailDao;
  }

  public PageSupport getProductList(HqlQuery hql)
  {
    return this.productDao.find(hql);
  }

  public PageSupport getProductList(CriteriaQuery cq)
  {
    return this.productDao.find(cq);
  }

  @Required
  public void setProductDao(ProductDao productDao)
  {
    this.productDao = productDao;
  }

  public Product getProductById(Long prodId)
  {
    if (prodId == null)
      return null;

    return this.productDao.getProduct(prodId);
  }

  public void updateProduct(Product product, Product origin)
  {
    Date date = new Date();
    origin.setModifyDate(date);
    if ((product.getStocks() != null) && (!(product.getStocks().equals(origin.getStocks()))))
      origin.setActualStocks(product.getStocks());

    origin.setName(product.getName());
    origin.setSortId(product.getSortId());
    origin.setSubNsortId(product.getSubNsortId());
    origin.setNsortId(product.getNsortId());
    origin.setModelId(product.getModelId());
    origin.setKeyWord(product.getKeyWord());
    origin.setPrice(product.getPrice());
    origin.setCash(product.getCash());
    origin.setCarriage(product.getCarriage());
    origin.setStocks(product.getStocks());
    origin.setBrandId(product.getBrandId());
    origin.setBrief(product.getBrief());
    origin.setContent(product.getContent());
    origin.setProdType(product.getProdType());
    origin.setStartDate(product.getStartDate());
    origin.setEndDate(product.getEndDate());
    origin.setCommend(product.getCommend());
    origin.setHot(product.getHot());
    origin.setStatus(product.getStatus());
    this.productDao.updateProduct(origin);
    this.shopDetailDao.updateShopDetailWhenProductChange(origin);
  }

  public Long saveProduct(Product product, String prodType)
  {
    Date date = new Date();
    product.setStatus(ProductStatusEnum.PROD_ONLINE.value());
    product.setRecDate(date);
    product.setModifyDate(date);
    product.setViews(Integer.valueOf(0));
    product.setComments(Integer.valueOf(0));
    product.setBuys(Integer.valueOf(0));
    product.setProdType(prodType);
    if (product.getStocks() != null)
      product.setActualStocks(product.getStocks());

    Long prodId = this.productDao.saveProduct(product);
    product.setProdId(prodId);
    this.shopDetailDao.updateShopDetailWhenProductChange(product);
    return prodId;
  }

  public String getProductGallery(HttpServletRequest request, HttpServletResponse response, Long prodId)
  {
    ProductDetail prod = this.productDao.getProdDetail(prodId);
    if (prod != null) {
      if (!(Constants.ONLINE.equals(prod.getStatus())))
        throw new NotFoundException("Product does not online.");

      request.setAttribute("prod", prod);

      List prodPics = this.imgFileDao.getProductPics(prod.getUserName(), prodId);
      if (AppUtils.isNotBlank(prodPics))
        request.setAttribute("prodPics", prodPics);

      return PathResolver.getPath(request, response, FrontPage.PROD_PIC_GALLERY);
    }
    UserMessages uem = new UserMessages();
    uem.setTitle(ResourceBundleHelper.getString("product.not.found"));
    uem.setDesc(ResourceBundleHelper.getString("product.status.check"));
    uem.setCode("404");
    request.setAttribute(UserMessages.MESSAGE_KEY, uem);
    return PathResolver.getPath(request, response, FrontPage.FAIL);
  }

  public String getAttributeprodAttribute(Long prodId)
  {
    return ((String)this.productDao.findUniqueBy("select prod.attribute from Product prod where prod.prodId = ?", String.class, new Object[] { prodId }));
  }

  public List<Product> getHotSale(String shopName)
  {
    return this.productDao.gethotsale(shopName);
  }

  public Product getProd(Long prodId, String userName)
  {
    return this.productDao.getProd(prodId, userName);
  }

  public String getProdParameter(Long prodId)
  {
    return this.productDao.getProdParameter(prodId);
  }

  public Long saveDynamicTemp(DynamicTemp dynamicTemp)
  {
    return this.productDao.saveDynamicTemp(dynamicTemp);
  }

  public boolean updateDynamicTemp(DynamicTemp dynamicTemp)
  {
    return this.productDao.updateDynamicTemp(dynamicTemp);
  }

  public DynamicTemp getDynamicTemp(Long tempId)
  {
    return this.productDao.getDynamicTemp(tempId);
  }

  public int deleteDynamicTemp(Long tempId, String userName)
  {
    if (AppUtils.isNotBlank(userName)) {
      DynamicTemp temp = this.productDao.getDynamicTemp(tempId);
      if (temp != null) {
        if (userName.equals(temp.getUserName()))
          this.productDao.deleteDynamicTemp(temp);

        return temp.getType().intValue();
      }
    }
    return 1;
  }

  public String saveProdItem(List<String> idList, List<String> nameList, Long prodId, String userName)
  {
    return this.productDao.saveProdItem(idList, nameList, prodId, userName);
  }

  public void updateProd(Product product)
  {
    this.productDao.updateProduct(product);
  }

  public ProductDetail getProdDetail(Long prodId)
  {
    if (prodId == null)
      return null;

    return this.productDao.getProdDetail(prodId);
  }

  public List<Product> getReleationProd(String shopName, Long prodId, int total)
  {
    return this.productDao.getReleationProd(shopName, prodId, total);
  }

  public List<RelProduct> getReleationProd(Long prodId, String userName)
  {
    return this.productDao.getReleationProd(prodId, userName);
  }

  public void updateProdViews(Long prodId)
  {
    this.productDao.updateProdViews(prodId);
  }

  public List<Product> getHotOn(String shopName, Long sortId)
  {
    return this.productDao.getHotOn(shopName, sortId);
  }

  public List<Product> getHotViewProd(String shopName, int maxNum)
  {
    return this.productDao.getHotViewProd(shopName, Integer.valueOf(maxNum));
  }

  @Cacheable(value={"ProductDetailList"}, condition="T(Integer).parseInt(#curPageNO) < 3")
  public PageSupport getProdDetail(String curPageNO, Long sortId)
  {
    return this.productDao.getProdDetail(curPageNO, sortId);
  }

  public void setImgFileDao(ImgFileDao imgFileDao)
  {
    this.imgFileDao = imgFileDao;
  }

  @Cacheable({"ProductDetailList"})
  public List<Product> getHotComment(String shopName, Long sortId, int maxNum)
  {
    return this.productDao.getHotComment(shopName, sortId, maxNum);
  }

  @Cacheable({"ProductDetailList"})
  public List<Product> getHotRecommend(String shopName, Long sortId, int maxNum)
  {
    return this.productDao.getHotRecommend(shopName, sortId, maxNum);
  }

  public PageSupport getDynamicTemp(CriteriaQuery cq)
  {
    return this.productDao.getDynamicTemp(cq);
  }

  public PageSupport getDynamicTemp(SimpleHqlQuery hql)
  {
    return this.productDao.getDynamicTemp(hql);
  }

  public List<DynamicTemp> getDynamicTemp(Integer type, String userName)
  {
    return this.productDao.getDynamicTemp(type, userName);
  }

  public List<DynamicTemp> getDynamicTemp(Integer type, Long sortId, String userName)
  {
    return this.productDao.getDynamicTemp(type, sortId, userName);
  }

  public List<ProductDetail> getVisitedProd(HttpServletRequest request)
  {
    List prodIds = VisitHistoryHelper.getVisitedProd(request);
    return this.productDao.getProdDetail(prodIds);
  }

  public List<ProductDetail> getRecommendProd(Long prodId)
  {
    return getVisitedProd(ThreadLocalContext.getRequest());
  }

  public boolean deleteDynamicTemp(DynamicTemp temp)
  {
    return this.productDao.deleteDynamicTemp(temp);
  }

  public String delete(String loginedUser, Long prodId)
  {
    try
    {
      if (this.productDao.hasOrder(prodId))
        return "该产品已经有订单,不能删除，请将该产品下线";

      return this.productDao.deleteProd(loginedUser, prodId);
    } catch (Exception e) {
      this.log.error("delete product fail with id " + prodId, e); }
    return "fail";
  }

  public String saveRelProd(String idJson, String nameJson, Long prodId, String userName)
  {
    List idList = JSONUtil.getArray(idJson, String.class);
    List nameList = JSONUtil.getArray(nameJson, String.class);
    return this.productDao.saveProdItem(idList, nameList, prodId, userName);
  }

  public List<Item> getUsableProductItemByName(Long prodId, String userName, String prodName)
  {
    return this.productDao.getUsableProductItemByName(prodId, userName, prodName);
  }

  public List<Item> getUsableProductItem(Long prodId, String userName)
  {
    return this.productDao.getUsableProductItem(prodId, userName);
  }

  public List<Item> getUsedProductItem(Long prodId, String userName)
  {
    return this.productDao.getUsedProd(prodId, userName);
  }
}