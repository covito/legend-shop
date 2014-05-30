package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.BrandService;
import com.legendshop.spi.service.NsortService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BrandController extends BaseController
{

  @Autowired
  private BrandService brandService;

  @Autowired
  private NsortService nsortService;
  private final Logger logger = LoggerFactory.getLogger(IndexController.class);

  @RequestMapping({"/allbrands"})
  public String allBrands(HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      this.logger.debug("toAllBrand calling...");
    } catch (Exception e) {
      this.logger.error("invoking toAllBrand", e);
    }
    return PathResolver.getPath(request, response, TilesPage.ALL_BRAND);
  }

  @RequestMapping({"/brand/loadBrands/{subNsortId}"})
  @ResponseBody
  public List<KeyValueEntity> loadBrands(HttpServletRequest request, HttpServletResponse response, @PathVariable Long subNsortId)
  {
    String shopName = this.nsortService.getUserNameByNsortId(subNsortId);
    return this.brandService.loadBrandEntityBySubSortId(subNsortId, shopName);
  }

  public void setNsortService(NsortService nsortService) {
    this.nsortService = nsortService;
  }
}