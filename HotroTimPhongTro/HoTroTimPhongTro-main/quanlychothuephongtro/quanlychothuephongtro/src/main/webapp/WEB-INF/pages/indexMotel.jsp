<%-- 
    Document   : indexMotel
    Created on : Sep 13, 2023, 1:28:02 PM
    Author     : HOME
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if  test="${principalInfo.fkuserroleuserId.name eq 'ROLE_HOST' or principalInfo.fkuserroleuserId.name eq 'ROLE_ADMIN'}">
    <a href="<c:url value="/host/motels" />" class="btn btn-info mb-2">Add</a>
</c:if>
<c:url value="/index/Motel/" var="searchMotel" />
<form action="${searchMotel}">
    <div class="row mb-2">
        <div class="col-12 mb-2">
            <input autocomplete="off"  width="48" height="48" class="form-control me-2" type="text" name="Address" placeholder="Search Address...">
        </div>
        <div class="row">
            <div class="col-12 col-md-6 mb-2">
                <div class="form-outline">
                    <select name="calc_shipping_provinces" required="" class="form-select">
                        <option value="">Tỉnh / Thành phố</option>
                    </select>
                    <label class="form-label" for="fkmotelwardId">City</label>
                </div>
            </div>
            <div class="col-12 col-md-6 mb-2">
                <div class="form-outline">
                    <select name="calc_shipping_district" required="" class="form-select">
                        <option value="">Quận / Huyện</option>
                    </select>
                    <label class="form-label" for="fkmotelwardId">District</label>
                </div>
            </div>
            <input autocomplete="off" class="billing_address_1" name="City2" type="hidden"  value=""/>
            <input autocomplete="off" class="billing_address_2" name="District2" type="hidden" value=""/>
        </div>
    </div>
    <div class="row mb-2">
        <div class="col-0">Price:</div>
        <div class="col-6">
            <input autocomplete="off" width="48" height="48" class="form-control me-2" type="number" name="fromPrice" placeholder="Search fromPrice...">
        </div>
        <div class="col-6">
            <input autocomplete="off" width="48" height="48" class="form-control me-2" type="number" name="toPrice" placeholder="Search toPrice...">
        </div>
        <div class="col-0">Number <i class="fa fa-user"></i></div>
        <div class="col-4">
            <input autocomplete="off" width="48" height="48" class="form-control me-2" type="number" name="numberOfResidents" placeholder="Search numberOfResidents...">
        </div>
    </div>
    <button class="btn btn-primary ml-2" type="submit">Search</button>
</form>
<br>
<c:if test="${counter > 1}">
    <ul class="pagination mt-1">
        <li class="page-item"><a class="page-link" href="<c:url value="/index/Motel/" />">All</a></li>
            <c:forEach begin="1" end="${counter}" var="i">
                <c:url value="/index/Motel/" var="pageUrl">
                    <c:param name="page" value="${i}"></c:param>
                </c:url>
            <li class="page-item"><a class="page-link" onclick="reload()" href="${pageUrl}">${i}</a></li>
            </c:forEach>
    </ul>
</c:if>
<c:forEach items="${listMotels}" var="motel">
    <c:choose>
        <c:when test="${pageContext.request.userPrincipal.name eq null or principalInfo.fkuserroleuserId.name eq 'ROLE_ADMIN' 
                        or principalInfo.fkuserroleuserId.name eq 'ROLE_RENTER'}">
            <c:if test="${motel.apply eq true}">
                <c:if test="${principalInfo.fkuserroleuserId.name eq 'ROLE_ADMIN'}">
                    <a href="<c:url value="/host/motels/${motel.id}" />" class="btn btn-success">Update</a>
                    <c:url value="/host/motels/${motel.id}/delete" var="Del" />
                    <button class="btn btn-danger" onclick="delUser('${Del}', ${motel.id})">Delete</button>

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
        </c:when>
        <c:otherwise>
            <c:if test="${principalInfo.fkuserroleuserId.name eq 'ROLE_HOST' and motel.fkmoteluserId.id eq principalInfo.id}">
                <a href="<c:url value="/host/motels/${motel.id}" />" class="btn btn-success">Update</a>
                <c:url value="/host/motels/${motel.id}/delete" var="Del" />
                <button class="btn btn-danger" onclick="delUser('${Del}', ${motel.id})">Delete</button>
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
                            <div class="col-7 p-3 bg-white text-black">
                                <div class = "row"> 
                                    Own ${motel.fkmoteluserId.lastname}
                                </div>
                                <div class = "row"> 
                                    Time : ${motel.createdDate}
                                </div>
                            </div>
                            <div class="col-4 p-3 bg-white text-black">
                                <span style="border: 1px solid; padding:5px">
                                    <i class="fa fa-phone"></i>
                                    <span>  ${motel.phone}</span>
                                </span>
                            </div>
                        </div>
                    </div>
                </a>
            </c:if>
        </c:otherwise>
    </c:choose>

</c:forEach>
<script src="<c:url value="/assets/js/main_1.js" />"></script>
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
