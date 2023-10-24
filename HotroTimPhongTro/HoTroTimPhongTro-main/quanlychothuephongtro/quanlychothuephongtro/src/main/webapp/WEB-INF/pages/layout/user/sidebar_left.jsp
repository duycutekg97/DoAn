<%-- 
    Document   : sidebar_left
    Created on : Jul 18, 2023, 12:57:29 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/logout" var="action" />
<nav id="sidebar">
    <div class="p-4 pt-5">        
        <c:choose>
            <c:when test="${pageContext.request.userPrincipal.name != null}">
                <p style="text-align: center;">${principalInfo.firstname} ${principalInfo.lastname}</p>
                <a href="<c:url value="/details/users/${principalInfo.id}"/>" class="img logo rounded-circle mb-5" style="background-image: url(${principalInfo.image});"></a>
                <a style="text-align: center;" class="nav-link" href="<c:url value="/" />">${pageContext.request.userPrincipal.name}</a>
                <form:form action="${action}" method="post">
                    <p style="text-align: center;">
                        <input type="submit" value="Logoff" class="btn btn-danger"></input>
                    </p>
                </form:form>
            </c:when>
            <c:otherwise>
                <p style="text-align: center;">
                    <a href="<c:url value="/login" />" class="btn btn-success">Sign In</a>
                    <a href="<c:url value="/register" />" class="btn btn-info">Register</a>

                </p>
            </c:otherwise>
        </c:choose>
        <ul class="list-unstyled components mb-5">
            <li class="active">
                <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Home</a>
                <ul class="collapse list-unstyled" id="homeSubmenu">
                    <li>
                        <a href="#">Home 1</a>
                    </li>
                    <li>
                        <a href="#">Home 2</a>
                    </li>
                    <li>
                        <a href="#">Home 3</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#">About</a>
            </li>
            <li>
                <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Pages</a>
                <ul class="collapse list-unstyled" id="pageSubmenu">
                    <li>
                        <c:if test="${principalInfo.fkuserroleuserId.name eq 'ROLE_ADMIN'}">
                            <a href="<c:url value="/index/User/" />">User</a>
                            <a href="<c:url value="/admin/motels/apply/false/" />">Awaiting Review Motel</a>
                            <a href="<c:url value="/admin/chart/" />">Chart</a>
                        </c:if>

                        <a href="<c:url value="/index/Motel/" />">Motel</a>
                        <a href="<c:url value="/indexFindAccommodation/" />">Find Accommodation</a>
                        <a href="<c:url value="/gpt/" />">GPT</a>        
                    </li>

                </ul>
            </li>
            <li>
                <a href="#">Portfolio</a>
            </li>
            <li>
                <a href="#">Contact</a>
            </li>
        </ul>

        <div class="footer">
            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib.com</a>
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
        </div>

    </div>
</nav>
<script src="<c:url value="/assets/js/main_1.js" />"></script>

