package com.legendshop.central.event;

import com.legendshop.central.license.LSResponse;
import com.legendshop.central.license.LicenseHelper;
import com.legendshop.event.EventContext;
import com.legendshop.event.processor.BaseProcessor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LicenseStatusCheckProcessor extends BaseProcessor<EventContext>
{
  private final Logger _$1 = LoggerFactory.getLogger(LicenseStatusCheckProcessor.class);

  public void process(EventContext paramEventContext)
  {
    String str = paramEventContext.getHttpRequest().getParameter("liensekey");
    LSResponse localLSResponse = null;
    try
    {
      localLSResponse = LicenseHelper.updateLicense(paramEventContext.getHttpRequest().getSession().getServletContext(), str);
    }
    catch (Exception localException)
    {
      this._$1.error("update license error happened {}", localException);
    }
    if (localLSResponse == null)
      paramEventContext.setResponse(Integer.valueOf(0));
    else
      paramEventContext.setResponse(Integer.valueOf(localLSResponse.getReturnCode()));
  }
}