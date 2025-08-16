<%@page import="entidades.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cliente Editar</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="./assets/css/admin/styles.css">
<link rel="stylesheet" href="https://unpkg.com/lucide@latest/dist/lucide.min.css">
</head>
<body>


	<div class="container" >
		
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
                      <h3 class="fs-bold">Editar clientes</h3>
                  </div>
                </div>
                <br>
                <%
					Cliente cliente = (Cliente)request.getAttribute("registro");
				%>
				
				<form id="formCurso" action="cliente?opcion=registrar" method="post" >
					<input type="hidden" name="clienteId" value="<%= cliente.getClienteId() %>" />
					<div class="mb-3 row">
						<label class="col-sm-2 col-form-label">Dni</label>
						<div class="col-sm-10" >
							<input type="text" name="dni" class="form-control" value="<%= cliente.getDni() %>" />
						</div>
					</div>
					<div class="mb-3 row">
						<label class="col-sm-2 col-form-label">Nombre</label>
						<div class="col-sm-10" >
							<input type="text" name="nombre" class="form-control" value="<%= cliente.getNombre() %>" />
						</div>
					</div>
								<div class="mb-3 row">
						<label class="col-sm-2 col-form-label">Email</label>
						<div class="col-sm-10" >
							<input type="email" name="email" class="form-control" value="<%= cliente.getEmail() %>" />
						</div>
					</div>
								<div class="mb-3 row">
						<label class="col-sm-2 col-form-label">Telefono</label>
						<div class="col-sm-10" >
							<input type="text" name="telefono" class="form-control" value="<%= cliente.getTelefono() %>" />
						</div>
					</div>
								<div class="mb-3 row">
						<label class="col-sm-2 col-form-label">Direccion</label>
						<div class="col-sm-10" >
							<input type="text" name="direccion" class="form-control" value="<%= cliente.getDireccion() %>" />
						</div>
					</div>
								<div class="mb-3 row">
						<label class="col-sm-2 col-form-label">Fecha Nacimiento</label>
						<div class="col-sm-10" >
							<input type="date" name="fecha" class="form-control" value="<%= cliente.getFechaNac() %>" />
						</div>
					</div>
					
					<div class="row" >
						<button type="submit" class="btn btn-primary" >Enviar</button>
					</div>
				</form>
	
			</div>
		</div>
		
		
		
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js" ></script>
	<script type="text/javascript">
		$(() => {
			
			$("#formCurso").validate({
				rules: {
					dni: {
						required: true,
					},
					nombre: {
						required: true,
						minlength: 3
					},
					telefono: {
						required: true
					}
				},
				messages: {
					dni: {
						required: 'El campo dni es requerido'
					},
					nombre: {
						required: 'El campo nombre es requerido',
						minlength: 'El campo debe tener un mínimo de 5 caracteres'
					},
					telefono: {
						required: 'El campo telefono es requerido'
					}
				}
			});
			
		});
	</script>
	
	<script src="./assets/js/app.js"></script>
	<script src="./assets/js/active_nav.js"></script> 

     <script src="https://unpkg.com/lucide@latest"></script>
     <script>
       lucide.createIcons();
     </script>
</body>
</html>