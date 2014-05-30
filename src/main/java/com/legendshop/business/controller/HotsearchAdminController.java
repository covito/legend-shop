package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.QueryMap;
import com.legendshop.core.dao.support.SimpleSqlQuery;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.Hotsearch;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.HotsearchService;
import com.legendshop.util.sql.ConfigCode;
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
@RequestMapping({"/admin/hotsearch"})
public class HotsearchAdminController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(HotsearchAdminController.class);

  @Autowired
  private HotsearchService hotsearchService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Hotsearch hotsearch)
  {
    QueryMap map = new QueryMap();
    map.put("sort", hotsearch.getSort());
    map.put("title", hotsearch.getTitle());
    map.hasAllDataFunction("userName", hotsearch.getUserName());
    String queryAllSQL = ConfigCode.getInstance().getCode("prod.queryHotSearchCount", map);
    String querySQL = ConfigCode.getInstance().getCode("prod.queryHotSearch", map);
    SimpleSqlQuery query = new SimpleSqlQuery(Hotsearch.class, querySQL, queryAllSQL, map.toArray());
    PageSupport ps = this.hotsearchService.query(query);
    ps.savePage(request);
    request.setAttribute("bean", hotsearch);
    return PathResolver.getPath(request, response, BackPage.HOT_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Hotsearch hotsearch)
  {
    String userName = UserManager.getUserName(request);
    if (hotsearch.getId() != null) {
      Hotsearch entity = this.hotsearchService.getHotsearchById(hotsearch.getId());
      if (entity != null)
      {
        if ((!(CommonServiceUtil.haveViewAllDataFunction(request))) && (!(userName.equals(entity.getUserName()))))
          throw new PermissionException("Can't edit Hotsearch does not onw to you!");

        entity.setDate(new Date());
        entity.setMsg(hotsearch.getMsg());
        entity.setTitle(hotsearch.getTitle());
        entity.setSort(hotsearch.getSort());
        entity.setSeq(hotsearch.getSeq());
        entity.setStatus(hotsearch.getStatus());
        this.hotsearchService.updateHotsearch(entity);
      }
    } else {
      hotsearch.setUserId(UserManager.getUserId(request.getSession()));
      hotsearch.setUserName(userName);
      hotsearch.setDate(new Date());
      this.hotsearchService.saveHotsearch(hotsearch);
    }
    saveMessage(request, ResourceBundleHelper.getSucessfulString());
    return PathResolver.getPath(request, response, FowardPage.HOT_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Hotsearch hotsearch = this.hotsearchService.getHotsearchById(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), hotsearch.getUserName());
    if (result != null)
      return result;

    this.log.info("{} delete Hotsearch Title {}, Msg {}", 
      new Object[] { hotsearch.getUserName(), hotsearch.getTitle(), hotsearch.getMsg() });
    this.hotsearchService.delete(id);
    saveMessage(request, ResourceBundleHelper.getDeleteString());
    return PathResolver.getPath(request, response, FowardPage.HOT_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Hotsearch hotsearch = this.hotsearchService.getHotsearchById(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), hotsearch.getUserName());
    if (result != null)
      return result;

    request.setAttribute("bean", hotsearch);
    return PathResolver.getPath(request, response, BackPage.HOT_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Hotsearch hotsearch)
  {
    Hotsearch origin = this.hotsearchService.getHotsearchById(hotsearch.getId());
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), origin.getUserName());
    if (result != null)
      return result;

    this.log.info("{} update Hotsearch Title{}", origin.getUserName(), origin.getTitle());
    hotsearch.setUserId(origin.getUserId());
    hotsearch.setUserName(origin.getUserName());
    this.hotsearchService.updateHotsearch(hotsearch);
    return PathResolver.getPath(request, response, FowardPage.HOT_LIST_QUERY);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, BackPage.HOT_EDIT_PAGE);
  }

  @RequestMapping(value={"/updatestatus/{hotId}/{status}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Integer updateStatus(HttpServletRequest request, HttpServletResponse response, @PathVariable Long hotId, @PathVariable Integer status)
  {
    Hotsearch hotsearch = this.hotsearchService.getHotsearchById(hotId);
    if (hotsearch == null)
      return Integer.valueOf(-1);

    if (!(status.equals(hotsearch.getStatus())))
      if (!(FoundationUtil.haveViewAllDataFunction(request))) {
        String loginName = UserManager.getUserName(request.getSession());

        if (!(loginName.equals(hotsearch.getUserName())))
          return Integer.valueOf(-1);

        if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status))) {
          hotsearch.setStatus(status);
          this.hotsearchService.updateHotsearch(hotsearch);
        }

      }
      else if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status)) || (Constants.STOPUSE.equals(status))) {
        hotsearch.setStatus(status);
        this.hotsearchService.updateHotsearch(hotsearch);
      }


    return hotsearch.getStatus();
  }
}