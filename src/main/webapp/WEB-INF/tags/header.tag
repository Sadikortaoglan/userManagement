<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ tag pageEncoding="UTF-8" %>
<html>
<header class="navbar">
    <div class="logo">User Management</div>
    <nav>
        <sec:authorize access="hasAuthority('ADMIN_ROLE')">
            <ul>
                <li><a href="<c:url value='/newuser' />">Add New User</a></li>
            </ul>
        </sec:authorize>

    </nav>
</header>
    <tags:css />
    <tags:js />
</html>
