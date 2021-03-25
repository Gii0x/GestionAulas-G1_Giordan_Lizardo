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
