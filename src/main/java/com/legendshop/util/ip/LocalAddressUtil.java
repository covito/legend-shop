package com.legendshop.util.ip;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class LocalAddressUtil {
	public static LocalAddress getLocalAddress() {
		InetAddress localInetAddress = null;
		LocalAddress localLocalAddress = null;
		try {
			Enumeration localEnumeration1 = NetworkInterface
					.getNetworkInterfaces();
			while (localEnumeration1.hasMoreElements()) {
				NetworkInterface localNetworkInterface = (NetworkInterface) localEnumeration1
						.nextElement();
				Enumeration localEnumeration2 = localNetworkInterface
						.getInetAddresses();
				while (true) {
					do {
						if (!(localEnumeration2.hasMoreElements())) {

						}
						localInetAddress = (InetAddress) localEnumeration2
								.nextElement();
					} while ((localInetAddress == null)
							|| (localInetAddress.isLoopbackAddress())
							|| (!(localInetAddress instanceof Inet4Address)));
					localLocalAddress = new LocalAddress(
							localInetAddress.getHostAddress(),
							localInetAddress.getHostName());
				}
			}
		} catch (Exception localException) {
		}
		return localLocalAddress;
	}

	public static boolean isInnerIP(String paramString) {
		boolean i = false;
		long l1 = _$1(paramString);
		long l2 = _$1("10.0.0.0");
		long l3 = _$1("10.255.255.255");
		long l4 = _$1("172.16.0.0");
		long l5 = _$1("172.31.255.255");
		long l6 = _$1("192.168.0.0");
		long l7 = _$1("192.168.255.255");
		i = ((_$1(l1, l2, l3)) || (_$1(l1, l4, l5)) || (_$1(l1, l6, l7)) || (paramString
				.equals("127.0.0.1"))) ? true : false;
		return i;
	}

	private static long _$1(String paramString) {
		String[] arrayOfString = paramString.split("\\.");
		long l1 = Integer.parseInt(arrayOfString[0]);
		long l2 = Integer.parseInt(arrayOfString[1]);
		long l3 = Integer.parseInt(arrayOfString[2]);
		long l4 = Integer.parseInt(arrayOfString[3]);
		long l5 = l1 * 256L * 256L * 256L + l2 * 256L * 256L + l3 * 256L + l4;
		return l5;
	}

	private static boolean _$1(long paramLong1, long paramLong2, long paramLong3) {
		return ((paramLong1 >= paramLong2) && (paramLong1 <= paramLong3));
	}
}