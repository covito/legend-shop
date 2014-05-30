package com.legendshop.core.tag;

import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ServiceLocatorIF;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public abstract class LegendShopTag extends SimpleTagSupport {
	protected HttpServletRequest request() {
		return ((HttpServletRequest) pageContext().getRequest());
	}

	protected void setAttribute(String paramString, Object paramObject) {
		request().setAttribute(paramString, paramObject);
	}

	protected HttpServletResponse response() {
		return ((HttpServletResponse) pageContext().getResponse());
	}

	protected void write(String paramString) throws IOException {
		pageContext().getOut().write(paramString);
	}

	protected void invokeJspBody() throws JspException, IOException {
		if (getJspBody() != null)
			getJspBody().invoke(pageContext().getOut());
	}

	protected Object getBean(String paramString) {
		return ContextServiceLocator.getInstance().getBean(paramString);
	}

	protected <T> T getBean(Class<T> paramClass) {
		return (T)getBean(paramClass.getName());
	}

	protected PageContext pageContext() {
		return ((PageContext) getJspContext());
	}
}