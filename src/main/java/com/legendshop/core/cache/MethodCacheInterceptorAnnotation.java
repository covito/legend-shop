package com.legendshop.core.cache;

import com.legendshop.util.DateUtil;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component("methodCacheInterceptor")
public class MethodCacheInterceptorAnnotation implements MethodInterceptor {
	private static transient Log logger = LogFactory
			.getLog(MethodCacheInterceptor.class);
	private Cache cache;

	public void setMethodCache(Cache cache) {
		this.cache = cache;
	}

	public Object invoke(MethodInvocation methodInv) throws Throwable {
		String str1 = methodInv.getThis().getClass().getInterfaces()[0]
				.getName();
		String str2 = methodInv.getMethod().getName();
		Object[] arrayOfObject = methodInv.getArguments();
		Class[] arrayOfClass1 = new Class[arrayOfObject.length];
		for (int i = 0; i < arrayOfObject.length; ++i)
			arrayOfClass1[i] = arrayOfObject[i].getClass();
		if (methodInv.getThis().getClass().getCanonicalName()
				.contains("$Proxy")) {
			if (logger.isWarnEnabled())
				logger.warn("----- The object has been proxyed and method cache interceptor can't get the target, so the method result can't be cached which name is ------"
						+ str2);
			return methodInv.proceed();
		}
		if (methodInv.getThis().getClass()
				.isAnnotationPresent(ObjectCache.class)) {
			ObjectCache localObject1 = (ObjectCache) methodInv.getThis()
					.getClass().getAnnotation(ObjectCache.class);
			return _$1(str1, str2, arrayOfObject, methodInv,
					((ObjectCache) localObject1).expire());
		}
		Method[] localObject1 = methodInv.getThis().getClass().getMethods();
		Method localObject2 = null;
		int j = localObject1.length;
		for (int k = 0; k < j; ++k) {
			Method localObject4 = localObject1[k];
			if (localObject4.getName().equals(str2)) {
				int l = 1;
				Class[] arrayOfClass2 = localObject4.getParameterTypes();
				if (arrayOfClass2.length != arrayOfClass1.length) {
					l = 0;
				} else {
					for (int i1 = 0; i1 < arrayOfClass1.length; ++i1)
						if (!(arrayOfClass1[i1].equals(arrayOfClass2[i1]))) {
							l = 0;
							break;
						}
					if (l != 0) {
						localObject2 = localObject4;
						break;
					}
				}
			}
		}
		if ((localObject2 != null)
				&& (localObject2.isAnnotationPresent(MethodCache.class))) {
			MethodCache mc = (MethodCache) localObject2
					.getAnnotation(MethodCache.class);
			return _$1(str1, str2, arrayOfObject, methodInv, mc.expire());
		}
		return methodInv.proceed();
	}

	private Object _$1(String paramString1, String paramString2,
			Object[] paramArrayOfObject,
			MethodInvocation paramMethodInvocation, int paramInt)
			throws Throwable {
		String str = _$1(paramString1, paramString2, paramArrayOfObject);
		Element localElement = this.cache.get(str);
		if (localElement == null)
			synchronized (this) {
				localElement = this.cache.get(str);
				if (localElement == null) {
					Object localObject1 = paramMethodInvocation.proceed();
					localElement = new Element(str, (Serializable) localObject1);
					if (paramInt > 0) {
						localElement.setTimeToIdle(paramInt);
						localElement.setTimeToLive(paramInt);
					}
					this.cache.put(localElement);
				}
			}
		return localElement.getValue();
	}

	private String _$1(String paramString1, String paramString2,
			Object[] paramArrayOfObject) {
		StringBuffer localStringBuffer = new StringBuffer();
		localStringBuffer.append(paramString1).append("#").append(paramString2);
		if ((paramArrayOfObject != null) && (paramArrayOfObject.length != 0))
			for (int i = 0; i < paramArrayOfObject.length; ++i)
				if (paramArrayOfObject[i] instanceof Date)
					localStringBuffer.append("#").append(
							DateUtil.DateToString((Date) paramArrayOfObject[i],
									"yyyy-MM-dd:HH:mm:ss"));
				else
					localStringBuffer.append("#").append(paramArrayOfObject[i]);
		return localStringBuffer.toString();
	}
}