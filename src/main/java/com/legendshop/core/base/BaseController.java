package com.legendshop.core.base;

import com.legendshop.core.UserManager;
import com.legendshop.core.constant.ConfigPropertiesEnum;
import com.legendshop.core.constant.FunctionEnum;
import com.legendshop.core.constant.PagePathCalculator;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.model.UserMessages;
import com.legendshop.util.AppUtils;
import com.legendshop.util.BeanUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class BaseController extends MultiActionController {
	private final String _$1 = "/theme";

	public void copyProperties(Object paramObject1, Object paramObject2) {
		BeanUtils.copyProperties(paramObject1, paramObject2);
	}

	public <T> T copyProperties(Class<T> paramClass, Object paramObject) {
		return BeanUtils.copyProperties(paramClass, paramObject);
	}

	public void saveMessage(HttpServletRequest paramHttpServletRequest,
			String paramString) {
		if (StringUtils.isNotBlank(paramString)) {
			List localList = (List) getOrCreateRequestAttribute(
					paramHttpServletRequest, "springMessages", ArrayList.class);
			localList.add(paramString);
		}
	}

	protected static void saveError(HttpServletRequest paramHttpServletRequest,
			String paramString) {
		if (StringUtils.isNotBlank(paramString)) {
			List localList = (List) getOrCreateRequestAttribute(
					paramHttpServletRequest, "springErrors", ArrayList.class);
			localList.add(paramString);
		}
	}

	protected String handleException(
			HttpServletRequest paramHttpServletRequest,
			UserMessages paramUserMessages) {
		paramHttpServletRequest.setAttribute(UserMessages.MESSAGE_KEY,
				paramUserMessages);
		return "/common/error";
	}

	public static <T> T getOrCreateRequestAttribute(
			HttpServletRequest paramHttpServletRequest, String paramString,
			Class<T> paramClass) {
		T localObject =(T) paramHttpServletRequest.getAttribute(paramString);
		if (localObject == null) {
			try {
				localObject = paramClass.newInstance();
			} catch (Exception localException) {
				ReflectionUtils.handleReflectionException(localException);
			}
			paramHttpServletRequest.setAttribute(paramString, localObject);
		}
		return localObject;
	}

	protected static String appendParamToURI(String paramString1,
			String paramString2, String paramString3) {
		String str = paramString1;
		if (null != str) {
			if (str.indexOf("?") < 0)
				str = str + "?";
			else
				str = str + "&";
			str = str + paramString2 + "=" + paramString3;
		}
		return str;
	}

	private static String _$1(Random paramRandom, int paramInt) {
		StringBuilder localStringBuilder = new StringBuilder();
		for (int i = 0; i < paramInt; ++i) {
			int j = paramRandom.nextInt(10);
			localStringBuilder.append(String.valueOf(j));
		}
		return localStringBuilder.toString();
	}

	protected Set<MultipartFile> getFileSet(
			HttpServletRequest paramHttpServletRequest) {
		MultipartHttpServletRequest localMultipartHttpServletRequest = (MultipartHttpServletRequest) paramHttpServletRequest;
		LinkedHashSet localLinkedHashSet = new LinkedHashSet();
		Iterator localIterator = localMultipartHttpServletRequest
				.getFileNames();
		while (localIterator.hasNext()) {
			String str = (String) localIterator.next();
			MultipartFile localMultipartFile = localMultipartHttpServletRequest
					.getFile(str);
			if (localMultipartFile.getOriginalFilename().length() > 0)
				localLinkedHashSet.add(localMultipartFile);
		}
		return localLinkedHashSet;
	}

	protected CriteriaQuery hasAllDataFunction(
			CriteriaQuery paramCriteriaQuery,
			HttpServletRequest paramHttpServletRequest, String paramString) {
		if (FoundationUtil.haveViewAllDataFunction(paramHttpServletRequest)) {
			if (!(AppUtils.isBlank(paramString)))
				paramCriteriaQuery.like("userName",
						"%" + StringUtils.trim(paramString) + "%");
		} else {
			String str = UserManager.getUserName(paramHttpServletRequest
					.getSession());
			if (str == null)
				throw new AuthorizationException(paramString
						+ " did not logon yet!");
			paramCriteriaQuery.eq("userName", str);
		}
		return paramCriteriaQuery;
	}

	@Deprecated
	protected CriteriaQuery hasAllDataAndOperationFunction(
			CriteriaQuery paramCriteriaQuery,
			HttpServletRequest paramHttpServletRequest, String paramString) {
		if (UserManager.hasFunction(paramHttpServletRequest.getSession(),
				new String[] { FunctionEnum.FUNCTION_VIEW_ALL_DATA.value(),
						FunctionEnum.FUNCTION_F_ADMIN.value(),
						FunctionEnum.FUNCTION_F_SYSTEM.value() })) {
			if (!(AppUtils.isBlank(paramString)))
				paramCriteriaQuery.like("userName",
						"%" + StringUtils.trim(paramString) + "%");
		} else {
			String str = UserManager.getUserName(paramHttpServletRequest
					.getSession());
			if (str == null)
				throw new AuthorizationException(paramString
						+ " did not logon yet!");
			paramCriteriaQuery.eq("userName", str);
		}
		return paramCriteriaQuery;
	}

	protected void hasAllDataFunction(CriteriaQuery paramCriteriaQuery,
			HttpServletRequest paramHttpServletRequest, String paramString1,
			String paramString2) {
		if (FoundationUtil.haveViewAllDataFunction(paramHttpServletRequest)) {
			if (AppUtils.isNotBlank(paramString2))
				paramCriteriaQuery.like(paramString1,
						"%" + StringUtils.trim(paramString2) + "%");
		} else {
			String str = UserManager.getUserName(paramHttpServletRequest
					.getSession());
			if (str == null)
				throw new AuthorizationException(paramString2
						+ " did not logon yet!");
			paramCriteriaQuery.eq(paramString1, str);
		}
	}

	protected String checkPrivilege(HttpServletRequest paramHttpServletRequest,
			String paramString1, String paramString2) {
		String str = null;
		if ((!(FoundationUtil.haveViewAllDataFunction(paramHttpServletRequest)))
				&& (!(paramString1.equals(paramString2)))) {
			UserMessages localUserMessages = new UserMessages("401",
					"Access Deny", " can not edit this object belongs to "
							+ paramString2);
			str = handleException(paramHttpServletRequest, localUserMessages);
		}
		return str;
	}

	protected String checkLogin(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString) {
		if (paramString == null)
			return PagePathCalculator.calculateTilesPath(
					paramHttpServletRequest, paramHttpServletResponse,
					"login.", null);
		return null;
	}

	protected void checkNullable(String paramString, Object paramObject) {
		if (paramObject == null)
			throw new BusinessException(paramString + "  is non nullable");
	}

	protected Object getSessionAttribute(
			HttpServletRequest paramHttpServletRequest, String paramString) {
		Object localObject = null;
		HttpSession localHttpSession = paramHttpServletRequest
				.getSession(false);
		if (localHttpSession != null)
			localObject = localHttpSession.getAttribute(paramString);
		return localObject;
	}

	protected void setSessionAttribute(
			HttpServletRequest paramHttpServletRequest, String paramString,
			Object paramObject) {
		HttpSession localHttpSession = paramHttpServletRequest
				.getSession(false);
		if (localHttpSession != null)
			localHttpSession.setAttribute(paramString, paramObject);
	}

	protected String redirectToInstallPage(
			HttpServletRequest paramHttpServletRequest) {
		String str = PropertiesUtil.getProperties("config/global.properties",
				ConfigPropertiesEnum.LEGENDSHOP_VERSION.name());
		UserMessages localUserMessages = new UserMessages();
		localUserMessages.setTitle("系统还没有安装成功");
		localUserMessages
				.setDesc("System will be available until install operation is finished!");
		localUserMessages.setCode("901");
		localUserMessages.addCallBackList("安装系统", "LegendShop " + str,
				"install");
		paramHttpServletRequest.setAttribute(UserMessages.MESSAGE_KEY,
				localUserMessages);
		return "redirect:plugins/install/index.jsp";
	}
}