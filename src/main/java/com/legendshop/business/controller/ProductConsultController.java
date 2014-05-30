package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductConsult;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.service.ProductConsultService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.SafeHtml;
import java.util.Date;
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
@RequestMapping({"/productConsult"})
public class ProductConsultController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(ProductConsultController.class);

  @Autowired
  private ProductConsultService productConsultService;

  @RequestMapping({"/list/{prodId}"})
  public String list(HttpServletRequest request, HttpServletResponse response, String curPageNO, Integer pointType, @PathVariable Long prodId)
  {
    parseConsult(request, response, curPageNO, pointType, prodId);
    return PathResolver.getPath(request, response, FrontPage.PRODUCT_CONSULTS);
  }

  @RequestMapping({"/listcontent/{prodId}"})
  public String listcontent(HttpServletRequest request, HttpServletResponse response, String curPageNO, Integer pointType, @PathVariable Long prodId)
  {
    parseConsult(request, response, curPageNO, pointType, prodId);
    return PathResolver.getPath(request, response, FrontPage.PRODUCT_CONSULTS_LIST);
  }

  private void parseConsult(HttpServletRequest request, HttpServletResponse response, String curPageNO, Integer pointType, Long prodId)
  {
    PageSupport ps = this.productConsultService.getProductConsultForList(curPageNO, pointType, prodId);

    request.setAttribute("prodCousultList", ps.getResultList());
    request.setAttribute("prodCousultCurPageNO", curPageNO);
    request.setAttribute("prodCousultToolBar", ps.getToolBar());
    request.setAttribute("prodId", prodId);
    request.setAttribute("prodCousultTotal", Long.valueOf(ps.getTotal()));
  }

  @RequestMapping({"/save"})
  @ResponseBody
  public Integer save(HttpServletRequest request, HttpServletResponse response, ProductConsult consult)
  {
    String userName = UserManager.getUserName(request.getSession());
    if (userName == null) {
      this.log.debug("save product consult required user login before");
      return Integer.valueOf(-1);
    }
    String content = consult.getContent();
    if ((AppUtils.isBlank(content)) || (content.length() < 5) || (content.length() > 200))
    {
      return Integer.valueOf(-2);
    }

    SafeHtml safe = new SafeHtml();
    consult.setContent(safe.makeSafe(content));
    consult.setRecDate(new Date());
    consult.setPostip(request.getRemoteAddr());
    consult.setUserId(UserManager.getUserId());
    consult.setAskUserName(userName);

    long frequency = this.productConsultService.checkFrequency(consult);
    if (frequency > -4648542910910824448L) {
      return Integer.valueOf(-3);
    }

    this.productConsultService.saveProductConsult(consult);
    return Integer.valueOf(0);
  }
}