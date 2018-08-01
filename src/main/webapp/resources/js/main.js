$(".collapse").click(function(){
	$(".sidebar").css("display", "none");
	$("main").css("min-width", "100%");
	$(".expand").css("display", "block");
});

$(".expand").click(function(){
	$(".sidebar").css("display", "block");
	$("main").css("min-width", "");
	$(".expand").css("display", "none");
});