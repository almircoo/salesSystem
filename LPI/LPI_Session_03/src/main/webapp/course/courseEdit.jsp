<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
</head>
<body>

<div class="container">
	<div class="row justify-content-center">
		<div class="col-md-5">
		<H4>Registro de tablas - listas</H4>
		
		
			<form action="course?opcion=registrar" method="post">
				<div class="mb-3 row">
					<label class="col-ms-2 form-label">Codigo</label>
					<div class="col-sm-10">
						<input type="text" name="codigo" class="form-control" />
					</div>
				</div>
				<div class="mb-3 row">
					<label class="col-ms-2 form-label">Nombre</label>
					<div class="col-sm-10">
						<input type="text" name="nombres" class="form-control" />
					</div>
				</div>
				<div class="mb-3 row">
					<label class="col-ms-2 form-label">Vacante</label>
					<div class="col-sm-10">
						<input type="text" name="vacantes" class="form-control" />
					</div>
				</div>
				<!-- <div class="mb-3 row">
					<label class="col-ms-2 form-label">Codigo</label>
					<div class="col-sm-10">
						<input type="text" name="codigo" class="form-control" />
					</div>
				</div> -->
				<div class="mb-3 row">
					<button type="submit" class="btn btn-primary">Enviar</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
</body>
</html>