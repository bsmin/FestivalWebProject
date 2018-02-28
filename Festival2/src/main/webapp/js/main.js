$(document).ready(function() {
	// Preloader
	$(window).on('load', function() {
		$("#preloader").delay(600).fadeOut();
	});

	// Mobile Toggle Btn
	$('.navbar-toggle').on('click',function(){
		$('#header').toggleClass('nav-collapse')
	});
	
	
	
	
	var $videoSrc;  
	$('.video-btn').click(function() {
	    $videoSrc = $(this).data( "src" );
	});
	console.log($videoSrc);

	// when the modal is opened autoplay it  
	$('#myModal').on('shown.bs.modal', function (e) {
	    
	// set the video src to autoplay and not to show related video. Youtube related video is like a box of chocolates... you never know what you're gonna get
	$("#video").attr('src',$videoSrc + "?rel=0&amp;showinfo=0&amp;modestbranding=1&amp;autoplay=1" ); 
	})
	    
	// stop playing the youtube video when I close the modal
	$('#myModal').on('hide.bs.modal', function (e) {
	    // a poor man's stop video
	    $("#video").attr('src',$videoSrc); 
	});
	
});
function go_page( page ){
	$("#curPage").val( page );
	$("#list").submit();
}
function go_season( no ){
	$("#season").val( no );
	$("#list").submit();
}
function go_detail( contentId,title,image,term,addr,mapx,mapy ,curPage){
	$('#contentId').val( contentId );
	$('#title').val( title );
	$('#image').val( image );
	$('#term').val( term );
	$('#addr').val( addr );
	$('#mapx').val( mapx );
	$('#mapy').val( mapy );
	$('#curPage').val( curPage );
	$('#list').attr('action', 'detail.fa');
	$('#list').submit();
}
