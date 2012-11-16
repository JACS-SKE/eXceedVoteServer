package jacs.util;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class PropertyManager {
	private static final String PROPERTIES_FILE = "exceedvote.properties";
	private static Properties properties; // application properties
	private static Logger log;
	
	static {
		// load properties immediately
		loadProperties();
	}
	
	private static void loadProperties() {
		// read properties from a file
//		FileInputStream in = new FileInputStream(PROPERTIES_FILE);
		ClassLoader loader = PropertyManager.class.getClassLoader();
		InputStream in = loader.getResourceAsStream(PROPERTIES_FILE);
		
		properties = new Properties();
		if (in != null) try {
			properties.load(in);
		} catch (IOException ioe) {
			getLogger().fatal("Could not load properties ", ioe);
		}
	}
	
	public static String getProperty(String name) {
		return properties.getProperty(name);
	}
	
	public static Logger getLogger() {
		if (log == null) log = Logger.getLogger(PropertyManager.class);
		return log;
	}
}
