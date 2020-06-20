$(document).ready(function () {
    // $('ul, span').hover(
    //     function () {
    //         clearInterval(autoSwap);
    //     },
    //     function () {
    //         // autoSwap = setInterval( swap,3500);
    //     });

    // $('.selected').removeClass("selected");

    $('.day').on('click', function () {
        // let id = $(this).attr('id');
        console.log('hi')
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

        let url = new URL('http://localhost:8080/home')

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

    $('#buy').on('click', function () {
        let movieId = $('.main-pos').attr('class').split(/\s+/)[0];
        let url = window.location.origin + '/movie/order?movieId=' + movieId;
        window.location.href = url
    })

//global variables
    var items = [];
    var startItem = 1;
    var position = 0;
    var itemCount = $('.carousel li.items').length;
    var leftpos = itemCount;
    var resetCount = itemCount;

//unused: gather text inside items class
    $('li.items').each(function (index) {
        items[index] = $(this).text();
    });

//swap images function
    function swap(action) {
        var direction = action;

        //moving carousel backwards
        if (direction == 'counter-clockwise') {
            var leftitem = $('.left-pos').attr('id') - 1;
            if (leftitem == 0) {
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

        //moving carousel forward
        if (direction == 'clockwise' || direction == '' || direction == null) {
            function pos(positionvalue) {
                if (positionvalue != 'leftposition') {
                    //increment image list id
                    position++;

                    //if final result is greater than image count, reset position.
                    if ((startItem + position) > resetCount) {
                        position = 1 - startItem;
                    }
                }

                //setting the left positioned item
                if (positionvalue == 'leftposition') {
                    //left positioned image should always be one left than main positioned image.
                    position = startItem - 1;

                    //reset last image in list to left position if first image is in main position
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

//next button click function
    $('#next').click(function () {
        swap('clockwise');
    });

//prev button click function
    $('#prev').click(function () {
        swap('counter-clockwise');
    });

//if any visible items are clicked
    $('section li').click(function () {
        if ($(this).attr('class') == 'items left-pos') {
            swap('counter-clockwise');
        } else {
            swap('clockwise');
        }
    });
})
