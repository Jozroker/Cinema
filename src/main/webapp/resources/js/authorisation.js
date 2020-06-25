$(document).ready(
    function () {
        $('.selected').removeClass('selected');

        $('#tab-1').on('change', function () {
            if (window.location.pathname === '/login') {
                $('.login-html').animate({height: '50%'})
            } else {
                $('.login-html').animate({height: '43%'})
            }
        });

        $('#tab-1').on('change', function () {
            if (window.location.pathname === '/authorization') {
                $('.login-html').animate({height: '43%'})
            }
        });

        $('#tab-2').on('change', function () {
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