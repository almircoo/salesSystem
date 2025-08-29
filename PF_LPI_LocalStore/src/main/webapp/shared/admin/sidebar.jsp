<%@page import="entidades.Usuario" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	/* HttpSession se = request.getSession();
	Usuario user = new Usuario();
	if (se.getAttribute("usuario") != null)
		user = (Usuario)se.getAttribute("usuario");
	
	String rol = user.getRol(); */
%>
<div class="card rounded-4 sidebar-card-bg shadow-sm border-0">
	<div class="card-body">
		<div class="text-center">
			<h5 class="my-3">LocalStore Admin</h5>
			<!-- Indicador de rol -->
		     <c:if test="${usuario.rol != null}">
				  <span class="badge ${usuario.rol == 'ADMIN' ? 'bg-danger' : 
				                      (usuario.rol == 'MANAGER' ? 'bg-warning' : 'bg-info')} mb-2">
				    ${usuario.rol}
				  </span>
			</c:if>
		</div>
		<%
			/* String active = (String) request.getAttribute("activepage");
			
			if (active == null) active = "";  */
		
		%>
		<ul class="list-group list-group-flush">
			<li class="list-group-item fw-bold ${activepage == 'dashboard' ? 'active':''}" style="border: none">
				<a href="dashboard" class="text-decoration-none text-dark d-flex align-items-center" data-page="dashboard"> 
					<i data-lucide="home" class="me-2"></i>
					Dashboard
				</a>
			</li>
			<li class="list-group-item  fw-bold ${activepage == 'producto' ? 'active':''}" style="border: none">
				<a href="producto" class="text-decoration-none text-dark d-flex align-items-center" data-page="producto">  
					<i data-lucide="layout-grid" class="me-2"></i>
					Productos
				</a>
			</li>
			<li class="list-group-item fw-bold ${activepage == 'categoria' ? 'active':''}" style="border: none">
				<a href="categoria" class="text-decoration-none text-dark d-flex align-items-center" data-page="categoria"> 
					<i data-lucide="tags" class="me-2"></i>
				 	Categorias
				</a>
			</li>
			
			<li class="list-group-item fw-bold ${activepage == 'pedidos' ? 'active':''}" style="border: none">
				<a href="pedidos" class="text-decoration-none text-dark d-flex align-items-center" data-page="pedidos">
					<i data-lucide="receipt-text" class="me-2"></i>
					 Ventas
				</a>
			</li>
			<!--  -->
			<li class="list-group-item fw-bold ${activepage == 'cliente' ? 'active':''}" style="border: none">
				<a href="cliente" class="text-decoration-none text-dark d-flex align-items-center" data-page="cliente">
					<i data-lucide="users-round" class="me-2"></i> 
				 	Clientes
				</a>
			</li>
			<!-- todos solo lectura -->
			<li class="list-group-item fw-bold ${activepage == 'reportes' ? 'active':''}" style="border: none">
				<a href="reportes" class="text-decoration-none text-dark d-flex align-items-center" data-page="cliente">
					<i data-lucide="bar-chart-3" class="me-2"></i> 
				 	Reportes
				</a>
			</li>
			
			<!-- Solo ADMIN y MANAGER, MANAGER solo lectura -->
			<c:if test="${usuario.rol == 'ADMIN' || usuario.rol == 'MANAGER'}">
				<li class="list-group-item fw-bold ${activepage == 'usuario' ? 'active':''}" style="border: none">
		            <a href="usuario" class="text-decoration-none text-dark d-flex align-items-center" data-page="usuario">
		              <i data-lucide="user-cog" class="me-2"></i> 
		              Usuarios
		              <c:if test="${usuario.rol == 'MANAGER'}">
							<small class="ms-auto text-muted">(Solo lectura)</small>
					  </c:if>
		            </a>
	          	</li>
			</c:if>
			
			<!-- Solo ADMIN tiene acceso -->
			<c:if test="${usuario.rol == 'ADMIN'}">
				<li class="list-group-item fw-bold" style="border: none">
					<a href="#" class="text-decoration-none text-dark d-flex align-items-center" data-page="configuracion">
						 <i data-lucide="settings" class="me-2"></i>
						Configuracion
					</a>
				</li>
			</c:if>
		</ul>
		
		<br />
		<div class="d-grid gap-1">
			<a href="auth?opcion=logout" class="btn btn-dark text-center btn-rounded d-flex align-items-center">
				<i data-lucide="log-out"></i> Cerrar Sesion 
			</a>
		</div>
	</div>
</div>