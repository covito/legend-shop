package com.legendshop.util.ip;

public class IPEntry {
	public String beginIp = this.endIp = this.country = this.area = "";
	public String endIp;
	public String country;
	public String area;

	public String toString() {
		return this.area + "  " + this.country + "IP范围:" + this.beginIp + "-"
				+ this.endIp;
	}
}