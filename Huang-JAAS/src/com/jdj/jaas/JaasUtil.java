package com.jdj.jaas;

/**
 * @author ghuang
 *
 * Usage:
 * 1) login config file - file name as the name pass in this class,
 *    JaasUtil {
 *  com.jdj.jaas.AuthLoginModule required debug=true;
 * };
 *
 *
 */

//Usage:
//1) login config file - file name as the name pass in this class,
// UserAuth
// {
//      auth.serLoginModule  required debug=true groupFile="groups.xml";
// };
//
// when run, $java -Djava.security.auth.login.config=which_location/userauth.login
// OR in JAVA_HOME/jre/lib/security directory, modify the file java.security, adding:
//  login.config.url.1=file:${java.home}/lib/security/userauth.login
//

import javax.security.auth.*;
import javax.security.auth.login.*;
import java.security.*;
import java.io.*;


public class JaasUtil
{
	public static final String LOGINCONTEXT_NAME = "JaasUtil";
	private static final String USERAUTH_KEY_CONFIGFILE = "com.auth.config";

	private String _name;
	private String _userId;
	private String _password;
	private LoginContext _loginContext;
	private static AuthConfig _config;

	/**
	 * Constructor for UserAuth.
	 */
	public JaasUtil(String userID, String password)
	{
		this(userID, password, LOGINCONTEXT_NAME);
	}

	public JaasUtil(String userID, String password, String contextName)
	{
		_userId = userID;
		_password = password;
		_name = contextName;
	}

	//the name used as the index into the Configuration. for LoginContext
	public void setLoginContextName (String name)
	{
		_name = name;
	}

	public boolean authenticate ()
	{
		if (_userId == null)
			return false;

		if (_name == null)
			_name = LOGINCONTEXT_NAME;

		if (Debug.DEBUG)
		{
			Debug.trace("Context name:" + _name);
			Debug.trace("userId:" + _userId + ", password:" + _password);
		}

		AuthConfig config = AuthConfig.getInstance();
		if (!config.isLoaded())
		{
			String file = System.getProperty(USERAUTH_KEY_CONFIGFILE);
			if (file != null)
			{
				if (file.startsWith("="))
					file = file.substring(1);
				_config = config;
				setConfigFile (file);
			}
		}

		try
		{
			_loginContext = new LoginContext (_name,
						new AuthCallbackHandler (_userId, _password));
			_loginContext.login();
		}
		catch (LoginException le)
		{
			le.printStackTrace();
			return false;
		}

		return true;
	}

	public Subject getSubject ()
	{
		if (_loginContext != null)
			return _loginContext.getSubject();
		else
			return null;
	}

	public void logout ()
	{
		if (_loginContext != null)
		{
			try
			{
				_loginContext.logout();
			}
			catch (LoginException le)
			{
			}
		}
	}

	public static boolean checkPermission (Subject subject, final Permission perm)
	{
		if (Debug.DEBUG)
			Debug.trace("UserAuth::check permission...");

		if (subject == null || perm == null)
			return false;

		final SecurityManager sm;
		if (null == System.getSecurityManager())
			sm = new SecurityManager ();
		else
			sm = System.getSecurityManager();


		try
		{
			Subject.doAsPrivileged(subject, new PrivilegedExceptionAction()
			{
				public Object run()
				{
					sm.checkPermission(perm);
					return null;
				}
			}, null);

			return true;
		}
		catch (AccessControlException ace)
		{
			if (Debug.DEBUG)
				Debug.log(ace);
			return false;
		}
		catch (PrivilegedActionException pae)
		{
			if (Debug.DEBUG)
				Debug.log(pae);
			return false;
		}
	}


	public static void setConfigFile (String strFile)
	{
		if (_config == null)
			_config = AuthConfig.getInstance();

		try
		{
			_config.load(strFile);
			if (Debug.DEBUG)
				Debug.trace("configUserAuth: Load done");
		}
		catch (IOException ie)
		{
			ie.printStackTrace();
		}
	}

	public static void setConfigFile (InputStream in)
	{
		if (_config == null)
			_config = AuthConfig.getInstance();

		try
		{
			_config.load(in);
		}
		catch (IOException ie)
		{
			ie.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		try
		{
			JaasUtil ua = new JaasUtil ("test" , "test");
			ua.setLoginContextName("JaasUtil");
			if (!ua.authenticate())
				System.out.println("Invalid user");
			else
				System.out.println("User validated");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
