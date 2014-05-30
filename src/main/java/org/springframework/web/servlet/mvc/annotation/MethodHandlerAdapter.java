package org.springframework.web.servlet.mvc.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.legendshop.central.license.LicenseEnum;
import com.legendshop.core.exception.PermissionException;

public class MethodHandlerAdapter extends AnnotationMethodHandlerAdapter {
	public ModelAndView handle(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject)
			throws Exception {
		String str = (String) paramHttpServletRequest.getSession()
				.getServletContext().getAttribute("UN_AUTH_MSG");
		if (LicenseEnum.UN_AUTH.name().equals(str))
			throw new PermissionException(str);
		return super.handle(paramHttpServletRequest, paramHttpServletResponse,
				paramObject);
	}
}