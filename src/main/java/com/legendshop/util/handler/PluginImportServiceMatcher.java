package com.legendshop.util.handler;

import com.legendshop.plugins.Plugin;
import com.legendshop.plugins.PluginConfig;
import com.legendshop.plugins.PluginStatusEnum;
import com.legendshop.util.AppUtils;

public class PluginImportServiceMatcher extends PluginImportMatcher
{
  private Plugin _$1 = null;

  public boolean isMatch()
  {
    String str = getValue();
    if (AppUtils.isBlank(str))
      return false;
    if (isThePluginNormal(this._$1, str))
    {
      if (!(PluginStatusEnum.N.equals(this._$1.getPluginConfig().getStatus())))
        PluginRepository.getInstance().registerPlugin(this._$1);
      PluginConfig localPluginConfig = resolvePluginConfig(this._$1, true);
      if (localPluginConfig == null)
        return false;
      return (PluginStatusEnum.Y.equals(localPluginConfig.getStatus()));
    }
    return false;
  }

  public Plugin getPlugin()
  {
    return this._$1;
  }

  public void setPlugin(Plugin paramPlugin)
  {
    this._$1 = paramPlugin;
  }
}