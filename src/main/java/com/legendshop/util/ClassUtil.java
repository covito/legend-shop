package com.legendshop.util;

import com.legendshop.annotation.RequestMapping;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

public class ClassUtil {
	private static final Log _$2 = LogFactory.getLog(ClassUtil.class);
	private static final Map<Class<?>, Class<?>> _$1 = new HashMap(8);
	public static final String CLASS_SUFFIX = ".class";
	public static final String FILE_URL_PREFIX = "file:";
	public static final String URL_PROTOCOL_FILE = "file";
	public static final String URL_PROTOCOL_JAR = "jar";
	public static final String URL_PROTOCOL_ZIP = "zip";
	public static final String URL_PROTOCOL_WSJAR = "wsjar";
	public static final String JAR_URL_SEPARATOR = "!/";

	public static boolean isPrimitiveOrWrapper(Class paramClass) {
		return ((paramClass.isPrimitive()) || (_$1.containsKey(paramClass)));
	}

	public static String getWapperClassShortName(Class paramClass) {
		String str = "";
		if (paramClass.isPrimitive())
			if (paramClass == Boolean.TYPE)
				str = "Boolean";
			else if (paramClass == Byte.TYPE)
				str = "Byte";
			else if (paramClass == Character.TYPE)
				str = "Character";
			else if (paramClass == Double.TYPE)
				str = "Double";
			else if (paramClass == Float.TYPE)
				str = "Float";
			else if (paramClass == Integer.TYPE)
				str = "Integer";
			else if (paramClass == Long.TYPE)
				str = "Long";
			else if (paramClass == Short.TYPE)
				str = "Short";
			else
				throw new RuntimeException("Primitive class is required ...");
		return str;
	}

	public static Object createArrayInstance(Class paramClass, int paramInt) {
		if (String.class == paramClass)
			return new String[paramInt];
		if (Integer.class == paramClass)
			return new Integer[paramInt];
		if (Long.class == paramClass)
			return new Long[paramInt];
		if (Short.class == paramClass)
			return new Short[paramInt];
		if (Byte.class == paramClass)
			return new Byte[paramInt];
		if (Boolean.class == paramClass)
			return new Boolean[paramInt];
		if (Double.class == paramClass)
			return new Double[paramInt];
		if (Float.class == paramClass)
			return new Float[paramInt];
		return null;
	}

	public static ClassLoader getDefaultClassLoader() {
		ClassLoader localClassLoader = null;
		try {
			localClassLoader = Thread.currentThread().getContextClassLoader();
		} catch (Throwable localThrowable) {
			_$2.debug(
					"Cannot access thread context ClassLoader - falling back to system class loader",
					localThrowable);
		}
		if (localClassLoader == null)
			localClassLoader = ClassUtil.class.getClassLoader();
		return localClassLoader;
	}

	public static Class forName(String paramString)
			throws ClassNotFoundException, LinkageError {
		Assert.notNull(paramString, "Name must not be null");
		return getDefaultClassLoader().loadClass(paramString);
	}

	public static Object newInstance(String paramString) {
		try {
			return forName(paramString).newInstance();
		} catch (InstantiationException localInstantiationException) {
			throw new RuntimeException(localInstantiationException);
		} catch (IllegalAccessException localIllegalAccessException) {
			throw new RuntimeException(localIllegalAccessException);
		} catch (ClassNotFoundException localClassNotFoundException) {
			throw new RuntimeException(localClassNotFoundException);
		} catch (LinkageError localLinkageError) {
			throw new RuntimeException(localLinkageError);
		}
	}

	public static List<Method> getMethods(Class<?> paramClass,
			String paramString) throws NoSuchMethodException {
		Method[] arrayOfMethod = paramClass.getMethods();
		ArrayList localArrayList = new ArrayList();
		for (int i = 0; i < arrayOfMethod.length; ++i) {
			Method localMethod = arrayOfMethod[i];
			RequestMapping localRequestMapping = (RequestMapping) AnnotationUtils
					.findAnnotation(localMethod, RequestMapping.class);
			if ((localRequestMapping != null)
					&& (localRequestMapping.value() != null))
				for (int j = 0; j < localRequestMapping.value().length; ++j)
					if (paramString.equals(localRequestMapping.value()[j])) {
						System.out.println("match method mapping = "
								+ localRequestMapping.value()[j]);
						localArrayList.add(localMethod);
					}
		}
		if (localArrayList.size() == 0)
			throw new NoSuchMethodException(paramString);
		return localArrayList;
	}

	public static Method getMethodByName(Class<?> paramClass,
			String paramString, Class<?>[] paramArrayOfClass)
			throws NoSuchMethodException {
		StringBuilder localStringBuilder2;
		StringBuilder sb = new StringBuilder(paramString);
		if ((paramArrayOfClass != null) && (paramArrayOfClass.length > 0)) {
			int i = paramArrayOfClass.length;
			for (int j = 0; j < i; ++j) {
				Class a = paramArrayOfClass[j];
				//sb.append(localStringBuilder2.toString());
			}
		}
		Method[] localObject1 = paramClass.getMethods();
		for (int i = 0; i < localObject1.length; ++i) {
			Class localObject2 = paramArrayOfClass[i];
			localStringBuilder2 = new StringBuilder(localObject2.getName());
			Class[] arrayOfClass1 = null;// localObject2.getParameterTypes();
			if ((arrayOfClass1 != null) && (arrayOfClass1.length > 0)) {
				Class[] arrayOfClass2 = arrayOfClass1;
				int k = arrayOfClass2.length;
				for (int l = 0; l < k; ++l) {
					Class localClass = arrayOfClass2[l];
					localStringBuilder2.append(localClass.toString());
				}
			}
			if (localStringBuilder2.toString().equals(sb.toString())) {
				System.out.println("match method mapping = " + localObject2);
				return null;
			}
		}
		return ((Method) null);
	}

	public static synchronized Collection<Class<?>> getClassesOfPackage(
			ClassFilter paramClassFilter, String[] paramArrayOfString) {
		ArrayList localArrayList = new ArrayList();
		if (paramClassFilter == null)
			paramClassFilter = new IlllIlIlIlIIIIlI();
		String[] arrayOfString = paramArrayOfString;
		int i = arrayOfString.length;
		for (int j = 0; j < i; ++j) {
			String str1 = arrayOfString[j];
			String str2 = str1.trim();
			if (!("".equals(str2))) {
				String str3 = str2.replace('.', '/') + "/";
				_$2.debug("try to find classes in pakcage " + str3);
				try {
					localArrayList.addAll(_$1(getDefaultClassLoader(), str3,
							paramClassFilter));
				} catch (IOException localIOException) {
					throw new RuntimeException(localIOException);
				}
			}
		}
		return localArrayList;
	}

	private static Collection<Class<?>> _$1(ClassLoader paramClassLoader,
			String paramString, ClassFilter paramClassFilter)
			throws IOException {
		ArrayList localArrayList = new ArrayList();
		Enumeration localEnumeration = paramClassLoader
				.getResources(paramString);
		HashSet localHashSet = new HashSet();
		while (localEnumeration.hasMoreElements()) {
			URL localURL = (URL) localEnumeration.nextElement();
			if (!(localHashSet.contains(localURL.getPath()))) {
				localHashSet.add(localURL.getPath());
				_$1(paramString, localArrayList, localURL, paramClassLoader,
						paramClassFilter);
			}
		}
		localHashSet.clear();
		return localArrayList;
	}

	private static void _$1(String paramString,
			Collection<Class<?>> paramCollection, URL paramURL,
			ClassLoader paramClassLoader, ClassFilter paramClassFilter)
			throws IOException {
		Object localObject1;
		Object localObject2;
		if (_$1(paramURL)) {
			_$2.debug("url is [jar or weblogic zip or websphere wsjar],scan the classes in jar file");
			localObject1 = paramURL.openConnection();
			localObject2 = null;
			if (localObject1 instanceof JarURLConnection) {
				localObject2 = ((JarURLConnection) localObject1).getJarFile();
			} else {
				String str1 = paramURL.getFile();
				int i = str1.indexOf("!/");
				String str2 = str1.substring(0, i);
				if (str2.startsWith("file:"))
					str2 = str2.substring("file:".length());
				_$2.debug("jar file url is " + str2 + ",create jar file");
				localObject2 = new JarFile(str2);
			}
			_$1(paramString, paramCollection, (JarFile) localObject2,
					paramClassFilter);
		} else {
			localObject1 = new Stack();
			((Stack) localObject1).push(new IIlIllIlIlIllIIl(paramURL,
					paramString));
			while (!(((Stack) localObject1).isEmpty())) {
				localObject2 = (IIlIllIlIlIllIIl) ((Stack) localObject1).pop();
				_$1(((IIlIllIlIlIllIIl) localObject2)._$1,
						((IIlIllIlIlIllIIl) localObject2)._$2, paramCollection,
						(Stack) localObject1, paramClassLoader,
						paramClassFilter);
			}
		}
	}

	private static void _$1(String paramString, URL paramURL,
			Collection<Class<?>> paramCollection,
			Stack<IIlIllIlIlIllIIl> paramStack, ClassLoader paramClassLoader,
			ClassFilter paramClassFilter) throws IOException {
		String str2 = null;
		Object localObject1;
		String str3 = null;
		BufferedInputStream localBufferedInputStream = null;
		try {
			localBufferedInputStream = new BufferedInputStream(
					paramURL.openStream());
		} catch (FileNotFoundException localFileNotFoundException) {
			_$2.info("FielNotFoundException for url " + paramString,
					localFileNotFoundException);
			return;
		}
		InputStreamReader localInputStreamReader = new InputStreamReader(
				localBufferedInputStream);
		LineNumberReader localLineNumberReader = new LineNumberReader(
				localInputStreamReader);
		String str1 = null;
		try {
			while (true) {
				str2 = localLineNumberReader.readLine();
				if (str2 == null)
					break;
				if (!(str2.contains("$")))
					break;
			}
			if (str2.endsWith(".class")) {
				if (str1 == null)
					str1 = paramString.replace('/', '.');
				// str3 = str1 + ((String) localObject1);
			}
		} finally {
			try {
				Class localClass = Class.forName(str3);
				if (paramClassFilter.accept(localClass)) {
					_$2.debug("found class " + str3);
					paramCollection.add(localClass);
				}
			} catch (ClassNotFoundException localClassNotFoundException) {
				while (true) {
					localClassNotFoundException.printStackTrace();
					if (!(str2.contains(".")))
						break;
				}
				localObject1 = new URL(paramURL.toExternalForm() + str2 + "/");
				str3 = paramString + str2 + "/";
				paramStack.push(new IIlIllIlIlIllIIl((URL) localObject1, str3));
				localLineNumberReader.close();
				localLineNumberReader = null;
				if (localLineNumberReader != null) {
					try {
						localLineNumberReader.close();
					} catch (IOException localIOException1) {
					} finally {
						if (localLineNumberReader != null)
							try {
								localLineNumberReader.close();
							} catch (IOException localIOException2) {
								throw localIOException2;
							}
					}
				}
			}
		}
	}

	private static void _$1(String paramString,
			Collection<Class<?>> paramCollection, JarFile paramJarFile,
			ClassFilter paramClassFilter) throws IOException {
		String str1 = _$2(paramString);
		try {
			Enumeration localEnumeration = paramJarFile.entries();
			while (localEnumeration.hasMoreElements()) {
				JarEntry localJarEntry = (JarEntry) localEnumeration
						.nextElement();
				String str2 = localJarEntry.getName();
				if ((str2.startsWith(str1)) && (str2.endsWith(".class"))
						&& (!(str2.contains("$")))) {
					String str3 = str2.substring(0,
							str2.length() - ".class".length());
					try {
						Class localClass = Class.forName(_$1(str3));
						if (paramClassFilter.accept(localClass))
							paramCollection.add(localClass);
					} catch (ClassNotFoundException localClassNotFoundException) {
						_$2.error("无法获取类:" + str3,
								localClassNotFoundException.getCause());
					}
				}
			}
		} finally {
			if (paramJarFile != null)
				try {
					paramJarFile.close();
				} catch (IOException localIOException2) {
				}
		}
	}

	private static String _$2(String paramString) {
		return paramString.replace('.', '/');
	}

	private static String _$1(String paramString) {
		return paramString.replace('/', '.').replace('\\', '.');
	}

	private static boolean _$1(URL paramURL) {
		String str = paramURL.getProtocol();
		return (("jar".equals(str)) || ("zip".equals(str)) || ("wsjar"
				.equals(str)));
	}

	static {
		_$1.put(Boolean.class, Boolean.TYPE);
		_$1.put(Byte.class, Byte.TYPE);
		_$1.put(Character.class, Character.TYPE);
		_$1.put(Double.class, Double.TYPE);
		_$1.put(Float.class, Float.TYPE);
		_$1.put(Integer.class, Integer.TYPE);
		_$1.put(Long.class, Long.TYPE);
		_$1.put(Short.class, Short.TYPE);
	}

	public static abstract interface ClassFilter {
		public abstract boolean accept(Class<?> paramClass);
	}
}