package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Message;
import com.legendshop.spi.service.MessageService;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/message"})
public class MessageController extends BaseController
  implements AdminController<Message, String>
{

  @Autowired
  private MessageService messageService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Message message)
  {
    CriteriaQuery cq = new CriteriaQuery(Message.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    cq = hasAllDataFunction(cq, request, StringUtils.trim(message.getSender()));

    cq.add();
    PageSupport ps = this.messageService.getMessage(cq);
    ps.savePage(request);
    request.setAttribute("message", message);
    return "/message/messageList";
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Message message) {
    this.messageService.saveMessage(message);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return "forward:/admin/message/query.htm";
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
    Message message = this.messageService.getMessage(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), message.getSender());
    if (result != null)
      return result;

    this.messageService.deleteMessage(message);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return "forward:/admin/message/query.htm";
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
    Message message = this.messageService.getMessage(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), message.getSender());
    if (result != null)
      return result;

    request.setAttribute("#entityClassInstance", message);
    return "/message/message";
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response) {
    return "/message/message";
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
    Message message = this.messageService.getMessage(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), message.getSender());
    if (result != null)
      return result;

    request.setAttribute("message", message);
    return "forward:/admin/message/query.htm";
  }
}