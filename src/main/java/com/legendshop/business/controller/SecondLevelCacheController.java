package com.legendshop.business.controller;

import java.io.File;
import java.io.Serializable;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Ehcache;
import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.cache.LegendCache;
import com.legendshop.core.cache.MemCachedManager;
import com.legendshop.core.cache.MemcachedCache;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.LegendFilter;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.util.AppUtils;
import com.legendshop.util.FileTimeWrapper;

@Controller
public class SecondLevelCacheController extends BaseController {

	@Autowired(required = false)
	private CacheManager cacheManager;

	@RequestMapping({ "/admin/system/cache/query" })
	public String query(HttpServletRequest request, HttpServletResponse response) {
		List cacheList = null;
		if (this.cacheManager instanceof MemCachedManager)
			cacheList = parseMemcache(this.cacheManager);
		else
			cacheList = parseEhcache(this.cacheManager);

		request.setAttribute("cacheList", cacheList);

		return PathResolver
				.getPath(request, response, BackPage.CACHE_LIST_PAGE);
	}

	@RequestMapping({ "/admin/cache/queryhtml" })
	public String queryHtml(HttpServletRequest request,
			HttpServletResponse response) {
		String requestFolder = request.getParameter("requestFolder");
		String currentUserName = "";
		if (requestFolder == null) {
			requestFolder = LegendFilter.HTML_PATH;
			if (!(FoundationUtil.haveViewAllDataFunction(request))) {
				currentUserName = UserManager.getUserName(request);
				requestFolder = requestFolder + currentUserName;
			}
		} else {
			String direct = request.getParameter("direct");
			if (AppUtils.isNotBlank(direct))
				requestFolder = requestFolder.substring(0,
						requestFolder.lastIndexOf("/"));

		}

		File dir = new File(requestFolder);
		File[] files = (File[]) null;
		if (dir.isDirectory())
			files = dir.listFiles();

		if (files != null) {
			FileTimeWrapper[] fileWrappers = new FileTimeWrapper[files.length];
			for (int j = 0; j < files.length; ++j) {
				File file = files[j];
				FileTimeWrapper fileTimeWrapper = new FileTimeWrapper(file);
				fileTimeWrapper.setIsFile(Boolean.valueOf(file.isFile()));
				fileTimeWrapper.setFileName(file.getName());
				fileWrappers[j] = fileTimeWrapper;
			}
			Arrays.sort(fileWrappers);
			request.setAttribute("fileList", fileWrappers);
		}
		request.setAttribute("requestFolder", requestFolder);
		String requestPath = null;
		if (AppUtils.isNotBlank(currentUserName))
			requestPath = LegendFilter.HTML_PATH + currentUserName;
		else
			requestPath = LegendFilter.HTML_PATH;

		if (requestPath.equals(requestFolder)) {
			request.setAttribute("requestPath", currentUserName);
			request.setAttribute("rootPath", Boolean.valueOf(true));
		} else {
			request.setAttribute("requestPath",
					requestFolder.substring(LegendFilter.HTML_PATH.length()));
		}
		return PathResolver.getPath(request, response, BackPage.HTML_LIST_PAGE);
	}

	@RequestMapping({ "/admin/system/cache/clear" })
	public String clear(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String id) {
		return PathResolver.getPath(request, response,
				FowardPage.PARAM_LIST_QUERY);
	}

	@RequestMapping({ "/admin/system/cache/removeall" })
	@ResponseBody
	public String clearSecondLevelCache() {
		Collection cacheNames;
		try {
			cacheNames = this.cacheManager.getCacheNames();
			for (Iterator localIterator = cacheNames.iterator(); localIterator
					.hasNext();) {
				String cacheName = (String) localIterator.next();
				Cache cache = this.cacheManager.getCache(cacheName);
				if (cache == null) {
					continue;
				}
				System.out.println("clear cache " + cache.getName());
				cache.clear();
			}

			return null;
		} catch (Exception e) {
		}
		return "fail";
	}

	private List<KeyValueEntity> parseMemcache(CacheManager cacheManager) {
		List result = new ArrayList();
		Collection cacheNames = cacheManager.getCacheNames();

		if (AppUtils.isNotBlank(cacheNames)) {
			Iterator localIterator1 = cacheNames.iterator();
			while (localIterator1.hasNext()) {
				String cacheName = (String) localIterator1.next();
				MemcachedClient cache = ((MemcachedCache) cacheManager
						.getCache(cacheName)).getNativeCache();
				if (cache != null) {
					Map map = cache.getStats();
					Set socketAddresses = map.keySet();

					for (Iterator localIterator2 = socketAddresses.iterator(); localIterator2
							.hasNext();) {
						SocketAddress address = (SocketAddress) localIterator2
								.next();
						KeyValueEntity entity = new KeyValueEntity();
						entity.setKey(cacheName + ":" + address.toString());
						Map valueMap = (Map) map.get(address);
						Set keyValue = valueMap.keySet();
						if (keyValue != null) {
							StringBuilder sb = new StringBuilder();
							for (Iterator localIterator3 = keyValue.iterator(); localIterator3
									.hasNext();) {
								String key = (String) localIterator3.next();
								String valueValue = (String) valueMap.get(key);
								sb.append(valueValue).append(",");
							}
							entity.setValue(sb.toString());
						}
						result.add(entity);
					}
				}
			}
		}

		return result;
	}

	private List<KeyValueEntity> parseEhcache(CacheManager cacheManager) {
		List result = new ArrayList();
		Collection cacheNames = cacheManager.getCacheNames();
		if (AppUtils.isNotBlank(cacheNames))
			for (Iterator localIterator = cacheNames.iterator(); localIterator
					.hasNext();) {
				String cacheName = (String) localIterator.next();
				KeyValueEntity entity = new KeyValueEntity();
				entity.setKey(cacheName);
				Ehcache cache = ((LegendCache) cacheManager.getCache(cacheName))
						.getNativeCache();
				List keys = cache.getKeys();
				if (AppUtils.isNotBlank(keys)) {
					StringBuilder sb = new StringBuilder(
							((Serializable) keys.get(0)).toString());
					for (int i = 1; i < keys.size(); ++i)
						sb.append(",").append(keys.get(i));

					entity.setValue(sb.toString());
				}

				result.add(entity);
			}

		return result;
	}
}