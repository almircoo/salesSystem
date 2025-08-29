<%@page import="entidades.Pedido"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte general</title>
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
				<!-- Agregando header de reportes -->
				<div class="mt-5 d-flex justify-content-between align-items-center">
                     <div>
                         <h3 class="fw-bold">Reporte General</h3>
                         <p class="text-muted">Resumen general del sistema</p>
                     </div>
                     <div>
                         <a href="reportes?opcion=ventas" class="btn btn-primary me-2">
                         	<i data-lucide="trending-up"></i> Reporte Ventas
                         </a>
                         <a href="reportes?opcion=clientes" class="btn btn-outline-primary">
                         	<i data-lucide="square-user"></i> Reporte Clientes
                         </a>
                         <a href="reportes?opcion=productos" class="btn btn-outline-primary">
                         	<i data-lucide="package"></i> Reporte Productos
                         </a>
                     </div>
                 </div>
                 <!-- Agregando tarjetas de estadísticas -->
				<div class="row mt-4">
					<div class="col-md-3">
						<div class="card rounded-4 text-center">
							<div class="card-body">
								<i data-lucide="shopping-cart" class="text-primary mb-2" style="width: 48px; height: 48px;"></i>
								<h4 class="fw-bold">${estadisticas.totalPedidos}</h4>
								<p class="text-muted mb-0">Total Pedidos</p>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="card rounded-4 text-center">
							<div class="card-body">
								<i data-lucide="dollar-sign" class="text-success mb-2" style="width: 48px; height: 48px;"></i>
								<h4 class="fw-bold">S/. <fmt:formatNumber value="${estadisticas.totalVentas}" pattern="#,##0.00"/></h4>
								<p class="text-muted mb-0">Total Ventas</p>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="card rounded-4 text-center">
							<div class="card-body">
								<i data-lucide="users" class="text-info mb-2" style="width: 48px; height: 48px;"></i>
								<h4 class="fw-bold">${estadisticas.totalClientes}</h4>
								<p class="text-muted mb-0">Total Clientes</p>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="card rounded-4 text-center">
							<div class="card-body">
								<i data-lucide="package" class="text-warning mb-2" style="width: 48px; height: 48px;"></i>
								<h4 class="fw-bold">${estadisticas.productosActivos}</h4>
								<p class="text-muted mb-0">Productos Activos</p>
							</div>
						</div>
					</div>
				</div>
				<!-- Agregando alertas y pedidos recientes -->
				<div class="row mt-4">
					<div class="col-md-6">
						<div class="card rounded-4">
							<div class="card-header">
								<h5 class="mb-0">Alertas del Sistema</h5>
							</div>
							<div class="card-body">
								<c:choose>
									<c:when test="${estadisticas.pedidosPendientes > 0}">
										<div class="alert alert-warning d-flex align-items-center" role="alert">
											<i data-lucide="alert-triangle" class="me-2"></i>
											<div>
												Tienes <strong>${estadisticas.pedidosPendientes}</strong> pedidos pendientes por procesar.
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="alert alert-success d-flex align-items-center" role="alert">
											<i data-lucide="check-circle" class="me-2"></i>
											<div>
												¡Excelente! No hay pedidos pendientes.
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="card rounded-4">
							<div class="card-header">
								<h5 class="mb-0">Pedidos Recientes</h5>
							</div>
							<div class="card-body">
								<c:choose>
									<c:when test="${not empty pedidosRecientes}">
										<c:forEach items="${pedidosRecientes}" var="pedido" end="4" varStatus="loop">
											<div class="d-flex justify-content-between align-items-center mb-2">
												<div>
													<strong>#${pedido.pedidoId}</strong> - ${pedido.cliente}
													<br><small class="text-muted"><fmt:formatDate value="${pedido.fechaPedido}" pattern="dd/MM HH:mm" /></small>
												</div>
												<div class="text-end">
													<span class="badge bg-primary">${pedido.estado}</span>
													<br><small>S/. <fmt:formatNumber value="${pedido.total}" pattern="#,##0.00"/></small>
												</div>
											</div>
											<c:if test="${!loop.last}">
												<hr>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<p class="text-muted">No hay pedidos recientes.</p>
									</c:otherwise>
								</c:choose>
								
							</div>
						</div>
					</div>
				</div>
				<!-- sección de graficos-->
				<div class="row mt-4">
					<div class="col-md-8">
						<div class="card rounded-4">
							<div class="card-header">
								<h5 class="mb-0">Resumen Estadístico</h5>
							</div>
							<div class="card-body">
								<canvas id="estadisticasChart" width="400" height="200"></canvas>
							</div>
						</div>
					</div>
					
					<div class="col-md-4">
						<div class="card rounded-4">
							<div class="card-header">
								<h5 class="mb-0">Estado de Pedidos</h5>
							</div>
							<div class="card-body">
								<canvas id="estadoPedidosChart" width="300" height="300"></canvas>
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
	</script>
	<script>
		
		
		// Gráfico de estadísticas generales
		const estadisticasCtx = document.getElementById('estadisticasChart').getContext('2d');
		const estadisticasChart = new Chart(estadisticasCtx, {
			type: 'bar',
			data: {
				labels: ['Pedidos', 'Clientes', 'Productos', 'Ventas (Miles)'],
				datasets: [{
					label: 'Cantidad',
					data: [
						${estadisticas.totalPedidos},
						${estadisticas.totalClientes},
						${estadisticas.productosActivos},
						<fmt:formatNumber value="${estadisticas.totalVentas / 1000}" pattern="0"/>
					],
					backgroundColor: [
						'rgba(54, 162, 235, 0.8)',
						'rgba(75, 192, 192, 0.8)',
						'rgba(255, 205, 86, 0.8)',
						'rgba(255, 99, 132, 0.8)'
					],
					borderColor: [
						'rgba(54, 162, 235, 1)',
						'rgba(75, 192, 192, 1)',
						'rgba(255, 205, 86, 1)',
						'rgba(255, 99, 132, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				responsive: true,
				plugins: {
					legend: {
						display: false
					}
				},
				scales: {
					y: {
						beginAtZero: true
					}
				}
			}
		});
		
		// Gráfico de estado de pedidos
		const estadoPedidosCtx = document.getElementById('estadoPedidosChart').getContext('2d');
		const estadoPedidosChart = new Chart(estadoPedidosCtx, {
			type: 'pie',
			data: {
				labels: ['Pendientes', 'Procesados'],
				datasets: [{
					data: [
						${estadisticas.pedidosPendientes},
						${estadisticas.totalPedidos - estadisticas.pedidosPendientes}
					],
					backgroundColor: [
						'rgba(255, 193, 7, 0.8)',
						'rgba(40, 167, 69, 0.8)'
					],
					borderColor: [
						'rgba(255, 193, 7, 1)',
						'rgba(40, 167, 69, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				responsive: true,
				plugins: {
					legend: {
						position: 'bottom'
					}
				}
			}
		});
	</script>
</body>
</html>