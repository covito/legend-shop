package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Navigation;
import com.legendshop.model.entity.NavigationItem;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.NavigationItemService;
import com.legendshop.spi.service.NavigationService;
import com.legendshop.util.AppUtils;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin/system/navigation"})
public class NavigationController extends BaseController
  implements AdminController<Navigation, Long>
{

  @Autowired
  private NavigationService navigationService;

  @Autowired
  private NavigationItemService navigationItemService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Navigation navigation)
  {
    CriteriaQuery cq = new CriteriaQuery(Navigation.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    PageSupport ps = this.navigationService.getNavigation(cq);
    List list = ps.getResultList();
    if (AppUtils.isNotBlank(list)) {
      List navigationItemList = this.navigationItemService.getAllNavigationItem();

      Iterator localIterator1 = list.iterator(); while (localIterator1.hasNext()) { Navigation navi = (Navigation)localIterator1.next();
        for (Iterator localIterator2 = navigationItemList.iterator(); localIterator2.hasNext(); ) { NavigationItem navigationItem = (NavigationItem)localIterator2.next();
          navi.addSubItems(navigationItem);
        }
      }
    }

    ps.savePage(request);
    request.setAttribute("navigation", navigation);

    String path = PathResolver.getPath(request, response, BackPage.NAVIGATION_LIST_PAGE);
    return path;
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Navigation navigation) {
    this.navigationService.saveNavigation(navigation);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.NAVIGATION_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Navigation navigation = this.navigationService.getNavigation(id);
    this.navigationService.deleteNavigation(navigation);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return PathResolver.getPath(request, response, FowardPage.NAVIGATION_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Navigation navigation = this.navigationService.getNavigation(id);
    request.setAttribute("navigation", navigation);
    return PathResolver.getPath(request, response, BackPage.NAVIGATION_EDIT_PAGE);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, BackPage.NAVIGATION_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    Navigation navigation = this.navigationService.getNavigation(id);
    request.setAttribute("navigation", navigation);

    return PathResolver.getPath(request, response, BackPage.NAVIGATION_EDIT_PAGE);
  }

  @RequestMapping(value={"/updatestatus/{naviId}/{status}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Integer updateStatus(HttpServletRequest request, HttpServletResponse response, @PathVariable Long naviId, @PathVariable Integer status)
  {
    Navigation navigation = this.navigationService.getNavigation(naviId);
    if (navigation == null)
      return Integer.valueOf(-1);

    if (!(status.equals(navigation.getStatus())))
      if (!(FoundationUtil.haveViewAllDataFunction(request))) {
        String loginName = UserManager.getUserName(request.getSession());

        if (!(loginName.equals(navigation.getName())))
          return Integer.valueOf(-1);

        if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status))) {
          navigation.setStatus(status);
          this.navigationService.updateNavigation(navigation);
        }

      }
      else if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status)) || (Constants.STOPUSE.equals(status))) {
        navigation.setStatus(status);
        this.navigationService.updateNavigation(navigation);
      }


    return navigation.getStatus();
  }

  @RequestMapping({"/querysearth/{id}"})
  public String querysearth(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Navigation navigation = this.navigationService.getNavigation(id);

    request.setAttribute("navigation", navigation);

    return PathResolver.getPath(request, response, BackPage.NAVIGATIONITEM_EDIT_PAGE);
  }
}