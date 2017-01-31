/**
 * LoginDBSourceAdapter.java
 *
 * @author ghuang
 *
 * $Header$
 * $Log$
 */

package com.jdj.jaas;


import java.util.ArrayList;
import java.util.Hashtable;


import java.sql.*;


public class LoginDBSourceAdapter implements LoginSourceAdapter
{
	private Hashtable _htConnProp;
	private Connection _connection;
	private ArrayList _groups = new ArrayList();

	public LoginDBSourceAdapter ()
	{
	}

	public void initialize (Hashtable htProperty)
	{
		_htConnProp = htProperty;
	}

	public boolean authenticate (String userID, char[] password)
	{
		Connection conn = getConnection ();
		if (conn == null)
		{
			System.out.println("No connection");
			return false;
		}

		boolean bOK = false;
		_groups.clear();
		String sql = "SELECT name, password, role FROM user WHERE name=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String strPw = new String (password);
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userID);
			rs = stmt.executeQuery();
			if (rs != null)
			{
				while (rs.next ())
				{
					String pw = rs.getString ("password");
					if (pw.equals (strPw))
					{
						_groups.add(rs.getString("role"));
						bOK = true;
						break;
					}
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
			}

			return bOK;
		}


	}

	public String[] getGroupNames (String userID)
	{
		String [] groups = null;
		if (_groups.size() > 0)
		{
			int count = _groups.size();
			groups = new String[count];
			for (int i = 0; i < count; i++)
			{
				groups[i] = (String)_groups.get(i);
			}
		}
		return groups;
	}

	public void terminate ()
	{
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
}
