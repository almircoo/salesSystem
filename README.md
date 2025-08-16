# Sales System Platform

> Este proyecto consiste en un sistema de ventas adaptable (POS - Web) y una plataforma de panel de control,
desarrollado con Bootstrap 5.3.7 y JAVA, diseñado para gestionar las ventas de diferentes sectores y proporciona análisis detallados.
>

# Tasks de Desarrollo
---
## 1. Preparación del Proyecto
- [X] Crear carpeta principal del proyecto.
- [x] Inicializar repositorio Git.
- [x] Configurar estructura de carpetas:
  - `/` → Lado cliente (tienda en línea)
  - `/admin` → Dashboard administrador
  - `/images` → Imágenes, iconos, fuentes
  - `/css` → Estilos
  - `/js` → javascript
- [x] Configurar archivo `.gitignore`.
- [x] Instalar dependencias iniciales (Bootstrap 5.3.7, Lucide Icons, etc.).

---

## 2. Lado Cliente
### 2.1. Estructura Base
- [x] Crear archivo `index.html` con plantilla HTML5.
- [x] Importar Bootstrap 5.3.7 y Lucide Icons.
- [x] Configurar hoja de estilos `styles.css`.
- [x] Configurar script principal `app.js`.

### 2.2. Componentes UI
- [x] Navbar responsiva con:
  - [x] Logo y nombre
  - [x] Menú de navegación
  - [x] Barra de búsqueda
  - [x] Iconos de carrito y usuario
  - [x] Botones de iniciar sesión y registrarse
- [x] Sección Hero (banner principal con CTA).
- [x] Sección de Categorías con imágenes.
- [x] Sección de Productos destacados:
  - [x] Tarjetas con imagen, nombre, precio, botón de añadir al carrito.
- [x] Página de Detalles del Producto.
- [x] Carrito de compras:
  - [x] Listado de productos
  - [x] Actualizar cantidades
  - [x] Mostrar subtotal y total
- [x] Footer fijo al final de la página.

### 2.3. Funcionalidad JS
- [x] Script para abrir/cerrar menú en móvil.
- [x] Script para manejar búsqueda.
- [x] Script para añadir productos al carrito (localStorage).
- [x] Script para mostrar/actualizar el carrito.
- [x] Script para procesar checkout simulado.

---

##  3. Backend Logica
- [x] Configurar servidor Mysql + Eclipse.
- [x] Conectar a base de datos (MySQL).
- [x] Crear Logica para:
  - [x] Obtener categorías.
  - [x] Obtener productos.
  - [ ] Crear pedidos.
  - [ ] Autenticación de usuario.

---

## 4. Dashboard Administrador
### 4.1. Estructura Base
- [x] Crear archivo `dashboard.html` en `/admin`.
- [x] Importar Bootstrap 5.3.7 y Lucide Icons.
- [x] Configurar `admin.css` y `admin.js`.

### 4.2. Componentes UI
- [ ] Navbar lateral (sidebar) con enlaces a:
  - [ ] Resumen / Panel principal
  - [ ] Gestión de productos
  - [ ] Gestión de categorías
  - [ ] Gestión de pedidos
  - [ ] Gestión de usuarios
  - [ ] Configuración
- [ ] Dashboard principal con tarjetas estadísticas:
  - [ ] Ventas totales
  - [ ] Pedidos activos
  - [ ] Productos publicados
  - [ ] Usuarios registrados
- [ ] Tabla de productos con:
  - [ ] Imagen
  - [ ] Nombre
  - [ ] Precio
  - [ ] Stock
  - [ ] Botón de editar/eliminar
- [ ] Formulario para añadir/editar productos.
- [ ] Tabla de pedidos con:
  - [ ] ID Pedido
  - [ ] Cliente
  - [ ] Estado
  - [ ] Fecha
  - [ ] Total
  - [ ] Botón de ver detalles

### 4.3. Funcionalidad JS
- [ ] Script para mostrar datos de API.
- [ ] Script para CRUD de productos.
- [ ] Script para CRUD de categorías.
- [ ] Script para actualizar estado de pedidos.
- [ ] Script para exportar datos (CSV/Excel).

---

## 5. Optimización y Extras
- [x] Implementar paginación en listas de productos.
- [x] Agregar filtros y búsqueda avanzada.
- [x] Implementar validaciones de formularios.
- [x] Optimizar imágenes (lazy loading).
- [ ] Mejorar SEO (metadatos, etiquetas alt).
- [ ] Configurar notificaciones visuales (toasts).

---
