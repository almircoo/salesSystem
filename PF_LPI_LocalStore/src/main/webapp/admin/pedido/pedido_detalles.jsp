<%@page import="entidades.Pedido"%>
<%@page import="entidades.DetallePedido"%>
<%@page import="entidades.Cliente"%>
<%@page import="entidades.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalles de pedido</title>
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
				<!-- aqui detalles del pedido -->
				<%@include file="/../shared/admin/navbar.jsp" %>
				
				<%
					Pedido pedido = (Pedido)request.getAttribute("pedido");
					ArrayList<DetallePedido> detalles = (ArrayList<DetallePedido>)request.getAttribute("detalles");
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					/* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); */
				%>
				
				<!-- Agregando header con navegación -->
				<div class="mt-5 d-flex justify-content-between align-items-center">
                     <div>
                         <h3 class="fw-bold">Detalles del Pedido #<%= pedido.getPedidoId() %></h3>
                     </div>
                     <div>
                         <a href="pedidos" class="btn btn-secondary">
                         	<i data-lucide="arrow-left"></i> Volver a Pedidos
                         </a>
                     </div>
                 </div>
                 <!-- Agregando información del pedido -->
				<div class="row mt-4">
					<div class="col-md-6">
						<div class="card rounded-4">
							<div class="card-header">
								<h5 class="mb-0">Información del Pedido</h5>
							</div>
							<div class="card-body">
								<div class="row mb-2">
									<div class="col-sm-4"><strong>ID Pedido:</strong></div>
									<div class="col-sm-8">#<%= pedido.getPedidoId() %></div>
								</div>
								<div class="row mb-2">
									<div class="col-sm-4"><strong>Cliente:</strong></div>
									<div class="col-sm-8"><%= pedido.getCliente() %></div>
								</div>
								<div class="row mb-2">
									<div class="col-sm-4"><strong>Fecha:</strong></div>
									<div class="col-sm-8"><%= formatter.format(pedido.getFechaPedido()) %></div> 
									<!-- pedido.getFechaPedido().format(formatter) -->
								</div>
								<div class="row mb-2">
									<div class="col-sm-4"><strong>Estado:</strong></div>
									<div class="col-sm-8">
										<% 
											String estadoClass = "";
											switch(pedido.getEstado()) {
												case "PENDIENTE": estadoClass = "badge bg-warning"; break;
												case "PROCESANDO": estadoClass = "badge bg-info"; break;
												case "EN_CAMINO": estadoClass = "badge bg-primary"; break;
												case "ENTREGADO": estadoClass = "badge order-status-delivered"; break;
												case "CANCELADO": estadoClass = "badge bg-danger"; break;
												default: estadoClass = "badge bg-secondary";
											}
										%>
										<span class="<%= estadoClass %>"><%= pedido.getEstado() %></span>
									</div>
								</div>
								<div class="row mb-2">
									<div class="col-sm-4"><strong>Total:</strong></div>
									<div class="col-sm-8"><strong>S/. <%= String.format("%.2f", pedido.getTotal()) %></strong></div>
								</div>
								<% if (pedido.getObservaciones() != null && !pedido.getObservaciones().trim().isEmpty()) { %>
								<div class="row mb-2">
									<div class="col-sm-4"><strong>Observaciones:</strong></div>
									<div class="col-sm-8"><%= pedido.getObservaciones() %></div>
								</div>
								<% } %>
							</div>
						</div>
					</div>
				</div>
                 <!-- Agregando tabla de detalles del pedido -->
				<div class="card rounded-4 mt-4">
					<div class="card-header">
						<h5 class="mb-0">Productos del Pedido</h5>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th scope="col">Producto</th>
										<th scope="col">Cantidad</th>
										<th scope="col">Precio Unitario</th>
										<th scope="col">Subtotal</th>
									</tr>
								</thead>
								<tbody>
								<% 
									double totalCalculado = 0;
									for(DetallePedido d: detalles) { 
										totalCalculado += d.getSubtotal();
								%>
									<tr>
										<td><%= d.getProducto().getNombre() %></td>
										<td><%= d.getCantidad() %></td>
										<td>S/. <%= String.format("%.2f", d.getPrecioUnitario()) %></td>
										<td>S/. <%= String.format("%.2f", d.getSubtotal()) %></td>
									</tr>
								<% } %>
								</tbody>
								<tfoot>
									<tr class="table-active">
										<th colspan="3" class="text-end">Total:</th>
										<th>S/. <%= String.format("%.2f", totalCalculado) %></th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
                 <!-- endcol-md-9 -->
			</div>
			
		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	
	<script src="https://unpkg.com/lucide@latest"></script>
     <script>
       lucide.createIcons();
     </script>
	
	
</body>
</html>