
<%@page import="entidades.Categoria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categoria - Editar</title>
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
				<%@include file="/shared/sidebar.jsp" %>
			</div>
			<!-- content -->
			<div class="col-md-9">
				<%@include file="/shared/navbar.jsp" %>
				
				
				<div class="mt-5 d-flex justify-content-between align-items-center flex-wrap gap-2">
                     <div>
                         <h3 class="fw-bold">Categorias</h3>
                     </div>
                     <%
						Categoria categoria = (Categoria)request.getAttribute("registro");
					%>
                 </div>
				<br>
				<br>
				<!-- product table -->
				<div class="card rounded-4">
					<div class="card-body">

						<div class="row justify-content-center">
							<div class="col-md-12">
								<form id="formCategoria" action="categoria?opcion=registrar" method="post">
									<input type="hidden" name="categoriaId" value="<%= categoria.getCategoriaId() %>" />

									<!-- Nombre -->
									<div class="mb-3">
										<label class="form-label">Nombre Categoría</label>
										 <input
											type="text" class="form-control" name="nombre" value="<%= categoria.getNombre() %>" 
											placeholder="Ingrese nombre de categoría" required>
									</div>

									<!-- Estado -->
									<div class="mb-3">
								        <p class="form-label font-medium">Estado</p>
								        <div class="flex gap-6">
								            <div class="form-check">
								                <input class="form-check-input" type="radio"
								                       name="estado" id="statusActive" value="true"
								                       <%= categoria.getEstado() ? "checked" : "" %>> 
								                <label class="form-check-label" for="statusActive"> 
								                    Activo 
								                </label>
								            </div>
								            <div class="form-check">
								                <input class="form-check-input" type="radio"
								                       name="estado" id="statusInactive" value="false"
								                       <%= !categoria.getEstado() ? "checked" : "" %>>
								                <label class="form-check-label" for="statusInactive">
								                    Desactivado 
								                </label>
								            </div>
								        </div>
								    </div>
									<div class="mb-3" >
										<button type="submit" class="btn btn-primary" >Enviar</button>
									</div>
								
							</form>
		
								
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	
		<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
		
		<script src="./assets/js/app.js"></script>
		<script src="./assets/js/active_nav.js"></script> 

     <script src="https://unpkg.com/lucide@latest"></script>
     <script>
       lucide.createIcons();
     </script>
</body>
</html>