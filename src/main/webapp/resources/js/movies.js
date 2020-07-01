$(document).ready(function () {
    $(document).on('click', '.cross', function () {
        let url = window.location.origin + '/admin/delete/movie?movieId=' + $(this).attr('id');
        $.ajax({
            url: url,
            method: 'POST',
            async: false
        })
        location.reload();
    })

    $('#container a').on('click', function () {
        window.location.href = window.location.origin + '/movie/' + $(this).attr('id');
    })
})