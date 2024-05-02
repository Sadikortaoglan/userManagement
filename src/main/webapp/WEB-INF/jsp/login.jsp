<%@page contentType="text/html;encoding=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<spring:htmlEscape defaultHtmlEscape="true" />

<html>
    <tags:header/>
<body>
<c:url var="loginUrl" value='/spring_security_check'/>
<div class="container">
    <div class="card card-shadow col-6 p-100"  >
        <form  action="${loginUrl}" method="post">
            <div class="mb-4 d-flex justify-content-center">
                <i class="bi bi-person-circle zoomUser"></i>
            </div>
            <div class="d-flex justify-content-center mb-3">
                <h4>Lütfen Giriş Yapınız</h4>
            </div>
            <div>
                <input type="text" class="form-control mb-2" name="username" placeholder="Username" required>
                <input type="password" class="form-control mb-2" name="password" placeholder="Password" required>
            </div>
            <hr>
            <div class="d-flex justify-content-center">
                <input type="submit"  class="btn btn-primary"value="Login">
            </div>
        </form>
    </div>
</div>
</body>
</html>
