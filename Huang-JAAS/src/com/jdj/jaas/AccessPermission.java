/**
 * AccessPermission.java
 *
 *
 *
 *
 *
 * @author ghuang
 *
 * Create:5-Mar-2003
 *
 * $Header$
 * $Log$
 */



package com.jdj.jaas;

import java.security.Permission;
import java.util.*;
import java.io.Serializable;
import java.io.File;

public class AccessPermission extends Permission implements Serializable
{
	protected static final String ACTION_DELIM = ",";
	private static final String WILD = "*";
	private static final String SEP_WILD = File.separator + WILD;

	private String _action;
	private ArrayList _listActions = new ArrayList();
	private String _path;
	private boolean _bTail = false;

	/**
	 * @param name
	 */
	public AccessPermission(String name, String action)
	{
		super(name);

		getPath (name);
		_action = action;
		parseAction (action);
	}

	/* (non-Javadoc)
	 * @see java.security.Permission#implies(java.security.Permission)
	 */
	public boolean implies(Permission permission)
	{
		//System.out.println("###AccessPermission::implies");
		if (!(permission instanceof AccessPermission))
			return false;

		AccessPermission that = (AccessPermission)permission;

		return (containWith (that._path) && containAction(that._listActions));

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (! (obj instanceof AccessPermission))
				return false;

		AccessPermission that = (AccessPermission) obj;

		return (this.getName() == that.getName() &&
				this.compareAction(that._listActions));

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return _action.hashCode();

	}

	/* (non-Javadoc)
	 * @see java.security.Permission#getActions()
	 */
	public String getActions()
	{
		StringBuffer buf = new StringBuffer();
		int count = _listActions.size();
		for (int i = 0; i < count; i++)
		{
			if (i > 0)
				buf.append(ACTION_DELIM);
			buf.append((String)_listActions.get(i));
		}

		return buf.toString();
	}


	/////////////////////////////////////
	//
	protected void getPath (String name)
	{
		if (name.endsWith(SEP_WILD) || name.equals(WILD))
		{
			_path = name.substring(0, name.length()-1);
			_bTail = true;
		}
		else
		{
			_path = name;
		}

	}
	protected void parseAction (String action)
	{
		if (action == null)
			return;

		if (action.indexOf(action) == -1)
		{
			action = action.trim();
			_listActions.add(action);
			return;
		}

		StringTokenizer token = new StringTokenizer (action, ACTION_DELIM);
		while (token.hasMoreTokens())
		{
			String str = token.nextToken();
			str = str.trim();
			_listActions.add(str);
		}
	}

	protected boolean compareAction (ArrayList listActions)
	{
		if (_listActions.size() != listActions.size())
			return false;

		int num = 0;
		int count = _listActions.size();
		for (int i = 0; i < count; i++)
		{
			String action = (String)_listActions.get(i);
			for (int j = 0; j < count; j++)
			{
				if (action.equals(listActions.get(j)))
					num++;
			}
		}

		return (num == count);
	}


	protected boolean containAction (ArrayList listActions)
	{
		//if (_listActions.size() != listActions.size())
		//	return false;

		int count = _listActions.size();
		for (int i = 0; i < count; i++)
		{
			String action = (String)_listActions.get(i);
			for (int j = 0; j < listActions.size(); j++)
			{	
				if (action.equals(listActions.get(j)))
				{
					return true;
				}	
			}
		}

		return false;
	}

	protected boolean containWith (String path)
	{
		if (_bTail)
			return (path.length() >= _path.length()) &&
						path.startsWith(_path);
		else
			return _path.equals(path);
	}
}
