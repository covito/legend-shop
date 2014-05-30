package com.legendshop.core.randing;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class JcaptchaServlet extends HttpServlet
{
  public static final String CAPTCHA_IMAGE_FORMAT = "jpeg";
  private ImageCaptchaService _$1 = null;

  public void init()
    throws ServletException
  {
    WebApplicationContext localWebApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    if (localWebApplicationContext != null)
      this._$1 = ((ImageCaptchaService)BeanFactoryUtils.beanOfTypeIncludingAncestors(localWebApplicationContext, ImageCaptchaService.class));
  }

  protected void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    _$1(paramHttpServletRequest, paramHttpServletResponse);
  }

  private void _$1(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws IOException
  {
    paramHttpServletResponse.setHeader("Cache-Control", "no-store");
    paramHttpServletResponse.setHeader("Pragma", "no-cache");
    paramHttpServletResponse.setDateHeader("Expires", 5439830222188838912L);
    paramHttpServletResponse.setContentType("image/jpeg");
    ServletOutputStream localServletOutputStream = paramHttpServletResponse.getOutputStream();
    try
    {
      String str = paramHttpServletRequest.getSession(true).getId();
      BufferedImage localBufferedImage = (BufferedImage)CaptchaServiceSingleton.getInstance().getChallengeForID(str, paramHttpServletRequest.getLocale());
      ImageIO.write(localBufferedImage, "jpg", localServletOutputStream);
      localServletOutputStream.flush();
    }
    catch (CaptchaServiceException localCaptchaServiceException)
    {
    }
    finally
    {
      localServletOutputStream.close();
    }
  }
}