package com.legendshop.business.service.locator;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.locator.GenericServiceLocator;
import com.legendshop.spi.service.SortService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortServiceLocator extends GenericServiceLocator<SortService>
{
  private SortService sortService;

  public SortService getConcreteService(HttpServletRequest request, HttpServletResponse response, PageDefinition page, Sort sort)
  {
    String template = ThreadLocalContext.getFrontType(request, response, page.getNativeValue(), page);
    SortService service = (SortService)this.serviceMap.get(template);
    if (service == null)
      service = (SortService)this.serviceMap.get("default");

    return service;
  }

  public Sort getSort(Long sortId) {
    Sort sort = this.sortService.getSortById(sortId);
    if (sort == null)
      throw new NotFoundException("sort can not be null", "404");

    return sort;
  }

  public void setSortService(SortService sortService)
  {
    this.sortService = sortService;
  }

  public SortService getSortService()
  {
    return this.sortService;
  }
}