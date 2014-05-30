package com.legendshop.core.randing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Image {
	public final int STRING_LENGTH = 4;

	public Color getRandColor(int paramInt1, int paramInt2) {
		Random localRandom = new Random();
		if (paramInt1 > 255)
			paramInt1 = 255;
		if (paramInt2 > 255)
			paramInt2 = 255;
		int i = paramInt1 + localRandom.nextInt(paramInt2 - paramInt1);
		int j = paramInt1 + localRandom.nextInt(paramInt2 - paramInt1);
		int k = paramInt1 + localRandom.nextInt(paramInt2 - paramInt1);
		return new Color(i, j, k);
	}

	public BufferedImage creatImage(String paramString) {
		int i = 70;
		int j = 20;
		BufferedImage localBufferedImage = new BufferedImage(i, j, 1);
		Graphics localGraphics = localBufferedImage.getGraphics();
		Random localRandom = new Random();
		localGraphics.setColor(new Color(255, 244, 175));
		localGraphics.fillRect(0, 0, i, j);
		localGraphics.setFont(new Font("Arial", 2, 20));
		localGraphics.setColor(getRandColor(160, 200));
		for (int k = 0; k < 200; ++k) {
			int l = localRandom.nextInt(i);
			int i1 = localRandom.nextInt(j);
			int i2 = localRandom.nextInt(15);
			int i3 = localRandom.nextInt(15);
			localGraphics.drawLine(l, i1, l + i2, i1 + i3);
		}
		for (int k = 0; k < paramString.length(); ++k) {
			String str = String.valueOf(paramString.charAt(k));
			localGraphics.setColor(new Color(20 + localRandom.nextInt(110),
					20 + localRandom.nextInt(110), 20 + localRandom
							.nextInt(110)));
			localGraphics.drawString(str, 15 * k + 6, 16);
		}
		localGraphics.dispose();
		return localBufferedImage;
	}
}