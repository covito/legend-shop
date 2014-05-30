package com.legendshop.business.helper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageGengrator
{
  protected Logger log = LoggerFactory.getLogger(PageGengrator.class);
  private static PageGengrator instance = null;
  private Configuration freemarkerCfg = null;

  public static PageGengrator getInstance()
  {
    if (instance == null)
      instance = new PageGengrator();

    return instance;
  }

  public void crateHTML(ServletContext context, Map<String, Object> data, String templatePath, String targetHtmlPath)
  {
    Template template;
    try
    {
      template = getConfiguration(context).getTemplate(templatePath, "UTF-8");
      template.setEncoding("UTF-8");

      String htmlPath = context.getRealPath("/html") + "/" + targetHtmlPath;
      File htmlFile = new File(htmlPath);
      Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));

      template.process(data, out);
      out.flush();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Configuration getConfiguration(ServletContext context)
  {
    if (this.freemarkerCfg == null) {
      this.freemarkerCfg = new Configuration();
      this.freemarkerCfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/ftl/default");
      this.freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
      this.freemarkerCfg.setTemplateUpdateDelay(3600);
    }
    return this.freemarkerCfg;
  }

  public String crateHTML(ServletContext context, String filePath, Map<String, Object> map, Locale locale)
  {
    Configuration configuration;
    try
    {
      configuration = getConfiguration(context);
      Template template = configuration.getTemplate(filePath, locale);
      if (template == null)
        return "";

      template.setEncoding("UTF-8");
      StringWriter writer = new StringWriter();
      template.process(map, writer);
      return writer.toString();
    } catch (Exception e) {
      this.log.error("crateHTML error", e); }
    return "";
  }
}