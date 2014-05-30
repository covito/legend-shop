package com.legendshop.util.uid;

import java.util.UUID;

public class UID {

	public static String getUID() {
		return UUID.randomUUID().toString();
	}

	private static int _$1() {
		return new Object().hashCode();
	}

	public static void main(String[] paramArrayOfString) {
		for (int i = 0; i < 100000000; ++i) {
			String str = getUID();
			System.out.println(i + "=" + str);
		}
	}
}