/**
 * 
 */
package com.qaforum.service;

import com.qaforum.bo.LoginInfoBO;

/**
 * @author cdacr
 *
 */
public interface LoginInfoService {
	/** */
	String LOGIN_LKP = "java:global/RestTest/LoginInfoBean";

	/**
	 * 
	 * @param loginBo 
	 * @return {@link LoginInfoBO}
	 */
	LoginInfoBO save(LoginInfoBO loginBo);

	/**
	 * 
	 * @param loginId 
	 */
	void delete(String loginId);

	/**
	 * 
	 * @param loginId 
	 * @return {@link LoginInfoBO}
	 */
	LoginInfoBO getLoginInfo(String loginId);

	/**
	 * 
	 * @param loginBo 
	 * @return TRUE|FALSE
	 */
	boolean isAuthenticatedUser(LoginInfoBO loginBo);

	/**
	 * Method updates session id again login id in DB.
	 * @param loginId 
	 * @param sessionId 
	 */
	void updateSessionId(String loginId, String sessionId);
}
