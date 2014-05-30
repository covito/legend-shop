package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.newservice.EventService;
import com.legendshop.model.entity.UserEvent;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
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
@RequestMapping({"/admin/event"})
public class EventController extends BaseController
  implements AdminController<UserEvent, Long>
{

  @Autowired
  private EventService eventService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, UserEvent userEvent)
  {
    CriteriaQuery cq = new CriteriaQuery(UserEvent.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());

    if (!(AppUtils.isBlank(userEvent.getUserName())))
      cq.like("userName", "%" + StringUtils.trim(userEvent.getUserName()) + "%");

    cq.eq("modifyUser", userEvent.getModifyUser());
    cq.eq("type", userEvent.getType());
    cq.ge("createTime", userEvent.getStartTime());
    cq.le("createTime", userEvent.getEndTime());
    cq.addOrder("desc", "createTime");

    PageSupport ps = this.eventService.getEvent(cq);
    ps.savePage(request);
    request.setAttribute("event", userEvent);
    return PathResolver.getPath(request, response, BackPage.EVENT_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, UserEvent userEvent) {
    this.eventService.saveEvent(userEvent);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.EVENT_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    UserEvent userEvent = this.eventService.getEvent(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), userEvent.getUserName());
    if (result != null)
      return result;

    this.eventService.deleteEvent(userEvent);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return PathResolver.getPath(request, response, FowardPage.EVENT_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    UserEvent userEvent = this.eventService.getEvent(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), userEvent.getUserName());
    if (result != null)
      return result;

    request.setAttribute("event", userEvent);
    return PathResolver.getPath(request, response, BackPage.EVENT_EDIT_PAGE);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response) {
    return PathResolver.getPath(request, response, BackPage.EVENT_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    UserEvent userEvent = this.eventService.getEvent(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), userEvent.getUserName());
    if (result != null)
      return result;

    request.setAttribute("event", userEvent);
    return PathResolver.getPath(request, response, FowardPage.EVENT_QUERY);
  }
}