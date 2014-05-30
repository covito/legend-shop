package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.LoginHistory;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.service.LoginHistoryService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/loginHistory"})
public class LoginHistoryController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(LoginHistoryController.class);

  @Autowired
  private LoginHistoryService loginHistoryService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, LoginHistory login)
  {
    CriteriaQuery cq = new CriteriaQuery(LoginHistory.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    cq.eq("userName", login.getUserName());
    cq.ge("time", login.getStartTime());
    cq.le("time", login.getEndTime());
    cq.addOrder("desc", "time");

    PageSupport ps = this.loginHistoryService.getLoginHistory(cq);
    ps.savePage(request);
    request.setAttribute("login", login);
    return PathResolver.getPath(request, response, BackPage.LOGIN_HIST_LIST_PAGE);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, BackPage.LOGIN_HIST_LIST_PAGE);
  }

  @RequestMapping({"/sum"})
  public String loginHistorySum(HttpServletRequest request, HttpServletResponse response, String curPageNO, LoginHistory login)
  {
    SqlQuery query = new SqlQuery(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue(), curPageNO);
    Map map = new HashMap();
    if (!(AppUtils.isBlank(login.getStartTime()))) {
      map.put("startTime", login.getStartTime().toString());
      query.addParams(login.getStartTime());
    }
    if (!(AppUtils.isBlank(login.getEndTime()))) {
      map.put("endTime", login.getEndTime().toString());
      query.addParams(login.getEndTime());
    }
    if (!(AppUtils.isBlank(login.getUserName()))) {
      map.put("userName", login.getUserName());
    }

    String sql = ConfigCode.getInstance().getCode("biz.loginHistorySum", map);
    String countSql = ConfigCode.getInstance().getCode("biz.loginHistoryCount", map);
    query.setQueryString(sql);
    query.setAllCountString(countSql);
    PageSupport ps = this.loginHistoryService.getLoginHistoryBySQL(query);
    ps.savePage(request);
    request.setAttribute("login", login);
    return PathResolver.getPath(request, response, BackPage.LOGIN_HIST_SUM_PAGE);
  }
}