function registerUser() {
    var formData = $("#registerForm").serialize();

    $.ajax({
    type: "POST",
    url: "/registerUser",
    data: formData,
    success: function(response) {
        console.log("Success:", response);
    },
    error: function(xhr, status, error) {
        console.error("Error:", error);
    }
    });
}