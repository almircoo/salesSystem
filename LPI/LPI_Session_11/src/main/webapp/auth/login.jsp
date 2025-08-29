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

	<div class="row" >
		<div class="col"></div>
		<div style="width: 500px">
		
			<div class="card mt-5">
				<div class="card-body">
					<form action="auth?opcion=autenticar" method="post" >
						<div class="mb-3" >
							<label for="nombre" class="form-label" >Nombre de usuario</label>
							<input type="text" class="form-control" id="nombre" name="nombre" />
						</div>
						<div class="mb-3" >
							<label for="clave" class="form-label" >Contraseña</label>
							<input type="password" class="form-control" id="clave" name="clave" />
						</div>
						<button type="submit" class="btn btn-primary" >Ingresar</button>
					</form>
				</div>
			</div>
			
			<br />
			
			<%
				if (request.getAttribute("error") != null) {
					String error = request.getAttribute("error").toString();
			%>
			
				<div class="alert alert-danger" >
					<%= error %>
				</div>
			
			<% } %>
		
		</div>
		<div class="col"></div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
</body>
</html>