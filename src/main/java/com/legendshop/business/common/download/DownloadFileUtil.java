package com.legendshop.business.common.download;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class DownloadFileUtil
{
  private static DownloadFileUtil instance;
  private static Logger logger = Logger.getLogger(DownloadFileUtil.class);

  public static DownloadFileUtil getInstance()
  {
    if (instance == null)
      instance = new DownloadFileUtil();

    return instance;
  }

  public void downloadFile(HttpServletResponse response, String filePath, String fileName, boolean isDownload, DownLoadCallBack callBack, Object entity)
  {
    downloadFile(response, filePath, fileName, isDownload);
    callBack.afterDownload(entity);
  }

  public void downloadFile(HttpServletResponse response, String filePath, String fileName, boolean isDownload)
  {
    File file;
    try
    {
      int i;
      file = new File(filePath);
      if ((!(file.exists())) || (!(file.isFile()))) {
        logger.debug("File: " + filePath + " Not Exists");
        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        ServletOutputStream os = response.getOutputStream();
        os.println("文件不存在！联系管理员！"); return;
      }
      if (isDownload) {
        response.setHeader("Content-Type", "application/x-download; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentLength((int)file.length());
      } else {
        String contentType = "application/pdf";
        if ((fileName != null) && (fileName.endsWith(".doc"))) {
          contentType = "application/msword";
          response.setHeader("Content-Type", contentType);
        } else if ((fileName != null) && (fileName.endsWith(".pdf"))) {
          contentType = "application/pdf";
          response.setHeader("Content-Type", contentType);
        } else {
          contentType = "application/force-download";
          response.setHeader("Content-Type", contentType);
        }
        response.setHeader("Content-Disposition", "filename=" + URLEncoder.encode(fileName, "UTF-8"));
      }
      FileInputStream fis = new FileInputStream(filePath);
      byte[] data = new byte[8192];
      ServletOutputStream os = response.getOutputStream();

      while ((i = fis.read(data, 0, 8192)) != -1) {
        os.write(data, 0, i);
      }

      os.flush();
      fis.close();
      os.close();

      logger.debug("Download File: " + filePath + " Finished");
    }
    catch (Exception e) {
      logger.error("DownloadFile: " + filePath + " Reason: " + e.toString());
    }
  }
}