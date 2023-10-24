<%-- 
    Document   : indexFindAccommodation
    Created on : Sep 19, 2023, 1:33:27 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section style="background-color: #eee;">
    <div class="container my-5 py-5">
        <div class="row d-flex justify-content-center">

            <div class="col-md-12 col-lg-10 col-xl-8">
                <c:url value="/indexFindAccommodation/post/" var="action" />

                <form:form action="${action}" method="post"  modelAttribute="FindAccommodationAdd" enctype="multipart/form-data">
                    <h5 class="text-center">Find Accommodation</h5>
                    <h5 class="text-center text-danger">${msgErrShow}</h5>

                    <form:hidden path="id" />
                    <div class="mb-0" class="d-flex">
                        <form:textarea required="true" placeholder="detailed description" path="detaileddescription" 
                                       class="form-control" id="detaileddescription" name="detaileddescription" rows="4"/>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 mb-1 mt-1">
                            <div class="form-outline">
                                <select name="calc_shipping_provinces" required="" class="form-select">
                                    <option value="">Tỉnh / Thành phố</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 mb-1 mt-1">
                            <div class="form-outline">
                                <select name="calc_shipping_district" required="" class="form-select">
                                    <option value="">Quận / Huyện</option>
                                </select>
                            </div>
                        </div>
                        <form:input path="city" type="hidden" id="city" class="billing_address_1" 
                                    name="" value=""/>
                        <form:input path="district" type="hidden" id="district" class="billing_address_2" 
                                    name="" value=""/>
                    </div>
                    <div class="mb-1" class="d-flex">
                        <form:textarea  placeholder="Google map iframe (Optional)" path="map" 
                                        class="form-control" id="map" name="map" rows="1"/>
                    </div>
                    <button type="submit" class="btn btn-info btn-block mb-4">Post news</button>
                </form:form>
                <c:if test="${counter > 1}">
                    <ul class="pagination mt-1">
                        <li class="page-item"><a class="page-link" href="<c:url value="/indexFindAccommodation/" />">All</a></li>
                            <c:forEach begin="1" end="${counter}" var="i">
                                <c:url value="/indexFindAccommodation/" var="pageUrl">
                                    <c:param name="page" value="${i}"></c:param>
                                </c:url>
                            <li class="page-item"><a class="page-link" onclick="reload()" href="${pageUrl}">${i}</a></li>
                            </c:forEach>
                    </ul>
                </c:if>
                <c:forEach items="${findMotelList}" var="find">
                    <c:if test="${find.fkfindmotelfindmotelId.id == null}">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="d-flex flex-start align-items-center">
                                    <img class="rounded-circle shadow-1-strong me-3"
                                         src="${find.fkfindmoteluserId.image}" alt="avatar" width="60"
                                         height="60" />
                                    <div>
                                        <a href="<c:url value="/details/users/${find.fkfindmoteluserId.id}"/>"><h6 class="fw-bold text-primary mb-1">${find.fkfindmoteluserId.firstname} ${find.fkfindmoteluserId.lastname}</h6></a>
                                        <p class="text-muted small mb-0">
                                            ${find.createdDate}
                                        </p>
                                    </div>
                                </div>

                                <div class="mt-1 mb-1 pb-2 p-3">
                                    <i class="fa fa-map-marker"></i>
                                    <span>District: ${find.district}, ${find.city}</span>
                                    <p style="font-size: 14px;color: black; word-wrap: break-word;">
                                        ${find.detaileddescription}
                                    </p>


                                </div>
                                <div class="row ml-1 mr-1" style="width: 100%;height:100%">
                                    ${find.map}                    
                                </div>   

                            </div>
                            <div class="card-footer py-3 border-0" style="background-color: #f8f9fa;">
                                <div class="d-flex flex-start w-100">
                                    <c:if test="${principalInfo != null}">
                                        <img class="rounded-circle shadow-1-strong me-3"
                                             src="${principalInfo.image}" alt="avatar" width="40"
                                             height="40" />
                                    </c:if>

                                    <div class="form-outline w-100">
                                        <c:url value="/indexFindAccommodation/post/${find.id}/findmotelId/${find.id}/findmotelIdReply" var="action" />
                                        <form:form modelAttribute="FindAccommodationAdd" action="${action}" method="post"  enctype="multipart/form-data">
                                            <form:textarea required="true" path="detaileddescription" class="form-control" id="textAreaExample" rows="4"
                                                           style="background: #fff;"/>
                                            <button type="submit" class="btn btn-primary btn-block">Reply to ${find.fkfindmoteluserId.lastname}</button>

                                        </form:form>
                                    </div>
                                </div>
                                <c:forEach items="${findMotelListReply}" var="reply">
                                    <c:if test="${reply.fkfindmotelfindmotelId.id != null && reply.fkfindmotelfindmotelId.id eq find.id 
                                          }">
                                        <div class="card mb-0 mt-2 ml-0">
                                            <div class="card-body">
                                                <div class="d-flex flex-start mb-0">
                                                    <img class="rounded-circle shadow-1-strong me-3"
                                                         src="${reply.fkfindmoteluserId.image}" alt="avatar" width="40"
                                                         height="40" />
                                                    <div class="w-100">
                                                        <div class="d-flex justify-content-between align-items-center mb-0">
                                                            <h6 class="text-primary fw-bold mb-0">
                                                                <a class="text-primary fw-bold mb-0" href="<c:url value="/details/users/${reply.fkfindmoteluserId.id}"/>">${reply.fkfindmoteluserId.lastname}</a>
                                                                <span class="text-dark ms-2">${reply.detaileddescription}</span>
                                                            </h6>
                                                            <p class="mb-0">${reply.createdDate}</p>
                                                        </div>
                                                        <div class="d-flex justify-content-between align-items-center">
                                                            <p class="small mb-0" style="color: #aaa;">
                                                                Reply comment ${reply.fkfindmotelfindmotelIdReply.fkfindmoteluserId.lastname}
                                                            </p>
                                                            <div class="d-flex flex-row">
                                                                <i class="fa fa-check-circle text-primary"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <c:url value="/indexFindAccommodation/post/${find.id}/findmotelId/${reply.id}/findmotelIdReply" var="action" />
                                            <form:form action="${action}" modelAttribute="FindAccommodationAdd" method="post"  enctype="multipart/form-data">
                                                <div class="mb-0" class="d-flex">
                                                    <form:textarea required="true" path="detaileddescription" class="form-control" id="textAreaExample" rows="1"
                                                                   style="background: #fff;"/>
                                                </div>
                                                <button type="submit" class="btn btn-primary btn-block">Reply to ${reply.fkfindmoteluserId.lastname}</button>
                                            </form:form>
                                        </div>

                                    </c:if>
                                </c:forEach>              
                            </div>

                        </div>
                    </c:if>

                </c:forEach>

            </div>
        </div>
    </div>
</section>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>
<script src='https://cdn.jsdelivr.net/gh/vietblogdao/js/districts.min.js'></script>


<script>//<![CDATA[
                                if (address_2 = localStorage.getItem('address_2_saved')) {
                                    $('select[name="calc_shipping_district"] option').each(function () {
                                        if ($(this).text() == address_2) {
                                            $(this).attr('selected', '')
                                        }
                                    })
                                    $('input.billing_address_2').attr('value', address_2)
                                }
                                if (district = localStorage.getItem('district')) {
                                    $('select[name="calc_shipping_district"]').html(district)
                                    $('select[name="calc_shipping_district"]').on('change', function () {
                                        var target = $(this).children('option:selected')
                                        target.attr('selected', '')
                                        $('select[name="calc_shipping_district"] option').not(target).removeAttr('selected')
                                        address_2 = target.text()
                                        $('input.billing_address_2').attr('value', address_2)
                                        district = $('select[name="calc_shipping_district"]').html()
                                        localStorage.setItem('district', district)
                                        localStorage.setItem('address_2_saved', address_2)
                                    })
                                }


                                $('select[name="calc_shipping_provinces"]').each(function () {
                                    var $this = $(this),
                                            stc = ''
                                    c.forEach(function (i, e) {
                                        e += +1
                                        stc += '<option value=' + e + '>' + i + '</option>'
                                        $this.html('<option value="">Tỉnh / Thành phố</option>' + stc)

                                        $this.on('change', function (i) {
                                            i = $this.children('option:selected').index() - 1
                                            var str = '',
                                                    r = $this.val()
                                            if (r != '') {
                                                arr[i].forEach(function (el) {
                                                    str += '<option value="' + el + '">' + el + '</option>'
                                                    $('select[name="calc_shipping_district"]').html('<option value="">Quận / Huyện</option>' + str)
                                                })
                                                var address_1 = $this.children('option:selected').text()
                                                var district = $('select[name="calc_shipping_district"]').html()
                                                localStorage.setItem('address_1_saved', address_1)
                                                localStorage.setItem('district', district)
                                                $('select[name="calc_shipping_district"]').on('change', function () {
                                                    var target = $(this).children('option:selected')
                                                    target.attr('selected', '')
                                                    $('select[name="calc_shipping_district"] option').not(target).removeAttr('selected')
                                                    var address_2 = target.text()
                                                    $('input.billing_address_2').attr('value', address_2)
                                                    district = $('select[name="calc_shipping_district"]').html()
                                                    localStorage.setItem('district', district)
                                                    localStorage.setItem('address_2_saved', address_2)
                                                })
                                            } else {
                                                $('select[name="calc_shipping_district"]').html('<option value="">Quận / Huyện</option>')
                                                district = $('select[name="calc_shipping_district"]').html()
                                                localStorage.setItem('district', district)
                                                localStorage.removeItem('address_1_saved', address_1)
                                            }

                                            if (address_1 = localStorage.getItem('address_1_saved')) {
                                                $('select[name="calc_shipping_provinces"] option').each(function () {
                                                    if ($(this).text() == address_1) {
                                                        $(this).attr('selected', '')
                                                    }
                                                })
                                                $('input.billing_address_1').attr('value', address_1)
                                            }
                                        })
                                    })
                                })
//]]></script>