package com.legendshop.core.exception;

import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.core.model.UserMessages;

public abstract class BaseException extends RuntimeException
{
  private static final long serialVersionUID = 2848225058035251507L;
  protected String errorCode;
  protected UserMessages userMessages;

  public BaseException(String paramString1, String paramString2)
  {
    super(paramString1);
    this.errorCode = paramString2;
    _$1(paramString1);
  }

  public BaseException(Throwable paramThrowable, String paramString1, String paramString2)
  {
    super(paramString1, paramThrowable);
    this.errorCode = paramString2;
    _$1(paramString1);
  }

  public String getErrorCode()
  {
    return this.errorCode;
  }

  public void setErrorCode(String paramString)
  {
    this.errorCode = paramString;
  }

  public UserMessages getUserMessages()
  {
    return this.userMessages;
  }

  public void setUserMessages(UserMessages paramUserMessages)
  {
    this.userMessages = paramUserMessages;
  }

  public BaseException(UserMessages paramUserMessages)
  {
    super(paramUserMessages.getDesc());
    this.userMessages = paramUserMessages;
  }

  public String getDesc()
  {
    return getUserMessages().getDesc();
  }

  public String getTitle()
  {
    return getUserMessages().getTitle();
  }

  private void _$1(String paramString)
  {
    UserMessages localUserMessages = new UserMessages();
    localUserMessages.setCode(this.errorCode);
    localUserMessages.setTitle(ResourceBundleHelper.getString("error.code." + this.errorCode, "ERROR"));
    localUserMessages.setDesc(paramString);
    setUserMessages(localUserMessages);
  }
}