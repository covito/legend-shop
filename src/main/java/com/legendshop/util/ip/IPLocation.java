package com.legendshop.util.ip;

class IPLocation {
	public String country = this.area = "";
	public String area;

	public IPLocation getCopy() {
		IPLocation ret = new IPLocation();
		ret.country = country;
		ret.area = area;
		return ret;
	}
}