<%@ include file="header.jsp"%>

<fmt:setLocale value="es_ES" />

<div class="row main-row fill">
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
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3">
		<h1 class="h2">Empleados</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
			<div class="btn-group mr-2">
				<button id="addButton" class="btn btn-sm btn-outline-secondary"
					data-toggle="tooltip" data-placement="top" title="AÃ±adir">
					<span class="fas fa-plus"></span>
				</button>
			</div>
		</div>
	</div>

	<div class="table-responsive">
		<c:choose>
			<c:when test="${resultado eq 0}">
				<div class="alert alert-success" role="alert">
					<i class="fas fa-check"></i> Acción realizada con éxito
				</div>
			</c:when>
			<c:otherwise>
				<c:if test="${counterempleados ne 0}">
					<c:if test="${resultado eq '-1'}">
						<div class="alert alert-danger" role="alert">
							<i class="fas fa-exclamation-triangle"></i> Error interno
						</div>
					</c:if>
					<c:if test="${resultado eq '-2'}">
						<div class="alert alert-danger" role="alert">
							<i class="fas fa-exclamation-triangle"></i> Error! El D.N.I.
							introducido ya existe
						</div>
					</c:if>
				</c:if>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${counterempleados eq 0}">
				<c:choose>
					<c:when test="${counterclientes eq 0}">
						<div class="alert alert-warning" role="alert">
							<i class="fas fa-exclamation-triangle"></i> No existen clientes.
							<a href="clientes.html">Añadir Clientes</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-warning" role="alert">
							<i class="fas fa-exclamation-triangle"></i> No hay empleados para
							mostrar
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			</c:choose>
			
			<table class="table table-striped table-sm">
					<thead>
						<tr>
							<th class="cabecera">
								<div class="row ml-0">Nombre 
									<form action="empleados" method="get">
										<input type="hidden" value="nombre" name="columna"/>
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
																<i class="fas fa-sort-up"></i>
															</button>
														</c:otherwise>
													</c:choose>
												</c:when>
											<c:otherwise>
												<input type="hidden" value="ASC" name="orden">
												<button type="submit" class="btn btn-link">
													<span class="orden-control"> <i
														class="fas fa-sort-up"></i> <i class="fas fa-sort-down"></i>
													</span>
												</button>
											</c:otherwise>
										</c:choose>
									</form>
								</div>
							</th>
							<th>
								<div class="row">D.N.I.
									<form action="empleados" method="get">
										<input type="hidden" value="dni" name="columna"/>
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
								</div>
							</th>
							<th>
							<div class="row">Cliente
									<form action="empleados" method="get">
										<input type="hidden" value="cliente" name="columna"/>
											<c:choose>
												<c:when test="${columna eq 'cliente' }">
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
								</div>
							</th>
							<th>
								<div class="row">Guardia
									<form action="empleados" method="get">
										<input type="hidden" value="precioguardia" name="columna"/>
											<c:choose>
												<c:when test="${columna eq 'precioguardia' }">
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
								</div>
							</th>
							<th>
								<div class="row">Hora Extra 
									<form action="empleados" method="get">
										<input type="hidden" value="preciohoraextra" name="columna"/>
											<c:choose>
												<c:when test="${columna eq 'preciohoraextra' }">
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
								</div>
							</th>
							<th>
								<div class="row">Freelance
									<form action="empleados" method="get">
										<input type="hidden" value="freelance" name="columna"/>
											<c:choose>
												<c:when test="${columna eq 'freelance' }">
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
								</div>
							</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr class="add-row">
							<form:form action="saveEmpleado" method="post" modelAttribute="empleado">
								<td>
									<label class="sr-only" for="inlineFormInput">Nombre</label>
									<input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" name="nombre" placeholder="Nombre" >
									<label class="sr-only" for="inlineFormInput">Apellido</label>
  									<input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" name="apellidos" placeholder="apellidos">
								</td>
								<td>
									<form:input type="text" path="dni" required="true" />
								</td>
								<td>
									<form:select path="cliente">
										<c:forEach var="cliente" items="${listClientes}">
											<form:option value="${cliente.id}" label="${cliente.nombre}" />
										</c:forEach>
									</form:select>
								</td>
								<td>
									<fmt:formatNumber value="0" type="currency" var="fprecioguardia" />
									<form:input type="currency" path="precioguardia"
										value="${fprecioguardia}" />
								</td>
								<td>
									<fmt:formatNumber value="0" type="currency" var="fpreciohoraextra" />
									<form:input type="currency" path="preciohoraextra"
										value="${fpreciohoraextra}" />
								</td>
								<td class="text-center"><label class="form-check-label">
											<form:checkbox class="form-check-input" path="freelance" />
									</label>
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
			<c:when test="${counterempleados ne 0}">
						<c:forEach var="empleado" items="${listEmpleado}">
							<tr class="view-row">
								<td>${empleado.nombre}</td>
								<td>${empleado.dni}</td>
								<td>${empleado.nCliente}</td>
								<td class="center-text"><fmt:formatNumber
										value="${empleado.precioguardia}" type="currency"
										currencySymbol="&euro;" maxFractionDigits="2" /></td>
								<td class="center-text"><fmt:formatNumber
										value="${empleado.preciohoraextra}" type="currency"
										currencySymbol="&euro;" maxFractionDigits="2" /></td>
								<td class="center-text"><c:if
										test="${empleado.freelance eq true}">
										<input class="form-check-input" type="checkbox" disabled
											checked />
									</c:if> <c:if test="${empleado.freelance eq false}">
										<input class="form-check-input" type="checkbox" disabled />
									</c:if></td>
								<td>
									<button class="btn btn-sm btn-outline-secondary update-button"
										data-toggle="tooltip" data-placement="top" title="Editar">
										<span class="fas fa-edit"></span>
									</button>
									<button class="btn btn-sm btn-outline-secondary delete-button"
										data-toggle="tooltip" data-placement="top" title="Borrar">
										<span class="far fa-trash-alt"></span>
									</button>
								</td>
							</tr>

							<tr class="edit-row">
								<form:form action="editEmpleado" method="post"
									modelAttribute="empleado">
									<input type="hidden" name="dniantiguo" value="${empleado.dni}" />
									<td><form:input type="text" value="${empleado.nombre}"
											path="nombre" required="true" /></td>
									<td><form:input type="text" value="${empleado.dni}"
											path="dni" required="true" /></td>
									<td><form:select path="cliente">
											<c:forEach var="cliente" items="${listClientes}">
												<c:choose>
													<c:when test="${empleado.cliente eq cliente.id}">
														<form:option value="${cliente.id}"
															label="${cliente.nombre}" selected="true" />
													</c:when>
													<c:otherwise>
														<form:option value="${cliente.id}"
															label="${cliente.nombre}" />
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>
									<td class="precio"><form:input type="number"
											value="${empleado.precioguardia}" path="precioguardia" /></td>
									<td class="precio"><form:input type="number"
											value="${empleado.preciohoraextra}" path="preciohoraextra" /></td>
									<td class="text-center"><label class="form-check-label">
											<form:checkbox class="form-check-input" path="freelance" />
									</label></td>
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
										action="deleteEmpleado" method="post"
										modelAttribute="empleado">
										<form:hidden path="dni" value="${empleado.dni}" />
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

				<nav aria-label="Page navigation example">
					<form:form action="empleados" method="get" modelAttribute="empleado" name="navigation">
						<%@ include file="navigation.jsp" %>
						<span class="float-right">Total de registros: ${counterempleados}</span>
					</form:form>
				</nav>
	</div>
	</main>
</div>

<script src="<c:url value="/resources/js/empleados.js" />"></script>

<%@ include file="footer.jsp"%>