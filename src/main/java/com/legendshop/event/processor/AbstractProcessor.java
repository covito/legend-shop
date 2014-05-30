package com.legendshop.event.processor;

public abstract class AbstractProcessor<T> implements Processor<T> {
	public boolean isSupport(T paramT) {
		return true;
	}

	public abstract void process(T paramT);
}