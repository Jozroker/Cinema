$(document).ready(function () {
    $('.selected').removeClass('selected');

    $(document).on('change', '#tab-1', function () {
        if (window.location.pathname === '/login') {
            $('.login-html').animate({height: '50%'})
        } else {
            $('.login-html').animate({height: '43%'})
        }
    });

        $(document).on('change', '#tab-1', function () {
            if (window.location.pathname === '/authorization') {
                $('.login-html').animate({height: '43%'})
            }
        });

        $(document).on('change', '#tab-2', function () {
            if (window.location.pathname === '/register') {
                $('.login-html').animate({height: '100%'})
            } else {
                $('.login-html').animate({height: '80%'})
            }
        })

        let url = new URL(window.location)

        if (url.searchParams.get('type') === 'registration') {
            $('#tab-2').click()
        }

        if (window.location.pathname === '/register') {
            $('#tab-2').click()
        }

        if (window.location.pathname === '/login') {
            $('.login-html').animate({height: '50%'})
        }
    }
)