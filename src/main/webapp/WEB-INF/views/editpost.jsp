<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit post</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <div class="row border m-1">
        <div class="col-4">
            <p class="h6 m-1">
                Edit post:
                <span class="fw-normal"><c:out value="${post.name}"/></span>
            </p>
            <p class="h6 m-1">
                Created:
                <fmt:parseDate value="${post.created}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both" />
                <span class="fw-normal">
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedDateTime}" />
                </span>
            </p>
            <p class="h6 m-1">
                Author:
                <span class="fw-normal"><c:out value="${post.author}"/></span>
            </p>
        </div>
        <div class="col-2 offset-6">
            <a class="btn btn-warning m-3" href="<%=request.getContextPath()%>/post/delete?postId=${post.id}">
                Delete
            </a>
        </div>
    </div>
    <div class="m-1">
        <c:if test="${not empty successMessage}">
            <div class="alert alert-info" role="alert">
                <c:out value="${successMessage}"/>
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-warning" role="alert">
                <c:out value="${errorMessage}"/>
            </div>
        </c:if>
    </div>
    <div class="row border m-1">
        <form  action="<%=request.getContextPath()%>/post/edit" method="post">
            <p class="h6">Edit name</p>
            <div class="row m-1">
                <div class="col-4">
                    <input type='text' name="name"/>
                </div>
                <div class="col-2">
                    <input name="submit" class="btn btn-primary m-1" type="submit" value="Save" />
                </div>
                <input type="hidden" name="postId" value="<c:out value='${post.id}'/>"/>
            </div>
        </form>
    </div>
    <div class="row m-1">
        <table class="table table-bordered table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">Comment</th>
                <th scope="col">Created</th>
                <th scope="col">Author</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${post.comments}" var="comment">
                <tr>
                    <td>
                        <p class="m-1">
                        <c:out value="${comment.content}"/>
                        </p>
                    </td>
                    <td>
                        <p class="m-1">
                        <fmt:parseDate value="${comment.created}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both" />
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedDateTime}" />
                        </p>
                    </td>
                    <td>
                        <p class="m-1">
                        <c:out value="${comment.author}"/>
                        </p>
                    </td>
                    <td>
                        <a class="btn btn-primary m-1"
                           href="<%=request.getContextPath()%>/comment/edit?postId=${post.id}&commentId=${comment.id}">
                            Edit
                        </a>
                        <a class="btn btn-primary m-1"
                           href="<%=request.getContextPath()%>/comment/delete?postId=${post.id}&commentId=${comment.id}">
                            Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
