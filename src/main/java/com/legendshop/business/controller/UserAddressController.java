package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.UserAddress;
import com.legendshop.spi.service.UserAddressService;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/userAddress"})
public class UserAddressController extends BaseController
  implements AdminController<UserAddress, Long>
{

  @Autowired
  private UserAddressService userAddressService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, UserAddress userAddress)
  {
    CriteriaQuery cq = new CriteriaQuery(UserAddress.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    cq = hasAllDataFunction(cq, request, StringUtils.trim(userAddress.getUserName()));

    PageSupport ps = this.userAddressService.getUserAddress(cq);
    ps.savePage(request);
    request.setAttribute("userAddress", userAddress);
    return "/userAddress/userAddressList";
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, UserAddress userAddress) {
    this.userAddressService.saveUserAddress(userAddress);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return "forward:/admin/userAddress/query.htm";
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    UserAddress userAddress = this.userAddressService.getUserAddress(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), userAddress.getUserName());
    if (result != null)
      return result;

    this.userAddressService.deleteUserAddress(userAddress);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return "forward:/admin/userAddress/query.htm";
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    UserAddress userAddress = this.userAddressService.getUserAddress(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), userAddress.getUserName());
    if (result != null)
      return result;

    request.setAttribute("#entityClassInstance", userAddress);
    return "/userAddress/userAddress";
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response) {
    return "/userAddress/userAddress";
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    UserAddress userAddress = this.userAddressService.getUserAddress(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), userAddress.getUserName());
    if (result != null)
      return result;

    request.setAttribute("userAddress", userAddress);
    return "forward:/admin/userAddress/query.htm";
  }
}