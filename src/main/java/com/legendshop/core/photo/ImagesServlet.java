package com.legendshop.core.photo;

import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ServiceLocatorIF;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImagesServlet extends AbstractPhotoServlet
{
  private static final long serialVersionUID = -358253459242958398L;
  Logger _$6 = LoggerFactory.getLogger(ImagesServlet.class);
  private String _$5;
  private String _$4;
  private Map<String, List<Integer>> _$3 = null;
  private int _$2;

  public void init()
    throws ServletException
  {
    this._$3 = ((Map)ContextServiceLocator.getInstance().getBean("scaleList"));
    if (this._$5 == null)
      this._$5 = PropertiesUtil.getSmallFilesAbsolutePath();
    if (this._$4 == null)
      this._$4 = PropertiesUtil.getBigFilesAbsolutePath();
    this._$2 = PropertiesUtil.getSmallImagePathPrefix().length();
  }

  public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws IOException
  {
    paramHttpServletResponse.setContentType("image/jpeg");
    paramHttpServletResponse.setDateHeader("expires", System.currentTimeMillis() + 60000L);
    paramHttpServletResponse.addHeader("Cache-Control", "max-age=60");
    String str1 = paramHttpServletRequest.getPathInfo();
    if ((str1 == null) || (str1.length() < 1))
      return;
    String str2 = this._$5 + str1;
    File localFile = new File(str2);
    if (!(localFile.isFile()))
    {
      localFile = new File(this._$4 + str1.substring(2));
      if (localFile.isFile())
      {
        List localList = (List)this._$3.get(_$1(str1));
        if (localList != null)
          outLogo(localFile, str2, paramHttpServletResponse.getOutputStream(), ((Integer)localList.get(0)).intValue(), ((Integer)localList.get(1)).intValue());
        else
          noFileError(paramHttpServletResponse, str2);
      }
      else
      {
        noFileError(paramHttpServletResponse, str2);
      }
    }
    else
    {
      outputFile(paramHttpServletResponse, localFile);
    }
  }

  private String _$1(String paramString)
  {
    return paramString.substring(1, 2);
  }

  public void destroy()
  {
  }
}