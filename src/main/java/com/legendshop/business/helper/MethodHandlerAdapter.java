package com.legendshop.business.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

public class MethodHandlerAdapter extends AnnotationMethodHandlerAdapter
{
  public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception
  {
    return super.handle(request, response, handler);
  }
}