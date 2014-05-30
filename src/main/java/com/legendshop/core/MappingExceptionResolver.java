package com.legendshop.core;

import com.legendshop.core.exception.BaseException;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.core.model.UserMessages;
import com.legendshop.util.AppUtils;
import java.sql.BatchUpdateException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class MappingExceptionResolver extends SimpleMappingExceptionResolver {
	private Map<String, String> _$1 = new HashMap();

	protected ModelAndView doResolveException(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject,
			Exception paramException) {
		String str = determineViewName(paramException, paramHttpServletRequest);
		if (str != null) {
			Integer localInteger = determineStatusCode(paramHttpServletRequest,
					str);
			if (localInteger != null)
				applyStatusCodeIfPossible(paramHttpServletRequest,
						paramHttpServletResponse, localInteger.intValue());
			this.logger.error("Error : ", paramException);
			paramHttpServletRequest.setAttribute("ERROR_MESSAGE",
					_$1(paramException));
			_$1(paramHttpServletRequest, paramException);
			return getModelAndView(str, paramException, paramHttpServletRequest);
		}
		return null;
	}

	private String _$1(Exception paramException) {
		Object localObject = paramException;
		StringBuffer localStringBuffer = new StringBuffer(
				((Throwable) localObject).toString()).append("\n");
		_$1(paramException, localStringBuffer);
		while (((Throwable) localObject).getCause() != null) {
			localObject = ((Throwable) localObject).getCause();
			localStringBuffer.append(((Throwable) localObject).toString())
					.append("\n");
			_$1((Throwable) localObject, localStringBuffer);
		}
		return ((String) localStringBuffer.toString());
	}

	private void _$1(Throwable paramThrowable, StringBuffer paramStringBuffer) {
		StackTraceElement[] arrayOfStackTraceElement1 = paramThrowable
				.getStackTrace();
		if (AppUtils.isNotBlank(arrayOfStackTraceElement1)) {
			StackTraceElement[] arrayOfStackTraceElement2 = arrayOfStackTraceElement1;
			int i = arrayOfStackTraceElement2.length;
			for (int j = 0; j < i; ++j) {
				StackTraceElement localStackTraceElement = arrayOfStackTraceElement2[j];
				paramStringBuffer.append(localStackTraceElement.toString())
						.append("\n");
			}
		}
	}

	private void _$1(HttpServletRequest paramHttpServletRequest,
			Exception paramException) {
		Object localObject;
		UserMessages localUserMessages = new UserMessages();
		if (paramException instanceof BaseException) {
			localObject = (BaseException) paramException;
			localUserMessages.setDesc(((BaseException) localObject).getDesc());
			localUserMessages
					.setTitle(((BaseException) localObject).getTitle());
			localUserMessages.setCode(((BaseException) localObject)
					.getErrorCode());
		} else if ((paramException != null)
				&& (this._$1.get(paramException.getClass().getName()) != null)) {
			localObject = (String) this._$1.get(paramException.getClass()
					.getName());
			getExceptionMessage(localUserMessages, paramException,
					(String) localObject);
		}
		paramHttpServletRequest.setAttribute(UserMessages.MESSAGE_KEY,
				localUserMessages);
	}

	public void getExceptionMessage(UserMessages paramUserMessages,
			Exception paramException, String paramString) {
		Object localObject = paramException;
		if (((Throwable) localObject).getCause() != null)
			localObject = ((Throwable) localObject).getCause();
		String str1 = ResourceBundleHelper.getString("error.code."
				+ paramString);
		if (AppUtils.isNotBlank(str1)) {
			paramUserMessages.setTitle(str1);
		} else {
			String str2 = null;
			if ((localObject instanceof ConstraintViolationException)
					|| (localObject instanceof BatchUpdateException)
					|| (localObject instanceof DataIntegrityViolationException))
				str2 = "409";
			else if (localObject instanceof NullPointerException)
				str2 = "404";
			paramUserMessages.setTitle(ResourceBundleHelper
					.getString("error.code." + str2));
		}
		paramUserMessages.setDesc(((Throwable) localObject).getMessage());
	}

	public void setExceptionCodeMappings(Map<String, String> paramMap) {
		this._$1 = paramMap;
	}
}