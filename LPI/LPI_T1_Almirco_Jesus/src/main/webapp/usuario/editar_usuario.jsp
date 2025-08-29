<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuario</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body>

	<%@include file="/shared/navbar.jsp" %>

	<div class="container" >
		<h1>Registro de Usuarios</h1>
		
		<form id="formUsuario" action="usuario?opcion=registrar" method="post" >
			
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">Nombre</label>
				<div class="col-sm-10" >
					<input type="text" name="nombre" class="form-control" />
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">Apellidos</label>
				<div class="col-sm-10" >
					<input type="text" name="apellidos" class="form-control" />
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">dni</label>
				<div class="col-sm-10" >
					<input type="text" name="dni" class="form-control" maxlength="8" minlength="8"/>
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">correo</label>
				<div class="col-sm-10" >
					<input type="text" name="correo" class="form-control" max=8/>
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">rol</label>
				<div class="col-sm-10" >
					<input type="text" name="rol" class="form-control" />
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">intentos</label>
				<div class="col-sm-10" >
					<input type="text" name="intentos" class="form-control" />
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
    	
        $("#formUsuario").validate({
            rules: {
                apellidos: {
                    required: true,
                    minlength: 3
                },
                nombre: {
                    required: true,
                    minlength: 3
                },
                dni: {
                    required: true,
                    minlength: 8
                },
                correo: {
                    required: true,
                    email: true
                },
                rol: {
                    required: true
                },
                intentos: {
                    required: true,
                    min: 1,
                    max: 10
                }
            },
            messages: {
                apellidos: {
                    required: 'El campo apellido es requerido',
                    minlength: 'El campo debe tener un mínimo de 3 caracteres'
                },
                nombre: {
                    required: 'El campo nombre es requerido',
                    minlength: 'El campo debe tener un mínimo de 3 caracteres'
                },
                dni: {
                    required: 'El campo dni es requerido',
                    minlength: 'El campo debe tener 8 caracteres'
                },
                correo: {
                    required: 'El campo correo es requerido',
                    email: 'Por favor ingresa un correo electrónico válido'
                },
                rol: {
                    required: 'El campo rol es requerido'
                },
                intentos: {
                    required: 'El campo intentos es requerido',
                    min: 'El número de intentos debe ser mayor a 1',
                    max: 'El número de intentos debe ser menor a 10'
                }
            }
        });
    });
</script>
</body>
</html>