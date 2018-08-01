<%@ include file="header.jsp"%>

<div class="row main-row fill ml-sm-auto">
	<nav class="col-sm-2 sidebar hidden-xs pt-3 px-4">
		<div class="sidebar-sticky">
			<%@ include file="menu_items.jsp" %>
			
			<h6
				class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
				<span>Informes frecuentes </span>
			</h6>
			<ul class="nav flex-column mb-2">
				<li class="nav-item">
					<form:form action="gastos" method="get" modelAttribute="concepto">
						<input type="hidden" name="tipo" value="todo" />
						<button type="submit" class="nav-link">
							<i class="fas fa-list"></i> Todos
						</button>
					</form:form></li>
				<li class="nav-item">
					<form:form action="gastos" method="get" modelAttribute="concepto">
						<input type="hidden" name="tipo" value="mes" />
						<button type="submit" class="nav-link">
							<i class="fas fa-list"></i> Este mes
						</button>
					</form:form></li>
				<li class="nav-item"><form:form action="gastos" method="get" modelAttribute="concepto">
						<input type="hidden" name="tipo" value="trimestre" />
						<button type="submit" class="nav-link">
							<i class="fas fa-list"></i> Último trimestre
						</button>
					</form:form></li>
				<li class="nav-item">
					<form:form action="gastos" method="get" modelAttribute="concepto">
						<input type="hidden" name="tipo" value="anual" />
						<button type="submit" class="nav-link">
							<i class="fas fa-list"></i> Este año
						</button>
					</form:form></li>
				<li class="nav-item"><form:form action="gastosportipo"
						method="post" modelAttribute="concepto">
						<input type="hidden" name="tipo" value="guardia" />
						<button type="submit" class="nav-link">
							<i class="fas fa-list"></i> Guardias
						</button>
					</form:form></li>
				<li class="nav-item"><form:form action="gastosportipo"
						method="post" modelAttribute="concepto">
						<input type="hidden" name="tipo" value="horasextra" />
						<button type="submit" class="nav-link">
							<i class="fas fa-list"></i> Horas Extra
						</button>
					</form:form></li>
				<li class="nav-item"><form:form action="gastosportipo"
						method="post" modelAttribute="concepto">
						<input type="hidden" name="tipo" value="dietas" />
						<button type="submit" class="nav-link">
							<i class="fas fa-list"></i> Gastos
						</button>
					</form:form>
					</li>
				<li class="nav-item">
					<form:form action="selectcliente" method="get" modelAttribute="concepto">
						<button type="submit" class="nav-link">
							<i class="fas fa-list"></i> Por Cliente...
						</button>
					</form:form>
					</li>
			</ul>
		</div>
	</nav>

	<main role="main"
		class="col-sm-10 col-xs-12 main-content-area pt-3 px-4">
		<div class="expand">
		<i class="far fa-arrow-alt-circle-right expand"></i>
	</div>
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 cabecera-clientes">
		<h1 class="h2">Selección de Clientes</h1>
	</div>

	<div class="container">
		<c:if test="${resultado eq 0}">
			<div class="alert alert-success" role="alert">
				<i class="fas fa-check"></i> Acción realizada con éxito
			</div>
		</c:if>

		<c:choose>
			<c:when test="${counter eq 0 }">
				<div class="alert alert-warning" role="alert">
					<i class="fas fa-exclamation-triangle"></i> No hay clientes para mostrar
				</div>
			</c:when>
			<c:otherwise>
				<form:form action="gastosporcliente" method="post">
					<ul class="list-unstyled">
							<c:forEach var="cliente" items="${listCliente}" varStatus="loop" begin="0">
								<li> <input type="checkbox" class="form-check-input" value="${cliente.id}" name="${cliente.id}">${cliente.nombre}</li>
							</c:forEach>
					</ul>
					<div class="row-fluid form-element" align="center">
						<button type="submit" class="btn btn-primary btn-sm">Generar</button>
						<input type="button" id="cancel" value="Cancelar"
							class="btn btn-primary btn-sm" />
					</div>
				</form:form>
			</c:otherwise>
		</c:choose>
	</div>
	</main>
</div>



<script src="<c:url value="/resources/js/clientes.js" />"></script>

<script>
	$(".collapse").click(function() {
		$(".sidebar").css("display", "none");
		$("main").css("min-width", "100%");
		$(".expand").css("display", "block");
	});

	$(".expand").click(function() {
		$(".sidebar").css("display", "block");
		$("main").css("min-width", "");
		$(".expand").css("display", "none");
	});
</script>

<%@ include file="footer.jsp"%>