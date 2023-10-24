<%-- 
    Document   : motelDetail
    Created on : Sep 13, 2023, 3:56:17 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    .link-grey {
        color: #aaa;
    }
    .link-grey:hover {
        color: #00913b;
    }

    .mySlides {
        display: none
    }
    img {
        vertical-align: middle;
    }

    /* Slideshow container */
    .slideshow-container {
        max-width: 1000px;
        position: relative;
        margin: auto;
    }

    /* Next & previous buttons */
    .prev, .next {
        cursor: pointer;
        position: absolute;
        top: 50%;
        width: auto;
        padding: 16px;
        margin-top: -22px;
        color: white;
        font-weight: bold;
        font-size: 18px;
        transition: 0.6s ease;
        border-radius: 0 3px 3px 0;
        user-select: none;
    }

    /* Position the "next button" to the right */
    .next {
        right: 0;
        border-radius: 3px 0 0 3px;
    }

    /* On hover, add a black background color with a little bit see-through */
    .prev:hover, .next:hover {
        background-color: rgba(0,0,0,0.8);
    }

    /* Caption text */
    .text {
        color: #f2f2f2;
        font-size: 15px;
        padding: 8px 12px;
        position: absolute;
        bottom: 8px;
        width: 100%;
        text-align: center;
    }

    /* Number text (1/3 etc) */
    .numbertext {
        color: #f2f2f2;
        font-size: 12px;
        padding: 8px 12px;
        position: absolute;
        top: 0;
    }

    /* The dots/bullets/indicators */
    .dot {
        cursor: pointer;
        height: 15px;
        width: 15px;
        margin: 0 2px;
        background-color: #bbb;
        border-radius: 50%;
        display: inline-block;
        transition: background-color 0.6s ease;
    }

    .activeMySlide, .dot:hover {
        background-color: #717171;
    }

    /* Fading animation */
    .fadeSlide {
        animation-name: fade;
        animation-duration: 1.5s;
    }

    @keyframes fade{
        from {
            opacity: .4
        }
        to{
            opacity: 1
        }

    }

    /* On smaller screens, decrease text size */
    @media only screen and (max-width: 300px) {
        .prev, .next,.text {
            font-size: 11px
        }
    }
    #follow-button {
        color: #3399FF;
        font-family: "Helvetica";
        font-size: 10pt;
        background-color: #ffffff;
        border: 1px solid;
        border-color: #3399FF;
        border-radius: 3px;
        width: 85px;
        height: 30px;
        top: 50px;
        left: 50px;
        cursor: hand;
    }

</style>

<div class="section">
    <div class="container">
        <div class="row justify-content">
            <div class="col-lg-8">
                <div class="img-property-slide-wrap">
                    <div class="tns-outer mb-4" id="tns1-ow">
                        <div class="slideshow-container">
                            <c:set var="imgCountSlide" value="0" />

                            <c:forEach items="${motelImage}"  var="img">
                                <c:set var="imgCountSlide" value="${imgCountSlide + 1}" />

                                <div class="mySlides fadeSlide">
                                    <div class="numbertext text-info">Image : ${imgCountSlide}</div>
                                    <img src="${img.image}" style="width:100%">
                                </div>
                            </c:forEach>



                            <a class="prev" onclick="plusSlides(-1)">❮</a>
                            <a class="next" onclick="plusSlides(1)">❯</a>

                        </div>
                        <br>

                        <div style="text-align:center">
                            <c:set var="imgCount" value="0" />
                            <c:forEach items="${motelImage}"  var="img">
                                <c:set var="imgCount" value="${imgCount + 1}" />

                                <span class="dot" onclick="currentSlide(${imgCount})"></span> 
                            </c:forEach>
                        </div>
                    </div>

                </div>
                <div class="row ml-1 mr-1" style="width: 100%;height:100%">
                    ${motelDetail.map}                    
                </div>
            </div>
            <div class="col-lg-4">
                <h5 class="heading text-primary">${motelDetail.address}</h5>
                <p class="meta">District: ${motelDetail.district},City: ${motelDetail.cityprovince}</p>
                <p class="text-black-50">
                    ${motelDetail.title}
                </p>
                <p class="text-black-50">
                    ${motelDetail.description}
                </p>
                <p class="text-black-50">
                    <span style="color:red;">${motelDetail.price}/Month</span>
                    <span>·</span><span style="color:red;"> ${motelDetail.acreage} m²</span>

                    <span>·</span>
                    <span><span> ${motelDetail.numberofresidents} </span><i class="fa fa-user"></i></span>
                    <span>·</span>
                    <span><span> 2 </span><i class="fa fa-bed"></i></span>
                </p>

                <div class="d-block agent-box mt-5">
                    <div class="img mb-4 d-flex">
                        <img src="${motelDetail.fkmoteluserId.image}" style="border-radius: 50%;" class="img-circle" alt="Cinque Terre" width="50" height="50"> 
                        <div class="row">
                            <a class="text-primary fw-bold mb-0" href="<c:url value="/details/users/${motelDetail.fkmoteluserId.id}"/>"><h5 class=" ml-2">${motelDetail.fkmoteluserId.firstname} ${motelDetail.fkmoteluserId.lastname}</h5></a>
                            <c:if test="${principalInfo.id != motelDetail.fkmoteluserId.id}">
                                <c:choose>
                                    <c:when test="${follow == null}">
                                        <c:url value="/follow/${principalInfo.id}/renter/${motelDetail.fkmoteluserId.id}/host/following" var="following" />
                                        <button class="btn ml-4" id="follow-button" onclick="follow('${following}', ${principalInfo.id},${motelDetail.fkmoteluserId.id})">+ Follow</button>
                                    </c:when>
                                    <c:otherwise>
                                        <c:url value="/follow/${principalInfo.id}/renter/${motelDetail.fkmoteluserId.id}/host/unfollow" var="following" />

                                        <button class="btn ml-4" id="follow-button" onclick="unFollow('${following}', ${principalInfo.id},${motelDetail.fkmoteluserId.id})">Following</button>
                                    </c:otherwise>
                                </c:choose>

                            </c:if>
                        </div>

                    </div>
                    <div class="">
                        <div class="meta mb-3">Phone: ${motelDetail.phone}</div>
                        <div class="meta mb-3">Mail: ${motelDetail.fkmoteluserId.email}</div>

                    </div>
                </div>

            </div>

        </div>

        <div class="row mt-5">
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <div  style="position: absolute; width: 100%; height: 100%; z-index: 1; overflow: hidden;" id="lockscreen">
                    <a href="<c:url value="/login" />" class="btn btn-success">Sign In first</a>
                </div>
            </c:if>
            <div id="content123">

                <section style="background-color: #f7f6f6;">
                    <div class="container my-0 py-5 text-dark">
                        <div class="row d-flex justify-content-center">
                            <div class="col-md-12 col-lg-10 col-xl-8">
                                <div class="d-flex justify-content-between align-items-center mb-4">
                                    <h4 tabindex="-1" class="text-dark mb-0">Comments ${counter}</h4>                               
                                </div>
                                <div class="justify-content-center" style="color:red;text-align: center;">${msgErrShow}</div>
                                <c:url value="/detailMotel/${motelDetail.id}/evaluate" var="action" />
                                <form:form action="${action}" method="post"  modelAttribute="EvaluatelAdd" enctype="multipart/form-data" onsubmit="return validateForm()">
                                    <form:hidden path="id" />
                                    <div class="mb-1" class="d-flex">
                                        <form:textarea tabindex="-1" required="true" path="comment" class="form-control" id="comment" name="comment" rows="3"/>
                                    </div>
                                    <button tabindex="-1" type="submit" class="btn btn-primary">Send</button>
                                </form:form>
                                <c:forEach items="${EvaluatelList}" var="evaluate">
                                    <c:if test="${evaluate.fkevaluteevaluateId == null}">
                                        <div class="card mb-0 mt-2">
                                            <div class="card-body">
                                                <div class="d-flex flex-start">
                                                    <img class="rounded-circle shadow-1-strong me-3"
                                                         src="${evaluate.fkevaluteuserIdRespondent.image}" alt="avatar" width="40"
                                                         height="40" />
                                                    <div class="w-100">
                                                        <div class="d-flex justify-content-between align-items-center mb-3">
                                                            <h6 class="text-primary fw-bold mb-0">
                                                                <a class="text-primary fw-bold mb-0" href="<c:url value="/details/users/${evaluate.fkevaluteuserIdRespondent.id}"/>">${evaluate.fkevaluteuserIdRespondent.lastname}</a>
                                                                <span class="text-dark ms-2">${evaluate.comment}</span>
                                                            </h6>
                                                            <p class="mb-0">${evaluate.createdDate}</p>
                                                        </div>
                                                        <div class="d-flex justify-content-between align-items-center">
                                                            <p class="small mb-0" style="color: #aaa;">
                                                            </p>
                                                            <div class="d-flex flex-row">
                                                                <i class="fa fa-check-circle text-primary"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <c:url value="/detailMotel/${motelDetail.id}/evaluate/${evaluate.id}/reply/${evaluate.id}" var="action" />
                                        <form:form action="${action}" method="post"  modelAttribute="EvaluatelAdd" enctype="multipart/form-data">

                                            <form:hidden path="id" />
                                            <div class="mb-0" class="d-flex">
                                                <form:textarea tabindex="-1" required="true" path="comment" class="form-control" id="comment" name="comment" rows="1"/>
                                            </div>
                                            <button tabindex="-1" type="submit" class="btn btn-primary">Reply to ${evaluate.fkevaluteuserIdRespondent.lastname}</button>
                                        </form:form>
                                    </c:if>
                                    <c:forEach items="${ReplyList}" var="reply">
                                        <c:if test="${reply.fkevaluteevaluateId.id != null && reply.fkevaluteevaluateId.id eq evaluate.id 
                                              }">
                                            <div class="card mb-0 mt-2 ml-5">
                                                <div class="card-body">
                                                    <div class="d-flex flex-start mb-3">
                                                        <img class="rounded-circle shadow-1-strong me-3"
                                                             src="${reply.fkevaluteuserIdRespondent.image}" alt="avatar" width="40"
                                                             height="40" />
                                                        <div class="w-100">
                                                            <div class="d-flex justify-content-between align-items-center mb-3">
                                                                <h6 class="text-primary fw-bold mb-0">
                                                                    <a class="text-primary fw-bold mb-0" href="<c:url value="/details/users/${reply.fkevaluteuserIdRespondent.id}"/>">${reply.fkevaluteuserIdRespondent.lastname}</a>
                                                                    <span class="text-dark ms-2">${reply.comment}</span>
                                                                </h6>
                                                                <p class="mb-0">${reply.createdDate}</p>
                                                            </div>
                                                            <div class="d-flex justify-content-between align-items-center">
                                                                <p class="small mb-0" style="color: #aaa;">
                                                                    Reply comment ${reply.fkevaluteevaluateIdReply.fkevaluteuserIdRespondent.lastname}
                                                                </p>
                                                                <div class="d-flex flex-row">
                                                                    <i class="fa fa-check-circle text-primary"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <c:url value="/detailMotel/${motelDetail.id}/evaluate/${evaluate.id}/reply/${reply.id}" var="action" />
                                                <form:form action="${action}" method="post"  modelAttribute="EvaluatelAdd" enctype="multipart/form-data">
                                                    <div class="mb-0" class="d-flex">
                                                        <form:textarea tabindex="-1" required="true" path="comment" class="form-control" id="comment" name="comment" rows="1"/>
                                                    </div>
                                                    <button tabindex="-1" type="submit" class="btn btn-primary btn-block">Reply to ${reply.fkevaluteuserIdRespondent.lastname}</button>
                                                </form:form>
                                            </div>

                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </section>
            </div>

        </div>
    </div>
</div>        
<script>
    document.getElementById("lockscreen").style.height = document.getElementById("content123").clientHeight + "px";
</script>
<script>
    <% if (request.getParameter("msgErrShow") != null) { %>
    alert("Comment must not be empty");
    <% }%>
</script>

<script>
    let slideIndex = 1;
    showSlides(slideIndex);

    function plusSlides(n) {
        showSlides(slideIndex += n);
    }

    function currentSlide(n) {
        showSlides(slideIndex = n);
    }

    function showSlides(n) {
        let i;
        let slides = document.getElementsByClassName("mySlides");
        let dots = document.getElementsByClassName("dot");
        if (n > slides.length) {
            slideIndex = 1
        }
        if (n < 1) {
            slideIndex = slides.length
        }
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }

        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" activeMySlide", "");
        }
        slides[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " activeMySlide";
    }
</script>
<script>
    function validateForm() {
        var comment = document.getElementById("comment");
        if (comment.value === "") {
            alert("Commemt must not be emtpy");
            return false;
        } else {
            return true;
        }
    }
</script>

