<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ tag isELIgnored="false" %>
<script>
    var hasError = ${error};
</script>
<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="errorModalLabel">Hata Var!</h5>
            </div>
            <div class="modal-body" id="error">
                Lütfen bütün alanları tekrar kontrol ediniz.
            </div>
        </div>
    </div>
</div>