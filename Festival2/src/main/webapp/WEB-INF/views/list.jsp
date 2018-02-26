<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   	<%
   	request.setCharacterEncoding("utf-8");
   	String list = (String)request.getAttribute("list");
   	%>
   	<%=list %>
