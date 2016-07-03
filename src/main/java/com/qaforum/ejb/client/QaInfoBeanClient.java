/**
 * 
 */
package com.qaforum.ejb.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.qaforum.bo.QaInfoBO;
import com.qaforum.bo.QaInfoSearchBO;
import com.qaforum.ejb.QaInfoRemote;
import com.qaforum.ejb.impl.QaInfoBean;

/**
 * @author cdacr
 * 
 */
public final class QaInfoBeanClient {

	/** */
	public static final String JNDI_NAME = "ejb:/QAForum//"
			+ QaInfoBean.class.getSimpleName() + "!"
			+ QaInfoRemote.class.getName();
	/** */
	public static final String JMS_PROPERTIES = "jms.properties";

	/**
	 * 
	 * @return Context 
	 * @throws IOException 
	 * @throws NamingException 
	 */
	public Context getContext() throws IOException, NamingException {
		final Properties prop = loadProp(JMS_PROPERTIES);
		return new InitialContext(prop);
	}

	/**
	 * 
	 * @param fileName 
	 * @return Properties 
	 * @throws IOException 
	 */
	public Properties loadProp(final String fileName) throws IOException {
		final Properties prop = new Properties();
		final InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(fileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + fileName
					+ "' not found in the classpath");
		}
		return prop;
	}

	/**
	 * 
	 * @param args 
	 * @throws IOException 
	 * @throws NamingException 
	 */
	public static void main(final String[] args) throws IOException,
			NamingException {
		final QaInfoBeanClient client = new QaInfoBeanClient();
		final Context ctx = client.getContext();
		final QaInfoRemote qaInfoRemote = (QaInfoRemote) ctx.lookup(JNDI_NAME);
		client.processInput(qaInfoRemote);
	}

	/**
	 * 
	 */
	public void showGUI() {
		System.out.println("Enter you choice");
		System.out.println("1. Add Question");
		System.out.println("2. Remove Question");
		System.out.println("3. Update Question");
		System.out.println("4. Get Question");
		System.out.println("5. Get All Question");
		System.out.println("6: Save Batch");
		System.out.println("7. Exit");
	}

	/**
	 * 
	 * @param qaInfoRemote 
	 * @throws IOException 
	 */
	public void processInput(final QaInfoRemote qaInfoRemote)
			throws IOException {
		final InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		loopLabel: while (true) {
			showGUI();
			final Integer input = Integer.parseInt(br.readLine());
			switch (input.intValue()) {
			case 1:
				System.out.println("Added question"
						+ qaInfoRemote.save(addQuestionInput(br)));
				break;
			case 2:
				qaInfoRemote.delete(deleteQuestionInput(br));
				break;
			case 3:
				qaInfoRemote.save(updateBookInput(br));
				break;
			case 4:
				System.out
						.println("Enter search option: questionId, question or answer");
				final QaInfoBO info = new QaInfoBO();
				final QaInfoSearchBO searchBO = new QaInfoSearchBO();
				searchBO.setSearchOption(br.readLine());
				System.out.println("Enter search value");
				searchBO.setSearchValue(br.readLine());
				System.out
						.println("Enter operator: for instance, equals/startsWith/endsWith/greaterThan/lessThan/contains");
				searchBO.setOperator(br.readLine());
				final List<QaInfoBO> qaInfos2 = qaInfoRemote
						.findSelected(searchBO);
				System.out.println(qaInfos2);
				break;
			case 5:
				final List<QaInfoBO> qaInfos = qaInfoRemote.findAll();
				for (final QaInfoBO info1 : qaInfos) {
					System.out.println(info1);
				}
				break;
			case 6:
				List<QaInfoBO> qaInfosBatch = new ArrayList<QaInfoBO>();
				for (int i = 0; i < 5; i++) {
					final QaInfoBO info1 = new QaInfoBO();
					info1.setQuestion("Q" + i);
					info1.setAnswer("A" + i);
					qaInfosBatch.add(info1);
				}
				qaInfosBatch = qaInfoRemote.saveBatch(qaInfosBatch);
				break;
			case 7:
				System.out.println("Exiting");
				br.close();
				br = null;
				break loopLabel;
			default:
				System.out.println("Invalid choice");
				break;
			}
		}
	}

	/**
	 * 
	 * @param reader 
	 * @return QaInfoBO 
	 * @throws IOException 
	 */
	private QaInfoBO addQuestionInput(final BufferedReader reader)
			throws IOException {
		System.out.println("Enter question");
		final String question = reader.readLine();
		System.out.println("Enter answer");
		final String answer = reader.readLine();

		final QaInfoBO info = new QaInfoBO();
		info.setQuestion(question);
		info.setAnswer(answer);
		info.setType("SQL");
		return info;
	}

	/**
	 * 
	 * @param reader 
	 * @return Long
	 * @throws NumberFormatException 
	 * @throws IOException 
	 */
	private Long deleteQuestionInput(final BufferedReader reader)
			throws NumberFormatException, IOException {
		System.out.println("Enter question Id");
		final Long qaId = Long.parseLong(reader.readLine());
		return qaId;
	}

	/**
	 * 
	 * @param reader 
	 * @return QaInfoBO
	 * @throws NumberFormatException 
	 * @throws IOException 
	 */
	private QaInfoBO updateBookInput(final BufferedReader reader)
			throws NumberFormatException, IOException {
		System.out.println("Enter question Id");
		final QaInfoBO info = new QaInfoBO();
		info.setQaId(Long.parseLong(reader.readLine()));

		System.out.println("Enter answer");
		info.setAnswer(reader.readLine());
		return info;
	}
}
