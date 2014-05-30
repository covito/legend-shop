package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.NewsCategory;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.NewsCategoryService;
import com.legendshop.util.AppUtils;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/newsCategory"})
public class NewsCategoryAdminController extends BaseController
{

  @Autowired
  private NewsCategoryService newsCategoryService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, NewsCategory newsCategory)
  {
    CriteriaQuery cq = new CriteriaQuery(NewsCategory.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    cq = hasAllDataFunction(cq, request, StringUtils.trim(newsCategory.getUserName()));
    if (!(AppUtils.isBlank(newsCategory.getNewsCategoryName()))) {
      cq.like("newsCategoryName", "%" + newsCategory.getNewsCategoryName() + "%");
    }

    cq.eq("status", newsCategory.getStatus());

    PageSupport ps = this.newsCategoryService.getNewsCategoryList(cq);
    ps.savePage(request);
    request.setAttribute("bean", newsCategory);
    return PathResolver.getPath(request, response, BackPage.NEWSCAT_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, NewsCategory newsCategory)
  {
    newsCategory.setNewsCategoryDate(new Date());
    newsCategory.setUserId(UserManager.getUserId(request.getSession()));
    newsCategory.setUserName(UserManager.getUserName(request.getSession()));
    this.newsCategoryService.save(newsCategory);
    saveMessage(request, ResourceBundleHelper.getSucessfulString());
    return PathResolver.getPath(request, response, FowardPage.NEWSCAT_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    this.newsCategoryService.delete(id);
    saveMessage(request, ResourceBundleHelper.getDeleteString());
    return PathResolver.getPath(request, response, FowardPage.NEWSCAT_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    NewsCategory newsCategory = this.newsCategoryService.getNewsCategoryById(id);
    request.setAttribute("bean", newsCategory);
    return PathResolver.getPath(request, response, BackPage.NEWSCAT_EDIT_PAGE);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, BackPage.NEWSCAT_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable NewsCategory newsCategory)
  {
    this.newsCategoryService.update(newsCategory);
    return PathResolver.getPath(request, response, FowardPage.NEWSCAT_LIST_QUERY);
  }
}