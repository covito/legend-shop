package com.legendshop.model.entity;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

public abstract class UploadFile extends AbstractEntity
  implements Serializable
{
  private static final long serialVersionUID = -8055409574740815068L;
  protected MultipartFile file;

  public MultipartFile getFile()
  {
    return this.file;
  }

  public void setFile(MultipartFile file)
  {
    this.file = file;
  }
}