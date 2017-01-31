/**
 * LoginSourceAdapter.java
 *
 * @author ghuang
 * $Header$
 * $Log$
 */

package com.jdj.jaas;


import java.util.Hashtable;

public interface LoginSourceAdapter
{
	public void initialize (Hashtable htProperty);
	public boolean authenticate (String userID, char[] password);
	public String[] getGroupNames (String userID);
	public void terminate ();
}
