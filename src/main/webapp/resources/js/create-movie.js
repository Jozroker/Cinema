$(document).ready(function () {
    $("#time").durationPicker({
        hours: {
            label: "h",
            min: 0,
            max: 23
        },
        minutes: {
            label: "m",
            min: 0,
            max: 59
        },
        classname: 'picker',
        responsive: true
    });
})

$(document).ready(function () {

    $("#search-line").on("keyup", function () {
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
        actors.add('<li class="list-group-item avatar-selected">' + $(this).html() + '</li>');
        $('.actors').html(Array.from(actors));
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
        console.log(picture)
        let name = $('#name').val();
        let description = $('#description').val();
        let duration = miliseconds($('#duration-hours').val(), $('#duration-minutes').val());
        console.log(duration)
        let actors = [];
        for (let i = 0; i < $('.avatar-selected img').length; i++) {
            actors.push($($('.avatar-selected img')[i]).attr('class').split(/\s+/)[0]);
        }
        if ($('#fileToUpload').val().length === 0 || $('#name').val().length === 0 || $('#description').val().length === 0 ||
            $('#duration-hours').val().length === 0 || $('#duration-minutes').val().length === 0) {
            alert('Some fields is empty')
        } else {
            console.log('is')
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
                    alert('Movie from such name is already exists')
                } else {
                    window.location.href = contextPath + '/home'
                }
            }).fail(function (data) {
                let state = data.responseText;
                if (state === 'exists') {
                    alert('Movie from such name is already exists')
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
                let element = '<li class="actor list-group-item">' +
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