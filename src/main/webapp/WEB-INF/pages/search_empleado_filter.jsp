<form:form action="gastosporempleado" method="post">
	<input type="text"  id="filter" value="" name="nombreEmpleado">
	<button type="submit" class="btn btn-sm"><i class="fas fa-search"></i></button>
</form:form>

<script>
  $(document).ready(function() {

	$('#filter').autocomplete({
		source: '${pageContext.request.contextPath}/getEmpleados',
		paramName: "nombre",
		delimiter: ",",
	   transformResult: function(response) {
		return {      	
		  suggestions: $.map($.parseJSON(response), function(item) {      	
		      return { value: item.value, data: item.id};
		   })};        
       }    
	 });			
  });
  </script>