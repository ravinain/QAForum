/**
 * 
 */
package com.qaforum.util;

/**
 * @author cdacr
 * 
 */
public enum Operator {
	/** */
	EQUALS("equals"),
	/** */
	STARTS_WITH("startsWith"),
	/** */
	ENDS_WITH("endsWith"),
	/** */
	CONTAINS("contains"),
	/** */
	GREATER_THAN("greaterThan"),
	/** */
	LESS_THAN("lessThan");

	/** */
	private String string;

	/**
	 * 
	 * @param string 
	 */
	Operator(final String string) {
		this.string = string;
	}

	/**
	 * 
	 * @return string value
	 */
	public String getString() {
		return string;
	}
}
