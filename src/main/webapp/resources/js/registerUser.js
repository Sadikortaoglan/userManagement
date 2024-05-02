const BASE_URL = location.protocol + "//" + location.host;
const API_URL = BASE_URL + "/user";

const REMOVE_URL = API_URL + "/deleteUser/";
function deleteUser(userId) {
    $.ajax({
        method: "POST",
        url: REMOVE_URL + userId,
        contentType: 'application/json',
        cache: false,
        async: false,
        success: function (response) {
            $('#successModal').modal('show');
            setTimeout(function() {
                location.reload();
            }, 3000);
        },
        error: function (result) {
            console.log(result.message);
        }
    });
    return false;
}
function populateUpdateModal(userId) {
    $.ajax({
        method: "GET",
        url: API_URL + "/getUserById/" + userId,
        success: function (response) {
            $('#updateModal').modal('show');
            $('#userName').val(response.userName);
            $('#lastName').val(response.lastName);
            $('#email').val(response.email);
            $('#phone').val(response.phone);
            $('#passwd').val(response.passwd);
            $('#birthDate').val(response.birthDate);
        },
        error: function (result) {
            console.log(result.message);
        }
    });
}
function submitUpdateForm() {
    var formData = {
        id: $('#userId').val(),
        userName: $('#userName').val(),
        lastName: $('#lastName').val(),
        email: $('#email').val(),
        phone: $('#phone').val(),
        passwd: $('#passwd').val(),
        birthDate: $('#birthDate').val()
    };
    $.ajax({
        type: "POST",
        url: API_URL +"/registerUser",
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function(response) {
            $('#updateModal').modal('hide');
            location.reload();
        },
        error: function(xhr, status, error) {
            console.error(error);
            alert("Güncelleme işlemi sırasında bir hata oluştu.");
        }
    });
}

$(document).ready(function() {
    $(document).on('click', '[id^="updateBtn"]', function() {
        var target = $(this).data('target');
        $(target).modal('show');
    });
    var currentURL = window.location.href;
    console.log(currentURL.split("/"))
    if (currentURL.split("/")[3] === "login" || currentURL.split("/")[3] === "login?logout=true"){
        $("#header-link").hide();
    } else {
        $("#header-link").show();
    }
});
