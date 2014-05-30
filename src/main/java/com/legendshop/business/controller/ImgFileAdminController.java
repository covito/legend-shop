package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.Product;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.ImgFileService;
import com.legendshop.util.AppUtils;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/admin/imgFile"})
public class ImgFileAdminController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(ImgFileAdminController.class);

  @Autowired
  private ImgFileService imgFileService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, ImgFile imgFile)
  {
    Long productId = (Long)request.getAttribute("productId");
    if (productId == null) {
      String productIdStr = request.getParameter("productId");
      if (AppUtils.isNotBlank(productIdStr))
        productId = Long.valueOf(productIdStr);
    }
    if (productId == null)
      throw new NotFoundException("miss productId", "405");

    CriteriaQuery cq = new CriteriaQuery(ImgFile.class, curPageNO, "javascript:imgPager");
    cq.setPageSize(6);
    cq = hasAllDataFunction(cq, request, StringUtils.trim(imgFile.getUserName()));
    if (productId != null) {
      cq.eq("productId", productId);
      imgFile.setProductId(productId);
      Product product = this.imgFileService.getProd(productId);
      request.setAttribute("prod", product);
    }

    PageSupport ps = this.imgFileService.getImgFileList(cq);
    ps.savePage(request);
    return PathResolver.getPath(request, response, BackPage.IMG_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, ImgFile imgFile)
  {
    if (imgFile.getFile() != null) {
      String name = UserManager.getUserName(request.getSession());
      String subPath = name + "/imgFile/";
      String filePath = FileProcessor.uploadFileAndCallback(imgFile.getFile(), subPath, "");
      imgFile.setFilePath(filePath);
      imgFile.setFileSize(new Integer((int)imgFile.getFile().getSize()));
      imgFile.setFileType(filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase());
      imgFile.setProductType(Short.valueOf("1"));
      imgFile.setStatus(Constants.ONLINE);
      imgFile.setUpoadTime(new Date());
      imgFile.setUserName(name);
      this.imgFileService.save(imgFile);
      saveMessage(request, ResourceBundleHelper.getSucessfulString());
    }
    request.setAttribute("productId", imgFile.getProductId());
    return PathResolver.getPath(request, response, FowardPage.IMG_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    ImgFile imgFile = this.imgFileService.getImgFileById(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), imgFile.getUserName());
    if (result != null)
      return result;

    this.log.info("{}, delete ImgFile filePath {}", imgFile.getUserName(), imgFile.getFilePath());
    this.imgFileService.delete(id);
    String url = RealPathUtil.getBigPicRealPath() + "/" + imgFile.getFilePath();
    FileProcessor.deleteFile(url);
    request.setAttribute("productId", imgFile.getProductId());
    saveMessage(request, ResourceBundleHelper.getSucessfulString());
    return PathResolver.getPath(request, response, FowardPage.IMG_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    ImgFile imgFile = this.imgFileService.getImgFileById(id);
    request.setAttribute("bean", imgFile);
    return PathResolver.getPath(request, response, BackPage.IMG_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable ImgFile imgFile)
  {
    this.imgFileService.update(imgFile);
    return PathResolver.getPath(request, response, FowardPage.IMG_LIST_QUERY);
  }

  @RequestMapping(value={"/updatestatus/{fileId}/{status}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Integer updateStatus(HttpServletRequest request, HttpServletResponse response, @PathVariable Long fileId, @PathVariable Integer status)
  {
    ImgFile imgFile = this.imgFileService.getImgFileById(fileId);
    if (imgFile == null)
      return Integer.valueOf(-1);

    if (!(status.equals(imgFile.getStatus())))
      if (!(FoundationUtil.haveViewAllDataFunction(request))) {
        String loginName = UserManager.getUserName(request.getSession());

        if (!(loginName.equals(imgFile.getUserName())))
          return Integer.valueOf(-1);

        if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status))) {
          imgFile.setStatus(status);
          this.imgFileService.update(imgFile);
        }

      }
      else if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status)) || (Constants.STOPUSE.equals(status))) {
        imgFile.setStatus(status);
        this.imgFileService.update(imgFile);
      }


    return imgFile.getStatus();
  }
}