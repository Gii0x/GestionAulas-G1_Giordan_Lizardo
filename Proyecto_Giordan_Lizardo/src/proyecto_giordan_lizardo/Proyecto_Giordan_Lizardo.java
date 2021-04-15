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

/**
 *
 * @author Gii0
 */
public class Proyecto_Giordan_Lizardo {

    static Scanner lector = new Scanner(System.in);
    static File fichero = new File("..\\Aulas\\clasroom.txt");
    static int MAX = 7;

    public static void main(String[] args) {

        Login();

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
     * private static void ResetearFichero(File fichero) {
     *
     * try { FileWriter writer = new FileWriter(fichero);
     *
     * writer.write("Línea 1\n"); writer.write("Línea 2\n"); writer.write("Línea
     * 3\n");
     *
     * writer.close(); } catch (Exception e) { System.out.println("Ha ocurrido
     * un error al crear/escribir en el fichero"); } }
     */
    /**
     * Funcion que se encarga de añadir una linea adición al fichero clasroom
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

    /**
     *
     */
    public static void CrearUsuario() {
        // Lectura de archivo
        Usuarios personal []= leerFicheroEnMemoria();

        personal[0] = new Usuarios();

        personal[0].Rol = "Admin";

        personal[0].Nombre = "Admin";

        personal[0].IdUsuario = "Admin";

        personal[0].Contraseña = "Admin";

        boolean noEncontrado = true;

        for (int i = 1; (i < personal.length) && noEncontrado; i++) {
            //System.out.println(personal[i].equals(null));
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

                noEncontrado = false;
            }

        }

        // Escritura en archivo
        guardarMemoriaEnFichero(personal);

    }

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
            /* case 1:
                System.out.println("Opcion de Crear un fichero");
                ResetearFichero(fichero);
                break;
             */
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
                salir=true;
                break;
            default:
                
        }
            
        }while(!salir);
    }

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
                salir=true;
                break;
            default:
        }
        }while(!salir);
    }

    private static void ListarUsuarios() {
        Usuarios personal []= leerFicheroEnMemoria();
        
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

    private static void ModificarUsuarios() {
            //Usuarios[] personal = null;
            String IdActualiz="";
            Usuarios personal []= leerFicheroEnMemoria();
                    
            System.out.print("Introduce el Id Usuario que quieres modificar: ");
            IdActualiz = lector.next();
            
            boolean noEncontrado = true;
            
            for(int i = 0; (i<personal.length)&& noEncontrado;i++){
                System.out.println(personal[i].Nombre);
                if(personal[i].Nombre != null && personal[i].IdUsuario.equals(IdActualiz)){
                    System.out.print("Rol : ");
                    personal[i].Rol = lector.next();
                    System.out.print("Nombre : ");
                    personal[i].Nombre = lector.next();
                   
                    System.out.print("ID Usuario : ");
                    personal[i].IdUsuario = lector.next();
                    
                    System.out.print("Contraseña : ");
                    personal[i].Contraseña = lector.next();
                    
                    System.out.println("Registro actualizado correctamente!");
                    noEncontrado=false;
                }
            }
            if(noEncontrado){
                System.out.println("No existe ningún usuario con el Id Usuario: "+IdActualiz);
            }
            
            guardarMemoriaEnFichero(personal);
            
        }

    private static void Login() {
        boolean salir = true;
        char resp = ' ';
        do {
            System.out.println("----------Login----------");
            System.out.print("\tUsuario    : ");
            String Usuario = lector.next();
            System.out.print("\tContraseña : ");
            String Contraseña = lector.next();
            

            Usuarios personal []= leerFicheroEnMemoria();

            //for (Usuarios empleado : personal) {
            for (int i = 0;i<personal.length;i++){
                if (personal[i].Nombre != null && personal[i].IdUsuario.equals(Usuario) && personal[i].Contraseña.equals(Contraseña)) {
                    
                    if (personal[i].Rol.equals("Admin")) {
                        MenuAdmin();
                        salir = true;
                        break;
                    } else if (personal[i].Rol.equals("Professor")) {
                        System.out.println("Bienvenido : "+personal[i].Nombre);
                        MenuOpcionesProfessor(fichero);
                        salir = true;
                        break;
                    }
                }else if (i==(personal.length-1)){
                    System.out.println("No existe ningún usuario con el nombre "+Usuario);
                }
            }
            
            System.out.println("¿Deseas continuar?(S/N): ");
            resp = lector.next().toUpperCase().charAt(0);
            if (resp != 'N') {
                salir = false;
            }else {
                salir = true;
            }

        } while (!salir);
    }
    //--------FUNCIONES PARA INTERACTUAR CON EL FICHERO BINARIO "Users.dat"--------
    
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

    private static void guardarMemoriaEnFichero(Usuarios[] personal) {
        try {
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("Users.dat"));

            fichero.writeObject(personal);

            fichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear/guardar el fichero");
        }
    }
}
