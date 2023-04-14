<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dora
  Date: 28.03.2023
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>All People</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Читатели</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="person" items="${allPeople}">
        <tr>
            <td>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">${person.name}</li>
                </ul>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>