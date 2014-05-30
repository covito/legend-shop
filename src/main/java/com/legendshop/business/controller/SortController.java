package com.legendshop.business.controller;

import com.legendshop.business.service.locator.SortServiceLocator;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.ProdSearchArgs;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.SortService;
import com.legendshop.util.constant.ProductTypeEnum;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SortController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(SortController.class);

  @Autowired
  private SortServiceLocator sortServiceLocator;

  @RequestMapping({"/topsort"})
  public String topsort(HttpServletRequest request, HttpServletResponse response)
  {
    request.setAttribute("sortList", 
      this.sortServiceLocator.getSortService().getSort(ThreadLocalContext.getCurrentShopName(request, response), Boolean.valueOf(true)));
    return PathResolver.getPath(request, response, FrontPage.TOP_SORT);
  }

  @RequestMapping({"/nsort/{sortId}-{nsortId}"})
  public String nsort(HttpServletRequest request, HttpServletResponse response, @PathVariable Long sortId, @PathVariable Long nsortId)
  {
    if ((nsortId == null) || (sortId == null)) {
      this.log.error("sortId or nsortId is null! ");
      return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);
    }
    Sort sort = this.sortServiceLocator.getSort(sortId);
    return ((SortService)this.sortServiceLocator.getConcreteService(request, response, TilesPage.NSORT)).getSecSort(request, response, sort, 
      nsortId, null);
  }

  @RequestMapping({"/snsort/{sortId}-{nsortId}-{subnsortId}"})
  public String snsort(HttpServletRequest request, HttpServletResponse response, @PathVariable Long sortId, @PathVariable Long nsortId, @PathVariable Long subnsortId)
  {
    if ((nsortId == null) || (sortId == null)) {
      this.log.error("sortId or nsortId is null! ");
      return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);
    }
    Sort sort = this.sortServiceLocator.getSort(sortId);
    return ((SortService)this.sortServiceLocator.getConcreteService(request, response, TilesPage.NSORT)).getSecSort(request, response, sort, 
      nsortId, subnsortId);
  }

  @RequestMapping({"/nsortlist/{sortId}-{nsortId}"})
  public String nsortList(HttpServletRequest request, HttpServletResponse response, String curPageNO, @PathVariable Long sortId, @PathVariable Long nsortId, ProdSearchArgs args)
  {
    Sort sort = this.sortServiceLocator.getSort(sortId);
    return this.sortServiceLocator.getConcreteService(request, response, TilesPage.NSORT, sort).parseSort(request, response, 
      curPageNO, sortId, args);
  }

  @RequestMapping({"/nsort"})
  public String nsort(HttpServletRequest request, HttpServletResponse response, Long sortId, Long nsortId, Long subNsortId)
  {
    if ((nsortId == null) || (sortId == null)) {
      this.log.error("sortId or nsortId is null! ");
      return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);
    }
    Sort sort = this.sortServiceLocator.getSort(sortId);
    return ((SortService)this.sortServiceLocator.getConcreteService(request, response, TilesPage.NSORT)).getSecSort(request, response, sort, 
      nsortId, subNsortId);
  }

  @RequestMapping({"/sort/{sortId}"})
  public String sort(HttpServletRequest request, HttpServletResponse response, String curPageNO, @PathVariable Long sortId, String order, String seq)
  {
    if (sortId == null)
      return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);
    try
    {
      Sort sort = this.sortServiceLocator.getSort(sortId);
      return this.sortServiceLocator.getConcreteService(request, response, TilesPage.PRODUCT_SORT, sort).parseSort(request, 
        response, curPageNO, sort);
    } catch (Exception e) {
      this.log.error("query sort error", e); }
    return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);
  }

  @RequestMapping({"/sortlist/{sortId}"})
  public String sortList(HttpServletRequest request, HttpServletResponse response, String curPageNO, @PathVariable Long sortId, String order, String seq)
  {
    Sort sort = this.sortServiceLocator.getSort(sortId);
    return this.sortServiceLocator.getConcreteService(request, response, TilesPage.PRODUCT_SORT, sort).sortList(request, response, 
      sort);
  }

  @RequestMapping({"/prodsortlist/{sortId}"})
  public String prodSortList(HttpServletRequest request, HttpServletResponse response, String curPageNO, @PathVariable Long sortId, ProdSearchArgs args)
  {
    Sort sort = this.sortServiceLocator.getSort(sortId);
    return this.sortServiceLocator.getConcreteService(request, response, TilesPage.PRODUCT_SORT, sort).parseSort(request, response, 
      curPageNO, sortId, args);
  }

  @RequestMapping({"/prodnsortlist/{sortId}-{nsortId}-{subnsortId}"})
  public String prodNSortList(HttpServletRequest request, HttpServletResponse response, String curPageNO, @PathVariable Long sortId, @PathVariable Long nsortId, @PathVariable Long subnsortId, ProdSearchArgs args)
  {
    Sort sort = this.sortServiceLocator.getSort(sortId);
    return this.sortServiceLocator.getConcreteService(request, response, TilesPage.NSORT, sort).parseSecSort(request, response, 
      curPageNO, sortId, nsortId, subnsortId, args);
  }

  @RequestMapping({"/sort"})
  public String sortById(HttpServletRequest request, HttpServletResponse response, String curPageNO, Long sortId, String order, String seq)
  {
    if (sortId == null)
      return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);

    Sort sort = this.sortServiceLocator.getSort(sortId);
    return this.sortServiceLocator.getConcreteService(request, response, TilesPage.PRODUCT_SORT, sort).parseSort(request, response, 
      curPageNO, sort);
  }

  @RequestMapping({"/allsorts"})
  public String allSorts(HttpServletRequest request, HttpServletResponse response, String curPageNO, Long sortId)
  {
    List sortList = this.sortServiceLocator.getSortService().getSort(ThreadLocalContext.getCurrentShopName(request, response), 
      Boolean.valueOf(true));
    request.setAttribute("sortList", sortList);
    request.setAttribute("halfSize", Integer.valueOf(sortList.size() / 2));
    return PathResolver.getPath(request, response, TilesPage.ALL_SORTS);
  }

  @RequestMapping({"/sort/loadSorts/{sortId}"})
  @ResponseBody
  public List<KeyValueEntity> loadSorts(HttpServletRequest request, HttpServletResponse response, @PathVariable Long sortId)
  {
    SortService sortService = this.sortServiceLocator.getSortService();
    Sort sort = sortService.getSortById(sortId);
    if (sort == null)
      throw new NotFoundException("Can not found product category");

    return sortService.loadSorts(sort.getUserName(), ProductTypeEnum.PRODUCT.value());
  }

  @RequestMapping({"/sort/loadGroupSorts/{sortId}"})
  @ResponseBody
  public List<KeyValueEntity> loadGroupSorts(HttpServletRequest request, HttpServletResponse response, @PathVariable Long sortId)
  {
    SortService sortService = this.sortServiceLocator.getSortService();
    Sort sort = sortService.getSortById(sortId);
    if (sort == null)
      throw new NotFoundException("Can not found product category");

    return sortService.loadSorts(sort.getUserName(), ProductTypeEnum.GROUP.value());
  }

  @RequestMapping({"/sort/loadSorts"})
  @ResponseBody
  public List<KeyValueEntity> loadSorts(HttpServletRequest request, HttpServletResponse response)
  {
    String userNmae = UserManager.getUserName(request);
    SortService sortService = this.sortServiceLocator.getSortService();
    return sortService.loadSorts(userNmae, ProductTypeEnum.PRODUCT.value());
  }

  @RequestMapping({"/sort/loadGroupSorts"})
  @ResponseBody
  public List<KeyValueEntity> loadGroupSorts(HttpServletRequest request, HttpServletResponse response)
  {
    String userNmae = UserManager.getUserName(request);
    SortService sortService = this.sortServiceLocator.getSortService();
    return sortService.loadSorts(userNmae, ProductTypeEnum.GROUP.value());
  }

  @RequestMapping({"/sort/loadNSorts/{sortId}"})
  @ResponseBody
  public List<KeyValueEntity> loadNSorts(HttpServletRequest request, HttpServletResponse response, @PathVariable Long sortId)
  {
    return this.sortServiceLocator.getSortService().loadNSorts(sortId);
  }

  @RequestMapping({"/sort/loadSubNSorts/{nsortId}"})
  @ResponseBody
  public List<KeyValueEntity> loadSubNSorts(HttpServletRequest request, HttpServletResponse response, @PathVariable Long nsortId)
  {
    return this.sortServiceLocator.getSortService().loadSubNSorts(nsortId);
  }

  public void setSortServiceLocator(SortServiceLocator sortServiceLocator)
  {
    this.sortServiceLocator = sortServiceLocator;
  }
}