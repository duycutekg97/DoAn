<%-- 
    Document   : login
    Created on : Sep 10, 2023, 3:26:50 PM
    Author     : HOME
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/login/" var="action" />

<div style="margin-left: 250px;margin-right: 250px">
    <form method="post" action="${action}">
        <!-- Username input -->
        <div class="form-outline mb-4">
            <input type="text" required id="form2Example1" class="form-control" placeholder="username..." name="username"/>
            <label class="form-label" for="form2Example1">Username</label>
        </div>

        <!-- Password input -->
        <div class="form-outline mb-4">
            <input type="password" required id="form2Example2" class="form-control" placeholder="password..." name="password" />
            <label class="form-label" for="form2Example2">Password</label>
        </div>

        <!-- 2 column grid layout for inline styling -->

        <div class="form-outline mb-4">
            <% if (request.getParameter("error") != null) { %>
            <p class="text-danger text-center">Đăng nhập không hợp lệ</p>
            <% }%>
        </div>

        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button>

        <!-- Register buttons -->
        <div class="text-center">
            <p>Not a member? <a href="<c:url value="/register" />">Register</a></p>   
        </div>
    </form>
</div>