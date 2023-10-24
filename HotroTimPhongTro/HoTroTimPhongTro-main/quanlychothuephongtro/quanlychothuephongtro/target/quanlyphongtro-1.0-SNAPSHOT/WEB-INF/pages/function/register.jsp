<%-- 
    Document   : register
    Created on : Sep 10, 2023, 4:42:02 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>

    .gradient-custom-3 {
        /* fallback for old browsers */
        background: #84fab0;

        /* Chrome 10-25, Safari 5.1-6 */
        background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 0.5), rgba(143, 211, 244, 0.5));

        /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
        background: linear-gradient(to right, rgba(132, 250, 176, 0.5), rgba(143, 211, 244, 0.5))
    }
    .gradient-custom-4 {
        /* fallback for old browsers */
        background: #84fab0;

        /* Chrome 10-25, Safari 5.1-6 */
        background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 1), rgba(143, 211, 244, 1));

        /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
        background: linear-gradient(to right, rgba(132, 250, 176, 1), rgba(143, 211, 244, 1))
    }
</style>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/register" var="action" />

<div class="bg-image" 
     style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
    <div class="mask d-flex align-items-center h-100 gradient-custom-3 ">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                    <div class="card mt-5 mb-5" style="border-radius: 15px;">
                        <div class="card-body p-5">

                            <form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data" onsubmit="return matchPassword()">
                                <form:errors path="*" element="div" cssClass="alert alert-danger" />

                                <form:hidden path="image" />
                                <form:hidden path="id" />
                                <form:hidden path="createdDate" />
                                <form:hidden path="fkuserroleuserId" />

                                <h2 class="text-uppercase text-center mb-1">
                                    <c:choose>
                                        <c:when test="${user.id == null}">Create an account</c:when>
                                        <c:otherwise>Update account</c:otherwise>
                                    </c:choose>
                                </h2>
                                <c:if test="${msgErrShow != ''}">
                                    <p class="justify-content-center" id = "message1" style="color:red;text-align: center;">
                                        ${msgErrShow}<br><br>  
                                    </p>

                                </c:if>
                                <div class="form-floating mb-3 mt-0">
                                    <form:input type="text" id="firstname"  class="form-control" 
                                                path="firstname" placeholder="First Name..." />
                                    <label class="form-label" for="form3Example1cg">First Name</label>
                                </div>
                                <div class="form-floating mb-3 mt-3">
                                    <form:input type="text" id="lastname"  class="form-control" 
                                                path="lastname" placeholder="Last Name..." />
                                    <label class="form-label" for="form3Example1cg">Last Name</label>
                                </div>      
                                <div class="form-floating mb-3 mt-3">
                                    <form:input type="text" id="email"  class="form-control" 
                                                path="email" placeholder="example@gmail.com" />
                                    <label class="form-label" for="form3Example1cg">Email</label>
                                </div>     
                                <c:choose>
                                    <c:when test="${user.id == null}">
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input type="text" class="form-control" 
                                                        path="username" id="username" placeholder="Username..." />
                                            <label for="name">Username</label>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <form:hidden path="username" />
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${user.id == null}">
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input type="password" id="password"  class="form-control" 
                                                        path="password" placeholder="Password..." />
                                            <label class="form-label" for="form3Example1cg">Password</label>
                                        </div>   

                                        <div class="form-floating mb-3 mt-3">
                                            <input type="password" id="passwordconfirm" class="form-control" />
                                            <label class="form-label" for="form3Example4cdg">Repeat your password</label>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <form:hidden path="password" />
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${user.id != null}">
                                        <c:if test="${principalInfo.fkuserroleuserId.name == 'ROLE_ADMIN'}">
                                            <div class="form-floating mb-3 mt-3">
                                                <form:select class="form-select" id="fkuserroleuserId" name="fkuserroleuserId" path="fkuserroleuserId">
                                                    <c:forEach items="${userRole}" var="c">
                                                        <c:choose>
                                                            <c:when test="${c.id == user.fkuserroleuserId.id}">
                                                                <option value="${c.id}" selected>${c.name}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${c.id}">${c.name}</option>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </c:forEach>
                                                </form:select>
                                                <label for="category" class="form-label">ROLE:</label>
                                            </div>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <form:hidden path="fkuserroleuserId" />
                                    </c:otherwise>
                                </c:choose>
                                <div class="form-floating mb-3 mt-3">
                                    <form:input type="file" class="form-control" 
                                                path="file" id="file"  />
                                    <label for="file">Avatar</label>
                                </div> 
                                <div style="margin-left: auto;margin-right:auto;"  
                                     class="form-floating mb-3 mt-3">
                                    <img style="width: 100%;"  id="imgAva" alt=""/>
                                </div>
                                <div class="form-check d-flex ml-3 mb-5">
                                    <input class="form-check-input me-2" required type="checkbox" value="" id="form2Example3cg" />
                                    <label class="form-check-label" for="form2Example3g">
                                        I agree all statements in <a href="#!" class="text-body"><u>Terms of service</u></a>
                                    </label>
                                </div>

                                <div class="d-flex justify-content-center">

                                    <button type="submit" onclick="matchPassword()"
                                            class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">
                                        <c:choose>
                                            <c:when test="${user.id == null}">Register</c:when>
                                            <c:otherwise>Update</c:otherwise>
                                        </c:choose>
                                    </button>
                                </div>

                                <p class="text-center text-muted mt-5 mb-0">Have already an account? 
                                    <a href="#!"class="fw-bold text-body"><u>Login here</u></a>
                                </p>

                            </form:form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    const image = document.getElementById("imgAva");
    const input = document.getElementById("file");
    input.addEventListener('change', (e) => {
        image.src = URL.createObjectURL(e.target.files[0]);
    });
    function matchPassword() {
        // Lấy các giá trị của các trường nhập liệu.
        var fname = document.getElementById("firstname").value;
        var lname = document.getElementById("lastname").value;
        var email = document.getElementById("email").value;
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var passwordconfirm = document.getElementById("passwordconfirm").value;

        // Kiểm tra xem tên có trống hay không.
        if (fname === "") {
            alert("Vui lòng nhập tên của bạn.");
            return false;
        }

        // Kiểm tra xem họ có trống hay không.
        if (lname === "") {
            alert("Vui lòng nhập họ của bạn.");
            return false;
        }

        // Kiểm tra xem email có trống hay không.
        if (email === "") {
            alert("Vui lòng nhập email của bạn.");
            return false;
        }

        // Kiểm tra xem email có hợp lệ hay không.
        var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (!regex.test(email)) {
            alert("Vui lòng nhập email hợp lệ.");
            return false;
        }

        // Kiểm tra xem username có trống hay không.
        if (username === "") {
            alert("Vui lòng nhập tên người dùng của bạn.");
            return false;
        }

        // Kiểm tra xem password có trống hay không.
        if (password === "") {
            alert("Vui lòng nhập mật khẩu của bạn.");
            return false;
        }

        // Kiểm tra xem passwordconfirm có trống hay không.
        if (passwordconfirm === "") {
            alert("Vui lòng nhập lại mật khẩu của bạn.");
            return false;
        }

        // Kiểm tra xem password và passwordconfirm có khớp nhau hay không.
        if (password !== passwordconfirm) {
            alert("Mật khẩu không khớp.");
            return false;
        }



        var pw1 = document.getElementById("password").value;
        var pw2 = document.getElementById("passwordconfirm").value;
        if (pw1 !== pw2)
        {
            alert("Passwords did not match");
            return false;
        }
// Nếu tất cả các điều kiện đều được đáp ứng, thì trả về true để gửi form.
        return true;
    }


</script>  