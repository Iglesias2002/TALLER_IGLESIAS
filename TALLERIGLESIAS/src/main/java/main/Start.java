package main;
import java.awt.geom.Arc2D.Float;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import archivo.Archivo;
import datos.ClienteDao;
import datos.ImagenDao;
import datos.RecaudacionTallerDao;
import datos.TrabajoDao;
import datos.UsuariosDao2;
import dominio.Chapa;
import dominio.Cliente;
import dominio.Mecanica;
import dominio.RecaudacionTaller;
import dominio.Reparacion;
import dominio.Revision;
import dominio.Trabajo;
import dominio.Usuarios;
public class Start {
    private static int trabajosMaximos = 10;
//----------------------------------------------------------------------------------------------------------------------------
    public static void menu1(ArrayList < Usuarios > usuarios1, ArrayList < Trabajo > trabajos, ArrayList < RecaudacionTaller > dinero) throws FileNotFoundException {
        Scanner teclado = new Scanner(System.in);
        String aux = "";
        int opcion = 0;
        while (opcion != 3) {
            do {
                System.out.println("     ___________________________________________");
                System.out.println("    | Por favor, seleccione la opci�n deseada: |");
                System.out.println("    |                                          |");
                System.out.println("    | 1. Iniciar sesion.                       |");
                System.out.println("    | 2. Crear usuario.                        |");
                System.out.println("    | 3. Salir.                                |");
                System.out.println("    |__________________________________________|");

                if (teclado.hasNextInt()) {
                    opcion = teclado.nextInt();
                } else {
                    aux = teclado.next();
                }
            } while (opcion <= 0 || opcion >= 4);
            switch (opcion) {
                case 1:
                    loquearse(usuarios1, trabajos, dinero);
                    break;
                case 2:
                    registrarse(usuarios1);
                    break;
                case 3:
                    System.out.println("Adios!!");
                    break;
            }
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void loquearse(ArrayList < Usuarios > usuarios1, ArrayList < Trabajo > trabajos, ArrayList < RecaudacionTaller > dinero) throws FileNotFoundException {
        Usuarios usuario = new Usuarios(0, null, null, 0);
        Trabajo trabajo = new Trabajo(0);
        RecaudacionTaller recaudacionTaller = new RecaudacionTaller(0);
        UsuariosDao2 usuariosDao2 = new UsuariosDao2();
        Scanner teclado1 = new Scanner(System.in);
        Scanner teclado2 = new Scanner(System.in);
        String respuesta = null;
        String valoracion = null;
        String nombre = "";
        String contrasenia = "";
        int opcion = 0;
        int tipo = 0;
        String aux = "";
        System.out.println(" Selecciono Inicio de sesion  ");
        System.out.println(" Introduce el nombre de usuario ");
        nombre = teclado1.nextLine().toLowerCase();
        if (usuarios1.contains(new Usuarios(0, nombre, "", 0))) {
            for (Usuarios u: usuarios1) {
                if (u.getNombre().equals(nombre)) {
                    usuario = u;
                }
            }
            System.out.println(" Introduce la contraseña de " + nombre);
            contrasenia = teclado2.nextLine();

            for (Usuarios u: usuarios1) {
                //------------------------------------------------------------------------------------------------------------------    
                if (u.getNombre().equals(nombre) && u.getContrasenia().equals(contrasenia) && u.getTipo() == (tipo = 2)) { //ADMIN
                    Scanner teclado = new Scanner(System.in);
                    while (opcion != 5) {
                        do {
                            System.out.println("     _________________________");
                            System.out.println("    | 1. Mostrar trabajo     |");
                            System.out.println("    | 2. Eliminar trabajo    |");
                            System.out.println("    | 3. Generar informe     |");
                            System.out.println("    | 4. Vaciar taller       |");
                            System.out.println("    | 5. Cerrar sesion       |");
                            System.out.println("    |________________________|");
                            if (teclado.hasNextInt()) {
                                opcion = teclado.nextInt();
                            } else {
                                aux = teclado.next();
                            }
                        } while (opcion <= 0 || opcion >= 6);
                        switch (opcion) {
                            case 1:
                                mostrarTrabajo(trabajos, dinero);
                                break;
                            case 2:
                                eliminarTrabajo(trabajos);
                                break;
                            case 3:
                                generarInforme(trabajos, dinero);
                                break;
                            case 4:
                                vaciarTaller(trabajos);
                                break;
                            case 5:
                                System.out.println(" SESION CERRADA CON EXITO !!!");
                                break;
                        }
                    }
//------------------------------------------------------------------------------------------------------------------    
                } else if (u.getNombre().equals(nombre) && u.getContrasenia().equals(contrasenia) && u.getTipo() == (tipo = 1)) { //TRABAJADOR
                    Scanner teclado = new Scanner(System.in);
                    while (opcion != 5) {
                        do {
                            System.out.println("    __________________________________________|");
                            System.out.println("    | Por favor, seleccione la opci�n deseada:|");
                            System.out.println("    |                                         |");
                            System.out.println("    | 1. Aumentar horas                       |");
                            System.out.println("    | 2. Aumentar coste de piezas             |");
                            System.out.println("    | 3. Finalizar trabajo                    |");
                            System.out.println("    | 4. Mostrar trabajo                      |");
                            System.out.println("    | 5. Cerrar sesion                        |");
                            System.out.println("    |_________________________________________|");
                            if (teclado.hasNextInt()) {
                                opcion = teclado.nextInt();
                            } else {
                                aux = teclado.next();
                            }
                        } while (opcion <= 0 || opcion >= 6);
                        switch (opcion) {
                            case 1:
                                aumentarHoras(trabajos);
                                break;
                            case 2:
                                aumentarCostes(trabajos);
                                break;
                            case 3:
                                finalizarTrabajo(trabajos);
                                break;
                            case 4:
                                mostrarTrabajo(trabajos, dinero);
                                break;
                            case 5:
                                System.out.println("SESION CERRADA CON EXITO");
                                break;
                        }
                    }
//------------------------------------------------------------------------------------------------------------------    
                } else if (u.getNombre().equals(nombre) && u.getContrasenia().equals(contrasenia) && u.getTipo() == (tipo = 0)) { //CLIENTE
                    Scanner teclado = new Scanner(System.in);
                    while (opcion != 4) {
                        do {
                        	System.out.println("    __________________________________________|");
                            System.out.println("    | Por favor, seleccione la opci�n deseada:|");
                            System.out.println("    |                                         |");
                            System.out.println("    | 1. Registrar trabajo                    |");
                            System.out.println("    | 2. Consultar estado del vehiculo        |");
                            System.out.println("    | 3. Valorar TalleresIgleias              |");
                            System.out.println("    | 4. Cerrar sesion                        |");
                            System.out.println("    |_________________________________________|");
                            
                            if (teclado.hasNextInt()) {
                                opcion = teclado.nextInt();
                            } else {
                                aux = teclado.next();
                            }
                        } while (opcion <= 0 || opcion >= 5);
                        switch (opcion) {
                            case 1:
                                if (trabajos.size() < trabajosMaximos) {
                                    registrarTrabajo(trabajos, nombre);
                                } else if (trabajos.size() >= trabajosMaximos) {
                                    System.out.println(" El Taller se encuentra lleno ");
                                    System.out.println(" Disculpe las molestias ");
                                }
                                break;
                            case 2:
                                ClienteDao clienteDao = new ClienteDao();
                                ArrayList < Usuarios > usuarios = new ArrayList < Usuarios > ();
                                ArrayList < Cliente > cliente = new ArrayList < Cliente > ();

                                try {
                                    System.out.println("________________________________________________");
                                    System.out.println(" Mostrando sus trabajos " + nombre);
                                    cliente = (ArrayList < Cliente > ) clienteDao.seleccionarPalabra1(nombre);
                                    cliente.forEach(taller -> {
                                        System.out.println(taller);
                                    });
                                } catch (SQLException ex) {
                                    ex.printStackTrace(System.out);
                                }
                                System.out.println("________________________________________________");
                                break;
                            case 3:
                                clienteDao = new ClienteDao();
                                Scanner teclado4 = new Scanner(System.in);
                                Scanner teclado5 = new Scanner(System.in);
                                ArrayList < Cliente > cliente1 = new ArrayList < Cliente > ();
                                try {
                                    System.out.println(" Mostrando sus trabajos " + nombre + "...");
                                    cliente1 = (ArrayList < Cliente > ) clienteDao.seleccionarPalabra1(nombre);
                                    cliente1.forEach(taller -> {
                                        System.out.println(taller);
                                    });
                                } catch (SQLException ex) {
                                    ex.printStackTrace(System.out);
                                }
                                System.out.println(" Que trabajo quieres valorar(Indique el identificador)");

                                if (teclado4.hasNextInt()) {
                                    opcion = teclado4.nextInt();
                                } else {
                                    aux = teclado4.next();
                                }
                                if (cliente1.contains(new Cliente(opcion))) {
                                    for (int i = 0; i < cliente1.size(); i++) {
                                        if (cliente1.get(i).getIdentificador() == opcion && cliente1.get(i).getNombre().equals(nombre)) {
                                            System.out.println(" Deje aqui la valoracion del trabajo realizado");
                                            valoracion = teclado5.nextLine();
                                            clienteDao.modificarValoracion(valoracion, opcion, nombre);
                                        } else {
                                            if (i >= cliente1.size()) {
                                                System.out.println(" No encontramos ese trabajo");
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println(" No tienes ese trabajo");
                                }
                                break;
                            case 4:
                                System.out.println(" SESION CERRADA CON EXITO");
                                break;
                        }
                    }
//------------------------------------------------------------------------------------------------------------------    	
                } else if (u.getNombre().equals(nombre) && !u.getContrasenia().equals(contrasenia) && u.getTipo() == (tipo = 0)) {
                    System.out.println("error contraseja1");
                } else if (u.getNombre().equals(nombre) && !u.getContrasenia().equals(contrasenia) && u.getTipo() == (tipo = 1)) {
                    System.out.println("error contraseja2");
                } else if (u.getNombre().equals(nombre) && !u.getContrasenia().equals(contrasenia) && u.getTipo() == (tipo = 2)) {
                    System.out.println("error contraseja3");
                }
            }
        } else {
            System.out.println(" USUARIO NO Encontrado !!!");
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------
    public static void registrarse(ArrayList < Usuarios > usuarios1) {
        Usuarios usuario = new Usuarios(0, null, null, 0);
        UsuariosDao2 usuariosDao2 = new UsuariosDao2();
        Scanner teclado1 = new Scanner(System.in);
        Scanner teclado2 = new Scanner(System.in);
        Scanner teclado3 = new Scanner(System.in);
        Scanner teclado4 = new Scanner(System.in);
        String nombre = "";
        String contrasenia = "";
        String contraseniaAdmin = "";
        int tipo = 0;
        boolean acceso = false;
        System.out.println(" ");
        System.out.println(" Registrate con nosotros (TallerES-Iglesias)");
        do {
            do {
                System.out.println("Introduce el nombre de usuario");
                nombre = teclado1.nextLine().toLowerCase();
                if (usuarios1.contains(new Usuarios(0, nombre, "", 0))) {
                    for (Usuarios u: usuarios1) {
                        if (u.getNombre().equals(nombre)) {
                            usuario = u;
                            System.out.println("ERROR -- El usuario " + nombre + " YA EXISTE!!");
                        }
                    }
                } else {

                }
            } while (usuarios1.contains(new Usuarios(0, nombre, "", 0)));
            do {
                System.out.println(" Introduce el contrasenia de " + nombre + " entre (min 4-max 10 caracteres)");
                contrasenia = teclado2.nextLine();
                if (contrasenia.length() < 4 || contrasenia.length() > 10) {
                    System.out.println(" ERROR La contraseña no cumple las caracteristicas de tamaño");
                }
            } while (contrasenia.length() < 4 || contrasenia.length() > 10);
            do {
                System.out.println("    ___________________________________ ");
                System.out.println("    | Introduce el tipo de " + nombre );
                System.out.println("    |                                 |");
                System.out.println("    | 0. Cliente                      |");
                System.out.println("    | 1. Trabajador                   |");
                System.out.println("    | 2. Administrador                |");
                System.out.println("    |_________________________________| ");

                tipo = teclado3.nextInt();
                if (tipo == 2 || tipo == 1) {
                    System.out.println(" Selecionaste " + tipo + "  ");
                    System.out.println(" Por seguridad tenemos que verificar con la contraseña del taller");
                    System.out.println(" Inroduzca la contraseña del taller :");
                    contraseniaAdmin = teclado4.nextLine();
                    if (contraseniaAdmin.equals("1234")) {
                        System.out.println(" Correcto usuario creado ");
                        acceso = true;
                    } else {
                        System.out.println(" Denegado usuario no creado ");
                    }
                } else if (tipo == 0) {
                    System.out.println(" Correcto usuario creado ");
                    acceso = true;
                } else {
                    System.out.println(" tipo incorrecto ");
                }
            } while (tipo < 0 || tipo > 2);
        } while (acceso != true);
        Usuarios usuarios = new Usuarios(0, nombre, contrasenia, tipo);
        usuarios1.add(new Usuarios(0, nombre, contrasenia, tipo));
        usuariosDao2.insertar(usuarios);
    }
//---------------------------------------------------------------------------------------------------------------------------
    public static int registrarTrabajo(ArrayList < Trabajo > trabajos, String nombre) {
        TrabajoDao trabajodao = new TrabajoDao();
        ClienteDao clienteDao = new ClienteDao();
        Scanner teclado = new Scanner(System.in);
        Scanner teclado1 = new Scanner(System.in);
        int opcion = 0;
        int id;
        float costes = 0;
        String aux = "";
        String descripcion = "";
        do {
            System.out.println("     ______________________________________________________________");
            System.out.println("    | Por favor " + nombre + ", introduzca el tipo de trabajo      ");
            System.out.println("    |                                                              |");
            System.out.println("    | 1. Mec�nica                                                  |");
            System.out.println("    | 2. Chapa                                                     |");
            System.out.println("    | 3. Revisi�n                                                  |");
            System.out.println("    |______________________________________________________________|");
            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
            } else {
                aux = teclado.next();
            }
        } while (opcion <= 0 || opcion >= 4);
        if (trabajos.isEmpty()) {
            id = trabajos.size(); //tamaño 0
        } else {
            id = trabajos.get(trabajos.size() - 1).getIdentificador();
            id = id + 1;
        }
        		do {
        			System.out.println(" Por favor, introduzca la descripci�n ");
            			descripcion = teclado1.nextLine();
            			if(descripcion.length()>150) {
            				System.out.println("Descripcion demasiado extensa");
            			}
            	}while(descripcion.length()>150);
        switch (opcion) {
            case 1:
                Trabajo trabajo = new Trabajo(id, nombre, 0, opcion, descripcion, false);
                trabajos.add(new Mecanica(id, descripcion, costes));
                trabajodao.insertar(trabajo);
                clienteDao.insertar(nombre, id);
                System.out.println(" Su identificador es " + (id));
                break;
            case 2:
                trabajo = new Trabajo(id, nombre, 0, opcion, descripcion, false);
                trabajos.add(new Chapa(id, descripcion, costes));
                trabajodao.insertar(trabajo);
                clienteDao.insertar(nombre, id);
                System.out.println(" Su identificador es " + (id));
                break;
            case 3:
                trabajo = new Trabajo(id, nombre, 0, opcion, descripcion, false);
                trabajos.add(new Revision(id, descripcion));
                trabajodao.insertar(trabajo);
                clienteDao.insertar(nombre, id);
                System.out.println(" Su identificador es " + (id));
                break;
        }
        return id;
    }
//---------------------------------------------------------------------------------------------------------------------------
    public static void aumentarHoras(ArrayList < Trabajo > trabajos) {
        TrabajoDao trabajodao = new TrabajoDao();
        Scanner teclado1 = new Scanner(System.in);
        int id = 0;
        String aux = "";
        String descripcion = "";


        do {
            System.out.println(" Introduzca el identificador");
            if (teclado1.hasNextInt()) {
                id = teclado1.nextInt();
            } else {
                aux = teclado1.next();
            }
        } while (id < 0);
        int numHoras = 0;

        if (trabajos.contains(new Trabajo(id, descripcion))) {
            for (int i = 0; i < trabajos.size(); i++) {
                if (trabajos.get(i).getIdentificador() == id) {
                    if (trabajos.get(i).isFinalizado() == false) {
                        do {
                            System.out.println(" Introduzca el numero de horas");
                            if (teclado1.hasNextInt()) {
                                numHoras = teclado1.nextInt();
                            } else {
                                aux = teclado1.next();
                            }
                        } while (numHoras <= 0);

                        trabajos.get(i).aumentarHoras(numHoras);
                        Trabajo trabajo = new Trabajo(id, numHoras);
                        trabajodao.updatear(id, numHoras);
                        System.out.println(trabajodao.updatear(trabajo.getNumHoras(), trabajo.getIdentificador()));
                        System.out.println(" Se ha aumentado las horas en el identificador " + id);
                    } else {
                        System.out.println(" El trabajo ha sido finalizado");
                    }
                }
            }
        } else {
            System.out.println(" Ese identificador no existe (" + id + ")");
        }
    }
//---------------------------------------------------------------------------------------------------------------------------
    public static void aumentarCostes(ArrayList < Trabajo > trabajos) {
        TrabajoDao trabajodao = new TrabajoDao();
        Scanner teclado1 = new Scanner(System.in);
        String aux = "";
        int id = 0;
        String descripcion = "";
        float costes = 0;
        do {
            System.out.println(" Introduzca el identificador");
            if (teclado1.hasNextInt()) {
                id = teclado1.nextInt();
            } else {
                aux = teclado1.next();
            }
        } while (id < 0);
        if (trabajos.contains(new Trabajo(id, descripcion))) {
            for (int i = 0; i < trabajos.size(); i++) {
                if (trabajos.get(i).getIdentificador() == id) {
                    if (trabajos.get(i).isFinalizado() == false) {
                        do {
                            System.out.println(" Introduzca el costo de piezas");
                            if (teclado1.hasNextFloat()) {
                                costes = teclado1.nextFloat();
                            } else {
                                aux = teclado1.next();
                            }
                        } while (costes <= 0);

                        trabajos.get(i).aumentarCostes(costes);
                        Trabajo trabajo = new Trabajo(id, costes);
                        System.out.println(trabajodao.updatearPiezas(trabajo.getCoste(), trabajo.getIdentificador()));
                        System.out.println(" Su identificador es " + id);
                    } else {
                        System.out.println(" Ese trabajo esta finalizado, por lo que no se pueden aumentar los costes");
                    }
                }
            }

        } else {
            System.out.println(" Ese identificador no existe (" + id + ")");
        }
    }
//---------------------------------------------------------------------------------------------------------------------------
    public static void finalizarTrabajo(ArrayList < Trabajo > trabajos) throws FileNotFoundException {
        TrabajoDao trabajodao = new TrabajoDao();
        ClienteDao clienteDao = new ClienteDao();
        ImagenDao imagenDao = new ImagenDao();
        Scanner teclado1 = new Scanner(System.in);

        int id = 0;
        String aux = "";
        String recoger = "Ya puede recoger el vehiculo";
        String descripcion = "";
        do {
            System.out.println("Introduzca el identificador");
            if (teclado1.hasNextInt()) {
                id = teclado1.nextInt();
            } else {
                aux = teclado1.next();
            }
        } while (id < 0);
        if (trabajos.contains(new Trabajo(id, descripcion))) {
            for (int i = 0; i < trabajos.size(); i++) {
                if (trabajos.get(i).getIdentificador() == id) {
                    if (trabajos.get(i).isFinalizado() == false) {

                        trabajos.get(i).finalizarTrabajo();
                        System.out.println(" Finalizado!!!");
                        System.out.println(" El identificador es " + id);
                        Trabajo trabajo = new Trabajo(id, false);

                        trabajodao.modificar(trabajo);

                        float dinero = trabajos.get(i).costeTotal();

                        trabajodao.insertarTabla(id, dinero, id);
                        System.out.println(dinero);
                        clienteDao.modificar(recoger, dinero, id);
                        String ruta = "E:\\TALLER-IGLESIAS.jpg";
                        imagenDao.insertar(ruta, id);
                    } else {
                        System.out.println(" Ese trabajo ya estaba finalizado (" + id + ")");
                    }
                }
            }
        } else {
            System.out.println(" Ese identificador no existe por lo que no se puede finalizar (" + id + ")");
        }
    }
//---------------------------------------------------------------------------------------------------------------------------
    public static void mostrarTrabajo(ArrayList < Trabajo > trabajos, ArrayList < RecaudacionTaller > dinero) {
        Scanner teclado1 = new Scanner(System.in);
        Scanner teclado2 = new Scanner(System.in);
        Scanner teclado3 = new Scanner(System.in);

        String descripcion = "";
        String aux = "";
        int id = 0;
        int opcion = 0;

        RecaudacionTallerDao recaudacionTallerDao = new RecaudacionTallerDao();
        TrabajoDao trabajoDao = new TrabajoDao();
        do {
            System.out.println("     _______________________________________________");
            System.out.println("    | Indique el filtro para visualizar el trabajo: |");
            System.out.println("    |                                               |");
            System.out.println("    |  1. Filtrar por ID                            |");
            System.out.println("    |  2. Visualizar todos los trabajos             |");
            System.out.println("    |  3. Visualizar los trabajos ( ACTIVOS )       |");
            System.out.println("    |  4. Visualizar los trabajos ( FINALIZADOS )   |");
            System.out.println("    |  5. Buscar trabajo por descripcion            |");
            System.out.println("    |_______________________________________________|");
            if (teclado2.hasNextInt()) {
                opcion = teclado2.nextInt();
            } else {
                aux = teclado2.next();
            }
        } while (opcion <= 0 || opcion >= 6);
        switch (opcion) {
            case 1:
                System.out.println(" SELECCIONO EL TRABAJO FILTRANDO POR ID ");
                do {
                    System.out.println(" Introduzca el identificador");
                    if (teclado1.hasNextInt()) {
                        id = teclado1.nextInt();
                    } else {
                        aux = teclado1.next();
                    }
                } while (id < 0);
                if (trabajos.contains(new Trabajo(id, descripcion))) {
                    for (int i = 0; i < trabajos.size(); i++) {
                        if (trabajos.get(i).getIdentificador() == id) {
                            trabajos.get(i).mostrarTrabajo();
                        }
                    }
                } else {
                    System.out.println(" Ese identificador no existe por lo que no se puede finalizar (" + id + ")");
                }
                break;
            case 2:
                System.out.println(" SELECCIONO VISUALIZAR TODOS LOS TRABAJOS ");
                try {
                    trabajos = (ArrayList < Trabajo > ) trabajoDao.seleccionar();;
                    trabajos.forEach(taller -> {
                        System.out.println(taller);
                    });
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                }
                break;
            case 3:
                System.out.println(" SELECCIONO VISUALIZAR TODOS LOS TRABAJOS --> (Activo) ");
                try {
                    trabajos = (ArrayList < Trabajo > ) trabajoDao.seleccionarActivo();;
                    trabajos.forEach(taller -> {
                        System.out.println(taller);
                    });
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                }
                break;
            case 4:
                System.out.println(" SELECCIONO VISUALIZAR TODOS LOS TRABAJOS --> (Finalizado) ");
                try {
                    dinero = (ArrayList < RecaudacionTaller > ) recaudacionTallerDao.seleccionar();
                    dinero.forEach(taller -> {
                        System.out.println(taller);
                    });
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                }
                break;
            case 5:
                System.out.println(" Buscar por descripcion ");
                System.out.println(" Introduzca la palabra de la descripcion");
                if (teclado3.hasNextLine()) {
                    descripcion = teclado3.nextLine();
                } else {
                    aux = teclado3.next();
                }
                System.out.println(" LOS TRABAJOS FINALIZADOS SON:");
                try {
                    dinero = (ArrayList < RecaudacionTaller > ) recaudacionTallerDao.seleccionarPalabra(descripcion);;
                    dinero.forEach(taller -> {
                        System.out.println(taller);
                    });
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);

                }
                System.out.println(" LOS TRABAJOS EN CURSO SON:");
                try {
                    trabajos = (ArrayList < Trabajo > ) trabajoDao.seleccionarPalabra(descripcion);;
                    trabajos.forEach(taller -> {
                        System.out.println(taller);
                    });
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                }
                break;
        }
    }
//---------------------------------------------------------------------------------------------------------------------------
    public static void generarInforme(ArrayList < Trabajo > trabajos, ArrayList < RecaudacionTaller > dinero) {
        RecaudacionTallerDao recaudacionTallerDao = new RecaudacionTallerDao();
        TrabajoDao trabajoDao = new TrabajoDao();
        Archivo archivo1 = new Archivo();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        String informe = "Informe_Talleres_Iglesias_" + formatter.format(date);
        String contenido = "";
        try {
            trabajos = (ArrayList < Trabajo > ) trabajoDao.seleccionar();
            trabajos.forEach(taller -> {});
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        archivo1.crear(informe);
        for (int i = 0; i < trabajos.size(); i++) {
            //System.out.println(trabajos.get(i).toString());
            contenido = contenido + " \n" + trabajos.get(i).toString();
            archivo1.ecribirArchivo(informe, contenido);
        }
        System.out.println(" Informe generado con exito " + informe + ".txt");
        System.out.println("                                          ");
    }
//---------------------------------------------------------------------------------------------------------------------------
    public static void eliminarTrabajo(ArrayList < Trabajo > trabajos) {
        TrabajoDao trabajodao = new TrabajoDao();
        ClienteDao clienteDao = new ClienteDao();

        Scanner teclado1 = new Scanner(System.in);
        Scanner teclado2 = new Scanner(System.in);

        String seguro = "";
        String aux = "";
        int id = 0;
        do {
            System.out.println(" Introduzca el identificador");
            if (teclado1.hasNextInt()) {
                id = teclado1.nextInt();
            } else {
                aux = teclado1.next();
            }
        } while (id < 0);
        if (trabajos.contains(new Trabajo(id))) {
            for (int i = 0; i < trabajos.size(); i++) {
                if (trabajos.get(i).getIdentificador() == id) {
                    if (trabajos.get(i).isFinalizado() == false) {
                        System.out.println(" ESTAS SEGURO DE ELIMINAR EL CONTENIDO DE LA ID " + id);
                        if (teclado2.hasNextLine()) {
                            seguro = teclado2.nextLine().toUpperCase();
                        } else {
                            aux = teclado2.next();
                        }
                        if (seguro.equals("SI")) {
                            System.out.println("Eliminado");
                            Trabajo trabajo = new Trabajo(id);
                            trabajos.remove(i);
                            for (int j = 0; j < trabajos.size(); j++) {
                                System.out.println(trabajos.get(j).toString());
                            }
                            trabajodao.eliminar(trabajo);
                            clienteDao.eliminar(trabajo);
                        } else {
                            System.out.println(" No se elimino");
                        }
                    } else {
                        System.out.println(" Ese trabajo esta finalizado, por lo que no se pueden sacar del taller se encuantra ya en el historial");
                    }
                }
            }
        } else {
            System.out.println(" Ese identificador no existe (" + id + ")");
        }
    }
//---------------------------------------------------------------------------------------------------------------------------
    public static void vaciarTaller(ArrayList < Trabajo > trabajos) {
        TrabajoDao trabajodao = new TrabajoDao();
        Scanner teclado1 = new Scanner(System.in);
        Scanner teclado2 = new Scanner(System.in);

        String aux = "";
        int id = 0;
        String seguro = "";
        do {
            System.out.println(" Introduzca el identificador");
            if (teclado1.hasNextInt()) {
                id = teclado1.nextInt();
            } else {
                aux = teclado1.next();
            }
        } while (id < 0);
        if (trabajos.contains(new Trabajo(id))) {
            if (trabajos.get(id).isFinalizado() == true) {
                System.out.println(" ESTAS SEGURO DE ENTREGAR EL VEHICULO DE LA ID " + id);
                if (teclado2.hasNextLine()) {
                    seguro = teclado2.nextLine().toUpperCase();
                } else {
                    aux = teclado2.next();
                }
                if (seguro.equals("SI")) {
                    System.out.println("Eliminado");
                    Trabajo trabajo = new Trabajo(id);
                    trabajos.remove(id);
                    for (int i = 0; i < trabajos.size(); i++) {
                        System.out.println(trabajos.get(i).toString());
                    }
                    trabajodao.eliminar(trabajo);
                } else {
                    System.out.println(" No se ENTREGO el vehiculo");
                }
            } else {
                System.out.println(" Ese trabajo no se termino");
            }
        } else {
            System.out.println(" Ese identificador no existe (" + id + ")");
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) throws FileNotFoundException {
        TrabajoDao trabajoDao = new TrabajoDao();
        UsuariosDao2 usuariosDao2 = new UsuariosDao2();
        ClienteDao clienteDao = new ClienteDao();
        ArrayList < Usuarios > usuarios = new ArrayList < Usuarios > ();
        ArrayList < RecaudacionTaller > dinero = new ArrayList < RecaudacionTaller > ();
        ArrayList < Trabajo > trabajos = new ArrayList < Trabajo > ();
        ArrayList < Cliente > cliente = new ArrayList < Cliente > ();
        try {
            usuarios = (ArrayList < Usuarios > ) usuariosDao2.seleccionar();
            usuarios.forEach(taller -> {
                //System.out.println(taller);
            });
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        try {
            trabajos = (ArrayList < Trabajo > ) trabajoDao.seleccionar();
            trabajos.forEach(taller -> {
                //System.out.println(taller);
            });
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        try {
            cliente = (ArrayList < Cliente > ) clienteDao.seleccionar();
            cliente.forEach(taller -> {
                //System.out.println(taller);
            });
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }  
        try {
            System.out.println("......__________         |---------------------|");
            System.out.println("..\\___/____|____\\___     |     Bienvenido      |");
            System.out.println(".(_---_________ _---_)   |          A          |");
            System.out.println("....(o).........:(o)     |  TALLERES-IGLESIAS  |");
            System.out.println("                         |---------------------|");
            Thread.sleep(4*1000);
         } catch (Exception e) {
        	 System.out.println();
         }
        
       
        
        menu1(usuarios, trabajos, dinero);
    }
}
//-----------------------------------------------------------------------------------------------------------------------------------------------------