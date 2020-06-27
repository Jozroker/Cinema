<%--
  Created by IntelliJ IDEA.
  User: Jozroker
  Date: 17.06.2020
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" href="${contextPath}/resources/css/calendar.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script>
        let Calendar = function (o) {
            this.divId = o.ParentID;
            this.DaysOfWeek = o.DaysOfWeek;
            this.Months = o.Months;
            this.Months_ENG = o.Months_ENG;
            let d = new Date();
            this.CurrentMonth = d.getMonth();
            this.CurrentYear = d.getFullYear();

            let f = o.Format;

            if (typeof (f) == 'string') {
                this.f = f.charAt(0).toUpperCase();
            } else {
                this.f = 'M';
            }

        };

        Calendar.prototype.nextMonth = function () {

            if (this.CurrentMonth == 11) {
                this.CurrentMonth = 0;
                this.CurrentYear = this.CurrentYear + 1;
            } else {
                this.CurrentMonth = this.CurrentMonth + 1;
            }
            this.showCurrent();
        };

        Calendar.prototype.previousMonth = function () {

            if (this.CurrentMonth == 0) {
                this.CurrentMonth = 11;
                this.CurrentYear = this.CurrentYear - 1;
            } else {
                this.CurrentMonth = this.CurrentMonth - 1;
            }
            this.showCurrent();
        };

        Calendar.prototype.previousYear = function () {

            this.CurrentYear = this.CurrentYear - 1;

            this.showCurrent();
        }

        Calendar.prototype.nextYear = function () {
            console.log(" ");
            console.log("Calendar.prototype.nextYear = function() {");
            console.log("this.CurrentYear == " + this.CurrentYear);
            this.CurrentYear = this.CurrentYear + 1;
            console.log("this.CurrentYear - 1 i.e. this.CurrentYear == " + this.CurrentYear);
            this.showCurrent();
        }

        Calendar.prototype.showCurrent = function () {
            this.Calendar(this.CurrentYear, this.CurrentMonth);
        };

        Calendar.prototype.Calendar = function (y, m) {
            typeof (y) == 'number' ? this.CurrentYear = y : null;
            typeof (y) == 'number' ? this.CurrentMonth = m : null;

            let firstDayOfCurrentMonth = new Date(y, m, 1).getDay();
            let lastDateOfCurrentMonth = new Date(y, m + 1, 0).getDate();
            let lastDateOfLastMonth = m == 0 ? new Date(y - 1, 11, 0).getDate() : new Date(y, m, 0).getDate();
            let monthandyearhtml = '<span class="' + this.Months[m] + ' ' + y + '" id="monthandyearspan">' + this.Months[m] + ' - ' + y + '</span>';
            let html = '<table>';
            html += '<tr>';

            for (let i = 0; i < 7; i++) {
                html += '<th class="daysheader">' + this.DaysOfWeek[i] + '</th>';
            }
            html += '</tr>';

            let validDates = new Map();
            let validDays = [];
            let validMonth = this.Months[m].toString().toLowerCase();
            let validMonthEng = this.Months_ENG[m];

            <c:forEach var="date" items="${dates}">
            validDates.set('${date.key}', ${date.value});
            </c:forEach>

            validDates.forEach((v, k) => {
                if (k === validMonth || k === validMonthEng) {
                    v.forEach(item => validDays.push(item));
                }
            })

            let p = dm = this.f == 'M' ? 1 : firstDayOfCurrentMonth == 0 ? -5 : 2;
            let cellvalue;
            for (let d, i = 0, z0 = 0; z0 < 6; z0++) {
                html += '<tr>';
                for (let z0a = 0; z0a < 7; z0a++) {
                    d = i + dm - firstDayOfCurrentMonth;
                    if (d < 1) {
                        cellvalue = lastDateOfLastMonth - firstDayOfCurrentMonth + p++;
                        html += '<td class="prevmonthdates" id="prevmonthdates">' + (cellvalue) + '</td>';
                    } else if (d > lastDateOfCurrentMonth) {
                        html += '<td class="nextmonthdates" id="nextmonthdates">' + (p++) + '</td>';
                    } else {
                        if (window.location.pathname === '/schedule') {
                            html += '<td class="currentmonthdates" id="currentmonthdates">' + (d) + '</td>';
                        } else {
                            if (validDays.includes(d)) {
                                html += '<td class="currentmonthdates" id="currentmonthdates">' + (d) + '</td>';
                            } else {
                                html += '<td class="not-used" id="">' + (d) + '</td>';
                            }
                        }
                        p = 1;
                    }

                    if (i % 7 == 6 && d >= lastDateOfCurrentMonth) {
                        z0 = 10;
                    }
                    i++;
                }
                html += '</tr>';
            }

            html += '</table>';

            document.getElementById("monthandyear").innerHTML = monthandyearhtml;
            document.getElementById(this.divId).innerHTML = html;
        };

        window.onload = function () {
            let c = new Calendar({
                ParentID: "divcalendartable",

                DaysOfWeek: [
                    '<spring:message code="day.monday" />',
                    '<spring:message code="day.tuesday" />',
                    '<spring:message code="day.wednesday" />',
                    '<spring:message code="day.thursday" />',
                    '<spring:message code="day.friday_js" />',
                    '<spring:message code="day.saturday" />',
                    '<spring:message code="day.sunday" />'
                ],

                Months: ['<spring:message code="month.january" />', '<spring:message code="month.february" />', '<spring:message code="month.march" />',
                    '<spring:message code="month.april" />', '<spring:message code="month.may" />', '<spring:message code="month.june" />',
                    '<spring:message code="month.july" />', '<spring:message code="month.august" />', '<spring:message code="month.september" />',
                    '<spring:message code="month.october" />', '<spring:message code="month.november" />', '<spring:message code="month.december" />'],

                Months_ENG: ['january', 'february', 'march',
                    'april', 'may', 'june',
                    'july', 'august', 'september',
                    'october', 'november', 'december'],

                Format: 'dd/mm/yyyy'
            });
            c.showCurrent();

            getId('btnPrev').onclick = function () {
                c.previousMonth();
            };

            getId('btnPrevYr').onclick = function () {
                c.previousYear();
            };

            getId('btnNext').onclick = function () {
                c.nextMonth();
            };

            getId('btnNextYr').onclick = function () {
                c.nextYear();
            };
        }

        function getId(id) {
            return document.getElementById(id);
        }

        $(document).ready(function () {
            $('#btnNextYr').hide();
            $('#btnPrevYr').hide();
        })
    </script>
</head>
<body>

<div class="divcalendar">

    <div id="calendaroverallcontrols">
        <div id="calendarmonthcontrols">
            <a id="btnPrevYr" href="#" title="Previous Year"><span><<</span></a>
            <a id="btnPrev" href="#" title="Previous Month"><span><</span></a>
            <div id="monthandyear"></div>
            <a id="btnNext" href="#" title="Next Month"><span>></span></a>
            <a id="btnNextYr" href="#" title="Next Year"><span>>></span></a>
        </div>
    </div>

    <div id="divcalendartable"></div>

</div>
</body>
</html>
