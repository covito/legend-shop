package com.legendshop.spi.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.service.SortService;
import com.legendshop.util.AppUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class AbstractSortController extends BaseController
{
  protected final Logger log = LoggerFactory.getLogger(AbstractSortController.class);

  @Autowired(required=false)
  protected SortService sortService;

  public PageSupport querySort(HttpServletRequest request, HttpServletResponse response, String curPageNO, Sort sort)
  {
    CriteriaQuery cq = new CriteriaQuery(Sort.class, curPageNO);
    if (AppUtils.isNotBlank(sort.getSortName()))
      cq.like("sortName", "%" + sort.getSortName() + "%");

    cq.eq("sortType", sort.getSortType());
    if (AppUtils.isNotBlank(sort.getHeaderMenu()))
      cq.eq("headerMenu", sort.getHeaderMenu());

    if (AppUtils.isNotBlank(sort.getNavigationMenu()))
      cq.eq("navigationMenu", sort.getNavigationMenu());

    hasAllDataFunction(cq, request, "userName", sort.getUserName());
    if (!(FoundationUtil.isDataForExport(cq, request)))
      cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());

    if (!(FoundationUtil.isDataSortByExternal(cq, request))) {
      cq.addOrder("asc", "seq");
    }

    return this.sortService.getSortList(cq);
  }

  public void parseSort(HttpServletRequest request, HttpServletResponse response, Sort entity) {
    MultipartFile formFile = entity.getFile();
    String userName = UserManager.getUserName(request);
    String subPath = userName + "/sort/";
    String filename = null;
    try {
      if (entity.getSortId() != null)
      {
        String orginPicture = null;
        Sort origin = this.sortService.getSortById(entity.getSortId());
        if ((formFile != null) && (formFile.getSize() > 5439829638073286656L)) {
          orginPicture = RealPathUtil.getBigPicRealPath() + "/" + origin.getPicture();

          filename = FileProcessor.uploadFileAndCallback(formFile, subPath, "");
          origin.setPicture(filename);
        }
        updateSort(request, response, entity, origin);

        if ((formFile == null) || (formFile.getSize() <= 5439829638073286656L)) return;
        FileProcessor.deleteFile(orginPicture);
        FileProcessor.deleteFile(orginPicture); return;
      }

      entity.setUserId(UserManager.getUserId(request));
      entity.setUserName(userName);
      entity.setStatus(Constants.ONLINE);

      if ((formFile != null) && (formFile.getSize() > 5439830273728446464L)) {
        filename = FileProcessor.uploadFileAndCallback(formFile, subPath, "");
        entity.setPicture(filename);
      }
      saveSort(request, response, entity);
    }
    catch (Exception e)
    {
      if ((formFile != null) && (formFile.getSize() > 5439830273728446464L))
        FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + subPath + filename);

      throw new BusinessException(e, "Save sort error", "998");
    }
  }

  private void updateSort(HttpServletRequest request, HttpServletResponse response, Sort entity, Sort origin)
  {
    origin.setSeq(entity.getSeq());
    origin.setSortName(entity.getSortName());
    origin.setSortType(entity.getSortType());
    origin.setHeaderMenu(entity.getHeaderMenu());
    origin.setNavigationMenu(entity.getNavigationMenu());
    this.sortService.updateSort(origin);
  }

  private void saveSort(HttpServletRequest request, HttpServletResponse response, Sort entity)
  {
    this.sortService.save(entity);
  }

  public void deleteSort(HttpServletRequest request, HttpServletResponse response, Long id) {
    Sort sort = this.sortService.getSortById(id);
    if (sort != null) {
      String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), sort.getUserName());
      if (result != null)
        return;

      this.log.info("{} delete SortName {}", new Object[] { sort.getUserName(), sort.getSortName() });
      this.sortService.delete(sort);
    }
    FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + sort.getPicture());
  }
}