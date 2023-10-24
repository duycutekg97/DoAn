<%-- 
    Document   : chart
    Created on : Sep 23, 2023, 12:46:11 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<c:url value="/admin/chart/" var="searchYear" />
<form class="d-flex" action="${searchUser}">
    <select name="Year" class="me-2">
        <option value="">ALL</option>
        <option value="2023">2023</option>
        <option value="2022">2022</option>
        <option value="2021">2021</option>
        <option value="2020">2020</option>
        <option value="2019">2019</option>
        <option value="2018">2018</option>
        <option value="2017">2017</option>
        <option value="2016">2016</option>
        <option value="2015">2015</option>
    </select>
    <select name="roleUser" id="role">
        <option  value=""></option>
        <c:forEach items="${roleList}" var="role">
            <option value="${role.id}">${role.name}</option>
        </c:forEach>
    </select>  
    <button class="btn btn-success ml-2" type="submit">Calc</button>
</form>
<br>
<h5>Number users : ${numberUser}</h5>
<canvas class="mt-5 ml-auto mr-auto justify-content-center" id="myChart" style="width:100%;"></canvas>
<canvas class="mt-5 ml-auto mr-auto justify-content-center" id="quarterChart"  style="width:100%;max-width:600px"></canvas>


<script>
    var xValues = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    var yValues = [${January}, ${February}, ${March}, ${April}, ${May}, ${June}, ${July}, ${August}, ${September}, ${October}, ${November}, ${December}];
    var barColors = [];
    for (const color in xValues) {
        barColors.push('#' + (0x1000000 + Math.random() * 0xffffff).toString(16).substr(1, 6));
    }

    new Chart("myChart", {
        type: "bar",
        data: {
            labels: xValues,
            datasets: [{
                    backgroundColor: barColors,
                    data: yValues
                }]
        },
        options: {
            legend: {display: false},
            title: {
                display: true,
                text: "Number of users during the year"
            }
        }
    });
</script>

<script>
    var xValues = ["Q1", "Q2", "Q3", "Q4"];
    var yValues = [${Q1}, ${Q2}, ${Q3}, ${Q4}];
    var barColors = [];
    for (const color in xValues) {
        barColors.push('#' + (0x1000000 + Math.random() * 0xffffff).toString(16).substr(1, 6));
    }
    new Chart("quarterChart", {
        type: "pie",
        data: {
            labels: xValues,
            datasets: [{
                    backgroundColor: barColors,
                    data: yValues
                }]
        },
        options: {
            title: {
                display: true,
                text: "Number of users in quarter"
            }
        }
    });
</script>