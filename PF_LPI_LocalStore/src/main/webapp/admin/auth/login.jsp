<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<link rel="" href="./assets/css/admin/styles.css" >
<link rel="stylesheet" href="https://unpkg.com/lucide@latest/dist/lucide.min.css">
</head>
<body>
	<div class="container" >
		<div class="row justify-content-center mt-5 py-5">
            <div class="col-md-4">
                <div class="card rounded-4" style="border-color: #CEDBE8; background-color: #F8FAFC; color: #49739C;">
                    <div class="card-body">
                        <h4 class="text-center mb-3">Admin Login</h4>

                        <form action="auth?opcion=autenticar" method="POST">
                            <div class="mb-3">
                                <label for="email" class="form-label">Usuario</label>
                                <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Nombre" />
                            </div>

                            <div class="mb-3">
                                <label for="password" class="form-label">Contraseña</label>
                                <input type="password" id="clave" name="clave" class="form-control" placeholder="Contraseña" />
                            </div>

                            <p class="text-center mt-3">
                                <a href="" class="text-decoration-none text-secondary">
                                ¿Olvidaste tu contraseña?
                                </a>
                            </p>
                            <div class="d-grid gap-2">
                                <button class="btn btn-dark btn-rounded" type="submit">
                                    Ingresar
                                </button>
                            </div>
                        </form>
                    </div>
                    <%
						if (request.getAttribute("error") != null) {
							String error = request.getAttribute("error").toString();
					%>
			
					<div class="alert alert-danger" >
						<%= error %>
					</div>
				
					<% } %>
		
                </div>
            </div>
        </div>
		
	</div>
	
	

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	<script src="./assets/js/app.js"></script>

     <script src="https://unpkg.com/lucide@latest"></script>
     <script>
       lucide.createIcons();
     </script>
</body>
</html>