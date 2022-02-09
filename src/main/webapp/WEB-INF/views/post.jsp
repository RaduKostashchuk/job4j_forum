<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Содержание темы</title>

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
    <div class="m-1">
        <div class="row">
            <div class="col-4">
                <p class="h6 m-1">
                    Просмотр темы:
                    <span class="fw-normal"><c:out value="${post.name}"/></span>
                </p>
                <p class="h6 m-1">
                    Создана:
                    <fmt:parseDate value="${post.created}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both" />
                    <span class="fw-normal">
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedDateTime}" />
                    </span>
                </p>
                <p class="h6 m-1">
                    Автор:
                    <span class="fw-normal"><c:out value="${post.author}"/></span>
                </p>
            </div>
            <div class="col-2 offset-6">
                <a class="btn btn-warning m-3" href="<%=request.getContextPath()%>/deletepost?id=${post.id}">
                    Удалить тему
                </a>
            </div>
        </div>
        <table class="table table-bordered table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">Содержание поста</th>
                <th scope="col">Создан</th>
                <th scope="col">Автор</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${post.comments}" var="comment">
                <tr>
                    <td>
                        <c:out value="${comment.content}"/>
                    </td>
                    <td>
                        <fmt:parseDate value="${comment.created}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both" />
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedDateTime}" />
                    </td>
                    <td>
                        <c:out value="${comment.author}"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row">
        <div class="col-2 offset-10">
            <a class="btn btn-primary m-1" href="<%=request.getContextPath()%>/addcomment?id=${post.id}">
                Оставить сообщение
            </a>
        </div>
    </div>
</div>
</body>
</html>
