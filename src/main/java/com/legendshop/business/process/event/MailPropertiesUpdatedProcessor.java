package com.legendshop.business.process.event;

import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.processor.BaseProcessor;
import com.legendshop.model.entity.SystemParameter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailPropertiesUpdatedProcessor extends BaseProcessor<SystemParameter>
{
  private final Logger log = LoggerFactory.getLogger(MailPropertiesUpdatedProcessor.class);
  private List<String> parameterList;

  public void setParameterList(List<String> parameterList)
  {
    this.parameterList = parameterList;
  }

  public boolean isSupport(SystemParameter systemParameter)
  {
    return this.parameterList.contains(systemParameter.getName());
  }

  public void process(SystemParameter systemParameter)
  {
    this.log.info("PropertiesUpdater update mail parameter {} , value {}", systemParameter.getName(), systemParameter.getValue());
    PropertiesUtil.setObject(SysParameterEnum.MAIL_PROPERTIES_CHANGED, Boolean.valueOf(true));
  }
}