<%-- 
    Document   : addMotel
    Created on : Sep 14, 2023, 11:07:11 AM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/host/motels" var="action" />

<section class="intro">
    <div class="h-100">
        <div class="mask d-flex align-items-center h-100">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12 col-lg-10">
                        <div class="card" style="border-radius: 1rem;">
                            <div class="card-body p-5">


                                <form:form  method="post" action="${action}" modelAttribute="motelAdd" enctype="multipart/form-data">
                                    <form:errors path="*" element="div" cssClass="alert alert-danger" />
                                    <form:hidden path="id" />
                                    <form:hidden path="createdDate" />

                                    <c:choose>
                                        <c:when test="${motelAdd.id == null}">
                                            <h1 class="text-center text-black mt-1 mb-5">ADD MOTEL</h1>
                                        </c:when>
                                        <c:otherwise>
                                            <h1 class="text-center text-black mt-1 mb-5">UPDATE MOTEL</h1>
                                        </c:otherwise>
                                    </c:choose>

                                    <p class="justify-content-center" id = "message1" style="color:red;text-align: center;">
                                        ${msgErrShow}<br><br>  
                                    </p>

                                    <!-- 2 column grid layout with text inputs for the first and last names -->
                                    <div class="row">
                                        <div class="col-12 col-md-6 mb-4">
                                            <div class="form-outline">
                                                <form:input autocomplete="off" path="name" type="text" id="name" class="form-control" />
                                                <label class="form-label" for="name">Motel name</label>
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-6 mb-4">
                                            <div class="form-outline">
                                                <form:input autocomplete="off" path="title" type="text" id="title" class="form-control" />
                                                <label class="form-label" for="title">Title</label>
                                            </div>
                                        </div>  
                                        <div class="col-12 col-md-2 mb-4">
                                            <div class="form-outline">
                                                <form:input autocomplete="off" path="acreage" type="number" id="acreage" class="form-control" />
                                                <label class="form-label" for="acreage">Acreage</label>
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-2 mb-4">
                                            <div class="form-outline">
                                                <form:input autocomplete="off" path="numberofresidents" type="number" id="numberofresidents" class="form-control" />
                                                <label class="form-label" for="numberofresidents">Number <i class="fa fa-user"></i></label>
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-4 mb-4">
                                            <div class="form-outline">
                                                <form:input path="price" type="number" id="price" class="form-control" />
                                                <label class="form-label" for="price">Price</label>
                                            </div>
                                        </div>   
                                        <div class="col-12 col-md-4 mb-4">
                                            <div class="form-outline">
                                                <form:input autocomplete="off" path="phone" type="number" id="phone" class="form-control" />
                                                <label class="form-label" for="phone">Phone</label>
                                            </div>
                                        </div>        
                                    </div>

                                    <!-- Text input -->
                                    <div class="form-outline mb-4">
                                        <form:input autocomplete="off" path="address" type="text" id="address" class="form-control" 
                                                    />
                                        <label class="form-label" for="address">Address</label>
                                    </div>
                                    <div class="row">
                                        <div class="col-12 col-md-6 mb-4">
                                            <div class="form-outline">
                                                <select name="calc_shipping_provinces" required="" class="form-select">
                                                    <option value="">Tỉnh / Thành phố</option>
                                                </select>
                                                <label class="form-label" for="fkmotelwardId">City</label>
                                            </div>
                                        </div>
                                        <div class="col-12 col-md-6 mb-4">
                                            <div class="form-outline">
                                                <select name="calc_shipping_district" required="" class="form-select">
                                                    <option value="">Quận / Huyện</option>
                                                </select>
                                                <label class="form-label" for="fkmotelwardId">District</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-outline mb-4">
                                        <form:input autocomplete="off" path="cityprovince" type="hidden" id="cityprovince" class="billing_address_1" 
                                                    name="" value=""/>
                                    </div>
                                    <div class="form-outline mb-4">
                                        <form:input autocomplete="off" path="district" type="hidden" id="district" class="billing_address_2" 
                                                    name="" value=""/>
                                    </div> 
                                    <!-- Text input -->

                                    <!-- Text input -->
                                    <div class="form-outline mb-4">
                                        <form:input autocomplete="off" path="map" type="text" id="map" class="form-control"
                                                    placeholder="example: <iframe src='https://www.google.com/maps/embed?pb='></iframe>" />
                                        <label class="form-label" for="map">Map</label>
                                    </div>

                                    <!-- Message input -->
                                    <div class="form-outline mb-4">
                                        <form:textarea path="description" class="form-control" id="description" rows="4"/>
                                        <label class="form-label" for="description">Description</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <form:input type="file" class="form-control" 
                                                    path="file" id="images" multiple="multiple" />
                                        <label class="form-label" for="images">Image</label>
                                    </div>
                                    <div class="form-outline mb-4" id="image-previews">
                                    </div>

                                    <!-- Submit button -->
                                    <button type="submit" class="btn btn-secondary btn-roundded btn-block">
                                        <c:choose>
                                            <c:when test="${motelAdd.id == null}">ADD</c:when>
                                            <c:otherwise>UPDATE</c:otherwise>
                                        </c:choose>
                                    </button>
                                </form:form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script>
    function previewImages() {
        var images = document.getElementById("images");
        var imagePreviews = document.getElementById("image-previews");

        images.addEventListener("change", function () {
            imagePreviews.innerHTML = "";
            for (var i = 0; i < images.files.length; i++) {
                if (images.files[i] !== null) {
                    var url = URL.createObjectURL(images.files[i]);
                    var imagePreview = document.createElement("img");
                    imagePreview.src = url;
                    imagePreview.width = 200;
                    imagePreviews.appendChild(imagePreview);
                }
            }
        });
    }

    document.addEventListener("DOMContentLoaded", previewImages);
</script>
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