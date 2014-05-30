package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.CashFlow;
import com.legendshop.spi.service.CashFlowService;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/cashFlow"})
public class CashFlowController extends BaseController
  implements AdminController<CashFlow, Long>
{

  @Autowired
  private CashFlowService cashFlowService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, CashFlow cashFlow)
  {
    CriteriaQuery cq = new CriteriaQuery(CashFlow.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    cq = hasAllDataFunction(cq, request, StringUtils.trim(cashFlow.getUserName()));

    PageSupport ps = this.cashFlowService.getCashFlow(cq);
    ps.savePage(request);
    request.setAttribute("cashFlow", cashFlow);
    return "/cashFlow/cashFlowList";
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, CashFlow cashFlow) {
    this.cashFlowService.saveCashFlow(cashFlow);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return "forward:/admin/cashFlow/query.htm";
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    CashFlow cashFlow = this.cashFlowService.getCashFlow(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), cashFlow.getUserName());
    if (result != null)
      return result;

    this.cashFlowService.deleteCashFlow(cashFlow);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return "forward:/admin/cashFlow/query.htm";
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    CashFlow cashFlow = this.cashFlowService.getCashFlow(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), cashFlow.getUserName());
    if (result != null)
      return result;

    request.setAttribute("#entityClassInstance", cashFlow);
    return "/cashFlow/cashFlow";
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response) {
    return "/cashFlow/cashFlow";
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    CashFlow cashFlow = this.cashFlowService.getCashFlow(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), cashFlow.getUserName());
    if (result != null)
      return result;

    request.setAttribute("cashFlow", cashFlow);
    return "forward:/admin/cashFlow/query.htm";
  }
}