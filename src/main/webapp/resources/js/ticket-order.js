$(document).ready(function () {
    $('#date').on('click', function () {
        $('#calendar').show()
    })

    $('#time').on('click', function () {
        $('#time-list').show()
    })

    $(document).on('click', function (e) {
        if (!($('#date').is(e.target) || $(e.target).parents("#calendar").length)) {
            $('#calendar').hide()
        }

        if (!($('#time').is(e.target) || $(e.target).parents("#time-list").length)) {
            $('#time-list').hide()
        }

    });

})