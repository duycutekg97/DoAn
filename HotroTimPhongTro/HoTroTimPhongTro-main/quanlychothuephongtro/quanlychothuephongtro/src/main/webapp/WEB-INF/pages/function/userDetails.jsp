<%-- 
    Document   : userDetails
    Created on : Sep 22, 2023, 1:53:26 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<section class="vh-100" style="background-color: #9de2ff;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-md-12 col-lg-7 col-xl-7">
                <div class="card" style="border-radius: 15px;">
                    <div class="card-body p-3">
                        <div class="d-flex text-black">
                            <div class="flex-shrink-0">
                                <img src="${host.image}"
                                     alt="Generic placeholder image" class="img-fluid"
                                     style="width: 200px;height: 100%; border-radius: 10px;">
                            </div>
                            <div class="justify-content-between align-items-center flex-grow-1 ms-3">
                                <h5 class="mb-1 text-center">${host.firstname} ${host.lastname}</h5>
                                <p class="mb-2 pb-1 text-center" style="color: #2b2a2a;">${host.fkuserroleuserId.name}</p>
                                <p class="mb-2 pb-1 text-center" style="color: #2b2a2a;">Join: ${host.createdDate}</p>
                                <c:if test="${host.fkuserroleuserId.name eq 'ROLE_HOST' and pageContext.request.userPrincipal.name != null}">
                                    <div class="d-flex justify-content-center p-2 mb-2"
                                         style="background-color: #efefef;">               
                                        <div class="px-3">
                                            <p class="small text-muted mb-1">Followers</p>
                                            <p class="mb-0" style="text-align: center;">${numberFollower}</p>
                                        </div>
                                    </div>

                                    <div class="d-flex pt-1">
                                        <c:if test="${principalInfo.id != host.id}">
                                            <c:choose>
                                                <c:when test="${follow == null}">
                                                    <c:url value="/follow/${principalInfo.id}/renter/${host.id}/host/following" var="following" />
                                                    <button  type="button" class="btn btn-primary flex-grow-1" onclick="follow('${following}', ${principalInfo.id},${host.id})">Follow</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:url value="/follow/${principalInfo.id}/renter/${host.id}/host/unfollow" var="following" />
                                                    <button  type="button" class="btn btn-success flex-grow-1" onclick="unFollow('${following}', ${principalInfo.id},${host.id})">Following</button>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:if>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>