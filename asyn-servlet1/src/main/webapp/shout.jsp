<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shoutout</title>
</head>
<body>
<div id="message">
    <% if (application.getAttribute("message") != null) {%>
    <%=application.getAttribute("message")%>
    <%}%>
</div>
</body>
</html>