package com.qaforum.validation;

import java.util.ArrayList;
import java.util.List;

import com.qaforum.bo.QaInfoBO;

/**
 * 
 * @author cdacr
 *
 */
public final class QaInfoValidate {

	/**
	 * 
	 * @param qaInfo 
	 * @return List of error messages 
	 */
	public List<String> validateSave(final QaInfoBO qaInfo) {
		final List<String> errorList = new ArrayList<String>();

		if (qaInfo == null) {
			errorList.add("No input data available");
		} else {
			final String question = qaInfo.getQuestion();
			if (question == null || question.equals("")) {
				errorList.add("Invalid question value");
			}
			final String answer = qaInfo.getAnswer();
			if (answer == null || answer.equals("")) {
				errorList.add("Invalid answer value");
			}
		}

		return errorList;
	}

}
