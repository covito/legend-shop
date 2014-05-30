package com.legendshop.core.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.legendshop.core.SystemConfig;
import com.legendshop.core.constant.BusinessModeEnum;
import com.legendshop.core.constant.ConfigPropertiesEnum;
import com.legendshop.core.event.SysDestoryEvent;
import com.legendshop.core.event.SysInitEvent;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.SpringBeanManager;
import com.legendshop.core.page.PagerUtil;
import com.legendshop.event.EventHome;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.sql.ConfigCode;

public class InitSysListener implements ServletContextListener {
	private static Logger logger = LoggerFactory.getLogger(InitSysListener.class);
	private static boolean _$2 = false;
	private static final Object object = new Object();

	public void contextDestroyed(ServletContextEvent sce) {
		logger.debug("system destory start");
		EventHome.publishEvent(new SysDestoryEvent(sce));
	}

	public void contextInitialized(ServletContextEvent sce) {
		synchronized (object) {
			logger.info("********* Initializing LegendShop System now *************");
			_$2(sce);
			if ((!(_$2)) && (PropertiesUtil.isSystemInstalled())) {
				_$2 = true;
				_$1(sce);
				ConfigCode localConfigCode = ConfigCode
						.getInstance("classpath*:DAL.cfg.xml");
				boolean bool = PropertiesUtil.isSystemInDebugMode();
				logger.debug("System in DEBUG MODE is {}", Boolean.valueOf(bool));
				localConfigCode.setDebug(bool);
				if (AppUtils.isBlank(PropertiesUtil.getLegendShopSystemId()))
					PropertiesUtil.changeLegendShopSystemId();
				Map localMap = EventHome.initBaseEventListener();
				SpringBeanManager localSpringBeanManager = (SpringBeanManager) ContextServiceLocator
						.getInstance().getBean("springBeanManager");
				localSpringBeanManager.removeBean(sce.getServletContext(),
						localMap.keySet());
				SystemConfig localSystemConfig = (SystemConfig) ContextServiceLocator
						.getInstance().getBean("systemConfig");
				sce.getServletContext().setAttribute(
						ConfigPropertiesEnum.SYSTEM_CONFIG.name(),
						localSystemConfig);
				EventHome.publishEvent(new SysInitEvent(sce));
				_$1(ContextServiceLocator.getInstance().getContext());
			}
			logger.info("*********  LegendShop System Initialized successful **********");
		}
	}

	private void _$2(ServletContextEvent paramServletContextEvent) {
		String str1 = RealPathUtil.getSystemRealPath(paramServletContextEvent
				.getServletContext());
		if ((str1 != null) && (!(str1.endsWith("/"))))
			str1 = str1 + "/";
		PropertiesUtil.setSystemRealPath(str1);
		ServletContext localServletContext = paramServletContextEvent
				.getServletContext();
		PagerUtil.setPath(localServletContext.getContextPath());
		localServletContext.setAttribute(
				ConfigPropertiesEnum.CURRENCY_PATTERN.name(),
				PropertiesUtil.getCurrencyPattern());
		localServletContext.setAttribute("DOMAIN_NAME",
				PropertiesUtil.getDomainName());
		String str2 = PropertiesUtil.getProperties("config/global.properties",
				ConfigPropertiesEnum.BUSINESS_MODE.name());
		if (BusinessModeEnum.C2C.name().equals(str2))
			localServletContext.setAttribute("BUSINESS_MODE",
					BusinessModeEnum.C2C.name());
		else
			localServletContext.setAttribute("BUSINESS_MODE",
					BusinessModeEnum.B2C.name());
		String str3 = PropertiesUtil.getProperties("config/global.properties",
				ConfigPropertiesEnum.LANGUAGE_MODE.name());
		if ("english".equals(str3))
			localServletContext.setAttribute("LANGUAGE_MODE", "userChoice");
		else if ("chinese".equals(str3))
			localServletContext.setAttribute("LANGUAGE_MODE", "chinese");
		else
			localServletContext.setAttribute("LANGUAGE_MODE", "userChoice");
		localServletContext.setAttribute("RUNTIME_MODE", "PROD");
		localServletContext
				.setAttribute(ConfigPropertiesEnum.LEGENDSHOP_VERSION.name(),
						PropertiesUtil.getProperties(
								"config/global.properties",
								ConfigPropertiesEnum.LEGENDSHOP_VERSION.name()));
	}

	private void _$1(ServletContextEvent paramServletContextEvent) {
		WebApplicationContext localWebApplicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(paramServletContextEvent
						.getServletContext());
		ContextServiceLocator.getInstance().setContext(
				localWebApplicationContext);
	}

	private void _$1(ApplicationContext paramApplicationContext) {
		if (logger.isDebugEnabled()) {
			int i = 0;
			String[] arrayOfString1 = paramApplicationContext
					.getBeanDefinitionNames();
			StringBuffer localStringBuffer = new StringBuffer(
					"系统配置的Spring Bean [ \n");
			if (!(AppUtils.isBlank(arrayOfString1))) {
				String[] arrayOfString2 = arrayOfString1;
				int j = arrayOfString2.length;
				for (int k = 0; k < j; ++k) {
					String str = arrayOfString2[k];
					localStringBuffer.append(++i).append(" ").append(str)
							.append("\n");
				}
				localStringBuffer.append(" ]");
				logger.debug(localStringBuffer.toString());
			}
		}
	}
}