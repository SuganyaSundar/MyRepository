package com.amazon.enums;

import com.amazon.factories.ExplicitWaitFactory;

/**
 * Custom Enum with the list of Wait Strategies used for Explicit wait implementation for action on WebElements
 * 
 * @since 05-Oct-2022
 * @author Suganya Jothiramalingam
 * @see ExplicitWaitFactory
 */

public enum WaitStrategy {
	CLICKABLE,
	PRESENCE,
	VISIBLE,
	NONE;
}
