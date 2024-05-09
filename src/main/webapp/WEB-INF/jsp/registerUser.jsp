<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html;encoding=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<body>
<c:if test="${error}">
    <tags:error/>
</c:if>
<section>
    <tags:registerUser/>
</section>
</body>
<tags:footer/>
</html>