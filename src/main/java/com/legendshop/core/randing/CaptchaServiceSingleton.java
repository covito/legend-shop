package com.legendshop.core.randing;

import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ServiceLocatorIF;
import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

public class CaptchaServiceSingleton
{
  private static ImageCaptchaService _$1;

  public static ImageCaptchaService getInstance()
  {
    if (_$1 == null)
    {
      CaptchaEngine localCaptchaEngine = (CaptchaEngine)ContextServiceLocator.getInstance().getBean("imageEngine");
      _$1 = new DefaultManageableImageCaptchaService(new FastHashMapCaptchaStore(), localCaptchaEngine, 180, 100000, 75000);
    }
    return _$1;
  }
}