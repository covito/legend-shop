package com.legendshop.util.sql;

import com.legendshop.util.AppUtils;
import com.legendshop.util.xml.Configure;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.log4j.Logger;
import org.apache.oro.text.regex.MalformedPatternException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ConfigCode {
	private static Logger logger = Logger.getLogger(ConfigCode.class);
	private static ConfigCode configCode = null;
	private boolean isDebug = false;
	private Map<String, String> codeMap = new HashMap<String, String>();
	private Map<String, CodeItem> codeItemMap = new HashMap<String, CodeItem>();
	private File file = null;
	private long lastMdTime;
	private boolean initFlag = false;
	private static final Object obj = new Object();
	private final DynamicCode dycode = new DynamicCode();
	private String cfgPath = "classpath*:DAL.cfg.xml";

	public static ConfigCode getInstance() {
		if (configCode == null) {
			configCode = new ConfigCode();
			if (!(configCode.initFlag))
				configCode.init(configCode.cfgPath);
		}
		return configCode;
	}

	public static ConfigCode getInstance(String paramString) {
		if (configCode == null) {
			configCode = new ConfigCode();
			if (!(configCode.initFlag)) {
				configCode.setConfName(paramString);
				configCode.init(paramString);
			}
		}
		return configCode;
	}

	public static ConfigCode refresh() {
		synchronized (obj) {
			if (!(configCode.initFlag))
				logger.warn("ConfigCode还没有初始化，不能刷新，先要调用getInstance方法");
			else
				logger.info("注意：开始刷新ConfigCode！");
			String str = configCode.getConfName();
			configCode = null;
			configCode = new ConfigCode();
			if (!(configCode.initFlag)) {
				configCode.setConfName(str);
				configCode.init(str);
			}
			return configCode;
		}
	}

	public String getCode(String paramString) {
		initCode();
		String str = (String) this.codeMap.get(paramString);
		if (str == null) {
			logger.warn(" getCode return null, signature = " + paramString);
			return null;
		}
		return str;
	}

	public String getCode(String paramString, Map<String, Object> paramMap) {
		initCode();
		return this.dycode.convert(getCode(paramString), paramMap);
	}

	private void initCode() {
		this.isDebug=true;
		if (this.isDebug)
			if (configCode.isRelush()) {
				logger.debug(this.cfgPath + " had modify,load again!");
				configCode = new ConfigCode();
				configCode.init(this.cfgPath);
			} else {
				Iterator localIterator = this.codeItemMap.keySet().iterator();
				while (localIterator.hasNext()) {
					String str = (String) localIterator.next();
					CodeItem localCodeItem = (CodeItem) this.codeItemMap.get(str);
					if (localCodeItem.isModified())
						_$2(str, localCodeItem);
				}
			}
	}

	private boolean init(String paramString) {
		synchronized (obj) {
			if (this.initFlag) {
				logger.warn("ConfigCode had inited, should not init again");
				return true;
			}
			logger.info("The current version of DAL is : 2012-04-26.");
			Configure localConfigure = new Configure();
			try {
				this.file = getFile(paramString);
				this.lastMdTime = this.file.lastModified();
				localConfigure.parse(paramString);
				String str1 = "/DataAccessLayer/MappingFiles";
				int i = localConfigure.getItemCount(str1);
				for (int j = 1; j <= i; ++j) {
					Resource[] localObject2;
					str1 = "/DataAccessLayer/MappingFiles/Mapping[" + j + "]";
					String str2 = localConfigure.getItemProp(str1, "resource");
					if (str2.startsWith("classpath")) {
						localObject2 = getResources(str2);
						for (int k = 0; k < localObject2.length; ++k) {
							Resource localObject3 = localObject2[k];
							CodeItem localCodeItem = new CodeItem();
							try {
								String str3 = localObject3.getFile().toString();
								_$1(str3, localCodeItem);
							} catch (Exception localException2) {
								_$1(localObject3.getInputStream(),
										localCodeItem);
							}
						}
					} else {
						CodeItem code = new CodeItem();
						_$1(str2, code);
					}
				}
			} catch (Exception localException1) {
				logger.error("初始化DAL配置文件出错", localException1);
				this.initFlag = false;
				return this.initFlag;
			}
			this.initFlag = true;
			_$3();
			return this.initFlag;
		}
	}

	private void _$3() {
		_$2();
	}

	private void _$2() {
		Iterator localIterator;
		try {
			localIterator = this.codeMap.keySet().iterator();
			while (localIterator.hasNext()) {
				String str = (String) localIterator.next();
				HashMap localHashMap = new HashMap();
				this.dycode.fillPlaceHolder(str, this.codeMap, localHashMap);
			}
		} catch (MalformedPatternException localMalformedPatternException) {
			localMalformedPatternException.printStackTrace();
		}
	}

	private void _$1(InputStream paramInputStream, CodeItem paramCodeItem) {
		logger.debug("initCodeItem by inputStream");
		Map localMap = paramCodeItem.init(paramInputStream);
		if (AppUtils.isNotBlank(localMap)) {
			Iterator localIterator = localMap.keySet().iterator();
			while (localIterator.hasNext()) {
				String str = (String) localIterator.next();
				_$1(str, (String) localMap.get(str));
			}
		}
	}

	private void _$2(String paramString, CodeItem paramCodeItem) {
		logger.debug("sql mapping fileName = " + paramString);
		Map<String, String> localMap = paramCodeItem.init(paramString);
		if (AppUtils.isNotBlank(localMap)) {
			Iterator localIterator = localMap.keySet().iterator();
			while (localIterator.hasNext()) {
				String str = (String) localIterator.next();
				this.codeMap.put(str, localMap.get(str));
			}
		}
		this.codeItemMap.put(paramString, paramCodeItem);
	}

	private void _$1(String paramString, CodeItem paramCodeItem) {
		logger.debug("sql mapping fileName = " + paramString);
		Map localMap = paramCodeItem.init(paramString);
		if (AppUtils.isNotBlank(localMap)) {
			Iterator localIterator = localMap.keySet().iterator();
			while (localIterator.hasNext()) {
				String str = (String) localIterator.next();
				_$1(str, (String) localMap.get(str));
			}
		}
		this.codeItemMap.put(paramString, paramCodeItem);
	}

	public String toString() {
		StringBuffer localStringBuffer = new StringBuffer();
		Iterator localIterator = this.codeItemMap.entrySet().iterator();
		while (localIterator.hasNext()) {
			Map.Entry localEntry = (Map.Entry) localIterator.next();
			localStringBuffer.append("<Mapping resource=")
					.append((String) localEntry.getKey()).append(">\n");
			CodeItem localCodeItem = (CodeItem) localEntry.getValue();
			localStringBuffer.append(localCodeItem.toString());
			localStringBuffer.append("</Mapping>").append('\n');
		}
		return localStringBuffer.toString();
	}

	private void _$1(String paramString1, String paramString2) {
		if (this.codeMap.containsKey(paramString1))
			logger.warn(" unique constraint violated ,key = " + paramString1);
		else
			this.codeMap.put(paramString1, paramString2);
	}

	private boolean isRelush() {
		if (this.file == null)
			return false;
		return (this.file.lastModified() > this.lastMdTime);
	}

	public Map<String, String> getParameters() {
		return this.codeMap;
	}

	public void setDebug(boolean paramBoolean) {
		this.isDebug = paramBoolean;
	}

	public boolean isInitStatus() {
		return this.initFlag;
	}

	public boolean isDebug() {
		return this.isDebug;
	}

	public String getConfName() {
		return this.cfgPath;
	}

	public void setConfName(String paramString) {
		this.cfgPath = paramString;
	}

	public Resource[] getResources(String paramString) throws Exception {
		PathMatchingResourcePatternResolver localPathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		if (paramString.startsWith("classpath"))
			return localPathMatchingResourcePatternResolver
					.getResources(paramString);
		return localPathMatchingResourcePatternResolver
				.getResources("classpath*:" + paramString);
	}

	public File getFile(String paramString) throws IOException {
		File localFile = null;
		if (paramString.startsWith("classpath")) {
			int i = paramString.indexOf(":");
			String str = paramString.substring(i + 1);
			ClassPathResource localClassPathResource = new ClassPathResource(
					str);
			localFile = localClassPathResource.getFile();
		} else {
			localFile = new File(paramString);
		}
		return localFile;
	}

	public static void main(String[] paramArrayOfString) {
		ConfigCode localConfigCode;
		try {
			localConfigCode = getInstance();
			localConfigCode.setDebug(true);
			String str1 = ObjectSignature.toSignature("TestObject1", "find");
			String str2 = localConfigCode.getCode(str1);
			HashMap localHashMap = new HashMap();
			localHashMap.put("id", "1");
			localHashMap.put("name", "name");
			localHashMap.put("condition1", "and address = gm");
			System.out.println(localConfigCode.getCode("TestObject1.update",
					localHashMap));
			System.out.println(localConfigCode.getCode("TestObject1.update"));
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}
}