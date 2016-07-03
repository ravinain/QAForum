package com.qaforum.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.qaforum.bo.QaInfoBO;
import com.qaforum.bo.QaInfoSearchBO;

/**
 * 
 * @author cdacr
 *
 */
@Remote
public interface QaInfoRemote {

	/**
	 * Method add/update data in DB, if QaInfo has qaId 
	 * then update the record otherwise insert it.
	 * @param qaInfo 
	 * @return QaInfo
	 */
	QaInfoBO save(QaInfoBO qaInfo);

	/**
	 * Method deletes the record based on input qaId.
	 * @param qaId 
	 */
	void delete(Long qaId);

	/**
	 * Method fetches all QaInfoBO data from DB.
	 * @return List of QaInfoBO
	 */
	List<QaInfoBO> findAll();

	/**
	 * Method returns the QaInfoBO based on input search data.
	 * @param searchBO 
	 * @return List of QaInfoBO
	 */
	List<QaInfoBO> findSelected(QaInfoSearchBO searchBO);

	/**
	 * Method saves the data in DB in batches.
	 * @param qaInfos 
	 * @return List of QaInfoBO
	 */
	List<QaInfoBO> saveBatch(List<QaInfoBO> qaInfos);

}
