<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Categoria"%>
<%@page import="entidades.Producto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Producto - Editar</title>
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
				<div class="mt-5 d-flex">
					<div class="p-2 w-100">
						<b>Producto</b>
					</div>
					<%
					Producto producto = (Producto) request.getAttribute("registro");
					ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("listaCategorias");
					%>
				</div>
				<br>
				<!-- product table -->
				<div class="card rounded-4">
					<div class="card-body">

						<div class="row justify-content-center">
							<div class="col-md-12">
								<form id="formProducto" action="producto?opcion=registrar"
									method="post">
									<input type="hidden" name="productoId"
										value="<%=producto.getProductoId()%>" />

									<!-- Nombre -->
									<div class="mb-2">
										<label for="productName" class="form-label font-medium">Nombre
											Producto</label> <input type="text" class="form-control rounded-lg"
											name="nombre" value="<%=producto.getNombre()%>"
											placeholder="Ingrese Nombre Producto" required>
									</div>
									<div class="mb-2">
										<label for="stockQuantity" class="form-label font-medium">Cantidad
											Stock</label> <input type="number" class="form-control rounded-lg"
											name="stock" value="<%=producto.getStock()%>"
											placeholder="Ingrese cantidad stock" required>
									</div>
									<div class="mb-2">
										<label for="price" class="form-label  font-medium">Precio</label>
										<input type="number" step="0.01"
											class="form-control rounded-lg" name="precio"
											value="<%=producto.getPrecio()%>"
											placeholder="Ingresar precio" required>
									</div>
									<div class="mb-2">
										<label for="category" class="form-label font-medium">Categoria</label>
										<select class="form-select rounded-lg" name="categoria" required>
											<%
											for (Categoria c : categorias) {
											%>
											<option value="<%=c.getCategoriaId()%>"
												<%=(c.getCategoriaId() == producto.getCategoriaId() ? "selected=\"selected\"" : "")%>>
												<%=c.getNombre()%>
											</option>
											<%
											}
											%>
										</select>
									</div>

									<!-- Estado -->
									<div class="mb-3">
										<p class="form-label font-medium">Estado</p>
										<div class="flex gap-6">
											<div class="form-check">
												<input class="form-check-input" type="radio" name="estado"
													id="statusActive" value="true"
													<%=producto.isEstado() ? "checked" : ""%>> <label
													class="form-check-label" for="statusActive"> Activo
												</label>
											</div>
											<div class="form-check">
												<input class="form-check-input" type="radio" name="estado"
													id="statusInactive" value="false"
													<%=!producto.isEstado() ? "checked" : ""%>> <label
													class="form-check-label" for="statusInactive">
													Desactivado </label>
											</div>
										</div>
									</div>
									<div class="mb-3">
										<button type="submit" class="btn btn-primary">Enviar</button>
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

	<script type="text/javascript">
		$(() => {
			
			$("#formProducto").validate({
				rules: {
					nombre: {
						required: true,
						minlength: 3
					},
					stock: {
						required: true
					},
					precio: {
						requerid: true
					}
				},
				messages: {
					nombre: {
						required: 'El campo nombre es requerido',
						minlength: 'El campo debe tener un mínimo de 3 caracteres'
					},
					stock: {
						required: 'El campo stock es requerido'
					},
					precio: {
						required: 'El campo precio es requerido'
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