package com.legendshop.core.randing;

import com.legendshop.util.MD5Util;
import com.legendshop.util.converter.ByteConverter;
import com.legendshop.util.des.DES2;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ImageServlet extends HttpServlet
{
  private boolean _$2 = true;
  private final DES2 _$1 = new DES2();

  public void destroy()
  {
    super.destroy();
  }

  public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    doPost(paramHttpServletRequest, paramHttpServletResponse);
  }

  public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    paramHttpServletResponse.setContentType("image/jpeg");
    paramHttpServletResponse.setHeader("Pragma", "No-cache");
    paramHttpServletResponse.setHeader("Cache-Control", "no-cache");
    paramHttpServletResponse.setDateHeader("Expires", 5439830222188838912L);
    String str1 = paramHttpServletRequest.getParameter("rand");
    paramHttpServletRequest.getSession().setAttribute("randStr", str1);
    try
    {
      if (str1 != null)
      {
        String str2 = new String(this._$1.createDecryptor(this._$1.stringToByte(ByteConverter.decode(str1))));
        ImageIO.write(new Image().creatImage(str2), "JPEG", paramHttpServletResponse.getOutputStream());
      }
    }
    catch (Exception localException)
    {
      System.out.println(localException);
    }
  }

  public void doPost_backup(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    paramHttpServletResponse.setContentType("image/jpeg");
    paramHttpServletResponse.setHeader("Pragma", "No-cache");
    paramHttpServletResponse.setHeader("Cache-Control", "no-cache");
    paramHttpServletResponse.setDateHeader("Expires", 5439830222188838912L);
    _$1(paramHttpServletRequest, paramHttpServletResponse);
  }

  private void _$1(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
  {
    String str = RandomStringUtils.random(4, true, true);
    try
    {
      ImageIO.write(new Image().creatImage(str), "JPEG", paramHttpServletResponse.getOutputStream());
      if (this._$2)
      {
        paramHttpServletRequest.getSession().setAttribute("LEGENDSHOP_RANDNUM", MD5Util.toMD5(str));
      }
      else
      {
        Cookie localCookie = new Cookie("LEGENDSHOP_RANDNUM", MD5Util.toMD5(str));
        localCookie.setPath("/");
        localCookie.setMaxAge(-1);
        paramHttpServletResponse.addCookie(localCookie);
      }
    }
    catch (Exception localException)
    {
      System.out.println(localException);
    }
  }

  public void init()
    throws ServletException
  {
  }

  public void setUseSession(boolean paramBoolean)
  {
    this._$2 = paramBoolean;
  }
}