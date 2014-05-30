package com.legendshop.model;

import java.io.Serializable;

public class MailInfo
  implements Serializable
{
  private static final long serialVersionUID = -5571413803153472735L;
  private String to;
  private String subject;
  private String text;

  public String getTo()
  {
    return this.to;
  }

  public void setTo(String to)
  {
    this.to = to;
  }

  public String getSubject()
  {
    return this.subject;
  }

  public void setSubject(String subject)
  {
    this.subject = subject;
  }

  public String getText()
  {
    return this.text;
  }

  public void setText(String text)
  {
    this.text = text;
  }
}