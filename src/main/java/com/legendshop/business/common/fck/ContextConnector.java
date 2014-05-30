package com.legendshop.business.common.fck;

import com.legendshop.core.UserManager;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.exception.InvalidFormatException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.fckeditor.connector.exception.InvalidCurrentFolderException;
import net.fckeditor.connector.exception.WriteException;
import net.fckeditor.connector.impl.LocalConnector;
import net.fckeditor.handlers.ResourceType;
import net.fckeditor.requestcycle.ThreadLocalData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextConnector extends LocalConnector {
	private final Logger log = LoggerFactory.getLogger(ContextConnector.class);
	private List<String> flashFileType;

	public String fileUpload(ResourceType type, String currentFolder,
			String fileName, InputStream inputStream)
			throws InvalidCurrentFolderException, WriteException {
		String userName = UserManager.getUserName(ThreadLocalData.getRequest());
		if (userName == null)
			throw new AuthorizationException("did not logon yet!");

		String name = fileName;
		try {
			int size = inputStream.available();
			if ((size >= 0)
					&& (size <= ((Long) PropertiesUtil.getObject(
							SysParameterEnum.MAX_FILE_SIZE, Long.class))
							.longValue()))
				throw new RuntimeException(
						"File is 0 or File Size exceeded MAX_FILE_SIZE: "
								+ size);
		} catch (IOException e) {
			this.log.error("fileUpload error", e);

			String extName = FileProcessor.getFileExtName(name);
			String fileType = null;
			if (isPic(extName))
				fileType = "/image/";
			else if (isFlash(extName))
				fileType = "/flash/";

			if (fileType != null) {
				String path = PropertiesUtil.getBigFilesAbsolutePath() + "/"
						+ userName + "/editor" + fileType + currentFolder;
				FileProcessor.uploadFile(inputStream, path, "", fileName, true,
						false);
				return name;
			}
			throw new InvalidFormatException("Invalidate File:" + name);
		}
		return null;
	}

	private boolean isPic(String extName) {
		List list = (List) PropertiesUtil.getObject(
				SysParameterEnum.ALLOWED_UPLOAD_FILE_TPYE, List.class);

		return (list.contains(extName));
	}

	private boolean isFlash(String extName) {
		if (this.flashFileType == null) {
			this.flashFileType = new ArrayList();
			this.flashFileType.add(".swf");
		}

		return (this.flashFileType.contains(extName));
	}
}