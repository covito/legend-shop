package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Ask;
import com.legendshop.spi.service.AskService;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/ask"})
public class AskController extends BaseController
  implements AdminController<Ask, Long>
{

  @Autowired
  private AskService askService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Ask ask)
  {
    CriteriaQuery cq = new CriteriaQuery(Ask.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    cq = hasAllDataFunction(cq, request, StringUtils.trim(ask.getUserName()));

    PageSupport ps = this.askService.getAsk(cq);
    ps.savePage(request);
    request.setAttribute("ask", ask);
    return "/ask/askList";
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Ask ask) {
    this.askService.saveAsk(ask);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return "forward:/admin/ask/query.htm";
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    Ask ask = this.askService.getAsk(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), ask.getUserName());
    if (result != null)
      return result;

    this.askService.deleteAsk(ask);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return "forward:/admin/ask/query.htm";
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    Ask ask = this.askService.getAsk(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), ask.getUserName());
    if (result != null)
      return result;

    request.setAttribute("#entityClassInstance", ask);
    return "/ask/ask";
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response) {
    return "/ask/ask";
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    Ask ask = this.askService.getAsk(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), ask.getUserName());
    if (result != null)
      return result;

    request.setAttribute("ask", ask);
    return "forward:/admin/ask/query.htm";
  }
}