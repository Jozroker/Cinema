$(document).ready(function () {
    let currentDate = getDate(0);
    let currentUrl = window.location.href + '/seances?date=' + currentDate;
    getSchedule(currentUrl);

    $('.day').on('click', function () {
        let number = $(this).attr('id').slice(-1);
        $('.day').removeClass('active');
        $(this).addClass('active');
        let date = getDate(number);

        let url = window.location.href + '/seances?date=' + date;
        getSchedule(url);
    })
})

function getDate(dayIndex) {
    let date = new Date(Date.now());
    date.setDate(date.getDate() + parseInt(dayIndex));
    let year = '' + date.getFullYear();
    let month = '' + (date.getMonth() + 1);
    let day = '' + date.getDate();
    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;
    return year + "-" + month + "-" + day;
}

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
        $('#table-body').html(seances);
    })
}
