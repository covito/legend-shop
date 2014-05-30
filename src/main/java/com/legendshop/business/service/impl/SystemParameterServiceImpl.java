package com.legendshop.business.service.impl;

import com.legendshop.business.dao.SystemParameterDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.SystemParameter;
import com.legendshop.spi.service.SystemParameterService;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class SystemParameterServiceImpl implements SystemParameterService {
	private static Logger log = LoggerFactory
			.getLogger(SystemParameterServiceImpl.class);
	private SystemParameterDao systemParameterDao;

	private List<SystemParameter> list() {
		return this.systemParameterDao.findByHQL("from SystemParameter",
				new Object[0]);
	}

	public SystemParameter getSystemParameter(String id) {
		return ((SystemParameter) this.systemParameterDao.get(
				SystemParameter.class, id));
	}

	public void delete(String id) {
		this.systemParameterDao.deleteSystemParameterById(id);
	}

	public void update(SystemParameter systemParameter) {
		this.systemParameterDao.updateSystemParameter(systemParameter);
	}

	public PageSupport getSystemParameterList(CriteriaQuery cq) {
		return this.systemParameterDao.find(cq);
	}

	public void initSystemParameter() {
		List list = list();
		for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
			SystemParameter parameter = (SystemParameter) localIterator.next();
			PropertiesUtil.setParameter(parameter);
		}
		log.info("System Parameter size = {}", Integer.valueOf(list.size()));
	}

	@Required
	public void setBaseDao(SystemParameterDao systemParameterDao) {
		this.systemParameterDao = systemParameterDao;
	}
}