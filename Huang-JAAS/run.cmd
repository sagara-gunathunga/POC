cls
rem @echo off
echo  This file is used to run jaas sample

set JAVA_HOME=C:/j2sdk1.4.1

set DB_PATH=D:/lib/mysql-connector-java-3.0.5-gamma/mysql-connector-java-3.0.5-gamma-bin.jar
set XML_PATH=C:/xalan22D14/bin/xerces.jar
set JAR_PATH=./dist/lib/jaasutil.jar
set TEST_PATH=./dist/lib/test.jar
set MYPATH=.;%CLASSPATH%;%DB_PATH%;%TEST_PATH%;%XML_PATH%

%JAVA_HOME%\bin\java -Xbootclasspath:%DBPATH%;%JAVA_HOME%/jre/lib/rt.jar -classpath %MYPATH% -Djava.security.manager -Djava.security.policy==jaasutil.policy -Djava.security.auth.login.config==jaasutil.config -Dcom.auth.config==jaasutil_config.xml test.Test %1 %2 %3 %4 %5 %6 %7 %8 %9

