/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_giordan_lizardo;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;

/**
 *
 * @author Gii0
 */
public class Proyecto_Giordan_Lizardo {

    static Scanner lector = new Scanner(System.in);
    static File fichero = new File("..\\Aulas\\clasroom.txt");

    public static void main(String[] args) {

        ImprimirDatosFichero(fichero);

        MenuOpcionesProfessor(fichero);

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

    private static void eliminarLineaFichero(File fichero) {
        String aula="";
        System.out.println("Indiqueme que aula desea eliminar (Or**): ");
        aula=lector.nextLine();
        lector.next();
        System.out.println(aula);
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
        // Eliminaremos la línea 3
        try {
            FileWriter writer = new FileWriter(fichero);
            
            for (String linea : lineas) {
                if(!linea.split(",")[0].equals (aula)){
                    writer.write(linea + "\n");
                }else {
                    System.out.println("El aula indicada no existe.");
                }
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");
        }
    }

    private static void actualizarLineaFichero(File fichero) {

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
                if ("Línea 4".equals(linea)) {
                    writer.write("Nueva Línea 4\n");
                } else {
                    writer.write(linea + "\n");
                }
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");
        }
    }

    private static void MenuOpcionesProfessor(File fichero) {

        System.out.println("Como Professor indica que acción desea realizar:");
        System.out.print("\t1. Crear un nuevo registro.\n"
                + "\t2. Modificar un registro.\n"
                + "\t3. Eliminiar un registro.\n"
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
            default:
        }
    }

}
