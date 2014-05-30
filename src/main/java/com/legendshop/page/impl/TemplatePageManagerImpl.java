package com.legendshop.page.impl;

import com.legendshop.page.TemplatePage;
import com.legendshop.page.TemplatePageManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TemplatePageManagerImpl implements TemplatePageManager {
	private Map<String, List<String>> tempMap = new HashMap();

	public List<String> getTemplateByPage(String paramString) {
		return ((List) this.tempMap.get(paramString));
	}

	public void registerTemplatePage(TemplatePage page) {
		Iterator localIterator = page.getPageNames().iterator();
		while (localIterator.hasNext()) {
			String str = (String) localIterator.next();
			List localList = (List) this.tempMap.get(str);
			if (localList == null) {
				ArrayList localArrayList = new ArrayList();
				localArrayList.add(page.getTemplate());
				this.tempMap.put(str, localArrayList);
			} else {
				localList.add(page.getTemplate());
				this.tempMap.put(str, localList);
			}
		}
	}
}