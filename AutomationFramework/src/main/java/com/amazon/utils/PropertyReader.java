package com.amazon.utils;

import java.util.EnumMap;
import java.util.Map;

import org.aeonbits.owner.ConfigFactory;

import com.amazon.enums.ConfigProperties;
import com.amazon.owner.FrameworkConfig;
/**
 * Utility class to read Config.properties file using <strong>Config</strong> Interface from <strong>Owner</strong> library
 * 
 * @since 18-Oct-2022
 * @author Suganya Jothiramalingam
 * @see FrameworkConfig
 * @see ConfigProperties
 */
public final class PropertyReader {
	
	/**
	 * Private Constructor to prevent external initialization of the class
	 */
	private PropertyReader() {
		
	}
	
	/**
	 * Variable to declare and initialize a HashMap to store data from Properties file as Key-Value pair
	 */
	private static final Map<ConfigProperties,Object> CONFIGMAP=new EnumMap<>(ConfigProperties.class);

	/**
	 * Static block to load the Enum map with all values of the keys as one-time activity
	 */
	static {
		FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);
		CONFIGMAP.put(ConfigProperties.AMAZONURL,config.amazonurl());
		CONFIGMAP.put(ConfigProperties.OVERRIDEREPORTS,config.overridereports());
		CONFIGMAP.put(ConfigProperties.PASSEDSTEPSCREENSHOT,config.passedstepscreenshot());
		CONFIGMAP.put(ConfigProperties.FAILEDSTEPSCREENSHOT,config.failedstepscreenshot());
		CONFIGMAP.put(ConfigProperties.SKIPPEDSTEPSCREENSHOT,config.skippedstepscreenshot());
		CONFIGMAP.put(ConfigProperties.RUNMODE,config.runmode());
		CONFIGMAP.put(ConfigProperties.REMOTEDRIVERURL,config.remotedriverurl());
	}
	
	/**
	 * Method to retrieve value for any key from Config Map
	 * 
	 * @param key Key for which the value needs to be fetched
	 * @return Value for the key passed as parameter
	 */
	public static Object getPropertValue(ConfigProperties key) {		
		return CONFIGMAP.get(key);
	}
}
