<%@ include file="header.jsp"%>

<style>
	.ui-widget-content {
		background-color: white;
	}
	
	.ui-state-active {
		background-color: lightgrey;
	}
	
	.ui-autocomplete {
		list-style-type: none;
	}
	
	#filter {
		width: 50%;
	}
	
	.w-40 {
		width: 40px;
	}
	
	.w-50 {
		width: 50px;
	}
	
	.w-100 {
		width: 100px;
	}
	
	.line {
		display: inline-flex;
	}
</style>

<script type="text/javascript" src="<c:url value="/resources/js/excel/jszip.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/excel/FileSaver.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/excel/myexcel.js" />" ></script>

<script>
		function go(){
			var excel = $JExcel.new("Calibri light 10 #333333");			// Default font
			
		    excel.set( {sheet:0,value:"Hoja 1" } );													  
 
			var headers=["Nombre", "DNI", "Cliente", "Cantidad", "Concepto", "F.Pago", "Improte", "Fecha", "Observaciones", "Fecha Registro"]; 
			var formatHeader=excel.addStyle ( {
					font: "Calibri 10 #000000 B"});

			for (var i=0;i<headers.length;i++){
				excel.set(0,i,0,headers[i],formatHeader);
				excel.set(0,i,undefined,"auto");
			}

			var gastoRow=excel.addStyle ( {
				font: "Calibri 10 #FF0000"});
			
		var conceptos = [
				<c:forEach items="${listConcepto}" var="concepto" varStatus="status">  
				    {dni: '${concepto.dni}',	
					tipo: '${concepto.tipo}',	
					cantidad: '${concepto.cantidad}',	
					nombreEmpleado: '${concepto.nombreEmpleado}',
					nombreCliente: '${concepto.nombreCliente}',
					importe: '${concepto.importe}',
					observaciones: '${concepto.observaciones}',
					anotaciones: '${concepto.anotaciones}',
					fechaRegistro: '${concepto.fechaRegistro}'}
					<c:if test="${!provinceStatus.last}">
		              ,
		              </c:if>
				    </c:forEach>  
				];

			
			for (var i=0; i< conceptos.length; i++){	
				if(conceptos[i].tipo == 'Gastos') {
					excel.set({row:i+1,style:gastoRow  });
				}
				excel.set(0,0,i+1,conceptos[i].nombreEmpleado);	
				excel.set(0,1,i+1,conceptos[i].dni	);
				excel.set(0,2,i+1,conceptos[i].nombreCliente);
				excel.set(0,3,i+1,conceptos[i].cantidad);
				excel.set(0,4,i+1,conceptos[i].tipo);
				if(conceptos[i].tipo == 'Gastos') {
					excel.set(0,5,i+1,"Dietas");
				} else {
					excel.set(0,5,i+1,"Plus Productividad");
				}
				
				excel.set(0,6,i+1,conceptos[i].importe);
				excel.set(0,7,i+1,conceptos[i].observaciones);
				excel.set(0,8,i+1,conceptos[i].anotaciones);
				excel.set(0,9,i+1,conceptos[i].fechaRegistro);
				}
		
			var nombrefichero = document.getElementById('nombrefichero').value;
		    excel.generate(nombrefichero + ".xlsx");

		    document.getElementById("exportexceldiv").style.display = "none";

		    document.getElementById("exportexcel").submit();
		}
	</script>

<div class="row main-row fill">
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
						<input type="hidden" name="tipo" value="gastos" />
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
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
		<h2 class="h2">Resumen de Gastos</h2>
	</div>
	<c:if test="${cliente ne ''}">
		<hr>
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
			<h3 class="h3">${cliente}</h3>
		</div>
	</c:if>
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3">

		<div class="row">
			<c:if test="${mes ne ''}">
				<form:form action="gastos" method="get">
					<input type="hidden" name="mes" value="${mes}" />
					<input type="hidden" name="direccion" value="atras" />
					<button type="submit" id="mesAtrasButton" class="btn btn-link"
						data-toggle="tooltip" data-placement="top" title="Mes anterior">
						<span class="fas fa-angle-double-left text-muted"></span>
					</button>
				</form:form>
				<h2 class="h2">
					<fmt:formatDate pattern="MMMM YYYY" value="${mes}" dateStyle="full" />
				</h2>
				<form:form action="gastos" method="get">
					<input type="hidden" name="mes" value="${mes}" />
					<input type="hidden" name="direccion" value="adelante" />
					<button type="submit" id="mesAlanteButton" class="btn btn-link"
						data-toggle="tooltip" data-placement="top" title="Mes siguiente">
						<span class="fas fa-angle-double-right text-muted"></span>
					</button>
				</form:form>
			</c:if>
		</div>


		<div class="btn-toolbar mb-2 mb-md-0">
			<div class="btn-group mr-2">
				<button id="addButton" class="btn btn-sm btn-outline-secondary"
					data-toggle="tooltip" data-placement="top" title="Añadir gasto">
					<span class="fas fa-plus"></span>
				</button>
				<button id="exportbutton" class="btn btn-sm btn-outline-secondary"
					data-toggle="tooltip" data-placement="top" title="Generar Excel">
					<span class="far fa-file-excel"></span>
				</button>
			</div>
		</div>
	</div>

	<!-- ExportExcel Form -->
	<div id="exportexceldiv">
		<form:form action="exportExcel" method="post" id="exportexcel">
			<h2 class="title">Exportar a Excel</h2>
			<input type="hidden" name="cliente" value ="${cliente}" />
			<input type="hidden" name="tipo" value="${tipo}" />
			<input type="hidden" name="mes" value="${mes}" />
			<div class="row-fluid form-element">
				<label class="col-form-label col-5">Nombre del fichero</label> <input
					type="text" name="nombrefichero" id="nombrefichero" value="" />
			</div>
			<div class="row-fluid form-element" align="center">
				<button type="button" class="btn btn-primary btn-sm" onclick="go();">Generar</button>
				<input type="button" id="cancel" value="Cancelar"
					class="btn btn-primary btn-sm" />
			</div>
		</form:form>
	</div>
	<!-- End ExportExcel Form --> 
	
	<!-- Buscador de empleados -->
	<div class="row">
		<%@ include file="search_empleado_filter.jsp"%>
	</div>
	<!-- Fin buscador de empleados -->

	<div class="table-responsive">	
		<c:choose>
			<c:when test="${resultado eq 0}">
				<div class="alert alert-success" role="alert">
					<i class="fas fa-check"></i> Acción realizada con éxito
				</div>
			</c:when>
			<c:otherwise>
				<c:if test="${counterconceptos ne 0}">
					<c:if test="${resultado eq '-1'}">
						<div class="alert alert-danger" role="alert">
							<i class="fas fa-exclamation-triangle"></i> Error interno
						</div>
					</c:if>
					<c:if test="${resultado eq '-2'}">
						<div class="alert alert-danger" role="alert">
							<i class="fas fa-exclamation-triangle"></i> Error! No existe
							ningún empleado con ese D.N.I.
						</div>
					</c:if>
					<c:if test="${resultado eq '-3'}">
						<div class="alert alert-danger" role="alert">
							<i class="fas fa-exclamation-triangle"></i> Error! Ya existe un
							gasto de ese tipo en esa fecha.
						</div>
					</c:if>
				</c:if>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${counterconceptos eq 0}">
				<c:choose>
					<c:when test="${counterclientes eq 0}">
						<div class="alert alert-warning" role="alert">
							<i class="fas fa-exclamation-triangle"></i> No hay clientes en el
							sistema. <a href="clientes.html">Añadir Clientes</a>
						</div>
					</c:when>
					<c:when test="${counterempleados eq 0}">
						<div class="alert alert-warning" role="alert">
							<i class="fas fa-exclamation-triangle"></i> No hay empleados en
							el sistema <a href="empleados.html">Añadir Empleados</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-warning" role="alert">
							<i class="fas fa-exclamation-triangle"></i> No hay gastos para
							mostrar
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
		
		<p>${conceptoSize}</p>
		
		<table class="table table-striped">
					<thead>
						<tr>
							<th><div class="row ml-0">
									<span class="align-self-center">Nombre</span>
									<form action="gastos" method="get">
										<input type="hidden" value="nombre" name="columna" />
										<c:choose>
											<c:when test="${columna eq 'nombre' }">
												<c:choose>
													<c:when test="${orden eq 'ASC'}">
														<input type="hidden" value="DESC" name="orden" />
														<button type="submit" class="btn btn-link">
															<i class="fas fa-sort-up"></i>
														</button>
													</c:when>
													<c:otherwise>
														<input type="hidden" value="ASC" name="orden">
														<button type="submit" class="btn btn-link">
															<i class="fas fa-sort-down"></i>
														</button>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<input type="hidden" value="ASC" name="orden">
												<button type="submit" class="btn btn-link">
													<span class="orden-control">
														<i class="fas fa-sort-up"></i>
														<i class="fas fa-sort-down"></i>
													</span>
												</button>
											</c:otherwise>
										</c:choose>
									</form>
								</div></th>
							<th><div class="row">
									<span class="align-self-center">D.N.I.</span>
									<form action="gastos" method="get">
										<input type="hidden" value="dni" name="columna" />
										<c:choose>
											<c:when test="${columna eq 'dni' }">
												<c:choose>
													<c:when test="${orden eq 'ASC'}">
														<input type="hidden" value="DESC" name="orden" />
														<button type="submit" class="btn btn-link">
															<i class="fas fa-sort-up"></i>
														</button>
													</c:when>
													<c:otherwise>
														<input type="hidden" value="ASC" name="orden">
														<button type="submit" class="btn btn-link">
															<i class="fas fa-sort-down"></i>
														</button>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<input type="hidden" value="ASC" name="orden">
												<button type="submit" class="btn btn-link">
													<span class="orden-control">
														<i class="fas fa-sort-up"></i>
														<i class="fas fa-sort-down"></i>
													</span>
												</button>
											</c:otherwise>
										</c:choose>
									</form>
								</div></th>
							<th><span class="align-self-center">Cliente</span></th>
							<th><div class="row">
									<span class="align-self-center">Concepto</span>
									<form action="gastos" method="get">
										<input type="hidden" value="tipo" name="columna" />
										<c:choose>
											<c:when test="${columna eq 'tipo' }">
												<c:choose>
													<c:when test="${orden eq 'ASC'}">
														<input type="hidden" value="DESC" name="orden" />
														<button type="submit" class="btn btn-link">
															<i class="fas fa-sort-up"></i>
														</button>
													</c:when>
													<c:otherwise>
														<input type="hidden" value="ASC" name="orden">
														<button type="submit" class="btn btn-link">
															<i class="fas fa-sort-down"></i>
														</button>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<input type="hidden" value="ASC" name="orden">
												<button type="submit" class="btn btn-link">
													<span class="orden-control">
														<i class="fas fa-sort-up"></i>
														<i class="fas fa-sort-down"></i>
													</span>
												</button>
											</c:otherwise>
										</c:choose>
									</form>
								</div></th>
							<th><span class="align-self-center">F. Pago</span></th>
							<th><span class="align-self-center">Importe</span></th>
							<th><div class="row">
									<span class="align-self-center">Fecha</span>
									<form action="gastos" method="get">
										<input type="hidden" value="observaciones" name="columna" />
										<c:choose>
											<c:when test="${columna eq 'observaciones' }">
												<c:choose>
													<c:when test="${orden eq 'ASC'}">
														<input type="hidden" value="DESC" name="orden" />
														<button type="submit" class="btn btn-link">
															<i class="fas fa-sort-up"></i>
														</button>
													</c:when>
													<c:otherwise>
														<input type="hidden" value="ASC" name="orden">
														<button type="submit" class="btn btn-link">
															<i class="fas fa-sort-down"></i>
														</button>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<input type="hidden" value="ASC" name="orden">
												<button type="submit" class="btn btn-link">
													<span class="orden-control">
														<i class="fas fa-sort-up"></i>
														<i class="fas fa-sort-down"></i>
													</span>
												</button>
											</c:otherwise>
										</c:choose>
									</form>
								</div></th>
								<th>Observaciones</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						
						<tr class="add-row">
							<form:form action="saveConcepto" method="post" modelAttribute="concepto">
								<input type="hidden" name="mes" value="${mes}" />
							<td>
			 					<%@ include file="search_empleado.jsp"%>
							</td>
							<td>
								<form:input type="text" path="dni" class="w-100"/>
							</td>
							<td class="vacio"></td>
							<td class="line">
								<form:input type="text" path="cantidad" class="w-40"/>
								<form:select path="tipo" itemValue="${concepto.tipo}">
									<form:option value="horasextra" label="Horas extra"></form:option>
									<form:option value="guardia" label="Guardia"></form:option>
									<form:option value="gastos" label="Gastos"></form:option>
									<form:option value="released" label="Released"></form:option>
									<form:option value="standby" label = "Standby"></form:option>
									<form:option value="festivonacional" label="Festivo Nacional"></form:option>
									<form:option value="festivononacional" label="Festivo No Nacional"></form:option>
								</form:select>
							</td>
							<td class="vacio"></td>
							<td>
								<form:input type="currency" path="importe" class="w-50"/>
							</td>
							<td>
								<form:input class="form-control" id="datepicker" name="datepicker" placeholder="DD/MM/YYY" type="text" path="observaciones" />
								<script>
									$(function() {
										$("#datepicker").datepicker({
											format : "dd/mm/yyyy",
											language : "es",
											multidate : true,
											multidateSeparator : "-",
											calendarWeeks : true,
											weekStart: 1
										});
									});
								</script>
							</td>
							<td>
								<form:input type="text" path="anotaciones" />
							</td>
							<td>
								<button type="submit" class="btn btn-sm btn-outline-secondary">
									<span class="fas fa-check acept"></span>
								</button>
								<button id="cancel" type="button" name="button" value="deleteCliente" class="btn btn-sm btn-outline-secondary">
									<span class="fas fa-times cancel"></span>
								</button>
							</td>
						</form:form>
							</tr>
		<c:choose>
			<c:when test="${counterconceptos ne 0 }">
						<c:forEach var="concepto" items="${listConcepto}">
							<c:choose>
								<c:when test='${concepto.tipo eq "Gastos"}'>
									<tr class="dietas view-row">
								</c:when>
								<c:otherwise>
									<tr class="view-row">
								</c:otherwise>
							</c:choose>
							<td>
								<c:forEach items="${fn:split(concepto.nombreEmpleado, ',')}" var="nombre">
								    <c:out value="${nombre}" /><br />
								</c:forEach>
							</td>
							<td>${concepto.dni}</td>
							<td>${concepto.nombreCliente}</td>
							<td><fmt:formatNumber value="${concepto.cantidad}"
									type="number" /> &nbsp;${concepto.tipo}</td>
							<td><c:choose>
									<c:when test='${concepto.freelance eq true}'>Freelance</c:when>
									<c:when test='${concepto.tipo eq "Gastos"}'>Dietas</c:when>
									<c:otherwise>Plus productividad</c:otherwise>
								</c:choose></td>
							<td><fmt:formatNumber value="${concepto.importe}"
									type="currency" currencySymbol="&euro;" /></td>
							<td>
								<c:forEach items="${fn:split(concepto.observaciones, '-')}" var="fecha">
								    <c:out value="${fecha}" /><br />
								</c:forEach>
							</td>
							<td>${concepto.anotaciones}</td>
							<td>
								<button class="btn btn-sm btn-outline-secondary copy-button"
									data-toggle="tooltip" data-placement="top" title="Copiar gasto">
									<span class="far fa-clone"></span>
								</button>
								<button class="btn btn-sm btn-outline-secondary update-button"
									data-toggle="tooltip" data-placement="top" title="Editar">
									<span class="fas fa-edit"></span>
								</button>
								<button class="btn btn-sm btn-outline-secondary delete-button"
									data-toggle="tooltip" data-placement="top" title="Borrar">
									<span class="far fa-trash-alt"></span>
								</button>
							</td>
							<tr>

							<tr class="copy-row">
								<form:form action="saveConcepto" method="post"
									modelAttribute="concepto">
									<form:hidden path="id" value="0" />
									<form:hidden path="dni" value="${concepto.dni}" />
									<form:hidden path="tipo" value="${concepto.tipo}" />
									<form:hidden path="cantidad" value="${concepto.cantidad}" />
									<form:hidden path="importe" value="${concepto.importe }" />
									<input type="hidden" name="nombreEmpleado" value="${concepto.nombreEmpleado}"/>
									<td>
										<c:forEach items="${fn:split(concepto.nombreEmpleado, ',')}" var="nombre">
										    <c:out value="${nombre}" /><br />
										</c:forEach>
									</td>
									<td>${concepto.dni}</td>
									<td>${concepto.nombreCliente}</td>
									<td>${concepto.cantidad} ${concepto.tipo}</td>
									<td><c:choose>
											<c:when test='${concepto.tipo eq "gastos"}'>
												Gastos
											</c:when>
											<c:otherwise>
												Plus productividad
											</c:otherwise>
										</c:choose></td>
									<td>${concepto.importe}</td>
									<td><form:input class="form-control datepicker"
											id="datepick" name="datepick" type="text"
											path="observaciones" value="${observaciones}" /> <script>
												$(function() {
													$(".datepicker")
															.datepicker(
																	{
																		format : "dd/mm/yyyy",
																		language : "es",
																		multidate : true,
																		multidateSeparator : "-",
																		calendarWeeks : true,
																		weekStart: 1
																	});
												});
											</script></td>
											<td>
												<form:input type="text" path="anotaciones" />
											</td>
									<td>
										<button type="submit" class="btn btn-sm btn-outline-secondary">
											<span class="fas fa-check acept"></span>
										</button>
										<button id="cancel" type="button" name="button"
											value="deleteCliente"
											class="btn btn-sm btn-outline-secondary">
											<span class="fas fa-times cancel"></span>
										</button>
									</td>
								</form:form>
							</tr>
							<tr class="edit-row">
								<form:form action="editConcepto" method="post"
									modelAttribute="concepto">
									<input type="hidden" name="mes" value="${mes}" />
									<form:hidden path="id" value="${concepto.id}" />
									<form:hidden path="dni" value="${concepto.dni}" />
									<td>
										<c:forEach items="${fn:split(concepto.nombreEmpleado, ',')}" var="nombre">
										    <c:out value="${nombre}" /><br />
										</c:forEach>
									</td>
									<td>${concepto.dni}</td>
									<td>${concepto.nombreCliente}</td>
									<td><form:input type="number" value="${concepto.cantidad}"
											path="cantidad" step="0.5" class="edit-cantidad" /> <form:select
											path="tipo" itemValue="${concepto.tipo}">
											<c:choose>
												<c:when test='${concepto.tipo eq "horasextra"}'>
													<form:option value="horasextra" label="Horas extra"
														selected="true"></form:option>
												</c:when>
												<c:otherwise>
													<form:option value="horasextra" label="Horas extra"></form:option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test='${concepto.tipo eq "guardia"}'>
													<form:option value="guardia" label="Guardia"
														selected="true"></form:option>
												</c:when>
												<c:otherwise>
													<form:option value="guardia" label="Guardia"></form:option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test='${concepto.tipo eq "gastos"}'>
													<form:option value="gastos" label="Gastos" selected="true"></form:option>
												</c:when>
												<c:otherwise>
													<form:option value="gastos" label="Gastos"></form:option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test='${concepto.tipo eq "released"}'>
													<form:option value="released" label="Released" selected="true"></form:option>
												</c:when>
												<c:otherwise>
													<form:option value="released" label="Released"></form:option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test='${concepto.tipo eq "standby"}'>
													<form:option value="standby" label="Standby" selected="true"></form:option>
												</c:when>
												<c:otherwise>
													<form:option value="standby" label="Standby"></form:option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test='${concepto.tipo eq "festivonacional"}'>
													<form:option value="festivonacional" label="Festivo Nacional" selected="true"></form:option>
												</c:when>
												<c:otherwise>
													<form:option value="festivonacional" label="Festivo Nacional"></form:option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test='${concepto.tipo eq "festivononacional"}'>
													<form:option value="festivononacional" label="Festivo No Nacional" selected="true"></form:option>
												</c:when>
												<c:otherwise>
													<form:option value="festivononacional" label="Festivo No Nacional"></form:option>
												</c:otherwise>
											</c:choose>
										</form:select></td>
									<td><c:choose>
											<c:when test='${concepto.tipo eq "gastos"}'>
												Gastos
											</c:when>
											<c:otherwise>
												Plus productividad
											</c:otherwise>
										</c:choose></td>
									<td><c:choose>
											<c:when test='${concepto.tipo ne "horasextra" or concepto.tipo ne "guardia"}'>
												<form:input type="text" value="${concepto.importe}" path="importe" />
											</c:when>
											<c:otherwise>
												<form:input type="text" value="${concepto.importe}"
													path="importe" disabled="true" />
											</c:otherwise>
										</c:choose></td>
									<td>
										<form:input class="form-control datepicker" id="datepick" name="datepick" type="text"
											path="observaciones" value="${concepto.observaciones}" /> 
											<script>
												$(function() {$(".datepicker").datepicker({
													format : "dd/mm/yyyy",
													language : "es",
													multidate : true,
													multidateSeparator : "-",
													calendarWeeks : true,
													weekStart: 1
													});
												});
											</script>
									</td>
									<td>
										<form:input type="text" path="anotaciones" />
									</td>
									<td>
										<button type="submit" class="btn btn-sm btn-outline-secondary">
											<span class="fas fa-check acept"></span>
										</button>
										<button id="cancel" type="button" name="button"
											value="deleteCliente"
											class="btn btn-sm btn-outline-secondary">
											<span class="fas fa-times cancel"></span>
										</button>
									</td>
								</form:form>
							</tr>

							<tr class="delete-row">
								<td colspan="12"><form:form class="form"
										action="deleteConcepto" method="post"
										modelAttribute="concepto">
										<form:hidden path="id" value="${concepto.id}" />
										<input type="hidden" name="mes" value="${mes}" />
										<p class="center-text">¿Estás seguro de eliminar este
											registro?</p>
										<div class="row-fluid form-element" align="center">
											<button type="submit" name="button"
												class="btn btn-primary btn-sm">Eliminar</button>
											<input type="button" id="cancel" value="Cancelar"
												class="btn btn-primary btn-sm" />
										</div>
									</form:form></td>
							</tr>
						</c:forEach>
					</c:when>
		</c:choose>
					</tbody>
				</table>

				
				<nav aria-label="Page navigation">
					<form:form action="gastos" method="get" modelAttribute="concepto" name="navigation" id="navigation">
						<input type="hidden" name="mes" value="${mes}">
						<%@ include file="navigation.jsp" %>
						<span class="float-right">Total de registros: ${counterconceptos}</span>
					</form:form>
				</nav>
				
			
	</div>
	</main>
</div>

<!-- Datepicker JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>

<script src="<c:url value="/resources/js/gastos.js" />"></script>

<%@ include file="footer.jsp"%>