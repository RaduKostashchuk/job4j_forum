<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Содержание нового сообщения</title>

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
        <p class="h6 m-1">
                Новое сообщение для темы:
            <span class="fw-normal"><c:out value="${post.name}"/></span>
        </p>
    </div>
    <div class="m-1">
        <form  action="<%=request.getContextPath()%>/addcomment" method="post">
            <div class="row m-1">
                <div class="col-6">
                    <textarea class="form-control m-1" name="content"
                              style="height: 150px" placeholder="Новое сообщение"></textarea>
                </div>
                <input type="hidden" name="postId" value="<c:out value='${post.id}'/>" />
            </div>
            <div class="row m-1">
                <div class="col-2">
                    <input type="submit" value="Сохранить" />
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
