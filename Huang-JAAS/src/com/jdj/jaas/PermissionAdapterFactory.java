/**
 * PermissionAdapterFactory.java
 *
 * @author ghuang
 *
 * $Header$
 * $Log$
 */

package com.jdj.jaas;


public class PermissionAdapterFactory
{
	protected PermissionAdapter _adapter;
	protected boolean _bOK = false;
	protected boolean _bFirst = true;
	
	private static PermissionAdapterFactory _instance;
	
	public static PermissionAdapterFactory getInstance ()
	{
		if (_instance == null)
			_instance = new PermissionAdapterFactory ();
		return _instance;
	}
	
	public static PermissionAdapter getAdapter ()
	{
		PermissionAdapterFactory me = getInstance();
		if (me.prepare())
			return me.getHandlerInternal();
		else
			return null;
	}

	
	public PermissionAdapterFactory()
	{
	}
	
	protected PermissionAdapter getHandlerInternal ()
	{
		return _adapter;
	}
	
	public boolean prepare ()
	{
		if (Debug.DEBUG)
			Debug.trace("PermissionAdapterFactory::prepare");
		if (!_bOK && _bFirst)
		{
			_bFirst = false;
			_adapter = instantiateAdapter ();
			_bOK = true;
		}
		
		return _bOK;
	}
	
	public PermissionAdapter instantiateAdapter ()
	{
		if (Debug.DEBUG)
			Debug.trace("PermissionAdapterFactory::instantiateAdapter");
		AuthConfig cfg = AuthConfig.getInstance();
		String cName = cfg.getAuthznHandlerClassName();
		if (Debug.DEBUG)
			Debug.trace("adapter class:" + cName);
		PermissionAdapter adapter = null;
		if (cName != null)
		{
			try
			{
				Class c = Class.forName(cName);
				adapter = (PermissionAdapter)c.newInstance();
				adapter.initialize (cfg.getAuthznInitParam());
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

		return adapter;
	}
}
