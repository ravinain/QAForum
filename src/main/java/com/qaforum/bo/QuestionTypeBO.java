/**
 * 
 */
package com.qaforum.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author cdacr
 * 
 */
@Entity
@Table(name = "QuestionType", schema = "QADB")
public class QuestionTypeBO implements Serializable {

	private static final long serialVersionUID = -9155658648934307327L;

	/** */
	@Id
	@Column(name = "TypeId", unique = true, nullable = false)
	@TableGenerator(name = "TABLE_GEN", schema = "QADB", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "QUESTION_TYPE_COUNT")
	@GeneratedValue(generator = "TABLE_GEN", strategy = GenerationType.TABLE)
	private Long typeId;

	/** */
	@Column(name = "TypeDescription")
	private String typeDescription;

	/**
	 * 
	 * @return typeId
	 */
	public final Long getTypeId() {
		return typeId;
	}

	/**
	 * 
	 * @param typeId 
	 */
	public final void setTypeId(final Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * 
	 * @return typeDescription 
	 */
	public final String getTypeDescription() {
		return typeDescription;
	}

	/**
	 * 
	 * @param typeDescription 
	 */
	public final void setTypeDescription(final String typeDescription) {
		this.typeDescription = typeDescription;
	}

}
