package com.legendshop.business.service.impl;

import com.legendshop.spi.processor.PaymentProcessor;
import com.legendshop.spi.service.PaymentService;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentServiceImpl
  implements PaymentService
{
  private static Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
  private Map<String, PaymentProcessor> paymentProcessors;

  public String payto(String shopName, String userName, String payTypeId, String out_trade_no, String subject, String body, String price, String ip)
  {
    log.debug("payto shopName = {},userName = {},payTypeId = {}", new Object[] { shopName, userName, payTypeId });
    return getPaymentProcessor(payTypeId).payto(shopName, userName, payTypeId, out_trade_no, subject, body, price, ip);
  }

  public PaymentProcessor getPaymentProcessor(String payTypeId)
  {
    PaymentProcessor processor = (PaymentProcessor)this.paymentProcessors.get(payTypeId);
    if (processor == null)
      processor = (PaymentProcessor)this.paymentProcessors.get(Integer.valueOf(1));

    return processor;
  }

  public void setPaymentProcessors(Map<String, PaymentProcessor> paymentProcessors)
  {
    this.paymentProcessors = paymentProcessors;
  }
}