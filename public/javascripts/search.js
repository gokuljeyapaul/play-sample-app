$(function() {
    $( "#search" ).autocomplete({
      source: '/products/ids',
      minLength: 1,
      select: function( event, ui ) {
        var url = '/products/:id/search';
        url = url.replace(":id", ui.item.value);
        $.get(url, function(data, status){
	        $("#edit").html(data);
	    });
      }
    });
  });