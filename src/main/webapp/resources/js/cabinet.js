$(document).ready(function () {
    $('.table').on('click', function () {
        let id = $(this).find('#first').attr('class').split(/\s+/)[0];
        let url = window.location.origin + '/ticket?id=' + id;
        window.location.href = url
    })

    if ($('.table').length) {
        $('#tickets').removeClass('empty');
    }
})