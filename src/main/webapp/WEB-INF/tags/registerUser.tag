<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ tag description="Register User Form" pageEncoding="UTF-8"%>

<%@ attribute name="userName" type="java.lang.String" description="User Name" %>
<%@ attribute name="lastName" type="java.lang.String" description="Last Name" %>
<%@ attribute name="email" type="java.lang.String" description="Email" %>
<%@ attribute name="phone"  type="java.lang.String" description="Phone" %>
<%@ attribute name="passwd"  type="java.lang.String" description="Password" %>
<%@ attribute name="birthDate"  type="java.lang.String" description="Birth Date" %>

<form id="registerForm">
    <input type="text" name="userName" value="${userName}" placeholder="User Name" required>
    <input type="text" name="lastName" value="${lastName}" placeholder="Last Name" required>
    <input type="email" name="email" value="${email}" placeholder="Email" required>
    <input type="text" name="phone" value="${phone}" placeholder="Phone" required>
    <input type="password" name="passwd" value="${passwd}" placeholder="Password" required>
    <input type="text" name="birthDate" value="${birthDate}" placeholder="Birth Date" required>
    <button type="button" onclick="registerUser()">Register</button>
</form>
