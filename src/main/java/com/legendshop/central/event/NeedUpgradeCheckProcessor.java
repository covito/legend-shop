package com.legendshop.central.event;

import com.legendshop.central.license.LSResponse;
import com.legendshop.central.license.LicenseEnum;
import com.legendshop.event.EventContext;
import com.legendshop.event.processor.BaseProcessor;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class NeedUpgradeCheckProcessor extends BaseProcessor<EventContext>
{
  public void process(EventContext paramEventContext)
  {
    LSResponse localLSResponse = (LSResponse)paramEventContext.getHttpRequest().getSession().getServletContext().getAttribute("LEGENSHOP_LICENSE");
    paramEventContext.setResponse(Boolean.valueOf(false));
    if (localLSResponse != null)
    {
      String str = localLSResponse.getLicense();
      if ((LicenseEnum.instance(str)) && (LicenseEnum.needUpgrade(str)))
        paramEventContext.setResponse(Boolean.valueOf(true));
    }
  }
}