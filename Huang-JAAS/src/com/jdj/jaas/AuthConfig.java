/*
 * @author ghuang
 *
 * Create:5-Mar-2003
 *
 *
 * $Header$
 * $Log$
 */
package com.jdj.jaas;

import java.io.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import java.util.*;

import org.xml.sax.*;

public class AuthConfig implements Serializable
{
	private static final String DEFAULT_SAX_DRIVER =
		"org.apache.xerces.parsers.SAXParser";
	private String _saxDriverClass = DEFAULT_SAX_DRIVER;
	private SAXConfigHandler _handler = new SAXConfigHandler();


	private String _loginHandlerClass;
	private Hashtable _htLoginInitParam;
	private String _authznHandlerClass;
	private Hashtable _htAuthznInitParam;
	private boolean _bLoaded = false;


	private static AuthConfig _instance;

	public static AuthConfig getInstance ()
	{
		if (_instance == null)
			_instance = new AuthConfig();
		return _instance;
	}

	private AuthConfig()
	{

	}

	public void setLoginHandlerClassName (String handler)
	{
		_loginHandlerClass = handler;
	}

	public String getLoginHandlerClassName ()
	{
		return _loginHandlerClass;
	}

	public void addLoginInitParam (String key, Object value)
	{
		if (_htLoginInitParam == null)
			_htLoginInitParam = new Hashtable ();
		_htLoginInitParam.put(key, value);
	}

	public Hashtable getLoginInitParam ()
	{
		return _htLoginInitParam;
	}

	public void setAuthznHandlerClassName (String handler)
	{
		_authznHandlerClass = handler;
	}

	public String getAuthznHandlerClassName ()
	{
		return _authznHandlerClass;
	}

	public void addAuthznInitParam (String key, Object value)
	{
		if (_htAuthznInitParam == null)
			_htAuthznInitParam = new Hashtable();
		_htAuthznInitParam.put (key, value);
	}

	public Hashtable getAuthznInitParam ()
	{
		return _htAuthznInitParam;
	}

	public boolean isLoaded ()
	{
		return _bLoaded;
	}

	public void load(String file) throws IOException
	{
		
		System.out.println("load config:" + file);
		FileInputStream in = new FileInputStream (file);
		load (in);
	}

	public void load (InputStream in) throws IOException
	{
		try
		{
			build(new InputSource(in));
			_bLoaded = true;
		}
		catch (SAXException aec)
		{
			aec.printStackTrace();
			throw new IOException (aec.getMessage());
		}
	}

	private String checkFile (String file)
	{
		File f = new File (file);
		if (f.exists())
			return file;
		else
			return file;
	}
	private void build(InputSource in) throws SAXException, IOException
	{
		XMLReader parser = XMLReaderFactory.createXMLReader(_saxDriverClass);
		parser.setContentHandler(_handler);
		parser.parse(in);
	}

/////////////////////////////////////////////////////////////////
//
	private class SAXConfigHandler extends DefaultHandler
	{
		private String _curBlock = null; //main block name
		private String _curElem = null; //current element

		public void startElement(String namespaceURI, String localName,
			String qName, Attributes attrs)throws SAXException
		{

			if (AuthConfigConstant.USERAUTH.is(localName))
			{
				//_stackObj.push(localName);
				return;
			}

			//if it is categorylist
			boolean bInMajorBlock = true;
			if (AuthConfigConstant.AUTHENTICATION.is(localName))
			{
				_curBlock = localName;
			}
			else if (AuthConfigConstant.AUTHORIZATION.is(localName))
			{
				_curBlock = localName;
			}
		    else if (AuthConfigConstant.LOGINHANDLER.is(localName))
			{
				handleLoginHandlerAttr(localName, attrs);
			}
			else if (AuthConfigConstant.AUTHZNHANDLER.is(localName))
			{
				handleAuthznHandlerAttr (localName, attrs);
			}
			else if (AuthConfigConstant.INITPARAM.is(localName))
			{
				if (AuthConfigConstant.AUTHENTICATION.is(_curBlock))
					handleLoginInitParamAttr(localName, attrs);
				else if (AuthConfigConstant.AUTHORIZATION.is(_curBlock))
					handleAuthznInitParamAttr (localName, attrs);
			}



		}

		public void endElement(String namespaceURI,
							String localName, String rawName)
		{
		}


		private void handleLoginHandlerAttr (String obj, Attributes attr)
		{
			for (int i = 0, len = attr.getLength(); i < len; i++)
			{
				String name = attr.getLocalName(i);
				if (AuthConfigConstant.CLASS.is(name))
					setLoginHandlerClassName (attr.getValue(i));
			}
		}
		private void handleLoginInitParamAttr (String obj, Attributes attr)
		{
			String key = null;
			String value = null;
			for (int i = 0, len = attr.getLength(); i < len; i++)
			{
				String name = attr.getLocalName(i);
				String val = attr.getValue(i);
				if (AuthConfigConstant.NAME.is(name))
					key = val;
				else if (AuthConfigConstant.VALUE.is(name))
					value = val;
			}
			if (key != null && value != null)
				addLoginInitParam (key, value);
		}

		private void handleAuthznHandlerAttr (String obj, Attributes attr)
		{
			for (int i = 0, len = attr.getLength(); i < len; i++)
			{
				String name = attr.getLocalName(i);
				if (AuthConfigConstant.CLASS.is(name))
					setAuthznHandlerClassName (attr.getValue(i));
			}
		}
		private void handleAuthznInitParamAttr (String obj, Attributes attr)
		{
			String key = null;
			String value = null;
			for (int i = 0, len = attr.getLength(); i < len; i++)
			{
				String name = attr.getLocalName(i);
				String val = attr.getValue(i);
				if (AuthConfigConstant.NAME.is(name))
					key = val;
				else if (AuthConfigConstant.VALUE.is(name))
					value = val;
			}

			if (key != null && value != null)
				addAuthznInitParam (key, value);
		}
	}
}
