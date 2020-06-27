function changeLanguage(language) {
    window.location.replace()
}

$(document).ready(
    function () {
        $('.navbar .dropdown-item').on('click', function (e) {
            var $el = $(this).children('.dropdown-toggle');
            var $parent = $el.offsetParent(".dropdown-menu");
            $(this).parent("li").toggleClass('open');

            if (!$parent.parent().hasClass('navbar-nav')) {
                if ($parent.hasClass('show')) {
                    $parent.removeClass('show');
                    $el.next().removeClass('show');
                    $el.next().css({"top": -999, "left": -999});
            } else {
                $parent.parent().find('.show').removeClass('show');
                $parent.addClass('show');
                $el.next().addClass('show');
                $el.next().css({
                    "top": $el[0].offsetTop,
                    "left": $parent.outerWidth() - 4
                });
            }
                e.preventDefault();
                e.stopPropagation();
            }
        });

        $('.navbar .dropdown').on('hidden.bs.dropdown', function () {
            $(this).find('li.dropdown').removeClass('show open');
            $(this).find('ul.dropdown-menu').removeClass('show open');
        });

        if (window.location.pathname === '/authorization' ||
            window.location.pathname === '/register' ||
            window.location.pathname === '/login') {
            $('#sign-in').hide();
        } else {
            $('#sign-in').show();
        }

        $('#sign-in-button').on('click',
            function () {
                let url = window.location.origin + '/authorization';
                window.location.href = url
            }
        )
    }
);

$(document).ready(function () {

    if (window.location.pathname === '/schedule') {
        $('#schedule-link').addClass('selected')
    } else if (window.location.pathname === '/movies') {
        $('#movies-link').addClass('selected')
    }

    $.ajax({
        url: contextPath + '/movies/list'
    }).done(function (data) {
        let list = []
        for (let elem in data) {
            let element = '<li class="list-group-item"><a href="' + contextPath + '/movie/' + elem + '">' + data[elem] + '</a></li>';
            list.push(element);
        }
        $('#movies').html(list)
    })

    $("#search-line").on("keyup", function () {
        let value = $(this).val().toLowerCase();
        if (value.length > 0) {
            $('#movies').show()
        } else {
            $('#movies').hide()
        }
        $("#movies a").filter(function () {
            $(this).parent().toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
})