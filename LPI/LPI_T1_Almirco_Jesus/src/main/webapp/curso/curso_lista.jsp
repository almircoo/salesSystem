<%@page import="entidades.Curso"%>
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
		<h1>Lista de Cursos</h1>
		
		<%
			ArrayList<Curso> lista = (ArrayList<Curso>)request.getAttribute("lista");
		%>
		
		<a href="curso?opcion=editar" class="btn btn-sm btn-success"><i class="bi bi-person-plus"></i> Agregar</a>
		<table class="table table-striped table-hover table-sm" >
			<thead>
				<tr>
					<th>Codigo</th>
					<th>Nombre</th>
					<th>Vacantes</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<% for(int i = 0; i < lista.size(); i++) { %>
					<tr>
						<td><%= lista.get(i).getCodigo() %></td>
						<td><%= lista.get(i).getNombre() %></td>
						<td><%= lista.get(i).getVacantes() %></td>
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