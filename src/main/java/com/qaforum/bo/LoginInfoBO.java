/**
 * 
 */
package com.qaforum.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cdacr
 *
 */
@Entity
@Table(name = "Login_Info", schema = "QADB")
public class LoginInfoBO implements Serializable {

	/**	 */
	private static final long serialVersionUID = -6436929676282906541L;

	/**	 */
	@Id
	@Column(name = "login_id", unique = true, nullable = false)
	private String loginId;
	/**	 */
	private String password;
	/**	 */
	@Column(name = "is_login")
	private Boolean isLogin;
	/**	 */
	@Column(name = "last_login_dt")
	private Date lastLoginDt;
	/**	 */
	@Column(name = "last_login_ip_address")
	private String lastLoginIpAddress;
	/**	 */
	@Column(name = "updated_by")
	private String updatedBy;
	/**	 */
	@Column(name = "inserted_by")
	private String insertedBy;
	/**	 */
	@Column(name = "updated_dt")
	private Date updatedDt;
	/**	 */
	@Column(name = "inserted_dt")
	private Date insertedDt;

	/** */
	@Column(name = "session_id")
	private String sessionId;

	/**
	 * @return the loginId
	 */
	public final String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId the loginId to set
	 */
	public final void setLoginId(final String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public final void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @return the isLogin
	 */
	public final Boolean getIsLogin() {
		return isLogin;
	}

	/**
	 * @param isLogin the isLogin to set
	 */
	public final void setIsLogin(final Boolean isLogin) {
		this.isLogin = isLogin;
	}

	/**
	 * @return the lastLoginDt
	 */
	public final Date getLastLoginDt() {
		return lastLoginDt;
	}

	/**
	 * @param lastLoginDt the lastLoginDt to set
	 */
	public final void setLastLoginDt(final Date lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}

	/**
	 * @return the lastLoginIpAddress
	 */
	public final String getLastLoginIpAddress() {
		return lastLoginIpAddress;
	}

	/**
	 * @param lastLoginIpAddress the lastLoginIpAddress to set
	 */
	public final void setLastLoginIpAddress(final String lastLoginIpAddress) {
		this.lastLoginIpAddress = lastLoginIpAddress;
	}

	/**
	 * @return the updatedBy
	 */
	public final String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public final void setUpdatedBy(final String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the insertedBy
	 */
	public final String getInsertedBy() {
		return insertedBy;
	}

	/**
	 * @param insertedBy the insertedBy to set
	 */
	public final void setInsertedBy(final String insertedBy) {
		this.insertedBy = insertedBy;
	}

	/**
	 * @return the updatedDt
	 */
	public final Date getUpdatedDt() {
		return updatedDt;
	}

	/**
	 * @param updatedDt the updatedDt to set
	 */
	public final void setUpdatedDt(final Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	/**
	 * @return the insertedDt
	 */
	public final Date getInsertedDt() {
		return insertedDt;
	}

	/**
	 * @param insertedDt the insertedDt to set
	 */
	public final void setInsertedDt(final Date insertedDt) {
		this.insertedDt = insertedDt;
	}

	/**
	 * @return the sessionId
	 */
	public final String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public final void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public final String toString() {
		// TODO Auto-generated method stub
		return "User : " + loginId + ", Password : " + password;
	}
}
