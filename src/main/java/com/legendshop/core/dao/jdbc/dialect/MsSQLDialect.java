package com.legendshop.core.dao.jdbc.dialect;

public class MsSQLDialect implements Dialect {
	private static final String _$3 = "select";
	private static final String _$2 = "from";
	private static final String _$1 = "distinct";

	public String getLimitString(String paramString, int paramInt1,
			int paramInt2) {
		if (paramInt1 > 0)
			throw new UnsupportedOperationException(
					"query result offset is not supported");
		return new StringBuilder(paramString.length() + 8).append(paramString)
				.insert(_$1(paramString), " top " + paramInt2).toString();
	}

	private int _$1(String paramString) {
		int i = paramString.toLowerCase().indexOf("select");
		int j = paramString.toLowerCase().indexOf("select distinct");
		return (i + ((j == i) ? 15 : 6));
	}

	public String getLimitString(String paramString, boolean paramBoolean) {
		StringBuilder localStringBuilder = new StringBuilder(paramString.trim()
				.toLowerCase());
		int i = localStringBuilder.indexOf("order by");
		CharSequence str = (i > 0) ? localStringBuilder.subSequence(i,
				localStringBuilder.length()) : "ORDER BY CURRENT_TIMESTAMP";
		if (i > 0)
			localStringBuilder.delete(i, i + str.length());
		replaceDistinctWithGroupBy(localStringBuilder);
		insertRowNumberFunction(localStringBuilder, str);
		localStringBuilder.insert(0, "WITH query AS (").append(
				") SELECT * FROM query ");
		if (paramBoolean)
			localStringBuilder
					.append("WHERE __hibernate_row_nr__ BETWEEN ? AND ?");
		else
			localStringBuilder.append("WHERE __hibernate_row_nr__  <= ?");
		return localStringBuilder.toString();
	}

	protected static void replaceDistinctWithGroupBy(
			StringBuilder paramStringBuilder) {
		int i = paramStringBuilder.indexOf("distinct");
		if (i > 0) {
			paramStringBuilder.delete(i, i + "distinct".length() + 1);
			paramStringBuilder.append(" group by").append(
					getSelectFieldsWithoutAliases(paramStringBuilder));
		}
	}

	protected static void insertRowNumberFunction(
			StringBuilder paramStringBuilder, CharSequence paramCharSequence) {
		int i = paramStringBuilder.indexOf("select") + "select".length();
		paramStringBuilder.insert(i, " ROW_NUMBER() OVER (" + paramCharSequence
				+ ") as __hibernate_row_nr__,");
	}

	protected static CharSequence getSelectFieldsWithoutAliases(
			StringBuilder paramStringBuilder) {
		String str = paramStringBuilder.substring(
				paramStringBuilder.indexOf("select") + "select".length(),
				paramStringBuilder.indexOf("from"));
		return stripAliases(str);
	}

	protected static String stripAliases(String paramString) {
		return paramString.replaceAll("\\sas[^,]+(,?)", "$1");
	}
}