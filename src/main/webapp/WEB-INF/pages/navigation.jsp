<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

		<c:if test="${pages gt 1}">
			<ul class="pagination justify-content-center">
				<li class="page-item disabled">
				<input type="hidden" value="${pageActive}" id="pageNumber" name="pageNumber" />
				<li class="page-item">
					<button type="submit" class="page-link" id="primero">&lt;&lt;</button>
				</li>
				<li class="page-item">
					<button type="submit" class="page-link" id="atras">&lt;</button>
				</li>
				<c:forEach begin="1" end="${pages}" var="page" varStatus="loop">
					<c:choose>
						<c:when test="${pageActive eq page}">
							<li class="page-item active"><a class="page-link">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item">
								<button type="submit" class="page-link" id="${page}">${page}</button></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li class="page-item">
					<button type="submit" class="page-link" id="adelante">&gt;</button>
				</li>
				<li class="page-item">
					<button type="submit" class="page-link" id="ultimo">&gt;&gt;</button>
					<input type="hidden" id="pages" value="${pages}"/>
				</li>
			</ul>
		</c:if>
		<label>Numero de registros a mostrar</label>
		<select name="pageSize" id="pageSize" >
			<c:choose>
				<c:when test="${pageSize eq 5}">
					<option value="5" label="5" selected="selected" />
				</c:when>
				<c:otherwise>
					<option value="5" label="5" />
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${pageSize eq 10}">
					<option value="10" label="10" selected="selected" />
				</c:when>
				<c:otherwise>
					<option value="10" label="10" />
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${pageSize eq 25}">
					<option value="25" label="25" selected="selected" />
				</c:when>
				<c:otherwise>
					<option value="25" label="25" />
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${pageSize eq 50}">
					<option value="50" label="50" selected="selected" />
				</c:when>
				<c:otherwise>
					<option value="50" label="50" />
				</c:otherwise>
			</c:choose>
		</select>

<script>
	$(".page-link").on('click', function() {
		var id = $(this).attr('id');
		if(id === 'atras' || id === 'adelante') {
			if(id === 'atras') {
				var num = +$("#pageNumber").val() - 1;
				$("#pageNumber").val(num);
			} else {
				var num = +$("#pageNumber").val() + 1;
				$("#pageNumber").val(num);
			}
		} else {
			$("#pageNumber").val(id);
		}
		var $form = $(this).closest('form');
	    $form.submit();
		});

	$("#pageSize").on('change', function() {
		$("#pageNumber").val("1");
		var $form = $(this).closest('form');
	    $form.submit();
	});

	$("#primero").click(function(){
		$("#pageNumber").val(1);
		var $form = $(this).closest('form');
	    $form.submit();
	});

	$("#ultimo").click(function(){
		var pages = +$("#pages").val();
		$("#pageNumber").val(pages);
		var $form = $(this).closest('form');
	    $form.submit();
	});
</script>