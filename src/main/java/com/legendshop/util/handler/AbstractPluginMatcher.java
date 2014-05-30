package com.legendshop.util.handler;

public abstract class AbstractPluginMatcher {
	protected String resource;
	protected String type;
	protected String value;

	public String getResource() {
		return this.resource;
	}

	public void setResource(String paramString) {
		this.resource = paramString;
	}

	public String getParsedResource() {
		return null;
	}

	public abstract boolean isMatch();

	public String getType() {
		return this.type;
	}

	public void setType(String paramString) {
		this.type = paramString;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String paramString) {
		this.value = paramString;
	}
}