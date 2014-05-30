package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class ImgFile extends UploadFile
  implements BaseEntity
{
  private static final long serialVersionUID = -7521569831212302925L;
  private Long fileId;
  private String userName;
  private Long productId;
  private Short productType;
  private String filePath;
  private String fileType;
  private Integer fileSize;
  private Date upoadTime;
  private Integer status;

  public Long getFileId()
  {
    return this.fileId;
  }

  public void setFileId(Long fileId)
  {
    this.fileId = fileId;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public Long getProductId()
  {
    return this.productId;
  }

  public void setProductId(Long productId)
  {
    this.productId = productId;
  }

  public Short getProductType()
  {
    return this.productType;
  }

  public void setProductType(Short productType)
  {
    this.productType = productType;
  }

  public String getFilePath()
  {
    return this.filePath;
  }

  public void setFilePath(String filePath)
  {
    this.filePath = filePath;
  }

  public String getFileType()
  {
    return this.fileType;
  }

  public void setFileType(String fileType)
  {
    this.fileType = fileType;
  }

  public Integer getFileSize()
  {
    return this.fileSize;
  }

  public void setFileSize(Integer fileSize)
  {
    this.fileSize = fileSize;
  }

  public Date getUpoadTime()
  {
    return this.upoadTime;
  }

  public void setUpoadTime(Date upoadTime)
  {
    this.upoadTime = upoadTime;
  }

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public Serializable getId() {
    return this.fileId;
  }
}