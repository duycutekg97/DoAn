<%-- 
    Document   : indexMotel
    Created on : Sep 13, 2023, 1:28:02 PM
    Author     : HOME
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:forEach items="${motelFalse}" var="motel">
    <c:if test="${motel.apply eq false}">
        <c:if test="${principalInfo.fkuserroleuserId.name eq 'ROLE_ADMIN'}">
            <a href="<c:url value="/host/motels/${motel.id}" />" class="btn btn-success">Update</a>
            <c:url value="/host/motels/${motel.id}/delete" var="Del" />
            <button class="btn btn-danger" onclick="delUser('${Del}', ${motel.id})">Delete</button>
            <c:url value="/admin/active/${motel.id}/motel" var="Apply" />
            <button class="btn btn-info" onclick="apply('${Apply}', ${motel.id})">Apply</button>
        </c:if>

        <a href="<c:url value="/detailMotel/${motel.id}" />">
            <div class="container-fluid mb-4">
                <div class="row"  style="border: 1px solid; padding:5px">
                    <div class="col-4 p-3 bg-white text-black">
                        <div class="row mt-2">
                            <c:set var="postCount" value="0" />

                            <c:forEach items="${listMotelImages}" var="image">
                                <c:if test="${motel.id eq image.fkmotelimagemotelId.id && postCount lt 4}">
                                    <c:choose>
                                        <c:when test="${postCount eq 0}">
                                            <div class="col-12"> 
                                                <img src="${image.image}"
                                                     style="background-size: cover;background-position: center;background-repeat: no-repeat;width:100%;height:100%"/>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="col-4 mt-2"> 
                                                <img src="${image.image}"
                                                     style="background-size: cover;background-position: center;background-repeat: no-repeat;width:100%;height:100%"/>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:set var="postCount" value="${postCount + 1}" />
                                </c:if>
                            </c:forEach>                
                        </div>       
                    </div>
                    <div class="col-8 p-3 bg-white text-black">
                        <div>
                            <h3>
                                <span>
                                    ${motel.title}
                                </span>
                            </h3>
                            <div>
                                <div>
                                    <span style="color:red;">${motel.price}/Month</span>
                                    <span>·</span><span style="color:red;"> ${motel.acreage} m²</span>

                                    <span>·</span>
                                    <span><span> ${motel.numberofresidents} </span><i class="fa fa-user"></i></span>
                                    <span>·</span>
                                    <span><span> 2 </span><i class="fa fa-bed"></i></span>
                                    <div></div>
                                </div>
                                <div>
                                    <i class="fa fa-map-marker"></i>
                                    <span>District: ${motel.district}, ${motel.cityprovince}</span>
                                </div>
                                <div></div>
                            </div>
                            <div>
                                ${motel.description}
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row" style="border: 1px solid; padding:5px">
                    <div class="col-1 p-3 bg-white text-white" >
                        <img src="${motel.fkmoteluserId.image}"
                             style="background-size: cover;background-position: center;background-repeat: no-repeat;border-radius: 50%;width:100%;height:100%"/>
                    </div>
                    <div class="col-6 p-3 bg-white text-black">
                        <div class = "row"> 
                            Own ${motel.fkmoteluserId.lastname}
                        </div>
                        <div class = "row"> 
                            Time : ${motel.createdDate}
                        </div>

                    </div>
                    <div class="col-5 p-3 bg-white text-black">
                        <div class = "row"> 
                            <span style="border: 1px solid; padding:5px;width:40%">
                                <i class="fa fa-phone"></i>
                                <span>  ${motel.phone}</span>

                            </span>
                        </div>                        
                    </div>

                </div>
            </div>
        </a>
    </c:if>
</c:forEach>
<script src="<c:url value="/assets/js/main_1.js" />"></script>
