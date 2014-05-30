package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.util.AppUtils;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class LocaleController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(LocaleController.class);
  private final String LANGUAGE = "language";
  private final String COUNTRY = "country";
  private final String PAGE = "page";

  @Autowired
  private LocaleResolver localeResolver;

  @RequestMapping({"/changeLocale"})
  public String changeLocale(HttpServletRequest request, HttpServletResponse response)
  {
    String language = request.getParameter("language");
    String country = request.getParameter("country");
    Locale locale = null;
    this.log.debug("language = {}, country = {}", language, country);
    if ((AppUtils.isNotBlank(language)) && (AppUtils.isNotBlank(country)))
      locale = new Locale(language, country);
    else if (AppUtils.isNotBlank(language))
      locale = new Locale(language, "");

    if (locale != null)
      this.localeResolver.setLocale(request, response, locale);

    String target = request.getParameter("page");
    if (AppUtils.isNotBlank(target))
      return PathResolver.getPath(request, response, target, BackPage.VARIABLE);

    return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);
  }
}