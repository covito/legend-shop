package com.legendshop.command.framework.servicerepository;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

public class ServiceLocator {
	private static ServiceLocator _$7 = null;
	private static Logger _$6 = Logger.getLogger(ServiceLocator.class);
	private Map _$5 = new HashMap();
	private Map _$4 = null;
	private Map _$3 = new HashMap();
	private Map _$2 = new HashMap();
	private boolean _$1 = false;

	public static synchronized ServiceLocator getInstance() {
		if (_$7 == null)
			_$7 = new ServiceLocator();
		return _$7;
	}

	protected synchronized Context getContext(String paramString) {
		Context localObject = (Context) getInstance()._$5.get(paramString);
		if (localObject != null)
			return localObject;
		Map localMap = (Map) getInstance()._$4.get(paramString);
		if (localMap == null) {
			_$6.debug("can't find specified env");
			return null;
		}
		Hashtable localHashtable = new Hashtable();
		localHashtable.put("java.naming.provider.url", localMap.get("URL"));
		localHashtable.put("java.naming.factory.initial",
				localMap.get("Factory"));
		String str1 = (String) localMap.get("User");
		String str2 = (String) localMap.get("Password");
		if ((str1 != null) && (str2 != null)) {
			localHashtable.put("java.naming.security.principal", str1);
			localHashtable.put("java.naming.security.credentials", str2);
		}
		try {
			localObject = new InitialContext(localHashtable);
			getInstance()._$5.put(paramString, localObject);
			return localObject;
		} catch (NamingException localNamingException) {
			_$6.debug("init context fail:" + paramString, localNamingException);
		}
		return ((Context) null);
	}

	public synchronized boolean init(String paramString) {
		if (this._$1)
			return this._$1;
		ServiceConfig localServiceConfig = new ServiceConfig();
		this._$1 = localServiceConfig.config(paramString);
		if (!(this._$1))
			return false;
		this._$4 = localServiceConfig.getEnvs();
		if ((this._$4 == null) || (this._$4.size() <= 0)) {
			_$6.error("no env specified in the config file");
			this._$1 = false;
			return false;
		}
		Map localMap = localServiceConfig.getEJBMetaData();
		if (localMap != null)
			this._$3.putAll(localMap);
		localMap = localServiceConfig.getJMSMetaData();
		if (localMap != null)
			this._$3.putAll(localMap);
		localMap = localServiceConfig.getObjMetaData();
		if (localMap != null)
			this._$3.putAll(localMap);
		localMap = localServiceConfig.getSpringApplicationContext();
		if (localMap != null)
			this._$2.putAll(localMap);
		this._$1 = true;
		return true;
	}

	public IMetaData getMetaData(String paramString) {
		IMetaData localIMetaData = (IMetaData) this._$3.get(paramString);
		return localIMetaData;
	}

	public List getSpringApplicationContext(String paramString) {
		return ((List) this._$2.get(paramString));
	}

	public boolean getState() {
		return this._$1;
	}

	public Object get(String paramString1, String paramString2) {
		IMetaData localIMetaData = (IMetaData) this._$3.get(paramString1);
		if (localIMetaData == null)
			_$6.error("can't find the object with name " + paramString1);
		return localIMetaData.get(paramString2);
	}

	public Object getOne(String paramString) {
		IMetaData localIMetaData = (IMetaData) this._$3.get(paramString);
		if (localIMetaData == null)
			_$6.error("can't find the object with name " + paramString);
		return localIMetaData.getOne();
	}

	public Map get(String paramString) {
		IMetaData localIMetaData = (IMetaData) this._$3.get(paramString);
		if (localIMetaData == null)
			_$6.error("can't find the object with name " + paramString);
		return localIMetaData.get();
	}
}