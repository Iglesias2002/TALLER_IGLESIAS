package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

import dominio.RecaudacionTaller;
import dominio.Trabajo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrabajoDao {
    
    private static final String SQL_SELECT = "SELECT * from trabajo";
    
    private static final String SQL_SELECTActivo = "SELECT * from trabajo where estado = 0;";
    
    private static final String SQL_INSERT = "insert into trabajo(nombreCliente,idregistro,horas,piezas,descripcion,estado,tipo)values(?,?,0,0,?,?,?);";
    
    private static final String SQL_INSERTCOSTES = "INSERT INTO RecaudacionTaller  SELECT  trabajo.idregistro,trabajo.horas,trabajo.piezas,trabajo.descripcion,trabajo.tipo,trabajo.estado,?,cliente.valoracionCliente,? FROM trabajo,cliente WHERE trabajo.idregistro=? and cliente.identificadorTrabajo=?;";

    private static final String SQL_UPDATE = "update trabajo set estado = ? where idregistro = ?;";
    
    private static final String SQL_DELETE = "delete from trabajo where idregistro = ?";
    
    public String aumentarHoras(int horas,int idRegistro) {
        return "UPDATE trabajo SET horas=horas+" + String.valueOf(horas)  + " where idregistro=" + String.valueOf(idRegistro);
    }
    public String aumentarCoste(float piezas,int idRegistro) {
        return "UPDATE trabajo SET piezas=piezas+" + String.valueOf(piezas)  + " where idregistro=" + String.valueOf(idRegistro);
    }
    public String aumentarHoras(String descripcion) {
        return "SELECT * FROM trabajo WHERE descripcion LIKE '%"+descripcion+"%' ;";
    }
    public List <Trabajo> seleccionar() throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Trabajo trabajo = null;
        List <Trabajo> trabajo1 = new ArrayList<>();

        conn = getConnection();
        ps = conn.prepareStatement(SQL_SELECT);
        rs = ps.executeQuery();

        while (rs.next()) {
            int idregistro = rs.getInt("idregistro");
            String nombreCliente = rs.getString("nombreCliente");
            int horas = rs.getInt("horas");
            int tipo = rs.getInt("tipo");
            float piezas = rs.getFloat("piezas");
            String descripcion = rs.getString("descripcion");
            boolean finalizado = rs.getBoolean("estado");
 
            trabajo1.add(new Trabajo(idregistro,nombreCliente,horas,descripcion,finalizado,piezas,tipo));
        }
        close(rs);
        close(ps);
        close(conn);

        return trabajo1;
    }
    public List <Trabajo> seleccionarActivo() throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Trabajo trabajo = null;
        List <Trabajo> trabajo1 = new ArrayList<>();

        conn = getConnection();
        ps = conn.prepareStatement(SQL_SELECTActivo);
        rs = ps.executeQuery();

        while (rs.next()) {
            int idregistro = rs.getInt("idregistro");
            String nombreCliente = rs.getString("nombreCliente");
            int horas = rs.getInt("horas");
            int tipo = rs.getInt("tipo");
            float piezas = rs.getFloat("piezas");
            String descripcion = rs.getString("descripcion");
            boolean finalizado = rs.getBoolean("estado");
 
            trabajo1.add(new Trabajo(idregistro,nombreCliente,horas,descripcion,finalizado,piezas,tipo));
        }
        close(rs);
        close(ps);
        close(conn);

        return trabajo1;
    }
    public List <Trabajo> seleccionarPalabra(String descripcion) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Trabajo trabajo = null;
        List <Trabajo> dinero1 = new ArrayList<>();
        conn = getConnection();
        
        ps = conn.prepareStatement(aumentarHoras(descripcion));
        rs = ps.executeQuery();
        
                   
        
        while (rs.next()) {
            int idregistro = rs.getInt("idregistro");
            String nombreCliente = rs.getString("nombreCliente");
            int horas = rs.getInt("horas");
            int tipo = rs.getInt("tipo");
            float piezas = rs.getFloat("piezas");
            String descripcion1 = rs.getString("descripcion");
            boolean finalizado = rs.getBoolean("estado");
 
            dinero1.add(new Trabajo(idregistro,nombreCliente,horas,descripcion1,finalizado,piezas,tipo));
        }
        close(rs);
        close(ps);
        close(conn);

        return dinero1;
    }
//------------------------------------------------------------------------------
   public void insertar(Trabajo trabajo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, trabajo.getNombreCliente());
            stmt.setInt(2, trabajo.getIdentificador());
            stmt.setString(3, trabajo.getDescripcion());
            stmt.setBoolean(4, trabajo.isFinalizado());
            stmt.setInt(5, trabajo.getTipo());
           

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
   public void insertarTabla(int id,float dinero,int registro) {
       Connection conn = null;
       PreparedStatement stmt = null;
       int registros = 0;
       LocalDateTime hora = LocalDateTime.now();
       try {
           conn = getConnection();
           stmt = conn.prepareStatement(SQL_INSERTCOSTES);
           
           stmt.setFloat(1, dinero);
           stmt.setTimestamp(2, Timestamp.valueOf(hora));
           stmt.setInt(3, id);
           stmt.setInt(4, registro);
           
           registros = stmt.executeUpdate();
       } catch (SQLException ex) {
           ex.printStackTrace(System.out);
       } finally {
           try {
               close(stmt);
               close(conn);
           } catch (SQLException ex) {
               ex.printStackTrace(System.out);
           }
       }
   }
//------------------------------------------------------------------------------
 /*   public  void updatear(Trabajo trabajo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATEHORA);
            stmt.setInt(1, trabajo.getNumHoras());
            
            stmt.setInt(2, trabajo.getIdentificador());
            
            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }*/
   public int updatear(int aumentarHora,int idRegistro) {
       Connection conn = null;
       PreparedStatement ps = null;
       int registros = 0;

       try {
           //1. Establecemos la conexion
           conn = getConnection();
           ps = conn.prepareStatement(aumentarHoras(aumentarHora,idRegistro));
           registros = ps.executeUpdate();
       } catch (SQLException ex) {
           ex.printStackTrace(System.out);
       } finally {
           try {
               close(ps);
               close(conn);
           } catch (SQLException ex) {
               ex.printStackTrace(System.out);
           }
       }
       return registros;

   }
//------------------------------------------------------------------------------
   /* public void updatearPiezas(Trabajo trabajo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATECOSTE);
            stmt.setFloat(1, trabajo.getCoste());
            
            stmt.setInt(2, trabajo.getIdentificador());
            
            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }*/
    public int updatearPiezas(float aumentarCoste,int idRegistro) {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            //1. Establecemos la conexion
            conn = getConnection();
            ps = conn.prepareStatement(aumentarCoste(aumentarCoste,idRegistro));
            registros = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;

    }
    public int modificar(Trabajo trabajo) {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            //1. Establecemos la conexion
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);

            ps.setBoolean(1, trabajo.isFinalizado());
            ps.setInt(2, trabajo.getIdentificador());
           
            registros = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;

    }
    public int eliminar(Trabajo trabajo) {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);

            ps.setInt(1, trabajo.getIdentificador());
           
            registros = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
