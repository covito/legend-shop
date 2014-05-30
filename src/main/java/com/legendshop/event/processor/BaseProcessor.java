package com.legendshop.event.processor;

public abstract class BaseProcessor<T> extends AbstractProcessor<T> {
	public void onEvent(T paramT) {
		process(paramT);
	}
}