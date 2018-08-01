<input type="text"  id="w-input-search" value="" name="nombreEmpleado">
	
  <script>
  $(document).ready(function() {

	$('#w-input-search').autocomplete({
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
