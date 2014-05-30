package com.legendshop.business.controller;

import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.SensitiveWord;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.SensitiveWordService;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/system/sensitiveWord"})
public class SensitiveWordController extends BaseController
  implements AdminController<SensitiveWord, Long>
{

  @Autowired
  private SensitiveWordService sensitiveWordService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, SensitiveWord sensitiveWord)
  {
    CriteriaQuery cq = new CriteriaQuery(SensitiveWord.class, curPageNO);

    cq.eq("sortId", sensitiveWord.getSortId());
    cq.eq("nsortId", sensitiveWord.getNsortId());
    cq.eq("subNsortId", sensitiveWord.getSubNsortId());
    cq.eq("words", sensitiveWord.getWords());
    PageSupport ps = this.sensitiveWordService.getSensitiveWord(cq);
    ps.savePage(request);
    request.setAttribute("sensitiveWord", sensitiveWord);

    return PathResolver.getPath(request, response, BackPage.SENSITIVEWORD_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, SensitiveWord sensitiveWord)
  {
    this.sensitiveWordService.saveSensitiveWord(sensitiveWord);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.SENSITIVEWORD_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    SensitiveWord sensitiveWord = this.sensitiveWordService.getSensitiveWord(id);

    this.sensitiveWordService.deleteSensitiveWord(sensitiveWord);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return PathResolver.getPath(request, response, FowardPage.SENSITIVEWORD_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    SensitiveWord sensitiveWord = this.sensitiveWordService.getSensitiveWord(id);

    request.setAttribute("sensitiveWord", sensitiveWord);
    return PathResolver.getPath(request, response, BackPage.SENSITIVEWORD_EDIT_PAGE);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response) {
    return PathResolver.getPath(request, response, BackPage.SENSITIVEWORD_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    SensitiveWord sensitiveWord = this.sensitiveWordService.getSensitiveWord(id);

    request.setAttribute("sensitiveWord", sensitiveWord);
    return PathResolver.getPath(request, response, BackPage.SENSITIVEWORD_EDIT_PAGE);
  }
}