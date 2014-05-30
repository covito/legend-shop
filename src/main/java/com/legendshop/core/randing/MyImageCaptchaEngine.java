package com.legendshop.core.randing;

import com.octo.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import java.awt.Color;
import java.awt.Font;

public class MyImageCaptchaEngine extends ListImageCaptchaEngine
{
  protected void buildInitialFactories()
  {
    RandomWordGenerator localRandomWordGenerator = new RandomWordGenerator("abcdefghijklmnopqrstuvwxyz123456789");
    RandomRangeColorGenerator localRandomRangeColorGenerator = new RandomRangeColorGenerator(new int[] { 0, 100 }, new int[] { 0, 100 }, new int[] { 0, 100 });
    RandomTextPaster localRandomTextPaster = new RandomTextPaster(new Integer(4), new Integer(4), localRandomRangeColorGenerator, Boolean.valueOf(true));
    GradientBackgroundGenerator localGradientBackgroundGenerator = new GradientBackgroundGenerator(new Integer(80), new Integer(25), Color.gray, Color.white);
    Font[] arrayOfFont = { new Font("Arial", 0, 10), new Font("Tahoma", 0, 10), new Font("Verdana", 0, 10) };
    RandomFontGenerator localRandomFontGenerator = new RandomFontGenerator(new Integer(15), new Integer(16), arrayOfFont);
    ComposedWordToImage localComposedWordToImage = new ComposedWordToImage(localRandomFontGenerator, localGradientBackgroundGenerator, localRandomTextPaster);
    addFactory(new GimpyFactory(localRandomWordGenerator, localComposedWordToImage));
  }
}