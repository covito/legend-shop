package com.legendshop.util;

import java.io.File;

public class FileTimeWrapper
  implements Comparable<FileTimeWrapper>
{
  private File _$3;
  private Boolean _$2;
  private String _$1;

  public String getFileName()
  {
    return this._$1;
  }

  public void setFileName(String paramString)
  {
    this._$1 = paramString;
  }

  public void setFile(File paramFile)
  {
    this._$3 = paramFile;
  }

  public FileTimeWrapper(File paramFile)
  {
    this._$3 = paramFile;
  }

  public int compareTo(FileTimeWrapper paramFileTimeWrapper)
  {
    if (this._$3.lastModified() - paramFileTimeWrapper.getFile().lastModified() > 5439830582966091776L)
      return -1;
    if (this._$3.lastModified() - paramFileTimeWrapper.getFile().lastModified() < 5439830582966091776L)
      return 1;
    return 0;
  }

  public File getFile()
  {
    return this._$3;
  }

  public Boolean getIsFile()
  {
    return this._$2;
  }

  public void setIsFile(Boolean paramBoolean)
  {
    this._$2 = paramBoolean;
  }
}