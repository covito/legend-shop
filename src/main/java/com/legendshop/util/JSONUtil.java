package com.legendshop.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {
	public static String getJson(Object paramObject) {
		JSONObject localJSONObject = JSONObject.fromObject(paramObject);
		return localJSONObject.toString();
	}

	public static String getJson(List<?> paramList) {
		JSONArray localJSONArray = JSONArray.fromObject(paramList);
		return localJSONArray.toString();
	}

	public static String getJson(Object[] paramArrayOfObject) {
		JSONArray localJSONArray = JSONArray.fromObject(paramArrayOfObject);
		return localJSONArray.toString();
	}

	public static Map getMap(String paramString) {
		return JSONObject.fromObject(paramString);
	}

	public static <T> T getObject(String paramString, Class<T> paramClass) {
		JSONObject localJSONObject = JSONObject.fromObject(paramString);
		return (T) JSONObject.toBean(localJSONObject, paramClass);
	}

	public static <T> List<T> getArray(String paramString, Class<T> paramClass) {
		JSONArray json = JSONArray.fromObject(paramString);
		Object[] arrayOfObject = (Object[]) JSONArray.toArray(json, paramClass);
		return (List<T>) Arrays.asList(arrayOfObject);
	}

	public static Object[] getArray(String paramString) {
		JSONArray localJSONArray = JSONArray.fromObject(paramString);
		return localJSONArray.toArray();
	}
}