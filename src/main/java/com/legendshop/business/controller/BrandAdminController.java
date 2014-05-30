package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.ConflictException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.dynamic.Item;
import com.legendshop.model.entity.Brand;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.BrandService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/admin/brand"})
public class BrandAdminController extends BaseController
  implements AdminController<Brand, Long>
{
  private final Logger log = LoggerFactory.getLogger(BrandAdminController.class);

  @Autowired
  private BrandService brandService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Brand brand)
  {
    CriteriaQuery cq = new CriteriaQuery(Brand.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    cq = hasAllDataFunction(cq, request, StringUtils.trim(brand.getUserName()));

    PageSupport ps = this.brandService.getDataByCriteriaQuery(cq);
    ps.savePage(request);
    request.setAttribute("brand", brand);
    return PathResolver.getPath(request, response, BackPage.BRAND_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Brand brand)
  {
    Brand originbrand = null;
    String brandPic = null;
    String name = UserManager.getUserName(request.getSession());
    String subPath = name + "/brand/";
    if ((brand != null) && (brand.getBrandId() != null)) {
      originbrand = this.brandService.getBrand(brand.getBrandId());
      if (originbrand == null)
        throw new NotFoundException("Origin Brand is empty");

      String originBrandPic = originbrand.getBrandPic();
      if ((!(CommonServiceUtil.haveViewAllDataFunction(request))) && 
        (!(name.equals(originbrand.getUserName())))) {
        throw new PermissionException("Can't edit Brand does not own to you!");
      }

      originbrand.setMemo(brand.getMemo());
      originbrand.setBrandName(brand.getBrandName());
      originbrand.setSeq(brand.getSeq());
      if ((brand.getFile() != null) && (brand.getFile().getSize() > -4648542618853048320L)) {
        brandPic = FileProcessor.uploadFileAndCallback(brand.getFile(), subPath, "");
        originbrand.setBrandPic(brandPic);
        String url = RealPathUtil.getBigPicRealPath() + "/" + originBrandPic;
        FileProcessor.deleteFile(url);
      }
      this.brandService.update(originbrand);
    }
    else {
      if ((brand.getFile() != null) && (brand.getFile().getSize() > -4648542618853048320L)) {
        brandPic = FileProcessor.uploadFileAndCallback(brand.getFile(), subPath, "");
        brand.setBrandPic(brandPic);
      }
      brand.setUserId(UserManager.getUserId(request.getSession()));
      brand.setUserName(UserManager.getUserName(request.getSession()));
      brand.setStatus(Constants.ONLINE);
      this.brandService.save(brand);
    }
    saveMessage(request, ResourceBundleHelper.getSucessfulString());
    String result = PathResolver.getPath(request, response, FowardPage.BRAND_LIST_QUERY);
    return result;
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Brand brand = this.brandService.getBrand(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), brand.getUserName());
    if (result != null)
      return result;

    if (this.brandService.hasChildProduct(brand.getUserName(), brand.getBrandId()))
      throw new ConflictException("商品品牌下有产品, 该品牌不能删除！");

    this.log.info("{}, delete Brand Picture {}", brand.getUserName(), brand.getBrandPic());
    this.brandService.delete(id);
    if (brand.getBrandPic() != null) {
      String url = RealPathUtil.getBigPicRealPath() + "/" + brand.getBrandPic();
      FileProcessor.deleteFile(url);
    }
    saveMessage(request, ResourceBundleHelper.getDeleteString());
    return PathResolver.getPath(request, response, FowardPage.BRAND_LIST_QUERY);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, BackPage.BRAND_EDIT_PAGE);
  }

  @RequestMapping({"/update/{id}"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Brand brand = this.brandService.getBrand(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), brand.getUserName());
    if (result != null)
      return result;

    request.setAttribute("bean", brand);
    return PathResolver.getPath(request, response, BackPage.BRAND_EDIT_PAGE);
  }

  @RequestMapping(value={"/updatestatus/{brandId}/{status}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Integer updateStatus(HttpServletRequest request, HttpServletResponse response, @PathVariable Long brandId, @PathVariable Integer status)
  {
    Brand brand = this.brandService.getBrand(brandId);
    if (brand == null)
      return Integer.valueOf(-1);

    if (!(status.equals(brand.getStatus())))
      if (!(FoundationUtil.haveViewAllDataFunction(request))) {
        String loginName = UserManager.getUserName(request.getSession());

        if (!(loginName.equals(brand.getUserName())))
          return Integer.valueOf(-1);

        if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status))) {
          brand.setStatus(status);
          this.brandService.update(brand);
        }

      }
      else if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status)) || (Constants.STOPUSE.equals(status))) {
        brand.setStatus(status);
        this.brandService.update(brand);
      }


    return brand.getStatus(); }

  @RequestMapping({"/saveBrandItem/{nsortId}"})
  @ResponseBody
  public String saveBrandItem(HttpServletRequest request, HttpServletResponse response, String idJson, String nameJson, @PathVariable Long nsortId) {
    return this.brandService.saveBrandItem(idJson, nameJson, nsortId, UserManager.getUserName(request)); }

  @RequestMapping({"/getUsableProductItemByName/{nsortId}"})
  @ResponseBody
  public List<Item> getUsableBrandByName(HttpServletRequest request, @PathVariable Long nsortId, String brandName) {
    return this.brandService.getUsableBrandByName(nsortId, UserManager.getUserName(request), brandName); }

  @RequestMapping({"/getUsableBrand/{nsortId}"})
  @ResponseBody
  public List<Item> getUsableBrand(HttpServletRequest request, @PathVariable Long nsortId) {
    return this.brandService.getUsableBrand(nsortId, UserManager.getUserName(request)); }

  @RequestMapping({"/getUsedBrand/{nsortId}"})
  @ResponseBody
  public List<Item> getUsedBrand(HttpServletRequest request, @PathVariable Long nsortId) {
    return this.brandService.getUsedBrand(nsortId, UserManager.getUserName(request));
  }

  @RequestMapping({"/loadSortBrands/{subNsortId}"})
  public String loadBrandBySubSortId(HttpServletRequest request, HttpServletResponse response, @PathVariable Long subNsortId)
  {
    List brandList = this.brandService.loadBrandBySubSortId(subNsortId, PropertiesUtil.getDefaultShopName());
    request.setAttribute("brandList", brandList);
    return PathResolver.getPath(request, response, BackPage.PROD_PUBLISH4_PAGE);
  }

  @RequestMapping({"/loadBrandByName/{subNsortId}"})
  public String loadBrandByName(HttpServletRequest request, HttpServletResponse response, @PathVariable Long subNsortId, String brandName)
  {
    List brandList = this.brandService.loadBrandByName(subNsortId, PropertiesUtil.getDefaultShopName(), brandName);
    request.setAttribute("brandList", brandList);
    return PathResolver.getPath(request, response, BackPage.PUBLISH_BRAND_QUERY_PAGE);
  }
}