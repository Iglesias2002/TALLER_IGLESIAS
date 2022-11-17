package archivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Archivo implements Intefaz{
    @Override
    public void crear(String NOMBRE) {
        File archivo = new File (NOMBRE); 
        try {
           PrintWriter salida = new PrintWriter(archivo);
           salida.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }
    @Override
    public void ecribirArchivo(String NOMBRE ,String CONTENIDO) {
        PrintWriter salida = null;
        File archivo = new File (NOMBRE);
        try {
            salida = new PrintWriter(archivo);
            salida.println(CONTENIDO);
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            salida.close();
        }
        
    }
    @Override
    public void leerarchivo(String NOMBRE) {
          File archivo = new File (NOMBRE);
        PrintWriter salida = null;
        BufferedReader entrada = null;

        try {
             entrada = new BufferedReader(new FileReader(NOMBRE));
            String lectura = entrada.readLine();
            while(lectura!=null){
                System.out.println("lectura = "+lectura);
                lectura = entrada.readLine();
                
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void borrarArchivo(String NOMBRE) {
         File archivo = new File (NOMBRE);
         if (archivo.delete()) {
             System.out.println("se borro");
        }else {
             System.out.println("no existe");
         }
    }
}
