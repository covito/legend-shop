package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Cash;
import com.legendshop.spi.service.CashService;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/cash"})
public class CashController extends BaseController
  implements AdminController<Cash, Long>
{

  @Autowired
  private CashService cashService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Cash cash)
  {
    CriteriaQuery cq = new CriteriaQuery(Cash.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    cq = hasAllDataFunction(cq, request, StringUtils.trim(cash.getUserName()));

    PageSupport ps = this.cashService.getCash(cq);
    ps.savePage(request);
    request.setAttribute("cash", cash);
    return "/cash/cashList";
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Cash cash) {
    this.cashService.saveCash(cash);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return "forward:/admin/cash/query.htm";
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    Cash cash = this.cashService.getCash(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), cash.getUserName());
    if (result != null)
      return result;

    this.cashService.deleteCash(cash);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return "forward:/admin/cash/query.htm";
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    Cash cash = this.cashService.getCash(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), cash.getUserName());
    if (result != null)
      return result;

    request.setAttribute("#entityClassInstance", cash);
    return "/cash/cash";
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response) {
    return "/cash/cash";
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    Cash cash = this.cashService.getCash(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), cash.getUserName());
    if (result != null)
      return result;

    request.setAttribute("cash", cash);
    return "forward:/admin/cash/query.htm";
  }
}