package com.legendshop.core.tag;

import com.legendshop.core.dao.BaseDao;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodeTablesCache implements TableCache {
	private static Log _$3 = LogFactory.getLog(CodeTablesCache.class);
	private final Map<String, Map<String, String>> _$2 = new HashMap();
	private BaseDao _$1;

	public void setBaseDao(BaseDao paramBaseDao) {
		this._$1 = paramBaseDao;
	}

	public void clear(Class paramClass) {
		this._$2.remove(paramClass);
		System.gc();
	}

	public void clearAll() {
		this._$2.clear();
		System.gc();
	}

	public void addCache(String paramString, Map<String, String> paramMap) {
		this._$2.put(paramString, paramMap);
	}

	public Map<String, String> getCodeTable(String paramString) {
		if ((paramString == null) || (paramString.trim().length() == 0))
			return null;
		Object localObject1 = (Map) this._$2.get(paramString);
		if (localObject1 == null) {
			LinkedHashMap localLinkedHashMap = null;
			List localList = this._$1.findByHQL("from " + paramString
					+ " order by name", new Object[0]);
			if ((localList != null) && (localList.size() > 0)) {
				localLinkedHashMap = new LinkedHashMap();
				Iterator localIterator = localList.iterator();
				while (localIterator.hasNext()) {
					Object localObject2 = localIterator.next();
					CodeTable localCodeTable = (CodeTable) localObject2;
					localLinkedHashMap.put(localCodeTable.getId(),
							localCodeTable.getName());
				}
			}
			if (localLinkedHashMap != null) {
				localObject1 = localLinkedHashMap;
				addCache(paramString, localLinkedHashMap);
			}
		}
		return ((Map<String, String>) localObject1);
	}

	public String getValue(String paramString1, String paramString2) {
		Map localMap = getCodeTable(paramString1);
		return ((localMap == null) ? "" : (String) localMap.get(paramString2));
	}

	public void initCodeTablesCache() {
	}
}