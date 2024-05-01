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
<head>
    <style>
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }

        input {
            margin: 5px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 300px;
        }

        button {
            margin-top: 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
    <script src="../js/registerUser.js"></script>
</head>
<div class="container">
    <form id="registerForm">
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

        <button type="button" onclick="registerUser()">Register</button>
    </form>
</div>


