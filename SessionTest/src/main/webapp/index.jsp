<!DOCTYPE html>
<html>
<head><title>Cluster test app</title></head>
<body>
<h1>Backend Tomcat: <%= java.net.InetAddress.getLocalHost().getHostAddress() %></h1>
<h1>Session ID: <%=request.getSession().getId()%></h1>
<%
Long counter = (Long) request.getSession().getAttribute("counter");
if (counter == null) {
counter = 0l;
}
counter++;
request.getSession().setAttribute("counter", counter);
%>
<h2>current counter value is ${counter}</h2>
</body>
</html>