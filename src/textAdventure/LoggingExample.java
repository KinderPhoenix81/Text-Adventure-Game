package textAdventure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingExample {
	final static Logger log = LogManager.getLogger(
			LoggingExample.class.getName());
	
	public static void main(String[] args) {
		log.debug("Hello World");
		log.info("Hello World [Info Message]");
	}

}