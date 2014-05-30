package com.legendshop.business.process.event;

import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.processor.ThreadProcessor;
import com.legendshop.model.MailInfo;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMailProcessor extends ThreadProcessor<MailInfo>
{
  private Logger log = LoggerFactory.getLogger(SendMailProcessor.class);
  private JavaMailSenderImpl javaMailSender;

  public void process(MailInfo task)
  {
    this.log.info("SendMailProcessor process calling, task= " + task);
    if (this.log.isDebugEnabled())
      this.log.debug("send mail to {} ,subject is {}", new Object[] { task.getTo(), task.getSubject() });
    try
    {
      sendHTMLMail(task.getTo(), task.getSubject(), task.getText());
    } catch (Exception e) {
      this.log.error("send mail fail for ", e);
    }
  }

  private void sendHTMLMail(String to, String subject, String text)
    throws MessagingException
  {
    configJavaMailSender();
    MimeMessage message = this.javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
    helper.setFrom((String)PropertiesUtil.getObject(SysParameterEnum.MAIL_NAME, String.class));
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(text, true);
    this.javaMailSender.send(message);
  }

  private void configJavaMailSender()
  {
    Boolean changed = (Boolean)PropertiesUtil.getObject(SysParameterEnum.MAIL_PROPERTIES_CHANGED, Boolean.class);
    if ((changed == null) || (changed.booleanValue())) {
      this.javaMailSender.setDefaultEncoding("UTF-8");
      this.javaMailSender.setHost((String)PropertiesUtil.getObject(SysParameterEnum.MAIL_HOST, String.class));
      this.javaMailSender.setPort(((Integer)PropertiesUtil.getObject(SysParameterEnum.MAIL_PORT, Integer.class)).intValue());
      String mailname = (String)PropertiesUtil.getObject(SysParameterEnum.MAIL_NAME, String.class);
      this.javaMailSender.setUsername(mailname.substring(0, mailname.indexOf("@")));
      this.javaMailSender.setPassword((String)PropertiesUtil.getObject(SysParameterEnum.MAIL_PASSWORD, String.class));
      Properties properties = new Properties();
      properties.setProperty("mail.smtp.auth", 
        ((Boolean)PropertiesUtil.getObject(SysParameterEnum.MAIL_STMP_AUTH, Boolean.class)).toString());
      properties.setProperty("mail.smtp.timeout", (String)PropertiesUtil.getObject(SysParameterEnum.MAIL_STMP_TIMEOUT, String.class));
      this.javaMailSender.setJavaMailProperties(properties);
      this.log.info("Configuration Mail Sender successful Host: {}, Username {}, mail.smtp.auth: {}", 
        new Object[] { this.javaMailSender.getHost(), this.javaMailSender.getUsername(), 
        this.javaMailSender.getJavaMailProperties().get("mail.smtp.auth") });
      PropertiesUtil.setObject(SysParameterEnum.MAIL_PROPERTIES_CHANGED, Boolean.FALSE);
    }
  }

  public void setJavaMailSender(JavaMailSenderImpl javaMailSender)
  {
    this.javaMailSender = javaMailSender;
  }
}