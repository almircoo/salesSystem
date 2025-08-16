
<div class="card rounded-4 sidebar-card-bg shadow-sm border-0">
	<div class="card-body">
		<div class="text-center">
			<h5 class="my-3">LocalStore Admin</h5>
		</div>
		<%
			String active = (String) request.getAttribute("activepage");
			
			if (active == null) active = ""; 
		
		%>
		<ul class="list-group list-group-flush">
			<li class="list-group-item fw-bold" style="border: none">
				<a href="" class="text-decoration-none text-dark" data-page="dashboard"> 
					<i data-lucide="home"></i>
					Dashboard
				</a>
			</li>
			<li class="list-group-item  fw-bold <%= "producto".equals(active) ? "active":"" %>" style="border: none">
				<a href="producto" class="text-decoration-none text-dark" data-page="producto">  
					<i data-lucide="layout-grid"></i>
					Productos
				</a>
			</li>
			<li class="list-group-item fw-bold <%= "categoria".equals(active) ? "active":"" %>" style="border: none">
				<a href="categoria" class="text-decoration-none text-dark" data-page="categoria"> 
					<i data-lucide="tags"></i>
				 	Categoria
				</a>
			</li>
			<li class="list-group-item fw-bold" style="border: none">
				<a href="pedidos" class="text-decoration-none text-dark" data-page="pedidos">
					<i data-lucide="receipt-text"></i>
					 Pedidos
				</a>
			</li>
			<li class="list-group-item fw-bold <%= "cliente".equals(active) ? "active":"" %>" style="border: none">
				<a href="cliente" class="text-decoration-none text-dark" data-page="cliente">
					<i data-lucide="users-round"></i> 
				 	Clientes
				</a>
			</li>
			
			<li class="list-group-item fw-bold <%= "reporte".equals(active) ? "active":"" %>" style="border: none">
				<a href="" class="text-decoration-none text-dark" data-page="cliente">
					<i data-lucide="users-round"></i> 
				 	Reporte
				</a>
			</li>

			<li class="list-group-item fw-bold <%= "configuracion".equals(active) ? "active":"" %>" style="border: none">
				<a href="#" class="text-decoration-none text-dark" data-page="configuracion">
					 <i data-lucide="settings"></i>
					Configuracion
				</a>
			</li>
		</ul>
		<br />
		<div class="d-grid gap-1">
			<a href="" class="btn btn-dark btn-rounded">
				Cerrar Sesión </a>
		</div>
	</div>
</div>