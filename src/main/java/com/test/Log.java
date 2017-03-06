package com.test;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
	public static Logger logger = Logger.getLogger("MyLog");
	static {
		FileHandler fh;
		try {

			// This block configure the logger with handler and formatter
			new File("Log").mkdir();
			fh = new FileHandler("Log/MyLogFile.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.setUseParentHandlers(false);
			// the following statement is used to log any messages
			logger.info("My first log");

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
