package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.model.entity.NavigationItem;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.NavigationItemService;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin/system/navigationItem"})
public class NavigationItemController extends BaseController
  implements AdminController<NavigationItem, Long>
{

  @Autowired
  private NavigationItemService navigationItemService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, NavigationItem navigationItem)
  {
    CriteriaQuery cq = new CriteriaQuery(NavigationItem.class, curPageNO);
    PageSupport ps = this.navigationItemService.getNavigationItem(cq);
    ps.savePage(request);
    request.setAttribute("navigationItem", navigationItem);
    return PathResolver.getPath(request, response, BackPage.NAVIGATION_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, NavigationItem navigationItem) {
    this.navigationItemService.saveNavigationItem(navigationItem);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.NAVIGATION_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    NavigationItem navigationItem = this.navigationItemService.getNavigationItem(id);

    this.navigationItemService.deleteNavigationItem(navigationItem);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return PathResolver.getPath(request, response, FowardPage.NAVIGATION_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    NavigationItem navigationItem = this.navigationItemService.getNavigationItem(id);
    request.setAttribute("navigationItem", navigationItem);
    return PathResolver.getPath(request, response, BackPage.NAVIGATIONITEM_EDIT_PAGE);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response) {
    return PathResolver.getPath(request, response, BackPage.NAVIGATIONITEM_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    NavigationItem navigationItem = this.navigationItemService.getNavigationItem(id);
    request.setAttribute("navigationItem", navigationItem);
    return PathResolver.getPath(request, response, BackPage.NAVIGATIONITEM_EDIT_PAGE);
  }

  @RequestMapping(value={"/updatestatus/{naviId}/{status}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Integer updateStatus(HttpServletRequest request, HttpServletResponse response, @PathVariable Long naviId, @PathVariable Integer status)
  {
    NavigationItem navigationItem = this.navigationItemService.getNavigationItem(naviId);
    if (navigationItem == null)
      return Integer.valueOf(-1);

    if (!(status.equals(navigationItem.getStatus())))
      if (!(FoundationUtil.haveViewAllDataFunction(request))) {
        String loginName = UserManager.getUserName(request.getSession());

        if (!(loginName.equals(navigationItem.getName())))
          return Integer.valueOf(-1);

        if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status))) {
          navigationItem.setStatus(status);
          this.navigationItemService.updateNavigationItem(navigationItem);
        }
      }
      else if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status)) || (Constants.STOPUSE.equals(status))) {
        navigationItem.setStatus(status);
        this.navigationItemService.updateNavigationItem(navigationItem);
      }


    return navigationItem.getStatus();
  }
}