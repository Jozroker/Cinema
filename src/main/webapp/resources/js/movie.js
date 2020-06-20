$(document).ready(function () {
    $('.day').on('click', function () {
        // let id = $(this).attr('id');
        let number = $(this).attr('id').slice(-1);
        $('.day').removeClass('active');
        $(this).addClass('active');
        let date = new Date(Date.now());
        date.setDate(date.getDate() + parseInt(number));
        let year = '' + date.getFullYear();
        let month = '' + (date.getMonth() + 1);
        let day = '' + date.getDate();
        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;
        let dateStr = year + "-" + month + "-" + day;

        let movieId = window.location.pathname.slice(-1)

        let url = new URL('http://localhost:8080/movie/' + movieId.toString())

        let params = {date: dateStr}

        url.search = new URLSearchParams(params).toString();

        $.ajax({
            url: url,
            data: $('#schedule')
        }).done(function (data) {
            let elem = $(data).find('#schedule').html()
            $('#schedule').html(elem)
        })
    })
})
