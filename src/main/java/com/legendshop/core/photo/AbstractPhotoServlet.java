package com.legendshop.core.photo;

import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = -2064158852379937245L;
	private static Logger _$1 = LoggerFactory
			.getLogger(AbstractPhotoServlet.class);
	protected static String NO_FILE_REPLACEMENT = "images/blank.png";

	public synchronized void outLogo(File paramFile, String paramString,
			OutputStream paramOutputStream, int paramInt1, int paramInt2)
			throws IOException {
		int k;
		int l;
		BufferedInputStream localBufferedInputStream = new BufferedInputStream(
				new FileInputStream(paramFile), 4096);
		BufferedImage localBufferedImage1 = ImageIO
				.read(localBufferedInputStream);
		int i = localBufferedImage1.getWidth(null);
		int j = localBufferedImage1.getHeight(null);
		if ((i > paramInt1) || (j > paramInt2)) {
			if (i / paramInt1 >= j / paramInt2) {
				k = paramInt1;
				l = paramInt2;
			} else {
				l = paramInt2;
				k = paramInt1;
			}
		} else {
			k = i;
			l = j;
		}
		BufferedImage localBufferedImage2 = new BufferedImage(k, l, 1);
		localBufferedImage2.getGraphics().drawImage(localBufferedImage1, 0, 0,
				k, l, null);
		JPEGImageEncoder localJPEGImageEncoder1 = JPEGCodec
				.createJPEGEncoder(paramOutputStream);
		localJPEGImageEncoder1.encode(localBufferedImage2);
		localBufferedInputStream.close();
		paramOutputStream.flush();
		paramOutputStream.close();
		if (paramString != null) {
			_$1.debug("generate small image url  {}", paramString);
			File localFile = new File(paramString.substring(0,
					paramString.lastIndexOf("/")));
			if (!(localFile.exists()))
				localFile.mkdirs();
			FileOutputStream localFileOutputStream = null;
			try {
				localFileOutputStream = new FileOutputStream(paramString);
				JPEGImageEncoder localJPEGImageEncoder2 = JPEGCodec
						.createJPEGEncoder(localFileOutputStream);
				localJPEGImageEncoder2.encode(localBufferedImage2);
			} catch (Exception localException) {
				localException.printStackTrace();
			} finally {
				if (localFileOutputStream != null)
					localFileOutputStream.close();
			}
		}
		localBufferedImage1 = null;
	}

	protected void outputFile(HttpServletResponse paramHttpServletResponse,
			File paramFile) throws IOException {
		paramHttpServletResponse.setContentType("image/gif");
		paramHttpServletResponse.setContentLength((int) paramFile.length());
		ServletOutputStream localServletOutputStream = paramHttpServletResponse
				.getOutputStream();
		try {
			dumpFile(paramFile, localServletOutputStream);
		} catch (Exception localException) {
			_$1.error("outputFile: {}", localException.getLocalizedMessage());
		} finally {
			localServletOutputStream.close();
		}
	}

	protected void dumpFile(File paramFile, OutputStream paramOutputStream)
			throws IOException {
		byte[] arrayOfByte = new byte[4096];
		BufferedInputStream localBufferedInputStream = null;
		try {
			localBufferedInputStream = new BufferedInputStream(
					new FileInputStream(paramFile));
			int len;
			while ((len = localBufferedInputStream.read(arrayOfByte)) != -1) {
				paramOutputStream.write(arrayOfByte, 0, len);
			}
		} catch (Exception localException) {
			_$1.error("dumpFile: {}", localException.getLocalizedMessage());
		} finally {
			if (paramOutputStream != null)
				paramOutputStream.close();
			if (localBufferedInputStream != null)
				localBufferedInputStream.close();
		}
	}

	protected void noFileError(HttpServletResponse paramHttpServletResponse,
			String paramString) {
		PrintWriter localPrintWriter = null;
		try {
			paramHttpServletResponse.setContentType("text/html");
			localPrintWriter = paramHttpServletResponse.getWriter();
			localPrintWriter.println("<html>");
			localPrintWriter.println("<br><br>Could not get file name ");
			localPrintWriter.println("<br><br>&copy; <a href=\"mailto:"
					+ ((String) PropertiesUtil.getObject(
							SysParameterEnum.SUPPORT_MAIL_LIST, String.class))
					+ "\">LegendShop</a>");
			localPrintWriter.println("</html>");
		} catch (Exception localException) {
			_$1.error("noFileError", localException);
		} finally {
			if (localPrintWriter != null) {
				localPrintWriter.flush();
				localPrintWriter.close();
			}
		}
		_$1.error("there is no image file named {}", paramString);
	}
}