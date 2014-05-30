package com.legendshop.event.processor;

public class TaskRunner<T> implements Runnable {
	T clz;
	AbstractProcessor<T> processor;

	public TaskRunner(T paramT, AbstractProcessor<T> paramAbstractProcessor) {
		this.clz = paramT;
		this.processor = paramAbstractProcessor;
	}

	public void run() {
		this.processor.process(this.clz);
	}
}