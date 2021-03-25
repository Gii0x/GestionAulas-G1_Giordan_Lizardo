/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_giordan_lizardo;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Gii0
 */
public class Proyecto_Giordan_Lizardo {

    static File fichero = new File("..\\Aulas\\clasroom.txt");
    public static void main(String[] args) {
    
    String [][] clases = new String[14][7];
        
    ImprimirDatosFichero(fichero);   
    
    }
    /**
     * Generamos una funcion que lee el fichero pasado por parametro e imprime los datos de las aulas.
     * @param fichero 
     */
    private static void ImprimirDatosFichero(File fichero) {
        String aula="", descripcion="",capacidad="",pc="",numpc="",proyectors="",insonorizado="";
        String linea="";
        try {
            // Codificación ISO-8859-1 (ANSI) o UTF-8 dependiendo de cómo esté creado el fichero de texto
            Scanner lectorFichero = new Scanner(fichero, "ISO-8859-1");
            
            while(lectorFichero.hasNext()) {
                
                System.out.println("");
                linea=lectorFichero.nextLine();
                //Utilizamos el metodo Split para recoger los datos del fichero txt.
                String [] parts = linea.split(",");
                aula= parts[0];
                descripcion = parts[1];
                capacidad = parts[2];
                pc = parts[3];
                numpc = parts[4];
                proyectors = parts[5];
                insonorizado = parts [6];
                
                System.out.println("Aula = "+aula+"\nDescripción = "+descripcion+"\nCapacidad = "+capacidad+"\nPC = "+pc+
                        "\nNumero de PCs = "+numpc+"\nProyectors = "+proyectors+"\nInsonorización = "+insonorizado);
                
            }
            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }
    }
    
}
    public static void main(String[] args) {
        Scanner lector=new Scanner(System.in);
        System.out.println("Opcion a realizar como profesor");
        int opcion=lector.nextInt();
        switch(opcion){
            case 1:
                System.out.println("Opcion de Crear un fichero");
                crearFichero();
                break;
            case 2:
                System.out.println("Opcion de Añadir lineas en el fichero");
                añadirLineasFichero();
                break;
            case 3:
                System.out.println("Opcion de Eliminar lineas en el fichero");
                eliminarLineaFichero();
                break;
            case 4:
                System.out.println("Opcion de Actualizar linea en el fichero");
                actualizarLineaFichero();
                break;
            default:
        }     
        //investigar el metodo split de la clase string
    }

    private static void crearFichero() {
        File fichero = new File("/Test/nuevo4.txt");
        
        try {
            FileWriter writer = new FileWriter(fichero);
            
            writer.write("Línea 1\n");
            writer.write("Línea 2\n");
            writer.write("Línea 3\n");
            
            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear/escribir en el fichero");
        }
    }

    private static void añadirLineasFichero() {
        File fichero = new File("/Test/nuevo4.txt");
        
        try {
            FileWriter writer = new FileWriter(fichero,true);
            
            writer.write("Línea 1\n");
            writer.write("Línea 2\n");
            writer.write("Línea 3\n");
            
            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear/escribir en el fichero");
        }
    }

    private static void eliminarLineaFichero() {
        File fichero = new File("C:/Test/nuevo.txt");
        
        // Array para guardar todas las líneas leídas del fichero
        ArrayList<String> lineas = new ArrayList<>();
        
        // Abrimos el fichero de texto para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(fichero);
            
            int i=0;
            
            while(lectorFichero.hasNext()) {
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
                if (!"Línea 3".equals(linea)) {
                    writer.write(linea + "\n");
                }
            }
            
            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");
        }
    }

    private static void actualizarLineaFichero() {
        File fichero = new File("C:/Test/nuevo.txt");
        
        // Array para guardar todas las líneas leídas del fichero
        ArrayList<String> lineas = new ArrayList<>();
        
        // Abrimos el fichero de texto para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(fichero);
            
            int i=0;
            
            while(lectorFichero.hasNext()) {
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
    
}