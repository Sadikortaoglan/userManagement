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
<div class="login-container">
    <form action="${loginUrl}" method="post">
        <input type="text" name="username" placeholder="Username" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="submit" value="Login">
    </form>
    <!-- Error message can be displayed here if login fails -->
    <!-- <div class="error-message">Invalid username or password.</div> -->
</div>
</body>
</html>
