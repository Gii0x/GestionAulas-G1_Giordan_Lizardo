/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_giordan_lizardo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Gii0
 */
public class Proyecto_Giordan_Lizardo {

    static Scanner lector = new Scanner(System.in);
    static File fichero = new File("..\\Aulas\\clasroom.txt");
    static int MAX = 7;

    public static void main(String[] args) {

        GenerarPerfilAdmin();
        Login();
        //Direccion();
        //creamos un string para almacenar datosque retornen de la funcion.
        String dir = Direccion();
        //imprimimo para visualizar el contenido de la funcion.
        System.out.println(dir);
        //almacenamos el contenido en el fichero " direccion.txt ".
        crearFichero(dir);

    }
    //------------------------------>>>>FUNCIONES<<<<--------------------------------------  

    //--------------------------------------------------------------------------
    //-----------------------        LOGIN        ------------------------------
    //--------------------------------------------------------------------------
    /**
     * Funcion encargada de ejecutar el Login de inicio. Esta función solocitara
     * usuario y contraseña.
     */
    private static void Login() {
        boolean salir = true;
        char resp = ' ';

        do {
            System.out.println("----------Login----------");
            System.out.print("\tUsuario    : ");
            String Usuario = lector.next();
            System.out.print("\tContraseña : ");
            String Contraseña = lector.next();

            //Una vez recogidos los datos insertados por el usuario a continuación leemos el fichero binario y almacenamos en un array.
            Usuarios personal[] = leerFicheroEnMemoria();

            //A continuación realizamos un bucle en el que verificamos si los datos introducidos son validos para acceder al sistema.
            /**
             * El boolean "existe" lo utilizamos para determinar si el usuario
             * indicado existe o no. En caso de no existir no * cumplira la
             * primera condición por lo que finalizara el bucle sin realizar
             * ninguna acción.
             */
            boolean existe = false;
            for (int i = 0; i < personal.length; i++) {
                if (personal[i].Nombre != null && personal[i].IdUsuario.equals(Usuario)) {
                    existe = true;

                    //La siguiente condición se utilizara para verificar la contraseña. 
                    //En caso de coincidir pasara a la siguiente condición en la que se revisara el tipo de ROL.
                    if (personal[i].Contraseña.equals(Contraseña)) {

                        // Luego verificando los datos en el Array Personal comprobaremos a que Rol pertenece dicho usuario y ejecutaremos su correspondiente Menu.
                        if (personal[i].Rol.equals("Admin")) {
                            System.out.println("Bienvenido : " + personal[i].Nombre);
                            MenuAdmin();
                            salir = true;
                            break;
                        } else if (personal[i].Rol.equals("Professor")) {
                            System.out.println("Bienvenido : " + personal[i].Nombre);
                            MenuOpcionesProfessor(fichero);
                            salir = true;
                            break;
                        }
                    }
                    // De lo contrario si la contraseña no coincide, se emitira el siguiente mensaje.
                    System.out.println("Error contraseña incorrecta.");
                }
            }

            // En caso de no existir el usuario indicando se ejecutara la siguiente condición e imprimar por pantalla un mensaje.
            if (existe == false) {
                System.out.println("No existe ningún usuario con el nombre " + Usuario);
            }

            // En la siguiente condición se solicitara si desea continuar al usuario y se almacenara la respuesta en una variable CHAR en la que sera indiferente si es en mayusculas o minusculas.
            // Esta condición activara la salida del Do While, si así lo solicita el usuario. De lo contrario permanecera en el bucle y se retornara a false el boolean "existe" por si el usuario fallo
            // al escribir el usuario.
            System.out.println("¿Deseas continuar?(S/N): ");
            resp = lector.next().toUpperCase().charAt(0);
            if (resp != 'N') {
                salir = false;
                existe = false;
            } else {
                salir = true;
            }

        } while (!salir);
    }

    //--------------------------------------------------------------------------
    //-----------------------      PROFESORES     ------------------------------
    //--------------------------------------------------------------------------
    
    /**
     * Funcion encargada de ejecutar el Menu para el perfil de profesor
     * Este menu enlaza con otras funciones acordes a lo que solicite.
     * @param fichero 
     */
    
    private static void MenuOpcionesProfessor(File fichero) {
        boolean salir = false;
        do {

            System.out.println("---------- MENÚ PROFESOR ----------");
            System.out.println("¿Que acción deseas realizar?");
            System.out.print("\t1. Crear un nuevo registro.\n"
                    + "\t2. Modificar un registro.\n"
                    + "\t3. Eliminiar un registro.\n"
                    + "\t4. Listar registros.\n"
                    + "\t0. Salir \n---->");
            int opcion = lector.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Opcion de Añadir lineas en el fichero");
                    añadirLineasFichero(fichero);
                    break;
                case 2:
                    System.out.println("Opcion de Actualizar linea en el fichero");
                    actualizarLineaFichero(fichero);
                    break;
                case 3:

                    System.out.println("Opcion de Eliminar lineas en el fichero");
                    eliminarLineaFichero(fichero);
                    break;
                case 4:
                    ImprimirDatosFichero(fichero);
                    break;
                case 0:
                    salir = true;
                    break;
                default:

            }

        } while (!salir);
    }

    /**
     * Generamos una funcion que lee el fichero pasado por parametro e imprime
     * los datos de las aulas.
     *
     * @param fichero
     */
    private static void ImprimirDatosFichero(File fichero) {
        String aula = "", descripcion = "", capacidad = "", pc = "", numpc = "", proyectors = "", insonorizado = "";
        String linea = "";
        try {
            // Codificación ISO-8859-1 (ANSI) o UTF-8 dependiendo de cómo esté creado el fichero de texto
            Scanner lectorFichero = new Scanner(fichero); // , "ISO-8859-1"

            while (lectorFichero.hasNext()) {

                System.out.println("");
                linea = lectorFichero.nextLine();
                //Utilizamos el metodo Split para recoger los datos del fichero txt.

                String[] parts = linea.split(",");
                aula = parts[0];
                descripcion = parts[1];
                capacidad = parts[2];
                pc = parts[3];
                numpc = parts[4];
                proyectors = parts[5];
                insonorizado = parts[6];

                System.out.println("Aula = " + aula + "\nDescripción = " + descripcion + "\nCapacidad = " + capacidad + "\nPC = " + pc
                        + "\nNumero de PCs = " + numpc + "\nProyectors = " + proyectors + "\nInsonorización = " + insonorizado);

            }
            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }
        System.out.println("");
    }

    /**
     * Funcion que se encarga de añadir una linea adicional al fichero clasroom
     *
     * @param fichero
     */
    private static void añadirLineasFichero(File fichero) {
        //Generamos 2 arrays uno para almacenar la respuesta del usuario
        //Y otro para imnprimir datos por pantalla.
        String[] array = new String[7];
        String[] datos = new String[7];
        datos[0] = "Aula (Or**) = ";
        datos[1] = "Descripción = ";
        datos[2] = "Capacidad = ";
        datos[3] = "PC (Si/No)= ";
        datos[4] = "Numero de PCs = ";
        datos[5] = "Proyector (Si/No) = ";
        datos[6] = "Insonorización (Si/No) = ";

        //Con el siguiente bucle almacenamos datos en Array e imprimimos por pantalla el dato que solicitamos.
        for (int i = 0, x = 0; i < array.length; i++, x++) {
            System.out.print(datos[x]);
            array[i] = lector.next();
        }
        //Finalmente añadimos el array por partes al documento con el complemento WRITER.
        try {

            FileWriter writer = new FileWriter(fichero, true);

            writer.write(array[0] + "," + array[1] + "," + array[2] + "," + array[3] + "," + array[4] + "," + array[5] + "," + array[6] + "\n");
            //writer.write("\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear/escribir en el fichero");
        }
    }

    /**
     * Funcion encargada de eliminar una linea de registros tomando como
     * referencia el aula.
     *
     * @param fichero
     */
    private static void eliminarLineaFichero(File fichero) {

        //Solicitamos el aula de la cual desea eliminar toda la linea de registro.
        String aula = "";
        Scanner lector1 = new Scanner(System.in);
        System.out.println("Indiqueme que aula desea eliminar (Or**): ");
        aula = lector1.nextLine();

        // Array para guardar todas las líneas leídas del fichero
        ArrayList<String> lineas = new ArrayList<>();

        // Abrimos el fichero de texto para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(fichero);

            while (lectorFichero.hasNext()) {
                //añade las lineas del fichero al Arraylist.
                lineas.add(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }

        // Abrimos el fichero de texto para sobreescribirlo
        // Eliminaremos el linea solicitada por el profesor utilizando el aula como referencia.
        try {
            FileWriter writer = new FileWriter(fichero);

            /**
             * En el siguiente bucle en la cual tenemos una condición indicamos
             * que si el split almacenado con la posición 0 es diferente al aula
             * indicada por el profesor este retornara un TRUE y realizara la
             * escritura sobre el fichero introduciendo la linea que toque y
             * luego dando un "Enter" (\n). Si por lo contrario la condición
             * retornase un FALSE este no imprimir la linea que le toca en el
             * archivo y quedaria eliminada. Luego devolveria el mensaje que
             * aparece en el ELSE.
             */
            for (String linea : lineas) {
                if (linea.split(",")[0].equals(aula)) {
                    System.out.println("Los registros del Aula " + aula + " han sido borrados correctamente");

                } else if (!linea.split(",")[0].equals(aula)) {
                    writer.write(linea + "\n");
                }
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");
        }
    }
    
    /**
     * Funcion encargada de actualizar Linea de fichero
     * @param fichero 
     */
    private static void actualizarLineaFichero(File fichero) {
        // Se añadió un lector dentro de esta función 
        //para que no surja problemas al momento de solicitar los datos.
        Scanner lector2 = new Scanner(System.in);
        //Se crea la variable aula dentro de esta funcion.
        String aula = "";
        //Se le indica al usurario El " codigo " de aula.
        System.out.println("Indiqueme que aula deseas modificar (Or**): ");
        aula = lector2.nextLine(); // Se recoge los datos por teclado
        System.out.println(aula);// Se imprime los datos ingresados por teclado

        String[] array = new String[6];//[7]
        String[] datos = new String[6];//[7]

        //Generamos 2 arrays uno para almacenar la respuesta del usuario
        //Y otro para imnprimir datos por pantalla.
        //datos[0] = "Aula (Or**) = ";
        datos[0] = "Descripción = ";
        datos[1] = "Capacidad = ";
        datos[2] = "PC (Si/No)= ";
        datos[3] = "Numero de PCs = ";
        datos[4] = "Proyector (Si/No) = ";
        datos[5] = "Insonorización (Si/No) = ";

        for (int i = 0, x = 0; i < array.length; i++, x++) {
            System.out.print(datos[x]);
            array[i] = lector.next();
        }

        // Array para guardar todas las líneas leídas del fichero
        ArrayList<String> lineas = new ArrayList<>();

        // Abrimos el fichero de texto para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(fichero);

            while (lectorFichero.hasNext()) {
                lineas.add(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }

        // Abrimos el fichero de texto para sobreescribirlo
        // Actualizaremos la línea 4
        try {
            FileWriter writer = new FileWriter(fichero);

            for (String linea : lineas) {
                if (linea.split(",")[0].equals(aula)) {
                    writer.write(aula + "," + array[0] + "," + array[1] + "," + array[2] + "," + array[3] + "," + array[4] + "," + array[5] + "\n");
                } else {
                    writer.write(linea + "\n");
                }
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");
        }
    }
    
    //--------------------------------------------------------------------------
    //----------------------      ADMINISTRADOR     ----------------------------
    //--------------------------------------------------------------------------
    
    /**
     * Funcion que imprime por pantalla el Menu para Admin.
     */
    private static void MenuAdmin() {
        int opcion = 0;
        boolean salir = false;
        do {

            System.out.println("---------- MENÚ ADMIN ----------");
            System.out.println("¿Que acción deseas realizar?");
            System.out.print("\t1.Crear un usuario\n"
                    + "\t2.Listar\n"
                    + "\t3.Modificar\n"
                    + "\t0.Salir\n---->");
            opcion = lector.nextInt();

            switch (opcion) {
                case 1:
                    CrearUsuario();
                    break;
                case 2:
                    ListarUsuarios();
                    break;
                case 3:
                    ModificarUsuarios();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
            }
        } while (!salir);
    }

    /**
     * Funcion para crear usuarios y almacenaros en el fichero binario
     * (Users.dat)
     */
    public static void CrearUsuario() {
        // A continuación llamamos a la función indicada para leer el fichero binario y almacenarlo en el Array personal []
        Usuarios personal[] = leerFicheroEnMemoria();

        boolean noEncontrado = true;
        //Luego con el siguiente bucle indicamos que si encuentra una posición vacia o igual a NULL está solicite datos para cada apartado.
        for (int i = 1; (i < personal.length) && noEncontrado; i++) {

            if (personal[i].Nombre == null) {

                personal[i] = new Usuarios();
                System.out.print("Rol : ");
                personal[i].Rol = lector.next();

                System.out.print("Nombre : ");
                personal[i].Nombre = lector.next();

                System.out.print("Id Usuario : ");
                personal[i].IdUsuario = lector.next();

                System.out.print("Contraseña : ");
                personal[i].Contraseña = lector.next();

                //Y alfinal dicha acción cambiamos el boolean para que rompa el bucle.
                noEncontrado = false;
            }

        }

        //  Guardamos los datos introducidos del array al fichero binario.
        guardarMemoriaEnFichero(personal);

    }

    /**
     * Funcion encargada de imprimir el listado de usuarios almacenado en el
     * fichero binario.
     */
    private static void ListarUsuarios() {
        //En primer lugar almacenamos los datos del fichero binario en un Array que conlleva una clase declarada. "Usuarios.java"
        Usuarios personal[] = leerFicheroEnMemoria();

        //Luego ejecutamos un bucle para imprimir todos los datos almacenados en array.
        for (Usuarios empleados : personal) {
            if (empleados != null) {
                System.out.println("Rol : " + empleados.Rol);
                System.out.println("Nombre : " + empleados.Nombre);
                System.out.println("ID Usuario : " + empleados.IdUsuario);
                System.out.println("Contraseña : " + empleados.Contraseña);
                System.out.println("--------------------------");
            }
        }
    }

    /**
     * Funcion encargada de modificar los parametros de un usuario existente.
     */
    private static void ModificarUsuarios() {

        String IdActualiz = "";

        //A continuación llamamos a la función indicada para leer el fichero binario y almacenarlo en el Array personal []
        Usuarios personal[] = leerFicheroEnMemoria();

        // Solicitamos el ID del usuario que se quiere modificar.
        System.out.print("Introduce el Id Usuario que quieres modificar: ");
        IdActualiz = lector.next();

        //Generamos un boolean para verificar si el usuario existe o no.
        boolean noEncontrado = true;

        // Este bucle se ejecutara hasta encontrar el ID indicado y una ves lo encuentre se ejecutara la siguiente condición.
        for (int i = 0; (i < personal.length) && noEncontrado; i++) {

            //En la condición solicitaremos los datos nuevos para el usuario. Y finalmente cambiaremos el boolean para que no continue el bucle.
            if (personal[i].Nombre != null && personal[i].IdUsuario.equals(IdActualiz)) {
                System.out.print("Rol : ");
                personal[i].Rol = lector.next();
                System.out.print("Nombre : ");
                personal[i].Nombre = lector.next();

                System.out.print("ID Usuario : ");
                personal[i].IdUsuario = lector.next();

                System.out.print("Contraseña : ");
                personal[i].Contraseña = lector.next();

                System.out.println("Registro actualizado correctamente!");
                noEncontrado = false;
            }
        }
        if (noEncontrado) {
            System.out.println("No existe ningún usuario con el Id Usuario: " + IdActualiz);
        }

        // Como paso final almacenaremos los cambios realizados en el fichero Binario,traspasando el Array a la siguiente funcion.
        guardarMemoriaEnFichero(personal);

    }

    /**
     * Funcion encargada de generar el perfil Admin siempre que inicie el
     * programa.
     */
    private static void GenerarPerfilAdmin() {
        //Declaramos que siempre la primera posición sea almacenada para el usuario Admin y de esta manera aun que se modifique o elimine dicho usuario
        //se podrá volver a generar siempre que se ejecute el programa.

        //Leemos el fichero binario y traspasamos los datos al Array para poder manipularlos.
        Usuarios personal[] = leerFicheroEnMemoria();

        personal[0] = new Usuarios();

        personal[0].Rol = "Admin";

        personal[0].Nombre = "Admin";

        personal[0].IdUsuario = "Admin";

        personal[0].Contraseña = "Admin";

        // Guardamos los datos introducidos del array al fichero binario.
        guardarMemoriaEnFichero(personal);
    }

    
    //--------------------------------------------------------------------------
    //-----FUNCIONES PARA INTERACTUAR CON EL FICHERO BINARIO "Users.dat"--------
    //--------------------------------------------------------------------------
    /**
     * Funcion encargada de leer el fichero en memoria y almacenarlo en
     * Usuarios[]personal.
     *
     * @return
     */
    private static Usuarios[] leerFicheroEnMemoria() {
        Usuarios[] personal = null;
        try {
            // A partir de aquí accederemos al fichero a leer mediante la variable fichero
            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream("Users.dat"));

            // Y rellenamos con lo recuperado de leer el fichero mediante readObject
            // readObject recibe todo un array de Empleados y por eso lo casteamos (Empleado[])
            personal = (Usuarios[]) fichero.readObject();

            // Cerramos el fichero
            fichero.close();

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al leer el fichero");
        }
        return personal;
    }

    /**
     * Funcion encargada de guardar los datos que teniamos en memoria
     * "Usuarios[] personal) a fichero binario.
     *
     * @param personal
     */
    private static void guardarMemoriaEnFichero(Usuarios[] personal) {
        try {
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("Users.dat"));

            fichero.writeObject(personal);

            fichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear/guardar el fichero");
        }
    }
    public static String Direccion(){
        
        String x="";
        try{
            //String host ="ortizol.blogspot.com";
            // Creamos un objeto de tipo Date e importamos su libreria.
            // Este objeto nos da una información es precisa incluyendo la hora
            Date fecha=new Date();
            // Creamos un objeto que nos permita cambiar de 
            // Date a string-- 00/00/0000 
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
            // Cambiamos el formato de date a string usando este metodo
            // Y así obtenemos los datos que deseamos.
            String a=fecha.toString();// formatoFecha.format(fecha);
        
            // Averiguamos la direccion del host local.
            // llamamos al objeto " InetAddress " y luego invocamos el metodo
            // " .getLocalhost();" para optener el nombre y la IP del host
            final InetAddress localhost =InetAddress.getLocalHost();
            // Transformamos el valor de localhost a String para poder almacenar 
            // y llamarlo en otra Funcion de almacen.
            String linea = localhost.toString();
            // Imprimimos para ver los valores que tiene cada variable
            System.out.printf("La direccion LocalHost es %s \n",linea +"\n"
                    + "La Fecha y Hora "+a);
            return linea+"\n"+a+"\n";
        }catch (Exception e){
        }
        return x;
    }
    public static void crearFichero(String dir){
        
        try {
            // Codificación ISO-8859-1 (ANSI) o UTF-8 dependiendo de cómo esté creado el fichero de texto
            // Agregamos "( , true)" para que no se cree de nuevo el fichero y almacene sucesivamente 
            // los datos que obtenemos de la funcion DIRECCION.
            FileWriter writer = new FileWriter(fichero, true);
            // Escribimos los datos que teniamos almacenados en el String dir
            // al fichero direcciones.txt
            writer.write(dir);
            // Cerramos el fichero para terminar de guardar los datos.
            //writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }
    }
}
