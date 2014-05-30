package com.legendshop.core.listener;

import java.io.File;
import java.io.FileFilter;

class PluginFilter implements FileFilter {
	public boolean accept(File paramFile) {
		String str = paramFile.getAbsolutePath();
		return ((str.toLowerCase().endsWith(".jar")) || (str.toLowerCase()
				.endsWith(".zip")));
	}
}