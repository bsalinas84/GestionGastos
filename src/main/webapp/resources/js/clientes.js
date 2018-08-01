$(document).ready(function() {
	$("#addButton").click(function() {
		$(".edit-row").css("display", "none");
		$(".delete-row").css("display", "none");
		$(".add-row").css("display", "flex");
	});

	$(".add-row #cancel").click(function() {
		$(".add-row").css("display", "none");
	});

	$(".updatebutton").click(function() {
		$("li").removeClass("active");
		$(".edit-row").css("display", "none");
		$(".delete-row").css("display", "none");
		$(".add-row").css("display", "none");
		$(".normal-row").css("display", "flex");
		$(this).closest("li").addClass("active");
		$("li.active > .edit-row").css("display", "flex");
		$("li.active > .normal-row").css("display", "none");
	});

	$(".edit-row #cancel").click(function() {
		$("li.active > .normal-row").css("display", "flex");
		$("li.active > .edit-row").css("display", "none");
		$("li").removeClass("active");
	});

	$(".deletebutton").click(function() {
		$("li").removeClass("active");
		$(".edit-row").css("display", "none");
		$(".delete-row").css("display", "none");
		$(".add-row").css("display", "none");
		$(".normal-row").css("display", "flex");
		$(this).closest("li").addClass("active");
		$("li.active > .delete-row").css("display", "block");
	});

	$(".delete-row #cancel").click(function() {
		$(".delete-row").css("display", "none");
		$("li").removeClass("active");
	});

});