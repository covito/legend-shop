package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.exception.ConflictException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.PayType;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.processor.PaymentProcessor;
import com.legendshop.spi.service.PayTypeService;
import com.legendshop.spi.service.PaymentService;
import com.legendshop.util.AppUtils;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/paytype"})
public class PayTypeAdminController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(PayTypeAdminController.class);

  @Autowired
  private PayTypeService payTypeService;

  @Autowired
  private PaymentService paymentService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, PayType payType)
  {
    CriteriaQuery cq = new CriteriaQuery(PayType.class, curPageNO, "javascript:pager");
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());

    String name = UserManager.getUserName(request.getSession());
    if (name == null)
      throw new AuthorizationException(name + " did not logon yet!");

    cq.eq("userName", name);

    PageSupport ps = this.payTypeService.getPayTypeList(cq);
    ps.savePage(request);
    request.setAttribute("bean", payType);
    return PathResolver.getPath(request, response, BackPage.PAY_TYPE_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, PayType payType)
  {
    String name = UserManager.getUserName(request.getSession());
    if (AppUtils.isBlank(name))
      throw new AuthorizationException("you are not logon yet!");

    payType.setUserName(name);
    PaymentProcessor paymentProcessor = this.paymentService.getPaymentProcessor(payType.getPayTypeId());
    if (AppUtils.isNotBlank(paymentProcessor))
      payType.setPayTypeName(paymentProcessor.getName());

    if (AppUtils.isNotBlank(payType.getPayId()))
      return update(request, response, payType);

    PayType origin = this.payTypeService.getPayTypeByIdAndName(payType.getUserName(), payType.getPayTypeId());
    if (AppUtils.isNotBlank(origin))
      throw new ConflictException("你已经创建一个叫 “" + payType.getPayTypeName() + "” 的支付方式");

    this.payTypeService.save(payType);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.PAY_TYPE_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    this.payTypeService.delete(id);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return PathResolver.getPath(request, response, FowardPage.PAY_TYPE_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    PayType payType = this.payTypeService.getPayTypeById(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), payType.getUserName());
    if (result != null)
      return result;

    request.setAttribute("bean", payType);
    return PathResolver.getPath(request, response, BackPage.PAY_TYPE_EDIT_PAGE);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, BackPage.PAY_TYPE_EDIT_PAGE);
  }

  private String update(HttpServletRequest request, HttpServletResponse response, PayType payType)
  {
    PayType origin = this.payTypeService.getPayTypeById(payType.getPayId());
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), origin.getUserName());
    if (result != null)
      return result;

    this.log.info("{} update paytype, Partner: {}, ValidateKey: {}, SellerEmail: {} ", 
      new Object[] { origin.getUserName(), origin.getPartner(), origin.getValidateKey(), origin.getSellerEmail() });
    origin.setMemo(payType.getMemo());
    origin.setPartner(payType.getPartner());
    origin.setPayTypeId(payType.getPayTypeId());
    origin.setPayTypeName(payType.getPayTypeName());
    origin.setSellerEmail(payType.getSellerEmail());
    origin.setValidateKey(payType.getValidateKey());
    try {
      this.payTypeService.update(origin);
    } catch (DataIntegrityViolationException e) {
      throw new ConflictException(e, "你已经创建一个叫 “" + payType.getPayTypeName() + "” 的支付方式", "409");
    }
    return PathResolver.getPath(request, response, FowardPage.PAY_TYPE_LIST_QUERY);
  }
}