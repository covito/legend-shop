package com.legendshop.core.helper;

import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.exception.BaseException;
import com.legendshop.core.handler.HandlerManager;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ServiceLocatorIF;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class LegendFilter extends OncePerRequestFilter {
	private HandlerManager _$3;
	public static String HTML_PATH = PropertiesUtil.getHtmlPath();
	private List<String> _$2;
	private String _$1 = null;

	protected void doFilterInternal(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,
			FilterChain paramFilterChain) throws ServletException, IOException {
		String str = paramHttpServletRequest.getRequestURI();
		boolean bool = _$1(str);
		Boolean localBoolean = (Boolean) PropertiesUtil.getObject(
				SysParameterEnum.STATIC_PAGE_SUPPORT, Boolean.class);
		if (localBoolean == null)
			bool = false;
		if ((localBoolean != null) && (localBoolean.booleanValue()))
			processStaticPage(str, bool, paramHttpServletRequest,
					paramHttpServletResponse, paramFilterChain);
		else
			doHandle(bool, paramHttpServletRequest, paramHttpServletResponse,
					paramFilterChain);
	}

	protected void doHandle(boolean paramBoolean,
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,
			FilterChain paramFilterChain) {
		try {
			if (paramBoolean) {
				ThreadLocalContext.setRequest(paramHttpServletRequest);
				this._$3.handle(paramHttpServletRequest,
						paramHttpServletResponse);
			}
			paramFilterChain.doFilter(paramHttpServletRequest,
					paramHttpServletResponse);
		} catch (BaseException localBaseException) {
		} catch (Exception localException) {
		} finally {
			if (paramBoolean)
				ThreadLocalContext.clean();
		}
	}

	public void processStaticPage(String paramString, boolean paramBoolean,
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,
			FilterChain paramFilterChain) throws IOException, ServletException {
		String str1 = _$1(paramString, this._$1);
		if (str1 != null) {
			String str2 = paramString
					.substring(paramString.lastIndexOf("/") + 1);
			if (AppUtils.isBlank(str2))
				str2 = "index";
			String str3 = str2 + ".html";
			String str4 = ThreadLocalContext.getCurrentShopName(
					paramHttpServletRequest, paramHttpServletResponse);
			String str5 = HTML_PATH + str4;
			StringBuilder localStringBuilder = new StringBuilder(str5)
					.append(str1).append("/").append(str3);
			String str6 = localStringBuilder.toString();
			localStringBuilder.setLength(0);
			String str7 = this._$1 + "/" + "html/" + str4 + str1 + "/" + str3;
			File localFile = new File(str6);
			if (!(localFile.exists())) {
				CharResponseWrapper localCharResponseWrapper = new CharResponseWrapper(
						paramHttpServletResponse);
				paramFilterChain.doFilter(paramHttpServletRequest,
						localCharResponseWrapper);
				String str8 = localCharResponseWrapper.toString();
				if (str8 != null) {
					FileProcessor.writeFile(str8, str5 + str1, str3);
					paramHttpServletResponse.sendRedirect(str7);
				}
			} else {
				paramHttpServletResponse.sendRedirect(str7);
			}
		} else {
			doHandle(paramBoolean, paramHttpServletRequest,
					paramHttpServletResponse, paramFilterChain);
		}
	}

	private boolean _$1(String paramString) {
		if (paramString == null)
			return false;
		return (paramString.indexOf(".") < 0);
	}

	private String _$1(String paramString1, String paramString2) {
		String str1 = paramString1.substring(paramString2.length());
		String str2 = str1.substring(0, str1.lastIndexOf("/"));
		if ((this._$2.contains(str2)) || (str1.equals("/index")))
			return str2;
		return null;
	}

	public void destroy() {
	}

	protected void initFilterBean() throws ServletException {
		this._$3 = ((HandlerManager) ContextServiceLocator.getInstance()
				.getBean("handlerManager"));
		this._$1 = getFilterConfig().getServletContext().getContextPath();
		this._$2 = new ArrayList();
		this._$2.add("/sort");
		this._$2.add("/views");
		this._$2.add("/views");
		this._$2.add("/group");
		this._$2.add("/group/view");
	}

	public void setHandlerManager(HandlerManager paramHandlerManager) {
		this._$3 = paramHandlerManager;
	}
}