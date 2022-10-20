package com.amazon.owner;

import org.aeonbits.owner.Config;
/**
 * Interface for reading Config Properties file using <strong>Config</strong> Interface from <strong>Owner</strong> library
 * 
 * @since 18-Oct-2022
 * @author Suganya Jothiramalingam
 */
@Config.Sources(value="file:${user.dir}/src/test/resources/config/config.properties")
public interface FrameworkConfig extends Config{
	
	/**
	 * Method to retrieve value for the key amazonurl from properties file
	 * @return amazonurl value
	 */
	String amazonurl();
	/**
	 * Method to retrieve value for the key overridereports from properties file
	 * @return overridereports value
	 */
	boolean overridereports();
	/**
	 * Method to retrieve value for the key passedstepscreenshot from properties file
	 * @return passedstepscreenshot value
	 */
	boolean passedstepscreenshot();
	/**
	 * Method to retrieve value for the key failedstepscreenshot from properties file
	 * @return failedstepscreenshot value
	 */
	boolean failedstepscreenshot();
	/**
	 * Method to retrieve value for the key skippedstepscreenshot from properties file
	 * @return skippedstepscreenshot value
	 */
	boolean skippedstepscreenshot();
	/**
	 * Method to retrieve value for the key runmode from properties file - remote/local
	 * @return runmode value
	 */
	String runmode();
	
	/**
	 * Method to retrieve value for the key remotedriverurl from properties file
	 * @return remotedriverurl value
	 */
	String remotedriverurl();
}
