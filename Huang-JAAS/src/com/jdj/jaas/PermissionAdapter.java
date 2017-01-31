/**
 * PermissionAdapter.java
 *
 * @author ghuang
 *
 * $Header$
 * $Log$
 */

package com.jdj.jaas;


import java.util.Hashtable;
import java.security.PermissionCollection;
import java.security.Principal;
import java.security.CodeSource;
import java.security.ProtectionDomain;

public interface PermissionAdapter
{
	public void initialize (Hashtable initParams);
	public PermissionCollection getPermissions (Principal[] principals, CodeSource codeSource);
	public PermissionCollection getPermissions (CodeSource codeSource);
	public PermissionCollection getPermissions (ProtectionDomain domain);
	public void terminate ();
}
