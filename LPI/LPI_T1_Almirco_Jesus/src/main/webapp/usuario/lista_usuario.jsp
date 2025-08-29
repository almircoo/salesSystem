<%@page import="entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body>
	<%@include file="/shared/navbar.jsp" %>
	<div class="container">
		<h1>Lista Usuarios</h1>
		
		<%
			/* ArrayList<Usuario> lista = (ArrayList<Usuario>)request.getAttribute("listar"); */
			ArrayList<Usuario> lista = (ArrayList<Usuario>)request.getAttribute("lista");
			/* if (lista == null) {
			    lista = new ArrayList<Usuario>(); // Evitar NullPointerException
			} */
		%>
		
		<a href="usuario?opcion=editar" class="btn btn-sm btn-success"><i class="bi bi-person-plus"></i> Agregar</a>
		<table class="table table-striped table-hover table-sm" >
			<thead>
				<tr>
					<th>ID</th>
					<th>Nombres</th>
					<th>Apellidos</th>
					<th>DNI</th>
					<th>Correo</th>
					<th>Role</th>
					<th>Intentos</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<% for(int i = 0; i < lista.size(); i++) { %>
					<tr>
						<td><%= lista.get(i).getUsuarioId() %></td>
						<td><%= lista.get(i).getNombres() %></td>
						<td><%= lista.get(i).getApellidos() %></td>
						<td><%= lista.get(i).getDni() %></td>
						<td><%= lista.get(i).getCorreo() %></td>
						<td><%= lista.get(i).getRol() %></td>
						<td><%= lista.get(i).getIntentos() %></td>
						<td>
							<div class="btn-group">
								<button class="btn btn-sm btn-primary"><i class="bi bi-pencil-fill"></i> Editar</button>
								<button class="btn btn-sm btn-danger"><i class="bi bi-x-circle"></i> Eliminar</button>
							</div>
						</td>
					</tr>
				<% } %>
			</tbody>
		</table>
		
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
</body>
</html>
