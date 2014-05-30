package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.event.CoreEventId;
import com.legendshop.event.EventContext;
import com.legendshop.event.EventHome;
import com.legendshop.event.GenericEvent;
import com.legendshop.spi.page.BackPage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin/license"})
public class LicenseController extends BaseController
{
  @RequestMapping({"/upgrade"})
  public String upgrade(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, BackPage.UPGRADE_PAGE);
  }

  @RequestMapping({"/postUpgrade"})
  @ResponseBody
  public Integer postUpgrade(HttpServletRequest request, HttpServletResponse response)
  {
    EventContext eventContext = new EventContext(request);
    EventHome.publishEvent(new GenericEvent(eventContext, CoreEventId.LICENSE_STATUS_CHECK_EVENT));
    return ((Integer)eventContext.getResponse());
  }
}