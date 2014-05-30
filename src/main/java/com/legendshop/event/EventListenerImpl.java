package com.legendshop.event;

import com.legendshop.event.processor.Processor;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventListenerImpl<T> extends AbstractEventListenerImpl<T> {
	private final Logger logger = LoggerFactory.getLogger(EventListenerImpl.class);
	private List<Processor<T>> processor;

	public void onEvent(SystemEvent<T> paramSystemEvent) {
		if (this.processor != null) {
			Iterator localIterator = this.processor.iterator();
			while (localIterator.hasNext()) {
				Processor localProcessor = (Processor) localIterator.next();
				if (localProcessor.isSupport(paramSystemEvent.getSource())) {
					this.logger.debug("processor {} calling, eventId {}",
							localProcessor, paramSystemEvent.getEventId());
					localProcessor.onEvent(paramSystemEvent.getSource());
				}
			}
		}
	}

	public void setProcessors(List<Processor<T>> paramList) {
		this.processor = paramList;
	}
}