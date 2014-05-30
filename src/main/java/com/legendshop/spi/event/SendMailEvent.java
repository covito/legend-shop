package com.legendshop.spi.event;

import com.legendshop.event.SystemEvent;
import com.legendshop.model.MailInfo;

public class SendMailEvent extends SystemEvent<MailInfo>
{
  public SendMailEvent(String to, String subject, String text)
  {
    super(EventId.SEND_MAIL_EVENT);
    MailInfo mail = new MailInfo();
    mail.setSubject(subject);
    mail.setText(text);
    mail.setTo(to);
    setSource(mail);
  }
}