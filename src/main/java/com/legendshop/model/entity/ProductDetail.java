package com.legendshop.model.entity;

import java.util.List;

public class ProductDetail extends AbstractProduct
{
  private static final long serialVersionUID = 6275498676062425893L;
  private String sortName;
  private String nsortName;
  private String subNsortName;
  private String brandName;
  List<ImgFile> prodPics;
  List<Product> relProds;

  public String getSortName()
  {
    return this.sortName;
  }

  public void setSortName(String sortName)
  {
    this.sortName = sortName;
  }

  public String getNsortName()
  {
    return this.nsortName;
  }

  public void setNsortName(String nsortName)
  {
    this.nsortName = nsortName;
  }

  public String getSubNsortName()
  {
    return this.subNsortName;
  }

  public void setSubNsortName(String subNsortName)
  {
    this.subNsortName = subNsortName;
  }

  public String getBrandName()
  {
    return this.brandName;
  }

  public void setBrandName(String brandName)
  {
    this.brandName = brandName;
  }

  public List<ImgFile> getProdPics()
  {
    return this.prodPics;
  }

  public void setProdPics(List<ImgFile> prodPics)
  {
    this.prodPics = prodPics;
  }

  public List<Product> getRelProds()
  {
    return this.relProds;
  }

  public void setRelProds(List<Product> relProds)
  {
    this.relProds = relProds;
  }
}