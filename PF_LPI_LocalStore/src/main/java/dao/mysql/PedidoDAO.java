package dao.mysql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConexion;
import entidades.Cliente;
import entidades.DetallePedido;
import entidades.Pedido;
import entidades.Producto;
import entidades.Usuario;
import interfaces.IPedidoDAO;

public class PedidoDAO implements IPedidoDAO {
private static IPedidoDAO instancia;
	
	public static IPedidoDAO getInstancia() {
		if (instancia == null) {
			instancia = new PedidoDAO();
		}
		return instancia;
	}
	
	private PedidoDAO() {}

	/*
	@Override
	public int crear(Pedido pedido) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false); // no se auto confirma
			String sql = "insert into pedido values (null, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);

			ps.setInt(1, pedido.getClienteId());
			ps.setString(2, pedido.getFechaPedido().toString());
			ps.setString(3, pedido.getEstado());
			ps.setDouble(4, pedido.getTotal());
			ps.setString(5, pedido.getObservaciones());
			
			value = ps.executeUpdate();
			con.commit(); // conrima y se guarda en base de datos
		} catch (Exception e) {
			try {
				con.rollback(); // deshace todo lo se haya ejectado en el base datos
			}catch(SQLException ex){
				System.out.println("Error al crear: " + e.getMessage());
			}
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}
	*/
	
	@Override
	public int crear(Pedido pedido) {
	    int value = 0;
	    Connection con = null;
	    PreparedStatement psPedido = null;
	    PreparedStatement psDetalle = null;
	    ResultSet rs = null;
	    
	    try {
	        con = MySQLConexion.getConexion();
	        con.setAutoCommit(false);
	        
	        String sqlPedido = "INSERT INTO pedido (cliente_id, usuario_id, fecha_pedido, tipo_comprobante, " +
	                          "numero_comprobante, subtotal, igv, total, metodo_pago, estado, observaciones) " +
	                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        psPedido = con.prepareStatement(sqlPedido, PreparedStatement.RETURN_GENERATED_KEYS);
	        
	        psPedido.setInt(1, pedido.getClienteId());
	        psPedido.setInt(2, pedido.getUsuarioId());
	        psPedido.setTimestamp(3, new java.sql.Timestamp(pedido.getFechaPedido().getTime()));
	        psPedido.setString(4, pedido.getTipoComprobante());
	        psPedido.setString(5, pedido.getNumeroComprobante());
	        psPedido.setDouble(6, pedido.getSubtotal());
	        psPedido.setDouble(7, pedido.getIgv());
	        psPedido.setDouble(8, pedido.getTotal());
	        psPedido.setString(9, pedido.getMetodoPago());
	        psPedido.setString(10, pedido.getEstado());
	        psPedido.setString(11, pedido.getObservaciones());
	        
	        value = psPedido.executeUpdate();
	        
	        int pedidoId = 0;
	        rs = psPedido.getGeneratedKeys();
	        if (rs.next()) {
	            pedidoId = rs.getInt(1);
	        }
	        
	        if (pedidoId > 0 && pedido.getDetalles() != null && !pedido.getDetalles().isEmpty()) {
	            String sqlDetalle = "INSERT INTO DetallePedido (pedido_id, producto_id, cantidad, precio_unitario, subtotal) " +
	                               "VALUES (?, ?, ?, ?, ?)";
	            
	            psDetalle = con.prepareStatement(sqlDetalle);
	            
	            for (DetallePedido detalle : pedido.getDetalles()) {
	                psDetalle.setInt(1, pedidoId);
	                psDetalle.setInt(2, detalle.getProductoId());
	                psDetalle.setInt(3, detalle.getCantidad());
	                psDetalle.setDouble(4, detalle.getPrecioUnitario());
	                psDetalle.setDouble(5, detalle.getSubtotal());
	                psDetalle.addBatch();
	            }
	            
	            int[] detallesInsertados = psDetalle.executeBatch();
	            value += detallesInsertados.length;
	        }
	        
	        con.commit(); // Confirmar la transacción
	        //pedido.setPedidoId(pedidoId); // Asignar el ID generado al objeto
	        
	    } catch (Exception e) {
	        try {
	            if (con != null) {
	                con.rollback();
	            }
	            System.out.println("Error al crear pedido: " + e.getMessage());
	            e.printStackTrace();
	            value = 0;
	        } catch (SQLException ex) {
	            System.out.println("Error al hacer rollback: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    } finally {
	        MySQLConexion.closeConexion(con);
	    }
	    return value;
	}

	@Override
	public ArrayList<Pedido> listar() {
	    ArrayList<Pedido> lista = new ArrayList<Pedido>();
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	        con = MySQLConexion.getConexion();
	        String sql = "SELECT p.*, c.nombre as cliente_nombre, u.nombre as usuario_nombre " +
	                    "FROM pedido p " +
	                    "INNER JOIN cliente c ON p.cliente_id = c.cliente_id " +
	                    "INNER JOIN usuario u ON p.usuario_id = u.usuario_id " +
	                    "ORDER BY p.fecha_pedido DESC";
	        
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();
	        
	        while(rs.next()) {
	            Pedido pedido = new Pedido();
	            pedido.setPedidoId(rs.getInt("pedido_id"));
	            pedido.setClienteId(rs.getInt("cliente_id"));
	            pedido.setUsuarioId(rs.getInt("usuario_id"));
	            pedido.setFechaPedido(rs.getTimestamp("fecha_pedido"));
	            pedido.setTipoComprobante(rs.getString("tipo_comprobante"));
	            pedido.setNumeroComprobante(rs.getString("numero_comprobante"));
	            pedido.setSubtotal(rs.getDouble("subtotal"));
	            pedido.setIgv(rs.getDouble("igv"));
	            pedido.setTotal(rs.getDouble("total"));
	            pedido.setMetodoPago(rs.getString("metodo_pago"));
	            pedido.setEstado(rs.getString("estado"));
	            pedido.setObservaciones(rs.getString("observaciones"));
	            
	            Cliente cliente = new Cliente();
	            cliente.setClienteId(rs.getInt("cliente_id"));
	            cliente.setNombre(rs.getString("cliente_nombre"));
	            pedido.setCliente(cliente);
	            
	            Usuario usuario = new Usuario();
	            usuario.setUsuarioId(rs.getInt("usuario_id"));
	            usuario.setNombre(rs.getString("usuario_nombre"));
	            pedido.setUsuario(usuario);
	            
	            lista.add(pedido);
	        }
	    } catch (Exception e) {
	        System.out.println("Error al listar pedidos: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        MySQLConexion.closeConexion(con);
	    }
	    
	    return lista;
	}
	/*
	@Override
	public ArrayList<Pedido> listar() {
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT p.*, c.nombre as cliente_nombre FROM pedido p " +
						"INNER JOIN cliente c ON p.cliente_id = c.cliente_id " +
						"ORDER BY p.fecha_pedido DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Pedido pedido = new Pedido(
						rs.getInt("pedido_id"),
						rs.getInt("cliente_id"),
						rs.getString("cliente_nombre"),
						new Date(rs.getString("fecha_pedido")),
						rs.getString("estado"),
						rs.getDouble("total"),
						rs.getString("observaciones"));
				lista.add(pedido);
			}
		} catch (Exception e) {
			System.out.println("Error al listar pedidos: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}
	*/

	/*
	@Override
	public Pedido obtener(int id) {
		Pedido pedido = null;		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT p.*, c.nombre as cliente_nombre FROM pedido p " +
						"INNER JOIN cliente c ON p.cliente_id = c.cliente_id " +
						"WHERE p.pedido_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				pedido = new Pedido(
						rs.getInt("pedido_id"),
						rs.getInt("cliente_id"),
						rs.getString("cliente_nombre"),
						new Date(rs.getString("fecha_pedido")),
						rs.getString("estado"),
						rs.getDouble("total"),
						rs.getString("observaciones"));
			}
		} catch (Exception e) {
			System.out.println("Error al obtener pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return pedido;
	}
	*/
	
	@Override
	public Pedido obtener(int id) {
	    Pedido pedido = null;
	    Connection con = null;
	    CallableStatement cs = null;
	    ResultSet rsPedido = null;
	    ResultSet rsDetalles = null;
	    
	    try {
	        con = MySQLConexion.getConexion();
	        
	        String sql = "{call usp_Buscar_Pedido_Por_Id(?)}";
	        cs = con.prepareCall(sql);
	        cs.setInt(1, id);
	        
	        boolean hasResults = cs.execute();
	        if (hasResults) {
	            rsPedido = cs.getResultSet();
	            if (rsPedido.next()) {
	                pedido = new Pedido();
	                pedido.setPedidoId(rsPedido.getInt("pedido_id"));
	                pedido.setClienteId(rsPedido.getInt("cliente_id"));
	                pedido.setUsuarioId(rsPedido.getInt("usuario_id"));
	                pedido.setFechaPedido(rsPedido.getTimestamp("fecha_pedido"));
	                pedido.setTipoComprobante(rsPedido.getString("tipo_comprobante"));
	                pedido.setNumeroComprobante(rsPedido.getString("numero_comprobante"));
	                pedido.setSubtotal(rsPedido.getDouble("subtotal"));
	                pedido.setIgv(rsPedido.getDouble("igv"));
	                pedido.setTotal(rsPedido.getDouble("total"));
	                pedido.setMetodoPago(rsPedido.getString("metodo_pago"));
	                pedido.setEstado(rsPedido.getString("estado"));
	                pedido.setObservaciones(rsPedido.getString("observaciones"));
	                
	                Cliente cliente = new Cliente();
	                cliente.setClienteId(rsPedido.getInt("cliente_id"));
	                cliente.setNombre(rsPedido.getString("cliente_nombre"));
	                cliente.setEmail(rsPedido.getString("cliente_email"));
	                cliente.setTelefono(rsPedido.getString("cliente_telefono"));
	                cliente.setDireccion(rsPedido.getString("cliente_direccion"));
	                pedido.setCliente(cliente);
	                
	                Usuario usuario = new Usuario();
	                usuario.setUsuarioId(rsPedido.getInt("usuario_id"));
	                usuario.setNombre(rsPedido.getString("usuario_nombre"));
	                pedido.setUsuario(usuario);
	            }
	            rsPedido.close();
	        }
	        
	        if (cs.getMoreResults()) {
	            rsDetalles = cs.getResultSet();
	            List<DetallePedido> detalles = new ArrayList<>();
	            
	            while (rsDetalles.next()) {
	                DetallePedido detalle = new DetallePedido();
	                detalle.setDetalleId(rsDetalles.getInt("detalle_id"));
	                detalle.setPedidoId(rsDetalles.getInt("pedido_id"));
	                detalle.setProductoId(rsDetalles.getInt("producto_id"));
	                detalle.setCantidad(rsDetalles.getInt("cantidad"));
	                detalle.setPrecioUnitario(rsDetalles.getDouble("precio_unitario"));
	                detalle.setSubtotal(rsDetalles.getDouble("detalle_subtotal"));
	                
	                Producto producto = new Producto();
	                producto.setProductoId(rsDetalles.getInt("producto_id"));
	                producto.setNombre(rsDetalles.getString("producto_nombre"));
	                detalle.setProducto(producto);
	                
	                detalles.add(detalle);
	            }
	            
	            if (pedido != null) {
	                pedido.setDetalles(detalles);
	            }
	            rsDetalles.close();
	        }
	        
	    } catch (Exception e) {
	        System.out.println("Error al obtener pedido: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        MySQLConexion.closeConexion(con);
	    }
	    
	    return pedido;
	}
	/*
	@Override
	public int actualizar(Pedido pedido) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update pedido set cliente_id = ?, fecha_pedido = ?, estado = ?, total = ?, observaciones = ? where pedido_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pedido.getClienteId());
			ps.setString(2, pedido.getFechaPedido().toString());
			ps.setString(3, pedido.getEstado());
			ps.setDouble(4, pedido.getTotal());
			ps.setString(5, pedido.getObservaciones());
			ps.setInt(6, pedido.getPedidoId());
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}
	*/

	@Override
	public int eliminar(int id) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from pedido where pedido_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Pedido> buscar(String texto) {
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rsPedido = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT p.*, c.nombre as cliente_nombre FROM pedido p " +
						"INNER JOIN cliente c ON p.cliente_id = c.cliente_id " +
						"WHERE c.nombre LIKE ? OR p.estado LIKE ? OR p.pedido_id LIKE ? " +
						"ORDER BY p.fecha_pedido DESC";
			ps = con.prepareStatement(sql);
			String busqueda = "%" + texto + "%";
			ps.setString(1, busqueda);
			ps.setString(2, busqueda);
			ps.setString(3, busqueda);
			rsPedido = ps.executeQuery();
			
			while(rsPedido.next()) {
				Pedido pedido = new Pedido();
                pedido.setPedidoId(rsPedido.getInt("pedido_id"));
                pedido.setClienteId(rsPedido.getInt("cliente_id"));
                pedido.setUsuarioId(rsPedido.getInt("usuario_id"));
                pedido.setFechaPedido(rsPedido.getTimestamp("fecha_pedido"));
                pedido.setTipoComprobante(rsPedido.getString("tipo_comprobante"));
                pedido.setNumeroComprobante(rsPedido.getString("numero_comprobante"));
                pedido.setSubtotal(rsPedido.getDouble("subtotal"));
                pedido.setIgv(rsPedido.getDouble("igv"));
                pedido.setTotal(rsPedido.getDouble("total"));
                pedido.setMetodoPago(rsPedido.getString("metodo_pago"));
                pedido.setEstado(rsPedido.getString("estado"));
                pedido.setObservaciones(rsPedido.getString("observaciones"));
				lista.add(pedido);
			}
		} catch (Exception e) {
			System.out.println("Error al buscar pedidos: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public int cambiarEstado(int pedidoId, String nuevoEstado) {
		int value = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update pedido set estado = ? where pedido_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, nuevoEstado);
			ps.setInt(2, pedidoId);
			value = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al cambiar estado del pedido: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	}

	@Override
	public ArrayList<Pedido> listarPorEstado(String estado) {
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rsPedido = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT p.*, c.nombre as cliente_nombre FROM pedido p " +
						"INNER JOIN cliente c ON p.cliente_id = c.cliente_id " +
						"WHERE p.estado = ? ORDER BY p.fecha_pedido DESC";
			ps = con.prepareStatement(sql);
			ps.setString(1, estado);
			rsPedido = ps.executeQuery();
			
			while(rsPedido.next()) {
				Pedido pedido = new Pedido();
                pedido.setPedidoId(rsPedido.getInt("pedido_id"));
                pedido.setClienteId(rsPedido.getInt("cliente_id"));
                pedido.setUsuarioId(rsPedido.getInt("usuario_id"));
                pedido.setFechaPedido(rsPedido.getTimestamp("fecha_pedido"));
                pedido.setTipoComprobante(rsPedido.getString("tipo_comprobante"));
                pedido.setNumeroComprobante(rsPedido.getString("numero_comprobante"));
                pedido.setSubtotal(rsPedido.getDouble("subtotal"));
                pedido.setIgv(rsPedido.getDouble("igv"));
                pedido.setTotal(rsPedido.getDouble("total"));
                pedido.setMetodoPago(rsPedido.getString("metodo_pago"));
                pedido.setEstado(rsPedido.getString("estado"));
                pedido.setObservaciones(rsPedido.getString("observaciones"));
				lista.add(pedido);
			}
		} catch (Exception e) {
			System.out.println("Error al listar pedidos por estado: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public ArrayList<Pedido> listarPorFecha(String fechaInicio, String fechaFin) {
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rsPedido = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT p.*, c.nombre as cliente_nombre FROM pedido p " +
						"INNER JOIN cliente c ON p.cliente_id = c.cliente_id " +
						"WHERE DATE(p.fecha_pedido) BETWEEN ? AND ? " +
						"ORDER BY p.fecha_pedido DESC";
			ps = con.prepareStatement(sql);
			ps.setString(1, fechaInicio);
			ps.setString(2, fechaFin);
			rsPedido = ps.executeQuery();
			
			while(rsPedido.next()) {
				Pedido  pedido = new Pedido();
                pedido.setPedidoId(rsPedido.getInt("pedido_id"));
                pedido.setClienteId(rsPedido.getInt("cliente_id"));
                pedido.setUsuarioId(rsPedido.getInt("usuario_id"));
                pedido.setFechaPedido(rsPedido.getTimestamp("fecha_pedido"));
                pedido.setTipoComprobante(rsPedido.getString("tipo_comprobante"));
                pedido.setNumeroComprobante(rsPedido.getString("numero_comprobante"));
                pedido.setSubtotal(rsPedido.getDouble("subtotal"));
                pedido.setIgv(rsPedido.getDouble("igv"));
                pedido.setTotal(rsPedido.getDouble("total"));
                pedido.setMetodoPago(rsPedido.getString("metodo_pago"));
                pedido.setEstado(rsPedido.getString("estado"));
                pedido.setObservaciones(rsPedido.getString("observaciones"));
				lista.add(pedido);
			}
		} catch (Exception e) {
			System.out.println("Error al listar pedidos por fecha: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista;
	}

	@Override
	public int actualizar(Pedido pedido) {
		// TODO Auto-generated method stub
		int value = 0;
		Connection con = null;
		PreparedStatement psPedido = null;
		PreparedStatement psDetalle = null;

		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);

			// Actualizar pedido principal
			String sqlPedido = "UPDATE pedido SET cliente_id = ?, usuario_id = ?, fecha_pedido = ?, " +
							  "tipo_comprobante = ?, numero_comprobante = ?, subtotal = ?, igv = ?, " +
							  "total = ?, metodo_pago = ?, estado = ?, observaciones = ? WHERE pedido_id = ?";

			psPedido = con.prepareStatement(sqlPedido);
			psPedido.setInt(1, pedido.getClienteId());
			psPedido.setInt(2, pedido.getUsuarioId());
			psPedido.setTimestamp(3, new java.sql.Timestamp(pedido.getFechaPedido().getTime()));
			psPedido.setString(4, pedido.getTipoComprobante());
			psPedido.setString(5, pedido.getNumeroComprobante());
			psPedido.setDouble(6, pedido.getSubtotal());
			psPedido.setDouble(7, pedido.getIgv());
			psPedido.setDouble(8, pedido.getTotal());
			psPedido.setString(9, pedido.getMetodoPago());
			psPedido.setString(10, pedido.getEstado());
			psPedido.setString(11, pedido.getObservaciones());
			psPedido.setInt(12, pedido.getPedidoId());

			value = psPedido.executeUpdate();

			// Actualizar detalles si existen
			if (pedido.getDetalles() != null && !pedido.getDetalles().isEmpty()) {
				// Primero eliminar detalles existentes
				String sqlDeleteDetalles = "DELETE FROM DetallePedido WHERE pedido_id = ?";
				PreparedStatement psDelete = con.prepareStatement(sqlDeleteDetalles);
				psDelete.setInt(1, pedido.getPedidoId());
				psDelete.executeUpdate();
				psDelete.close();

				// Insertar nuevos detalles
				String sqlDetalle = "INSERT INTO DetallePedido (pedido_id, producto_id, cantidad, precio_unitario, subtotal) " +
								   "VALUES (?, ?, ?, ?, ?)";

				psDetalle = con.prepareStatement(sqlDetalle);

				for (DetallePedido detalle : pedido.getDetalles()) {
					psDetalle.setInt(1, pedido.getPedidoId());
					psDetalle.setInt(2, detalle.getProductoId());
					psDetalle.setInt(3, detalle.getCantidad());
					psDetalle.setDouble(4, detalle.getPrecioUnitario());
					psDetalle.setDouble(5, detalle.getSubtotal());
					psDetalle.addBatch();
				}

				psDetalle.executeBatch();
			}

			con.commit();

		} catch (Exception e) {
			try {
				if (con != null) {
					con.rollback();
				}
				System.out.println("Error al actualizar pedido: " + e.getMessage());
				e.printStackTrace();
				value = 0;
			} catch (SQLException ex) {
				System.out.println("Error al hacer rollback: " + ex.getMessage());
			}
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return value;
	
	}
	
}
