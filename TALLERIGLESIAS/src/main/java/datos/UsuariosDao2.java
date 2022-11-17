package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.Trabajo;
import dominio.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDao2 {
    private static final String SQL_SELECT = "SELECT idusuario,nombre,aes_decrypt(contrasenia,'clave') as contrasenia,tipo from usuarios;";
    
    private static final String SQL_INSERTAR = "INSERT INTO Usuarios (nombre,contrasenia,tipo)values(?,aes_encrypt(?,'clave'),?);";


    public List <Usuarios> seleccionar() throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Trabajo trabajo = null;
        List <Usuarios> usuarios1 = new ArrayList<>();

        conn = getConnection();
        ps = conn.prepareStatement(SQL_SELECT);
        rs = ps.executeQuery();

        while (rs.next()) {
            String nombre,contrasenia;
            int tipo, idusuario;
            
            nombre=rs.getString("nombre");
            contrasenia=rs.getString("contrasenia");
            tipo=rs.getInt("tipo");
            idusuario=rs.getInt("idusuario");

 
            usuarios1.add(new Usuarios(idusuario, nombre, contrasenia, tipo));
        }
        close(rs);
        close(ps);
        close(conn);

        return usuarios1;
    }
//------------------------------------------------------------------------------
    public void insertar(Usuarios usuarios) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);
            
            stmt.setString(1, usuarios.getNombre());
            stmt.setString(2, usuarios.getContrasenia());
            stmt.setInt(3, usuarios.getTipo());
           

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
}
