package com.legendshop.business.handler;

import com.legendshop.business.dao.BasketDao;
import com.legendshop.model.entity.Basket;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

public class MyRememberMeAuthenticationFilter extends RememberMeAuthenticationFilter
{
  private BasketDao basketDao;

  protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult)
  {
    request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME", 
      TextEscapeUtils.escapeEntities(authResult.getName()));

    Map basketMap = (Map)request.getSession().getAttribute("BASKET_KEY");
    if (basketMap != null)
    {
      for (Iterator localIterator = basketMap.values().iterator(); localIterator.hasNext(); ) { Basket basket = (Basket)localIterator.next();
        this.basketDao.saveToCart(authResult.getName(), basket.getProdId(), basket.getBasketCount(), basket.getAttribute());
      }
      request.getSession().removeAttribute("BASKET_KEY");
    }
  }

  @Required
  public void setBasketDao(BasketDao basketDao)
  {
    this.basketDao = basketDao;
  }
}