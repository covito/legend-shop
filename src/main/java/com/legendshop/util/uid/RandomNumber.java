package com.legendshop.util.uid;

import java.io.PrintStream;
import java.util.Random;

public class RandomNumber {
	private final int _$4;
	private final int _$3;
	private final int[] _$2;
	Random _$1 = new Random();

	public RandomNumber(int paramInt1, int paramInt2) {
		this._$4 = paramInt1;
		this._$3 = paramInt2;
		this._$2 = new int[this._$4];
		for (int i = 0; i < paramInt1; ++i)
			this._$2[i] = i;
	}

	public int[] getRamdomNumber() {
		int i = this._$4 - 1;
		for (int j = 0; j < this._$3; ++j) {
			int k = this._$1.nextInt(i);
			swap(k, i);
			--i;
		}
		int[] arrayOfInt = new int[this._$3];
		for (int k = 0; k < arrayOfInt.length; ++k)
			arrayOfInt[k] = this._$2[(this._$4 - 1 - k)];
		return arrayOfInt;
	}

	public void swap(int paramInt1, int paramInt2) {
		int i = this._$2[paramInt1];
		this._$2[paramInt1] = this._$2[paramInt2];
		this._$2[paramInt2] = i;
	}

	public static void main(String[] paramArrayOfString) {
		RandomNumber localRandomNumber = new RandomNumber(20, 6);
		for (int i = 0; i < 10; ++i) {
			int[] arrayOfInt = localRandomNumber.getRamdomNumber();
			StringBuilder localStringBuilder = new StringBuilder();
			for (int j = 0; j < arrayOfInt.length; ++j)
				localStringBuilder.append(arrayOfInt[j]).append(",");
			System.out.println(localStringBuilder.toString());
		}
	}
}