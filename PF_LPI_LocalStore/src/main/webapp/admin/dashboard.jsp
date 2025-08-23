<%@page import="java.util.Map"%>
<%@page import="entidades.Producto"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	xintegrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<!-- Bootstrap Icons -->
<link rel="stylesheet" href="./assets/css/admin/styles.css">
<link rel="stylesheet" href="https://unpkg.com/lucide@latest/dist/lucide.min.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center mt-3">
			<!-- sidebar -->
			<div class="col-md-3">
				<%@include file="../shared/admin/sidebar.jsp"%>
			</div>
			<!-- content -->
			<div class="col-md-9">
				<%@include file="../shared/admin/navbar.jsp" %>
				
				<div class="mt-3 d-flex">
					<div class="p-2 w-100">
						<h3 class="fw-bold">Dashboard</h3>
						<p class="text-muted"> Resumen general del sistema</p>
					</div>
				</div>
				<br/>
				
				<%
                    int totalProductos = (Integer) request.getAttribute("totalProductos");
                    int totalClientes = (Integer) request.getAttribute("totalClientes");
                    int totalCategorias = (Integer) request.getAttribute("totalCategorias");
                    int totalUsuarios = (Integer) request.getAttribute("totalUsuarios");
                    int productosStockBajo = (Integer) request.getAttribute("productosStockBajo");
                    String valorInventario = (String) request.getAttribute("valorInventario");
                    Map<String, Integer> productosPopulares = (Map<String, Integer>) request.getAttribute("productosPopulares");
                    ArrayList<Producto> productos = (ArrayList<Producto>) request.getAttribute("productos");
                %>
				
				<!-- Métricas principales -->
                <div class="row mb-4">
                    <div class="col-md-3 mb-3">
                        <div class="card rounded-4 border-0 shadow-sm">
                            <div class="card-body text-center">
                                <div class="d-flex justify-content-center align-items-center mb-2">
                                    <div class="bg-primary bg-opacity-10 rounded-circle p-3">
                                        <i data-lucide="package" class="text-primary" style="width: 24px; height: 24px;"></i>
                                    </div>
                                </div>
                                <h4 class="fw-bold mb-1"><%= totalProductos %></h4>
                                <p class="text-muted mb-0">Total Productos</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3 mb-3">
                        <div class="card rounded-4 border-0 shadow-sm">
                            <div class="card-body text-center">
                                <div class="d-flex justify-content-center align-items-center mb-2">
                                    <div class="bg-success bg-opacity-10 rounded-circle p-3">
                                        <i data-lucide="users" class="text-success" style="width: 24px; height: 24px;"></i>
                                    </div>
                                </div>
                                <h4 class="fw-bold mb-1"><%= totalClientes %></h4>
                                <p class="text-muted mb-0">Total Clientes</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3 mb-3">
                        <div class="card rounded-4 border-0 shadow-sm">
                            <div class="card-body text-center">
                                <div class="d-flex justify-content-center align-items-center mb-2">
                                    <div class="bg-warning bg-opacity-10 rounded-circle p-3">
                                        <i data-lucide="tags" class="text-warning" style="width: 24px; height: 24px;"></i>
                                    </div>
                                </div>
                                <h4 class="fw-bold mb-1"><%= totalCategorias %></h4>
                                <p class="text-muted mb-0">Total Categorías</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-3 mb-3">
                        <div class="card rounded-4 border-0 shadow-sm">
                            <div class="card-body text-center">
                                <div class="d-flex justify-content-center align-items-center mb-2">
                                    <div class="bg-warning bg-opacity-10 rounded-circle p-3">
                                        <i data-lucide="users" class="text-danger" style="width: 24px; height: 24px;"></i>
                                    </div>
                                </div>
                                <h4 class="fw-bold mb-1"><%= totalUsuarios %></h4>
                                <p class="text-muted mb-0">Total Usuarios</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3 mb-3">
                        <div class="card rounded-4 border-0 shadow-sm">
                            <div class="card-body text-center">
                                <div class="d-flex justify-content-center align-items-center mb-2">
                                    <div class="bg-info bg-opacity-10 rounded-circle p-3">
                                        <i data-lucide="dollar-sign" class="text-info" style="width: 24px; height: 24px;"></i>
                                    </div>
                                </div>
                                <h4 class="fw-bold mb-1">$<%= valorInventario %></h4>
                                <p class="text-muted mb-0">Valor Inventario</p>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Alertas y gráficos -->
                <div class="row mb-4">
                    <!-- Alertas de stock -->
                    <div class="col-md-6 mb-3">
                        <div class="card rounded-4 border-0 shadow-sm">
                            <div class="card-header bg-transparent border-0 pb-0">
                                <h5 class="fw-bold mb-0">Alertas de Stock</h5>
                            </div>
                            <div class="card-body">
                                <% if (productosStockBajo > 0) { %>
                                    <div class="alert alert-warning d-flex align-items-center" role="alert">
                                        <i data-lucide="alert-triangle" class="me-2"></i>
                                        <div>
                                            <strong><%= productosStockBajo %></strong> productos con stock bajo (menos de 5 unidades)
                                        </div>
                                    </div>
                                <% } else { %>
                                    <div class="alert alert-success d-flex align-items-center" role="alert">
                                        <i data-lucide="check-circle" class="me-2"></i>
                                        <div>Todos los productos tienen stock suficiente</div>
                                    </div>
                                <% } %>

                                <!-- Lista de productos con stock bajo -->
                                <% if (productos != null && productosStockBajo > 0) { %>
                                    <div class="mt-3">
                                        <h6 class="fw-bold">Productos que requieren restock:</h6>
                                        <div class="list-group list-group-flush">
                                            <% for (Producto p : productos) {
                                                if (p.getStock() < 5) { %>
                                                <div class="list-group-item px-0 d-flex justify-content-between align-items-center">
                                                    <div>
                                                        <strong><%= p.getNombre() %></strong>
                                                        <br><small class="text-muted">Stock actual: <%= p.getStock() %></small>
                                                    </div>
                                                    <span class="badge bg-warning">Bajo</span>
                                                </div>
                                            <% } } %>
                                        </div>
                                    </div>
                                <% } %>
                            </div>
                        </div>
                    </div>

                    <!-- Productos populares -->
                    <div class="col-md-6 mb-3">
                        <div class="card rounded-4 border-0 shadow-sm">
                            <div class="card-header bg-transparent border-0 pb-0">
                                <h5 class="fw-bold mb-0">Productos Más Vendidos</h5>
                            </div>
                            <div class="card-body">
                                <canvas id="productosChart" width="400" height="200"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- actividades recientes  -->
                <div class="row">
                	<div class="col-md-12">
                		<div class="card rounded-4 border-0 shadow-sm">
                			<div class="card-header bg-transparent border-0 pb-0">
                				<h5 class="fw-bold mb-0">Actividad Reciente</h5>
                			</div>
                			<div class="card-body">
	                               <div class="timeline">
	                                    <div class="d-flex align-items-center mb-3">
	                                        <div class="bg-primary bg-opacity-10 rounded-circle p-2 me-3">
	                                            <i data-lucide="plus" class="text-primary" style="width: 16px; height: 16px;"></i>
	                                        </div>
	                                        <div>
	                                            <p class="mb-1">Nuevo producto agregado al inventario</p>
	                                            <small class="text-muted">Hace 2 horas</small>
	                                        </div>
	                                    </div>
	                                    <div class="d-flex align-items-center mb-3">
	                                        <div class="bg-success bg-opacity-10 rounded-circle p-2 me-3">
	                                            <i data-lucide="user-plus" class="text-success" style="width: 16px; height: 16px;"></i>
	                                        </div>
	                                        <div>
	                                            <p class="mb-1">Nuevo cliente registrado</p>
	                                            <small class="text-muted">Hace 4 horas</small>
	                                        </div>
	                                    </div>
	                                    <div class="d-flex align-items-center mb-3">
	                                        <div class="bg-warning bg-opacity-10 rounded-circle p-2 me-3">
	                                            <i data-lucide="edit" class="text-warning" style="width: 16px; height: 16px;"></i>
	                                        </div>
	                                        <div>
	                                            <p class="mb-1">Información de producto actualizada</p>
	                                            <small class="text-muted">Hace 6 horas</small>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
                		</div>
                	</div>
                </div>
				
			</div>
		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	<script src="./assets/js/app.js"></script>
	<script src="./assets/js/active_nav.js"></script> 

     <script src="https://unpkg.com/lucide@latest"></script>
     <script>
       lucide.createIcons();
     </script>
     
     <script>
        // Inicializar iconos de Lucide
        lucide.createIcons();

        // Gráfico de productos populares
        const ctx = document.getElementById('productosChart').getContext('2d');
        const productosChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: [
                    <%
                    boolean first = true;
                    for (String producto : productosPopulares.keySet()) {
                        if (!first) out.print(", ");
                        out.print("'" + producto + "'");
                        first = false;
                    }
                    %>
                ],
                datasets: [{
                    data: [
                        <%
                        first = true;
                        for (Integer ventas : productosPopulares.values()) {
                            if (!first) out.print(", ");
                            out.print(ventas);
                            first = false;
                        }
                        %>
                    ],
                    backgroundColor: [
                        '#0d6efd',
                        '#198754',
                        '#ffc107',
                        '#dc3545',
                        '#6f42c1'
                    ],
                    borderWidth: 0
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'bottom',
                        labels: {
                            padding: 20,
                            usePointStyle: true
                        }
                    }
                }
            }
        });
    </script>
</body>
</html>