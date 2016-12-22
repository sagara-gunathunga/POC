package org.sample.jaas.authentication;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * Created by sagara on 10/28/16.
 */
public class SampleAcn {

    public static void main(String[] args) {

        // Obtain a LoginContext, needed for authentication. Tell it
        // to use the LoginModule implementation specified by the
        // entry named "Sample" in the JAAS login configuration
        // file and to also use the specified CallbackHandler.
        LoginContext lc = getLoginContext("Sample");


        // attempt authentication
        try {

            lc.login();

            Subject mySubject = lc.getSubject();

            System.out.println("Authentication succeeded!");

            System.out.println("Authentication user : " + mySubject );



        } catch (LoginException e) {
            e.printStackTrace();
        }


    }

    private static LoginContext getLoginContext(String name) {
        try {
            return new LoginContext(name, new MyCallbackHandler());
        } catch (LoginException | SecurityException le) {
            System.err.println("Cannot create LoginContext. "
                    + le.getMessage());
            le.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}


