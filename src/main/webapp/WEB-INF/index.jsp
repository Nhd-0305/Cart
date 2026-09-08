<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Murach's Java Servlets and JSP</title>
    </head>
    <body>
        <h1>CD list</h1>
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>Description</th>
                <th>Price</th>
                <th></th>
            </tr>
            <c:forEach items="${cdList}" var="cd">
                <tr>
                    <td>${cd.description}</td>
                    <td><fmt:formatNumber value="${cd.price}" type="currency"/></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/cart" method="post">
                            <input type="hidden" name="action" value="add">
                            <input type="hidden" name="id" value="${cd.id}">
                            <input type="submit" value="Add To Cart">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
