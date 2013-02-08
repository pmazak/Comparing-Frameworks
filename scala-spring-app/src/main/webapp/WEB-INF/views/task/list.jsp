<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="/">&lt;&lt; Back Home</a>
<hr/>
<h2>List Page</h2>
<hr/>
List of tasks.
<table border=1 cellspacing=0 cellpadding=3>
  <c:forEach var="task" items="${tasks}">
    <tr>${task.value}</tr>
  </c:forEach>
</table>
