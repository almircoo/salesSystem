<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte productos</title>
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
				<div class="mt-5 d-flex justify-content-between align-items-center">
                    <div>
                    	<h3 class="fw-bold">Reporte de prodcutos</h3>
                    </div>
                    <div>
                         <a href="reportes" class="btn btn-secondary">
                         	<i data-lucide="arrow-left"></i> Volver
                         </a>
                     </div>
                </div>
                <br>
                
                <%
				   /*  List<Map<String, Object>> productosStockBajo = (List<Map<String, Object>>) request.getAttribute("productosStockBajo"); */
				%>
                <!-- Productos con Stock Bajo -->
                <div class="row mb-4">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header bg-warning text-dark">
                                <h5 class="card-title mb-0">
                                    <i class="fas fa-exclamation-triangle me-2"></i>
                                    Productos con Stock Bajo (Menos de 5 unidades)
                                </h5>
                            </div>
                            <div class="card-body">
                            	<c:choose>
                                    <c:when test="${not empty productosStockBajo != null}">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover">
                                                <thead class="table-secondary">
                                                    <tr>
                                                        <th class="text-dark">Producto</th>
                                                        <th class="text-dark">Categoría</th>
                                                        <th class="text-dark">Stock Actual</th>
                                                        <th class="text-dark">Precio</th>
                                                        <th class="text-dark">Estado</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="producto" items="${productosStockBajo}">
                                                        <tr>
                                                            <td>${producto.nombre}</td>
                                                            <td>${producto.categoria}</td>
                                                            <td>
                                                                <span class="badge bg-danger">
                                                                    ${producto.stock} unidades
                                                                </span>
                                                            </td>
                                                            <td>S/ <fmt:formatNumber value="${producto.precio}" pattern="#,##0.00"/></td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${producto.stock == 0}">
                                                                        <span class="badge bg-danger">Sin Stock</span>
                                                                    </c:when>
                                                                    <c:when test="${producto.stock <= 2}">
                                                                        <span class="badge bg-danger">Crítico</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="badge bg-warning">Bajo</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="alert alert-success" role="alert">
                                            <i class="fas fa-check-circle me-2"></i>
                                            Excelente! Todos los productos tienen stock suficiente.
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- graficos -->
                <c:if test="${not empty productosStockBajo}">
                <div class="row">
                    <!-- Stock por Producto -->
                    <div class="col-md-6 mb-4">
                        <div class="card">
                            <div class="card-header">
                                <h5 class="card-title mb-0">
                                    <i class="fas fa-chart-bar me-2"></i>
                                    Stock Actual por Producto
                                </h5>
                            </div>
                            <div class="card-body">
                                <canvas id="stockChart" width="400" height="300"></canvas>
                            </div>
                        </div>
                    </div>

                    <!-- por Categoría -->
                    <div class="col-md-6 mb-4">
                        <div class="card">
                            <div class="card-header">
                                <h5 class="card-title mb-0">
                                    <i class="fas fa-chart-pie me-2"></i>
                                    Productos con Stock Bajo por Categoría
                                </h5>
                            </div>
                            <div class="card-body">
                                <canvas id="categoriaChart" width="400" height="300"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Resumen de estadísticas -->
                <div class="row">
                    <div class="col-md-3 mb-3">
                        <div class="card bg-danger text-white">
                            <div class="card-body">
                                <div class="d-flex justify-content-between">
                                    <div>
                                        <h4>${fn:length(productosStockBajo)}</h4>
                                        <p class="mb-0">Productos con Stock Bajo</p>
                                    </div>
                                    <div class="align-self-center">
                                        <i class="fas fa-exclamation-triangle fa-2x"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-3 mb-3">
                        <div class="card bg-dark text-white">
                            <div class="card-body">
                                <div class="d-flex justify-content-between">
                                    <div>
                                        <h4>
                                            <c:set var="sinStock" value="0" />
                                            <c:forEach var="producto" items="${productosStockBajo}">
                                                <c:if test="${producto.stock == 0}">
                                                    <c:set var="sinStock" value="${sinStock + 1}" />
                                                </c:if>
                                            </c:forEach>
                                            ${sinStock}
                                        </h4>
                                        <p class="mb-0">Sin Stock</p>
                                    </div>
                                    <div class="align-self-center">
                                        <i class="fas fa-times-circle fa-2x"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-3 mb-3">
                        <div class="card bg-warning text-dark">
                            <div class="card-body">
                                <div class="d-flex justify-content-between">
                                    <div>
                                        <h4>
                                            <c:set var="valorTotal" value="0" />
                                            <c:forEach var="producto" items="${productosStockBajo}">
                                                <c:set var="valorTotal" value="${valorTotal + producto.stock * producto.precio}" />
                                            </c:forEach>
                                            S/ <fmt:formatNumber value="${valorTotal}" pattern="#,##0"/>
                                        </h4>
                                        <p class="mb-0">Valor en Stock Bajo</p>
                                    </div>
                                    <div class="align-self-center">
                                        <i class="fas fa-dollar-sign fa-2x"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-3 mb-3">
                        <div class="card bg-info text-white">
                            <div class="card-body">
                                <div class="d-flex justify-content-between">
                                    <div>
                                        <h4>
                                            <c:set var="categoriasAfectadas" value="${''}" />
                                            <c:forEach var="producto" items="${productosStockBajo}">
                                                <c:if test="${fn:indexOf(categoriasAfectadas, producto.categoria) == -1}">
                                                    <c:set var="categoriasAfectadas" value="${categoriasAfectadas},${producto.categoria}" />
                                                </c:if>
                                            </c:forEach>
                                            ${fn:length(fn:split(categoriasAfectadas, ',')) -1}
                                        </h4>
                                        <p class="mb-0">Categorías Afectadas</p>
                                    </div>
                                    <div class="align-self-center">
                                        <i class="fas fa-tags fa-2x"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:if>
               
                
			</div>
		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	
	<script src="https://unpkg.com/lucide@latest"></script>
	<script>
		lucide.createIcons();
	</script>
	<c:if test="${not empty productosStockBajo}">
	    <script>
	        // Stock por Producto
	        const stockCtx = document.getElementById('stockChart').getContext('2d');
	        const stockChart = new Chart(stockCtx, {
	            type: 'bar',
	            data: {
	                labels: [
	                    <c:forEach var="producto" items="${productosStockBajo}" varStatus="loop">
	                        '${producto.nombre}'<c:if test="${!loop.last}">,</c:if>
	                    </c:forEach>
	                ],
	                datasets: [{
	                    label: 'Stock Actual',
	                    data: [
	                        <c:forEach var="producto" items="${productosStockBajo}" varStatus="loop">
	                            ${producto.stock}<c:if test="${!loop.last}">,</c:if>
	                        </c:forEach>
	                    ],
	                    backgroundColor: function(context) {
	                        const value = context.parsed.y;
	                        if (value === 0) return '#dc3545';
	                        if (value <= 2) return '#fd7e14';
	                        return '#ffc107';
	                    },
	                    borderColor: '#495057',
	                    borderWidth: 1
	                }]
	            },
	            options: {
	                responsive: true,
	                maintainAspectRatio: false,
	                plugins: {
	                    legend: {
	                        display: false
	                    },
	                    tooltip: {
	                        callbacks: {
	                            label: function(context) {
	                                return 'Stock: ' + context.parsed.y + ' unidades';
	                            }
	                        }
	                    }
	                },
	                scales: {
	                    y: {
	                        beginAtZero: true,
	                        title: {
	                            display: true,
	                            text: 'Unidades en Stock'
	                        }
	                    },
	                    x: {
	                        title: {
	                            display: true,
	                            text: 'Productos'
	                        }
	                    }
	                }
	            }
	        });
	
	        // por Categoría
	        const categoriaCtx = document.getElementById('categoriaChart').getContext('2d');
	        
	        // Agrupar productos por categoría y contar
	        let categoriaCount = {};
	        <c:forEach var="producto" items="${productosStockBajo}">
	            let categoria = '${producto.categoria}';
	            categoriaCount[categoria] = (categoriaCount[categoria] || 0) + 1;
	        </c:forEach>
	        
	        const categoriaChart = new Chart(categoriaCtx, {
	            type: 'doughnut',
	            data: {
	                labels: Object.keys(categoriaCount),
	                datasets: [{
	                    data: Object.values(categoriaCount),
	                    backgroundColor: [
	                        '#ff6384', '#36a2eb', '#ffce56', '#4bc0c0', 
	                        '#9966ff', '#ff9f40', '#ff6384', '#c9cbcf'
	                    ],
	                    borderWidth: 2,
	                    borderColor: '#fff'
	                }]
	            },
	            options: {
	                responsive: true,
	                maintainAspectRatio: false,
	                plugins: {
	                    legend: {
	                        position: 'bottom'
	                    },
	                    tooltip: {
	                        callbacks: {
	                            label: function(context) {
	                                return context.label + ': ' + context.parsed + ' productos';
	                            }
	                        }
	                    }
	                }
	            }
	        });
	    </script>
    </c:if>
</body>
</html>