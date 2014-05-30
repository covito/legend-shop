package com.legendshop.central.event;

import com.legendshop.central.license.LSResponse;
import com.legendshop.central.license.LicenseEnum;
import com.legendshop.central.license.LicenseHelper;
import com.legendshop.event.EventContext;
import com.legendshop.event.processor.BaseProcessor;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionCheckProcessor extends BaseProcessor<EventContext>
{
  private static Logger _$1 = LoggerFactory.getLogger(FunctionCheckProcessor.class);

  public void process(EventContext paramEventContext)
  {
    Boolean localBoolean = Boolean.valueOf(true);
    String str1 = (String)paramEventContext.getRequest();
    LSResponse localLSResponse = (LSResponse)paramEventContext.getHttpRequest().getSession().getServletContext().getAttribute("LEGENSHOP_LICENSE");
    if (localLSResponse == null)
      try
      {
        localLSResponse = LicenseHelper.getPersistedResopnse();
      }
      catch (Exception localException)
      {
      }
    if (localLSResponse != null)
    {
      String str2 = localLSResponse.getLicense();
      if ((LicenseEnum.FREE.name().equals(str2)) || (LicenseEnum.UNKNOWN.name().equals(str2)))
      {
        _$1.debug("user name = {} did not have function on this componment", str1);
        localBoolean = Boolean.valueOf(false);
      }
    }
    paramEventContext.setResponse(localBoolean);
  }
}