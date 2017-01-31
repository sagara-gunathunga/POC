/**
 * Test.java
 *
 * Purpose: Test Program for testing the JAAS sample code
 *
 * Auther :  ghuang
 * Created:  7-Mar-2003
 *
 * Note:
 *
 *
 * $Header$
 * $Log$
 */
package test;


//import java.security.AccessControlException;

import javax.security.auth.*;

import com.jdj.jaas.*;


public class Test
{
	private String CONFIG_FILE="userauth_config.xml";
	private String _configFile = CONFIG_FILE;
	
	public Test ()
	{
	}
	
	public Test (String configFile)
	{
		if (configFile != null)
			_configFile = configFile;
	}

	public void doTest (String user, String password)
	{
		log ("");
		System.out.println("**** JAAS Sample:  start test ***********");

		//UserAuth.configUserAuth("D:\\MyTest.gen\\Jaas\\userauth_config.xml");
		//UserAuth.configUserAuth(_configFile);


		//UserAuth ua = new UserAuth ("testUser" , "testPassword");
		//"Austin" , "123456"
		JaasUtil ua = new JaasUtil (user, password);
		//ua.setLoginContextName(JaasUtil.LOGINCONTEXT_NAME);
		if (!ua.authenticate())
		{
			System.out.println("Invalid user ID or password!!!");
			return;
		}
		else
			System.out.println("User <" + user + "> validated");

		Subject subj = ua.getSubject();
		//System.out.println("subject contain principal:" + subj.getPrincipals().size());
		System.out.println("================ now check access   ==========");

		//authorization
		AccessPermission permEdit = new AccessPermission ("file", "write");
		AccessPermission permRead = new AccessPermission ("file", "read");
		//FilePermission perm = new FilePermission ("foo.txt", "read");
		//FilePermission perm = new FilePermission ("file", "write");
		boolean bPermitted = false;
		bPermitted = JaasUtil.checkPermission(subj, permRead);
		if (bPermitted)
			System.out.println(user + " allowed to " + permRead.toString());
		else
			log (user + " NOT allowed to " + permRead.toString());
			
		bPermitted = JaasUtil.checkPermission(subj, permEdit);
		if (bPermitted)
			System.out.println(user + " allowed to " + permEdit.toString());
		else
			log (user + " NOT allowed to " + permEdit.toString());
			
			
		log ("");
		log ("************ test done *****************");
		log ("");
		
	}

	public static void main(String[] args)
	{
		System.out.println("###############");
		if (args.length == 0)
		{
			log ("Usage:");
			log ("test.Test <userName> <password> [config file(optional)]");
			return;
		}
		
		//System.out.println("getProperteis for java.security.auth.login.config:" + System.getProperty("java.security.auth.login.config"));
		
		String user = args[0];
		String password = args[1];
		String configFile = null;
		if (args.length == 3)
			configFile = args[2];
		
		
		try
		{
			Test tester = new Test (configFile);
			tester.doTest(user, password);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void log (String text)
	{
		System.out.println(text);
	}
}
