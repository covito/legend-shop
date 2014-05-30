package com.legendshop.central;

import com.legendshop.central.license.HealthCheckImpl;
import javax.servlet.ServletContext;

public class HealthCheckerHolder {
	private static HealthCheckerHolder instance = null;
	private static boolean isStart = false;

	private HealthCheckerHolder(ServletContext paramServletContext) {
		if (!(isStart)) {
			new Thread(new HealthCheckImpl(paramServletContext)).start();
			isStart = true;
		}
	}

	public static synchronized boolean isInitialized(
			ServletContext paramServletContext) {
		if (instance == null) {
			instance = new HealthCheckerHolder(paramServletContext);
		}
		return (instance != null);
	}
}