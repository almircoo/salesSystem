<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
</head>
<body >
<div class="container">
	<div class="row justify-content-center">
		<div class="col-md-5">
			<h1>Lista de Cursos</h1>
			<%
			
				/* ArrayList<String> codigos = new ArrayList<String>();
				ArrayList<String> nombres = new ArrayList<String>();
				ArrayList<String> vacantes = new ArrayList<Integer>(); */
				
				/*  String[] codigos = { "123", "456", "555", "789"};
				String[] nombres = { "Matematicas", "Lenguaje", "Historia", "Quimica"};
				int [] vacantes = {23, 30, 28, 32 };
				
				if(request.getAttribute("codiog") !=null){
					codigos = {};
				} */ 
				
				/* if(request.getAttribute("codigo") !=null){
					codigos.add(request.getAttribute("codigo").toString());
					nombres.add(request.getAttribute("nombres").toString());
					vacantes.add(Integer.parseInt(request.getAttribute("vacantes").toString()));
				} */
			%>
			
			<a href="course?opcion=editar" class="btn btn-sm btn-success"><i class="bi bi-person-plus"></i> Agregar</a>
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
				<%-- <% for(int i = 0; i < codigos.size(); i++) { %>
					<tr>
						<td><%= codigos.get(i) %></td>
						<td><%= nombres.get(i) %></td>
						<td><%= vacantes.get(i) %></td>
						<td>
							<div class="btn-group">
								<button class="btn btn-sm btn-primary"><i class="bi bi-pencil-fill"></i> Editar</button>
								<button class="btn btn-sm btn-danger"><i class="bi bi-x-circle"></i> Eliminar</button>
							</div>
						</td>
					</tr>
				<% } %> --%>
			</tbody>
		</table>
		</div>
	</div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
</body>
</html>