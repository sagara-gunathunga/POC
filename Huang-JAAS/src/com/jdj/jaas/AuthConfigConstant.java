/*
 * @author ghuang
 *
 * Created on 21-Feb-2003
 *
 */
package com.jdj.jaas;

public class AuthConfigConstant
{
	public static final AuthConfigConstant USERAUTH = new AuthConfigConstant("jaasAuth");
	public static final AuthConfigConstant AUTHENTICATION = new AuthConfigConstant("authentication");
	public static final AuthConfigConstant LOGINHANDLER = new AuthConfigConstant("loginAdapter");
	public static final AuthConfigConstant INITPARAM = new AuthConfigConstant("initParam");
	public static final AuthConfigConstant AUTHORIZATION = new AuthConfigConstant("authorization");
	public static final AuthConfigConstant AUTHZNHANDLER = new AuthConfigConstant("permissionAdapter");




	public static final AuthConfigConstant CLASS = new AuthConfigConstant("class");
	public static final AuthConfigConstant NAME = new AuthConfigConstant("name");
	public static final AuthConfigConstant VALUE = new AuthConfigConstant("value");



	protected String _id;

	protected AuthConfigConstant(String id)
	{
		_id = id;
	}

	public boolean is(String str)
	{
		if (_id.equalsIgnoreCase(str))
			return true;
		else
			return false;
	}

	public String toString()
	{
		return _id;
	}
}
