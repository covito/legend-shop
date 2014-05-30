package com.legendshop.business.service.impl;

import com.legendshop.business.dao.SensitiveWordDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.SensitiveWord;
import com.legendshop.spi.service.SensitiveWordService;
import com.legendshop.util.AppUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SensitiveWordServiceImpl implements SensitiveWordService {
	private SensitiveWordDao sensitiveWordDao;

	public void setSensitiveWordDao(SensitiveWordDao sensitiveWordDao) {
		this.sensitiveWordDao = sensitiveWordDao;
	}

	public List<SensitiveWord> getSensitiveWord(String userName) {
		return this.sensitiveWordDao.getSensitiveWord(userName);
	}

	public SensitiveWord getSensitiveWord(Long id) {
		return this.sensitiveWordDao.getSensitiveWord(id);
	}

	public void deleteSensitiveWord(SensitiveWord sensitiveWord) {
		this.sensitiveWordDao.deleteSensitiveWord(sensitiveWord);
	}

	public Long saveSensitiveWord(SensitiveWord sensitiveWord) {
		if (!(AppUtils.isBlank(sensitiveWord.getSensId()))) {
			updateSensitiveWord(sensitiveWord);
			return sensitiveWord.getSensId();
		}
		return ((Long) this.sensitiveWordDao.save(sensitiveWord));
	}

	public void updateSensitiveWord(SensitiveWord sensitiveWord) {
		this.sensitiveWordDao.updateSensitiveWord(sensitiveWord);
	}

	public PageSupport getSensitiveWord(CriteriaQuery cq) {
		return this.sensitiveWordDao.find(cq);
	}

	public String newcontainSensitiveWords(String src, Long sortId,
			Long nsortId, Long subNsortId) {
		List sensitiveWordList = this.sensitiveWordDao.getWords(sortId,
				nsortId, subNsortId);
		Map wordMap = new HashMap();
		for (Iterator localIterator1 = sensitiveWordList.iterator(); localIterator1
				.hasNext();) {
			String s = (String) localIterator1.next();
			char c = s.charAt(0);
			List strs = (List) wordMap.get(Character.valueOf(c));
			if (strs == null) {
				strs = new ArrayList();
				wordMap.put(Character.valueOf(c), strs);
			}
			strs.add(s);
		}
		String temp = null;
		StringBuilder strb = new StringBuilder();
		StringBuilder findwords = new StringBuilder();
		for (int i = 0; i < src.length(); ++i) {
			char c = src.charAt(i);
			String find = null;
			if (wordMap.containsKey(Character.valueOf(c))) {
				List words = (List) wordMap.get(Character.valueOf(c));
				for (Iterator localIterator2 = words.iterator(); localIterator2
						.hasNext();) {
					String s = (String) localIterator2.next();
					temp = src.substring(i,
							(s.length() <= src.length() - i) ? i + s.length()
									: i);
					if (!(s.equals(temp))) {
						break;
					}
					find = s;
				}
			}

			if (find != null) {
				findwords.append(find);
				i += find.length() - 1;
			} else {
				strb.append(c);
			}
		}

		return findwords.toString();
	}
}