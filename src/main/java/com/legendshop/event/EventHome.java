package com.legendshop.event;

import com.legendshop.util.AppUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class EventHome {
	private static Logger logger = LoggerFactory.getLogger(EventHome.class);
	private static Map<String, List<BaseEventListener>> listenerMap = new HashMap<String, List<BaseEventListener>>();
	private static boolean initFlag = false;

	public static synchronized Map<String, BaseEventListener> initBaseEventListener() {
		if (initFlag){
			return null;
		}
		
		
		Map<String, BaseEventListener> localMap = ContextLoader
				.getCurrentWebApplicationContext().getBeansOfType(
						BaseEventListener.class);
		
		
		if ((localMap == null) || (localMap.size() == 0)) {
			logger.info("no listener registered");
			return null;
		}
		
		logger.debug("EventHome initing");
		
		for(BaseEventListener b:localMap.values()){
			String eventId=b.getEventId();
			if (!AppUtils.isBlank(eventId)){
				List<BaseEventListener> list=listenerMap.get(eventId);
				if(list==null){
					list=new ArrayList<BaseEventListener>();
					listenerMap.put(eventId, list);
				}
				if(!list.contains(b)){
					list.add(b);
				}
			}
		}
		
		for(List<BaseEventListener> list:listenerMap.values()){
			Collections.sort(list, new EventListenerComparator());
		}
		
		initFlag = true;
		
		logger.debug("EventHome init finish");
		
		return localMap;
	}

	public static void publishEvent(SystemEvent event) {
		List<BaseEventListener> localList = listenerMap.get(event.getEventId().getEventId());
		if (localList != null) {
			Iterator<BaseEventListener> iterator = localList.iterator();
			while (iterator.hasNext()) {
				BaseEventListener listener =  iterator.next();
				listener.onEvent(event);
			}
		}
	}
}