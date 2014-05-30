package com.legendshop.util.handler;

import java.util.HashMap;

public class DefaultKeyMap<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = -1767115340400652866L;
	private String defaultKey;

	public DefaultKeyMap() {
	}

	public DefaultKeyMap(String defaultKey) {
		this.defaultKey = defaultKey;
	}

	public String getDefaultKey() {
		return this.defaultKey;
	}

	public void setDefaultKey(String defaultKey) {
		this.defaultKey = defaultKey;
	}

	public V get(Object key) {
		V localObject = super.get(key);
		if (localObject == null)
			localObject = super.get(this.defaultKey);
		return localObject;
	}
}