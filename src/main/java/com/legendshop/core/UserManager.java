package com.legendshop.core;

import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.security.GrantedFunction;
import com.legendshop.core.security.model.UserDetail;
import com.legendshop.util.AppUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class UserManager {
	public static String getUserName(HttpSession paramHttpSession) {
		UserDetail localUserDetail = getUser(paramHttpSession);
		if (localUserDetail != null)
			return localUserDetail.getUsername();
		return null;
	}

	public static String getUserName() {
		UserDetail localUserDetail = getUser(ThreadLocalContext.getRequest()
				.getSession());
		if (localUserDetail != null)
			return localUserDetail.getUsername();
		return null;
	}

	public static String getUserName(HttpServletRequest paramHttpServletRequest) {
		return getUserName(paramHttpServletRequest.getSession());
	}

	public static String getUserId(HttpSession paramHttpSession) {
		UserDetail localUserDetail = getUser(paramHttpSession);
		if (localUserDetail != null)
			return localUserDetail.getUserId();
		return null;
	}

	public static String getUserId() {
		UserDetail localUserDetail = getUser(ThreadLocalContext.getRequest()
				.getSession());
		if (localUserDetail != null)
			return localUserDetail.getUserId();
		return null;
	}

	public static String getUserId(HttpServletRequest paramHttpServletRequest) {
		if (paramHttpServletRequest == null)
			return null;
		return getUserId(paramHttpServletRequest.getSession());
	}

	public static UserDetail getUser(HttpSession paramHttpSession) {
		Authentication localAuthentication = getAuthentication(paramHttpSession);
		if ((localAuthentication != null)
				&& (localAuthentication.getPrincipal() instanceof UserDetail))
			return ((UserDetail) localAuthentication.getPrincipal());
		return null;
	}

	public static Authentication getAuthentication(HttpSession paramHttpSession) {
		Authentication localAuthentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if ((localAuthentication == null) && (paramHttpSession != null)) {
			SecurityContext localSecurityContext = (SecurityContext) paramHttpSession
					.getAttribute("SPRING_SECURITY_CONTEXT");
			if (localSecurityContext != null)
				localAuthentication = localSecurityContext.getAuthentication();
		}
		return localAuthentication;
	}

	public static String getPassword(HttpSession paramHttpSession) {
		UserDetail localUserDetail = getUser(paramHttpSession);
		if (localUserDetail != null)
			return localUserDetail.getPassword();
		return null;
	}

	public static String getSessionID() {
		SecurityContext localSecurityContext1 = SecurityContextHolder
				.getContext();
		if ((localSecurityContext1 != null)
				&& (localSecurityContext1 instanceof SecurityContext)) {
			SecurityContext localSecurityContext2 = localSecurityContext1;
			Authentication localAuthentication = localSecurityContext2
					.getAuthentication();
			if (localAuthentication != null) {
				Object localObject = localAuthentication.getDetails();
				if (localObject instanceof WebAuthenticationDetails)
					return ((WebAuthenticationDetails) localObject)
							.getSessionId();
				return null;
			}
		}
		return null;
	}

	public static Collection<GrantedFunction> getPrincipalFunctionByAuthorities(
			HttpSession paramHttpSession) {
		UserDetail localUserDetail = getUser(paramHttpSession);
		if (localUserDetail != null)
			return localUserDetail.getFunctions();
		return Collections.EMPTY_LIST;
	}

	public static boolean hasFunction(HttpSession paramHttpSession,
			String paramString) {
		Collection localCollection = getPrincipalFunctionByAuthorities(paramHttpSession);
		return _$1(localCollection, paramString);
	}

	public static boolean hasFunction(
			HttpServletRequest paramHttpServletRequest, String paramString) {
		return hasFunction(paramHttpServletRequest.getSession(), paramString);
	}

	public static boolean hasFunction(HttpSession paramHttpSession,
			String[] paramArrayOfString) {
		if (AppUtils.isBlank(paramArrayOfString))
			return false;
		Collection localCollection = getPrincipalFunctionByAuthorities(paramHttpSession);
		String[] arrayOfString = paramArrayOfString;
		int i = arrayOfString.length;
		for (int j = 0; j < i; ++j) {
			String str = arrayOfString[j];
			if (!(_$1(localCollection, str)))
				return false;
		}
		return true;
	}

	public static boolean hasFunction(
			HttpServletRequest paramHttpServletRequest,
			String[] paramArrayOfString) {
		return hasFunction(paramHttpServletRequest.getSession(),
				paramArrayOfString);
	}

	private static boolean _$1(Collection<GrantedFunction> paramCollection,
			String paramString) {
		boolean i = false;
		Iterator localIterator = paramCollection.iterator();
		while (localIterator.hasNext()) {
			GrantedFunction localGrantedFunction = (GrantedFunction) localIterator
					.next();
			if (localGrantedFunction.equals(paramString))
				return true;
		}
		return i;
	}
}