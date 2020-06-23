$(document).ready(function () {
    $('#date').on('click', function () {
        $('#calendar').show()
    })

    $(document).on('click', function (e) {
        if (!($('#date').is(e.target) || $(e.target).parents("#calendar").length)) {
            $('#calendar').hide()
        }
    });
})


let url;

$(document).ready(function () {

    $(document).on('mouseenter', '.table-row', function () {
        $(this).find('td').css({'background-color': '#565c65'});
        $(this).find('th').css({'background-color': '#565c65'});
    })

    $(document).on('mouseleave', '.table-row', function () {
        $(this).find('td').css({'background-color': '#343A40'});
        $(this).find('th').css({'background-color': '#343A40'});
    })

    $(document).on('click', '.currentmonthdates', function () {
        let day = $(this).text();
        let scope = $('#monthandyearspan').attr('class').split(/\s+/);
        let month = getMonthFromString(scope[0]);
        let year = scope[1];
        if (month.toString().length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;
        let dateStr = year + "-" + month + "-" + day;
        url = window.location.origin + '/schedule/seances?date=' + dateStr;
        $('#seanceDate').val(dateStr);

        getSchedule();

        function getMonthFromString(mon) {
            let Months_ENG = ['January', 'February', 'March',
                'April', 'May', 'June',
                'July', 'August', 'September',
                'October', 'November', 'December'];

            let Months = ['Січень', 'Лютий', 'Березень',
                'Квітень', 'Травень', 'Червень',
                'Липень', 'Серпень', 'Вересень',
                'Жовтень', 'Листопад', 'Грудень'];

            if (Months.includes(mon)) {
                mon = Months_ENG[Months.indexOf(mon)]
            }

            let d = Date.parse(mon + "1, 2012");
            if (!isNaN(d)) {
                return new Date(d).getMonth() + 1;
            }
            return -1;
        }
    })
})

$(document).on('click', function (e) {
    if (!($(e.target).parents(".table-row").length || $(e.target).parents(".change-row").length)) {
        getSchedule();
        $('#schedule').css({'margin-right': '40%'})
    }
});

$(document).on('click', '.change', function () {

    let seanceId = $('#seance-id').val();
    let movieId = $('#movie-input').attr('class').split(/\s+/)[0];
    let movieName = $('#movie-input').val();
    let movieBeginTime = $('#timepicker').val() + ':00';
    let hallId = $('#hall-input').val();
    let price = parseFloat($('#price-input').val()).toFixed(2);
    let dateFrom = $('#date-from').val();
    let dateTo = $('#date-to').val();
    let path = window.location.origin + '/admin/change/seance?movieName=' + movieName;

    $.ajax({
        url: path,
        method: 'POST',
        async: false,
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        cache: false,
        data: JSON.stringify({
            "id": seanceId,
            "movieId": movieId,
            "hallId": hallId,
            "ticketPrice": price,
            "movieBeginTime": movieBeginTime,
            "seanceDateFrom": dateFrom,
            "seanceDateTo": dateTo
        })
    })

    getSchedule();
})

function getSchedule() {

    $.ajax({
        url: url,
        method: 'GET'
    }).done(function (data) {
        let seances = [];
        let counter = 1;
        for (let elem in data) {
            seances.push('<tr class="table-row">' +
                '<th scope="row" class="' + data[elem]['id'] + ' spacing first seance">' + counter++ + '</th>' +
                '<td class="' + data[elem]['movie']['id'] + ' movie' +
                ' spacing">' + data[elem]['movie']['name'] + '</td>' +
                '<td class="spacing">' + data[elem]['movieBeginTime'].slice(0, -3) + '</td>' +
                '<td class="spacing">' + data[elem]['hall']['id'] + '</td>' +
                '<td class="spacing">' + data[elem]['hall']['type'].slice(1) + '</td>' +
                '<td class="spacing">' + data[elem]['seanceDateFrom'] + '</td>' +
                '<td class="spacing">' + data[elem]['seanceDateTo'] + '</td>' +
                '<td class="spacing last">' + data[elem]['ticketPrice'] + ' UAH</td>' +
                '</tr>');
        }
        $('#table-body').html(seances);
        $('#calendar').hide()
    })
}