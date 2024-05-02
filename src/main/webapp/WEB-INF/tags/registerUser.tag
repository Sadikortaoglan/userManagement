<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ tag description="Register User Form" pageEncoding="UTF-8"%>

<%@ attribute name="userName" type="java.lang.String" description="User Name" %>
<%@ attribute name="lastName" type="java.lang.String" description="Last Name" %>
<%@ attribute name="email" type="java.lang.String" description="Email" %>
<%@ attribute name="phone"  type="java.lang.String" description="Phone" %>
<%@ attribute name="passwd"  type="java.lang.String" description="Password" %>
<%@ attribute name="birthDate"  type="java.lang.String" description="Birth Date" %>
<tags:header/>
<c:set var="registerUrl" value="/user/registerUser" />
<div class="container">
    <form id="registerForm" method="post" name="userForm" action="${registerUrl}">
        <label for="userName">User Name:</label>
        <input type="text" id="userName" name="userName" value="${userName}" placeholder="User Name" required>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="${lastName}" placeholder="Last Name" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${email}" placeholder="Email" required>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="${phone}" placeholder="Phone" required>

        <label for="passwd">Password:</label>
        <input type="password" id="passwd" name="passwd" value="${passwd}" placeholder="Password" required>

        <label for="birthDate">Birth Date:</label>
        <input type="text" id="birthDate" name="birthDate" value="${birthDate}" placeholder="Birth Date" required>

        <button type="submit" class="btn btn-primary" id="submit-btn">Kaydet</button>

    </form>
</div>


