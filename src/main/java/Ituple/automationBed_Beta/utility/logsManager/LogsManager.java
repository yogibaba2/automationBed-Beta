package Ituple.automationBed_Beta.utility.logsManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsManager {
	
	private static Logger _logger;
	
	
	public static Logger createLogger(String appender) {
	    if (_logger == null) {
	        _logger = LogManager.getLogger(appender);
	        return _logger;
	    } else
	        return _logger;
	}
}
