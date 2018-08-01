$(document).ready(
		function() {
			if ($(".sin-importe").is(":checked")) {
				$(".importe").css("display", "none");
			} else {
				$(".importe").css("display", "block");
			}
			
			$("#exportbutton").click(function() {
				$("#exportexceldiv").css("display", "block");
			});
			
			$("#exportexcel #cancel").click(function() {
				$("#exportexceldiv").css("display", "none");
			});

			$(".delete-button").click(
					function() {
						$('.delete-row').css("display", "none");
						$('.edit-row').css("display", "none");
						$('.copy-row').css("display", "none");
						$('.view-row').css("display", "table-row");
						$(this).closest('tr').nextAll('.delete-row').first()
								.css("display", "table-row");
					});

			$(".delete-row #cancel").click(function() {
				$('.delete-row').css("display", "none");
			});

			$(".update-button").click(
					function() {
						$('.copy-row').css("display", "none");
						$('.edit-row').css("display", "none");
						$('.delete-row').css("display", "none");
						$('.view-row').css("display", "table-row");
						$(this).closest('.view-row').css("display", "none");
						$(this).closest('tr').nextAll('.edit-row').first().css(
								"display", "table-row");
						$('.sidebar').css("display", "none");
						$('main').removeClass("col-sm-10");
						$('main').addClass("col-sm-12");
					});

			$(".edit-row #cancel").click(function() {
				$('.view-row').css("display", "table-row");
				$('.edit-row').css("display", "none");
				$('.sidebar').css("display", "block");
				$('main').removeClass("col-sm-12");
				$('main').addClass("col-sm-10");
			});

			$(".copy-button").click(
					function() {
						$('.copy-row').css("display", "none");
						$('.edit-row').css("display", "none");
						$('.delete-row').css("display", "none");
						$('.view-row').css("display", "table-row");
						$(this).closest('.view-row').css("display", "none");
						$(this).closest('tr').nextAll('.copy-row').first().css("display", "table-row");
						$('.sidebar').css("display", "none");
						$('main').removeClass("col-sm-10");
						$('main').addClass("col-sm-12");
					});

			$(".copy-row #cancel").click(function() {
				$('.view-row').css("display", "table-row");
				$('.copy-row').css("display", "none");
				$('.sidebar').css("display", "block");
				$('main').removeClass("col-sm-12");
				$('main').addClass("col-sm-10");
			});

			$("#addButton").click(function() {
				$(".add-row").css("display", "table-row");
				$('.sidebar').css("display", "none");
				$('main').removeClass("col-sm-10");
				$('main').addClass("col-sm-12");
			});

			$(".add-row #cancel").click(function() {
				$('.add-row').css("display", "none");
				$('.sidebar').css("display", "block");
				$('main').removeClass("col-sm-12");
				$('main').addClass("col-sm-10");
			});
			
			$(".form-check-input").click(function() {
				if ($(".sin-importe").is(":checked")) {
				  $(".importe").css("display", "none");
				} else {
					$(".importe").css("display", "block");
				}
			});
			
			$(".collapse").click(function(){
				$(".sidebar").css("display", "none");
			});
		});