<%@page import="entidades.Pedido"%>
<%@page import="entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de pedidos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">

<link rel="stylesheet" href="./assets/css/admin/styles.css">
<link rel="stylesheet" href="https://unpkg.com/lucide@latest/dist/lucide.min.css">
</head>
<body>
	<div class="container" >
		
		<div class="row justify-content-center mt-3">
			<!-- sidebar -->
			<div class="col-md-3">
				<%@include file="/../shared/admin/sidebar.jsp"%>
			</div>
			
			<!-- content -->
			<div class="col-md-9">
				<!-- Listar pedido aqui -->
				<%@include file="/../shared/admin/navbar.jsp" %>
				<%
					/* ArrayList<Pedido> lista = (ArrayList<Pedido>)request.getAttribute("lista");
					HttpSession miSession = request.getSession(); 
					Usuario userRole = new Usuario();
					if (miSession .getAttribute("usuario") != null) userRole = (Usuario) miSession.getAttribute("usuario");
					String miRol = userRole.getRol(); */
				%>
				
				<!-- Agregando header con título y filtros -->
				<div class="mt-5 d-flex justify-content-between align-items-center flex-wrap gap-2">
                     <div>
                         <h3 class="fw-bold">Gestión de Pedidos</h3>
                     </div>
                     <div class="flex-shrink-0">
	                     <c:if test="${usuario.rol == 'ADMIN' }">
	                     	<a href="pedidos?opcion=editar" class="btn btn-secondary text-nowrap">
	                         	<i data-lucide="plus"></i> Nuevo Pedido
	                         </a>
	                     </c:if> 
                     </div>
                 </div>
                 
                 <!-- aqui filtros de búsqueda -->
				<div class="card rounded-4 mt-3">
					<div class="card-body">
						<form method="GET" action="pedidos" class="row g-3">
							<div class="col-md-4">
								<label class="form-label">Buscar</label>
								<input type="text" class="form-control" name="texto" placeholder="Cliente, ID pedido..." value="${param.texto != null ? param.texto : ''}">
							</div>
							<div class="col-md-3">
								<label class="form-label">Estado</label>
								<select class="form-select" name="estado">
									<option value="">Todos los estados</option>
									<option value="PENDIENTE" ${param.estado == 'PENDIENTE' ? 'selected' : ''}>Pendiente</option>
									<option value="PROCESANDO" ${param.estado == 'PROCESANDO' ? 'selected' : ''}>Procesando</option>
									<option value="EN_CAMINO" ${param.estado == 'EN_CAMINO' ? 'selected' : ''}>En Camino</option>
									<option value="ENTREGADO" ${param.estado == 'ENTREGADO' ? 'selected' : ''}>Entregado</option>
									<option value="CANCELADO" ${param.estado == 'CANCELADO' ? 'selected' : ''}>Cancelado</option>
								</select>
							</div>
							<div class="col-md-2">
								<label class="form-label">&nbsp;</label>
								<button type="submit" class="btn btn-primary d-block">
									<i data-lucide="search"></i> Buscar
								</button>
							</div>
						</form>
					</div>
				</div>
				
				<!-- aqui tabla de pedidos con funcionalidad completa -->
				<div class="card rounded-4 mt-3">
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th scope="col">ID</th>
										<th scope="col">Cliente</th>
										<th scope="col">Fecha</th>
										<th scope="col">Estado</th>
										<th scope="col">Total</th>
										<th scope="col">Acciones</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${lista}" var="p">
										<tr>
											<th scope="row"># ${p.pedidoId}</th>
											<td>${p.cliente}</td>
											<td><fmt:formatDate value="${p.fechaPedido}" pattern="dd/MM/yyyy HH:mm"/></td>
											<td>
												<c:choose>
													<c:when test="${p.estado == 'PENDIENTE'}"><span class="badge bg-warning">${p.estado}</span></c:when>
													<c:when test="${p.estado == 'PROCESANDO'}"><span class="badge bg-info">${p.estado}</span></c:when>
													<c:when test="${p.estado == 'EN_CAMINO'}"><span class="badge bg-primary">${p.estado}</span></c:when>
													<c:when test="${p.estado == 'ENTREGADO'}"><span class="badge order-status-delivered">${p.estado}</span></c:when>
													<c:when test="${p.estado == 'CANCELADO'}"><span class="badge bg-danger">${p.estado}</span></c:when>
													<c:otherwise><span class="badge bg-secondary">${p.estado}</span></c:otherwise>
												</c:choose>
											</td>
											<td>S/. <fmt:formatNumber value="${p.total}" type="number" minFractionDigits="2"/></td>
											<td>
												<div class="d-flex gap-1">
													<a class="btn btn-sm btn-outline-primary" href="pedidos?opcion=detalles&id=${p.pedidoId }">
														<i data-lucide="eye"></i> Ver
													</a>
													<%-- <% if ("ADMIN".equals(rol) || "MANAGER".equals(rol)) { %>
													<a class="btn btn-sm btn-dark" href="pedidos?opcion=editar&id=<%=p.getPedidoId()%>">
														<i data-lucide="edit"></i> Editar
													</a>
													<% } %> --%>
													<c:if test="${(usuario.rol == 'ADMIN' or usuario.rol == 'MANAGER') and (p.estado != 'ENTREGADO' and p.estado != 'CANCELADO')}">
														<div class="dropdown">
															<button class="btn btn-sm btn-success dropdown-toggle" type="button" data-bs-toggle="dropdown">
																<i data-lucide="refresh-cw"></i> Estado
															</button>
															<ul class="dropdown-menu">
																<li><a class="dropdown-item" href="javascript:cambiarEstado(${p.pedidoId}, 'PENDIENTE')">Pendiente</a></li>
																<li><a class="dropdown-item" href="javascript:cambiarEstado(${p.pedidoId}, 'PROCESANDO')">Procesando</a></li>
																<li><a class="dropdown-item" href="javascript:cambiarEstado(${p.pedidoId}, 'EN_CAMINO')">En Camino</a></li>
																<li><a class="dropdown-item" href="javascript:cambiarEstado(${p.pedidoId}, 'ENTREGADO')">Entregado</a></li>
																<li><hr class="dropdown-divider"></li>
																<li><a class="dropdown-item text-danger" href="javascript:cambiarEstado(${p.pedidoId}, 'CANCELADO')">Cancelar</a></li>
															</ul>
														</div>
													</c:if>
													<c:if test="${ usuario.rol == 'ADMIN' }">
														<a class="btn btn-sm btn-danger" href="javascript:eliminar(${p.pedidoId })">
															<i data-lucide="trash-2"></i> Eliminar
														</a>
													</c:if>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						
						<!-- Aqui formularios ocultos para acciones -->
						<form id="formEliminar" action="pedidos?opcion=eliminar" method="post">
							<input type="hidden" name="id" />
						</form>
						
						<form id="formCambiarEstado" action="pedidos?opcion=cambiar_estado" method="post">
							<input type="hidden" name="id" />
							<input type="hidden" name="nuevoEstado" />
						</form> 
					</div>
				</div>
				
			</div>
			
		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	
	<!-- scripts para funcionalidad -->
	<script type="text/javascript">
		const eliminar = (id) => {
			const respuesta = confirm('¿Desea eliminar este pedido? Esta acción no se puede deshacer.');
			if (respuesta) {
				document.querySelector('#formEliminar input[name=id]').value = id;
				document.getElementById('formEliminar').submit();
			}
		};
		
		const cambiarEstado = (id, nuevoEstado) => {
			const respuesta = confirm('¿Desea cambiar el estado de este pedido?');
			if (respuesta) {
				document.querySelector('#formCambiarEstado input[name=id]').value = id;
				document.querySelector('#formCambiarEstado input[name=nuevoEstado]').value = nuevoEstado;
				document.getElementById('formCambiarEstado').submit();
			}
		};
	</script>
	
	<script src="https://unpkg.com/lucide@latest"></script>
	<script>
		lucide.createIcons();
	</script>
</body>
</html>