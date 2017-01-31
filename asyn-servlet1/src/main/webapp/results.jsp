<%--
 * User: Damodar Chetty
 * Date: Jul 23, 2010
 * Time: 9:45:22 PM
 * An introduction to Servlet 3.0 and Java 6 (TC JUG)
 * (c) Software Engineering Solutions, Inc.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p class="mainpara">
    results.jsp:
    completed processing of long running activity with id:
    <span style="color:#ff3300">
                <%=request.getParameter("id")%>
            </span>
    <br/>
    received  at: <%=request.getAttribute("receivedAt")%>: <br/>
    completed at: <%=request.getAttribute("result")%>
</p>

</body>
</html>