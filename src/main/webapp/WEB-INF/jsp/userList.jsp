<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html;encoding=UTF-8" pageEncoding="UTF-8" %><%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="updateUrl" value="/user/registerUser" />
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
                    <th hidden="">id</th>
                    <th>Adı</th>
                    <th>Soyadı</th>
                    <th>Email</th>
                    <th>Telefon</th>
                    <th>Doğum Tarihi</th>
                    <sec:authorize access="hasAuthority('ADMIN_ROLE')">
                        <th></th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td hidden="">${user.id}</td>
                        <td>${user.userName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.phone}</td>
                        <td>${user.birthDate}</td>
                        <sec:authorize access="hasAuthority('ADMIN_ROLE')">
                            <th>
                                <a class="btn btn-primary" href="#" id="updateBtn${user.id}" data-toggle="modal" data-target="#updateModal${user.id}" onclick="populateUpdateModal(${user.id})">Güncelle</a>
                                <span>&nbsp;</span>
                                <a class="btn btn-secondary" href="#" onclick="return deleteUser(${user.id})">Sil</a>
                            </th>
                        </sec:authorize>
                    </tr>
                    <div class="modal fade" id="updateModal${user.id}" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel${user.id}" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="updateModalLabel${user.id}">Kullanıcı Güncelleme</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form id="updateForm">
                                        <div class="form-group">
                                            <input type="hidden" id="userId" name="userId" value="${user.id}" required>
                                            <label for="userName">User Name:</label>
                                            <input type="text" class="form-control" id="userName" name="userName" value="${userName}" placeholder="User Name" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="lastName">Last Name:</label>
                                            <input type="text" class="form-control" id="lastName" name="lastName" value="${lastName}" placeholder="Last Name" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="email">Email:</label>
                                            <input type="email" class="form-control" id="email" name="email" value="${email}" placeholder="Email" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="phone">Phone:</label>
                                            <input type="text" class="form-control" id="phone" name="phone" value="${phone}" placeholder="Phone" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="passwd">Password:</label>
                                            <input type="password" class="form-control" id="passwd" name="passwd" value="${passwd}" placeholder="Password" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="birthDate">Birth Date:</label>
                                            <input type="text" class="form-control" id="birthDate" name="birthDate" value="${birthDate}" placeholder="Birth Date" required>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary" onclick="submitUpdateForm()">Save</button>
                                </div>
                            </div>
                        </div>
                    </div>
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
<div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="successModalLabel">Başarılı!</h5>
            </div>
            <div class="modal-body">
                Kayıt başarıyla silindi.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Kapat</button>
            </div>
        </div>
    </div>
</div>