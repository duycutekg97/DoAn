<%-- 
    Document   : base
    Created on : Jul 15, 2023, 11:23:05 AM
    Author     : HOME
--%>

<%@ taglib prefix="tiles"
           uri="http://tiles.apache.org/tags-tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title><tiles:insertAttribute name="title" /></title>
        <tiles:insertAttribute name="stylesheet" />
    </head>
    <body>

        <div class="wrapper d-flex align-items-stretch">
            <tiles:insertAttribute name="sidebar_left" />
            <div id="content" class="p-4 p-md-5">
                <section class="container">
                    <tiles:insertAttribute name="header" />
                    <tiles:insertAttribute name="content" />
                    <tiles:insertAttribute name="footer" />
                </section>
            </div>

            <tiles:insertAttribute name="javascripts" />
        </div>
    </body>
</html>
