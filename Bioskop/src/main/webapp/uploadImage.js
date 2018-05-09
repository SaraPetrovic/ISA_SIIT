/*jslint browser: true, white: true, eqeq: true, plusplus: true, sloppy: true, vars: true*/
/*global $, console, alert, FormData, FileReader*/


function noPreview() {
  $('#image-preview-div').css("display", "none");
  $('#preview-img').attr('src', 'noimage');
  $('addItem').attr('disabled', '');
}

function selectImage(e) {
  $('#file').css("color", "green");
  $('#image-preview-div').css("display", "block");
  $('#preview-img').attr('src', e.target.result);
  $('#preview-img').css('max-width', '550px');
}



  var maxsize = 500 * 1024; // 500 KB

  $('#max-size').html((maxsize/1024).toFixed(2));

  $('#addItem').click( function() {
	//generete random image id
	  var extension = $('#file').val().split(/[. ]+/).pop();
	$('#itemImage').val( Math.random().toString(36).substr(2, 9) + "." + extension);
    $('#message').empty();
    $('#loading').show();
    var thematicItem = {}
	thematicItem.name = $('#itemName').val();
    thematicItem.description = $('#itemDescription').val();
    thematicItem.price = $('#price').val();
    thematicItem.picture = $('#itemImage').val();
    $.ajax({
      url: "api/uploadImage",
      type: "POST",
      data: new FormData($("#formItem")[0]),
      contentType: false,
      cache: false,
      processData: false,
      success: function(data)
      {
        $('#loading').hide();
        $('#message').html(data);
      }
    });
	$.ajax({
	    contentType: 'application/json',
	    data: JSON.stringify(thematicItem),
	    dataType: 'json',
	    success: function(data){
			$('#newItem').modal('hide');


	    },
	    error: function(data){
	    	alert("greska");
	    },
	    processData: false,
	    type: 'POST',
	    url: '/api/createItem'
	});
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

    $('#upload-button').removeAttr("disabled");

    var reader = new FileReader();
    reader.onload = selectImage;
    reader.readAsDataURL(this.files[0]);

  

});