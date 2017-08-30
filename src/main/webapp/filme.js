function getRow(movie) {
    var row = '<tr>' +
        '<td>' + movie.name + '</td>' +
        '<td>' + movie.year + '</td>' +
        '<td>' + movie.date + '</td>' +
        '<td>[<a href="#" class="remove-item" data-id="'+ movie.id +'">x</a>]</td>'+
        '</tr>';
    return row;
}

var table = document.getElementById('contacts-list');
var tbody = table.getElementsByTagName('tbody')[0];

$.ajax('movies?action=list', {
    dataType: 'json'
}).done(function(result){
    console.info('3) items loaded', result);

    var items = result.items;
    items.forEach(function(contact) {
        tbody.innerHTML += getRow(contact);
    });

    $('.remove-item').click(function(){
        var id = $(this).data('id');
        console.info('remove', id, this);
        removeItem(id);
        $(this).closest('tr').remove();
    });
});

function removeItem(id) {
    $.post('movie', {
        action: 'remove',
        id: id
    });
}