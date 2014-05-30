package com.legendshop.business.common.download;

import com.legendshop.command.framework.State;
import com.legendshop.command.framework.StateImpl;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.FunctionEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.DownloadLog;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ServiceLocatorIF;
import com.legendshop.util.ip.IPSeeker;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadFileServlet extends HttpServlet
{
  private static final long serialVersionUID = 6658159370029368321L;
  protected Logger log = LoggerFactory.getLogger(DownloadFileServlet.class);
  String filePath = PropertiesUtil.getDownloadFilePath();

  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    String ipAttr = request.getRemoteAddr();
    response.setContentType("text/html; charset=UTF-8");

    String filename = request.getPathInfo();
    if (!(AppUtils.isBlank(filename))) {
      filename = filename.substring(1);
      State state = checkFunction(request, filename);
      if (!(state.isOK())) {
        errorFound(response, state.getErrCode());
        this.log.error("{} attempt to download file {}, but without function", ipAttr, filename);
        return;
      }

      String fullName = this.filePath + "/" + filename;
      File file = new File(fullName);
      if (file.isFile())
      {
        if (getDownloadLogService().checkCanDownload(ipAttr, filename)) {
          errorFound(response, "超过下载次数");
        } else {
          this.log.info("{} download file {}", ipAttr, filename);
          String name = filename;
          if (filename.lastIndexOf("/") > -1)
            name = filename.substring(filename.lastIndexOf("/") + 1);

          DownloadLog downloadLog = new DownloadLog();
          downloadLog.setDate(new Date());
          downloadLog.setFileName(filename);
          downloadLog.setIp(ipAttr);
          downloadLog.setUserName(UserManager.getUserName(request.getSession()));
          downloadLog.setArea(IPSeeker.getInstance().getArea(downloadLog.getIp()));
          downloadLog.setCountry(IPSeeker.getInstance().getCountry(downloadLog.getIp()));
          downloadLog.setShopName(ThreadLocalContext.getCurrentShopName(request, response));

          DownloadFileUtil.getInstance().downloadFile(response, fullName, name, true, getDownloadLogService(), 
            downloadLog);
        }
      }
      else
        errorFound(response, "Could not get file name");
    }
    else {
      errorFound(response, "Could not get file name");
    }
  }

  private State checkFunction(HttpServletRequest request, String pathInfo)
  {
    State state = new StateImpl();
    String info = pathInfo;
    int pos = pathInfo.lastIndexOf("/");
    if (pos > -1)
      info = pathInfo.substring(0, pos);

    if (!(AppUtils.isBlank(info))) {
      if ((info.indexOf("secured") > -1) && 
        (!(UserManager.hasFunction(request.getSession(), FunctionEnum.FUNCTION_SECURED.value())))) {
        state.setErrCode("你还没有获得访问该目录的权限，请与管理员联系");
      }

      if ((info.indexOf("securest") > -1) && 
        (!(UserManager.hasFunction(request.getSession(), FunctionEnum.FUNCTION_SECUREST.value())))) {
        state.setErrCode("你还没有获得访问该保密目录的权限，请与管理员联系");
      }

      if (info.indexOf("order") > -1) {
        String userName = UserManager.getUserName(request);
        if (AppUtils.isBlank(userName)) {
          state.setErrCode("你还没有登录系，不能下载");
        }

        String prodId = info.substring(info.indexOf("order/") + 6, info.length());
        if (prodId.indexOf("/") > -1)
          prodId = prodId.substring(0, prodId.indexOf("/"));

        if (!(getDownloadLogService().isUserOrderProduct(Long.valueOf(prodId), userName)))
          state.setErrCode("你还没有购买该商品，不能下载");
      }
    }

    return state;
  }

  private void errorFound(HttpServletResponse response, String cause)
  {
    PrintWriter printwriter = null;
    try {
      response.setContentType("text/html");
      printwriter = response.getWriter();
      printwriter.println("<html>");
      printwriter.println("<br><br> " + cause);
      printwriter.println("</html>");
    }
    catch (Exception localException) {
      if (printwriter == null) return;
      printwriter.flush();
      printwriter.close();
      try {
        response.flushBuffer();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    finally
    {
      if (printwriter != null) {
        printwriter.flush();
        printwriter.close();
        try {
          response.flushBuffer();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    doGet(req, resp);
  }

  public DownLoadCallBack getDownloadLogService()
  {
    return ((DownLoadCallBack)ContextServiceLocator.getInstance().getBean("downloadLogService"));
  }
}