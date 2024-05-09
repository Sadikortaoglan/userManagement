<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ tag description="Register User Form" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ attribute name="userName" type="java.lang.String" description="User Name" %>
<%@ attribute name="lastName" type="java.lang.String" description="Last Name" %>
<%@ attribute name="email" type="java.lang.String" description="Email" %>
<%@ attribute name="phone" type="java.lang.String" description="Phone" %>
<%@ attribute name="passwd" type="java.lang.String" description="Password" %>
<%@ attribute name="birthDate" type="java.util.Date" description="Birth Date" %>
<tags:header/>
<c:set var="registerUrl" value="/user/registerUser"/>
<c:set var="userList" value="/user"/>
<div class="container card card-shadow mt-5 p-5 w-50" >
    <form id="registerForm" method="post" name="registerForm" action="${registerUrl}">
        <div class="d-flex gap-2 col-12 mb-2">
            <div class="col-6">
                <div>
                    <label for="userName">User Name</label>
                </div>
                <div><input class="form-control" type="text" id="userName" name="userName" value="${userName}" placeholder="User Name" oninput="checkUserNameAvailability()"
                            required></div>
                <div id="userNameAvailabilityMessage" class="message"></div>
                <div id="userNameError" class="text-danger"></div>
            </div>
            <div class="col-6">
                <div>
                    <label for="name">Name</label>
                </div>
                <div>
                    <input class="form-control" type="text" id="name" name="name" value="${name}" placeholder="Name" required>
                    <div id="nameError" class="text-danger"></div>
                </div>
            </div>

        </div>
        <div class="d-flex gap-2 col-12 mb-2">
            <div class="col-6">
                <div>
                    <label for="lastName">Last Name</label>
                </div>
                <div>
                    <input class="form-control" type="text" id="lastName" name="lastName" value="${lastName}" placeholder="Last Name" required>
                    <div id="lastNameError" class="text-danger"></div>
                </div>
            </div>

            <div class="col-6">
                <div>
                    <label for="phone">Phone</label>
                </div>
                <div>
                    <input class="form-control" type="text" id="phone" name="phone" value="${phone}" placeholder="Phone" required>
                    <div id="phoneError" class="text-danger"></div>
                </div>
            </div>
        </div>
        <div class="d-flex gap-2 col-12 mb-2">
            <div class="col-6">
                <div>
                    <label for="email">Email</label>
                </div>
                <div><input class="form-control" type="email" id="email" name="email" value="${email}" placeholder="Email" required>
                    <div id="emailError" class="text-danger"></div>
                </div>
            </div>
            <div class="col-6">
                <div>
                    <label for="passwd">Password</label>
                </div>
                <div>
                    <input class="form-control" type="password" id="passwd" name="passwd" value="${passwd}" placeholder="Password" required>
                    <div id="passwdError" class="text-danger"></div>
                </div>
            </div>

        </div>
        <div class="d-flex gap-2 col-12 mb-3">
            <div class="col-6">
                <div>
                    <label for="birthDate">Birth Date</label>
                </div>
                <div class="form-date">
                    <div class="input-group date" id="datepicker">
                        <input type="text" class="form-control" id="birthDate" name="birthDate" value="${birthDate}" />
                        <span class="input-group-append">
                            <span class="input-group-text bg-light d-block">
                                <i class="fa fa-calendar"></i>
                            </span>
                        </span>
                    </div>
                </div>
            </div>
            <div class="col-6 p-4 ml-x15">
                <label for="isAdmin">Admin</label>
                <input id="isAdmin" type="checkbox">
            </div>
        </div>

<div class="d-flex justify-content-end">
    <a href="${userList}" class="btn btn-danger btn-lg" role="button">İptal</a>&nbsp;
    <button type="submit" class="btn btn-primary" id="submit-btn">Kaydet</button>
</div>
    </form>
</div>