<%-- 
    Document   : indexuser
    Created on : Sep 10, 2023, 1:51:47 PM
    Author     : HOME
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="/index/User/" var="searchUser" />
<a href="<c:url value="/register" />" class="btn btn-info mb-2">Add</a>

<form class="d-flex" action="${searchUser}">
    <input width="48" height="48" class="form-control me-2" type="text" name="Username" placeholder="Search username...">
    <select name="roleUser" id="role">
        <option  value=""></option>
        <c:forEach items="${roleList}" var="role">
            <option value="${role.id}">${role.name}</option>
        </c:forEach>
    </select>  
    <button class="btn btn-primary ml-2" type="submit">Search</button>
</form>
<br>
<c:if test="${counter > 1}">
    <ul class="pagination mt-1">
        <li class="page-item"><a class="page-link" href="<c:url value="/index/User/" />">All</a></li>
            <c:forEach begin="1" end="${counter}" var="i">
                <c:url value="/index/User/" var="pageUrl">
                    <c:param name="page" value="${i}"></c:param>
                </c:url>
            <li class="page-item"><a class="page-link" onclick="reload()" href="${pageUrl}">${i}</a></li>
            </c:forEach>
    </ul>
</c:if>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Image</th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Configs</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>
                    <img src="${user.image}" alt="" width="120" height="120" />
                </td>
                <td>${user.firstname}</td>
                <td>${user.lastname}</td>
                <td>${user.email}</td>
                <td>
                    <c:url value="/admin/users/${user.id}" var="Del" />
                    <a href="<c:url value="/admin/users/${user.id}" />" class="btn btn-success">Update</a>
                    <button class="btn btn-danger" onclick="delUser('${Del}', ${user.id})">Delete</button>
                </td>
            </tr>   
        </c:forEach>
    </tbody>
</table>
<script src="<c:url value="/assets/js/main_1.js" />"></script>
