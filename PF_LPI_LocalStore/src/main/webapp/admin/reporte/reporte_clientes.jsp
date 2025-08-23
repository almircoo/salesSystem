<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="entidades.Cliente" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte clientes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">

<link rel="stylesheet" href="./assets/css/admin/styles.css">
<link rel="stylesheet" href="https://unpkg.com/lucide@latest/dist/lucide.min.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
	<div class="container">
        <div class="row justify-content-center mt-3">
            <!-- Sidebar -->
            <div class="col-md-3">
				<%@include file="/../shared/admin/sidebar.jsp"%>
			</div>
            
            <!-- content -->
            <div class="col-md-9">
                <!-- -->
                <%@include file="/../shared/admin/navbar.jsp" %>
                
                <%
				    /* List<Map<String, Object>> clientesMasActivos = (List<Map<String, Object>>) request.getAttribute("clientesMasActivos");
				    DecimalFormat df = new DecimalFormat("#,##0.00"); */
				%>
				
				  <div class="mt-5 d-flex justify-content-between align-items-center">
                    <div>
                    	<h3 class="fw-bold">Reporte de Clientes Más Activos</h3>
                    </div>
                    <div>
                         <a href="reportes" class="btn btn-secondary">
                         	<i data-lucide="arrow-left"></i> Volver
                         </a>
                     </div>
                </div>
                <br>
            

                <!-- graficos -->
                <div class="row mb-4 ">
                    <div class="col-md-8">
                        <div class="card rounded-4">
                            <div class="card-header border-0">
                                <h5 class="card-title mb-0">
                                    <i class="fas fa-chart-bar text-primary me-2"></i>Gasto Total por Cliente
                                </h5>
                            </div>
                            <div class="card-body">
                                <canvas id="clientesGastoChart" width="400" height="200"></canvas>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-4">
                        <div class="card rounded-4">
                            <div class="card-header border-0">
                                <h5 class="card-title mb-0">
                                    <i class="fas fa-chart-pie text-success me-2"></i>Top 5 Clientes
                                </h5>
                            </div>
                            <div class="card-body">
                                <canvas id="topClientesChart" width="300" height="300"></canvas>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Clientes activos -->
                <div class="row">
                    <div class="col-12">
                        <div class="card rounded-4">
                            <div class="card-header border-0">
                                <h5 class="card-title mb-0">
                                    <i class="fas fa-star text-warning me-2"></i>Top 10 Clientes Más Activos
                                </h5>
                            </div>
                            <div class="card-body">
                            	<c:choose>
                                    <c:when test="${not empty clientesMasActivos}">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover">
                                                <thead class="table-secondary">
                                                    <tr>
                                                        <th class="text-dark">#</th>
                                                        <th class="text-dark">Cliente</th>
                                                        <th class="text-dark">Email</th>
                                                        <th class="text-center text-dark">Total Pedidos</th>
                                                        <th class="text-end text-dark">Total Gastado</th>
                                                        <th class="text-end text-dark">Promedio por Pedido</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="cliente" items="${clientesMasActivos}" varStatus="loop">
                                                        <tr>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${loop.index == 0}">
                                                                        <i class="fas fa-trophy text-warning"></i> ${loop.count}
                                                                    </c:when>
                                                                    <c:when test="${loop.index == 1}">
                                                                        <i class="fas fa-medal text-secondary"></i> ${loop.count}
                                                                    </c:when>
                                                                    <c:when test="${loop.index == 2}">
                                                                        <i class="fas fa-award text-warning"></i> ${loop.count}
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${loop.count}
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <strong>${cliente.nombre}</strong>
                                                            </td>
                                                            <td>${cliente.email}</td>
                                                            <td class="text-center">
                                                                <span class="badge bg-primary">${cliente.totalPedidos}</span>
                                                            </td>
                                                            <td class="text-end">
                                                                <strong>
                                                                    S/ <fmt:formatNumber value="${cliente.totalGastado}" pattern="#,##0.00"/>
                                                                </strong>
                                                            </td>
                                                            <td class="text-end">
                                                                S/ <fmt:formatNumber value="${cliente.totalGastado / cliente.totalPedidos}" pattern="#,##0.00"/>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="text-center py-4">
                                            <i class="fas fa-users fa-3x text-muted mb-3"></i>
                                            <h5 class="text-muted">No hay datos de clientes disponibles</h5>
                                            <p class="text-muted">Los datos aparecerán cuando se registren pedidos.</p>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                
                                <%-- <% if (clientesMasActivos != null && !clientesMasActivos.isEmpty()) { %>
                                    <div class="table-responsive">
                                        <table class="table table-striped table-hover">
                                            <thead class="table-secondary">
                                                <tr>
                                                    <th class="text-dark">#</th>
                                                    <th class="text-dark">Cliente</th>
                                                    <th class="text-dark">Email</th>
                                                    <th class="text-center text-dark">Total Pedidos</th>
                                                    <th class="text-end text-dark">Total Gastado</th>
                                                    <th class="text-end text-dark">Promedio por Pedido</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% 
                                                for (int i = 0; i < clientesMasActivos.size(); i++) {
                                                    Map<String, Object> cliente = clientesMasActivos.get(i);
                                                    double totalGastado = (Double) cliente.get("totalGastado");
                                                    int totalPedidos = (Integer) cliente.get("totalPedidos");
                                                %>
                                                    <tr>
                                                        <td>
                                                            <% if (i == 0) { %>
                                                                <i class="fas fa-trophy text-warning"></i> <%= (i + 1) %>
                                                            <% } else if (i == 1) { %>
                                                                <i class="fas fa-medal text-secondary"></i> <%= (i + 1) %>
                                                            <% } else if (i == 2) { %>
                                                                <i class="fas fa-award text-warning"></i> <%= (i + 1) %>
                                                            <% } else { %>
                                                                <%= (i + 1) %>
                                                            <% } %>
                                                        </td>
                                                        <td>
                                                            <strong><%= cliente.get("nombre") %> </strong>
                                                        </td>
                                                        <td><%= cliente.get("email") %></td>
                                                        <td class="text-center">
                                                            <span class="badge bg-primary"><%= totalPedidos %></span>
                                                        </td>
                                                        <td class="text-end">
                                                            <strong>S/ <%= df.format(totalGastado) %></strong>
                                                        </td>
                                                        <td class="text-end">
                                                            S/ <%= df.format(totalGastado / totalPedidos) %>
                                                        </td>
                                                    </tr>
                                                <% } %>
                                            </tbody>
                                        </table>
                                    </div>
                                <% } else { %>
                                    <div class="text-center py-4">
                                        <i class="fas fa-users fa-3x text-muted mb-3"></i>
                                        <h5 class="text-muted">No hay datos de clientes disponibles</h5>
                                        <p class="text-muted">Los datos aparecerán cuando se registren pedidos.</p>
                                    </div>
                                <% } %> --%>
                            </div>
                        </div>
                    </div>
                </div>
                

            </div><!--  end col-md-9-->
        </div>
    </div>
	

	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	
	<script src="https://unpkg.com/lucide@latest"></script>
	<script>
		lucide.createIcons();
	</script>
	
	<script>
		<c:if test="${not empty clientesMasActivos}">
		    //  barras - Gasto total por cliente
		    const clientesGastoCtx = document.getElementById('clientesGastoChart').getContext('2d');
		    const clientesGastoChart = new Chart(clientesGastoCtx, {
		        type: 'bar',
		        data: {
		            labels: [
		                <c:forEach var="cliente" items="${clientesMasActivos}" varStatus="loop">
		                    '${cliente.nombre}'<c:if test="${!loop.last}">,</c:if>
		                </c:forEach>
		            ],
		            datasets: [{
		                label: 'Total Gastado (S/.)',
		                data: [
		                    <c:forEach var="cliente" items="${clientesMasActivos}" varStatus="loop">
		                        ${cliente.totalGastado}<c:if test="${!loop.last}">,</c:if>
		                    </c:forEach>
		                ],
		                backgroundColor: 'rgba(54, 162, 235, 0.8)',
		                borderColor: 'rgba(54, 162, 235, 1)',
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
		                    beginAtZero: true,
		                    ticks: {
		                        callback: function(value) {
		                            return 'S/ ' + value.toFixed(2);
		                        }
		                    }
		                },
		                x: {
		                    ticks: {
		                        maxRotation: 45,
		                        minRotation: 45
		                    }
		                }
		            }
		        }
		    });
		
		    // circular - Top 5 clientes
		    const topClientesCtx = document.getElementById('topClientesChart').getContext('2d');
		    const topClientesChart = new Chart(topClientesCtx, {
		        type: 'doughnut',
		        data: {
		            labels: [
		                <c:forEach var="cliente" items="${clientesMasActivos}" end="4" varStatus="loop">
		                    '${cliente.nombre}'<c:if test="${!loop.last}">,</c:if>
		                </c:forEach>
		            ],
		            datasets: [{
		                data: [
		                    <c:forEach var="cliente" items="${clientesMasActivos}" end="4" varStatus="loop">
		                        ${cliente.totalGastado}<c:if test="${!loop.last}">,</c:if>
		                    </c:forEach>
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
		                    labels: {
		                        boxWidth: 12,
		                        padding: 10
		                    }
		                },
		                tooltip: {
		                    callbacks: {
		                        label: function(context) {
		                            return context.label + ': S/ ' + context.parsed.toFixed(2);
		                        }
		                    }
		                }
		            }
		        }
		    });
		</c:if>
        <%-- <% if (clientesMasActivos != null && !clientesMasActivos.isEmpty()) { %>
            //  barras - Gasto total por cliente
            const clientesGastoCtx = document.getElementById('clientesGastoChart').getContext('2d');
            const clientesGastoChart = new Chart(clientesGastoCtx, {
                type: 'bar',
                data: {
                    labels: [
                        <% for (int i = 0; i < clientesMasActivos.size(); i++) {
                            Map<String, Object> cliente = clientesMasActivos.get(i);
                        %>
                            '<%= cliente.get("nombre") %> '<%= (i < clientesMasActivos.size() - 1) ? "," : "" %>
                        <% } %>
                    ],
                    datasets: [{
                        label: 'Total Gastado (S/.)',
                        data: [
                            <% for (int i = 0; i < clientesMasActivos.size(); i++) {
                                Map<String, Object> cliente = clientesMasActivos.get(i);
                            %>
                                <%= cliente.get("totalGastado") %><%= (i < clientesMasActivos.size() - 1) ? "," : "" %>
                            <% } %>
                        ],
                        backgroundColor: 'rgba(54, 162, 235, 0.8)',
                        borderColor: 'rgba(54, 162, 235, 1)',
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
                            beginAtZero: true,
                            ticks: {
                                callback: function(value) {
                                    return 'S/ ' + value.toFixed(2);
                                }
                            }
                        },
                        x: {
                            ticks: {
                                maxRotation: 45,
                                minRotation: 45
                            }
                        }
                    }
                }
            });

            // circular - Top 5 clientes
            const topClientesCtx = document.getElementById('topClientesChart').getContext('2d');
            const topClientesChart = new Chart(topClientesCtx, {
                type: 'doughnut',
                data: {
                    labels: [
                        <% 
                        int limit = Math.min(5, clientesMasActivos.size());
                        for (int i = 0; i < limit; i++) {
                            Map<String, Object> cliente = clientesMasActivos.get(i);
                        %>
                            '<%= cliente.get("nombre") %>'<%= (i < limit - 1) ? "," : "" %>
                        <% } %>
                    ],
                    datasets: [{
                        data: [
                            <% for (int i = 0; i < limit; i++) {
                                Map<String, Object> cliente = clientesMasActivos.get(i);
                            %>
                                <%= cliente.get("totalGastado") %><%= (i < limit - 1) ? "," : "" %>
                            <% } %>
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
                            labels: {
                                boxWidth: 12,
                                padding: 10
                            }
                        },
                        tooltip: {
                            callbacks: {
                                label: function(context) {
                                    return context.label + ': S/ ' + context.parsed.toFixed(2);
                                }
                            }
                        }
                    }
                }
            });
        <% } %> --%>
    </script>
</body>
</html>