package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

import dominio.Cliente;
import dominio.RecaudacionTaller;
import dominio.Trabajo;
import dominio.Usuarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImagenDao {
    private static final String SQL_INSERTAR = "insert into Imagen(idTrabajo,imagenTrabajo)values(?,?);";
    
//------------------------------------------------------------------------------
    public void insertar(String ruta,int id) throws FileNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);
            FileInputStream archivo=new FileInputStream(ruta);
            stmt.setInt(1, id);
            stmt.setBlob(2, archivo);
            
            
          
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
}
