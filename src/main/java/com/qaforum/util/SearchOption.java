/**
 * 
 */
package com.qaforum.util;

/**
 * @author cdacr
 * 
 */
public enum SearchOption {
	/** */
	QUESTION_ID("questionId"),
	/** */
	QUESTION("question"),
	/** */
	ANSWER("answer"),
	/** */
	TYPE("type"),
	/** */
	ALL("all");

	/**
	 * 
	 */
	private String string;

	/**
	 * 
	 * @param string 
	 */
	SearchOption(final String string) {
		this.string = string;
	}

	/**
	 * 
	 * @return string
	 */
	public String getString() {
		return string;
	}

	@Override
	public String toString() {
		return string;
	}
}
