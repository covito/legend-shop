package com.legendshop.business.security;

import com.legendshop.business.dao.BasketDao;
import com.legendshop.core.security.model.UserDetail;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.Basket;
import com.legendshop.spi.event.LoginEvent;
import com.legendshop.util.CookieUtil;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class AuthenticationSuccessHandlerImpl extends
		SavedRequestAwareAuthenticationSuccessHandler {
	private boolean supportSSO = false;
	private BasketDao basketDao;

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDetail userDetail = (UserDetail) authentication.getPrincipal();
		String userName = userDetail.getUsername();

		session.setAttribute("SPRING_SECURITY_LAST_USERNAME", userName);

		clearTryLoginCount(request);

		Map basketMap = null;
		try {
			basketMap = (Map) session.getAttribute("BASKET_KEY");
			if (basketMap == null) {

			}
			for (Iterator localIterator = basketMap.values().iterator(); localIterator
					.hasNext();) {
				Basket basket = (Basket) localIterator.next();
				this.basketDao.saveToCart(userName, basket.getProdId(),
						basket.getBasketCount(), basket.getAttribute());
			}
			session.removeAttribute("BASKET_KEY");
			session.removeAttribute("BASKET_HW_COUNT");
			session.removeAttribute("BASKET_HW_ATTR");
		} catch (Exception e) {
			this.logger.error("process unsave order", e);
		}

		Integer baskettotalCount = Integer.valueOf(this.basketDao
				.getTotalBasketByUserName(userName).intValue());
		session.setAttribute("BASKET_TOTAL_COUNT", baskettotalCount);

		if (this.supportSSO) {
			CookieUtil.addCookie(response, "jforumUserInfo", userName);
		}

		EventHome.publishEvent(new LoginEvent(userDetail, request
				.getRemoteAddr()));
		super.onAuthenticationSuccess(request, response, authentication);
	}

	private void clearTryLoginCount(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.removeAttribute("TRY_LOGIN_COUNT");
	}

	public void setSupportSSO(boolean supportSSO) {
		this.supportSSO = supportSSO;
	}

	public void setBasketDao(BasketDao basketDao) {
		this.basketDao = basketDao;
	}
}