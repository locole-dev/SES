package vn.com.telsoft.websheet.api.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;
import org.apache.log4j.Layout;

import java.util.Date;

public class LoggerLayout extends LayoutBase<ILoggingEvent> {

	@Override
	public String doLayout(ILoggingEvent event) {
		StringBuffer result = new StringBuffer(128);
		result.append(event.getMessage());
		result.append(" ");
		result.append("|");
		result.append(" ");
		result.append(new Date(event.getTimeStamp()));
		result.append(Layout.LINE_SEP);
		return result.toString();
	}
}
