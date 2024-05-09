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
        contentType: 'application/json',
        success: function (response) {
            $('#updateModal').modal('show');
            $('#userId').val(response.id);
            $('#userName').val(response.userName);
            $('#name').val(response.name);
            $('#lastName').val(response.lastName);
            $('#email').val(response.email);
            $('#phone').val(response.phone);
            $('#passwd').val(response.passwd);
            $('#isAdmin').prop("checked",response.admin)
            $('#birthDate').val(getFormattedDate(response.birthDate));
        },
        error: function (result) {
            console.log(result.message);
        }
    });
}
function getFormattedDate(timestamp) {
    var date = new Date(timestamp);
    var dateMonth = date.getMonth() + 1;
    var dateDay = date.getDate();
    if (dateMonth < 10) {
        dateMonth = '0' + dateMonth.toString();
    }
    if (dateDay < 10) {
        dateDay = '0' + dateDay.toString();
    }
    return  dateDay + "/" + dateMonth + "/" + date.getFullYear()
}
function submitUpdateForm() {
    if (!validateForm()) {
        return;
    }
    var formData = {
        id: $('#userId').val() || null,
        userName: $('#userName').val() || null,
        name: $('#name').val() || null,
        lastName: $('#lastName').val() || null,
        email: $('#email').val() || null,
        phone: $('#phone').val() || null,
        passwd: $('#passwd').val() || null,
        birthDate: $('#birthDate').val() || null,
        admin: $('#isAdmin').prop("checked"),
    };
    $.ajax({
        type: "PUT",
        url: API_URL +"/updateUser",
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function(response) {
            $('#updateModal').modal('hide');
            $('#updateUserModal').modal('show');
            setTimeout(function() {
                location.reload();

            }, 1000);
        },
        error: function(xhr, status, error) {
            console.error(error);
            alert("Güncelleme işlemi sırasında bir hata oluştu.");
        }
    });
}
function checkUserNameAvailability() {
    var userName = $('#userName').val().trim();
    if (userName.length >= 3 && !userName.includes(' ')) {
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

    $('td[id^="birthDateId"]').each(function() {
        var birthDateText = $(this).text();
        var dateParts = birthDateText.split(" ")[0].split("-");
        var year = dateParts[0];
        var month = dateParts[1];
        var day = dateParts[2];
        var formattedDate = day + "/" + month + "/" + year;
        $(this).text(formattedDate);
    });


    $("#birthDate").datepicker({dateFormat: "dd/MM/yyyy"});
    var currentURL = window.location.href;
    console.log(currentURL.split("/"))
    if (currentURL.split("/")[3] === "login" || currentURL.split("/")[3] === "login?logout=true"){
        $("#header-link").hide();
    } else {
        $("#header-link").show();
    }

    if (hasError) {
        $('#errorModal').modal('show');
        setTimeout(function() {
            $('#errorModal').modal('hide');
        }, 4000);
    }
});