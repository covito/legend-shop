package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.core.exception.ConflictException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.model.entity.Tag;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.TagService;
import com.legendshop.util.constant.TagTypeEnum;
import java.util.Date;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/tag"})
public class TagController extends BaseController
  implements AdminController<Tag, Long>
{

  @Autowired
  private TagService tagService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Tag tag)
  {
    SimpleHqlQuery hql = new SimpleHqlQuery(curPageNO);

    hql.fillLikeParameter("name", tag.getName());
    hql.fillParameter("status", tag.getStatus());
    hql.hasAllDataFunction(request, tag.getUserName());

    hql.fillOrder(request, "order by t.createTime desc");

    hql.fillPageSize(request);
    PageSupport ps = this.tagService.getTag(hql);
    ps.savePage(request);
    request.setAttribute("tag", tag);
    return PathResolver.getPath(request, response, BackPage.TAG_LIST);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Tag tag)
  {
    Tag origin;
    if (tag.getTagId() == null)
    {
      origin = this.tagService.getTag(tag.getName(), UserManager.getUserName(request));
      if (origin != null)
        throw new ConflictException("标签名称'" + tag.getName() + "'已经存在");

      tag.setCreateTime(new Date());
      tag.setType(TagTypeEnum.INDEX.value());
      tag.setUserId(UserManager.getUserId(request));
      tag.setUserName(UserManager.getUserName(request));
      this.tagService.saveTag(tag);
    }
    else {
      origin = this.tagService.getTag(tag.getTagId());
      if (origin == null)
        throw new NotFoundException("没有该标签");

      origin.setName(tag.getName());
      origin.setNewsCategoryId(tag.getNewsCategoryId());
      origin.setSortId(tag.getSortId());
      origin.setStatus(tag.getStatus());
      origin.setCreateTime(new Date());
      this.tagService.updateTag(origin);
    }

    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.TAG_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Tag tag = this.tagService.getTag(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), tag.getUserName());
    if (result != null)
      return result;

    this.tagService.deleteTag(tag);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return PathResolver.getPath(request, response, FowardPage.TAG_QUERY);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    Tag tag = new Tag();
    tag.setUserName(UserManager.getUserName(request));
    request.setAttribute("tag", tag);
    return PathResolver.getPath(request, response, BackPage.TAG);
  }

  @RequestMapping({"/update/{id}"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Tag tag = this.tagService.getTag(id);
    checkNullable("TAG_QUERY", tag);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), tag.getUserName());
    if (result != null)
      return result;

    request.setAttribute("tag", tag);
    return PathResolver.getPath(request, response, BackPage.TAG);
  }
}