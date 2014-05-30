package com.legendshop.core.listener;

import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.plugins.PluginClassLoader;
import com.legendshop.util.AppUtils;
import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

public class PluginContextListener extends ContextLoaderListener {
	protected final Logger log = LoggerFactory
			.getLogger(PluginContextListener.class);

	public void contextInitialized(ServletContextEvent paramServletContextEvent) {
		if (PropertiesUtil.isSystemInstalled()) {
			try {
				ArrayList localArrayList = new ArrayList();
				String str = paramServletContextEvent.getServletContext()
						.getRealPath("/") + "/WEB-INF/plugins/";
				parsePluginFolder(localArrayList, new File(str));
				URL[] arrayOfURL = (URL[]) localArrayList
						.toArray(new URL[localArrayList.size()]);
				PluginClassLoader localPluginClassLoader = new PluginClassLoader(
						arrayOfURL, Thread.currentThread()
								.getContextClassLoader());
				Thread.currentThread().setContextClassLoader(
						localPluginClassLoader);
			} catch (Exception localException) {
				localException.printStackTrace();
			}
			super.contextInitialized(paramServletContextEvent);
		}
	}

	public void parsePluginFolder(ArrayList<URL> paramArrayList, File paramFile) {
		this.log.info("Plugins: looking in: " + paramFile.getAbsolutePath());
		File[] arrayOfFile = paramFile.listFiles();
		int i = arrayOfFile.length;
		for (int j = 0; j < i; ++j) {
			File localFile = arrayOfFile[j];
			if (localFile.isDirectory())
				try {
					_$1(paramArrayList, localFile.getAbsolutePath());
				} catch (MalformedURLException localMalformedURLException) {
					this.log.warn(localMalformedURLException.toString());
				}
		}
	}

	private void _$1(ArrayList<URL> paramArrayList, String paramString)
			throws MalformedURLException {
		File localFile = new File(paramString + "/lib");
		File[] arrayOfFile = localFile.listFiles(getFilter());
		if (AppUtils.isNotBlank(arrayOfFile)) {
			int i = arrayOfFile.length;
			for (int j = 0; j < i; ++j) {
				File localObject2 = arrayOfFile[j];
				paramArrayList.add(localObject2.toURI().toURL());
			}
		}
		Object localObject1 = new File(paramString + "/classes");
		if (localObject1 != null)
			paramArrayList.add(((File) localObject1).toURI().toURL());
	}

	private FileFilter getFilter() {
		PluginFilter local = new PluginFilter();
		return local;
	}
}