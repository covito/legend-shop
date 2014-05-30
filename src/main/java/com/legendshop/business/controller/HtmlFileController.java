package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.LegendFilter;
import com.legendshop.core.helper.RealPathUtil;
import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin/file"})
public class HtmlFileController extends BaseController
{
  @RequestMapping({"/deleteHtmlFile"})
  @ResponseBody
  public Integer deleteHtmlFile(String filePath)
  {
    String realPath = LegendFilter.HTML_PATH;
    FileProcessor.deleteDirectory(new File(realPath + filePath));
    return Integer.valueOf(0); }

  @RequestMapping({"/deleteFile"})
  @ResponseBody
  public Integer deleteFile(String filePath) {
    String realPath = RealPathUtil.getFCKRealPath(filePath);
    int result = FileProcessor.deleteFile(realPath);
    return Integer.valueOf(result);
  }
}