let url;
let adminRole;

$(document).ready(function () {

    $('#date').on('click', function () {
        $('#calendar').show()
    })

    $(document).on('click', function (e) {
        if (!($('#date').is(e.target) || $(e.target).parents("#calendar").length)) {
            $('#calendar').hide()
        }
    });

    $('#seanceDate').val('')

    if (status) {
        adminRole = true;
    } else {
        adminRole = false;
    }
})

$(document).on('click', '#create', function () {
    $('#creating-table-body').html(null);

    let currentDate = new Date();
    let currentYear = currentDate.getFullYear();
    let currentMonth = currentDate.getMonth() + 1;
    let currentDay = currentDate.getDate();
    if (currentMonth.toString().length < 2)
        currentMonth = '0' + currentMonth;
    if (currentDay.length < 2)
        currentDay = '0' + currentDay;
    let dateStr = currentYear + "-" + currentMonth + "-" + currentDay;

    let creationDays =
        '<div class="checkbox"><input type="checkbox" id="MONDAY" name="MONDAY"><label for="MONDAY">' + monday + '</label></div>' +
        '<div class="checkbox"><input type="checkbox" id="TUESDAY" name="TUESDAY"><label for="TUESDAY">' + tuesday + '</label></div>' +
        '<div class="checkbox"><input type="checkbox" id="WEDNESDAY" name="WEDNESDAY"><label for="WEDNESDAY">' + wednesday + '</label></div>' +
        '<div class="checkbox"><input type="checkbox" id="THURSDAY" name="THURSDAY"><label for="THURSDAY">' + thursday + '</label></div>' +
        '<div class="checkbox"><input type="checkbox" id="FRIDAY" name="FRIDAY"><label for="FRIDAY">' + friday + '</label></div>' +
        '<div class="checkbox"><input type="checkbox" id="SATURDAY" name="SATURDAY"><label for="SATURDAY">' + saturday + '</label></div>' +
        '<div class="checkbox"><input type="checkbox" id="SUNDAY" name="SUNDAY"><label for="SUNDAY">' + sunday + '</label></div>';

    let creationForm = '<tr>' +
        '<td class="movie spacing first"><input name="movieName" id="movie-input" type="text"></td>' +
        '<td class="spacing"><input name="time" type="time" id="timepicker" value="00:00"></td>' +
        '<td class="spacing" colspan="2"><input name="hallId" id="hall-input" type="number" min="1" max="7" value="1"></td>' +
        '<td class="spacing"><input name="dateFrom" type="date" id="date-from" value="' + dateStr + '"></td>' +
        '<td class="spacing"><input name="dateTo" type="date" id="date-to" value="' + dateStr + '"></td>' +
        '<td class="spacing">' + creationDays + '</td>' +
        '<td class="spacing"><input name="price" id="price-input" type="number" step="0.01" value="0.00" min="0.01"> UAH</td>' +
        '<td class="spacing last"><button id="confirm">' + create + '</button></td>' +
        '</tr>';

    $('#creating-table-body').html(creationForm);
    $('#create-container').toggle();

    $('#create-seance').css({'margin-right': '30%'})
})

$(document).ready(function () {
    $(document).on('click', '.changing', function () {
        $(this).removeClass('changing').addClass('change-row');
        let days =
            '<div class="checkbox"><input type="checkbox" id="MONDAY" name="MONDAY"><label for="MONDAY">' + monday + '</label></div>' +
            '<div class="checkbox"><input type="checkbox" id="TUESDAY" name="TUESDAY"><label for="TUESDAY">' + tuesday + '</label></div>' +
            '<div class="checkbox"><input type="checkbox" id="WEDNESDAY" name="WEDNESDAY"><label for="WEDNESDAY">' + wednesday + '</label></div>' +
            '<div class="checkbox"><input type="checkbox" id="THURSDAY" name="THURSDAY"><label for="THURSDAY">' + thursday + '</label></div>' +
            '<div class="checkbox"><input type="checkbox" id="FRIDAY" name="FRIDAY"><label for="FRIDAY">' + friday + '</label></div>' +
            '<div class="checkbox"><input type="checkbox" id="SATURDAY" name="SATURDAY"><label for="SATURDAY">' + saturday + '</label></div>' +
            '<div class="checkbox"><input type="checkbox" id="SUNDAY" name="SUNDAY"><label for="SUNDAY">' + sunday + '</label></div>';

        let form =
            '<th scope="row" class="' + $(this).find('th').attr('class').split(/\s+/)[0] + ' spacing first seance">' +
            '<input id="seance-id" name="id" type="hidden" value="' + $(this).find('th').attr('class').split(/\s+/)[0] + '">' + $(this).find('th').text() +
            '</th>' +
            '<td class="movie spacing"><input class="' + $($(this).find('td')[0]).attr('class').split(/\s+/)[0] + '" name="movieName" id="movie-input" type="text" value="' +
            $(this).find('td')[0].innerText + '"></td>' +
            '<td class="spacing"><input name="time" type="time" id="timepicker" value="' + $(this).find('td')[1].innerText + '"></td>' +
            '<td class="spacing" colspan="2"><input name="hallId" id="hall-input" type="number" min="1" max="7" value="' + $(this).find('td')[2].innerText +
            '"></td>' +
            '<td class="spacing"><input name="dateFrom" type="date" id="date-from" value="' + $(this).find('td')[4].innerText + '"></td>' +
            '<td class="spacing"><input name="dateTo" type="date" id="date-to" value="' + $(this).find('td')[5].innerText + '"></td>' +
            '<td class="spacing">' + days + '</td>' +
            '<td class="spacing"><input name="price" id="price-input" type="number" step="0.01" value="' + $(this).find('td')[7].innerText.slice(0, -4) +
            '"> UAH</td>' +
            '<td class="spacing last change"><button id="change">' + button + '</button></td>';

        let activeDays = [];

        $(this).find('.day').each(function (index) {
            activeDays.push($(this).attr('class').split(/\s+/)[1])
        })

        $(this).html(form);

        let thisElement = $(this);
        for (let day in activeDays) {
            let selector = '#' + activeDays[day];
            $(thisElement).find(selector).attr({'checked': 'checked'});
        }

        $('#schedule').css({'margin-right': '30%'})
        $('.change').css({'width': '0'})
    })
})

$(document).ready(function () {

    $(document).on('mouseenter', '.changing', function () {
        $(this).find('td').css({'background-color': '#565c65'});
        $(this).find('th').css({'background-color': '#565c65'});
    })

    $(document).on('mouseleave', '.changing', function () {
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
        $('#calendar').hide()
        getSchedule(adminRole);

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
        if ($(document).find('.change-row').length) {
            getSchedule(adminRole);
            $('#schedule').css({'margin-right': '35%'})
        }
    }
});

$(document).on('click', '#confirm', function () {

    let movieName = $('#create-seance').find('#movie-input').val();
    let movieBeginTime = $('#create-seance').find('#timepicker').val() + ':00';
    let hallId = $('#create-seance').find('#hall-input').val();
    let price = parseFloat($('#create-seance').find('#price-input').val()).toFixed(2);
    let dateFrom = $('#create-seance').find('#date-from').val();
    let dateTo = $('#create-seance').find('#date-to').val();
    let path = window.location.origin + '/admin/creation/seance?movieName=' + movieName;
    let days = [];
    $(this).parent().parent().find('input:checked').each(function (index) {
        days.push($(this).attr('id'));
    })
    let parent = $(this).parent().parent();

    $.ajax({
        url: path,
        method: 'POST',
        async: false,
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        cache: false,
        data: JSON.stringify({
            "hallId": hallId,
            "ticketPrice": price,
            "movieBeginTime": movieBeginTime,
            "seanceDateFrom": dateFrom,
            "seanceDateTo": dateTo,
            "day": days
        })
    }).fail(function (data) {
        if (data.responseText === 'exists') {
            $(parent).find('#timepicker').addClass('invalid')
            $(parent).find('#date-from').addClass('invalid')
            $(parent).find('#date-to').addClass('invalid')
        } else if (data.responseText === 'fail movie') {
            console.log('hi')
            $(parent).find('#movie-input').addClass('invalid')
        } else {
            $(parent).find('#timepicker').removeClass('invalid')
            $(parent).find('#date-from').removeClass('invalid')
            $(parent).find('#date-to').removeClass('invalid')
            $('#create-container').hide(1000);
        }
    })
})

$(document).on('click', '.change', function () {

    let seanceId = $('#seance-id').val();
    let movieId = $('#movie-input').attr('class').split(/\s+/)[0];
    let movieName = $('#movie-input').val();
    let movieBeginTime = $('#timepicker').val() + ':00';
    let hallId = $('#hall-input').val();
    let price = parseFloat($('#price-input').val()).toFixed(2);
    let dateFrom = $('#date-from').val();
    let dateTo = $('#date-to').val();
    let path = window.location.origin + '/admin/creation/seance?movieName=' + movieName;
    let days = [];
    $(this).parent().parent().find('input:checked').each(function (index) {
        days.push($(this).attr('id'));
    })
    let parent = $(this).parent();

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
            "seanceDateTo": dateTo,
            "day": days
        })
    }).fail(function (data) {
        if (data.responseText === 'exists') {
            $(parent).find('#timepicker').addClass('invalid')
            $(parent).find('#date-from').addClass('invalid')
            $(parent).find('#date-to').addClass('invalid')
        } else if (data.responseText === 'fail movie') {
            $(parent).find('#movie-input').addClass('invalid')
        } else {
            $(parent).find('#timepicker').removeClass('invalid')
            $(parent).find('#date-from').removeClass('invalid')
            $(parent).find('#date-to').removeClass('invalid')
            getSchedule(adminRole);
        }
    })
})

function getSchedule(status) {
    $.ajax({
        url: url,
        method: 'GET',
        async: false
    }).done(function (data) {
        let seances = [];
        let counter = 1;
        for (let elem in data) {
            let days = '';

            data[elem]['day'].forEach(day => {
                switch (day) {
                    case 'MONDAY':
                        days += '<p class="day MONDAY">' + monday + '</p>';
                        break;
                    case 'TUESDAY':
                        days += '<p class="day TUESDAY">' + tuesday + '</p>';
                        break;
                    case 'WEDNESDAY':
                        days += '<p class="day WEDNESDAY">' + wednesday + '</p>';
                        break;
                    case 'THURSDAY':
                        days += '<p class="day THURSDAY">' + thursday + '</p>';
                        break;
                    case 'FRIDAY':
                        days += '<p class="day FRIDAY">' + friday + '</p>';
                        break;
                    case 'SATURDAY':
                        days += '<p class="day SATURDAY">' + saturday + '</p>';
                        break;
                    case 'SUNDAY':
                        days += '<p class="day SUNDAY">' + sunday + '</p>';
                        break;
                }
            })

            let first = '<tr class="table-row">';
            if (status) {
                first = '<tr class="table-row changing">';
            }

            let seance = first +
                '<th scope="row" class="' + data[elem]['id'] + ' spacing first seance">' + counter++ + '</th>' +
                '<td class="' + data[elem]['movie']['id'] + ' movie' +
                ' spacing">' + data[elem]['movie']['name'] + '</td>' +
                '<td class="spacing">' + data[elem]['movieBeginTime'].slice(0, -3) + '</td>' +
                '<td class="spacing">' + data[elem]['hall']['id'] + '</td>' +
                '<td class="spacing">' + data[elem]['hall']['type'].slice(1) + '</td>' +
                '<td class="spacing">' + data[elem]['seanceDateFrom'] + '</td>' +
                '<td class="spacing">' + data[elem]['seanceDateTo'] + '</td>' +
                '<td class="spacing">' + days + '</td>' +
                '<td class="spacing last">' + data[elem]['ticketPrice'] + ' UAH</td>' +
                '</tr>';

            seances.push(seance);
        }
        $('#table-body').html(seances);
    })
}
