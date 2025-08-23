<%@page import="entidades.Usuario" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	/* HttpSession see = request.getSession();
	Usuario userN = new Usuario();
	if (see.getAttribute("usuario") != null)
		userN = (Usuario)see.getAttribute("usuario"); */
%>

<div>
     <nav class="navbar navbar-expand bg-light shadow-sm px-3 py-2 mb-4 rounded-3" id="mainNavbar">
       <div class="container-fluid d-flex justify-content-between align-items-center">
         <!-- Texto -->
         <span class="fw-bold fs-5"> 
         	Welcome - 
         		<c:choose>
		          <c:when test="${usuario.nombre != null}">
		            ${usuario.nombre}
		          </c:when>
		          <c:otherwise>
		            User Name
		          </c:otherwise>
		        </c:choose>
         	<%-- <%= userN.getNombre() != null ? userN.getNombre() : "User Name" %> --%>
         	
         	</span>

         <!-- Iconos y avatar con dropdown -->
         <div class="d-flex align-items-center gap-3">
           	<a href="" class="text-decoration-none text-dark">
                <i data-lucide="calendar-days"></i>
            </a>
            <a href="" class="text-decoration-none text-dark">
                <i data-lucide="message-circle-more"></i>
            </a>
            <a href="" class="text-decoration-none text-dark">
                <i data-lucide="bell-dot"></i>
            </a>

           <!-- Avatar con dropdown -->
           <div class="dropdown">
             <a href="#" class="d-flex align-items-center text-decoration-none dropdown-toggle text-dark" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
               <i data-lucide="circle-user-round"></i>
             </a>
             <ul class="dropdown-menu dropdown-menu-end shadow" aria-labelledby="userDropdown">
               <li><h6 class="dropdown-header">
               		<c:choose>
	                  <c:when test="${usuario.nombre != null}">
	                    ${usuario.nombre}
	                  </c:when>
	                  <c:otherwise>
	                    User Name
	                  </c:otherwise>
	                </c:choose>
               		<%-- <%= userN.getNombre() != null ? userN.getNombre() : "User Name" %> --%>
               	</h6></li>
               <li><a class="dropdown-item" href="#"><i data-lucide="user-cog"></i> Perfil</a></li>
               <li><a class="dropdown-item" href="#"><i data-lucide="settings"></i> Configuración</a></li>
               <li><hr class="dropdown-divider"></li>
               <li><a class="dropdown-item text-danger" href="auth?opcion=logout"><i data-lucide="log-out"></i> Cerrar sesión</a></li>
             </ul>
           </div>
         </div>
       </div>
     </nav>
 </div>