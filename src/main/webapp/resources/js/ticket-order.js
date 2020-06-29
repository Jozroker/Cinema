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

    $('#alert a').on('click', function () {
        $('#alert').hide();
        $('input, textarea').prop('disabled', false);
    })

})

function createseating(rows, cols, reserved) {
    let seatingValue = [];
    let height = rows * 30;
    let width = (cols + 1) * 30;
    for (let i = 1; i <= rows; i++) {
        let seatingStyle = "<div class='seat hall-row'>" + i + "</div>";
        seatingValue.push(seatingStyle);
        for (let j = 1; j <= cols; j++) {
            if (reserved.has(i) && reserved.get(i).includes(j)) {
                let seatingStyle = "<div class='seat reserved'>" + j + "</div>";
                seatingValue.push(seatingStyle);
            } else {
                let seatingStyle = "<div class='" + i + ' ' + j + " seat available hall-place'>" + j + "</div>";
                seatingValue.push(seatingStyle);
            }
        }
    }

    $('.messagePanel').width(width).height(height);
    $('#screen').width(width);

    $('#messagePanel').html(seatingValue);

    $(function () {
        $('.hall-place').on('click', function () {
            if ($(this).hasClass("selected")) {
                $(this).removeClass("selected");
            } else {
                $(this).addClass("selected");
            }
        });
        $('.hall-place').mouseenter(function () {
            $(this).addClass("hovering");
            $(this).mouseleave(function () {
                $(this).removeClass("hovering");
            });
        });
    });
}

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
    let url = window.location.origin + '/order/times?movieId=' + movieId +
        '&date=' + dateStr;
    $('#seanceDate').val(dateStr);
    $('#seanceTime').val('');
    $('#places').hide();

    $.ajax({
        url: url,
        data: $('#time-list')
    }).done(function (data) {
        let times = [];
        for (let elem in data) {
            for (let inside = 0; inside < data[elem].length; inside++) {
                let hour = elem.length < 2 ? '0' + elem.toString() : elem.toString();
                let minute = data[elem][inside].length < 2 ? '0' + data[elem][inside].toString() : data[elem][inside].toString();
                times.push(
                    '<p class="time list-group-item list-group-item-action">' +
                    '<span id="hour">' + hour + '</span>:<span id="minute">' + minute + '</span></p>'
                );
            }
        }
        $('#time-container').html(times)
        $('#calendar').hide()
    })
})

$(document).on('click', '.time', function () {
    let hour = $(this).find('#hour').text();
    let minute = $(this).find('#minute').text();
    if (hour.toString().length < 2)
        hour = '0' + hour;
    if (minute.toString().length < 2)
        minute = '0' + minute;
    let time = hour + ':' + minute;
    let url1 = window.location.origin + '/movie/hall?movieId=' + movieId +
        '&date=' + $('#seanceDate').val() + '&time=' + time;
    let url2 = window.location.origin + '/movie/hall/reserved?movieId=' + movieId +
        '&date=' + $('#seanceDate').val() + '&time=' + time;
    $('#seanceTime').val(time);


    let rows = 0;
    let columns = 0;
    $.ajax({
        url: url1,
        async: false
    }).done(function (data) {
        rows = parseInt(data['rows']);
        columns = parseInt(data['columns']);
        $('#time-list').hide()
    })

    $.ajax({
        url: url2,
    }).done(function (data) {
        let reserved = new Map();
        for (let row in data) {
            for (let column in data[row]) {
                if (!reserved.has(parseInt(row))) {
                    reserved.set(parseInt(row), [])
                }
                reserved.get(parseInt(row)).push(parseInt(data[row][column]));
            }
        }
        createseating(rows, columns, reserved);
        $('#places').show();
    })
})

$(document).on('click', '#buy', function () {

    let seanceId = null;
    let date = $('#seanceDate').val();
    let time = $('#seanceTime').val();
    let url = window.location.origin + '/seance?movieId=' + movieId +
        '&date=' + date + '&time=' + time;

    $.ajax({
        url: url,
        async: false
    }).done(function (data) {
        seanceId = data['id'];
    })

    if ($('.selected').length === 0) {
        $('#alert').find('p').text('Please choose place');
        $('#alert').show();
        $('#alert').css('animation', 'show-alert 0.4s cubic-bezier(0.390, 0.575, 0.565, 1.000) both')
        $('input, textarea').prop('disabled', true);
    } else {
        console.log(1)
        console.log($('.selected').length)
        for (let i = 0; i < $('.selected').length; i++) {
            let row = $($('.selected')[i]).attr('class').split(/\s+/)[0];
            let column = $($('.selected')[i]).attr('class').split(/\s+/)[1];
            let url = window.location.origin + $('#order').attr('action') + '?row=' + row + '&column=' + column +
                '&seanceId=' + seanceId + '&date=' + date + '&time=' + time;
            console.log(url)
            $.ajax({
                url: url,
                method: 'POST',
                async: false
            })
        }
        window.location.href = window.location.origin + '/home'
    }
})

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