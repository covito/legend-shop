package com.legendshop.core.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncodingHandler extends AbstractHandler
  implements Handler
{
  private static Logger _$3 = LoggerFactory.getLogger(EncodingHandler.class);
  private String _$2;
  private boolean _$1 = false;

  public void handle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    if ((this._$2 != null) && (((this._$1) || (paramHttpServletRequest.getCharacterEncoding() == null))))
    {
      paramHttpServletRequest.setCharacterEncoding(this._$2);
      if (this._$1)
        paramHttpServletResponse.setCharacterEncoding(this._$2);
    }
  }

  public void setEncoding(String paramString)
  {
    this._$2 = paramString;
  }

  public void setForceEncoding(boolean paramBoolean)
  {
    this._$1 = paramBoolean;
  }
}