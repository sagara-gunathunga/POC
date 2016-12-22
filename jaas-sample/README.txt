How to run

1. Use following VM option => -Djava.security.auth.login.config=sample_jaas.config

2. Run without  SecurityManager

 java -Djava.security.auth.login.config=sample_jaas.config -cp target/jaas-sample-1.0.0.jar org.sample.jaas.authentication.SampleAcn


3. Run wit  SecurityManager

 java -Djava.security.manager -Djava.security.policy==sample-auth-auth.policy  -Djava.security.auth.login.config=sample_jaas.config -cp target/jaas-sample-1.0.0.jar org.sample.jaas.authentication.SampleAcn


3. User/pass => testUser/testPassword


5. Run Authz sample

 java -Djava.security.manager -Djava.security.policy==sample-autz.policy  -Djava.security.auth.login.config=sample_jaas.config -cp target/jaas-sample-1.0.0.jar org.sample.jaas.authorization.SampleAzn



6. Run AccessControllerSample

java -Djava.security.manager -Djava.security.policy==sample-autz.policy  -Djava.security.auth.login.config=sample_jaas.config -cp target/jaas-sample-1.0.0.jar org.sample.jaas.authorization.AccessControllerSample