package com.legendshop.business.service.impl;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.core.exception.ApplicationException;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.core.model.UserMessages;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.User;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.constants.RegisterEnum;
import com.legendshop.spi.event.SendMailEvent;
import com.legendshop.spi.form.UserForm;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.UserDetailService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.BeanHelper;
import com.legendshop.util.MD5Util;
import com.legendshop.util.SafeHtml;
import com.legendshop.util.constant.ShopStatusEnum;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.oro.text.regex.MalformedPatternException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class UserDetailServiceImpl extends BaseServiceImpl
  implements UserDetailService
{
  private static Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);
  private UserDetailDao userDetailDao;

  public Long getScore(String userName)
  {
    return this.userDetailDao.getUserScore(userName);
  }

  public PageSupport getUserDetailList(HqlQuery hqlQuery)
  {
    return this.userDetailDao.getUserDetailList(hqlQuery);
  }

  public UserDetail getUserDetail(String userName)
  {
    return this.userDetailDao.getUserDetail(userName);
  }

  public String updateAccount(HttpServletRequest request, HttpServletResponse response, UserForm form)
  {
    ShopDetail shopDetail = form.getShopDetail();
    String userName = UserManager.getUserName(request);
    if ((CommonServiceUtil.haveViewAllDataFunction(request)) && 
      (AppUtils.isNotBlank(form.getUserName()))) {
      userName = form.getUserName();
    }

    User user = this.userDetailDao.getUserByName(userName);
    if (user == null) {
      UserMessages messages = new UserMessages("601", 
        ResourceBundleHelper.getString("update.myaccount.fail"), ResourceBundleHelper.getString("check.parameter"));
      messages.addCallBackList(ResourceBundleHelper.getString("reupdate.myaccount"), null, "updateAccount.do");
      request.setAttribute(UserMessages.MESSAGE_KEY, messages);
      return PathResolver.getPath(request, response, TilesPage.AFTER_OPERATION);
    }
    UserDetail userDetail = this.userDetailDao.getUserDetail(userName);
    if ((!(AppUtils.isBlank(form.getPassword()))) && 
      (!(user.getPassword().equals(MD5Util.Md5Password(userName, form.getPasswordOld()))))) {
      log.warn("old password does not match!");
      UserMessages messages = new UserMessages("601", 
        ResourceBundleHelper.getString("error.old.password"), ResourceBundleHelper.getString("check.parameter"));
      messages.addCallBackList(ResourceBundleHelper.getString("reupdate.myaccount"), 
        ResourceBundleHelper.getString("notmatch.old.password"), "myaccount.do");
      request.setAttribute(UserMessages.MESSAGE_KEY, messages);

      return PathResolver.getPath(request, response, TilesPage.AFTER_OPERATION);
    }

    boolean update = true;
    if (userDetail == null) {
      update = false;
      userDetail = new UserDetail();
    }
    Date date = new Date();
    SafeHtml safeHtml = new SafeHtml();
    userDetail.setNickName(safeHtml.makeSafe(form.getNickName()));
    userDetail.setSex(safeHtml.makeSafe(form.getSex()));
    userDetail.setBirthDate(form.getBirthDate());

    userDetail.setUserMail(form.getUserMail());

    userDetail.setUserAdds(safeHtml.makeSafe(form.getUserAdds()));
    userDetail.setUserTel(safeHtml.makeSafe(form.getUserTel()));
    userDetail.setUserPostcode(safeHtml.makeSafe(form.getUserPostcode()));
    userDetail.setPassword(form.getPassword());
    userDetail.setFax(safeHtml.makeSafe(form.getFax()));
    userDetail.setModifyTime(date);
    userDetail.setUserId(user.getId());
    userDetail.setUserMobile(safeHtml.makeSafe(form.getUserMobile()));
    userDetail.setMsn(safeHtml.makeSafe(form.getMsn()));

    userDetail.setQq(safeHtml.makeSafe(form.getQq()));

    String year = form.getUserBirthYear();
    String month = form.getUserBirthMonth();
    String day = form.getUserBirthDay();
    if ((year != null) && (form.getUserBirthMonth() != null) && (form.getUserBirthDay() != null)) {
      if (month.length() < 2)
        month = "0" + month;

      if (day.length() < 2)
        day = "0" + day;

      userDetail.setBirthDate(year + month + day);
    }

    boolean openshop = request.getParameter("openShop") != null;
    if (update) {
      this.userDetailDao.updateUser(userDetail);
      this.userDetailDao.updateShopDetail(userDetail, shopDetail, openshop);
    } else {
      userDetail.setUserRegip(request.getRemoteAddr());
      userDetail.setUserRegtime(date);
      userDetail.setUserId(user.getId());
      userDetail.setUserName(userName);
      this.userDetailDao.saveUerDetail(userDetail, shopDetail, openshop);
      this.userDetailDao.updatePassword(userDetail);
    }

    UserMessages messages = new UserMessages("200", "", "");
    messages.addCallBackList(ResourceBundleHelper.getString("myaccount"), ResourceBundleHelper.getString("reupdate.myaccount"), 
      "p/myaccount");
    request.setAttribute(UserMessages.MESSAGE_KEY, messages);
    return PathResolver.getPath(request, response, TilesPage.AFTER_OPERATION);
  }

  public String saveUserReg(HttpServletRequest request, HttpServletResponse response, UserForm form)
  {
    SafeHtml safeHtml = new SafeHtml();
    form.setUserName(safeHtml.makeSafe(form.getUserName()));
    form.setUserMemo(safeHtml.makeSafe(form.getUserMemo()));
    form.setUserMobile(safeHtml.makeSafe(form.getUserMobile()));
    form.setUserPostcode(safeHtml.makeSafe(form.getUserPostcode()));
    form.setUserTel(safeHtml.makeSafe(form.getUserTel()));
    form.setUserMail(safeHtml.makeSafe(form.getUserMail()));
    form.setUserAdds(safeHtml.makeSafe(form.getUserAdds()));
    form.setMsn(safeHtml.makeSafe(form.getMsn()));
    form.setNote(safeHtml.makeSafe(form.getNote()));
    form.setQq(safeHtml.makeSafe(form.getQq()));
    form.setName(safeHtml.makeSafe(form.getName()));
    form.setNickName(safeHtml.makeSafe(form.getNickName()));

    ShopDetail shopDetail = form.getShopDetail();
    if (shopDetail != null) {
      shopDetail.setRealPath(RealPathUtil.getBigPicRealPath());
      shopDetail.setIp(request.getRemoteAddr());
      shopDetail.setSiteName(safeHtml.makeSafe(shopDetail.getSiteName()));
      shopDetail.setPostAddr(safeHtml.makeSafe(shopDetail.getPostAddr()));
      shopDetail.setIdCardNum(safeHtml.makeSafe(shopDetail.getIdCardNum()));
      shopDetail.setGradeId(Integer.valueOf(0));
    }

    if (isUserInfoValid(form, request)) {
      return PathResolver.getPath(request, response, FrontPage.FAIL);
    }

    User user = new User();
    UserDetail userDetail = new UserDetail();
    userDetail.setGradeId(Integer.valueOf(1));
    BeanHelper.copyProperties(user, form, true);
    BeanHelper.copyProperties(userDetail, form, true);
    Date date = new Date();
    String plaintPassword = user.getPassword();
    user.setPassword(MD5Util.Md5Password(user.getName(), plaintPassword));
    userDetail.setUserRegtime(date);
    userDetail.setModifyTime(date);
    userDetail.setUserRegip(request.getRemoteAddr());
    userDetail.setTotalCash(Double.valueOf(0D));
    userDetail.setTotalConsume(Double.valueOf(0D));
    boolean isOpenShop = request.getParameter("openShop") != null;

    this.userDetailDao.saveUser(user, userDetail, shopDetail, isOpenShop);

    UserMessages uem = new UserMessages();
    uem.setTitle(ResourceBundleHelper.getString("regFree") + " " + form.getName() + " " + 
      ResourceBundleHelper.getString("success.hint"));
    if (userDetail.getRegisterCode() == null)
      uem.setDesc(ResourceBundleHelper.getString("after.reg.success"));
    else {
      uem.setDesc(ResourceBundleHelper.getString("reg.success.acknowledgement"));
    }

    uem.addCallBackList(ResourceBundleHelper.getString("login"), ResourceBundleHelper.getString("logon.hint.desc"), "login");
    request.setAttribute(UserMessages.MESSAGE_KEY, uem);
    this.userDetailDao.flush();

    if (((Boolean)PropertiesUtil.getObject(SysParameterEnum.VALIDATION_FROM_MAIL, Boolean.class)).booleanValue())
      try {
        String text = getMailText(request, user, userDetail);
        EventHome.publishEvent(new SendMailEvent(userDetail.getUserMail(), "恭喜您，注册商城成功", text));
        log.info("{} 注册成功，发送通知邮件", userDetail.getUserMail());
      } catch (Exception e) {
        log.info("{}，发送通知邮件失败，请检查邮件配置", userDetail.getUserMail());
        throw new ApplicationException(e, "发送通知邮件失败，请检查邮件配置", "BUSINESS_ERROR");
      }


    return PathResolver.getPath(request, response, TilesPage.AFTER_OPERATION);
  }

  private String getMailText(HttpServletRequest request, User user, UserDetail userDetail) throws MalformedPatternException
  {
    StringBuffer buffer;
    String filePath = PropertiesUtil.getDownloadFilePath() + "/mail/registersuccess.jsp";

    Map values = new HashMap();
    values.put("#nickName#", userDetail.getNickName());
    values.put("#userName#", userDetail.getUserName());
    values.put("#password#", userDetail.getPassword());
    if (AppUtils.isNotBlank(userDetail.getRegisterCode())) {
      buffer = new StringBuffer();
      buffer.append("<p>你的帐号尚未开通，<a href=\"").append(PropertiesUtil.getDomainName()).append("/userRegSuccess?userName=").append
        (user.getName()).append("&registerCode=").append(userDetail.getRegisterCode()).append
        ("\">点击开通我的帐号</a></p><br>");
      values.put("#registerCode#", buffer.toString());
    } else {
      buffer = new StringBuffer();
      buffer.append("<p>你的帐号已经开通成功!</p><br>");
      values.put("#registerCode#", buffer.toString());
    }
    String text = AppUtils.convertTemplate(filePath, values);
    return text;
  }

  public String saveShop(HttpServletRequest request, HttpServletResponse response, ShopDetail shopDetail)
  {
    try {
      if (shopDetail != null)
        shopDetail.setRealPath(RealPathUtil.getBigPicRealPath());

      UserDetail userDetail = new UserDetail();
      userDetail.setUserId(UserManager.getUserId(request.getSession()));
      userDetail.setUserName(UserManager.getUserName(request.getSession()));
      Integer status = this.userDetailDao.saveShopDetailAndRole(userDetail, shopDetail);
      String openResultDesc = null;

      if (ShopStatusEnum.AUDITING.value().equals(status))
        openResultDesc = ResourceBundleHelper.getString("apply.shop.auditing");
      else {
        openResultDesc = ResourceBundleHelper.getString("apply.shop.success.relogin");
      }

      UserMessages messages = new UserMessages("200", 
        ResourceBundleHelper.getString("apply.shop.success"), openResultDesc);
      request.setAttribute(UserMessages.MESSAGE_KEY, messages);
      return PathResolver.getPath(request, response, TilesPage.AFTER_OPERATION);
    }
    catch (Exception e) {
      log.error("addShop ", e);
      UserMessages messages = new UserMessages("601", ResourceBundleHelper.getString("apply.shop.failed"), 
        ResourceBundleHelper.getString("check.parameter"));
      messages.addCallBackList(ResourceBundleHelper.getString("try.again"), null, "openshop");
      request.setAttribute(UserMessages.MESSAGE_KEY, messages); }
    return PathResolver.getPath(request, response, FrontPage.ERROR_PAGE);
  }

  public String updateUserReg(HttpServletRequest request, HttpServletResponse response, String userName, String registerCode)
  {
    RegisterEnum result = this.userDetailDao.getUserRegStatus(userName, registerCode);
    if (!(RegisterEnum.REGISTER_SUCCESS.equals(result)))
      throw new BusinessException(ResourceBundleHelper.getString(result.value()));

    UserMessages messages = new UserMessages("200", ResourceBundleHelper.getString("reg.success.actived"), 
      "");
    messages.addCallBackList(ResourceBundleHelper.getString("login"), ResourceBundleHelper.getString("logon.hint.desc"), 
      "login");
    request.setAttribute(UserMessages.MESSAGE_KEY, messages);
    return PathResolver.getPath(request, response, TilesPage.AFTER_OPERATION);
  }

  @Required
  public void setUserDetailDao(UserDetailDao userDetailDao)
  {
    this.userDetailDao = userDetailDao;
  }

  public PageSupport getUserDetailList(SqlQuery sqlQuery)
  {
    return this.userDetailDao.getUserDetailList(sqlQuery);
  }

  public String deleteUserDetail(String userId, String userName)
  {
    return this.userDetailDao.deleteUserDetail(userId, userName);
  }

  public boolean updatePassword(String userName, String mail, String templateFilePath)
    throws MalformedPatternException, MessagingException
  {
    return this.userDetailDao.updatePassword(userName, mail, templateFilePath);
  }

  private boolean isUserInfoValid(UserForm form, HttpServletRequest request)
  {
    boolean result = false;

    UserMessages messages = new UserMessages();
    if (AppUtils.isBlank(form.getName())) {
      messages.addCallBackList(ResourceBundleHelper.getString("username.required"));
      result = true;
    }
    if (!(result)) {
      if (form.getName().length() < 4) {
        messages.addCallBackList(ResourceBundleHelper.getString("username.minlength"));
        result = true;
      }
      if (this.userDetailDao.isUserExist(form.getName()))
        messages.addCallBackList(ResourceBundleHelper.getString("error.User.IsExist"));

      if (AppUtils.isBlank(form.getUserMail())) {
        messages.addCallBackList(ResourceBundleHelper.getString("user.email.required"));
      }
      else if (this.userDetailDao.isEmailExist(form.getUserMail())) {
        messages.addCallBackList("Email <b>" + form.getUserMail() + "</b> " + 
          ResourceBundleHelper.getString("user.email.exists"));
      }

    }

    result = messages.hasError();
    if (result) {
      request.setAttribute(UserMessages.MESSAGE_KEY, messages);
      request.setAttribute("userForm", form);
    }
    return result;
  }

  public boolean isUserExist(String userName)
  {
    return this.userDetailDao.isUserExist(userName);
  }

  public boolean isEmailExist(String email)
  {
    return this.userDetailDao.isEmailExist(email);
  }

  public boolean isShopExist(String shopName)
  {
    return this.userDetailDao.isShopExist(shopName);
  }

  public User getUser(String userId)
  {
    return this.userDetailDao.getUser(userId);
  }

  public void uppdateUser(User user)
  {
    this.userDetailDao.updateUser(user);
  }
}