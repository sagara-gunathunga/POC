package com.jdj.jaas;

import java.io.IOException;

//import javax.security.auth.callback.Callback;
//import javax.security.auth.callback.CallbackHandler;
//import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.callback.*;
//import javax.security.auth.*;
//import java.io.*;
//import java.util.*;
/**
 * @author ghuang
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 *
 * To Use:
 * 	lc	 = new LoginContext("AuthLogin", new UserAuthCallbackHandler(userId,
 * password));
 *  lc.login();
 */
public class AuthCallbackHandler implements CallbackHandler
{
	private String _userId;
	private String _password;
	/**
	 * Constructor for UserAuthCallbackHandler.
	 */
	public AuthCallbackHandler(String userID, String password)
	{
		super();

		_userId = userID;
		_password = password;
	}

	/**
	 * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback)
	 */
	public void handle(Callback[] callbacks)
		throws IOException, UnsupportedCallbackException
	{
		if (Debug.DEBUG)
			Debug.trace("UserAuthCallbackHandler::handle");
			
		for (int i = 0; i < callbacks.length; i++)
		{
			if (callbacks[i] instanceof TextOutputCallback)
			{
				// display the message according to the specified type
				TextOutputCallback toc = (TextOutputCallback)callbacks[i];
				switch (toc.getMessageType())
				{
					case TextOutputCallback.INFORMATION:
						System.out.println(toc.getMessage());
					break;
					case TextOutputCallback.ERROR:
						System.out.println("ERROR: " + toc.getMessage());
					break;
					case TextOutputCallback.WARNING:
						System.out.println("WARNING: " + toc.getMessage());
						break;
					default:
						throw new IOException("Unsupported message type: " +
									toc.getMessageType());
				}

			}
			else if (callbacks[i] instanceof NameCallback)
			{
				// prompt the user for a username
				NameCallback nc = (NameCallback)callbacks[i];

				// ignore the provided defaultName
				//System.err.print(nc.getPrompt());
				//System.err.flush();
				//nc.setName((new BufferedReader
				//	(new InputStreamReader(System.in))).readLine());
				nc.setName(_userId);
			}
			else if (callbacks[i] instanceof PasswordCallback)
			{
				// prompt the user for sensitive information
				PasswordCallback pc = (PasswordCallback)callbacks[i];
				//we do not need to read from inputStream
				//System.err.print(pc.getPrompt());
				//System.err.flush();
				//pc.setPassword(readPassword(System.in));
				pc.setPassword(_password.toCharArray());
			}
			else
			{
				throw new UnsupportedCallbackException
						(callbacks[i], "Unrecognized Callback");
			}
		}
	}
}
