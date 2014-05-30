package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.FunctionEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.randing.CaptchaServiceSingleton;
import com.legendshop.event.EventContext;
import com.legendshop.event.EventHome;
import com.legendshop.event.GenericEvent;
import com.legendshop.model.ValidationMessage;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.event.EventId;
import com.legendshop.spi.form.UserForm;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.BasketService;
import com.legendshop.spi.service.LoginService;
import com.legendshop.spi.service.UserDetailService;
import com.legendshop.spi.service.impl.DefaultLoginServiceImpl;
import com.legendshop.spi.service.timer.SubService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ServiceLocatorIF;
import com.octo.captcha.service.image.ImageCaptchaService;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private SubService subService;

	@Autowired
	private BasketService basketService;
	private LoginService loginService;

	@RequestMapping({ "/p/myaccount" })
	public String myaccount(HttpServletRequest request,
			HttpServletResponse response, String curPageNO, String newsCategory) {
		String userName = UserManager.getUserName(request);
		if (userName == null)
			return PathResolver.getPath(request, response, TilesPage.LOGIN);

		String viewName = request.getParameter("userName");
		if ((AppUtils.isNotBlank(viewName))
				&& (UserManager.hasFunction(request.getSession(),
						FunctionEnum.FUNCTION_VIEW_ALL_DATA.value()))) {
			userName = viewName;
			request.setAttribute("isAdmin", Boolean.valueOf(true));
		}

		UserDetail userDetail = this.userDetailService.getUserDetail(userName);
		if (userDetail == null) {
			this.log.error("userDetail not found, userName = " + userName);
			throw new NotFoundException("user not found");
		}

		ShopDetail shopDetail = userDetail.getShopDetail();
		if (shopDetail != null) {
			request.setAttribute("myShopDetail", shopDetail);
		}

		if (userDetail.getBirthDate() != null)
			setBirthDate(userDetail.getBirthDate(), request);

		if (userDetail.getScore() == null)
			userDetail.setScore(Long.valueOf(-4648550332614311936L));

		request.setAttribute("user", userDetail);

		EventContext eventContext = new EventContext(request);
		EventHome.publishEvent(new GenericEvent(eventContext,
				EventId.CAN_ADD_SHOPDETAIL_EVENT));

		request.setAttribute("supportOpenShop",
				eventContext.getBooleanResponse());

		request.setAttribute("totalProcessingOrder",
				this.subService.getTotalProcessingOrder(userName));
		request.setAttribute("totalBasketByuserName",
				this.basketService.getTotalBasketByUserName(userName));
		this.userDetailService.getAndSetOneAdvertisement(request, response,
				ThreadLocalContext.getCurrentShopName(request, response),
				"USER_REG_ADV_740");
		return PathResolver.getPath(request, response, TilesPage.MYACCOUNT);
	}

	@RequestMapping({ "/shopcontact" })
	public String shopcontact(HttpServletRequest request,
			HttpServletResponse response) {
		String shopName = request.getParameter("shop");
		if (shopName == null)
			shopName = ThreadLocalContext.getCurrentShopName(request, response);

		if (shopName == null)
			return PathResolver.getPath(request, response, TilesPage.SEARCHALL);

		UserDetail userDetail = this.userDetailService.getUserDetail(shopName);

		request.setAttribute("user", userDetail);
		return PathResolver.getPath(request, response, TilesPage.SHOPCONTACT);
	}

	@RequestMapping({ "/login" })
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, response, TilesPage.LOGIN);
	}

	@RequestMapping({ "/updateAccount" })
	public String updateAccount(HttpServletRequest request,
			HttpServletResponse response, UserForm userForm) {
		return this.userDetailService
				.updateAccount(request, response, userForm);
	}

	@RequestMapping({ "/userReg" })
	public String userReg(HttpServletRequest request,
			HttpServletResponse response, UserForm userForm) {
		ValidationMessage message = userForm.validate();
		if (message.isFailed()) {
			this.log.error("register failed: " + message);
			throw new BusinessException("UserForm validation failed");
		}
		String result = this.userDetailService.saveUserReg(request, response,
				userForm);
		if (!(((Boolean) PropertiesUtil.getObject(
				SysParameterEnum.VALIDATION_FROM_MAIL, Boolean.class))
				.booleanValue())) {
			getLoginService().onAuthentication(request, response,
					userForm.getName(), userForm.getPassword());
		}
		return result;
	}

	private LoginService getLoginService() {
		if (this.loginService == null) {
			if (ContextServiceLocator.getInstance()
					.containsBean("loginService"))
				this.loginService = ((LoginService) ContextServiceLocator
						.getInstance().getBean("loginService"));

			if (this.loginService == null)
				this.loginService = new DefaultLoginServiceImpl();
		}

		return this.loginService;
	}

	@RequestMapping({ "/reg" })
	public String reg(HttpServletRequest request, HttpServletResponse response) {
		this.userDetailService.getAndSetOneAdvertisement(request, response,
				ThreadLocalContext.getCurrentShopName(request, response),
				"USER_REG_ADV_950");

		EventContext eventContext = new EventContext(request);
		EventHome.publishEvent(new GenericEvent(eventContext,
				EventId.CAN_ADD_SHOPDETAIL_EVENT));
		request.setAttribute("supportOpenShop",
				eventContext.getBooleanResponse());

		request.setAttribute("validationOnOpenShop", PropertiesUtil.getObject(
				SysParameterEnum.VALIDATION_ON_OPEN_SHOP, Boolean.class));

		String content = null;
		try {
			File file = new File(PropertiesUtil.getDownloadFilePath()
					+ "/register/regItem.html");
			content = FileProcessor.readFile(file, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("regItem", content);
		return PathResolver.getPath(request, response, TilesPage.REG);
	}

	@RequestMapping({ "/addShop" })
	public String addShop(HttpServletRequest request,
			HttpServletResponse response, ShopDetail shopDetail) {
		String userName = UserManager.getUserName(request);
		if (AppUtils.isBlank(userName))
			return PathResolver.getPath(request, response, TilesPage.NO_LOGIN);

		return this.userDetailService.saveShop(request, response, shopDetail);
	}

	@RequestMapping({ "/isUserExist" })
	@ResponseBody
	public Boolean isUserExist(String userName) {
		return Boolean.valueOf(this.userDetailService.isUserExist(userName));
	}

	@RequestMapping({ "/isEmailExist" })
	@ResponseBody
	public Boolean isEmailExist(String email) {
		return Boolean.valueOf(this.userDetailService.isEmailExist(email));
	}

	@RequestMapping({ "/userRegSuccess" })
	public String userRegSuccess(HttpServletRequest request,
			HttpServletResponse response, String userName, String registerCode) {
		return this.userDetailService.updateUserReg(request, response,
				userName, registerCode);
	}

	@RequestMapping({ "/resetpassword" })
	public String resetpassword(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return PathResolver.getPath(request, response, FrontPage.RESETPASSWORD);
	}

	@RequestMapping({ "/openShop" })
	public String openShop(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return PathResolver.getPath(request, response, TilesPage.OPEN_SHOP);
	}

	@RequestMapping({ "/resetPassword" })
	@ResponseBody
	public String resetPassword(String userName, String userMail) {
		if ((AppUtils.isBlank(userName)) || (AppUtils.isBlank(userMail)))
			return "fail";
		try {
			String templateFilePath = PropertiesUtil.getDownloadFilePath()
					+ "/mail/resetpassmail.jsp";
			if (this.userDetailService.updatePassword(userName, userMail,
					templateFilePath))
				return null;

			return "fail";
		} catch (Exception e) {
			this.log.error("", e);
		}
		return "fail";
	}

	@RequestMapping({ "/validateRandImg" })
	@ResponseBody
	public boolean validateRandImg(HttpServletRequest request,
			HttpServletResponse response, String randNum) {
		HttpSession session = request.getSession();
		if (session == null)
			return true;

		return CaptchaServiceSingleton.getInstance()
				.validateResponseForID(session.getId(), randNum).booleanValue();
	}

	private void setBirthDate(String birthDate, HttpServletRequest request) {
		try {
			String year = birthDate.substring(0, 4);
			String month = birthDate.substring(4, 6);
			String day = birthDate.substring(6, 8);
			request.setAttribute("userBirthYear", year);
			request.setAttribute("userBirthMonth", month);
			request.setAttribute("userBirthDay", day);
		} catch (Exception year) {
		}
	}
}