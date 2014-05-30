package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.VisitLog;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.VisitLogService;
import com.legendshop.util.AppUtils;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/visitLog"})
public class VisitLogAdminController extends BaseController
  implements AdminController<VisitLog, Long>
{
  public static String LIST_PAGE = "/visitLog/visitLogList";

  @Autowired
  private VisitLogService visitLogService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, VisitLog visitLog)
  {
    CriteriaQuery cq = new CriteriaQuery(VisitLog.class, curPageNO, "javascript:pager");

    if (CommonServiceUtil.haveViewAllDataFunction(request)) {
      if (!(AppUtils.isBlank(visitLog.getShopName())))
        cq.like("shopName", "%" + StringUtils.trim(visitLog.getShopName()) + "%");
    }
    else {
      String name = UserManager.getUserName(request.getSession());
      if (name == null)
        throw new AuthorizationException("you are not logon yet!");

      cq.eq("shopName", name);
    }
    cq.ge("date", visitLog.getStartTime());
    cq.le("date", visitLog.getEndTime());
    cq.eq("page", visitLog.getPage());
    if (AppUtils.isNotBlank(visitLog.getUserName())) {
      cq.like("userName", visitLog.getUserName() + "%");
    }

    if (AppUtils.isNotBlank(visitLog.getProductName()))
      cq.like("productName", "%" + visitLog.getProductName() + "%");

    if (!(CommonServiceUtil.isDataForExport(cq, request)))
      cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());

    if (!(CommonServiceUtil.isDataSortByExternal(cq, request))) {
      cq.addOrder("desc", "date");
    }

    PageSupport ps = this.visitLogService.getVisitLogList(cq);
    ps.savePage(request);
    request.setAttribute("bean", visitLog);
    return PathResolver.getPath(request, response, BackPage.VLOG_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, VisitLog visitLog)
  {
    this.visitLogService.save(visitLog);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.VLOG_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    this.visitLogService.delete(id);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return PathResolver.getPath(request, response, FowardPage.VLOG_LIST_QUERY);
  }

  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return null;
  }

  public String update(HttpServletRequest request, HttpServletResponse response, Long id)
  {
    return null;
  }
}