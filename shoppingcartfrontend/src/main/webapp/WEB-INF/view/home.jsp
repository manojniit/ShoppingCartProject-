<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>ShoppingCart</title>
</head>
<body>
<div data-role="page">
<div data-role="header" data-position="fixed">
<h1>Welcome To My Cart</h1>
<table width="100%">
<tr>
<c:choose>
<c:when test="${empty loggedInUser}">
<td align="left">Existing User<a href="login">login</a></td>
<td align="center">New User<a href="register">register</a></td>
</c:when>
<c:when test="${not empty loggedInUser}">
<td>Welcome ${loggedInUser}</td>
<td align="right"><a href="logout">logout</a></td>
</tr>
<tr>
<c:if test="${loggedOut==true}">
<td>${logout message}</td>
</c:if>
</tr>
<tr>
<c:if test="${not empty cartSize}">
<td align="right"><a href="myCart">My Cart</a>(${cartSize})
</td>
</c:if>
</c:when>
</c:choose>
</table>
</div>
<div data-role="main" class="ui-content">
<hr color="red" size="5">
<ul id="menu">
<c:if test="${not empty categoryList}">
<c:forEach items="${categoryList}" var="category">
<li><a href="${category.name}">${category.name}</a>
<ul>
<c:forEach items="${category.products}" var="product">
<li><a href="<c:url value='product/get/${product.id }'">${product.id}</a>
</c:forEach>
</ul>
</li>
</c:forEach>
</c:if>
</ul>
<hr color="red" size="5">
<br> <br> <br>
<div>
<c:if test="${!empty selectedProduct.name}">
<table>
<tr>
<th align="left" width="100">Product ID</th>
<th align="left" width="100">Product Name</th>
<th align="left" width="100">Product Description</th>
<th align="left" width="100">Product Price</th>
<th align="left" width="100">Product Category</th>
<th align="left" width="100">Product Supplier</th>
</tr>
<td align="left">${selectedProduct.id}</td>
<td align="left">${selectedProduct.name}</td>
<td align="left">${selectedProduct.description}</td>
<td align="left">${selectedProduct.price}</td>
<td align="left">${selectedProduct.category}</td>
<td align="left">${selectedProduct.suppier}</td>
</tr>
</table>
</c:if>
</div>
<div id="register">
<c:if test="${isUserClickedRegister==true }">
<%@ include file="/view/register.jsp" %>
<%@ include file="/view/membership.jsp" %>
</c:if>
</div>
<div id="login">
<c:if test="${isUserClickedLogin==true|| invalidCredentials==true}">
<div id="error">${errorMessage}</div>
<%@ include file="/view/login.jsp" %>
</c:if>
</div>
<div id="admin">
<c:if test="${isAdmin==true }">
<%@ include file="/view/admin.jsp" %>
</c:if>
</div>
<div id="categories">
<c:if test="${isAdminClickedCategories==true }">
<%@ include file="//view/admin.jsp" %>
<%@ include file="/view/category.jsp" %>
</c:if>
</div>
<div id="products">
<c:if test="${isAdminClickedProducts==true }">
<%@ include file="/view/admin.jsp" %>
<%@ include file="/view/product.jsp" %>
</c:if>
</div>
<div id="suppliers">
<c:if test="${isAdminClickedSuppliers==true }">
<%@ include file="/view/admin.jsp"%>
<%@ include file="/view/supplier.jsp"%>
</c:if>
</div>
<div id="categories">
<c:if test="${displayCart==true }">
<table>
<tr>
<th align="left" width="100">Cart ID</th>
<th align="left" width="100">Product Name</th>
<th align="left" width="100">Quantity</th>
<th align="left" width="100">Price</th>
<th  colspan="2" align="left" width="100">Action</th>
</tr>
<c:forEach items="${cartList}" var="cart">
<tr>
<td align="left">${cart.id}</td>
<td align="left">${cart.productName}</td>
<td align="left">${cart.quantity}</td>
<td align="left">$cart.price}</td>
<td align="left"></td>
<a href="<c:url value='/mycart/delete/${cart.id }'/>">Delete</a>

</tr>

</c:forEach>
</table>
<h2>Total cost:${totalAmount}</h2>
<br>
<a href="javascript:showhide('paymentMethod')">payment Method</a>
<br>
<br>
<div id="paymentMethod">
<table>
<tr>
<td align="left"><input type="radio" name="pMethod" onclick="javascript:hide('netBanking');"checked>></td>
<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CashOnDelivery</td>
</tr>



</table>
</div>

</c:if>
</div>

</body>
</html>