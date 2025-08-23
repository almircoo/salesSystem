<%@page import="entidades.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categoria</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	xintegrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<!-- Bootstrap Icons -->
<link rel="stylesheet" href="./assets/css/admin/styles.css">
	<link rel="stylesheet" href="https://unpkg.com/lucide@latest/dist/lucide.min.css">
</head>
<body>
	<!-- sidebar -->
	<div class="container">
		<div class="row justify-content-center mt-3">
			<!-- sidebar -->
			<div class="col-md-3">
				<%@include file="/../shared/admin/sidebar.jsp" %>
			</div>
			<!-- content -->
			<div class="col-md-9">
				<%@include file="/../shared/admin/navbar.jsp" %>
				
				<div class="mt-5 d-flex justify-content-between align-items-center flex-wrap gap-2">
                     <div>
                         <h3 class="fw-bold">Categorias</h3>
                     </div>
                     <%
						/* ArrayList<Categoria> lista = (ArrayList<Categoria>)request.getAttribute("lista"); */
					%>
                     <div class="flex-shrink-0">
                     	<c:if test="${usuario.rol == 'ADMIN'}">
                     		<a href="categoria?opcion=editar" class="btn btn-secondary text-nowrap"><i data-lucide="plus"></i> Crear Categoria</a>
                     	</c:if>
                    
                     </div>
                 </div>
				<br>
				<!-- product table -->
				<div class="card rounded-4">
					<div class="card-body">

						<div class="row justify-content-center">
							<div class="col-md-12">
								<div class="table-responsive ">
									<table class="table table-hover ">
										<thead>
											<tr>
												<th scope="col">Id</th>
												<th scope="col">Nombre</th>
												<th scope="col">Estatus</th>
												<th scope="col">Acciones</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="c" items="${ lista }">
												<tr>
													<th scope="row">${c.categoriaId}</th>
													<td>${ c.nombre }</td>
													<td>
														<c:choose>
															<c:when test="c.estado">
																<span class="badge order-status-delivered">Activado</span>
															</c:when>
															<c:otherwise>
																<span class="badge danger-status">Desactivado</span>
															</c:otherwise>
														</c:choose>
													</td>
													<td>
														<div class="d-flex justify-content-start">
															<c:if test="${usuario.rol == 'ADMIN' or usuario.rol == 'MANAGER'}" >
																<a class="btn btn-sm btn-dark" href="categoria?opcion=editar&id=${c.categoriaId}"><i data-lucide="square-pen"></i>Editar</a>
															</c:if>
															<c:if test="${usuario.rol == 'ADMIN'}">
																<a class="btn btn-sm btn-danger" href="javascript:eliminar(${c.categoriaId})">
																	<i data-lucide="trash-2"></i> Eliminar</a>
															</c:if>
														
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
									<form id="formEliminar" action="categoria?opcion=eliminar" method="post">
										<input type="hidden" name="id" />
									</form>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- content -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
		
	<script type="text/javascript">
		const eliminar = (id) => {
			const respuesta = confirm('¿Desea eliminar la Categoria?');
			if (respuesta) {
				document.querySelector('#formEliminar input[name=id]').value = id;
				document.getElementById('formEliminar').submit();
			}
		};
	</script>
	
	<script src="./assets/js/app.js"></script>
	<script src="./assets/js/active_nav.js"></script> 

     <script src="https://unpkg.com/lucide@latest"></script>
     <script>
       lucide.createIcons();
     </script>
</body>
</html>