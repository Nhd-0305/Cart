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
        <h1>Your cart</h1>

        <c:choose>
            <c:when test="${empty sessionScope.cart.items}">
                <p>Your cart is empty.</p>
            </c:when>
            <c:otherwise>
                <table border="1" cellpadding="5" cellspacing="0">
                    <tr>
                        <th>Quantity</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Amount</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${sessionScope.cart.items}" var="item">
                        <tr>
                            <td>
                                <form action="${pageContext.request.contextPath}/cart" method="post">
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="id" value="${item.cd.id}">
                                    <input type="text" name="quantity" value="${item.quantity}" size="3">
                                    <input type="submit" value="Update">
                                </form>
                            </td>
                            <td>${item.cd.description}</td>
                            <td><fmt:formatNumber value="${item.cd.price}" type="currency"/></td>
                            <td><fmt:formatNumber value="${item.total}" type="currency"/></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/cart" method="post">
                                    <input type="hidden" name="action" value="remove">
                                    <input type="hidden" name="id" value="${item.cd.id}">
                                    <input type="submit" value="Remove Item">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <p><strong>To change the quantity</strong>, enter the new quantity and click on the Update button.</p>
            </c:otherwise>
        </c:choose>

        <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline">
            <input type="hidden" name="action" value="continue">
            <input type="submit" value="Continue Shopping">
        </form>
        <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline">
            <input type="hidden" name="action" value="checkout">
            <input type="submit" value="Checkout">
        </form>
    </body>
</html>
