package com.legendshop.model.template;

import com.legendshop.model.StatusKeyValueEntity;
import java.io.Serializable;
import java.util.List;

public class Templet
  implements Serializable
{
  private static final long serialVersionUID = 7955507406362239884L;
  private String id;
  private List<StatusKeyValueEntity> languages;
  private List<StatusKeyValueEntity> styles;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public List<StatusKeyValueEntity> getLanguages()
  {
    return this.languages;
  }

  public void setLanguages(List<StatusKeyValueEntity> languages)
  {
    this.languages = languages;
  }

  public List<StatusKeyValueEntity> getStyles()
  {
    return this.styles;
  }

  public void setStyles(List<StatusKeyValueEntity> styles)
  {
    this.styles = styles;
  }
}