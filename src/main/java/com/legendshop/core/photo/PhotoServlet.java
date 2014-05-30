package com.legendshop.core.photo;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;

public class PhotoServlet extends AbstractPhotoServlet {
	private static final long serialVersionUID = -7666260687183404653L;
	Logger _$3 = LoggerFactory.getLogger(PhotoServlet.class);
	private String _$2;

	public void init() throws ServletException {
		this._$2 = PropertiesUtil.getBigFilesAbsolutePath();
	}

	public void doGet(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException {
		paramHttpServletResponse.setContentType("image/jpeg");
		paramHttpServletResponse.setDateHeader("expires",
				System.currentTimeMillis() + 60000L);
		paramHttpServletResponse.addHeader("Cache-Control", "max-age=60");
		String str1 = paramHttpServletRequest.getPathInfo();
		String str2 = this._$2 + str1;
		try {
			Object localObject1;
			File localFile = new File(str2);
			if (!(localFile.isFile()))
				localFile = new File(PropertiesUtil.getSystemRealPath()
						+ NO_FILE_REPLACEMENT);
			if (localFile.isFile()) {
				localObject1 = null;
				try {
					paramHttpServletResponse.setContentType("image/gif");
					paramHttpServletResponse.setContentLength((int) localFile
							.length());
					localObject1 = paramHttpServletResponse.getOutputStream();
					dumpFile(localFile, (OutputStream) localObject1);
				} catch (Exception localException4) {
					this._$3.error("doGet: {}",
							localException4.getLocalizedMessage());
				} finally {
					if (localObject1 != null)
						try {
							((ServletOutputStream) localObject1).close();
						} catch (Exception localException6) {
							this._$3.error("clos servletoutputstream: {}",
									localException6.getLocalizedMessage());
						}
				}
			} else {
				localObject1 = null;
				try {
					paramHttpServletResponse.setContentType("text/html");
					localObject1 = paramHttpServletResponse.getWriter();
					((PrintWriter) localObject1).println("<html>");
					((PrintWriter) localObject1)
							.println("<br><br>Could not get file name ");
					((PrintWriter) localObject1)
							.println("<br><br>&copy; <a href=\"mailto:"
									+ ((String) PropertiesUtil.getObject(
											SysParameterEnum.SUPPORT_MAIL_LIST,
											String.class))
									+ "\">LegendShop</a>");
					((PrintWriter) localObject1).println("</html>");
				} catch (Exception localException5) {
					localException5.printStackTrace();
				} finally {
					if (localObject1 != null) {
						((PrintWriter) localObject1).flush();
						((PrintWriter) localObject1).close();
					}
				}
				this._$3.warn("there is no image file named {}", str2);
			}
		} catch (Exception localException1) {
			this._$3.error("PhotoServlet doGet: {}",
					localException1.getLocalizedMessage());
		}
	}

	public void destroy() {
		System.out.println("PicServlet destroying");
	}
}