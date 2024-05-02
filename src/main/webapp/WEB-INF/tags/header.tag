<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ tag pageEncoding="UTF-8" %>
<html>
<header class="navbar">
    <div class="logo">User Management</div>
    <nav>
        <sec:authorize access="hasAuthority('ADMIN_ROLE')">
            <ul id="header-link">
                <li><a href="<c:url value='user/registerUser' />">Kullanıcı Ekle</a></li>
                <li><a href="<c:url value='/user' />">Kullanıcılar</a></li>
                <li><a href="<c:url value='/logout' />">Çıkış Yap</a></li>
            </ul>
        </sec:authorize>

    </nav>
</header>
    <tags:css />
    <tags:js />
</html>
