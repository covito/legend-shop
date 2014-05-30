package com.legendshop.spi.service.impl;

import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.model.UserMessages;
import com.legendshop.spi.dao.AdvertisementDao;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.spi.service.BaseService;
import com.legendshop.util.AppUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class AbstractService
  implements BaseService
{
  protected ShopDetailDao shopDetailDao;
  protected AdvertisementDao advertisementDao;

  public Object getSessionAttribute(HttpServletRequest request, String name)
  {
    Object obj = null;
    HttpSession session = request.getSession();
    if (session != null)
      obj = session.getAttribute(name);

    return obj;
  }

  public void setSessionAttribute(HttpServletRequest request, String name, Object obj)
  {
    HttpSession session = request.getSession();
    if (session != null)
      session.setAttribute(name, obj);
  }

  public void getAndSetAdvertisement(HttpServletRequest request, HttpServletResponse response, String shopName, String page)
  {
    Map advertisement = this.advertisementDao.getAdvertisement(shopName, page);
    if (AppUtils.isNotBlank(advertisement))
      for (Iterator localIterator = advertisement.keySet().iterator(); localIterator.hasNext(); ) { String type = (String)localIterator.next();
        request.setAttribute(type, advertisement.get(type));
      }
  }

  public void getAndSetOneAdvertisement(HttpServletRequest request, HttpServletResponse response, String shopName, String key)
  {
    List advertisement = this.advertisementDao.getOneAdvertisement(shopName, key);
    if (!(AppUtils.isBlank(advertisement)))
      request.setAttribute(key, advertisement);
  }

  protected int random(int count)
  {
    Random random = new Random();
    return random.nextInt(count);
  }

  public void setShopDetailDao(ShopDetailDao shopDetailDao)
  {
    this.shopDetailDao = shopDetailDao;
  }

  public void setAdvertisementDao(AdvertisementDao advertisementDao)
  {
    this.advertisementDao = advertisementDao;
  }

  public String checkPrivilege(HttpServletRequest request, String loginName, String userName)
  {
    String result = null;
    if ((!(FoundationUtil.haveViewAllDataFunction(request))) && 
      (!(loginName.equals(userName)))) {
      UserMessages userMessages = new UserMessages("401", "Access Deny", 
        " can not edit this object belongs to " + userName);
      result = handleException(request, userMessages);
    }

    return result;
  }

  protected String handleException(HttpServletRequest request, UserMessages userMessages) {
    request.setAttribute(UserMessages.MESSAGE_KEY, userMessages);
    return "/common/error";
  }
}