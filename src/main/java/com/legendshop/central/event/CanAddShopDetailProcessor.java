package com.legendshop.central.event;

import com.legendshop.central.dao.CentralDao;
import com.legendshop.central.license.LSResponse;
import com.legendshop.central.license.LicenseEnum;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.EventContext;
import com.legendshop.event.processor.BaseProcessor;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CanAddShopDetailProcessor extends BaseProcessor<EventContext>
{
  private CentralDao _$1;

  public void process(EventContext paramEventContext)
  {
    LSResponse localLSResponse = (LSResponse)paramEventContext.getHttpRequest().getSession().getServletContext().getAttribute("LEGENSHOP_LICENSE");
    if (!(((Boolean)PropertiesUtil.getObject(SysParameterEnum.OPEN_SHOP, Boolean.class)).booleanValue()))
    {
      paramEventContext.setResponse(Boolean.valueOf(false));
      return;
    }
    Integer localInteger = null;
    if (localLSResponse == null)
    {
      localInteger = _$1(null, LicenseEnum.FREE);
    }
    else
    {
      String str = localLSResponse.getLicense();
      if (LicenseEnum.instance(str))
        localInteger = _$1(localLSResponse, LicenseEnum.valueOf(str));
      else
        localInteger = _$1(localLSResponse, LicenseEnum.FREE);
    }
    if (localInteger == null)
    {
      paramEventContext.setResponse(Boolean.valueOf(true));
      return;
    }
    paramEventContext.setResponse(Boolean.valueOf(localInteger.intValue() > _$1().longValue()));
  }

  private Integer _$1(LSResponse paramLSResponse, LicenseEnum paramLicenseEnum)
  {
    if ((paramLSResponse != null) && (paramLSResponse.getShopCount() != null))
      return paramLSResponse.getShopCount();
    if ((LicenseEnum.B2C_YEAR.equals(paramLicenseEnum)) || (LicenseEnum.C2C_YEAR.equals(paramLicenseEnum)))
      return Integer.valueOf(200);
    if ((LicenseEnum.FREE.equals(paramLicenseEnum)) || (LicenseEnum.EXPIRED.equals(paramLicenseEnum)))
      return Integer.valueOf(100);
    if ((LicenseEnum.B2C_ALWAYS.equals(paramLicenseEnum)) || (LicenseEnum.C2C_ALWAYS.equals(paramLicenseEnum)))
      return null;
    return Integer.valueOf(0);
  }

  private Long _$1()
  {
    return this._$1.getMaxShopDetail();
  }

  public void setCentralDao(CentralDao paramCentralDao)
  {
    this._$1 = paramCentralDao;
  }
}