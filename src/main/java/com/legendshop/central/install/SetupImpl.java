package com.legendshop.central.install;

import com.legendshop.core.StartupService;
import com.legendshop.core.constant.ConfigPropertiesEnum;
import com.legendshop.core.datasource.RefreshableDataSource;
import com.legendshop.core.exception.InternalException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ServiceLocatorIF;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetupImpl
  implements Setup
{
  private static Logger _$8 = LoggerFactory.getLogger(SetupImpl.class);
  private DBManager _$7 = null;
  private final String _$6 = "com.mysql.jdbc.Driver";
  private String _$5;
  private String _$4;
  private String _$3;
  private String _$2;
  private String _$1;

  public String getDomainName()
  {
    return this._$2;
  }

  public void setDomainName(String paramString)
  {
    this._$2 = paramString;
  }

  private SetupImpl()
  {
  }

  public SetupImpl(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    this._$4 = paramString1;
    this._$3 = paramString2;
    this._$2 = paramString6;
    this._$1 = paramString7;
    this._$5 = "jdbc:mysql://" + paramString4 + ":" + paramString5 + "/" + paramString3 + "?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8";
    this._$7 = new DBManager("com.mysql.jdbc.Driver", this._$5, this._$4, this._$3);
  }

  public void startSetup(HttpServletRequest paramHttpServletRequest)
  {
    if (PropertiesUtil.isSystemInstalled())
      throw new InternalException("LegendShop 已经安装,请勿重复安装");
    String str1 = StringUtils.replace(paramHttpServletRequest.getSession().getServletContext().getRealPath("/"), "\\", "/");
    System.out.println("realPath = " + str1);
    _$8.info("realPath = {}", str1);
    if ((str1 != null) && (!(str1.endsWith("/"))))
    {
      _$8.info("realPath append /");
      str1 = str1 + "/";
    }
    String str2 = PropertiesUtil.getProperties("config/global.properties", ConfigPropertiesEnum.LEGENDSHOP_VERSION.name());
    paramHttpServletRequest.getSession().getServletContext().setAttribute("DOMAIN_NAME", this._$2);
    if ("1".equals(this._$1))
      try
      {
        _$8.info("数据导入中，请勿关闭服务器，LegendShop DB Version：" + str2);
        String str3 = str1 + "plugins/install/db/legendshop-struct" + str2 + ".sql";
        String str4 = readFile(str3);
        String[] arrayOfString1 = str4.split(";");
        if (!(AppUtils.isBlank(str4)))
          this._$7.batchUpdate(arrayOfString1);
        str3 = str1 + "plugins/install/db/legendshop-data" + str2 + ".sql";
        String[] arrayOfString2 = readFilePerLine(str3);
        this._$7.batchUpdate(arrayOfString2);
      }
      catch (Exception localException1)
      {
        throw new DBException();
      }
    _$8.info("LegendShop " + str2 + " 跳过数据库，直接安装系统。installDb = " + this._$1);
    try
    {
      changePropertiesFileV3(str1, paramHttpServletRequest);
    }
    catch (Exception localException2)
    {
      throw new PropertiesException("changePropertiesFileV3 error");
    }
    try
    {
      RefreshableDataSource localRefreshableDataSource = (RefreshableDataSource)ContextServiceLocator.getInstance().getBean("dataSource");
      if (localRefreshableDataSource != null)
        localRefreshableDataSource.setDataSource(_$1());
    }
    catch (Exception localException3)
    {
      _$8.error("addDataSource in startup error", localException3);
    }
    StartupService localStartupService = (StartupService)ContextServiceLocator.getInstance().getBean("startupService");
    if (localStartupService != null)
      localStartupService.startup(paramHttpServletRequest.getSession().getServletContext());
    _$8.info("LegendShop " + str2 + " 已经安装成功。");
  }

  private DataSource _$1()
  {
    ComboPooledDataSource localComboPooledDataSource = null;
    try
    {
      localComboPooledDataSource = new ComboPooledDataSource();
      localComboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
      localComboPooledDataSource.setJdbcUrl(this._$5);
      localComboPooledDataSource.setUser(this._$4);
      localComboPooledDataSource.setPassword(this._$3);
    }
    catch (PropertyVetoException localPropertyVetoException)
    {
      localPropertyVetoException.printStackTrace();
    }
    return localComboPooledDataSource;
  }

  public boolean changePropertiesFileV3(String paramString, HttpServletRequest paramHttpServletRequest)
  {
    _$3(paramString);
    _$2(paramString);
    return true;
  }

  private void _$4(String paramString)
  {
    String str = paramString + "WEB-INF/classes/spring/jdbc.properties";
    HashMap localHashMap = new HashMap();
    localHashMap.put("jdbc.url", this._$5);
    localHashMap.put("jdbc.username", this._$4);
    localHashMap.put("jdbc.password", this._$3);
    localHashMap.put("alias", this._$4);
    localHashMap.put("prototypeCount", "20");
    localHashMap.put("minimumConnectionCount", "10");
    PropertiesUtil.writeProperties(str, localHashMap);
  }

  private void _$3(String paramString)
  {
    String str = paramString + "WEB-INF/classes/spring/jdbc.properties";
    HashMap localHashMap = new HashMap();
    localHashMap.put("jdbc.url", this._$5);
    localHashMap.put("jdbc.username", this._$4);
    localHashMap.put("jdbc.password", this._$3);
    localHashMap.put("jdbc.acquireIncrement", "3");
    localHashMap.put("jdbc.maxIdleTime", "60");
    localHashMap.put("jdbc.minPoolSize", "10");
    localHashMap.put("jdbc.maxPoolSize", "200");
    localHashMap.put("jdbc.initialPoolSize", "10");
    PropertiesUtil.writeProperties(str, localHashMap);
  }

  private void _$2(String paramString)
  {
    String str = paramString + "WEB-INF/classes/config/common.properties";
    HashMap localHashMap = new HashMap();
    localHashMap.put("DOMAIN_NAME", this._$2);
    localHashMap.put(ConfigPropertiesEnum.INSTALLED.name(), ConfigPropertiesEnum.INSTALLED.name());
    localHashMap.put(ConfigPropertiesEnum.DOWNLOAD_PATH.name(), PropertiesUtil.getDownloadFilePath());
    localHashMap.put(ConfigPropertiesEnum.SMALL_PIC_PATH.name(), PropertiesUtil.getSmallFilesAbsolutePath());
    localHashMap.put(ConfigPropertiesEnum.BIG_PIC_PATH.name(), PropertiesUtil.getBigFilesAbsolutePath());
    localHashMap.put(ConfigPropertiesEnum.BACKUP_PIC_PATH.name(), PropertiesUtil.getBackupFilesAbsolutePath());
    localHashMap.put(ConfigPropertiesEnum.LUCENE_PATH.name(), PropertiesUtil.getLucenePath());
    PropertiesUtil.writeProperties(str, localHashMap);
  }

  public boolean changePropertiesFile(String paramString, HttpServletRequest paramHttpServletRequest)
  {
    String str1 = paramHttpServletRequest.getContextPath();
    String str2 = paramString + "WEB-INF/classes/config/jdbc.properties";
    HashMap localHashMap = new HashMap();
    localHashMap.put("jdbc.url", this._$5);
    localHashMap.put("jdbc.username", this._$4);
    localHashMap.put("jdbc.password", this._$3);
    localHashMap.put("alias", this._$4);
    PropertiesUtil.writeProperties(str2, localHashMap);
    str2 = paramString + "WEB-INF/classes/config/acegi-cas.properties";
    localHashMap = new HashMap();
    localHashMap.put("cas.server.url", this._$2 + "cas");
    if (AppUtils.isBlank(str1))
      localHashMap.put("cas.service.url", this._$2);
    else
      localHashMap.put("cas.service.url", this._$2 + str1.substring(1));
    PropertiesUtil.writeProperties(str2, localHashMap);
    str2 = paramString + "WEB-INF/classes/config/fckeditor.properties";
    localHashMap = new HashMap();
    localHashMap.put(ConfigPropertiesEnum.DOWNLOAD_PATH.name(), paramString + "WEB-INF/download");
    localHashMap.put("connector.smallFilesAbsolutePath", _$1(paramString, str1, "photoserver/smallImage"));
    PropertiesUtil.writeProperties(str2, localHashMap);
    str2 = paramString + "WEB-INF/classes/fckeditor.properties";
    localHashMap = new HashMap();
    String str3 = "connector.userFilesAbsolutePath";
    localHashMap.put(str3, _$1(paramString, str1, "photoserver/bigImage"));
    PropertiesUtil.writeProperties(str2, localHashMap);
    str2 = _$1(paramString, str1, "cas/") + "WEB-INF/classes/config/common.properties";
    localHashMap = new HashMap();
    int i = paramHttpServletRequest.getServerPort();
    if (i == 80)
      localHashMap.put("cas.server.url", "http://" + paramHttpServletRequest.getServerName() + "/cas");
    else
      localHashMap.put("cas.server.url", "http://" + paramHttpServletRequest.getServerName() + ":" + paramHttpServletRequest.getServerPort() + "/cas");
    PropertiesUtil.writeProperties(str2, localHashMap);
    str2 = _$1(paramString, str1, "cas/") + "WEB-INF/cas.properties";
    localHashMap = new HashMap();
    localHashMap.put("jdbc.url", this._$5);
    localHashMap.put("jdbc.username", this._$4);
    localHashMap.put("jdbc.password", this._$3);
    localHashMap.put("alias", this._$4);
    PropertiesUtil.writeProperties(str2, localHashMap);
    String str4 = _$1(paramString, str1, "managerWeb/");
    str2 = str4 + "WEB-INF/classes/config.properties";
    localHashMap = new HashMap();
    localHashMap.put("upLoadPath", str4 + "dowonload");
    PropertiesUtil.writeProperties(str2, localHashMap);
    str2 = str4 + "WEB-INF/classes/config/jdbc.properties";
    localHashMap = new HashMap();
    localHashMap.put("jdbc.url", this._$5);
    localHashMap.put("jdbc.username", this._$4);
    localHashMap.put("jdbc.password", this._$3);
    localHashMap.put("alias", this._$4);
    PropertiesUtil.writeProperties(str2, localHashMap);
    str2 = str4 + "WEB-INF/classes/config/acegi-cas.properties";
    localHashMap = new HashMap();
    localHashMap.put("cas.server.url", this._$2 + "cas");
    localHashMap.put("cas.service.url", this._$2 + "managerWeb");
    PropertiesUtil.writeProperties(str2, localHashMap);
    return true;
  }

  private String _$1(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null)
      return null;
    String str = _$1(paramString1, paramString2) + paramString3;
    File localFile = new File(str);
    if (!(localFile.exists()))
      localFile.mkdirs();
    return str;
  }

  private String _$1(String paramString1, String paramString2)
  {
    if (AppUtils.isBlank(paramString2))
      paramString2 = "/ROOT";
    String str = paramString1.substring(0, paramString1.length() - paramString2.length());
    return str;
  }

  public String readFile(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      File localFile = new File(paramString);
      if (!(localFile.exists()))
        throw new NotFoundException(paramString + " 文件不存在", "405");
      FileInputStream localFileInputStream = new FileInputStream(localFile);
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localFileInputStream, "UTF-8"));
      String str = "";
      while (true)
      {
        do
          if ((str = localBufferedReader.readLine()) == null){
        	  
          }
        while (!(_$1(str)));
        localStringBuffer.append(str);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return localStringBuffer.toString();
  }

  public String[] readFilePerLine(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      File localFile = new File(paramString);
      if (!(localFile.exists()))
        throw new NotFoundException(paramString + " 数据文件不存在", "405");
      FileInputStream localFileInputStream = new FileInputStream(localFile);
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localFileInputStream, "UTF-8"));
      String str = "";
      while (true)
      {
        do
          if ((str = localBufferedReader.readLine()) == null){
        	  
          }
        while (!(_$1(str)));
        localArrayList.add(str);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    String[] arrayOfString = new String[localArrayList.size()];
    for (int i = 0; i < arrayOfString.length; ++i)
      arrayOfString[i] = ((String)localArrayList.get(i));
    return arrayOfString;
  }

  private boolean _$1(String paramString)
  {
    if (AppUtils.isBlank(paramString))
      return false;
    String str = paramString.trim();
    if (!(isLetter(str)))
      return (str.startsWith(")"));
    return true;
  }

  public boolean isLetter(String paramString)
  {
    int i = paramString.charAt(0);
    int j = i;
    return (((j >= 65) && (j <= 90)) || ((j >= 97) && (j <= 122)));
  }

  public static void main(String[] paramArrayOfString)
  {
  }
}