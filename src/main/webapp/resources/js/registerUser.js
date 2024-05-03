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
function deleteUserWithModal(userId) {
    deleteUserId = userId;
    $('#deleteUserModal').modal('show');
}
$('#confirmDeleteBtn').click(function() {
    deleteUser(deleteUserId);
    $('#deleteUserModal').modal('hide');
});
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
        id: $('#userId').val() || null,
        userName: $('#userName').val() || null,
        lastName: $('#lastName').val() || null,
        email: $('#email').val() || null,
        phone: $('#phone').val() || null,
        passwd: $('#passwd').val() || null,
        birthDate: $('#birthDate').val() || null,
        admin: null,
        roles: null
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
function checkUserNameAvailability() {
    var userName = $('#userName').val().trim();
    if (userName.length >= 3) {
        $.ajax({
            type: "GET",
            url: API_URL + "/checkUserNameAvailability?userName=" + userName,
            success: function (response) {
                if (response) {
                    $('#userNameAvailabilityMessage').text("Bu kullanıcı adı zaten kullanılıyor.").removeClass('success').addClass('error');
                } else {
                    $('#userNameAvailabilityMessage').text("Bu kullanıcı adı kullanılabilir.").removeClass('error').addClass('success');
                }
            },
            error: function (xhr, status, error) {
                console.error(error);
                $('#userNameAvailabilityMessage').text("Kullanıcı adı durumu kontrol edilemedi.").removeClass('success').addClass('error');
            }
        });
    } else {
        $('#userNameAvailabilityMessage').text("").removeClass('success').addClass('error');
    }
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
