/*
 *UserLoginHandlerFactory.java
 *
 *
 *
 *
 * @author ghuang
 *
 * Created on 2-May-2003
 *
 * $Header$
 * $Log$
 */
package com.jdj.jaas;


public class LoginSourceAdapterFactory
{
	public static LoginSourceAdapter getLoginAdapter ()
	{
		AuthConfig cfg = AuthConfig.getInstance();
		String cName = cfg.getLoginHandlerClassName();
		if (Debug.DEBUG)
			Debug.trace("LoginSourceAdapterFactory::getLoginAdapter - class:" + cName);
		LoginSourceAdapter handler = null;
		if (cName != null)
		{
			try
			{
				Class c = Class.forName(cName);
				handler = (LoginSourceAdapter)c.newInstance();
				handler.initialize (cfg.getLoginInitParam());
			}
			catch (ClassNotFoundException cfe)
			{
				cfe.printStackTrace();
			}
			catch (IllegalAccessException iae)
			{
				iae.printStackTrace();
			}
			catch (InstantiationException ie)
			{
				ie.printStackTrace();
			}
		}

		return handler;
	}

}
