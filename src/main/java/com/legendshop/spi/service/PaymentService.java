package com.legendshop.spi.service;

import com.legendshop.spi.processor.PaymentProcessor;

public abstract interface PaymentService
{
  public abstract String payto(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8);

  public abstract PaymentProcessor getPaymentProcessor(String paramString);
}