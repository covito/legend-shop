package com.legendshop.central.license;

import com.legendshop.core.exception.PermissionException;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpClientLicenseHelper {
	private final String _$1;

	public HttpClientLicenseHelper() {
		//this._$1 = "http://www.legendesign.net/license/query";
		this._$1 = "http://wwww";
	}

	public HttpClientLicenseHelper(String paramString) {
		this._$1 = paramString + "/license/query";
	}

	public String postMethod(String paramString) {
		String str = null;
		HttpClient localHttpClient = new HttpClient();
		PostMethod localPostMethod = new PostMethod(this._$1);
		NameValuePair[] arrayOfNameValuePair = new NameValuePair[1];
		arrayOfNameValuePair[0] = new NameValuePair("_ENTITY", paramString);
		localPostMethod.addParameters(arrayOfNameValuePair);
		try {
			localHttpClient.executeMethod(localPostMethod);
			str = localPostMethod.getResponseBodyAsString();
		} catch (Exception localException) {
		} finally {
			localPostMethod.releaseConnection();
		}
		return str;
	}
}