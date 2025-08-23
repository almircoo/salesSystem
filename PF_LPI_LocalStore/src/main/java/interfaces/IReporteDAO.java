package interfaces;

import java.util.ArrayList;
import java.util.Map;

public interface IReporteDAO {
	
    Map<String, Object> obtenerEstadisticasGenerales();

    ArrayList<Map<String, Object>> obtenerVentasPorFecha(String fechaInicio, String fechaFin);
    ArrayList<Map<String, Object>> obtenerProductosMasVendidos();
    ArrayList<Map<String, Object>> obtenerProductosStockBajo();
    ArrayList<Map<String, Object>> obtenerClientesMasActivos();

}
