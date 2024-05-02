<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ tag pageEncoding="UTF-8" %>
<html>
<header class="navbar p-4 "id="header-link">
    <div class="logo">User Management</div>
    <nav>
        <sec:authorize access="hasAuthority('ADMIN_ROLE')">

            <div  class="btn-group " style="margin-right: 75px;">
                <button type="button" class="btn btn-light dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    İşlemler
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="<c:url value='user/registerUser' />">
                        <div class="d-flex gap-2 align-items-center">
                        <div>
                            <i class="bi bi-person-add mr-1"></i>
                        </div>
                     <div>
                         Kullanıcı Ekle
                     </div>
                        </div>
                    </a></li>
                    <li><a class="dropdown-item"href="<c:url value='/user' />">
                        <div class="d-flex gap-2 align-items-center">
                            <div>
                                <i class="bi bi-card-list mr-1"></i>
                            </div>
                            <div>
                                Kullanıcılar
                            </div>
                        </div></a></li>
                    <li><hr class="dropdown-divider"></li>

                    <li><a class="dropdown-item" href="<c:url value='/logout' />">

                        <div class="d-flex gap-2 align-items-center">
                            <div>
                                <i class="bi bi-x mr-1"></i>
                            </div>
                            <div>
                                Çıkış Yap
                            </div>
                            </div>
                    </a></li>
                </ul>
            </div>
        </sec:authorize>
    </nav>
</header>
    <tags:css />
    <tags:js />
</html>
