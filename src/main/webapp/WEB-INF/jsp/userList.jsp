<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html;encoding=UTF-8" pageEncoding="UTF-8" %><%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
    <tags:header/>
</head>
<body>
<div id="table-container">
    <c:choose>
        <c:when test="${not empty userList and userList.size() > 0}">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Adı</th>
                    <th>Soyadı</th>
                    <th>Email</th>
                    <th>Telefon</th>
                    <th>Şifre</th>
                    <th>Doğum Tarihi</th>
                    <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                        <th></th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.userName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.phone}</td>
                        <td>${user.passwd}</td>
                        <td>${user.birthDate}</td>
                        <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                            <th><a class="btn btn-primary" href="${saveUrl}?id=${user.id}">Güncelle</a>
                                <span>&nbsp;</span>
                                <a class="btn btn-secondary" href="#" onclick="return removeUser(${user.id})">Sil</a></th>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p class="error-message">Kullanıcı Bulunamadı...</p>
        </c:otherwise>
    </c:choose>
</div>
</body>