$(document).ready(function () {
    $("#time").durationPicker({
        hours: {
            label: hourChar,
            min: 0,
            max: 23
        },
        minutes: {
            label: minuteChar,
            min: 0,
            max: 59
        },
        classname: 'picker',
        responsive: true
    });
})

$(document).ready(function () {

    $(document).on('click', function (e) {
        if (!($(e.target).parents("#search-actor").length || $(e.target).parents("#all-actors").length)) {
            $('#all-actors').hide()
        }
    });

    $(document).on('click', '.bi', function () {
        $(this).parent().remove();
        if (!$('#current-actors').find('li').length) {
            $('#current-actors').hide();
        }
    })

    $('#alert a').on('click', function () {
        $('#alert').hide();
        $('input, textarea').prop('disabled', false);
    })

    $('#search-actor').on('keyup', function () {
        let value = $(this).val().toLowerCase();
        if (value.length > 0) {
            $('#all-actors').show()
        } else {
            $('#all-actors').hide()
        }
        $("#all-actors li").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
        $('#create-actor').show()
    });

    let actors = new Set();

    $(document).on('click', '.actor', function () {
        actors.add('<li class="current-actor avatar-selected">' +
            '<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
            '  <path fill-rule="evenodd" d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>' +
            '  <path fill-rule="evenodd" d="M11.854 4.146a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708-.708l7-7a.5.5 0 0 1 .708 0z"/>' +
            '  <path fill-rule="evenodd" d="M4.146 4.146a.5.5 0 0 0 0 .708l7 7a.5.5 0 0 0 .708-.708l-7-7a.5.5 0 0 0-.708 0z"/>' +
            '</svg>' +
            $(this).html() + '</li>');
        $('.actors').html(Array.from(actors));
        $('#current-actors').show();
        $('#all-actors').hide()
    })

    let imageBytes;

    function readURL(input) {
        if (input.files && input.files[0]) {
            let reader = new FileReader();

            reader.onload = function (e) {
                $('#image-container').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);

            let reader2 = new FileReader();

            reader2.onload = function (e) {
                imageBytes = e.target.result;
            }
            reader2.readAsArrayBuffer(input.files[0])
        }
    }

    $("#fileToUpload").change(function () {
        readURL(this);
    });

    $(document).on('click', '#createMovieBtn', function () {

        function miliseconds(hrs, min) {
            return ((hrs * 360 + min * 60) * 1000);
        }

        let picture = Array.from(new Uint8Array(imageBytes));
        let name = $('#name').val();
        let description = $('#description').val();
        let duration = miliseconds($('#duration-hours').val(), $('#duration-minutes').val());
        let actors = [];
        for (let i = 0; i < $('.avatar-selected img').length; i++) {
            actors.push($($('.avatar-selected img')[i]).attr('class').split(/\s+/)[0]);
        }
        if ($('#name').val().length === 0 || $('#description').val().length === 0 ||
            $('#duration-hours').val().length === 0 || $('#duration-minutes').val().length === 0) {
            // alert('Some fields is empty')
            $('#alert').find('p').text('Some fields is empty');
            $('#alert').show();
            $('#alert').css('animation', 'show-alert 0.4s cubic-bezier(0.390, 0.575, 0.565, 1.000) both')
            $('input, textarea').prop('disabled', true);
        } else if ($('#fileToUpload').val().length === 0) {
            $('#alert').find('p').text('Image not uploaded');
            $('#alert').show();
            $('#alert').css('animation', 'show-alert 0.4s cubic-bezier(0.390, 0.575, 0.565, 1.000) both')
            $('input, textarea').prop('disabled', true);
        } else if ($('#duration-hours').val() < 0 || $('#duration-hours').val() > 23 ||
            $('#duration-minutes').val() < 0 || $('#duration-minutes').val() > 59) {
            $('#alert').find('p').text('Duration value is incorrect');
            $('#alert').show();
            $('#alert').css('animation', 'show-alert 0.4s cubic-bezier(0.390, 0.575, 0.565, 1.000) both')
            $('input, textarea').prop('disabled', true);
        } else {
            $.ajax({
                url: contextPath + '/admin/create/movie',
                type: 'POST',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                cache: false,
                data: JSON.stringify({
                    picture: picture,
                    name: name,
                    description: description,
                    duration: duration,
                    actorsIds: actors
                })

            }).done(function (data) {
                let state = data.responseText;
                if (state === 'exists') {
                    // alert('Movie from such name is already exists')
                    $('#alert').find('p').text('Movie from such name is already exists');
                    $('#alert').show();
                    $('#alert').css('animation', 'show-alert 0.4s cubic-bezier(0.390, 0.575, 0.565, 1.000) both')
                } else {
                    window.location.href = contextPath + '/home'
                }
            }).fail(function (data) {
                let state = data.responseText;
                if (state === 'exists') {
                    // alert('Movie from such name is already exists')
                    $('#alert').find('p').text('Movie from such name is already exists');
                    $('#alert').show();
                    $('#alert').css('animation', 'show-alert 0.4s cubic-bezier(0.390, 0.575, 0.565, 1.000) both')
                } else {
                    window.location.href = contextPath + '/home'
                }
            })
        }
    })
})

$(document).ready(function () {
    $(document).on('click', '#create-actor', function () {

        $.ajax({
            url: contextPath + '/admin/create/actor'
        }).done(function (data) {
            $('#actor-creating').html(data).show()
        })

        $('#all-actors').hide()

    })

    $(document).on('click', '#createBtn', function () {
        let form = $('#actor')[0];
        let data = new FormData(form);
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            data: data,
            url: contextPath + '/admin/create/actor'
        }).done(function (data) {
            $('#actor-creating').html(data)
            getActors();
        })
    })

    $(document).on('click', function (e) {
        if (!($(e.target).parents("#actor-creating").length)) {
            $('#actor-creating').hide()
        }
    });

    function getActors() {
        $.ajax({
            url: contextPath + '/admin/get/actors'
        }).done(function (data) {
            let list = []
            for (let elem in data) {
                let element = '<li class="actor">' +
                    '<img class="' + data[elem]['id'] + ' avatar" src="data:image/jpeg;base64,' + data[elem]['pictureString'] + '" alt="actor' +
                    data[elem]['id'] + '"/> ' +
                    data[elem]['firstName'] + ' ' + data[elem]['lastName'] +
                    '</li>';
                list.push(element);
            }
            list.push('<li id="create-actor" class="list-group-item">Create new actor</li>');
            $('#all-actors').html(list)
        })
    }

    getActors();
})