package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.model.entity.PayType;
import com.legendshop.model.entity.Sub;
import com.legendshop.spi.constants.OrderStatusEnum;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.service.PayTypeService;
import com.legendshop.spi.service.PaymentService;
import com.legendshop.spi.service.timer.SubService;
import com.legendshop.util.AppUtils;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/payment"})
public class PaymentController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(PaymentController.class);

  @Autowired
  private PaymentService paymentService;

  @Autowired
  private SubService subService;

  @Autowired
  private PayTypeService payTypeService;

  @RequestMapping({"/payto"})
  public String payment(HttpServletRequest request, HttpServletResponse response)
  {
    String userName = UserManager.getUserName(request.getSession());
    if (AppUtils.isBlank(userName)) {
      throw new RuntimeException("not logined yet!");
    }

    String out_trade_no = request.getParameter("subNumber");
    checkNull("out_trade_no", out_trade_no);
    String payTypeId = request.getParameter("payTypeId");
    checkNull("payTypeId", payTypeId);
    Sub sub = this.subService.getSubBySubNumber(out_trade_no);

    if (sub == null) {
      throw new NotFoundException("Can not find order");
    }

    updateSub(sub, payTypeId);

    String subject = sub.getProdName();

    String body = request.getParameter("others");

    String price = String.valueOf(sub.getActualTotal());

    String payment_result = this.paymentService.payto(sub.getShopName(), userName, payTypeId, out_trade_no, subject, body, 
      price, request.getRemoteAddr());
    this.log.debug("payment result = {}", payment_result);

    request.setAttribute("payment_result", payment_result);
    return PathResolver.getPath(request, response, BackPage.PAY_PAGE);
  }

  private void updateSub(Sub sub, String payTypeId) {
    PayType payType = this.payTypeService.getPayTypeByIdAndName(sub.getShopName(), payTypeId);

    if (payType != null) {
      sub.setPayTypeName(payType.getPayTypeName());
      sub.setPayTypeId(payType.getPayTypeId());
      sub.setPayId(payType.getPayId());
      sub.setPayDate(new Date());
      sub.setStatus(OrderStatusEnum.UNPAY.value());
      this.subService.updateSub(sub);
    }
  }

  private void checkNull(String name, String value)
  {
    if (AppUtils.isBlank(value))
      throw new BusinessException(name + " can no be null", "405");
  }
}