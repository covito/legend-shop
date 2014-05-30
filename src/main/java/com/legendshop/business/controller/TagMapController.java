package com.legendshop.business.controller;

import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.TagMap;
import com.legendshop.spi.service.TagMapService;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/tagMap"})
public class TagMapController extends BaseController
  implements AdminController<TagMap, Long>
{

  @Autowired
  private TagMapService tagMapService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, TagMap tagMap)
  {
    CriteriaQuery cq = new CriteriaQuery(TagMap.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());

    PageSupport ps = this.tagMapService.getTagMap(cq);
    ps.savePage(request);
    request.setAttribute("tagMap", tagMap);
    return "/tagMap/tagMapList";
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, TagMap tagMap)
  {
    this.tagMapService.saveTagMap(tagMap);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return "forward:/admin/tagMap/query.htm";
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    TagMap tagMap = this.tagMapService.getTagMap(id);

    this.tagMapService.deleteTagMap(tagMap);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return "forward:/admin/tagMap/query.htm";
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    TagMap tagMap = this.tagMapService.getTagMap(id);

    request.setAttribute("#entityClassInstance", tagMap);
    return "/tagMap/tagMap";
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return "/tagMap/tagMap";
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    TagMap tagMap = this.tagMapService.getTagMap(id);

    request.setAttribute("tagMap", tagMap);
    return "forward:/admin/tagMap/query.htm";
  }
}