package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dominio.RecaudacionTaller;
import dominio.Trabajo;

public class RecaudacionTallerDao {
	  public String aumentarHoras(String descripcion) {
	        return "SELECT * FROM RecaudacionTaller WHERE descripcion LIKE '%"+descripcion+"%' ;";
	    }
    private static final String SQL_SELECTDINERO = "SELECT * from RecaudacionTaller";
    public List <RecaudacionTaller> seleccionar() throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Trabajo trabajo = null;
        List <RecaudacionTaller> dinero1 = new ArrayList<>();

        conn = getConnection();
        ps = conn.prepareStatement(SQL_SELECTDINERO);
        rs = ps.executeQuery();

        while (rs.next()) {
        	
            int idregistro = rs.getInt("idregistro");
            int horas = rs.getInt("horas");
            float piezas = rs.getFloat("piezas");
            String descripcion = rs.getString("descripcion");
            int tipo = rs.getInt("tipo");
            boolean finalizado = rs.getBoolean("estado");
            float dinero = rs.getFloat("dinero");
            String valoracionCliente = rs.getString("valoracionCliente");
            LocalDateTime fecha = rs.getTimestamp("horaFinalizacion").toLocalDateTime();
            
 
            dinero1.add(new RecaudacionTaller( idregistro,  horas,  piezas,  descripcion,  tipo,  finalizado, dinero,valoracionCliente, fecha));
        }
        close(rs);
        close(ps);
        close(conn);

        return dinero1;
    }
  
    public List <RecaudacionTaller> seleccionarPalabra(String descripcion) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List <RecaudacionTaller> dinero1 = new ArrayList<>();
        conn = getConnection();
        
        ps = conn.prepareStatement(aumentarHoras(descripcion));
        rs = ps.executeQuery();
        
                   
        
        while (rs.next()) {
            int idregistro = rs.getInt("idregistro");
            int horas = rs.getInt("horas");
            float piezas = rs.getFloat("piezas");
            String descripcion1 = rs.getString("descripcion");
            int tipo = rs.getInt("tipo");
            boolean finalizado = rs.getBoolean("estado");
            float dinero = rs.getFloat("dinero");
            String valoracionCliente = rs.getString("valoracionCliente");
            LocalDateTime fecha = rs.getTimestamp("horaFinalizacion").toLocalDateTime();
            
 
            dinero1.add(new RecaudacionTaller( idregistro,  horas,  piezas,  descripcion1,  tipo,  finalizado, dinero,valoracionCliente, fecha));
        }
        close(rs);
        close(ps);
        close(conn);

        return dinero1;
    }
//------------------------------------------------------------------------------
}
