<%@page import="entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuario Editar</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">

<link rel="stylesheet" href="./assets/css/admin/styles.css">
<link rel="stylesheet" href="https://unpkg.com/lucide@latest/dist/lucide.min.css">
</head>
<body>


	<div class="container" >
		
		<div class="row justify-content-center mt-3">
			<!-- sidebar -->
			<div class="col-md-3">
				<%@include file="/../shared/admin/sidebar.jsp"%>
			</div>
			
			<!-- content -->
			<div class="col-md-9">
				<%@include file="/../shared/admin/navbar.jsp" %>
				<div class="mt-5 d-flex justify-content-between align-items-center flex-wrap gap-2">
                  <div>
                      <h3 class="fs-bold">Editar Usuario</h3>
                  </div>
                </div>
                <br>
                <%
                	/* Usuario usuario = (Usuario)request.getAttribute("registro"); */
				%>
				
				<div class="card rounded-4 shadow-sm mb-3">
					
					<div class="card-body">
						<form id="formUsuario" action="usuario?opcion=registrar" method="post">
						    <input type="hidden" name="usuarioId" value="${registro.usuarioId }" />
						    
						    <div class="row g-3">
						        <!-- Nombre -->
						        <div class="col-md-6">
						            <label class="form-label">Nombre</label>
						            <input type="text" name="nombre" class="form-control" value="${registro.nombre}" />
						        </div>
						        
						        <!-- Clave -->
						        <div class="col-md-6">
						            <label class="form-label">Clave</label>
						            <input type="password" name="clave" class="form-control" value="${registro.clave}" />
						        </div>
						        
						        <!-- Fecha -->
						        <div class="col-md-6">
						            <label class="form-label">Fecha</label>
						            <input type="date" name="fecha" class="form-control" value="${registro.fecha}" />
						        </div>
						        
						        <!-- Rol -->
						        <div class="col-md-6">
						            <label class="form-label">Rol</label>
						            <select name="rol" class="form-select">
						                <option value="VENDEDOR" ${usuario.rol == 'VENDEDOR' ? 'selected="selected"' : ''}>
						                    VENDEDOR
						                </option>
						                <option value="MANAGER" ${usuario.rol == 'MANAGER' ? 'selected="selected"' : ''}>
						                    MANAGER
						                </option>
						                <option value="ADMIN" ${usuario.rol == 'ADMIN' ? 'selected="selected"' : ''}>
						                    ADMINISTRADOR
						                </option>
						            </select>
						        </div>
						        
						        <!-- Botón de envío -->
						        <div class="col-12">
						            <button type="submit" class="btn btn-primary">Enviar</button>
						        </div>
						    </div>
						</form>
							
					</div>
				</div>
	
				</div>
			</div>
		</div>
		
		
		
	<!-- </div> -->

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js" ></script>
	<script type="text/javascript">
		$(() => {
			
			$("#formCurso").validate({
				rules: {
					rol: {
						required: true,
					},
					nombre: {
						required: true,
						minlength: 3
					},
					fecha: {
						required: true
					}
				},
				messages: {
					rol: {
						required: 'El campo rol es requerido'
					},
					nombre: {
						required: 'El campo nombre es requerido',
						minlength: 'El campo debe tener un mínimo de 3 caracteres'
					},
					fecha: {
						required: 'El campo fecha es requerido'
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