package com.legendshop.model.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class AbstractEntity implements BaseEntity {
	private static final long serialVersionUID = 2382363811364535140L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public abstract String getUserName();
}