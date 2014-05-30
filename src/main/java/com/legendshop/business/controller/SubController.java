package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.core.model.UserMessages;
import com.legendshop.model.SubForm;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.UserDetailService;
import com.legendshop.spi.service.timer.SubService;
import com.legendshop.util.AppUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/sub"})
public class SubController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(SubController.class);

  @Autowired
  private SubService subService;

  @Autowired
  private UserDetailService userDetailService;

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, SubForm sub)
  {
    String userName = UserManager.getUserName(request);
    String result = checkLogin(request, response, userName);
    if (result != null)
      return result;

    this.log.debug("{} save sub ", userName);
    HttpSession session = request.getSession();
    Integer i = (Integer)session.getAttribute("SESSION_TOKEN");
    Integer i2 = Integer.valueOf(Integer.parseInt(request.getParameter("SESSION_TOKEN")));
    if (i.equals(i2)) {
      session.setAttribute("SESSION_TOKEN", CommonServiceUtil.generateRandom());
      parseBasketCount(request, sub);
      List subList = this.subService.saveSub(sub);

      CommonServiceUtil.calBasketTotalCount(session, -sub.getBasketId().length);
      request.setAttribute("member", sub);
      request.setAttribute("subList", subList);

      request.setAttribute("availableScore", this.userDetailService.getScore(userName));
    }
    else {
      UserMessages uem = new UserMessages();
      uem.setTitle(ResourceBundleHelper.getString("webpage.timeout"));
      uem.setCode("603");
      uem.addCallBackList(ResourceBundleHelper.getString("myorder"), ResourceBundleHelper.getString("Query.Order.Status"), 
        "p/order");
      request.setAttribute(UserMessages.MESSAGE_KEY, uem);
      return PathResolver.getPath(request, response, FrontPage.ERROR_PAGE);
    }
    return PathResolver.getPath(request, response, TilesPage.PAGE_SUB);
  }

  private void parseBasketCount(HttpServletRequest request, SubForm subForm)
  {
    String[] basketIdList = request.getParameterValues("strArray");
    if (AppUtils.isBlank(basketIdList))
      throw new BusinessException("Basket at least choose one");

    Long[] basketId = new Long[basketIdList.length];
    Integer[] basketCount = new Integer[basketIdList.length];
    for (int i = 0; i < basketId.length; ++i) {
      basketId[i] = Long.valueOf(Long.parseLong(basketIdList[i]));
      basketCount[i] = Integer.valueOf(Integer.parseInt(request.getParameter("basketCount" + basketId[i])));
    }
    subForm.setBasketId(basketId);
  }
}