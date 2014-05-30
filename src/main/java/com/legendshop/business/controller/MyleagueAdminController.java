package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.Myleague;
import com.legendshop.spi.constants.MyLeagueEnum;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.MyleagueService;
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

@Controller
@RequestMapping({"/admin/myleague"})
public class MyleagueAdminController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(NewsAdminController.class);
  public static String LIST_PAGE = "/myleague/myleagueList";
  public static String EDIT_PAGE = "/myleague/myleague";
  public static String LIST_QUERY = "/admin/myleague/query";

  @Autowired
  private MyleagueService myleagueService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Myleague myleague)
  {
    CriteriaQuery cq = new CriteriaQuery(Myleague.class, curPageNO, "javascript:pager");
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    if (CommonServiceUtil.haveViewAllDataFunction(request))
      if (!(AppUtils.isBlank(myleague.getUserId())))
        cq.like("userId", "%" + StringUtils.trim(myleague.getUserId()) + "%");

    else
      cq.eq("userId", UserManager.getUserName(request.getSession()));

    if (!(AppUtils.isBlank(myleague.getFriendId()))) {
      cq.like("friendId", "%" + myleague.getFriendId() + "%");
    }

    PageSupport ps = this.myleagueService.getMyleagueList(cq);
    ps.savePage(request);
    request.setAttribute("bean", myleague);
    return PathResolver.getPath(request, response, BackPage.LEAGUE_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Myleague myleague)
  {
    this.myleagueService.save(myleague);
    saveMessage(request, ResourceBundleHelper.getSucessfulString());
    return PathResolver.getPath(request, response, FowardPage.LEAGUE_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    this.myleagueService.delete(id);
    saveMessage(request, ResourceBundleHelper.getDeleteString());
    return PathResolver.getPath(request, response, FowardPage.LEAGUE_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Myleague myleague = this.myleagueService.getMyleagueById(id);
    request.setAttribute("bean", myleague);
    return PathResolver.getPath(request, response, BackPage.LEAGUE_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, Myleague myleague)
  {
    Myleague origin = this.myleagueService.getMyleagueById(myleague.getId());
    if (origin == null)
      throw new NotFoundException("Myleague is empty");

    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), origin.getUserId());
    if (result != null)
      return result;

    this.log.info("{} update Myleague UserId{}", origin.getUserId(), origin.getFriendId());
    origin.setDisplayOrder(myleague.getDisplayOrder());
    origin.setFriendName(myleague.getFriendName());
    this.myleagueService.update(origin);
    saveMessage(request, ResourceBundleHelper.getSucessfulString());
    return PathResolver.getPath(request, response, FowardPage.LEAGUE_LIST_QUERY);
  }

  @RequestMapping({"/addMyLeague"})
  @ResponseBody
  public Integer addMyLeague(HttpServletRequest request, HttpServletResponse response, String siteName)
  {
    String userName = UserManager.getUserName();
    String shopName = ThreadLocalContext.getCurrentShopName(request, response);
    if ((AppUtils.isBlank(userName)) || (AppUtils.isBlank(shopName)))
      return MyLeagueEnum.ERROR.value();

    if (userName.equals(shopName))
      return MyLeagueEnum.THESAME.value();

    Myleague myleague = this.myleagueService.getMyleague(userName, shopName);
    if (!(AppUtils.isBlank(myleague)))
      return MyLeagueEnum.DONE.value();

    myleague = new Myleague();
    myleague.setAddtime(new Date());
    myleague.setFriendId(shopName);
    myleague.setStatus(MyLeagueEnum.ONGOING.value());
    myleague.setFriendName(siteName);
    myleague.setUserId(userName);
    this.myleagueService.save(myleague);
    return MyLeagueEnum.ONGOING.value();
  }
}