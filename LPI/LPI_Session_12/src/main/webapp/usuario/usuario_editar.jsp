<%@page import="entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body>

	<%@include file="/shared/navbar.jsp" %>

	<div class="container" >
		<h1>Registro de Usuario</h1>
		
		<%
			Usuario usuario = (Usuario)request.getAttribute("registro");
		%>
		
		<form id="formUsuario" action="usuario?opcion=registrar" method="post" >
			<input type="hidden" name="usuarioId" value="<%= usuario.getUsuarioId() %>" />
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">Nombre</label>
				<div class="col-sm-10" >
					<input type="text" name="nombre" class="form-control" value="<%= usuario.getNombre() %>" />
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">Clave</label>
				<div class="col-sm-10" >
					<input type="password" name="clave" class="form-control" value="" />
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">Rol</label>
				<div class="col-sm-10" >
					<select name="rol" class="form-select" >
						<option value="USER" <%=(usuario.getRol() == "USER" ? "selected=\"selected\"" : "") %> >
							USUARIO
						</option>
						<option value="ADMIN" <%=(usuario.getRol() == "ADMIN" ? "selected=\"selected\"" : "") %> >
							ADMINISTRADOR
						</option>
					</select>
				</div>
			</div>
			<div class="row" >
				<button type="submit" class="btn btn-primary" >Enviar</button>
			</div>
		</form>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js" ></script>
	<script type="text/javascript">
		$(() => {
			
			$("#formDocente").validate({
				rules: {
					nombre: {
						required: true,
						minlength: 5
					},
					clave: {
						required: true,
						minlength: 6
					}
				},
				messages: {
					nombre: {
						required: 'El campo nombre es requerido',
						minlength: 'El campo debe tener un mínimo de 5 caracteres'
					},
					especialidad: {
						required: 'El campo clave es requerido',
						minlength: 'El campo debe tener un mínimo de 6 caracteres'
					}
				}
			});
			
		});
	</script>
</body>
</html>