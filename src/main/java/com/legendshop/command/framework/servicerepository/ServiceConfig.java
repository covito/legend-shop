package com.legendshop.command.framework.servicerepository;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ServiceConfig {
	private static Logger _$2 = Logger.getLogger(ServiceConfig.class);
	public static final String ROOT = "/ServiceRepository";
	public static final String ENVS = "Envs";
	public static final String ENV = "Env";
	public static final String URL = "URL";
	public static final String Factory = "Factory";
	public static final String USER = "User";
	public static final String PWD = "Password";
	public static final String EJB_DEF = "EJBDef";
	public static final String EJB = "EJB";
	public static final String HOME_CLASS = "HomeClass";
	public static final String JMS_DEF = "JMSDef";
	public static final String JMS = "JMS";
	public static final String JMS_FACTORY = "JMSFactory";
	public static final String JMS_TOPIC = "JMSTopic";
	public static final String Topic = "Topic";
	public static final String Queue = "Queue";
	public static final String JMS_QUEUE = "JMSQueue";
	public static final String OBJECT_DEF = "ObjectDef";
	public static final String OBJECT = "Object";
	public static final String NAME_ATTR = "name";
	public static final String SPRING_DEF = "SpringDef";
	public static final String SPRING_XML_FILE_DEF = "SpringXmlFileDef";
	public static final String VALUE = "value";
	public static final String PROJECT = "Project";
	public static final String MAIN_ATTR = "main";
	Document _$1;

	public Map ElementIterator(Element paramElement) {
		HashMap localHashMap = new HashMap();
		Iterator localIterator = paramElement.elementIterator();
		while (localIterator.hasNext()) {
			Element localElement = (Element) localIterator.next();
			localHashMap
					.put(localElement.getName(), localElement.getTextTrim());
		}
		return localHashMap;
	}

	public Map ElementIterator(Element paramElement, String paramString) {
		HashMap localHashMap = new HashMap();
		Iterator localIterator = paramElement.elementIterator(paramString);
		while (localIterator.hasNext()) {
			Element localElement = (Element) localIterator.next();
			localHashMap
					.put(localElement.getName(), localElement.getTextTrim());
		}
		return localHashMap;
	}

	public List getItemValueList(Element paramElement, String paramString) {
		LinkedList localLinkedList = new LinkedList();
		Iterator localIterator = paramElement.elementIterator(paramString);
		while (localIterator.hasNext()) {
			Element localElement = (Element) localIterator.next();
			localLinkedList.add(localElement.getTextTrim());
		}
		return localLinkedList;
	}

	public String getItemValue(Element paramElement, String paramString) {
		String str = null;
		Iterator localIterator = paramElement.elementIterator(paramString);
		while (localIterator.hasNext()) {
			Element localElement = (Element) localIterator.next();
			str = localElement.getTextTrim();
		}
		return str;
	}

	public Map getEnvs() {
		HashMap localHashMap = new HashMap();
		try {
			Element localElement1 = this._$1.getRootElement();
			Element localElement2 = localElement1.element("Envs");
			Iterator localIterator = localElement2.elementIterator("Env");
			while (localIterator.hasNext()) {
				Element localElement3 = (Element) localIterator.next();
				String str = localElement3.attributeValue("name");
				Map localMap = ElementIterator(localElement3);
				localHashMap.put(str, localMap);
			}
		} catch (Exception localException) {
			_$2.debug("read context info fail", localException);
			return null;
		}
		return localHashMap;
	}

	public Map getEJBMetaData() {
		HashMap localHashMap = new HashMap();
		try {
			Element localElement1 = this._$1.getRootElement();
			Element localElement2 = localElement1.element("EJBDef");
			Iterator localIterator = localElement2.elementIterator("EJB");
			while (localIterator.hasNext()) {
				Element localElement3 = (Element) localIterator.next();
				String str1 = localElement3.attributeValue("name");
				List localList = getItemValueList(localElement3, "Env");
				String str2 = getItemValue(localElement3, "HomeClass");
				localHashMap.put(str1, new EJBMetaData(str1, str2, localList));
			}
		} catch (Exception localException) {
			_$2.debug("read EJB info fail", localException);
			return null;
		}
		return localHashMap;
	}

	public Map getJMSMetaData() {
		HashMap localHashMap = new HashMap();
		try {
			Object localObject2;
			Object localObject4;
			Object localObject5;
			String str2;
			Element localElement1 = this._$1.getRootElement();
			Element localElement2 = localElement1.element("JMSDef");
			Element localElement3 = localElement2.element("JMSTopic");
			Object localObject1 = localElement3.elementIterator("JMS");
			while (((Iterator) localObject1).hasNext()) {
				localObject2 = (Element) ((Iterator) localObject1).next();
				String str1 = ((Element) localObject2).attributeValue("name");
				Map localObject3 = ElementIterator((Element) localObject2);
				localObject4 = (String) ((Map) localObject3).get("Env");
				localObject5 = (String) ((Map) localObject3).get("JMSFactory");
				str2 = (String) ((Map) localObject3).get("Topic");
				localHashMap.put(str1, new JMSTopicMetaData(str1,
						(String) localObject5, str2, (String) localObject4));
			}
			localObject1 = localElement2.element("JMSQueue");
			Object localObject3 = ((Element) localObject1)
					.elementIterator("JMS");
			while (((Iterator) localObject3).hasNext()) {
				localObject4 = (Element) ((Iterator) localObject3).next();
				localObject2 = ((Element) localObject4).attributeValue("name");
				localObject5 = ElementIterator((Element) localObject4);
				str2 = (String) ((Map) localObject5).get("Env");
				String str3 = (String) ((Map) localObject5).get("JMSFactory");
				String str4 = (String) ((Map) localObject5).get("Queue");
				localHashMap.put(localObject2, new JMSQueueMetaData(
						(String) localObject2, str3, str4, str2));
			}
		} catch (Exception localException) {
			_$2.debug("read context info fail", localException);
			return null;
		}
		return ((Map) (Map) (Map) (Map) (Map) localHashMap);
	}

	public Map getSpringApplicationContext() {
		HashMap localHashMap = new HashMap();
		try {
			Element localElement1 = this._$1.getRootElement();
			Element localElement2 = localElement1.element("SpringDef");
			Element localElement3 = localElement2.element("SpringXmlFileDef");
			String str1 = null;
			String str2 = null;
			Iterator localIterator = localElement3.elementIterator("Project");
			while (localIterator.hasNext()) {
				Element localElement4 = (Element) localIterator.next();
				str1 = localElement4.attributeValue("name");
				str2 = localElement4.attributeValue("main");
				List localList = getItemValueList(localElement4, "value");
				localHashMap.put(str1 + "-main", str2);
				localHashMap.put(str1, localList);
			}
		} catch (Exception localException) {
			_$2.debug("read context info fail", localException);
			return null;
		}
		return localHashMap;
	}

	public Map getObjMetaData() {
		HashMap localHashMap = new HashMap();
		try {
			Element localElement1 = this._$1.getRootElement();
			Element localElement2 = localElement1.element("ObjectDef");
			Iterator localIterator = localElement2.elementIterator("Object");
			while (localIterator.hasNext()) {
				Element localElement3 = (Element) localIterator.next();
				String str1 = localElement3.attributeValue("name");
				String str2 = localElement3.getTextTrim();
				localHashMap.put(str1, new ObjectMetaData(str1, str2));
			}
		} catch (Exception localException) {
			_$2.debug("read context info fail", localException);
			return null;
		}
		return localHashMap;
	}

	public boolean config(String paramString) {
		SAXReader localSAXReader;
		try {
			localSAXReader = new SAXReader();
			this._$1 = localSAXReader.read(new File(paramString));
			return true;
		} catch (Exception localException) {
			_$2.debug("parser xml file fail", localException);
		}
		return false;
	}
}