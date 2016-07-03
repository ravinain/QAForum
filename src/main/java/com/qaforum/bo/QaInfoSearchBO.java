package com.qaforum.bo;

import java.io.Serializable;

/**
 * @author cdacr
 * 
 */
public class QaInfoSearchBO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8297850390783932433L;
	/** */
	private String searchOption;
	/** */
	private String operator;
	/** */
	private String searchValue;

	/**
	 * @return the searchOption
	 */
	public final String getSearchOption() {
		return searchOption;
	}

	/**
	 * @param searchOption
	 *            the searchOption to set
	 */
	public final void setSearchOption(final String searchOption) {
		this.searchOption = searchOption;
	}

	/**
	 * @return the operator
	 */
	public final String getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public final void setOperator(final String operator) {
		this.operator = operator;
	}

	/**
	 * @return the searchValue
	 */
	public final String getSearchValue() {
		return searchValue;
	}

	/**
	 * @param searchValue
	 *            the searchValue to set
	 */
	public final void setSearchValue(final String searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public final String toString() {
		// TODO Auto-generated method stub
		return "Search option : " + searchOption + " Operator: " + operator
				+ " Value: " + searchValue;
	}

}
