package com.legendshop.util.ip;

public class LocalAddress {
	public String ip;
	public String hostName;

	public LocalAddress(String paramString1, String paramString2) {
		this.ip = paramString1;
		this.hostName = paramString2;
	}

	public String toString() {
		return "ip:" + this.ip + ", hostname:" + this.hostName;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String paramString) {
		this.ip = paramString;
	}

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String paramString) {
		this.hostName = paramString;
	}
}