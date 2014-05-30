package com.legendshop.business.service.impl;

import com.legendshop.business.dao.LoginHistoryDao;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.LoginHistory;
import com.legendshop.spi.service.LoginHistoryService;
import com.legendshop.util.ip.IPSeeker;
import com.legendshop.util.sql.ConfigCode;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;

public class LoginHistoryServiceImpl
  implements LoginHistoryService
{
  private final Logger log = LoggerFactory.getLogger(LoginHistoryServiceImpl.class);
  private LoginHistoryDao loginHistoryDao;
  private JdbcTemplate jdbcTemplate;

  public void saveLoginHistory(String userName, String ip)
  {
    if (((Boolean)PropertiesUtil.getObject(SysParameterEnum.LOGIN_LOG_ENABLE, Boolean.class)).booleanValue()) {
      this.log.debug("user {} login system from ip {}", userName, ip);
      try {
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setIp(ip);
        loginHistory.setTime(new Date());
        loginHistory.setArea(IPSeeker.getInstance().getArea(ip));
        loginHistory.setCountry(IPSeeker.getInstance().getCountry(ip));
        loginHistory.setUserName(userName);
        this.loginHistoryDao.save(loginHistory);
        String sql = ConfigCode.getInstance().getCode("login.updateUserDetail");
        if (this.log.isDebugEnabled())
          this.log.debug("execute run sql {}, Ip {}, Time {}, UserName {} ", new Object[] { sql, loginHistory.getIp(), 
            loginHistory.getTime(), loginHistory.getUserName() });

        this.jdbcTemplate.update(sql, new Object[] { loginHistory.getIp(), loginHistory.getTime(), loginHistory.getUserName() });
      }
      catch (Exception e) {
        this.log.error("save userLoginHistory", e);
      }
    }
  }

  @Required
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }

  public PageSupport getLoginHistory(CriteriaQuery cq)
  {
    return this.loginHistoryDao.getLoginHistory(cq);
  }

  public PageSupport getLoginHistoryBySQL(SqlQuery query)
  {
    return this.loginHistoryDao.getLoginHistoryBySQL(query);
  }

  @Required
  public void setLoginHistoryDao(LoginHistoryDao loginHistoryDao)
  {
    this.loginHistoryDao = loginHistoryDao;
  }
}