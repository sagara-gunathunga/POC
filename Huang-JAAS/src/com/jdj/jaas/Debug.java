/*
 *Debug.java
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

import java.io.PrintWriter;
import java.io.StringWriter;


public class Debug
{
	public static boolean DEBUG = false;//true;
	
	public static void trace (String text)
	{
		if (DEBUG)
			System.out.println(text);
	}
	
	public static void log (Throwable ex)
	{
		System.out.println(getExceptionString(ex));		
	}
	
	public static String getExceptionString (Throwable except)
	{
		StringWriter sw = new StringWriter ();
		PrintWriter pw = new PrintWriter (sw);
		except.printStackTrace (pw);
		return sw.toString();
	}
}
