<%@page import="entidades.Docente"%>
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
		<h1>Registro de Docente</h1>
		
		<%
			Docente docente = (Docente)request.getAttribute("registro");
		%>
		
		<form id="formDocente" action="docente?opcion=registrar" method="post" >
			<input type="hidden" name="docenteId" value="<%= docente.getDocenteId() %>" />
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">DNI</label>
				<div class="col-sm-10" >
					<input type="text" name="dni" class="form-control" value="<%= docente.getDni() %>" />
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">Nombre</label>
				<div class="col-sm-10" >
					<input type="text" name="nombre" class="form-control" value="<%= docente.getNombre() %>" />
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">Especialidad</label>
				<div class="col-sm-10" >
					<input type="text" name="especialidad" class="form-control" value="<%= docente.getEspecialidad() %>" />
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
					dni: {
						required: true,
						minlength: 8,
						maxlength: 8
					},
					nombre: {
						required: true,
						minlength: 5
					},
					especialidad: {
						required: true
					}
				},
				messages: {
					dni: {
						required: 'El campo DNI es requerido',
							minlength: 'El campo DNI debe tener 8 caracteres',
							maxlength: 'El campo DNI debe tener 8 caracteres'
					},
					nombre: {
						required: 'El campo nombre es requerido',
						minlength: 'El campo debe tener un mínimo de 5 caracteres'
					},
					especialidad: {
						required: 'El campo especialidad es requerido'
					}
				}
			});
			
		});
	</script>
</body>
</html>