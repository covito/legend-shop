package com.legendshop.util.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.InputSource;

public class Configure {
	private Document _$4 = null;
	private Node _$3 = null;
	private String _$2 = null;
	private List<Node> _$1 = null;

	public void parse(String paramString)
			throws com.legendshop.util.xml.ConfigException {
		_$2(paramString);
	}

	public void parse(InputStream paramInputStream)
			throws com.legendshop.util.xml.ConfigException {
		_$1(paramInputStream);
	}

	public void parseXmlStr(String paramString)
			throws com.legendshop.util.xml.ConfigException {
		_$1(paramString);
	}

	public String currentPath() {
		return this._$2;
	}

	public void changePath(String paramString)
			throws com.legendshop.util.xml.ConfigException {
		this._$2 = paramString;
		this._$3 = this._$4.selectSingleNode(paramString);
		if (this._$3 == null)
			throw new com.legendshop.util.xml.ConfigException(paramString
					+ " not found.");
	}

	public String getItemProp(String paramString)
			throws com.legendshop.util.xml.ConfigException {
		String str = this._$3.valueOf('@' + paramString);
		if (str == null)
			throw new com.legendshop.util.xml.ConfigException(paramString
					+ " not found");
		return str;
	}

	public String getItemProp(String paramString1, String paramString2)
			throws com.legendshop.util.xml.ConfigException {
		changePath(paramString1);
		return getItemProp(paramString2);
	}

	@Deprecated
	public String getItemProp(String paramString1, String paramString2,
			int paramInt) throws com.legendshop.util.xml.ConfigException {
		this._$1 = this._$4.selectNodes(paramString1);
		try {
			this._$3 = ((Node) this._$1.get(paramInt));
			if (this._$3 == null)
				throw new com.legendshop.util.xml.ConfigException("the index ="
						+ paramInt + " node of " + paramString1 + " not found!");
		} catch (Exception localException) {
			throw new com.legendshop.util.xml.ConfigException("the index ="
					+ paramInt + " node of " + paramString1 + " not found!"
					+ localException);
		}
		String str = this._$3.valueOf(paramString2);
		if (str == null)
			throw new com.legendshop.util.xml.ConfigException(paramString2
					+ " not found");
		return str;
	}

	public List<String> getItemPropList(String paramString)
			throws com.legendshop.util.xml.ConfigException {
		LinkedList localLinkedList = new LinkedList();
		this._$1 = this._$4.selectNodes(this._$2);
		Iterator localIterator = this._$1.iterator();
		while (localIterator.hasNext()) {
			Node localNode = (Node) localIterator.next();
			String str = localNode.valueOf('@' + paramString);
			if ((str != null) && (!(str.equals(""))))
				localLinkedList.add(str);
		}
		return localLinkedList;
	}

	public List<String> getItemPropList(String paramString1, String paramString2)
			throws com.legendshop.util.xml.ConfigException {
		this._$2 = paramString1;
		this._$1 = this._$4.selectNodes(paramString1);
		return getItemPropList(paramString2);
	}

	@Deprecated
	public List getItemPropList(String paramString1, String paramString2,
			String paramString3, int paramInt)
			throws com.legendshop.util.xml.ConfigException {
		try {
			this._$1 = this._$4.selectNodes(paramString1);
			this._$3 = ((Node) this._$1.get(paramInt));
			if (this._$3 == null)
				throw new com.legendshop.util.xml.ConfigException("the index ="
						+ paramInt + " node of " + paramString1 + " not found!");
		} catch (Exception localException) {
			throw new com.legendshop.util.xml.ConfigException("the index ="
					+ paramInt + " node of " + paramString1 + " not found!"
					+ localException);
		}
		this._$1 = this._$3.selectNodes(paramString2);
		ArrayList localArrayList = new ArrayList();
		Iterator localIterator = this._$1.iterator();
		while (localIterator.hasNext()) {
			Node localNode = (Node) localIterator.next();
			String str = localNode.valueOf('@' + paramString3);
			if ((str != null) && (!(str.equals(""))))
				localArrayList.add(str);
		}
		return localArrayList;
	}

	public String getItemValueEx(String paramString1, String paramString2,
			String paramString3) {
		String str = null;
		try {
			getItemValue(paramString1, paramString2);
		} catch (Exception localException) {
			str = paramString3;
		}
		return str;
	}

	public String getItemValueEx(String paramString1, String paramString2,
			String paramString3, String paramString4) {
		String str = null;
		try {
			str = getItemValue(paramString1, paramString2, paramString3);
		} catch (Exception localException) {
			str = paramString4;
		}
		return str;
	}

	public String getItemValue(String paramString1, String paramString2,
			String paramString3) throws com.legendshop.util.xml.ConfigException {
		String str = null;
		try {
			str = getItemValue(paramString1, paramString3);
		} catch (Exception localException) {
			str = getItemValue(paramString2, paramString3);
		}
		return str;
	}

	public String getItemValue(String paramString1, String paramString2)
			throws com.legendshop.util.xml.ConfigException {
		changePath(paramString1);
		return getItemValue(paramString2);
	}

	@Deprecated
	public String getItemValue(String paramString1, String paramString2,
			int paramInt) throws com.legendshop.util.xml.ConfigException {
		this._$1 = this._$4.selectNodes(paramString1);
		try {
			this._$3 = ((Node) this._$1.get(paramInt));
			if (this._$3 == null)
				throw new com.legendshop.util.xml.ConfigException("the index ="
						+ paramInt + " node of " + paramString1 + " not found!");
		} catch (Exception localException) {
			throw new com.legendshop.util.xml.ConfigException("the index ="
					+ paramInt + " node of " + paramString1 + " not found!"
					+ localException);
		}
		Node localNode = this._$3.selectSingleNode("./" + paramString2);
		if (localNode == null)
			throw new com.legendshop.util.xml.ConfigException(paramString2
					+ " not found");
		return localNode.getText();
	}

	public String getItemValue(String paramString)
			throws com.legendshop.util.xml.ConfigException {
		if ((this._$2 != null) && (!(this._$2.equals(""))))
			this._$3 = this._$4.selectSingleNode(this._$2);
		Node localNode = this._$3.selectSingleNode("./" + paramString);
		if (localNode == null)
			throw new com.legendshop.util.xml.ConfigException(paramString
					+ " not found");
		return localNode.getText();
	}

	public String getItemValue() throws com.legendshop.util.xml.ConfigException {
		String str = this._$3.getText();
		if (str == null)
			throw new com.legendshop.util.xml.ConfigException(
					"data error ,curPath has something wrong");
		return str;
	}

	public List<String> getItemValueList(String paramString)
			throws com.legendshop.util.xml.ConfigException {
		LinkedList localLinkedList = new LinkedList();
		Iterator localIterator1 = this._$1.iterator();
		while (localIterator1.hasNext()) {
			Node localNode1 = (Node) localIterator1.next();
			List localList = localNode1.selectNodes("./" + paramString);
			Iterator localIterator2 = localList.iterator();
			while (localIterator2.hasNext()) {
				Node localNode2 = (Node) localIterator2.next();
				localLinkedList.add(localNode2.getText());
			}
		}
		return localLinkedList;
	}

	public List<String> getItemValueListWithFullPath(String paramString)
			throws com.legendshop.util.xml.ConfigException {
		List localList = this._$3.selectNodes(paramString);
		LinkedList localLinkedList = new LinkedList();
		Iterator localIterator = localList.iterator();
		while (localIterator.hasNext()) {
			Node localNode = (Node) localIterator.next();
			localLinkedList.add(localNode.getText());
		}
		return localLinkedList;
	}

	public List getItemValueList(String paramString1, String paramString2)
			throws com.legendshop.util.xml.ConfigException {
		this._$1 = this._$4.selectNodes(paramString1);
		return getItemValueList(paramString2);
	}

	@Deprecated
	public List getItemValueList(String paramString1, String paramString2,
			int paramInt) throws com.legendshop.util.xml.ConfigException {
		this._$1 = this._$4.selectNodes(paramString1);
		try {
			this._$3 = ((Node) this._$1.get(paramInt));
			if (this._$3 == null)
				throw new com.legendshop.util.xml.ConfigException("the index ="
						+ paramInt + " node of " + paramString1 + " not found!");
		} catch (Exception localException) {
			throw new com.legendshop.util.xml.ConfigException("the index ="
					+ paramInt + " node of " + paramString1 + " not found!"
					+ localException);
		}
		ArrayList localArrayList = new ArrayList();
		List localList = this._$3.selectNodes("./" + paramString2);
		Iterator localIterator = localList.iterator();
		while (localIterator.hasNext()) {
			Node localNode = (Node) localIterator.next();
			localArrayList.add(localNode.getText());
		}
		return localArrayList;
	}

	public int getItemCount(String paramString)
			throws com.legendshop.util.xml.ConfigException {
		changePath(paramString);
		return getItemCount();
	}

	public int getItemCount() {
		List localList;
		try {
			localList = this._$3.selectNodes("./*");
			if (localList != null)
				return localList.size();
			return 0;
		} catch (Exception localException) {
			System.out.println(" Cofigure getItemCount fail : "
					+ localException.toString());
		}
		return 0;
	}

	public HashMap getItem(String paramString)
			throws com.legendshop.util.xml.ConfigException {
		changePath(paramString);
		return getItem();
	}

	public HashMap<String, String> getItem()
			throws com.legendshop.util.xml.ConfigException {
		List localList = this._$3.selectNodes("./*");
		HashMap localHashMap = new HashMap();
		Iterator localIterator = localList.iterator();
		while (localIterator.hasNext()) {
			Node localNode = (Node) localIterator.next();
			localHashMap.put(localNode.getName(), localNode.getText());
		}
		return localHashMap;
	}

	public List getItemNameList(String paramString)
			throws com.legendshop.util.xml.ConfigException {
		changePath(paramString);
		return getItemNameList();
	}

	public List getItemNameList()
			throws com.legendshop.util.xml.ConfigException {
		List localList = this._$3.selectNodes("./*");
		LinkedList localLinkedList = new LinkedList();
		Iterator localIterator = localList.iterator();
		while (localIterator.hasNext()) {
			Node localNode = (Node) localIterator.next();
			localLinkedList.add(localNode.getName());
		}
		return localLinkedList;
	}

	private void _$2(String paramString)
			throws com.legendshop.util.xml.ConfigException {
		SAXReader localSAXReader = new SAXReader();
		Document localDocument = null;
		try {
			localDocument = localSAXReader.read(getFile(paramString));
		} catch (Exception localException) {
			throw new com.legendshop.util.xml.ConfigException("解析XML文件出错",
					localException);
		}
		this._$4 = localDocument;
		this._$3 = this._$4.getRootElement();
	}

	private void _$1(InputStream paramInputStream)
			throws com.legendshop.util.xml.ConfigException {
		SAXReader localSAXReader = new SAXReader();
		Document localDocument = null;
		try {
			localDocument = localSAXReader.read(new InputSource(
					paramInputStream));
		} catch (Exception localException) {
			throw new com.legendshop.util.xml.ConfigException("解析XML文件出错",
					localException);
		}
		this._$4 = localDocument;
		this._$3 = this._$4.getRootElement();
	}

	public void parse(File paramFile)
			throws com.legendshop.util.xml.ConfigException {
		SAXReader localSAXReader = new SAXReader();
		Document localDocument = null;
		try {
			localDocument = localSAXReader.read(paramFile);
		} catch (Exception localException) {
			throw new com.legendshop.util.xml.ConfigException("解析XML文件出错",
					localException);
		}
		this._$4 = localDocument;
		this._$3 = this._$4.getRootElement();
	}

	private void _$1(String paramString)
			throws com.legendshop.util.xml.ConfigException {
		SAXReader localSAXReader = new SAXReader();
		Document localDocument = null;
		try {
			localDocument = localSAXReader.read(new StringReader(paramString));
		} catch (Exception localException) {
			throw new com.legendshop.util.xml.ConfigException("解析XML字符串出错",
					localException);
		}
		this._$4 = localDocument;
		this._$3 = this._$4.getRootElement();
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
}