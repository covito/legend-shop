package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.spi.form.SearchForm;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.BusinessService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BusinessController extends BaseController
{

  @Autowired
  private BusinessService businessService;

  @RequestMapping({"/leaveword"})
  public String leaveword(HttpServletRequest request, HttpServletResponse response, String ipAddress)
  {
    return PathResolver.getPath(request, response, TilesPage.LEAVEWORD);
  }

  @RequestMapping({"/league"})
  public String league(HttpServletRequest request, HttpServletResponse response)
  {
    return this.businessService.getLeague(request, response);
  }

  @RequestMapping({"/friendlink"})
  public String friendlink(HttpServletRequest request, HttpServletResponse response)
  {
    return this.businessService.getFriendlink(request, response);
  }

  @RequestMapping({"/visitedshop"})
  public String visitedShop(HttpServletRequest request, HttpServletResponse response) {
    this.businessService.getVisitedShop(request);
    return PathResolver.getPath(request, response, FrontPage.VISITED_SHOP);
  }

  @RequestMapping({"/afteroperation"})
  public String afterOperation(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, TilesPage.AFTER_OPERATION);
  }

  @RequestMapping({"/search"})
  public String search(HttpServletRequest request, HttpServletResponse response, SearchForm searchForm)
  {
    return this.businessService.search(request, response, searchForm);
  }
}