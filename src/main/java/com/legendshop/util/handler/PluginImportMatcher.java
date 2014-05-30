package com.legendshop.util.handler;

import com.legendshop.plugins.Plugin;
import com.legendshop.plugins.PluginConfig;
import com.legendshop.plugins.PluginManager;
import com.legendshop.plugins.PluginStatusEnum;
import com.legendshop.util.AppUtils;
import java.util.Iterator;
import java.util.List;

public class PluginImportMatcher extends ImportMatcher
{
  public boolean isMatch()
  {
    String str = getValue();
    if (AppUtils.isBlank(str))
      return false;
    PluginManager localPluginManager = getPluginManager();
    if ((localPluginManager != null) && (localPluginManager.getPlugins() != null))
    {
      Iterator localIterator = localPluginManager.getPlugins().iterator();
      while (localIterator.hasNext())
      {
        Plugin localPlugin = (Plugin)localIterator.next();
        if (isThePluginNormal(localPlugin, str))
        {
          PluginConfig localPluginConfig = resolvePluginConfig(localPlugin, false);
          return (localPluginConfig.getStatus().equals(PluginStatusEnum.Y));
        }
      }
    }
    return false;
  }

  protected boolean isThePluginNormal(Plugin paramPlugin, String paramString)
  {
    return ((PluginStatusEnum.Y.equals(paramPlugin.getPluginConfig().getStatus())) && (paramString.equals(paramPlugin.getPluginConfig().getPulginId())));
  }

  protected PluginConfig resolvePluginConfig(Plugin paramPlugin, boolean paramBoolean)
  {
    int i = 0;
    Object localObject = paramPlugin.getPluginConfig();
    List localList = getDbPluginConfigList();
    if (localList != null)
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        PluginConfig localPluginConfig = (PluginConfig)localIterator.next();
        if (localPluginConfig.getPulginId().equals(paramPlugin.getPluginConfig().getPulginId()))
        {
          localObject = localPluginConfig;
          i = 1;
          paramPlugin.updateStatus(((PluginConfig)localObject).getStatus());
          break;
        }
      }
    }
    if ((i == 0) && (paramBoolean))
      getPluginManager().savePlugin((PluginConfig)localObject);
    return ((PluginConfig)localObject);
  }

  public List<PluginConfig> getDbPluginConfigList()
  {
    return PluginRepository.getInstance().getDbPluginConfigList();
  }

  public PluginManager getPluginManager()
  {
    return PluginRepository.getInstance();
  }
}