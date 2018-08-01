$(document).ready(
		function() {
			$("#addButton").click(function() {
				$(".add-row").css("display", "table-row");
			});

			$(".add-row #cancel").click(function() {
				$(".add-row").css("display", "none");
			});

			$(".delete-button").click(
					function() {
						$('.delete-row').css("display", "none");
						$('.edit-row').css("display", "none");
						$('.view-row').css("display", "table-row");
						$(this).closest('tr').nextAll('.delete-row').first()
								.css("display", "table-row");
					});

			$(".delete-row #cancel").click(function() {
				$('.delete-row').css("display", "none");
			});

			$(".update-button").click(
					function() {
						$('.edit-row').css("display", "none");
						$('.delete-row').css("display", "none");
						$('view-row').css("display", "table-row");
						$(this).closest('.view-row').css("display", "none");
						$(this).closest('tr').nextAll('.edit-row').first().css(
								"display", "table-row");
					});

			$(".edit-row #cancel").click(function() {
				$('.view-row').css("display", "table-row");
				$('.edit-row').css("display", "none");
			});
});