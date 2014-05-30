// What is $(document).ready ? See: http://flowplayer.org/tools/documentation/basics.html#document_ready
$(document).ready(function() {

// select the overlay element - and "make it an overlay"
$("#facebox").overlay({

	// custom top position
	top: 260,

	// some mask tweaks suitable for facebox-looking dialogs
	mask: {

		// you might also consider a "transparent" color for the mask
		color: '#fff',

		// load mask a little faster
		loadSpeed: 200,

		// very transparent
		opacity: 0.5
	},

	// disable this for modal dialog-type of overlays
	closeOnClick: true,

	// load it immediately after the construction
	load: false

});
});