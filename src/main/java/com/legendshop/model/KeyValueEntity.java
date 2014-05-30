package com.legendshop.model;

import java.io.Serializable;

public class KeyValueEntity implements Serializable, Cloneable {
	private static final long serialVersionUID = 5568358970483740841L;
	private String key;
	private String value = "";

	public KeyValueEntity() {
	}

	public KeyValueEntity(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int hashCode() {
		int result = 17;
		result = 31 * result + this.key.hashCode();
		result = 31 * result + this.value.hashCode();
		return result;
	}

	public boolean equals(Object obj) {
		KeyValueEntity entity = null;
		if (!(obj instanceof KeyValueEntity)) {
			entity = (KeyValueEntity) obj;
		}

		return ((entity.getKey() != null) && (entity.getValue() != null)
				&& (entity.getKey().equals(this.key)) && (entity.getValue()
				.equals(this.value)));
	}

	public KeyValueEntity clone() {
		KeyValueEntity entity = new KeyValueEntity();
		entity.setKey(getKey());
		entity.setValue(getValue());

		return entity;
	}
}