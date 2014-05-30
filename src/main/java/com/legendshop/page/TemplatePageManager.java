package com.legendshop.page;

import java.util.List;

public abstract interface TemplatePageManager
{
  public abstract List<String> getTemplateByPage(String paramString);

  public abstract void registerTemplatePage(TemplatePage paramTemplatePage);
}