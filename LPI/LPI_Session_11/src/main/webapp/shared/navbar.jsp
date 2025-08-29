<%@page import="entidades.Usuario"%>
<%
	HttpSession miSession = request.getSession();
	Usuario authUsuario = new Usuario();
	if (miSession.getAttribute("usuario") != null)
		authUsuario = (Usuario) miSession.getAttribute("usuario");
%>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container">
    <a class="navbar-brand" href="#">Cibertec LPI</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="inicio">Inicio</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="curso">Cursos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="docente">Docentes</a>
        </li>
        <% if ("ADMIN".equals(authUsuario.getRol())) { %>  
        <li class="nav-item">
          <a class="nav-link" href="usuario">Usuarios</a>
        </li>
        <% } %>
      </ul>
      
      <div class="d-flex">
      	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
      		<li>
      			<a class="nav-link" href="#" ><%= authUsuario.getNombre() %></a>
      		</li>
      		<li>
      			<a class="nav-link" href="auth?opcion=logout" >Cerrar sesión</a>
      		</li>
      	</ul>
      </div>
      
    </div>
  </div>
</nav>
