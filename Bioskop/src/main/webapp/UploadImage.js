/*jslint browser: true, white: true, eqeq: true, plusplus: true, sloppy: true, vars: true*/
/*global $, console, alert, FormData, FileReader*/


function noPreview() {
  $('#image-preview-div').css("display", "none");
  $('#preview-img').attr('src', 'noimage');
  $('addItem').attr('disabled', '');
}

function selectImage(e){
  $('#file').css("color", "green");

  $('#image-preview-div').css("display", "block");
  $('#preview-img').attr('src', e.target.result);
  $('#preview-img').css('max-width', '550px');
}



  var maxsize = 500 * 1024; // 500 KB

  $('#max-size').html((maxsize/1024).toFixed(2));

  $('#itemAdBtn').click( function() {
	//generete random image id
    $('#message').empty();
    $('#loading').show();
    var itemAd = {}
	itemAd.name = $('#itemName').val();
    itemAd.description = $('#itemDescription').val();
    itemAd.expiryDate = $("#itemExpiryDate").val();
    alert(itemAd.expiryDate);
    $.ajax({
      url: "api/uploadImage",
      type: "POST",
      data: new FormData($("#formItem")[0]),
      contentType: false,
      cache: false,
      processData: false,
      success: function(pictureName)
      {
    	  itemAd.picture = pictureName;
        $('#loading').hide();
        $.ajax({
    	    contentType: 'application/json',
    	    data: JSON.stringify(itemAd),
    	    dataType: 'json',
    	    success: function(data){
    			$('#newItem').modal('hide');
    			$("#image-preview-div").css("display", "none");
    			myAds();
    	    },
    	    error: function(data){
    	    	alert("greska");
    	    },
    	    processData: false,
    	    type: 'POST',
    	    url: '/api/itemAds'
    	});
      }
    });
    
    //$('#newItem').modal('hide');
    
//	
  });
  $('#officialItemBtn').click( function() {
		//generete random image id
	    $('#message').empty();
	    $('#loading').show();
	    var officialItem = {}
		officialItem.name = $('#itemName').val();
	    officialItem.description = $('#itemDescription').val();
	    officialItem.price = $('#price').val();
	    officialItem.quantity = $("#itemQuantity").val();
	    $.ajax({
	      url: "api/uploadImage",
	      type: "POST",
	      data: new FormData($("#formItem")[0]),
	      contentType: false,
	      cache: false,
	      processData: false,
	      success: function(pictureName)
	      {
	    	  officialItem.picture = pictureName;
	        $('#loading').hide();
	        $.ajax({
	    	    contentType: 'application/json',
	    	    data: JSON.stringify(officialItem),
	    	    dataType: 'json',
	    	    success: function(data){
	    			$('#newItem').modal('hide');
	    			$("#image-preview-div").css("display", "none");
	    			officialItems();
	    	    },
	    	    error: function(data){
	    	    	alert("greska");
	    	    },
	    	    processData: false,
	    	    type: 'POST',
	    	    url: '/api/officialItems'
	    	});
	      }
	    });
	    
	    //$('#newItem').modal('hide');
	    
	//	
	  });
  

  $('#file').change(function() {

    $('#message').empty();

    var file = this.files[0];
    var match = ["image/jpeg", "image/png", "image/jpg"];

    if ( !( (file.type == match[0]) || (file.type == match[1]) || (file.type == match[2]) ) )
    {
      noPreview();

      $('#message').html('<div class="alert alert-warning" role="alert">Unvalid image format. Allowed formats: JPG, JPEG, PNG.</div>');

      return false;
    }

    if ( file.size > maxsize )
    {
      noPreview();

      $('#message').html('<div class=\"alert alert-danger\" role=\"alert\">The size of image you are attempting to upload is ' + (file.size/1024).toFixed(2) + ' KB, maximum size allowed is ' + (maxsize/1024).toFixed(2) + ' KB</div>');

      return false;
    }
    
    $('#officialItemBtn').removeClass("disabled");
    $('#officialItemBtn').prop("disabled",false);

    $('#itemAdBtn').removeClass("disabled");
    $('#itemAdBtn').prop("disabled",false);
    var reader = new FileReader();

    reader.onload = selectImage;
    reader.readAsDataURL(this.files[0]);

  

});