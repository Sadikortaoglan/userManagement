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
<div class="card p-3 m-5 card-shadow" >
    <div id="table-container">
        <c:choose>
            <c:when test="${not empty userList and userList.size() > 0}">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>User Id</th>
                        <th>User Name</th>
                        <th>Adı</th>
                        <th>Soyadı</th>
                        <th>Email</th>
                        <th>Telefon</th>
                        <th>Doğum Tarihi</th>
                        <sec:authorize access="hasAnyAuthority('ADMIN_ROLE')">
                            <th>İşlemler</th>
                            <th></th>
                        </sec:authorize>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userList}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.userName}</td>
                            <td>${user.name}</td>
                            <td>${user.lastName}</td>
                            <td>${user.email}</td>
                            <td>${user.phone}</td>
                            <td id="birthDateId">${user.birthDate }</td>
                            <sec:authorize access="hasAnyAuthority('ADMIN_ROLE')">
                                <th>
                                    <a class="btn btn-outline-primary" href="#" id="updateBtn${user.id}" data-toggle="modal" data-target="#updateModal" onclick="populateUpdateModal(${user.id})"><i class="bi bi-pencil"></i></a>
                                    <span>&nbsp;</span>
                                    <a class="btn btn-outline-danger" href="#" onclick="deleteUserWithModal(${user.id})"><i class="bi bi-trash"></i></a>
                                </th>
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
</div>
<div style="max-height: 250px"><tags:footer/></div>
</body>
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateModalLabel">Kullanıcı Güncelleme</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="updateForm">
                    <div class="form-group">
                        <label for="userId">User ID:</label>
                        <input type="text" class="form-control" id="userId" name="userId" placeholder="User ID" required disabled>
                     </div>
                    <div class="form-group">
                        <label for="userName">User Name:</label>
                        <input type="text" class="form-control" id="userName" name="userName" placeholder="User Name" required>
                    </div>
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" name="lastName" placeholder="Name" required>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName"  placeholder="Last Name" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input type="text" class="form-control" id="phone" name="phone" placeholder="Phone" required>
                    </div>
                    <div class="form-group">
                        <label for="passwd">Password</label>
                        <input type="password" class="form-control" id="passwd" name="passwd" placeholder="Password" required>
                    </div>
                    <div class="d-flex w-100 ">
                        <div class="form-group col-9">
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
                        <div class="col-3 p-4 ml-x15">
                            <sec:authorize access="hasAnyAuthority('ADMIN_ROLE')">
                                <label for="isAdmin">Admin</label>
                                <input id="isAdmin" type="checkbox">
                            </sec:authorize>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitUpdateForm()">Save</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="successModalLabel">Başarılı!</h5>
            </div>
            <div class="modal-body">
                Kayıt başarıyla silindi.
            </div>
        </div>
    </div>
</div>
<!-- Delete User Modal -->
<div class="modal fade" id="deleteUserModal" tabindex="-1" role="dialog" aria-labelledby="deleteUserModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Kullanıcıyı Sil</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Bu kullanıcıyı silmek istediğinizden emin misiniz?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">İptal</button>
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="confirmDeleteBtn" onclick="deleteUser(deleteUserId)">Sil</button>
            </div>
        </div>
    </div>
</div>