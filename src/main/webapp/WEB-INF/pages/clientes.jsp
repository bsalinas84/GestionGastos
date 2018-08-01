<%@ include file="header.jsp"%>

<div class="row main-row fill ml-sm-auto">
	<nav class="col-sm-2 sidebar hidden-xs pt-3 px-4">
		<div class="sidebar-sticky">
			<%@ include file="menu_items.jsp" %>
		</div>
	</nav>

	<main role="main"
		class="col-sm-10 col-xs-12 main-content-area pt-3 px-4">
		<div class="expand">
		<i class="far fa-arrow-alt-circle-right expand"></i>
	</div>
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 cabecera-clientes">
		<h1 class="h2">Clientes</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
			<div class="btn-group mr-2">
				<button id="addButton" class="btn btn-sm btn-outline-secondary"
					data-toggle="tooltip" data-placement="top" title="Añadir">
					<span class="fas fa-plus"></span>
				</button>
			</div>
		</div>
	</div>

	<div class="container">
		<c:if test="${resultado eq 0}">
			<div class="alert alert-success" role="alert">
				<i class="fas fa-check"></i> Acción realizada con éxito
			</div>
		</c:if>
		
		<c:if test="${resultado eq -1}">
			<div class="alert alert-danger" role="alert">
				<i class="fas fa-exclamation-triangle"></i> Error. Ya existe un cliente con ese nombre
			</div>
		</c:if>
		
		<c:if test="${resultado eq -2}">
			<div class="alert alert-warning" role="alert">
				<i class="fas fa-exclamation-triangle"></i> Este cliente tiene  empleados asociados. No se borrará
			</div>
		</c:if>
		
		<c:choose>
			<c:when test="${counter eq 0 }">
				<div class="alert alert-warning" role="alert">
					<i class="fas fa-exclamation-triangle"></i> No hay clientes para mostrar
				</div>
			</c:when>
		</c:choose>
				<ul class="list-unstyled">
					<li class="row add-row">
						<div class="col-2 float-right"></div>
						<form:form action="saveCliente" method="post" modelAttribute="cliente">
							<div class="col-6 float-left">
								<form:input path="nombre" required="true" />
							</div>
							<div class="col-4 float-right">
								<button type="submit" name="button" value="updateCliente"
									class="btn btn-sm btn-outline-secondary" data-toggle="tooltip"
									data-placement="top" title="Editar">
									<span class="fas fa-check acept"></span>
								</button>
								<button id="cancel" type="button" name="button"
									value="deleteCliente" class="btn btn-sm btn-outline-secondary">
									<span class="fas fa-times cancel"></span>
								</button>
							</div>
						</form:form>
					</li>
					
			<c:choose>
			<c:when test="${counter ne 0 }">
					<c:forEach var="cliente" items="${listCliente}" varStatus="loop" begin="0">
						<li>
							<div class="row normal-row">
								<div class="col-2 float-right">
									<c:if test="${loop.index eq 0}">
										<form action="clientes" method="get">
											<c:choose>
												<c:when test="${orden eq 'ASC'}">
													<input type="hidden" value="DESC" name="orden"/>
													<button type="submit" class="btn btn-sm btn-outline-secondary">
														<span class="fas fa-arrow-down"></span>
													</button>
												</c:when>
												<c:otherwise>
													<input type="hidden" value="ASC" name="orden">
													<button type="submit" class="btn btn-sm btn-outline-secondary">
														<span class="fas fa-arrow-up"></span>
													</button>
												</c:otherwise>
											</c:choose>
										</form>
									</c:if>
								</div>
								<div class="col-4 float-left">${cliente.nombre}</div>
								<div class="col-6 float-right">
									<button class="btn btn-sm btn-outline-secondary updatebutton"
										data-toggle="tooltip" data-placement="top" title="Editar"
										id="updatebutton">
										<span class="fas fa-edit"></span>
									</button>
									<button class="btn btn-sm btn-outline-secondary deletebutton"
										data-toggle="tooltip" data-placement="top" title="Borrar"
										id="deleteButton">
										<span class="far fa-trash-alt"></span>
									</button>
								</div>
							</div>

							<div class="row edit-row">
									<div class="col-2 float-right"></div>
									<form:form action="saveCliente" method="post" modelAttribute="cliente">
										<div class="col-4 float-left">
											<form:hidden path="id" value="${cliente.id}" />
											<form:input path="nombre" value="${cliente.nombre}" required="true" />
										</div>
										<div class="col-6 float-right">
											<button type="submit" name="button" value="updateCliente"
												class="btn btn-sm btn-outline-secondary"
												data-toggle="tooltip" data-placement="top" title="Editar">
												<span class="fas fa-check acept"></span>
											</button>
											<button id="cancel" type="button" name="button"
												value="deleteCliente"
												class="btn btn-sm btn-outline-secondary">
												<span class="fas fa-times cancel"></span>
											</button>
										</div>
									</form:form>
							</div>

							<div class="row delete-row">
								<form:form class="form" action="deleteCliente" method="post"
									modelAttribute="cliente">
									<form:hidden path="id" value="${cliente.id}" />
									<p>¿Estás seguro de eliminar este registro?</p>
									<div class="row-fluid form-element" align="center">
										<button type="submit" name="button"
											class="btn btn-primary btn-sm">Eliminar</button>
										<input type="button" id="cancel" value="Cancelar"
											class="btn btn-primary btn-sm" />
									</div>
								</form:form>
							</div>
						</li>
					</c:forEach>
				
			</c:when>
		</c:choose>
		</ul>
	</div>
	</main>
</div>

<script src="<c:url value="/resources/js/clientes.js" />"></script>

<script>
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

</script>

<%@ include file="footer.jsp"%>