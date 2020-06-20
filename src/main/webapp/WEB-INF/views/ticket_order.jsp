<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 12.06.2020
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<sec:authentication var="user" property="principal"/>
<html>
<head>
    <title>Buy ticket</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/ticket-order.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/ticket-order.js"></script>
    <script>
        function createseating(rows, cols, reserved) {
            let seatingValue = [];
            let height = rows * 30;
            let width = (cols + 1) * 30;
            <%--let reserved = new Map();--%>

            <%--<c:forEach var="place" items="${reserved}">--%>
            <%--    reserved.set(${place.key}, ${place.value});--%>
            <%--</c:forEach>--%>

            // console.log(reserved)
            // let reserve = new Map(reserved);
            // console.log(Object.values(reserved))
            // console.log(reserve.keys())
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
                    // if ( j === 9){
                    //     let seatingStyle = "<div class='clearfix'>" + j + "</div>";
                    //     seatingValue.push(seatingStyle);
                    // }
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
    </script>
    <script>
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
            let params = new URLSearchParams(window.location.search);
            // //params.get('movieId')
            let url = window.location.origin + '/order/times?movieId=' + ${movie.id} +
                '&date=' + dateStr;
            $('#seanceDate').val(dateStr);
            $('#seanceTime').val('');

            // console.log(url)
            // window.location.href = url;
            $.ajax({
                url: url,
                data: $('#time-list')
            }).done(function (data) {
                // let elem = $(data).find('#time-list').html()
                // $('#time-list').html(elem)
                let times = [];
                for (let elem in data) {
                    for (let inside = 0; inside < data[elem].length; inside++) {
                        let hour = elem.length < 2 ? '0' + elem.toString() : elem.toString();
                        let minute = data[elem][inside].length < 2 ? '0' + data[elem][inside].toString() : data[elem][inside].toString();
                        // console.log(hour + ':' + minute)
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
            // console.log(hour)
            let time = hour + ':' + minute;
            // let params = new URLSearchParams(window.location.search);
            let url1 = window.location.origin + '/movie/hall?movieId=' + ${movie.id} +
                '&date=' + $('#seanceDate').val() + '&time=' + time;
            let url2 = window.location.origin + '/movie/hall/reserved?movieId=' + ${movie.id} +
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
                        if (!reserved.has(row)) {
                            reserved.set(parseInt(row), [])
                        }
                        reserved.get(parseInt(row)).push(parseInt(data[row][column]));
                    }
                }
                createseating(rows, columns, reserved);
                $('#places').show();
            })
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
    </script>
    <script>
        $(document).on('click', '#buy', function () {

            let seanceId = null;
            let date = $('#seanceDate').val();
            let time = $('#seanceTime').val();
            let url = window.location.origin + '/seance?movieId=' + ${movie.id} +
                '&date=' + date + '&time=' + time;

            $.ajax({
                url: url,
                async: false
            }).done(function (data) {
                seanceId = data['id'];
            })

            if ($('.selected').length === 0) {
                alert('Please choose place')
            } else {
                for (let i = 0; i < $('.selected').length; i++) {
                    let row = $($('.selected')[i]).attr('class').split(/\s+/)[0];
                    let column = $($('.selected')[i]).attr('class').split(/\s+/)[1];
                    let url = '${contextPath}' + $('#order').attr('action') + '?row=' + row + '&column=' + column +
                        '&seanceId=' + seanceId + '&date=' + date + '&time=' + time;
                    // $('#order').attr('action', url);
                    // $('#order').submit();
                    // console.log(url)
                    $.ajax({
                        url: url,
                        method: 'POST',
                        async: false
                    })
                }

                window.location.href = '${contextPath}/home'
            }

        })

        // $('#form').submit(function(){
        //
        //     let url =
        //
        //     return true;
        // });
    </script>
</head>
<body id="body">
<div class="register-photo">
    <div class="form-container">
        <div id="image" class="image-holder">
            <img src="data:image/jpeg;base64,${movie.pictureString}" alt="movie poster"/>
        </div>
        <div style="display: flex">
            <sec:authorize access="hasAuthority('USER')">
            <form id="order" method="POST" action="/order/user/ticket">
                </sec:authorize>
                <sec:authorize access="hasAuthority('WORKER')">
                <form id="order" method="POST" action="/order/ticket">
                    </sec:authorize>
                    <div id="form">
                        <h3 class="text-center"><strong><spring:message code="home.buy.ticket"/></strong></h3>
                        <h2><strong>${movie.name}</strong></h2>
                        <div id="info">
                            <div id="date" class="form-group">
                                <spring:message code="schedules.date"/>:
                                <div id="calendar">
                                    <jsp:include page="calendar.jsp"/>
                                </div>
                                <%--                        <springForm:input path="seanceDate" class="input" cssErrorClass="invalid"/>--%>
                                <%--                        <springForm:errors path="seanceDate" cssClass="invalid-text" element="div"/>--%>
                                <input id="seanceDate" class="input">
                            </div>
                            <div id="time" class="form-group">
                                <spring:message code="schedules.start.time"/>:
                                <div id="time-list">
                                    <div id="time-container" class="list-group">
                                        <p class="list-group-item list-group-item-action"><spring:message code="state.date.not.match"/></p>
                                    </div>
                                </div>
                                <%--                    <springForm:input path="seanceTime" class="input" cssErrorClass="invalid"/>--%>
                                <%--                    <springForm:errors path="seanceTime" cssClass="invalid-text" element="div"/>--%>
                                <input id="seanceTime" class="input">
                            </div>
                        </div>
                        <div id="places">
                            <div id="screen"></div>
                            <div id="messagePanel" class="messagePanel"></div>
                            <div id="button">
                                <button id="buy" class="btn btn-primary btn-block" type="submit" onclick="return false"><spring:message code="ticket.buy"/></button>
                            </div>
                        </div>
                    </div>
                </form>
        </div>

    </div>
</div>
</body>
</html>
