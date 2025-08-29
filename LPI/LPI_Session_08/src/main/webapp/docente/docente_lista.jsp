<%@page import="entidades.Docente"%>
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
		<h1>Lista de Docentes</h1>
		
		<%
			ArrayList<Docente> lista = (ArrayList<Docente>)request.getAttribute("lista");
		%>
		
		<a href="docente?opcion=editar" class="btn btn-sm btn-success"><i class="bi bi-person-plus"></i> Agregar</a>
		<table class="table table-striped table-hover table-sm" >
			<thead>
				<tr>
					<th>DNI</th>
					<th>Nombre</th>
					<th>Especialidad</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<% for(Docente d : lista) { %>
					<tr>
						<td><%= d.getDni() %></td>
						<td><%= d.getNombre() %></td>
						<td><%= d.getEspecialidad() %></td>
						<td>
							<div class="btn-group">
								<a class="btn btn-sm btn-primary" href="docente?opcion=editar&id=<%=d.getDocenteId()%>"><i class="bi bi-pencil-fill"></i> Editar</a>
								<a class="btn btn-sm btn-danger" href="javascript:eliminar(<%=d.getDocenteId()%>)"><i class="bi bi-x-circle"></i> Eliminar</a>
							</div>
						</td>
					</tr>
				<% } %>
			</tbody>
		</table>
		
	</div>
	
	<form id="formEliminar" action="docente?opcion=eliminar" method="post">
		<input type="hidden" name="id" />
	</form>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
	<script type="text/javascript">
		const eliminar = (id) => {
			const respuesta = confirm('¿Desea eliminar el docente?');
			if (respuesta) {
				document.querySelector('#formEliminar input[name=id]').value = id;
				document.getElementById('formEliminar').submit();
			}
		};
	</script>
</body>
</html>