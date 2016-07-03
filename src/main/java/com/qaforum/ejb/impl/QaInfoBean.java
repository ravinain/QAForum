package com.qaforum.ejb.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import com.qaforum.bo.QaInfoBO;
import com.qaforum.bo.QaInfoSearchBO;
import com.qaforum.ejb.QaInfoRemote;
import com.qaforum.util.Operator;
import com.qaforum.util.SearchOption;

/**
 * @author cdacr
 * 
 */
@Stateless
public class QaInfoBean implements QaInfoRemote {

	/** */
	private final Logger logger = Logger.getLogger(QaInfoBean.class.getName());

	/** */
	@PersistenceContext(unitName = "QaForumPU")
	private EntityManager entityManager;

	/**
	 * Method add/update data in DB, if QaInfoBO has qaId 
	 * then update the record otherwise insert it.
	 * @param qaInfo 
	 * @return QaInfoBO
	 */
	@Override
	public final QaInfoBO save(final QaInfoBO qaInfo) {
		if (qaInfo.getQaId() == null) {
			entityManager.persist(qaInfo);
		} else {
			entityManager.merge(qaInfo);
		}
		logger.info("QaId :: " + qaInfo.getQaId());
		return qaInfo;
	}

	/**
	 * Method deletes the record based on input qaId.
	 * @param qaId 
	 */
	@Override
	public final void delete(final Long qaId) {
		final QaInfoBO qaBo = entityManager.find(QaInfoBO.class, qaId);
		logger.info("Delete BO : " + qaBo);
		entityManager.remove(qaBo);
	}

	/**
	 * Method fetches all QaInfoBO data from DB.
	 * @return List of QaInfoBO
	 */
	@Override
	public final List<QaInfoBO> findAll() {
		return entityManager.createNamedQuery("findAllQAs", QaInfoBO.class)
				.getResultList();
	}

	/**
	 * Method returns the QaInfoBO based on input search data.
	 * @param searchBO 
	 * @return List of QaInfoBO
	 */
	@Override
	public final List<QaInfoBO> findSelected(final QaInfoSearchBO searchBO) {
		List<QaInfoBO> qaInfos = null;
		if (searchBO.getSearchOption().equals(
				SearchOption.QUESTION_ID.toString())) {
			qaInfos = findByQuestionId(searchBO);
		} else if (searchBO.getSearchOption().equals(
				SearchOption.QUESTION.toString())) {
			qaInfos = findByQuestion(searchBO);
		} else if (searchBO.getSearchOption().equals(
				SearchOption.ANSWER.toString())) {
			qaInfos = findByAnswer(searchBO);
		} else if (searchBO.getSearchOption().equals(
				SearchOption.TYPE.toString())) {
			qaInfos = findByType(searchBO);
		}
		return qaInfos;
	}

	/**
	 * Method filter the data in question id column based on input filter value.
	 * @param searchBO 
	 * @return List of QaInfoBO
	 */
	private List<QaInfoBO> findByQuestionId(final QaInfoSearchBO searchBO) {
		String query = "from QaInfoBO q where ";
		if (searchBO.getOperator().equals(Operator.EQUALS.getString())) {
			query += "q.qaId = :qaId";
		} else if (searchBO.getOperator().equals(
				Operator.GREATER_THAN.getString())) {
			query += "q.qaId > :qaId";
		} else if (searchBO.getOperator()
				.equals(Operator.LESS_THAN.getString())) {
			query += "q.qaId < :qaId";
		}
		return entityManager
				.createQuery(query, QaInfoBO.class)
				.setParameter("qaId", Long.parseLong(searchBO.getSearchValue()))
				.getResultList();
	}

	/**
	 * Method filter the data in question column based on input filter value.
	 * @param searchBO 
	 * @return List of QaInfoBO
	 */
	private List<QaInfoBO> findByQuestion(final QaInfoSearchBO searchBO) {
		String query = "from QaInfoBO q where ";
		String value = searchBO.getSearchValue();
		final String operator = searchBO.getOperator();
		if (operator.equals(Operator.EQUALS.getString())) {
			query += "q.question = :question";
		} else if (operator.equals(Operator.STARTS_WITH.getString())) {
			query += "q.question like :question";
			value += "%";
		} else if (operator.equals(Operator.ENDS_WITH.getString())) {
			query += "q.question like :question";
			value = "%" + value;
		} else if (operator.equals(Operator.CONTAINS.getString())) {
			query += "q.question like :question";
			value = "%" + value + "%";
		}
		return entityManager.createQuery(query, QaInfoBO.class)
				.setParameter("question", value).getResultList();
	}

	/**
	 * Method filter the data in answer column based on input filter value.
	 * @param searchBO 
	 * @return List of QaInfoBO
	 */
	private List<QaInfoBO> findByAnswer(final QaInfoSearchBO searchBO) {
		String query = "from QaInfoBO q where ";
		String value = searchBO.getSearchValue();
		final String operator = searchBO.getOperator();
		if (operator.equals(Operator.EQUALS.getString())) {
			query += "q.answer like :answer";
		} else if (operator.equals(Operator.STARTS_WITH.getString())) {
			query += "q.answer like :answer";
			value += "%";
		} else if (operator.equals(Operator.ENDS_WITH.getString())) {
			query += "q.answer like :answer";
			value = "%" + value;
		} else if (operator.equals(Operator.CONTAINS.getString())) {
			query += "q.answer like :answer";
			value = "%" + value + "%";
		}
		return entityManager.createQuery(query, QaInfoBO.class)
				.setParameter("answer", value).getResultList();
	}

	/**
	 * Method filter the data in type column based on input filter value.
	 * @param searchBO 
	 * @return List of QaInfoBO
	 */
	private List<QaInfoBO> findByType(final QaInfoSearchBO searchBO) {
		String query = "from QaInfoBO q where ";
		String value = searchBO.getSearchValue();
		final String operator = searchBO.getOperator();
		if (operator.equals(Operator.EQUALS.getString())) {
			query += "q.type = :type";
		} else if (operator.equals(Operator.STARTS_WITH.getString())) {
			query += "q.type like :type";
			value += "%";
		} else if (operator.equals(Operator.ENDS_WITH.getString())) {
			query += "q.type like :type";
			value = "%" + value;
		} else if (operator.equals(Operator.CONTAINS.getString())) {
			query += "q.type like :type";
			value = "%" + value + "%";
		}
		return entityManager.createQuery(query, QaInfoBO.class)
				.setParameter("type", value).getResultList();
	}

	/**
	 * Method saves the data in DB in batches.
	 * @param qaInfos  
	 * @return List of QaInfoBO
	 */
	@Override
	public final List<QaInfoBO> saveBatch(final List<QaInfoBO> qaInfos) {
		entityManager.setFlushMode(FlushModeType.COMMIT);

		for (final QaInfoBO qaInfo : qaInfos) {
			entityManager.persist(qaInfo);
		}
		entityManager.flush();

		return qaInfos;
	}
}
