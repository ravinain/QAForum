package com.qaforum.service;

import java.util.List;

import com.qaforum.bo.QaInfoBO;
import com.qaforum.bo.QaInfoSearchBO;
import com.qaforum.exception.InvalidDataException;

/**
 * 
 * @author cdacr
 *
 */
public interface QaInfoService {

	/**
	 * 
	 * @param qaInfo 
	 * @return QaInfoBO 
	 * @throws InvalidDataException 
	 */
	QaInfoBO save(QaInfoBO qaInfo) throws InvalidDataException;

	/**
	 * 
	 * @param qaId 
	 */
	void delete(Long qaId);

	/**
	 * 
	 * @return List of QaInfoBO
	 */
	List<QaInfoBO> findAll();

	/**
	 * 
	 * @param searchBO 
	 * @return List of QaInfoBO
	 */
	List<QaInfoBO> findSelected(QaInfoSearchBO searchBO);

	/**
	 * 
	 * @param qaInfos 
	 * @return List of QaInfoBO
	 */
	List<QaInfoBO> saveBatch(List<QaInfoBO> qaInfos);

}
