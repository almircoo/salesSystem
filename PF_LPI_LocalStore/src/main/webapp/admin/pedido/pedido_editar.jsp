<%@ page import="java.util.Date"%>
<%@page import="entidades.Cliente"%>
<%@page import="entidades.Producto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crear pedido</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">

<link rel="stylesheet" href="./assets/css/admin/styles.css">
<link rel="stylesheet" href="https://unpkg.com/lucide@latest/dist/lucide.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
	
	<div class="container">
		<div class="row justify-content-center mt-3">
			<!-- sidebar -->
			<div class="col-md-3">
				<%@include file="/../shared/admin/sidebar.jsp"%>
			</div>

			<!-- content -->
			<div class="col-md-9">
				<%@include file="/../shared/admin/navbar.jsp"%>
				
				<form method="post" action="pedidos" id="formPedido">
				    <input type="hidden" name="opcion" value="registrar">
				    <input type="hidden" name="pedidoId" value="${registro.pedidoId}">
				
				    <div class="row">
				        <!-- Información del pedido -->
				        <div class="col-md-6">
				            <div class="card shadow-sm mb-4">
				                <div class="card-header bg-light">
				                    <h5 class="card-title mb-0">
				                        <i class="bi bi-info-circle"></i> Información del Pedido
				                    </h5>
				                </div>
				                <div class="card-body">
				                    <div class="row g-3">
				                        <!-- Cliente -->
				                        <div class="col-12">
				                            <label for="clienteId" class="form-label fw-bold">Cliente *</label>
				                            <select class="form-select" id="clienteId" name="clienteId" required>
				                                <option value="">Seleccione un cliente</option>
				                                <c:forEach var="cliente" items="${clientes}">
				                                    <option value="${cliente.clienteId}" 
				                                            ${registro.clienteId == cliente.clienteId ? 'selected' : ''}>
				                                        ${cliente.nombre} - ${cliente.dni}
				                                    </option>
				                                </c:forEach>
				                            </select>
				                        </div>
				
				                        <!-- Fecha y Estado (solo edición) -->
				                        <c:if test="${registro.pedidoId > 0}">
				                            <div class="col-md-6">
				                                <label class="form-label fw-bold">Fecha</label>
				                                <input type="text" class="form-control" 
				                                       value="<fmt:formatDate value='${registro.fechaPedido}' pattern='dd/MM/yyyy HH:mm'/>" readonly>
				                            </div>
				                            <div class="col-md-6">
				                                <label class="form-label fw-bold">Estado</label>
				                                <input type="text" class="form-control" value="${registro.estado}" readonly>
				                            </div>
				                        </c:if>
				
				                        <!-- Tipo comprobante -->
				                        <div class="col-md-6">
				                            <label for="tipoComprobante" class="form-label fw-bold">Tipo de Comprobante *</label>
				                            <select class="form-select" id="tipoComprobante" name="tipoComprobante" required>
				                                <option value="BOLETA" ${registro.tipoComprobante == 'BOLETA' ? 'selected' : ''}>Boleta</option>
				                                <option value="FACTURA" ${registro.tipoComprobante == 'FACTURA' ? 'selected' : ''}>Factura</option>
				                            </select>
				                        </div>
				
				                        <!-- Método pago -->
				                        <div class="col-md-6">
				                            <label for="metodoPago" class="form-label fw-bold">Método de Pago *</label>
				                            <select class="form-select" id="metodoPago" name="metodoPago" required>
				                                <option value="">Seleccione método</option>
				                                <option value="EFECTIVO" ${registro.metodoPago == 'EFECTIVO' ? 'selected' : ''}>Efectivo</option>
				                                <option value="TARJETA" ${registro.metodoPago == 'TARJETA' ? 'selected' : ''}>Tarjeta</option>
				                                <option value="TRANSFERENCIA" ${registro.metodoPago == 'TRANSFERENCIA' ? 'selected' : ''}>Transferencia</option>
				                                <option value="DEPOSITO" ${registro.metodoPago == 'DEPOSITO' ? 'selected' : ''}>Depósito</option>
				                            </select>
				                        </div>
				
				                        <!-- Observaciones -->
				                        <div class="col-12">
				                            <label for="observaciones" class="form-label fw-bold">Observaciones</label>
				                            <textarea class="form-control" id="observaciones" name="observaciones" rows="3">${registro.observaciones}</textarea>
				                        </div>
				                    </div>
				                </div>
				            </div>
				        </div>
				
				        <!-- Agregar producto -->
				        <div class="col-md-6">
				            <div class="card shadow-sm mb-4">
				                <div class="card-header bg-light">
				                    <h5 class="card-title mb-0">
				                        <i class="bi bi-plus-circle"></i> Agregar Producto
				                    </h5>
				                </div>
				                <div class="card-body">
				                    <div class="row g-3">
				                        <!-- Producto -->
				                        <div class="col-12">
				                            <label for="productoSelect" class="form-label fw-bold">Producto</label>
				                            <select class="form-select" id="productoSelect" name="detallesProductoId[]" required>
				                                <option value="">Seleccione un producto</option>
				                                <c:forEach var="producto" items="${productos}">
				                                    <option value="${producto.productoId}" 
				                                            data-precio="${producto.precio}" 
				                                            data-stock="${producto.stock}">
				                                        ${producto.nombre} - S/. <fmt:formatNumber value="${producto.precio}" pattern="##0.00"/> 
				                                        (Stock: ${producto.stock})
				                                    </option>
				                                </c:forEach>
				                            </select>
				                        </div>
				
				                        <!-- Precio -->
				                        <div class="col-md-6">
				                            <label for="precioUnitario" class="form-label fw-bold">Precio Unitario</label>
				                            <input type="number" class="form-control" id="precioUnitario" name="detallesPrecioUnitario[]" step="0.01" readonly>
				                        </div>
				
				                        <!-- Cantidad -->
				                        <div class="col-md-6">
				                            <label for="cantidad" class="form-label fw-bold">Cantidad</label>
				                            <input type="number" class="form-control" id="cantidad" name="detallesCantidad[]" min="1" required>
				                        </div>
				                    </div>
				                </div>
				            </div>
				        </div>
				    </div>
				
				    <!-- Botones -->
				    <div class="d-flex justify-content-center gap-2 mt-3">
				        <a href="pedidos" class="btn btn-outline-secondary px-4">
				            <i class="bi bi-x-circle"></i> Cancelar
				        </a>
				        <button type="submit" class="btn btn-primary px-4">
				            <i class="bi bi-check-circle"></i> ${registro.pedidoId == 0 ? 'Crear Pedido' : 'Actualizar Pedido'}
				        </button>
				    </div>
				</form>
			
			</div>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	
	<script src="https://unpkg.com/lucide@latest"></script>
     <script>
       lucide.createIcons();
     </script>
     
     <script>
	  $(document).ready(function () {
	    // cuando cambie el producto seleccionado
	    $("#productoSelect").on("change", function () {
	      let precio = $(this).find(":selected").data("precio");
	      let stock = $(this).find(":selected").data("stock");
	
	      // colocar el precio en el input
	      if (precio) {
	        $("#precioUnitario").val(precio);
	      } else {
	        $("#precioUnitario").val("");
	      }
	
	      // opcional: limitar cantidad máxima al stock
	      if (stock) {
	        $("#cantidad").attr("max", stock);
	      } else {
	        $("#cantidad").removeAttr("max");
	      }
	    });
	  });
	</script>
	
</body>
</html>