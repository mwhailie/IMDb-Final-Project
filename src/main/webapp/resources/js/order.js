
function showModal(a) {
    //Modal heading showing food name
    var schedulename = $("#schedule" + a.id).html();
    var scheduleID = a.id;
    console.log(schedulename);
    $('#myModalLabel').text(schedulename);
    //Modal body
    var price = parseFloat($("#price" + a.id).html());
    $('.perTicket').html('<span>Price Per Ticket: $</span><span id="p">' +price +'</span>');
    $('#scheduleID').val(scheduleID);
    //Modal foot showing quatity and price
    var price = parseFloat($("#price" + a.id).html());
    var q = $('input[name=quantity]').html();
    $('input:text').val(1);

}

// add to bag quantity spinner
$(document).on('click', '.number-spinner button', function () {    
    var btn = $(this),
        oldValue = btn.closest('.number-spinner').find('input').val().trim(),
        newVal = 0;
    
    if (btn.attr('data-dir') == 'up') {
        newVal = parseInt(oldValue) + 1;
    } else {
        if (oldValue > 1) {
            newVal = parseInt(oldValue) - 1;
        } else {
            newVal = 1;
        }
    }
    btn.closest('.number-spinner').find('input').val(newVal);
});

function addtobag(){
    var p = parseFloat($('.modal-body #p').html());
        q = $('input#quantityinput:text').val().trim();
        subtotal = p*q;
        // subtotal = (subtotal).toFixed(2);
        schedulename = $('#myModalLabel').text();
        carttotal = parseFloat($('#totalprice').html());
        scheduleID = $('#scheduleID').val();
        
    console.log(p);
    console.log(q);
    console.log(subtotal);

    // fixed to 2 digit
    var subtotalfix2 = (subtotal).toFixed(2);
    $('.panel-body table').append('<tr><td id="ticketNum">'+ q +'</td><td>'+ scheduleID +'</td><td>'+ schedulename +'</td><td>$'+ subtotalfix2 +'</td></tr>');
    carttotal += subtotal;
    carttotalfix2 = (carttotal).toFixed(2);
    $('span#totalprice').html(carttotalfix2);
    $('#totalpricehiddenfield').val(carttotalfix2);
    $('#scheduleIDhiddenfield').val(scheduleID);
    $('#ticketNumhiddenfield').val(q);
    $('#pay').show();
    $('#time').hide();
}

