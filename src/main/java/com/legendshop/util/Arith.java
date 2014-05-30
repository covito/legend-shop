package com.legendshop.util;

import java.math.BigDecimal;

public class Arith {
	private static final int ROUNDING_MODE = 10;

	public static double add(double paramDouble1, double paramDouble2) {
		BigDecimal localBigDecimal1 = new BigDecimal(
				Double.toString(paramDouble1));
		BigDecimal localBigDecimal2 = new BigDecimal(
				Double.toString(paramDouble2));
		return localBigDecimal1.add(localBigDecimal2).doubleValue();
	}

	public static double sub(double paramDouble1, double paramDouble2) {
		BigDecimal localBigDecimal1 = new BigDecimal(
				Double.toString(paramDouble1));
		BigDecimal localBigDecimal2 = new BigDecimal(
				Double.toString(paramDouble2));
		return localBigDecimal1.subtract(localBigDecimal2).doubleValue();
	}

	public static double mul(double paramDouble1, double paramDouble2) {
		BigDecimal localBigDecimal1 = new BigDecimal(
				Double.toString(paramDouble1));
		BigDecimal localBigDecimal2 = new BigDecimal(
				Double.toString(paramDouble2));
		return localBigDecimal1.multiply(localBigDecimal2).doubleValue();
	}

	public static double div(double paramDouble1, double paramDouble2) {
		return div(paramDouble1, paramDouble2, ROUNDING_MODE);
	}

	public static double div(double paramDouble1, double paramDouble2,
			int paramInt) {
		if (paramInt < 0)
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		BigDecimal localBigDecimal1 = new BigDecimal(
				Double.toString(paramDouble1));
		BigDecimal localBigDecimal2 = new BigDecimal(
				Double.toString(paramDouble2));
		return localBigDecimal1.divide(localBigDecimal2, paramInt, 4)
				.doubleValue();
	}

	public static double round(double paramDouble, int paramInt) {
		if (paramInt < 0)
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		BigDecimal localBigDecimal1 = new BigDecimal(
				Double.toString(paramDouble));
		BigDecimal localBigDecimal2 = new BigDecimal("1");
		return localBigDecimal1.divide(localBigDecimal2, paramInt, 4)
				.doubleValue();
	}

	public static void main(String[] paramArrayOfString) {
		System.out.println(0.060000000000000005D);
		System.out.println(add(0.050000000000000003D, 0.01D));
		System.out.println(0.58000000000000007D);
		System.out.println(sub(1D, 0.41999999999999998D));
		System.out.println(401.49999999999994D);
		System.out.println(mul(4.0149999999999997D, 100.0D));
		System.out.println(1.2329999999999999D);
		System.out.println(div(123.3D, 100.0D, 2));
	}
}