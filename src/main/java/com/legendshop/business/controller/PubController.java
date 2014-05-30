package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.Pub;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.PubService;
import com.legendshop.util.AppUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PubController extends BaseController
{

  @Autowired
  private PubService pubService;

  @RequestMapping({"/pub/{id}"})
  public String pub(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    if (AppUtils.isNotBlank(id)) {
      String shopName = ThreadLocalContext.getCurrentShopName(request, response);
      Pub pub = this.pubService.getPubById(id);
      if (pub.getUserName().equals(shopName))
        request.setAttribute("pub", pub);

    }

    this.pubService.getAndSetOneAdvertisement(request, response, ThreadLocalContext.getCurrentShopName(request, response), 
      "USER_REG_ADV_740");
    return PathResolver.getPath(request, response, TilesPage.PUB);
  }
}