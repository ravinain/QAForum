/**
 * 
 */
package com.qaforum.ejb.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.qaforum.bo.LoginInfoBO;
import com.qaforum.ejb.LoginInfoRemote;
import com.qaforum.ejb.impl.LoginInfoBean;

/**
 * @author cdacr
 *
 */
public final class LoginInfoBeanClient {

	public static final String JNDI_NAME = "ejb:/QAForum//"
			+ LoginInfoBean.class.getSimpleName() + "!"
			+ LoginInfoRemote.class.getName();

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
		final LoginInfoBeanClient client = new LoginInfoBeanClient();
		final Context ctx = client.getContext();
		final LoginInfoRemote loginInfoRemote = (LoginInfoRemote) ctx
				.lookup(JNDI_NAME);
		client.processInput(loginInfoRemote);
	}

	/**
	 * 
	 */
	public void showGUI() {
		System.out.println("Enter you choice");
		System.out.println("1. Add User");
		System.out.println("2. Remove User");
		System.out.println("3. Update User");
		System.out.println("4. Get User");
		System.out.println("5. Validate User");
		System.out.println("6. Exit");
	}

	/**
	 * 
	 * @param loginInfoRemote 
	 * @throws IOException 
	 */
	public void processInput(final LoginInfoRemote loginInfoRemote)
			throws IOException {
		final InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		loopLabel: while (true) {
			showGUI();
			final Integer input = Integer.parseInt(br.readLine());
			switch (input.intValue()) {
			case 1:
				System.out.println("Added user"
						+ loginInfoRemote.save(addUserInput(br)));
				break;
			case 2:
				loginInfoRemote.delete(deleteUserInput(br));
				break;
			case 3:
				loginInfoRemote.save(updateLoginInput(br));
				break;
			case 4:
				System.out.println("Enter login id: ");
				final String loginId = br.readLine();
				System.out.println(loginInfoRemote.getLoginInfo(loginId));
				break;
			case 5:
				System.out.println("Enter login id:");
				final String loginId1 = br.readLine();
				System.out.println("Enter password");
				final String password = br.readLine();
				final LoginInfoBO authLoginBo = new LoginInfoBO();
				authLoginBo.setLoginId(loginId1);
				authLoginBo.setPassword(password);
				System.out.println(loginInfoRemote
						.isAuthenticatedUser(authLoginBo));
			case 6:
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
	 * @return LoginInfoBO 
	 * @throws IOException 
	 */
	private LoginInfoBO addUserInput(final BufferedReader reader)
			throws IOException {
		System.out.println("Enter loginid");
		final String loginId = reader.readLine();
		System.out.println("Enter password");
		final String password = reader.readLine();

		final LoginInfoBO info = new LoginInfoBO();
		info.setLoginId(loginId);
		info.setPassword(password);
		return info;
	}

	/**
	 * 
	 * @param reader 
	 * @return String 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 */
	private String deleteUserInput(final BufferedReader reader)
			throws NumberFormatException, IOException {
		System.out.println("Enter user Id");
		final String loginId = reader.readLine();
		return loginId;
	}

	/**
	 * 
	 * @param reader 
	 * @return QaInfoBO
	 * @throws NumberFormatException 
	 * @throws IOException 
	 */
	private LoginInfoBO updateLoginInput(final BufferedReader reader)
			throws NumberFormatException, IOException {
		System.out.println("Enter login Id");
		final LoginInfoBO info = new LoginInfoBO();
		info.setLoginId(reader.readLine());

		System.out.println("Enter password");
		info.setPassword(reader.readLine());
		return info;
	}
}
