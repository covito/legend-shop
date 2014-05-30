package com.legendshop.core.tag;

import com.legendshop.core.Selectable;
import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ServiceLocatorIF;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.LocaleResolver;

public class OptionGroupTag extends TagSupport {
	private static Log _$28 = LogFactory.getLog(OptionGroupTag.class);
	private static final String _$27 = "radio";
	private static final String _$26 = "label";
	private static final String _$25 = "checkbox";
	private static final String _$24 = "json";
	private static final String _$23 = "select";
	private int _$22 = 0;
	private int _$21 = 0;
	private String _$20;
	private String _$19;
	private String _$18;
	private boolean _$17;
	private int _$16 = 0;
	private boolean _$15;
	private String _$14;
	private String _$13;
	private String _$12;
	private String _$11;
	private String _$10;
	private Object _$9;
	private Object[] _$8;
	private String _$7 = "-- 请选择 --";
	private boolean _$6 = false;
	private String[] _$5;
	private String _$4;
	private String[] _$3;
	private static final long serialVersionUID = -518291441012821104L;
	private final BaseDao _$2 = (BaseDao) ContextServiceLocator.getInstance()
			.getBean("tagLibDao");
	private final LocaleResolver _$1 = (LocaleResolver) ContextServiceLocator
			.getInstance().getBean("localeResolver");

	public int doStartTag() {
		if ((!("select".equalsIgnoreCase(this._$19)))
				&& (!("label".equalsIgnoreCase(this._$19)))
				&& (!("radio".equalsIgnoreCase(this._$19)))
				&& (!("checkbox".equalsIgnoreCase(this._$19)))
				&& (!("json".equalsIgnoreCase(this._$19)))) {
			_$28.error("type \"" + this._$19 + "\" is unknown!");
			return 0;
		}
		this._$22 = 0;
		this._$21 = 0;
		StringBuilder localStringBuilder = new StringBuilder();
		if (!(this._$17))
			if ("select".equalsIgnoreCase(this._$19))
				localStringBuilder.append("<option value=\"\">")
						.append(this._$7).append("</option> ");
			else if ("json".equalsIgnoreCase(this._$19))
				localStringBuilder.append("{'value':'', 'text':'")
						.append(this._$7).append("'},");
		if (("radio".equalsIgnoreCase(this._$19))
				|| ("checkbox".equalsIgnoreCase(this._$19))) {
			if ((this._$20 == null) || (this._$20.trim().length() == 0)) {
				_$28.error("name is required for radio group!");
				return 0;
			}
			if ("checkbox".equalsIgnoreCase(this._$19))
				if (StringUtils.isNotEmpty(this._$18))
					this._$5 = this._$18.split(",");
				else
					this._$5 = null;
		}
		if (StringUtils.isNotEmpty(this._$4))
			this._$3 = this._$4.split(",");
		else
			this._$3 = null;
		if (this._$15) {
			if ((AppUtils.isBlank(this._$13)) && (AppUtils.isBlank(this._$14))) {
				_$28.error("beanName is required in cache mode!");
				return 0;
			}
			_$1(localStringBuilder);
		} else {
			List localList = null;
			if (this._$10 != null) {
				localList = this._$2.findBySQL(this._$10);
			} else {
				if ((this._$11 == null) || (this._$11.trim().length() == 0)) {
					if ((this._$14 == null) || (this._$14.trim().length() == 0)
							|| (this._$13 == null)
							|| (this._$13.trim().length() == 0)
							|| (this._$12 == null)
							|| (this._$12.trim().length() == 0)) {
						_$28.error("beanId and beanDisp is requied if data is not cached!");
						return 0;
					}
					this._$11 = "select t." + this._$13 + ", t." + this._$12
							+ " from " + this._$14 + " t order by t."
							+ this._$13;
				}
				try {
					if (this._$8 != null)
						localList = this._$2.findByHQL(this._$11, this._$8);
					else if (this._$9 != null)
						localList = this._$2.findByHQL(this._$11,
								new Object[] { this._$9 });
					else
						localList = this._$2
								.findByHQL(this._$11, new Object[0]);
				} catch (Exception localException) {
					_$28.error("get data throgh hql " + this._$11,
							localException);
				}
			}
			if (localList != null) {
				if (((("radio".equalsIgnoreCase(this._$19)) || ("checkbox"
						.equalsIgnoreCase(this._$19)))) && (this._$16 == 0))
					_$1(localList.size());
				Iterator localIterator = localList.iterator();
				while (localIterator.hasNext()) {
					Object[] arrayOfObject = (Object[]) localIterator.next();
					int i = 0;
					if (!(AppUtils.isBlank(this._$3))) {
						String[] arrayOfString = this._$3;
						int j = arrayOfString.length;
						for (int k = 0; k < j; ++k) {
							String str = arrayOfString[k];
							if (str.equals(String.valueOf(arrayOfObject[0]))) {
								i = 1;
								break;
							}
						}
					}
					if (i == 0)
						_$1(localStringBuilder, arrayOfObject[0],
								arrayOfObject[1]);
				}
			}
		}
		try {
			if (localStringBuilder.length() != 0)
				this.pageContext.getOut().println(
						localStringBuilder.substring(0,
								localStringBuilder.length() - 1));
			if (("label".equalsIgnoreCase(this._$19))
					&& (localStringBuilder.length() == 0) && (this._$7 != null))
				this.pageContext.getOut().println(this._$7);
		} catch (IOException localIOException) {
			_$28.error("IOException in SelectTag: ", localIOException);
		}
		return 0;
	}

	private void _$1(StringBuilder paramStringBuilder) {
		Object localObject2;
		Object localObject3;
		Object localObject1 = null;
		if (AppUtils.isNotBlank(this._$13)) {
			localObject2 = (Map) ContextServiceLocator.getInstance().getBean(
					this._$13);
			if (AppUtils.isNotBlank((Map) localObject2)) {
				localObject1 = new HashMap(((Map) localObject2).size());
				localObject3 = ((Map) localObject2).keySet().iterator();
				while (((Iterator) localObject3).hasNext()) {
					String str1 = (String) ((Iterator) localObject3).next();
					((Map) localObject1).put(str1,
							((Selectable) ((Map) localObject2).get(str1))
									.getName());
				}
			}
		} else if (AppUtils.isNotBlank(this._$14)) {
			localObject2 = (TableCache) ContextServiceLocator.getInstance()
					.getBean("codeTablesCache");
			localObject1 = ((TableCache) localObject2).getCodeTable(this._$14);
		}
		if (localObject1 != null) {
			if (((("radio".equalsIgnoreCase(this._$19)) || ("checkbox"
					.equalsIgnoreCase(this._$19)))) && (this._$16 == 0))
				_$1(((Map) localObject1).size());
			localObject2 = ((Map) localObject1).entrySet().iterator();
			while (((Iterator) localObject2).hasNext()) {
				localObject3 = (Map.Entry) ((Iterator) localObject2).next();
				int i = 0;
				if (!(AppUtils.isBlank(this._$3))) {
					String[] arrayOfString = this._$3;
					int j = arrayOfString.length;
					for (int k = 0; k < j; ++k) {
						String str2 = arrayOfString[k];
						if (str2.equals(String
								.valueOf(((Map.Entry) localObject3).getKey()))) {
							i = 1;
							break;
						}
					}
				}
				if (i == 0)
					_$1(paramStringBuilder,
							((Map.Entry) localObject3).getKey(),
							((Map.Entry) localObject3).getValue());
			}
		}
	}

	private void _$1(StringBuilder paramStringBuilder, Object paramObject1,
			Object paramObject2) {
		String str2;
		String str1 = (String) paramObject2;
		if ((str1 != null) && (str1.startsWith("message:"))) {
			str2 = str1.substring("message:".length());
			Locale localObject1 = this._$1
					.resolveLocale((HttpServletRequest) this.pageContext
							.getRequest());
			if (localObject1 != null)
				paramObject2 = ResourceBundleHelper.getString(
						(Locale) localObject1, String.valueOf(str2),
						String.valueOf(str2));
			else
				paramObject2 = ResourceBundleHelper.getString(
						String.valueOf(str2), String.valueOf(str2));
		}
		if ("select".equalsIgnoreCase(this._$19)) {
			paramStringBuilder.append("<option value=\"").append(paramObject1)
					.append("\"");
			if (String.valueOf(paramObject1).equals(this._$18))
				paramStringBuilder.append(" selected ");
			paramStringBuilder.append(">");
			paramStringBuilder.append(paramObject2);
			paramStringBuilder.append("</option> ");
		} else if (("radio".equalsIgnoreCase(this._$19))
				|| ("checkbox".equalsIgnoreCase(this._$19))) {
			this._$21 += 1;
			if (this._$21 > this._$16) {
				this._$21 = 0;
				paramStringBuilder.append("<br>");
			}
			str2 = this._$20 + (this._$22++);
			paramStringBuilder
					.append("<span style=\"cursor:hand\" onclick=\"$('")
					.append(str2)
					.append("').click();\">&nbsp;<input type=\"" + this._$19
							+ "\" value=\"").append(paramObject1)
					.append("\" name=\"").append(this._$20).append("\" id=\"")
					.append(str2).append("\"");
			if ("radio".equalsIgnoreCase(this._$19)) {
				if (String.valueOf(paramObject1).equals(this._$18))
					paramStringBuilder.append(" checked ");
			} else if (!(AppUtils.isBlank(this._$5))) {
				String[] localObject1 = this._$5;
				int i = localObject1.length;
				for (int j = 0; j < i; ++j) {
					Object localObject2 = localObject1[j];
					if (String.valueOf(paramObject1).equals(localObject2)) {
						paramStringBuilder.append(" checked ");
						break;
					}
				}
			}
			if (this._$6)
				paramStringBuilder.append(" disabled ");
			paramStringBuilder.append("/>").append(paramObject2)
					.append("&nbsp;</span> ");
		} else if ("json".equalsIgnoreCase(this._$19)) {
			paramStringBuilder.append("{'value':'").append(paramObject1)
					.append("', 'text':'").append(paramObject2).append("'},");
		} else if (("label".equalsIgnoreCase(this._$19))
				&& (String.valueOf(paramObject1).equals(this._$18))) {
			paramStringBuilder.append(paramObject2).append(" ");
		}
	}

	private void _$1(int paramInt) {
		this._$16 = paramInt;
	}

	public String getSql() {
		return this._$10;
	}

	public void setSql(String paramString) {
		this._$10 = paramString;
	}

	public void setType(String paramString) {
		this._$19 = paramString;
	}

	public void setName(String paramString) {
		this._$20 = paramString;
	}

	public void setBeanName(String paramString) {
		this._$14 = paramString;
	}

	public void setSelectedValue(String paramString) {
		this._$18 = paramString;
	}

	public void setRequired(boolean paramBoolean) {
		this._$17 = paramBoolean;
	}

	public void setCols(int paramInt) {
		this._$16 = paramInt;
	}

	public void setBeanDisp(String paramString) {
		this._$12 = paramString;
	}

	public void setBeanId(String paramString) {
		this._$13 = paramString;
	}

	public void setCache(boolean paramBoolean) {
		this._$15 = paramBoolean;
	}

	public void setHql(String paramString) {
		this._$11 = paramString;
	}

	public void setParam(Object paramObject) {
		this._$9 = paramObject;
	}

	public void setParams(Object[] paramArrayOfObject) {
		this._$8 = paramArrayOfObject;
	}

	public void setDefaultDisp(String paramString) {
		this._$7 = paramString;
	}

	public void setDisabled(boolean paramBoolean) {
		this._$6 = paramBoolean;
	}

	public void setExclude(String paramString) {
		this._$4 = paramString;
	}
}