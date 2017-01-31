The sample contains:
1) java source code
2) configuration files needed.
3) Ant build script: build.xml
4) DOS batch files: build.cmd, run.cmd

The following assumes you are using JDK 1.4 and the files were extracted to the JaasSample directory.  And you have Ant installed.

Build:
Before you run the build script to build the code, you need to make some modifications:
1) Specify the Java home location in the build.cmd and build.xml
2) Specify the location of xerces.jar in build.xml.  You need to have the Apache's Xerces package for XML parsing.
3) Specify the location of database driver in build.xml, if you need to access to database, in the sample, the MySQL is used.
The build will build two jar files: jaasutil.jar and test.jar.
If you build all, the build script will automatically copy the jaasutil.jar to the JAVA home jre/lib/ext directory.
Run build script by:
run all

Run:
In order to run the sample, you need to set up something:
1) Create the tables in the database.  There are two tables:
table user:

ID            bigInt (20)     PK    No null
name          varchar(20)   
password      varchar(20)
role          varchar(100)

table role:

name   varchar(100)    //role name, such as admin, user, guess
Task  varchar(100)     //task, such as file, or URL 
Action varchar(250)    //action on the task, such as read, write, edit.  
		       //multiple actions 
                       //are seperated by ",".  e.g. read,write
parent varchar(100)    //for role hierarchy, in sample, just null.

2) You need to modify the jaasutil_config.xml to specify the driver, URL, user ID and password for the database.
3) In java JRE /lib/security/java.security, set: policy.provider=com.jdj.jaas.AuthPolicy
4) Copy the jaasutil.jar to the JAVA home jre/lib/ext directory if you did not run the build all.
5) Modify the run.cmd.  Specify the locations for xerces.jar and other stuffs.
Run sample by:
run <user_id> <password>
