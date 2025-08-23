<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte ventas</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">

<link rel="stylesheet" href="./assets/css/admin/styles.css">
<link rel="stylesheet" href="https://unpkg.com/lucide@latest/dist/lucide.min.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
				
				<%
					ArrayList<Map<String, Object>> ventasPorFecha = (ArrayList<Map<String, Object>>)request.getAttribute("ventasPorFecha");
					ArrayList<Map<String, Object>> productosMasVendidos = (ArrayList<Map<String, Object>>)request.getAttribute("productosMasVendidos");
					String fechaInicio = (String)request.getAttribute("fechaInicio");
					String fechaFin = (String)request.getAttribute("fechaFin");
				%>
				
				<!-- Agregando header con filtros de fecha -->
				<div class="mt-5 d-flex justify-content-between align-items-center">
                     <div>
                         <h3 class="fw-bold">Reporte de Ventas</h3>
                     </div>
                     <div>
                         <a href="reportes" class="btn btn-secondary">
                         	<i data-lucide="arrow-left"></i> Volver
                         </a>
                     </div>
                 </div>
                 <!-- Agregando filtro de fechas -->
				<div class="card rounded-4 mt-3">
					<div class="card-body">
						<form method="GET" action="reportes" class="row g-3">
							<input type="hidden" name="opcion" value="ventas">
							<div class="col-md-4">
								<label class="form-label">Fecha Inicio</label>
								<input type="date" class="form-control" name="fechaInicio" value="<%= fechaInicio %>">
							</div>
							<div class="col-md-4">
								<label class="form-label">Fecha Fin</label>
								<input type="date" class="form-control" name="fechaFin" value="<%= fechaFin %>">
							</div>
							<div class="col-md-2">
								<label class="form-label">&nbsp;</label>
								<button type="submit" class="btn btn-primary d-block">
									<i data-lucide="filter"></i> Filtrar
								</button>
							</div>
						</form>
					</div>
				</div>
				
				<!-- Agregando tabla de ventas por fecha -->
				<div class="row mt-4">
					<!-- Reduciendo tamaño de tabla para hacer espacio al gráfico -->
					<div class="col-md-6">
						<div class="card rounded-4">
							<div class="card-header">
								<h5 class="mb-0">Ventas por Fecha</h5>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>Fecha</th>
												<th>Pedidos</th>
												<th>Total Ventas</th>
												<th>Promedio</th>
											</tr>
										</thead>
										<tbody>
										<% 
											double totalGeneral = 0;
											int totalPedidos = 0;
											if (ventasPorFecha != null) {
												for(Map<String, Object> venta : ventasPorFecha) {
													totalGeneral += (Double)venta.get("totalVentas");
													totalPedidos += (Integer)venta.get("totalPedidos");
										%>
											<tr>
												<td><%= venta.get("fecha") %></td>
												<td><%= venta.get("totalPedidos") %></td>
												<td>S/. <%= String.format("%.2f", (Double)venta.get("totalVentas")) %></td>
												<td>S/. <%= String.format("%.2f", (Double)venta.get("promedioVenta")) %></td>
											</tr>
										<% 
												}
											}
										%>
										</tbody>
										<tfoot>
											<tr class="table-active">
												<th>Total</th>
												<th><%= totalPedidos %></th>
												<th>S/. <%= String.format("%.2f", totalGeneral) %></th>
												<th>S/. <%= totalPedidos > 0 ? String.format("%.2f", totalGeneral / totalPedidos) : "0.00" %></th>
											</tr>
										</tfoot>
									</table>
								</div>
							</div>
						</div>
					</div>
					
					
					<!-- Agregando gráfico de ventas por fecha -->
					<div class="col-md-6">
						<div class="card rounded-4">
							<div class="card-header">
								<h5 class="mb-0">Gráfico de Ventas</h5>
							</div>
							<div class="card-body">
								<canvas id="ventasChart" width="400" height="200"></canvas>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row mt-4">
					<!-- Agregando productos más vendidos -->
					<div class="col-md-6">
						<div class="card rounded-4">
							<div class="card-header">
								<h5 class="mb-0">Productos Más Vendidos</h5>
							</div>
							<div class="card-body">
								<% 
									if (productosMasVendidos != null && !productosMasVendidos.isEmpty()) {
										for(Map<String, Object> producto : productosMasVendidos) {
								%>
								<div class="d-flex justify-content-between align-items-center mb-3">
									<div>
										<strong><%= producto.get("producto") %></strong>
										<br><small class="text-muted">Vendidos: <%= producto.get("totalVendido") %></small>
									</div>
									<div class="text-end">
										<small>S/. <%= String.format("%.2f", (Double)producto.get("totalIngresos")) %></small>
									</div>
								</div>
								<% 
										}
									} else {
								%>
								<p class="text-muted">No hay datos de productos vendidos en este período.</p>
								<% } %>
							</div>
						</div>
					</div>
					
					<!-- gráfico de productos top-->
					<div class="col-md-6">
						<div class="card rounded-4">
							<div class="card-header">
								<h5 class="mb-0">Top Productos</h5>
							</div>
							<div class="card-body">
								<canvas id="productosChart" width="400" height="200"></canvas>
							</div>
						</div>
					</div>
				</div>
                 
                 
			</div>
			
		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	
	<script src="https://unpkg.com/lucide@latest"></script>
	<script>
		lucide.createIcons();
		
		// ventas por fecha
		const ventasCtx = document.getElementById('ventasChart').getContext('2d');
		const ventasChart = new Chart(ventasCtx, {
			type: 'line',
			data: {
				labels: [
					<% 
						if (ventasPorFecha != null) {
							for(int i = 0; i < ventasPorFecha.size(); i++) {
								Map<String, Object> venta = ventasPorFecha.get(i);
					%>
					'<%= venta.get("fecha") %>'<%= i < ventasPorFecha.size() - 1 ? "," : "" %>
					<% 
							}
						}
					%>
				],
				datasets: [{
					label: 'Ventas (S/.)',
					data: [
						<% 
							if (ventasPorFecha != null) {
								for(int i = 0; i < ventasPorFecha.size(); i++) {
									Map<String, Object> venta = ventasPorFecha.get(i);
						%>
						<%= venta.get("totalVentas") %><%= i < ventasPorFecha.size() - 1 ? "," : "" %>
						<% 
								}
							}
						%>
					],
					borderColor: 'rgb(75, 192, 192)',
					backgroundColor: 'rgba(75, 192, 192, 0.2)',
					tension: 0.1
				}]
			},
			options: {
				responsive: true,
				plugins: {
					legend: {
						position: 'top',
					}
				},
				scales: {
					y: {
						beginAtZero: true,
						ticks: {
							callback: function(value) {
								return 'S/. ' + value.toFixed(2);
							}
						}
					}
				}
			}
		});
		
		// productos más vendidos
		const productosCtx = document.getElementById('productosChart').getContext('2d');
		const productosChart = new Chart(productosCtx, {
			type: 'doughnut',
			data: {
				labels: [
					<% 
						if (productosMasVendidos != null) {
							for(int i = 0; i < Math.min(5, productosMasVendidos.size()); i++) {
								Map<String, Object> producto = productosMasVendidos.get(i);
					%>
					'<%= producto.get("producto") %>'<%= i < Math.min(4, productosMasVendidos.size() - 1) ? "," : "" %>
					<% 
							}
						}
					%>
				],
				datasets: [{
					data: [
						<% 
							if (productosMasVendidos != null) {
								for(int i = 0; i < Math.min(5, productosMasVendidos.size()); i++) {
									Map<String, Object> producto = productosMasVendidos.get(i);
						%>
						<%= producto.get("totalVendido") %><%= i < Math.min(4, productosMasVendidos.size() - 1) ? "," : "" %>
						<% 
								}
							}
						%>
					],
					backgroundColor: [
						'rgba(255, 99, 132, 0.8)',
						'rgba(54, 162, 235, 0.8)',
						'rgba(255, 205, 86, 0.8)',
						'rgba(75, 192, 192, 0.8)',
						'rgba(153, 102, 255, 0.8)'
					],
					borderColor: [
						'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)',
						'rgba(255, 205, 86, 1)',
						'rgba(75, 192, 192, 1)',
						'rgba(153, 102, 255, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				responsive: true,
				plugins: {
					legend: {
						position: 'bottom',
					}
				}
			}
		});
	</script>
</body>
</html>