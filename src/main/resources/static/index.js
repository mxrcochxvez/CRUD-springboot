$(document).ready(function() {
    $('button').click(function() {
        var x = $('#x').val() || 0;
        var y = $('#y').val() || 0;
        var url = 'api/math/add/' + x + '/' + y;

        $.get(url, function(data) {
            $('#result').text(data);
        });
    });
});