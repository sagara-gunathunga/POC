Hello
curl -v http://localhost:8080/hello/sagara
curl -v http://localhost:8080/hello/cancel/sagara


HelloTimeoutHandler
curl -v http://localhost:8080/helloto/to/sagara
curl -v http://localhost:8080/helloto/notto/sagara


PushAndPop
curl -v http://localhost:8080/hello/push/sagara
curl -v http://localhost:8080/hello/push/saman
curl -v http://localhost:8080/hello/pop/sagara

HelloCallback
curl -v http://localhost:8080/callback/sagara
curl -v http://localhost:8080/callback/cancel/sagara


HelloCallbackObject
curl -v http://localhost:8080/callbackobj/sagara
curl -v http://localhost:8080/callbackobj/cancel/sagara