package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.SystemParameter;
import com.legendshop.spi.constants.SysParamGroupEnum;
import com.legendshop.spi.event.SysParamUpdateEvent;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.SystemParameterService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/system/systemParameter"})
public class SystemParameterController extends BaseController
{

  @Autowired
  private SystemParameterService systemParameterService;

  @RequestMapping({"/query/{groupId}"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, SystemParameter systemParameter, @PathVariable String groupId)
  {
    CriteriaQuery cq = new CriteriaQuery(SystemParameter.class, curPageNO, "javascript:pager");
    cq.setPageSize(30);
    cq.eq("name", systemParameter.getName());
    cq.eq("changeOnline", "Y");
    cq.eq("groupId", SysParamGroupEnum.getName(groupId));
    cq.addOrder("asc", "displayOrder");

    PageSupport ps = this.systemParameterService.getSystemParameterList(cq);
    ps.savePage(request);
    request.setAttribute("groupId", groupId);
    request.setAttribute("bean", systemParameter);
    return PathResolver.getPath(request, response, BackPage.PARAM_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, SystemParameter systemParameter)
  {
    SystemParameter parameter = this.systemParameterService.getSystemParameter(systemParameter.getName());
    String groupId = SysParamGroupEnum.SYS.name();
    if (parameter != null) {
      groupId = parameter.getGroupId();
      parameter.setValue(systemParameter.getValue());
      PropertiesUtil.setParameter(parameter);

      EventHome.publishEvent(new SysParamUpdateEvent(parameter));
      this.systemParameterService.update(parameter);
      saveMessage(request, ResourceBundleHelper.getSucessfulString());
    } else {
      saveMessage(request, ResourceBundleHelper.getErrorString());
    }
    return FowardPage.PARAM_LIST_QUERY.getValue(request, response) + "/" + SysParamGroupEnum.getValue(groupId);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable String id)
  {
    saveMessage(request, ResourceBundleHelper.getErrorString());
    return PathResolver.getPath(request, response, FowardPage.PARAM_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable String id)
  {
    SystemParameter systemParameter = this.systemParameterService.getSystemParameter(id);
    request.setAttribute("bean", systemParameter);
    return PathResolver.getPath(request, response, BackPage.PARAM_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable SystemParameter systemParameter)
  {
    this.systemParameterService.update(systemParameter);
    return PathResolver.getPath(request, response, FowardPage.PARAM_LIST_QUERY);
  }
}