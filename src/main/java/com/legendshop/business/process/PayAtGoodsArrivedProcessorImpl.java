package com.legendshop.business.process;

import com.legendshop.spi.processor.PaymentProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayAtGoodsArrivedProcessorImpl
  implements PaymentProcessor
{
  private static Logger log = LoggerFactory.getLogger(PayAtGoodsArrivedProcessorImpl.class);

  public String getName()
  {
    return "货到付款";
  }

  public String payto(String shopName, String userName, String payTypeId, String out_trade_no, String subject, String body, String price, String ip)
  {
    return null;
  }
}