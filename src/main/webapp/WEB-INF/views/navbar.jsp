<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="user" scope="session" value="<%=request.getUserPrincipal()%>" />
<nav class="navbar navbar-expand-lg navbar-light bg-light m-1">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Welcome to our forum</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/">Index</a>
                </li>
                <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/post/add">Create post</a>
                </li>
                </c:if>
                <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login">Login</a>
                </li>
                </c:if>
                <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout">
                        <c:out value="${user.getName()}"/> | Logout</a>
                </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
