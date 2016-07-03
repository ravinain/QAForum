package com.qaforum.service.impl;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.qaforum.bo.QaInfoBO;
import com.qaforum.bo.QaInfoSearchBO;
import com.qaforum.ejb.QaInfoRemote;
import com.qaforum.exception.InvalidDataException;
import com.qaforum.service.QaInfoService;
import com.qaforum.validation.QaInfoValidate;

/**
 * 
 * @author cdacr
 *
 */
public class QaInfoServiceImpl implements QaInfoService {

	/** */
	private QaInfoRemote qaInfoBean;
	/** */
	public static final String QA_INFO_LKP = "java:global/RestTest/QaInfoBean";
	/** */
	private final QaInfoValidate validate = new QaInfoValidate();

	/**
	 * 
	 * @param qaInfo 
	 * @throws InvalidDataException 
	 */
	@Override
	public final QaInfoBO save(final QaInfoBO qaInfo)
			throws InvalidDataException {
		Context ctx;
		QaInfoBO retQaInfo = new QaInfoBO();
		try {
			ctx = new InitialContext();
			qaInfoBean = (QaInfoRemote) ctx.lookup(QA_INFO_LKP);
			final List<String> errorList = validate.validateSave(qaInfo);
			if (errorList.size() > 0) {
				throw new InvalidDataException(errorList);
			} else {
				retQaInfo = qaInfoBean.save(qaInfo);
			}
		} catch (final NamingException e) {
			e.printStackTrace();
		}
		return retQaInfo;
	}

	/**
	 * 
	 * @param qaId 
	 * 
	 */
	@Override
	public final void delete(final Long qaId) {
		Context ctx;
		try {
			ctx = new InitialContext();
			qaInfoBean = (QaInfoRemote) ctx.lookup(QA_INFO_LKP);
			qaInfoBean.delete(qaId);
		} catch (final NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return List of QaInfoBO
	 */
	@Override
	public final List<QaInfoBO> findAll() {
		List<QaInfoBO> qaInfos = null;
		Context ctx;
		try {
			ctx = new InitialContext();
			qaInfoBean = (QaInfoRemote) ctx.lookup(QA_INFO_LKP);
			qaInfos = qaInfoBean.findAll();
		} catch (final NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qaInfos;
	}

	/**
	 * @param searchBO 
	 * @return List of QaInfoBO
	 */
	@Override
	public final List<QaInfoBO> findSelected(final QaInfoSearchBO searchBO) {
		Context ctx;
		List<QaInfoBO> qaInfos = null;
		try {
			ctx = new InitialContext();
			qaInfoBean = (QaInfoRemote) ctx.lookup(QA_INFO_LKP);
			qaInfos = qaInfoBean.findSelected(searchBO);
		} catch (final NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qaInfos;
	}

	/**
	 * @param qaInfos 
	 * @return List of QaInfoBO
	 */
	@Override
	public final List<QaInfoBO> saveBatch(final List<QaInfoBO> qaInfos) {
		Context ctx;
		List<QaInfoBO> qaInfosRet = null;
		try {
			ctx = new InitialContext();
			qaInfoBean = (QaInfoRemote) ctx.lookup(QA_INFO_LKP);
			qaInfosRet = qaInfoBean.saveBatch(qaInfos);
		} catch (final NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qaInfosRet;
	}
}
