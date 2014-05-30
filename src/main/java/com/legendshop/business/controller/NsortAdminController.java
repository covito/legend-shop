package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.ConflictException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.NsortService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin/nsort"})
public class NsortAdminController extends BaseController
  implements AdminController<Nsort, Long>
{
  private final Logger log = LoggerFactory.getLogger(NsortAdminController.class);

  @Autowired
  private NsortService nsortService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Nsort nsort)
  {
    if (AppUtils.isBlank(nsort.getSortId()))
      throw new BusinessException("sort id can not be empty");

    Sort sort = this.nsortService.getSort(nsort.getSortId());
    if (AppUtils.isBlank(sort))
      throw new NotFoundException("Sort is empty");

    HqlQuery hql = new HqlQuery();
    hql.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    hql.setCurPage(curPageNO);
    Map map = new HashMap();
    hql.addParams(sort.getSortId());
    String userName = UserManager.getUserName(request.getSession());
    if (nsort.getParentNsortId() == null)
      map.put("isSecNsort", "and n.parentNsortId is null");

    if (!(CommonServiceUtil.haveViewAllDataFunction(request))) {
      map.put("userName", userName);
      hql.addParams(userName);
    }
    if (!(AppUtils.isBlank(nsort.getNsortName()))) {
      map.put("nsortName", nsort.getNsortName());
      hql.addParams(nsort.getNsortName());
    }
    String QueryNsortCount = ConfigCode.getInstance().getCode("biz.QueryNsortCount", map);
    String QueryNsort = ConfigCode.getInstance().getCode("biz.QueryNsort", map);
    hql.setAllCountString(QueryNsortCount);
    hql.setQueryString(QueryNsort);
    PageSupport ps = this.nsortService.getNsortList(hql);
    List list = ps.getResultList();
    List subNsort = this.nsortService.getNSort3BySort(nsort.getSortId());
    if (!(AppUtils.isBlank(list))) {
      Iterator localIterator1 = list.iterator(); while (localIterator1.hasNext()) { Nsort n = (Nsort)localIterator1.next();
        for (Iterator localIterator2 = subNsort.iterator(); localIterator2.hasNext(); ) { Nsort nsort2 = (Nsort)localIterator2.next();
          n.addSubSort(nsort2);
        }
      }
    }
    ps.savePage(request);
    nsort.setSortName(sort.getSortName());
    request.setAttribute("nsort", nsort);
    return PathResolver.getPath(request, response, BackPage.NSORT_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Nsort nsort)
  {
    String name = UserManager.getUserName(request.getSession());
    Sort sort = this.nsortService.getSort(nsort.getSortId());
    String result = checkPrivilege(request, name, sort.getUserName());
    if (result != null)
      return result;

    this.nsortService.save(nsort);
    saveMessage(request, ResourceBundleHelper.getSucessfulString());
    return PathResolver.getPath(request, response, FowardPage.NSORT_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Nsort nsort = this.nsortService.getNsort(id);
    Sort sort = this.nsortService.getSort(nsort.getSortId());
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), sort.getUserName());
    if (result != null)
      return result;

    this.log.info("{} delete Pub NsortName {}", sort.getUserName(), nsort.getNsortName());
    if (this.nsortService.hasChildNsort(sort.getUserName(), id, nsort.getParentNsortId()))
      throw new ConflictException("发现子商品分类，不能删除该商品分类！");

    if (this.nsortService.hasChildNsortBrand(id)) {
      throw new ConflictException("商品分类下有品牌, 该商品分类不能删除！");
    }

    if (this.nsortService.hasChildProduct(sort.getUserName(), id, nsort.getParentNsortId()))
      throw new ConflictException("商品分类下有产品, 该商品分类不能删除！");

    this.nsortService.delete(id);
    saveMessage(request, ResourceBundleHelper.getDeleteString());
    return PathResolver.getPath(request, response, FowardPage.NSORT_LIST_QUERY) + "?sortId=" + nsort.getSortId();
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    Nsort nsort = new Nsort();
    long sortId = ServletRequestUtils.getLongParameter(request, "sortId", -1L);
    if (sortId != -1L)
      nsort.setSortId(Long.valueOf(sortId));

    long parentNsortId = ServletRequestUtils.getLongParameter(request, "parentNsortId", -1L);
    if (parentNsortId != -1L) {
      nsort.setParentNsortId(Long.valueOf(parentNsortId));
    }

    request.setAttribute("nsort", nsort);

    return PathResolver.getPath(request, response, BackPage.NSORT_EDIT_PAGE);
  }

  @RequestMapping({"/update/{id}"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    checkNullable("nsort Id", id);
    String result = checkLogin(request, response, UserManager.getUserName(request));
    if (result != null)
      return result;

    Nsort nsort = this.nsortService.getNsortById(id);
    if (nsort != null) {
      Sort sort = this.nsortService.getSort(nsort.getSortId());
      request.setAttribute("sort", sort);
      request.setAttribute("nsort", nsort);
    }

    return PathResolver.getPath(request, response, BackPage.NSORT_EDIT_PAGE);
  }

  @RequestMapping({"/appendBrand/{id}"})
  public String appendBrand(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    request.setAttribute("nsortId", id);
    return PathResolver.getPath(request, response, BackPage.NSORT_APPENDBRAND_PAGE);
  }

  @RequestMapping({"/turnon/{nsortId}"})
  public void turnOn(HttpServletRequest request, HttpServletResponse response, @PathVariable Long nsortId) {
    Nsort nsort = this.nsortService.getNsort(nsortId);
    if (nsort != null) {
      Sort sort = this.nsortService.getSort(nsort.getSortId());
      String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), sort.getUserName());
      if (result != null)
        return;

      this.nsortService.turnOn(nsort);
    }
  }

  @RequestMapping({"/turnoff/{nsortId}"})
  public void turnOff(HttpServletRequest request, HttpServletResponse response, @PathVariable Long nsortId)
  {
    Nsort nsort = this.nsortService.getNsort(nsortId);
    if (nsort != null) {
      Sort sort = this.nsortService.getSort(nsort.getSortId());
      String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), sort.getUserName());
      if (result != null)
        return;

      this.nsortService.turnOff(nsort);
    }
  }

  @RequestMapping(value={"/updatestatus/{nsortId}/{status}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Integer updateStatus(HttpServletRequest request, HttpServletResponse response, @PathVariable Long nsortId, @PathVariable Integer status)
  {
    Nsort nsort = this.nsortService.getNsort(nsortId);
    if (nsort == null)
      return Integer.valueOf(-1);

    Sort sort = this.nsortService.getSort(nsort.getSortId());
    if (!(status.equals(nsort.getStatus())))
      if (!(FoundationUtil.haveViewAllDataFunction(request))) {
        String loginName = UserManager.getUserName(request.getSession());

        if (!(loginName.equals(sort.getUserName())))
          return Integer.valueOf(-1);

        if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status))) {
          nsort.setStatus(status);
          this.nsortService.update(nsort);
        }

      }
      else if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status)) || (Constants.STOPUSE.equals(status))) {
        sort.setStatus(status);
        this.nsortService.update(nsort);
      }


    return nsort.getStatus();
  }
}