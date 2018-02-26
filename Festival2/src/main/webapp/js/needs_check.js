function necessary(){
	var needs = true; 
	$(".needs").each( function(){
		if( $(this).val().trim()=="" ){
			alert( $(this).attr("title") + " 입력하세요!!");
			$(this).val("");
			$(this).focus();
			 needs = false;
			 return needs;
		}
	} );
	return needs;
}