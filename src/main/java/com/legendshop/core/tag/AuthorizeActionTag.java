package com.legendshop.core.tag;

import com.legendshop.core.UserManager;
import com.legendshop.core.security.GrantedFunction;
import com.legendshop.core.security.GrantedFunctionImpl;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.util.ExpressionEvaluationUtils;

public class AuthorizeActionTag extends TagSupport {
	private String _$3 = "";
	private String _$2 = "";
	private String _$1 = "";

	public void setIfAllGranted(String paramString) throws JspException {
		this._$3 = paramString;
	}

	public String getIfAllGranted() {
		return this._$3;
	}

	public void setIfAnyGranted(String paramString) throws JspException {
		this._$2 = paramString;
	}

	public String getIfAnyGranted() {
		return this._$2;
	}

	public void setIfNotGranted(String paramString) throws JspException {
		this._$1 = paramString;
	}

	public String getIfNotGranted() {
		return this._$1;
	}

	public int doStartTag() throws JspException {
		if ((((null == this._$3) || ("".equals(this._$3))))
				&& (((null == this._$2) || ("".equals(this._$2))))
				&& (((null == this._$1) || ("".equals(this._$1)))))
			return 0;
		String str1 = ExpressionEvaluationUtils.evaluateString("ifNotGranted",
				this._$1, this.pageContext);
		Collection localCollection = UserManager
				.getPrincipalFunctionByAuthorities(this.pageContext
						.getSession());
		if ((null != str1) && (!("".equals(str1)))) {
			Set localObject = _$1(localCollection, _$1(str1));
			if (!(((Set) localObject).isEmpty()))
				return 0;
		}
		Object localObject = ExpressionEvaluationUtils.evaluateString(
				"ifAllGranted", this._$3, this.pageContext);
		if ((null != localObject) && (!("".equals(localObject)))
				&& (!(localCollection.containsAll(_$1((String) localObject)))))
			return 0;
		String str2 = ExpressionEvaluationUtils.evaluateString("ifAnyGranted",
				this._$2, this.pageContext);
		if ((null != str2) && (!("".equals(str2)))) {
			Set localSet = _$1(localCollection, _$1(str2));
			if (localSet.isEmpty())
				return 0;
		}
		return 1;
	}

	private Set _$1(Collection paramCollection) {
		HashSet localHashSet = new HashSet();
		Iterator localIterator = paramCollection.iterator();
		while (localIterator.hasNext()) {
			GrantedFunction localGrantedFunction = (GrantedFunction) localIterator
					.next();
			if (null == localGrantedFunction.getFunction())
				throw new IllegalArgumentException(
						"Cannot process GrantedFunction objects which return null from getFunction() - attempting to process "
								+ localGrantedFunction.toString());
			localHashSet.add(localGrantedFunction.getFunction());
		}
		return localHashSet;
	}

	private Set _$1(String paramString) {
		HashSet localHashSet = new HashSet();
		String[] arrayOfString1 = StringUtils
				.commaDelimitedListToStringArray(paramString);
		String[] arrayOfString2 = arrayOfString1;
		int i = arrayOfString2.length;
		for (int j = 0; j < i; ++j) {
			String str1 = arrayOfString2[j];
			String str2 = StringUtils.replace(str1, " ", "");
			str2 = StringUtils.replace(str2, "\t", "");
			str2 = StringUtils.replace(str2, "\r", "");
			str2 = StringUtils.replace(str2, "\n", "");
			str2 = StringUtils.replace(str2, "\f", "");
			localHashSet.add(new GrantedFunctionImpl(str2));
		}
		return localHashSet;
	}

	private Set _$1(Collection paramCollection, Set paramSet) {
		Set localSet1 = _$1(paramCollection);
		Set localSet2 = _$1(paramSet);
		localSet1.retainAll(localSet2);
		return _$1(localSet1, paramCollection);
	}

	private Set _$1(Set paramSet, Collection paramCollection) {
		HashSet localHashSet = new HashSet();
		Iterator localIterator1 = paramSet.iterator();
		while (localIterator1.hasNext()) {
			String str = (String) localIterator1.next();
			Iterator localIterator2 = paramCollection.iterator();
			while (localIterator2.hasNext()) {
				GrantedFunction localGrantedFunction = (GrantedFunction) localIterator2
						.next();
				if (localGrantedFunction.getFunction().equals(str)) {
					localHashSet.add(localGrantedFunction);
					break;
				}
			}
		}
		return localHashSet;
	}
}