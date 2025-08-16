<%@page import="entidades.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cliente</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	xintegrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="./assets/css/admin/styles.css">
<link rel="stylesheet" href="https://unpkg.com/lucide@latest/dist/lucide.min.css">
</head>
<body>
	<!-- sidebar -->
	<div class="container">
		<div class="row justify-content-center mt-3">
			<!-- sidebar -->
			<div class="col-md-3">
				<%@include file="/shared/sidebar.jsp"%>
			</div>
			<!-- content -->
			<div class="col-md-9">
				<%@include file="/shared/navbar.jsp" %>
				<div class="mt-5 d-flex justify-content-between align-items-center flex-wrap gap-2">
                  <div>
                      <h3 class="fs-bold">Clientes</h3>
                  </div>
                  
                  <div class="flex-shrink-0">
                        <a href="cliente?opcion=editar" class="btn btn-secondary text-nowrap"> Agregar cliente</a>
                    </div>
                </div>
                <br>
                <%
					ArrayList<Cliente> lista = (ArrayList<Cliente>) request.getAttribute("lista");
					String texto = "";
					if (request.getParameter("texto") != null) {
						texto = request.getParameter("texto");
					}
					%>
                <div class="col-md-12">
                   <form class="d-flex " id="formBuscar" action="cliente" method="get">
                       <input type="text" name="texto" class="form-control" placeholder="Buscar..." value="<%=texto%>" />
                   </form>
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
												<th scope="col">DNI</th>
												<th scope="col">Nombre</th>
												<th scope="col">E-mail</th>
												<th scope="col">Telefono</th>
												<th scope="col">Direccion</th>
												<th scope="col">Fecha Nacimiento</th>
												<th scope="col">Acciones</th>
											</tr>
										</thead>
										<tbody>
											<%
											for (Cliente c : lista) {
											%>
											<tr>
												<th scope="row"><%=c.getDni()%></th>
												<td><%=c.getNombre()%></td>
												<td><%=c.getEmail()%></td>
												<td><%=c.getTelefono()%></td>
												<td><%=c.getDireccion()%></td>
												<td><%=c.getFechaNac()%></td>
												<td>
													<div class="d-flex justify-content-start">
														<a class="btn btn-sm btn-primary"
															href="cliente?opcion=editar&id=<%=c.getClienteId()%>"><i
															class="bi bi-pencil-fill"></i> Editar</a> <a
															class="btn btn-sm btn-danger"
															href="javascript:eliminar(<%=c.getClienteId()%>)"><i
															class="bi bi-x-circle"></i> Eliminar</a>
													</div>
												</td>
											</tr>
											<%
											}
											%>
										</tbody>
									</table>
								</div>
								<form id="formEliminar" action="cliente?opcion=eliminar"
									method="post">
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
			const respuesta = confirm('¿Desea eliminar la Cliente?');
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