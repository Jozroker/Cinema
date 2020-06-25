$(document).ready(function () {

    let currentDate = new Date(Date.now());
    let currentYear = '' + currentDate.getFullYear();
    let currentMonth = '' + (currentDate.getMonth() + 1);
    let currentDay = '' + currentDate.getDate();
    if (currentMonth.length < 2)
        currentMonth = '0' + currentMonth;
    if (currentDay.length < 2)
        currentDay = '0' + currentDay;
    let currentDateStr = currentYear + "-" + currentMonth + "-" + currentDay;
    let startingUrl = window.location.origin + '/schedule/seances?date=' + currentDateStr;
    getSchedule(startingUrl);

    $('.day').on('click', function () {
        // let id = $(this).attr('id');
        // console.log('hi')
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

        let url = new URL('http://localhost:8080/schedule/seances')

        let params = {date: dateStr}

        url.search = new URLSearchParams(params).toString();

        getSchedule(url);
    })

    $('#buy').on('click', function () {
        let movieId = $('.main-pos').attr('class').split(/\s+/)[0];
        let url = window.location.origin + '/movie/order?movieId=' + movieId;
        window.location.href = url
    })

    let items = [];
    let startItem = 1;
    let position = 0;
    let itemCount = $('.carousel li.items').length;
    let leftpos = itemCount;
    let resetCount = itemCount;

    $('li.items').each(function (index) {
        items[index] = $(this).text();
    });

    function swap(action) {
        let direction = action;

        if (direction === 'counter-clockwise') {
            let leftitem = $('.left-pos').attr('id') - 1;
            if (leftitem === 0) {
                leftitem = itemCount;
            }

            $('.right-pos').removeClass('right-pos').addClass('back-pos');
            $('.main-pos').removeClass('main-pos').addClass('right-pos');
            $('.left-pos').removeClass('left-pos').addClass('main-pos');
            $('#' + leftitem + '').removeClass('back-pos').addClass('left-pos');

            startItem--;
            if (startItem < 1) {
                startItem = itemCount;
            }
        }

        if (direction === 'clockwise' || direction === '' || direction == null) {
            function pos(positionvalue) {
                if (positionvalue !== 'leftposition') {
                    position++;

                    if ((startItem + position) > resetCount) {
                        position = 1 - startItem;
                    }
                }

                if (positionvalue === 'leftposition') {
                    position = startItem - 1;

                    if (position < 1) {
                        position = itemCount;
                    }
                }
                return position;
            }

            $('#' + startItem + '').removeClass('main-pos').addClass('left-pos');
            $('#' + (startItem + pos()) + '').removeClass('right-pos').addClass('main-pos');
            $('#' + (startItem + pos()) + '').removeClass('back-pos').addClass('right-pos');
            $('#' + pos('leftposition') + '').removeClass('left-pos').addClass('back-pos');

            startItem++;
            position = 0;
            if (startItem > itemCount) {
                startItem = 1;
            }
        }
    }

    $('#next').click(function () {
        swap('clockwise');
    });

    $('#prev').click(function () {
        swap('counter-clockwise');
    });

    $('section li').click(function () {
        if ($(this).attr('class') === 'items left-pos') {
            swap('counter-clockwise');
        } else {
            swap('clockwise');
        }
    });
})

function getSchedule(url) {

    $.ajax({
        url: url,
        method: 'GET'
    }).done(function (data) {
        let seances = [];
        let counter = 1;
        for (let elem in data) {
            seances.push('<tr>' +
                '<th scope="row" class="spacing first">' + counter++ + '</th>' +
                '<td class="movie spacing">' + data[elem]['movie']['name'] + '</td>' +
                '<td class="spacing">' + data[elem]['movieBeginTime'].slice(0, -3) + '</td>' +
                '<td class="spacing">' + data[elem]['hall']['id'] + '</td>' +
                '<td class="spacing">' + data[elem]['hall']['type'].slice(1) + '</td>' +
                '<td class="spacing">' + data[elem]['ticketPrice'] + ' UAH</td>' +
                '<td class="spacing last"><a class="pill"' +
                'href="' + window.location.origin + '/seance/order?seanceId=' + data[elem]['id'] + '">' + buy + '</a>' +
                '</td>' +
                '</tr>');
        }
        $('#schedule-body').html(seances);
    })
}


