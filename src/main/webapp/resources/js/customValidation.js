$(document).ready(function() {
    $('#registerForm').submit(function(event) {
        var valid = true;

        var fields = [
            { id: 'userName', label: 'User Name', minLength: 3 ,noWhitespace: true},
            { id: 'name', label: 'Name', minLength: 3 },
            { id: 'lastName', label: 'Last Name', minLength: 3 },
            { id: 'phone', label: 'Phone', minLength: 9 },
            { id: 'passwd', label: 'Password', minLength: 3 },
            { id: 'email', label: 'Email', minLength: 5 },
        ];

        fields.forEach(function(field) {
            var value = $('#' + field.id).val();
            var errorId = '#' + field.id + 'Error';

            if (!value) {
                $(errorId).text(field.label + ' boş olamaz');
                valid = false;
            } else if (field.noWhitespace && /\s/.test(value)) {
                $(errorId).text(field.label + ' boşluk içeremez');
                valid = false;
            } else if (field.minLength && value.length < field.minLength) {
                $(errorId).text(field.label + ' en az ' + field.minLength + ' karakter olmalıdır');
                valid = false;
            } else {
                $(errorId).text('');
            }
        });

        if (!valid) {
            event.preventDefault();
        }
    });
    $('#registerForm input').on('input', function() {
        var id = $(this).attr('id');
        var errorId = '#' + id + 'Error';
        $(errorId).text('');
    });
    $('#updateForm input').on('input', function() {
        var id = $(this).attr('id');
        var errorId = '#' + id + 'Error';
        $(errorId).text('');
    });

});

function validateForm() {
    var valid = true;

    var fields = [
        { id: 'userName', label: 'User Name', minLength: 3 ,noWhitespace: true},
        { id: 'name', label: 'Name', minLength: 3 },
        { id: 'lastName', label: 'Last Name', minLength: 3 },
        { id: 'phone', label: 'Phone', minLength: 9 },
        { id: 'passwd', label: 'Password', minLength: 3 },
        { id: 'email', label: 'Email', minLength: 5 },
    ];

    fields.forEach(function(field) {
        var value = $('#' + field.id).val();
        var errorId = '#' + field.id + 'Error';

        if (!value) {
            $(errorId).text(field.label + ' boş olamaz');
            valid = false;
        } else if (field.noWhitespace && /\s/.test(value)) {
            $(errorId).text(field.label + ' boşluk içeremez');
            valid = false;
        } else if (field.minLength && value.length < field.minLength) {
            $(errorId).text(field.label + ' en az ' + field.minLength + ' karakter olmalıdır');
            valid = false;
        } else {
            $(errorId).text('');
        }
    });

    return valid;
}
