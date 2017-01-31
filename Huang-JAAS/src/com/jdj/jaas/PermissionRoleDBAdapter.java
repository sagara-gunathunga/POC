/**
 * AuthznDBHanlder.java
 *
 *
 *
 *
 * @author ghuang
 *
 * Create:4-Mar-2003
 *
 * $Header$
 * $Log$
 */



package com.jdj.jaas;

import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Principal;
import java.security.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;


import java.sql.*;


public class PermissionRoleDBAdapter implements PermissionAdapter
{
	private static final String SQL_GETALLPERM = "SELECT name, task, action, parent FROM role";

	private Hashtable _htConnProp;
	private Connection _connection;
	private Hashtable _htPermissions;

	/**
	 *
	 */
	public PermissionRoleDBAdapter()
	{

	}

	/* (non-Javadoc)
	 * @see sample.auth.AuthznHandler#initialize(java.util.Hashtable)
	 */
	public void initialize(Hashtable initParams)
	{
		if (Debug.DEBUG)
			Debug.trace("PermissionRoleDBAdapter::initialize");
		_htConnProp = initParams;
		_htPermissions = getAllPermissions ();

	}
	
	
	public PermissionCollection getPermissions (CodeSource codeSource)
	{
		if (Debug.DEBUG)
			Debug.trace("PermissionRoleDBAdapter::getPermissions (CodeSource codeSource)");
		
		//we just care about the role-based, so do nothing here
		return null;
	}
	
	public PermissionCollection getPermissions (ProtectionDomain domain)
	{
		if (Debug.DEBUG)
			Debug.trace("PermissionRoleDBAdapter::getPermissions(ProtectionDomain domain)");
		Principal[] principals = domain.getPrincipals();
		AuthPermissionCollection perms = new AuthPermissionCollection();//Permissions();
		for (int i = 0; i < principals.length; i++)
		{
			String name = principals[i].getName();
			ArrayList list = (ArrayList)_htPermissions.get (name);
			if (list != null)
			{
				Iterator itr = list.iterator();
				while (itr.hasNext())
				{
					Permission item = (Permission)itr.next();
					perms.add(item);
				}
			}
		}

		return perms;		
	}


	/* (non-Javadoc)
	 * @see sample.auth.AuthznHandler#getPermissions(java.security.Principal[], java.security.CodeSource)
	 */
	public PermissionCollection getPermissions(
		Principal[] principals, CodeSource codeSource)
	{
		if (Debug.DEBUG)
			Debug.trace("AuthznDBHandler::getPermissions(Principal[] principals, CodeSource codeSource)");

		AuthPermissionCollection perms = new AuthPermissionCollection();//Permissions();
		for (int i = 0; i < principals.length; i++)
		{
			String name = principals[i].getName();
			ArrayList list = (ArrayList)_htPermissions.get (name);
			if (list != null)
			{
				Iterator itr = list.iterator();
				while (itr.hasNext())
				{
					Permission item = (Permission)itr.next();
					perms.add(item);
				}
			}
		}

		return perms;
	}


	/* (non-Javadoc)
	 * @see sample.auth.AuthznHandler#terminate()
	 */
	public void terminate()
	{
		try
		{
			if (_connection != null && !_connection.isClosed())
				_connection.close ();
			_connection = null;
		}
		catch (SQLException sqe)
		{
		}

	}


	//////////////////////////////////////////////
	private Connection getConnection ()
	{
		if (_htConnProp == null)
			return null;

		String driver = (String)_htConnProp.get ("driver");
		String url = (String)_htConnProp.get ("url");
		String user = (String)_htConnProp.get ("user");
		String password = (String)_htConnProp.get("password");

		Connection connection = null;

		// load the driver, if it is not loaded
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		// Get the connection to the database
		try
		{
			connection =
				DriverManager.getConnection(url, user, password);

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return connection;
	}


	private boolean isTitleTo (String name, Principal[] principals)
	{
		if (principals == null || principals.length == 0)
			return true;

		int count = principals.length;
		for (int i = 0; i < count; i++)
		{
			if (name.equals (principals[i].getName()))
				return true;
		}

		return false;
	}



	protected Hashtable getAllPermissions()
	{
		if (Debug.DEBUG)
			Debug.trace("PermissionRoleDBAdapter::getAllPermissions");
		Hashtable perms = new Hashtable();

		Connection conn = getConnection ();
		if (conn == null)
		{
			System.out.println("No connection");
			return perms;
		}

		boolean bOK = false;
		String sql = SQL_GETALLPERM;
		//PreparedStatement
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			//stmt = conn.prepareStatement(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs != null)
			{
				while (rs.next())
				{
					String name = rs.getString ("name");
					String task = rs.getString ("task");
					String action = rs.getString ("action");
					//System.out.println("permission:" + task + " " + action);
					//FilePermission perm = new FilePermission (task, action);
					AccessPermission perm = new AccessPermission(task, action);
					ArrayList ar = (ArrayList)perms.get(name);
					if (ar == null)
					{
						ar = new ArrayList ();
						perms.put(name, ar);
					}			
					ar.add (perm);
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			}
			catch (SQLException sqe)
			{
				//ignore it
			}

			return perms;
		}
	}

}
