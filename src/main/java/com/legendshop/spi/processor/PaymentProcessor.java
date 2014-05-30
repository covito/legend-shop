package com.legendshop.spi.processor;

import com.legendshop.core.Selectable;

public abstract interface PaymentProcessor extends Selectable
{
  public abstract String payto(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8);
}