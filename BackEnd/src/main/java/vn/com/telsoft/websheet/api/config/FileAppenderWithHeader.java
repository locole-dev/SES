package vn.com.telsoft.websheet.api.config;

import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class FileAppenderWithHeader extends RollingFileAppender {
	private String header;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void openFile(String fileName) throws IOException
	{
		super.openFile(fileName);
		File activeFile = new File(getFile());
		if (activeFile.exists() && activeFile.isFile() && activeFile.length() == 0)
		{
			lock.lock();
			try
			{
				new PrintWriter(new OutputStreamWriter(getOutputStream(), StandardCharsets.UTF_8), true).println("         iccid        |          activationCode                |       imsi      |    activationStatus   |  iccidStatus  |  timestamp");
			}
			finally
			{
				lock.unlock();
			}
		}
	}
}
