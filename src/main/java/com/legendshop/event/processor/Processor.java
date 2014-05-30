package com.legendshop.event.processor;

public abstract interface Processor<T> {
	public abstract boolean isSupport(T paramT);

	public abstract void onEvent(T paramT);
}