package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.spi.locator.GenericServiceLocator;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.service.CommonPageService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonPageController extends BaseController
{

  @Autowired
  private GenericServiceLocator<CommonPageService> commonPageServiceLocator;

  @RequestMapping({"/top"})
  public String top(HttpServletRequest request, HttpServletResponse response)
  {
    return ((CommonPageService)this.commonPageServiceLocator.getConcreteService(request, response, FrontPage.TOP)).getTop(request, response);
  }

  @RequestMapping({"/topuserinfo"})
  public String topUserInfo(HttpServletRequest request, HttpServletResponse response)
  {
    return ((CommonPageService)this.commonPageServiceLocator.getConcreteService(request, response, FrontPage.TOP)).getTopUserInfo(request, response);
  }

  @RequestMapping({"/home/top"})
  public String homeTop(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, FrontPage.HOME_TOP);
  }

  @RequestMapping({"/bottom"})
  public String bottom(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, FrontPage.BOTTOM);
  }

  @RequestMapping({"/all"})
  public String all(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, FrontPage.ALL);
  }

  @RequestMapping({"/topall"})
  public String topall(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, FrontPage.TOPALL);
  }

  @RequestMapping({"/copy"})
  public String copyAll(HttpServletRequest request, HttpServletResponse response, String curPageNO, String newsCategory)
  {
    return ((CommonPageService)this.commonPageServiceLocator.getConcreteService(request, response, FrontPage.COPY)).getCopy(request, response);
  }

  public void setCommonPageServiceLocator(GenericServiceLocator<CommonPageService> commonPageServiceServiceLocator)
  {
    this.commonPageServiceLocator = commonPageServiceServiceLocator;
  }
}