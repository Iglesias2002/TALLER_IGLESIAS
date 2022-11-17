package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

import dominio.Cliente;
import dominio.RecaudacionTaller;
import dominio.Trabajo;
import dominio.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    private static final String SQL_SELECT = "SELECT * from cliente;";
    private static final String SQL_UPDATE = "update cliente set estadoVeiculo = ?,costes = ? where identificadorTrabajo = ?";
    private static final String SQL_UPDATE1 = "update cliente set valoracionCliente = ? where identificadorTrabajo = ? and nombre= ?";
    private static final String SQL_INSERTAR = "insert into cliente select distinct usuarios.idusuario,usuarios.nombre,trabajo.idregistro,0,'recepcionado',0 from usuarios,trabajo where usuarios.nombre=? and trabajo.idregistro = ? and usuarios.nombre=trabajo.nombreCliente;";
    private static final String SQL_DELETE = "delete from cliente where identificadorTrabajo = ?";
    public String buscarTrabajos(String descripcion) {
        return "SELECT * FROM cliente WHERE nombre = '"+descripcion+"' ";
    }


    public List <Cliente> seleccionar() throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List <Cliente> cliente = new ArrayList<>();

        conn = getConnection();
        ps = conn.prepareStatement(SQL_SELECT);
        rs = ps.executeQuery();

        while (rs.next()) {
            String nombre,estadoVeiculo,valoracionCliente;
            int identificador,idusuario;
            float costes;
            
            nombre=rs.getString("nombre");
            identificador=rs.getInt("identificadorTrabajo");
            idusuario=rs.getInt("idusuario");
            costes=rs.getFloat("costes");
            estadoVeiculo=rs.getString("estadoVeiculo");
            valoracionCliente=rs.getString("valoracionCliente");
 
            cliente.add(new Cliente( idusuario,  nombre,  identificador,  costes,  estadoVeiculo, valoracionCliente));
        }
        close(rs);
        close(ps);
        close(conn);

        return cliente;
    }
//------------------------------------------------------------------------------
    public void insertar(String usuarios,int trabajo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);
            
            stmt.setString(1, usuarios);
            stmt.setInt(2, trabajo);
            
           

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
    public List <Cliente>  seleccionarPalabra1(String descripcion) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Trabajo trabajo = null;
        List <Cliente> cliente = new ArrayList<>();
        conn = getConnection();
        
        ps = conn.prepareStatement(buscarTrabajos(descripcion));
        rs = ps.executeQuery();
        
                   
        

        while (rs.next()) {
            String nombre,estadoVeiculo,valoracionCliente;
            int identificador, idusuario;
            float costes;
            
            nombre=rs.getString("nombre");
            identificador=rs.getInt("identificadorTrabajo");
            idusuario=rs.getInt("idusuario");
            costes=rs.getFloat("costes");
            estadoVeiculo=rs.getString("estadoVeiculo");
            valoracionCliente=rs.getString("valoracionCliente");
 
            cliente.add(new Cliente( idusuario,  nombre,  identificador,  costes,  estadoVeiculo,valoracionCliente));
        }
        close(rs);
        close(ps);
        close(conn);

        return cliente;
    }
    public int modificar(String recoger, float dinero,int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            //1. Establecemos la conexion
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            
            ps.setString(1, recoger);
            ps.setFloat(2, dinero);
            ps.setInt(3, id);
            
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
    public int modificarValoracion(String valoracion,int id,String nombre) {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            //1. Establecemos la conexion
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE1);
            
            ps.setString(1, valoracion);
            ps.setInt(2, id);
            ps.setString(3, nombre);

            
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
