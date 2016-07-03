/**
 * 
 */
package com.qaforum.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.qaforum.bo.LoginInfoBO;
import com.qaforum.ejb.LoginInfoRemote;
import com.qaforum.service.LoginInfoService;

/**
 * @author cdacr
 *
 */
public final class LoginInfoServiceImpl implements LoginInfoService {
	/** */
	private LoginInfoRemote loginRemote;

	/** */
	private static final String CLS_NM = LoginInfoServiceImpl.class.getName();
	/** */
	private static final Logger LOGGER = Logger.getLogger(CLS_NM);

	@Override
	public LoginInfoBO save(final LoginInfoBO loginBo) {
		LOGGER.entering(CLS_NM, "save");
		Context ctx;
		LoginInfoBO retLoginBo = null;
		try {
			ctx = new InitialContext();
			loginRemote = (LoginInfoRemote) ctx.lookup(LOGIN_LKP);
			retLoginBo = loginRemote.save(loginBo);
		} catch (final NamingException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		LOGGER.exiting(CLS_NM, "save");
		return retLoginBo;
	}

	@Override
	public void delete(final String loginId) {
		LOGGER.entering(CLS_NM, "delete");
		Context ctx;
		try {
			ctx = new InitialContext();
			loginRemote = (LoginInfoRemote) ctx.lookup(LOGIN_LKP);
			loginRemote.delete(loginId);
		} catch (final NamingException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		LOGGER.exiting(CLS_NM, "delete");
	}

	@Override
	public LoginInfoBO getLoginInfo(final String loginId) {
		LOGGER.entering(CLS_NM, "getLoginInfo");
		Context ctx;
		LoginInfoBO retLoginBo = null;
		try {
			ctx = new InitialContext();
			loginRemote = (LoginInfoRemote) ctx.lookup(LOGIN_LKP);
			retLoginBo = loginRemote.getLoginInfo(loginId);
		} catch (final NamingException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		LOGGER.exiting(CLS_NM, "getLoginInfo");
		return retLoginBo;
	}

	@Override
	public boolean isAuthenticatedUser(final LoginInfoBO loginBo) {
		LOGGER.entering(CLS_NM, "isAuthenticatedUser");
		final Context ctx;
		boolean authUserFlag = false;
		try {
			ctx = new InitialContext();
			loginRemote = (LoginInfoRemote) ctx.lookup(LOGIN_LKP);
			authUserFlag = loginRemote.isAuthenticatedUser(loginBo);
		} catch (final NamingException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

		LOGGER.exiting(CLS_NM, "isAuthenticatedUser");
		return authUserFlag;
	}

	@Override
	public void updateSessionId(final String loginId, final String sessionId) {
		LOGGER.entering(CLS_NM, "updateSessionId");
		final Context ctx;
		try {
			ctx = new InitialContext();
			loginRemote = (LoginInfoRemote) ctx.lookup(LOGIN_LKP);
			loginRemote.updateSessionId(loginId, sessionId);
		} catch (final NamingException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		LOGGER.exiting(CLS_NM, "updateSessionId");
	}

}
