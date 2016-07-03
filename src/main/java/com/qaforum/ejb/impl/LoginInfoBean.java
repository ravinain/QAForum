/**
 * 
 */
package com.qaforum.ejb.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import com.qaforum.bo.LoginInfoBO;
import com.qaforum.ejb.LoginInfoRemote;
import com.qaforum.util.StringUtils;

/**
 * @author cdacr
 *
 */
@Stateless
public class LoginInfoBean implements LoginInfoRemote {

	/** */
	private final Logger logger = Logger.getLogger(LoginInfoBean.class
			.getName());

	/** */
	@PersistenceContext(unitName = "QaForumPU")
	private EntityManager entityManager;

	/**
	 * @param loginBo 
	 * @return {@link LoginInfoBO}
	 */
	@Override
	public final LoginInfoBO save(final LoginInfoBO loginBo) {
		final LoginInfoBO loginInfoBo = getLoginInfo(loginBo.getLoginId());
		final String loginId = StringUtils.convertNullToBlank(loginInfoBo
				.getLoginId());
		if (loginId.equals("")) {
			entityManager.persist(loginBo);
		} else {
			entityManager.merge(loginBo);
		}
		logger.log(Level.INFO, loginBo);
		return loginBo;
	}

	/**
	 * @param loginId 
	 */
	@Override
	public final void delete(final String loginId) {
		entityManager.remove(getLoginInfo(loginId));
	}

	/**
	 * @param loginId 
	 * @return {@link LoginInfoBO}
	 */
	@Override
	public final LoginInfoBO getLoginInfo(final String loginId) {
		final String query = "from LoginInfoBO l where l.loginId = ?";
		LoginInfoBO loginBo = new LoginInfoBO();
		try {
			loginBo = entityManager.createQuery(query, LoginInfoBO.class)
					.setParameter(1, loginId).getSingleResult();
			logger.log(Level.INFO, loginBo);
		} catch (final NoResultException ex) {
		}
		return loginBo;
	}

	/**
	 * @param loginBo 
	 * @return TRUE|FALSE
	 * 
	 */
	@Override
	public final boolean isAuthenticatedUser(final LoginInfoBO loginBo) {
		final String query = "from LoginInfoBO l "
				+ "where l.loginId = ? and l.password = ?";
		final int count = entityManager
				.createQuery(query, LoginInfoBO.class)
				.setParameter(1,
						StringUtils.convertNullToBlank(loginBo.getLoginId()))
				.setParameter(2,
						StringUtils.convertNullToBlank(loginBo.getPassword()))
				.getResultList().size();
		logger.info("Count : " + count);
		logger.info("Count > 0 : " + (count > 0));
		return count > 0;
	}

	@Override
	public final void updateSessionId(final String loginId,
			final String sessionId) {
		final LoginInfoBO loginBo = getLoginInfo(loginId);
		loginBo.setSessionId(sessionId);
		save(loginBo);
	}

}
