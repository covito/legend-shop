package com.legendshop.core.helper;

import com.legendshop.core.UserManager;
import com.legendshop.core.constant.FunctionEnum;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.AbstractQuery;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.util.AppUtils;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.displaytag.util.ParamEncoder;

public class FoundationUtil {
	public static boolean haveViewAllDataFunction(
			HttpServletRequest paramHttpServletRequest) {
		return UserManager.hasFunction(paramHttpServletRequest,
				FunctionEnum.FUNCTION_VIEW_ALL_DATA.value());
	}

	public static boolean isDataSortByExternal(
			CriteriaQuery paramCriteriaQuery,
			HttpServletRequest paramHttpServletRequest) {
		String str1 = paramHttpServletRequest.getParameter(new ParamEncoder(
				"item").encodeParameterName("s"));
		String str2 = paramHttpServletRequest.getParameter(new ParamEncoder(
				"item").encodeParameterName("o"));
		if (AppUtils.isNotBlank(str1)) {
			if ("1".equals(str2))
				paramCriteriaQuery.addOrder("desc", str1);
			else
				paramCriteriaQuery.addOrder("asc", str1);
			return true;
		}
		return false;
	}

	public static boolean isDataSortByExternal(SqlQuery paramSqlQuery,
			HttpServletRequest paramHttpServletRequest,
			Map<String, Object> paramMap) {
		String str1 = paramHttpServletRequest.getParameter(new ParamEncoder(
				"item").encodeParameterName("s"));
		String str2 = paramHttpServletRequest.getParameter(new ParamEncoder(
				"item").encodeParameterName("o"));
		if (AppUtils.isNotBlank(str1)) {
			if ("1".equals(str2))
				paramMap.put("orderIndicator", "order by " + str1 + " desc");
			else
				paramMap.put("orderIndicator", "order by " + str1 + " asc");
			return true;
		}
		return false;
	}

	public static boolean isDataSortByExternal(HqlQuery paramHqlQuery,
			HttpServletRequest paramHttpServletRequest,
			Map<String, Object> paramMap) {
		String str1 = paramHttpServletRequest.getParameter(new ParamEncoder(
				"item").encodeParameterName("s"));
		String str2 = paramHttpServletRequest.getParameter(new ParamEncoder(
				"item").encodeParameterName("o"));
		if (AppUtils.isNotBlank(str1)) {
			if ("1".equals(str2))
				paramMap.put("orderIndicator", "order by " + str1 + " desc");
			else
				paramMap.put("orderIndicator", "order by " + str1 + " asc");
			return true;
		}
		return false;
	}

	public static boolean isDataForExport(AbstractQuery paramAbstractQuery,
			HttpServletRequest paramHttpServletRequest) {
		String str = paramHttpServletRequest.getParameter("6578706f7274");
		if (AppUtils.isNotBlank(str)) {
			paramAbstractQuery.setPageSize(((Integer) PropertiesUtil.getObject(
					SysParameterEnum.EXPORT_SIZE, Integer.class)).intValue());
			return true;
		}
		return false;
	}

	public static boolean isDataForExport(SqlQuery paramSqlQuery,
			HttpServletRequest paramHttpServletRequest) {
		String str = paramHttpServletRequest.getParameter("6578706f7274");
		if (AppUtils.isNotBlank(str)) {
			paramSqlQuery.setPageSize(((Integer) PropertiesUtil.getObject(
					SysParameterEnum.EXPORT_SIZE, Integer.class)).intValue());
			return true;
		}
		return false;
	}

	public static boolean isDataForExport(HqlQuery paramHqlQuery,
			HttpServletRequest paramHttpServletRequest) {
		String str = paramHttpServletRequest.getParameter("6578706f7274");
		if (AppUtils.isNotBlank(str)) {
			paramHqlQuery.setPageSize(((Integer) PropertiesUtil.getObject(
					SysParameterEnum.EXPORT_SIZE, Integer.class)).intValue());
			return true;
		}
		return false;
	}

	public static boolean needToValidation(HttpSession paramHttpSession) {
		if (paramHttpSession == null)
			return false;
		Integer localInteger = (Integer) paramHttpSession
				.getAttribute("TRY_LOGIN_COUNT");
		Boolean localBoolean1 = Boolean.valueOf(true);
		if ((localInteger == null) || (localInteger.intValue() < 3))
			localBoolean1 = Boolean.valueOf(false);
		Boolean localBoolean2 = PropertiesUtil
				.getBooleanObject(SysParameterEnum.VALIDATION_IMAGE.name());
		return ((localBoolean2 != null) && (localBoolean2.booleanValue()) && (localBoolean1
				.booleanValue()));
	}
}