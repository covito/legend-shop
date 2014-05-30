package com.legendshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ValidationMessage
  implements Serializable
{
  private static final long serialVersionUID = -5235165939452646497L;
  private List<ErrorInfo> errorInfos;

  public String toString()
  {
    if (this.errorInfos != null) {
      StringBuilder sb = new StringBuilder();
      for (Iterator localIterator = this.errorInfos.iterator(); localIterator.hasNext(); ) { ErrorInfo info = (ErrorInfo)localIterator.next();
        sb.append(info.getDesc()).append("\n");
      }
      return sb.toString();
    }
    return null;
  }

  public void addError(String filed, ErrorType type, String desc) {
    if (this.errorInfos == null)
      this.errorInfos = new ArrayList();

    this.errorInfos.add(new ErrorInfo(filed, type, desc));
  }

  public boolean isFailed() {
    return (this.errorInfos != null);
  }

  public List<ErrorInfo> getErrorInfos()
  {
    return this.errorInfos;
  }

  public void setErrorInfos(List<ErrorInfo> errorInfos)
  {
    this.errorInfos = errorInfos;
  }
}